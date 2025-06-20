package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.IPostpagoBO;
import pe.pucp.edu.pdm.portalbusiness.servicioimpl.PostpagoBOImpl;

/**
 *
 * @author alvma
 */
@WebService(serviceName = "PostpagoWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/"
        )
public class PostpagoWS {
    private final IPostpagoBO postpagoBO;

    public PostpagoWS(){
        this.postpagoBO = new PostpagoBOImpl();
    }
    //********************************************************
    //REQUISITO FUNCIONAL 3 (EVALUCAION DE DEUDAS)
    //********************************************************
    @WebMethod(operationName = "obtenerDeudaPendiente")
    public double obtenerDeudaPendiente(
        @WebParam(name = "idLinea") int idLinea
    ) {
        return postpagoBO.obtenerDeudaPendiente(idLinea);
    }
    
    @WebMethod(operationName = "eliminarPostpago")
    public boolean eliminarPostpago(
          @WebParam(name = "idLinea") int idLinea
    ){
        return postpagoBO.eliminarPostpago(idLinea);
    }
}
