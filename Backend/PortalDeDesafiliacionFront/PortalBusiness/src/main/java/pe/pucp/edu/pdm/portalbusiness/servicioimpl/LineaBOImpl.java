package pe.pucp.edu.pdm.portalbusiness.servicioimpl;

import java.util.List;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.ILineaBO;
import pe.edu.pucp.pdm.servicio.dao.ILineaDAO;
import pe.edu.pucp.pdm.servicio.dao.IPostpagoDAO;
import pe.edu.pucp.pdm.servicio.impl.LineaDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PostpagoDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.serviciomodel.Postpago;

public class LineaBOImpl implements ILineaBO{
    private final ILineaDAO lineaDAO;
    private final IPostpagoDAO postpagoDAO;
    
    public LineaBOImpl(){
        this.lineaDAO = new LineaDAOImpl();
        this.postpagoDAO = new PostpagoDAOImpl();
    }
    @Override
    public List<Linea> listarLineasPorCliente(int idCliente){
        return lineaDAO.listarLineaPorCliente(idCliente);
    }
    
    @Override
    public boolean desafiliarLinea(int idLinea){
        Linea linea = lineaDAO.buscar(idLinea);
        if (linea == null) return false;
        
        linea.setActiva(true);
        lineaDAO.modificar(linea);
        return true;
    }
}
