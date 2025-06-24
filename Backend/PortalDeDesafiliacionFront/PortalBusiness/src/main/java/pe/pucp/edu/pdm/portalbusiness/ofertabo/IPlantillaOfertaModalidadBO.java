package pe.pucp.edu.pdm.portalbusiness.ofertabo;

import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;

public interface IPlantillaOfertaModalidadBO {
    List<Modalidad> listarModalidadesPorPlantilla(int idPlantilla);
}
