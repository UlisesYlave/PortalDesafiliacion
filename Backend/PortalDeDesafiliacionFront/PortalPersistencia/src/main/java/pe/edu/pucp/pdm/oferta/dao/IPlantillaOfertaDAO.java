package pe.edu.pucp.pdm.oferta.dao;

import java.util.List;
import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.ofertamodel.Oferta;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;

public interface IPlantillaOfertaDAO extends ICrud<PlantillaOferta>{
    public List<Oferta>generarOfertas(Prioridad prioridadCliente,Linea linea);
}
