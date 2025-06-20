package pe.edu.pucp.pdm.portaldbtests.servicio;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.servicio.impl.LineaDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.SolicitudDesafiliacionDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.serviciomodel.Resultado;
import pe.edu.pucp.pdm.serviciomodel.SolicitudDesafiliacion;
import pe.edu.pucp.pdm.usuario.impl.EncuestaDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Encuesta;
import pe.pucp.edu.pdm.portalbusiness.servicioimpl.SolicitudDesafiliacionBOImpl;

public class PortalDBTestSolicitudDesafiliacion {

    public static void main(String[] args) throws ParseException, IOException {
        SolicitudDesafiliacionDAOImpl solicitudDao = new SolicitudDesafiliacionDAOImpl();
        
        SolicitudDesafiliacionBOImpl solicitudBO = new SolicitudDesafiliacionBOImpl();
        List<SolicitudDesafiliacion> solicitudes = solicitudDao.listarSolicitudesDesafiliacionPorCliente(1);
        boolean creado = solicitudBO.exportToCsv(solicitudes);
    }
    
}
