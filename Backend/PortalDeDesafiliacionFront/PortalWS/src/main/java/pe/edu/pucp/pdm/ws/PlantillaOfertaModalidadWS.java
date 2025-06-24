package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;
import pe.pucp.edu.pdm.portalbusiness.ofertabo.IPlantillaOfertaModalidadBO;
import pe.pucp.edu.pdm.portalbusiness.ofertaimpl.PlantillaOfertaModalidadBOImpl;

@WebService(serviceName = "PlantillaOfertaModalidadWS")
public class PlantillaOfertaModalidadWS {
    private final IPlantillaOfertaModalidadBO pomBO;
    
    public PlantillaOfertaModalidadWS(){
        pomBO = new PlantillaOfertaModalidadBOImpl();
    }
    
    @WebMethod(operationName = "listarModalidadesPorPlantilla")
    public List<Modalidad> listarModalidadesPorPlantilla(@WebParam(name = "idPlantilla") int idPlantilla) {
        return pomBO.listarModalidadesPorPlantilla(idPlantilla);
    }
}
