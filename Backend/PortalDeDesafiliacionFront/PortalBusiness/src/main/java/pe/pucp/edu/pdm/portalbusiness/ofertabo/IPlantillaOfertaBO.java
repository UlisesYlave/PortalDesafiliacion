package pe.pucp.edu.pdm.portalbusiness.ofertabo;

import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Oferta;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;

public interface IPlantillaOfertaBO {
    List<PlantillaOferta> listar();
    
    int insertar(PlantillaOferta plantilla);
    
    boolean modificar(PlantillaOferta plantilla);
    
    boolean eliminar(int idPlantilla);
    
    public List<Oferta> generarOfertas(Prioridad prioridadCliente,Linea linea);
}
