package pe.edu.pucp.pdm.portaldbtests.servicio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.servicio.impl.LineaDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PlanDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PostpagoDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.serviciomodel.Plan;
import pe.edu.pucp.pdm.serviciomodel.Postpago;

public class PortalDBTestPostpago {

    public static void main(String[] args) throws ParseException {
        PostpagoDAOImpl postpagoDao = new PostpagoDAOImpl();
        //insertar
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
        
        LineaDAOImpl lineaDao = new LineaDAOImpl();
        Linea linea = new Linea(4,"953437565","POSTPAGO",formato.parse("03/02/2025"));
        int idL = lineaDao.insertar(linea);
        System.out.println(idL);
        
        PlanDAOImpl planDao = new PlanDAOImpl();
        Plan plan = planDao.buscar(1);
        Postpago postpago = new Postpago(4,"953437565",formato.parse("03/02/2025"),0,30);
        postpago.setIdLinea(idL);
        postpago.setPlan(plan);
        
        idL = postpagoDao.insertar(postpago);
        
        System.out.println("El postpago con id: " + idL + " ha sido insertado correctamente");
        // modificar
        postpago.setDeudaPendiente(10);
        boolean modificado = postpagoDao.modificar(postpago);
        if (modificado == true) System.out.println("El postpago con id: " + idL + " ha sido modificado");
        // eliminar
        boolean eliminado = postpagoDao.eliminar(idL);
        if (eliminado == true) System.out.println("El postpago con id: " + idL + " ha sido eliminado");
        // buscar
        int idB = 3;
        postpago = postpagoDao.buscar(idB);
        if (idB==postpago.getIdLinea())System.out.println("El postpago con id: " + idB + " ha sido encontrado");
        // listar
        List<Postpago> postpagos = postpagoDao.listar();
        System.out.println("Todos los  postpagos:");
        for(Postpago p : postpagos){
            System.out.println(p.getIdLinea() + " " + p.getDetalleServicio());
        }
    }
    
}
