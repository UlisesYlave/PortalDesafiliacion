package pe.edu.pucp.pdm.portaldbtests.servicio;

import java.util.List;
import pe.edu.pucp.pdm.servicio.impl.PlanDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Plan;

public class PortalDBTestPlan {

    public static void main(String[] args) {
        PlanDAOImpl planDao = new PlanDAOImpl();
        //insertar
        Plan plan = new Plan("Plan 2","200MB",200.0);
        int idP = planDao.insertar(plan);
        plan.setIdServicio(idP);
        System.out.println("El Plan con id: " + idP + " ha sido insertado correctamente");
        // modificar
        plan.setPrecio(150);
        boolean modificado = planDao.modificar(plan);
        if (modificado == true) System.out.println("El Plan con id: "+idP + " ha sido modificado");
        // eliminar
        boolean eliminado = planDao.eliminar(idP);
        if (eliminado == true) System.out.println("El Plan con id: " + idP + " ha sido eliminado");
        // buscar
        int idB = 1;
        plan = planDao.buscar(idB);
        if (idB == plan.getIdServicio()) System.out.println("El Plan con id: "+idB + " ha sido encontrado");
        // listar
        List<Plan> planes = planDao.listar();
        System.out.println("Todos los planes");
        for(Plan p : planes){
            System.out.println(p.getIdServicio() + " " + p.getBeneficios());
        }
    }
    
}
