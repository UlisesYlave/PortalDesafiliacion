/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pdm.ws;

import java.util.List;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.IOException;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.ISolicitudDesafiliacionBO;
import pe.pucp.edu.pdm.portalbusiness.servicioimpl.SolicitudDesafiliacionBOImpl;
import pe.edu.pucp.pdm.serviciomodel.SolicitudDesafiliacion;
import pe.edu.pucp.pdm.serviciomodel.Resultado;
import java.util.Date;
@WebService(
        serviceName = "SolicitudDesafiliacionWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/")
public class SolicitudDesafiliacionWS {

    private final ISolicitudDesafiliacionBO solicitudBO;

    public SolicitudDesafiliacionWS() {
        this.solicitudBO = new SolicitudDesafiliacionBOImpl();
    }
    //********************************************************
    //REQUISITO FUNCIONAL 1 (DESAFILIACION DE SERVICIO MOVIL)
    //********************************************************
    @WebMethod(operationName = "insertarSolicitud")
    public int insertarSolicitud(
        @WebParam(name = "idLinea") int idLinea,
        @WebParam(name = "idCliente") int idCliente,
        @WebParam(name = "fechaSolicitud") Date fechaSolicitud,
        @WebParam(name = "resultado") Resultado resultado,
        @WebParam(name = "observacionAgente") String observacionAgente)
    {
        SolicitudDesafiliacion solicitud = new SolicitudDesafiliacion(idLinea,
                    idCliente,fechaSolicitud,Resultado.NO_DESAFILIADO,observacionAgente);
        return solicitudBO.insertarSolicitud(solicitud);
    }

    @WebMethod(operationName = "modificarSolicitud")
    public boolean modificarSolicitud(
            SolicitudDesafiliacion solicitud) {
        return solicitudBO.modificarSolicitud(solicitud);
    }

    @WebMethod(operationName = "eliminarSolicitud")
    public boolean eliminarSolicitud(
            int idSolicitud) {
        return solicitudBO.eliminarSolicitud(idSolicitud);
    }

    @WebMethod(operationName = "obtenerSolicitud")
    public SolicitudDesafiliacion obtenerSolicitud(
            int idSolicitud) {
        
        return solicitudBO.obtenerSolicitud(idSolicitud);
    }
    //********************************************************
    //REQUISITO FUNCIONAL 4 (HISTORIAL DE DESAFILIACIONES)
    //********************************************************
    @WebMethod(operationName = "listarSolicitudes")
    public List<SolicitudDesafiliacion> listarSolicitudes() {
        return solicitudBO.listarSolicitudes();
    }
    
    @WebMethod(operationName = "listarSolicitudesPorCliente")
    public List<SolicitudDesafiliacion> listarSolicitudesPorCliente(int idCliente){
        return solicitudBO.listarSolicitudesDesafiliacionPorCliente(idCliente);
    }
    
    @WebMethod(operationName = "exportToExcelPorCliente")
    public boolean exportToExcelPorCliente(
            @WebParam(name = "idCliente") int idCliente) throws IOException {
        return solicitudBO.exportToExcelPorCliente(idCliente);
    }
    
    @WebMethod(operationName = "exportToCsvPorCliente")
    public boolean exportToCsvPorCliente(
            @WebParam(name = "idCliente") int idCliente) throws IOException {
        return solicitudBO.exportToCsvPorCliente(idCliente);
    }
    
    /*
    RF12 - SOLICITAR CONFIRMACION VIA SMS, CON UN CÒDIGO
    */
    @WebMethod(operationName = "enviarCodigoSMS")
    public String enviarCodigoSMS(@WebParam(name = "idLinea") int idLinea) {
        return solicitudBO.enviarCodigoSMS(idLinea);  // En BO llamas al DAO o a utilitario SMS
    }

    @WebMethod(operationName = "validarCodigoSMS")
    public boolean validarCodigoSMS(
        @WebParam(name = "idLinea") int idLinea,
        @WebParam(name = "codigoIngresado") String codigoIngresado
    ) 
    {
        return solicitudBO.validarCodigoSMS(idLinea, codigoIngresado);
    }
    
    @WebMethod(operationName = "actualizarResultadoDesafiliacion")
    public boolean actualizarResultadoDesafiliacion(@WebParam(name = "idLinea") int idLinea) {
        return solicitudBO.actualizarResultadoDesafiliacion(idLinea);
    }
    
}
