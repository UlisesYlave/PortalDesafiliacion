package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Oferta;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;
import pe.pucp.edu.pdm.portalbusiness.ofertabo.IPlantillaOfertaBO;
import pe.pucp.edu.pdm.portalbusiness.ofertaimpl.PlantillaOfertaBOImpl;

@WebService(serviceName = "PlantillaOfertaWS")
public class PlantillaOfertaWS {
    
    private final IPlantillaOfertaBO plantillaOfertaBO;
    
    public PlantillaOfertaWS(){
        this.plantillaOfertaBO = new PlantillaOfertaBOImpl();
    }
    
    @WebMethod(operationName = "listarPlantillasOfertas")
    public List<PlantillaOferta> listarPlantillasOfertas() {
        return plantillaOfertaBO.listar();
    }
    
    @WebMethod(operationName = "insertarPlantilla")
    public int insertarPlantilla( @WebParam(name = "plantilla") PlantillaOferta plantilla){
        return plantillaOfertaBO.insertar(plantilla);
    }
    
    @WebMethod(operationName = "modificarPlantilla")
    public boolean modificarPlantilla( @WebParam(name = "plantilla") PlantillaOferta plantilla){
        return plantillaOfertaBO.modificar(plantilla);
    }
    
    @WebMethod(operationName = "eliminarPlantilla")
    public boolean eliminarPlantilla( @WebParam(name = "idPlantilla") int idPlantilla){
        return plantillaOfertaBO.eliminar(idPlantilla);
    }
    @WebMethod(operationName = "generarOfertas")
    public List<Oferta> generarOfertas(
            @WebParam(name = "prioridadCliente")Prioridad prioridadCliente,
            @WebParam(name = "idLinea")Linea linea
    ){
        return plantillaOfertaBO.generarOfertas(prioridadCliente,linea);
    }
}
