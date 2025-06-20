package pe.pucp.edu.pdm.portalbusiness.serviciobo;

import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.Linea;

public interface ILineaBO {
    List<Linea> listarLineasPorCliente(int idCliente);
    
    boolean desafiliarLinea(int idLinea);
}
