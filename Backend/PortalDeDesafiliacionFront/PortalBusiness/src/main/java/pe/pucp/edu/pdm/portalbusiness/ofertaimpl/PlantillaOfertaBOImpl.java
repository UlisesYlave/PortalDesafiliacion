package pe.pucp.edu.pdm.portalbusiness.ofertaimpl;

import java.util.List;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaDAO;
import pe.edu.pucp.pdm.oferta.impl.PlantillaOfertaDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.pucp.edu.pdm.portalbusiness.ofertabo.IPlantillaOfertaBO;

public class PlantillaOfertaBOImpl implements IPlantillaOfertaBO{
    
    private final IPlantillaOfertaDAO plantillaDAO;

    public PlantillaOfertaBOImpl(){
        this.plantillaDAO = new PlantillaOfertaDAOImpl();
    }
    
    @Override
     public List<PlantillaOferta> listar(){
         return plantillaDAO.listar();
     }
     
     @Override
     public int insertar(PlantillaOferta plantilla){
         return plantillaDAO.insertar(plantilla);
     }
     
     @Override
     public boolean modificar(PlantillaOferta plantilla){
         return plantillaDAO.modificar(plantilla);
     }
     
     @Override
     public boolean eliminar(int idPlantilla){
         return plantillaDAO.eliminar(idPlantilla);
     }
}
