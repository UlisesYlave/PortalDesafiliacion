package pe.pucp.edu.pdm.portalbusiness.servicioimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.pdm.servicio.dao.ILineaDAO;
import pe.edu.pucp.pdm.servicio.dao.IPlanDAO;
import pe.edu.pucp.pdm.servicio.dao.IPostpagoDAO;
import pe.edu.pucp.pdm.servicio.dao.IPrepagoDAO;
import pe.edu.pucp.pdm.servicio.impl.LineaDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PlanDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PostpagoDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PrepagoDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Plan;
import pe.edu.pucp.pdm.serviciomodel.Postpago;
import pe.edu.pucp.pdm.serviciomodel.Prepago;
import pe.pucp.edu.pdm.portalbusiness.serviciobo.IPrepagoBO;

public class PrepagoBOImpl implements IPrepagoBO{
    private final ILineaDAO lineaDAO;
    private final  IPrepagoDAO prepagoDAO;
    private final IPostpagoDAO postpagoDAO;
    private final IPlanDAO planDAO;
    public PrepagoBOImpl(){
        this.prepagoDAO = new PrepagoDAOImpl();
        this.lineaDAO = new LineaDAOImpl();
        this.postpagoDAO = new PostpagoDAOImpl();
        this.planDAO = new PlanDAOImpl();
    }
    
    @Override
    public List<Prepago> listar(){
        return prepagoDAO.listar();
    }
    
    @Override
    public boolean eliminarPrepago(int idLinea){
        boolean eliminado = prepagoDAO.eliminar(idLinea);
        if (eliminado) return lineaDAO.eliminar(idLinea);
        else return false;
    }
    @Override
    public  boolean migracionPrepagoAPostpago(int idPrepago,int idPlan, int diaCicloFacturacion){
        Prepago prepago = prepagoDAO.buscar(idPrepago);
        boolean eliminado = prepagoDAO.eliminar(idPrepago);
       if (eliminado){
            Date fechaActual = new Date();
           Postpago postpago = new Postpago(
                   prepago.getIdCliente(),
                   prepago.getNumeroTelefono(),
                   fechaActual,
                   0,
                   diaCicloFacturacion
         );
           postpago.setIdLinea(idPrepago);
           Plan plan = planDAO.buscar(idPlan);
           if (plan == null) return false;
           postpago.setPlan(plan);
           int idPostpago = postpagoDAO.insertar(postpago);
           if (idPostpago == idPrepago)return true;
           else return false;
       }else return false;
    }
}
