package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.IPrepagoBO;
import pe.pucp.edu.pdm.portalbusiness.servicioimpl.PrepagoBOImpl;

@WebService(serviceName = "PrepagoWS")
public class PrepagoWS {
    private final IPrepagoBO prepagoBO;
    
    public PrepagoWS(){
        this.prepagoBO = new PrepagoBOImpl();
    }
    
    @WebMethod(operationName = "eliminarPrepago")
    public boolean eliminarPrepago(@WebParam(name = "idLinea") int idLinea) {
        return prepagoBO.eliminarPrepago(idLinea);
    }
    
    @WebMethod(operationName = "migracionPrepagoAPostpago")
    public boolean migracionPrepagoAPostpago(
            @WebParam(name = "idLinea") int idLinea,
            @WebParam(name = "idPlan") int idPlan,
            @WebParam(name = "diaCicloFacturacion") int diaCicloFacturacion){
        return prepagoBO.migracionPrepagoAPostpago(idLinea,idPlan,diaCicloFacturacion);
    }
}
