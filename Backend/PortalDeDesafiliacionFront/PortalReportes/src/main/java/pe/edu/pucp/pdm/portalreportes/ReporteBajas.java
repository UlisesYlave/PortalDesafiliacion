/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.edu.pucp.pdm.portalreportes;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import pe.edu.pucp.pdm.config.DBManager;

@WebServlet(name = "ReporteBajas", urlPatterns = {"/ReporteBajas"})
public class ReporteBajas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1) Recoge las cadenas y conviérte a java.sql.Date
        String inicioStr = request.getParameter("fechaInicio");
        String finStr    = request.getParameter("fechaFin");
        Date fi, ff;
        try {
            fi = Date.valueOf(inicioStr);
            ff = Date.valueOf(finStr);
        } catch (IllegalArgumentException e) {
            throw new ServletException(
              "Formato de fecha inválido; debe ser yyyy-MM-dd", e);
        }

        // 2) Mete los valores con los nombres que espera tu JRXML (Fini, ffinal)
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Fini",   fi);
        parametros.put("ffinal", ff);

        // 3) Prepara la respuesta como PDF
        response.setContentType("application/pdf");

        // 4) Conexión y carga del .jasper desde el classpath
        try ( Connection conn = DBManager.getInstance().getConnection();
              InputStream jasperStream = getClass()
                  .getClassLoader()
                  .getResourceAsStream("reportes/ReporteBajas.jasper") ) {

            if (jasperStream == null) {
                throw new ServletException(
                  "No se encontró reportes/ReporteBajas.jasper en el classpath");
            }

            // 5) Llenar y exportar
            JasperPrint jp  = JasperFillManager.fillReport(jasperStream, parametros, conn);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jp);

            // 6) Escribir PDF en la respuesta
            response.setContentLength(pdfBytes.length);
            response.getOutputStream().write(pdfBytes);

        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new ServletException("Error generando reporte de bajas", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para reporte de bajas usando parámetros Fini y ffinal";
    }
}
