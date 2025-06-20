package pe.pucp.edu.pdm.portalbusiness.serviciobo;

import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.Postpago;

public interface IPostpagoBO {

    List<Postpago> listar();
    
    double obtenerDeudaPendiente(int idLinea);
    
    boolean eliminarPostpago(int idLinea);
}
