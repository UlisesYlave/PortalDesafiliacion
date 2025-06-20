package pe.pucp.edu.pdm.portalbusiness.ofertaimpl;

import java.util.List;
import pe.edu.pucp.pdm.oferta.dao.IEquipoDAO;
import pe.edu.pucp.pdm.oferta.impl.EquipoDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Equipo;
import pe.pucp.edu.pdm.portalbusiness.ofertabo.IEquipoBO;

public class EquipoBOImpl implements IEquipoBO {
    private final IEquipoDAO equipoDAO;

    public EquipoBOImpl() {
        this.equipoDAO = new EquipoDAOImpl();
    }
    
    @Override
    public List<Equipo> listar() {
        return equipoDAO.listar();
    }
    
    @Override
    public int insertar(Equipo equipo) {
         return equipoDAO.insertar(equipo);
    }
    

    @Override
    public boolean modificar(Equipo equipo) {
        return equipoDAO.modificar(equipo);
    }

    @Override
    public boolean eliminar(int idEquipo) {
        return equipoDAO.eliminar(idEquipo);
    }

    @Override
    public Equipo buscarPorId(int idEquipo) {
        return equipoDAO.buscar(idEquipo);
    }
}