package pe.pucp.edu.pdm.portalbusiness.ofertabo;

import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Equipo;

public interface IEquipoBO {
    List<Equipo> listar();
    int insertar(Equipo equipo);
    
    
    boolean modificar(Equipo equipo);
    boolean eliminar(int idEquipo);
    Equipo buscarPorId(int idEquipo);
}