package pe.edu.pucp.pdm.oferta.dao;

import java.util.List;
import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOfertaParametro;

public interface IPlantillaOfertaParametroDAO extends ICrud<PlantillaOfertaParametro>{
    public List<PlantillaOfertaParametro>listarPlantillaOfertaParametros(int idPlantilla);
    public String obtenerValorParametro(String palabraOriginal);
}
