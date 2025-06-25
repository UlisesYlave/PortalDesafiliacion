package pe.pucp.edu.pdm.portalbusiness.servicioimpl;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.ISolicitudDesafiliacionBO;
import pe.edu.pucp.pdm.serviciomodel.SolicitudDesafiliacion;
import pe.edu.pucp.pdm.servicio.dao.ISolicitudDesafiliacionDAO;
import pe.edu.pucp.pdm.servicio.impl.SolicitudDesafiliacionDAOImpl;
import pe.edu.pucp.pdm.usuario.dao.IClienteDAO;
import pe.edu.pucp.pdm.usuario.impl.ClienteDAOImpl;

/**
 * Capa de negocio (BO) para SolicitudDesafiliacion.
 * Simplemente delega en el DAO, pero aquí podrías agregar validaciones adicionales.
 */
public class SolicitudDesafiliacionBOImpl implements ISolicitudDesafiliacionBO {
    private final ISolicitudDesafiliacionDAO solicitudDAO;
    private static final Map<Integer, String> codigosSMS = new HashMap<>();
    
    public SolicitudDesafiliacionBOImpl() {
        this.solicitudDAO = new SolicitudDesafiliacionDAOImpl();
    }

    @Override
    public int insertarSolicitud(SolicitudDesafiliacion solicitud) {
        // ej. validar que la línea exista, que no haya otra solicitud pendiente, etc.
        return solicitudDAO.insertar(solicitud);
    }

    @Override
    public boolean modificarSolicitud(SolicitudDesafiliacion solicitud) {
        return solicitudDAO.modificar(solicitud);
    }

    @Override
    public boolean eliminarSolicitud(int idSolicitud) {
        return solicitudDAO.eliminar(idSolicitud);
    }

    @Override
    public SolicitudDesafiliacion obtenerSolicitud(int idSolicitud) {
        return solicitudDAO.buscar(idSolicitud);
    }

    @Override
    public List<SolicitudDesafiliacion> listarSolicitudes() {
        return solicitudDAO.listar();
    }
    
    @Override
    public List<SolicitudDesafiliacion>listarSolicitudesDesafiliacionPorCliente(int idCliente){
        return solicitudDAO.listarSolicitudesDesafiliacionPorCliente(idCliente);
    }
    
   @Override
    public boolean exportToExcel(List<SolicitudDesafiliacion> solicitudes) throws IOException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
             String fechaStr = sdf.format(new Date());
            // 1. Generar archivo localmente
            String fileName = String.format("Desafiliaciones_%s.xlsx", fechaStr);
            String downloadsPath = Paths.get(System.getProperty("user.home"), "Downloads", fileName).toString();

            // 1. Crear workbook y hoja
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Solicitudes de Desafiliación");

            // 2. Crear fila de encabezados
            String[] headers = {"ID", "IDLinea", "IDCliente", "Fecha Solicitud", "Resultado", "IDEncuesta", "Motivo", "Oferta"};
            XSSFRow headerRow = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 3. Llenar datos desde la lista
            int rowNum = 1;
            for (SolicitudDesafiliacion solicitud : solicitudes) {
                XSSFRow row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(solicitud.getIdSolicitud());
                row.createCell(1).setCellValue(solicitud.getIdLinea());
                row.createCell(2).setCellValue(solicitud.getIdCliente());
                row.createCell(3).setCellValue(solicitud.getFechaSolicitud().toString());
                row.createCell(4).setCellValue(solicitud.getResultado().name());
                row.createCell(5).setCellValue(solicitud.getEncuesta().getIdEncuesta());
                row.createCell(6).setCellValue(solicitud.getEncuesta().getMotivo().getNombre());
                row.createCell(7).setCellValue((solicitud.getIdOferta() != null) ? solicitud.getIdOferta() : -1);
            }

            // 4. Autoajustar columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 5. Convertir a bytes
            FileOutputStream out = new FileOutputStream(downloadsPath);
                workbook.write(out);
                out.close();

                return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falla
        }
    }

    @Override
    public boolean exportToCsv(List<SolicitudDesafiliacion> desafiliaciones) throws IOException {
        try {
            // 1. Definir ruta y nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String fileName = String.format("Desafiliaciones_%s.csv", sdf.format(new Date()));

            String downloadsPath = Paths.get(System.getProperty("user.home"), "Downloads", fileName).toString();

            // 2. Crear archivo CSV
            FileWriter writer = new FileWriter(downloadsPath);

            // Escribir encabezados
            writer.append("ID,IDLinea,IDCliente,Fecha Solicitud,Resultado,IDEncuesta,Motivo,Oferta\n");

            // Escribir datos
            for (SolicitudDesafiliacion s : desafiliaciones) {
                writer.append(String.format("%d,%s,%s,%s,%s,%d,%s,%d\n",
                    s.getIdSolicitud(),
                    s.getIdLinea(),
                    s.getIdCliente(),
                    s.getFechaSolicitud().toString(),
                    s.getResultado().name(),
                    s.getEncuesta().getIdEncuesta(),
                    s.getEncuesta().getMotivo().getNombre(),
                    s.getIdOferta() != null ? s.getIdOferta() : -1
                ));
            }

            writer.flush();
            writer.close();

            return true;
        } catch (Exception e) {
            System.err.println("Error al generar CSV: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean exportToExcelPorCliente(int idCliente) throws IOException{
        List<SolicitudDesafiliacion> solicitudes = listarSolicitudesDesafiliacionPorCliente(idCliente);
        if (solicitudes.isEmpty()) return false;
        if (exportToExcel(solicitudes)) return true;
        return false;
    }

    @Override
    public boolean exportToCsvPorCliente(int idCliente) throws IOException{
        List<SolicitudDesafiliacion> solicitudes = listarSolicitudesDesafiliacionPorCliente(idCliente);
        if (solicitudes.isEmpty()) return false;
        if (exportToCsv(solicitudes)) return true;
        return false;
    }

    @Override
    public String enviarCodigoSMS(int idLinea) {
        try {
        /*──────────────── 1. Leer el token y el número real ───────────────*/
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader()
                                     .getResourceAsStream("config.properties");
        props.load(input);

        String tokenEncoded   = props.getProperty("api.sms.token");
        String tokenDecodificado = new String(Base64.getDecoder()
                                                   .decode(tokenEncoded));

        
        String realNumberEncoded = props.getProperty("sms.real.receiver");
        String numero = new String(Base64.getDecoder().decode(realNumberEncoded));

        /*──────────────── 2. Generar código aleatorio ─────────────────────*/
        String codigo = String.valueOf((int) (Math.random() * 900000) + 100000);
        codigosSMS.put(idLinea, codigo);      // Guardar para la validación
        
        TrustManager[] trustAll = { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers(){ return null; }
            public void checkClientTrusted (java.security.cert.X509Certificate[] c,String a){}
            public void checkServerTrusted (java.security.cert.X509Certificate[] c,String a){}
        }};
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAll, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((h, s) -> true);
        
        /*──────────────── 3. Preparar POST y enviar ──────────────────────*/
        String mensaje = URLEncoder.encode("Tu código de verificación es: " + codigo, "UTF-8");
        String parametros = "celular=" + numero +
                            "&texto=" + mensaje +
                            "&token="   + tokenDecodificado;      // según instructivo

        URL url = new URL("http://cr.net.pe/enviar_sms.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(parametros.getBytes("UTF-8"));
        }

        /*──────────────── 4. Leer la respuesta ───────────────────────────*/
        int responseCode = conn.getResponseCode();
        String body;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    responseCode < 400 ? conn.getInputStream()     // 2xx
                                       : conn.getErrorStream(),    // 4xx/5xx
                    "UTF-8"))) {
            body = br.lines().reduce("", (a, b) -> a + b);
        }
        /*──────────────── 5. Retornar siempre un String no-nulo ───────────*/
        return (responseCode == 200) ? codigo : "ERROR_HTTP_" + responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR_EXCEP";
        }
    }

    @Override
    public boolean validarCodigoSMS(int Linea, String codigoIngresado) {
        String codigoCorrecto = codigosSMS.get(Linea);

        if (codigoCorrecto != null && codigoCorrecto.equals(codigoIngresado)) {
            codigosSMS.remove(Linea); // Código usado, se elimina
            return true;
        }
        return false;
    }
    
    @Override
    public boolean actualizarResultadoDesafiliacion(int idLinea) {
        return solicitudDAO.actualizarResultadoDesafiliacion(idLinea);
    }
}
