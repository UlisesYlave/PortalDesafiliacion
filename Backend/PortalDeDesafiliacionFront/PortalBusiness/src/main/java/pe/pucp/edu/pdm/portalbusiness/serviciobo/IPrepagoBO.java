package pe.pucp.edu.pdm.portalbusiness.serviciobo;

import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.Prepago;

public interface IPrepagoBO {
    
    List<Prepago> listar();
    
    boolean eliminarPrepago(int idLinea);
    
    boolean migracionPrepagoAPostpago(int idPrepago,int idPlan,int diaCicloFacturacion);
}
