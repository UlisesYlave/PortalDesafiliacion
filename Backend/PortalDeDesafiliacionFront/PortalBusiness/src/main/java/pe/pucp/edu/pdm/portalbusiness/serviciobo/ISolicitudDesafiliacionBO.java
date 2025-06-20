package pe.pucp.edu.pdm.portalbusiness.serviciobo;

import java.io.IOException;
import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.SolicitudDesafiliacion;

public interface ISolicitudDesafiliacionBO {

    int insertarSolicitud(SolicitudDesafiliacion solicitud);

    boolean modificarSolicitud(SolicitudDesafiliacion solicitud);

    boolean eliminarSolicitud(int idSolicitud);

    SolicitudDesafiliacion obtenerSolicitud(int idSolicitud);

    List<SolicitudDesafiliacion> listarSolicitudes();
    
    List<SolicitudDesafiliacion>listarSolicitudesDesafiliacionPorCliente(int idCliente);
    
    boolean exportToExcel(List<SolicitudDesafiliacion> desafiliaciones) throws IOException;

    boolean exportToCsv(List<SolicitudDesafiliacion> desafiliaciones) throws IOException;
    
    boolean exportToExcelPorCliente(int idCliente) throws IOException;

    boolean exportToCsvPorCliente(int idCliente) throws IOException;
    
    String enviarCodigoSMS(int idLinea);
    
    boolean validarCodigoSMS(int idLinea, String codigoIngresado) ;
    
    boolean actualizarResultadoDesafiliacion(int idLinea);
}
