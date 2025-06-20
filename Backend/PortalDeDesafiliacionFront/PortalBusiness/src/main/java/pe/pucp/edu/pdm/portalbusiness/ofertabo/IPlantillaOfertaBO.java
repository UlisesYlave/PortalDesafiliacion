package pe.pucp.edu.pdm.portalbusiness.ofertabo;

import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;

public interface IPlantillaOfertaBO {
    List<PlantillaOferta> listar();
    
    int insertar(PlantillaOferta plantilla);
    
    boolean modificar(PlantillaOferta plantilla);
    
    boolean eliminar(int idPlantilla);
}
