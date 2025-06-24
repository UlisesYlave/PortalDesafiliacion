package pe.pucp.edu.pdm.portalbusiness.ofertaimpl;

import java.util.List;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaModalidadDAO;
import pe.edu.pucp.pdm.oferta.impl.PlantillaOfertaModalidadDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;
import pe.pucp.edu.pdm.portalbusiness.ofertabo.IPlantillaOfertaModalidadBO;

public class PlantillaOfertaModalidadBOImpl  implements IPlantillaOfertaModalidadBO{
    private final IPlantillaOfertaModalidadDAO pomDAO;
    
    public PlantillaOfertaModalidadBOImpl(){
        pomDAO = new PlantillaOfertaModalidadDAOImpl();
    }
    
    @Override
    public List<Modalidad> listarModalidadesPorPlantilla(int idPlantilla){
        return pomDAO.listarModalidadesPorPlantilla(idPlantilla);
    }
}
