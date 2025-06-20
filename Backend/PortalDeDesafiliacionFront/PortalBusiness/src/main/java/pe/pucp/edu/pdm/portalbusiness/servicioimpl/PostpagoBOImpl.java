package pe.pucp.edu.pdm.portalbusiness.servicioimpl;

import java.util.List;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.IPostpagoBO;
import pe.edu.pucp.pdm.servicio.dao.ILineaDAO;
import pe.edu.pucp.pdm.serviciomodel.Postpago;
import pe.edu.pucp.pdm.servicio.dao.IPostpagoDAO;
import pe.edu.pucp.pdm.servicio.impl.LineaDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PostpagoDAOImpl;

public class PostpagoBOImpl implements IPostpagoBO {
    private final ILineaDAO lineaDAO;
    private final IPostpagoDAO postpagoDAO;

    public PostpagoBOImpl() {
        this.postpagoDAO = new PostpagoDAOImpl();
        this.lineaDAO = new LineaDAOImpl();
    }

    @Override
    public List<Postpago> listar(){
        return postpagoDAO.listar();
    }
    
    @Override
    public double obtenerDeudaPendiente(int idLinea) {
        Postpago p = postpagoDAO.buscar(idLinea);
        if (p == null) {
            return 0.0;
        }
        return p.getDeudaPendiente();
    }
    
    public boolean eliminarPostpago(int idLinea){
        boolean eliminado = postpagoDAO.eliminar(idLinea);
        if (eliminado) return lineaDAO.eliminar(idLinea);
        else return false;
    }
}

