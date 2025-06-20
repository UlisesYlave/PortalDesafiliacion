package pe.edu.pucp.pdm.servicio.dao;

import java.util.List;
import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.serviciomodel.Linea;

public interface ILineaDAO extends ICrud<Linea>{
    List<Linea> listarLineaPorCliente(int id);
}
