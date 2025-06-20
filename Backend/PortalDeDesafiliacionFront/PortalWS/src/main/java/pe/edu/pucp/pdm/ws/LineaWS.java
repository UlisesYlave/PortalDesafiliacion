package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.ILineaBO;
import pe.pucp.edu.pdm.portalbusiness.servicioimpl.LineaBOImpl;

@WebService(serviceName = "LineaWS")
public class LineaWS {

    private final ILineaBO lineaBO;
    
    public LineaWS(){
        this.lineaBO = new LineaBOImpl();
    }
    
    @WebMethod(operationName = "listarLineasPorCliente")
    public List<Linea> listarLineasPorCliente(
            @WebParam(name = "idCliente") int idCliente
    ) {
        return lineaBO.listarLineasPorCliente(idCliente);
    }
    
    @WebMethod(operationName = "desafiliarLinea")
    public boolean desafiliarLinea(@WebParam(name = "idLinea") int idLinea){
        return lineaBO.desafiliarLinea(idLinea);
    }
}
