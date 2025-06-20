package pe.edu.pucp.pdm.servicio.dao;

import java.util.List;
import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.serviciomodel.SolicitudDesafiliacion;

public interface ISolicitudDesafiliacionDAO extends ICrud<SolicitudDesafiliacion>{
    public List<SolicitudDesafiliacion> listarSolicitudesDesafiliacionPorCliente(int id);
    

    public boolean actualizarResultadoDesafiliacion(int idSolicitud);

}
