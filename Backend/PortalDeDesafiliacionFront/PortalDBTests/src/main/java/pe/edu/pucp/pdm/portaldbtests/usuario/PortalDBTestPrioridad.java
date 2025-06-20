package pe.edu.pucp.pdm.portaldbtests.usuario;

import java.util.List;
import pe.edu.pucp.pdm.usuario.impl.PrioridadDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;

public class PortalDBTestPrioridad {

    public static void main(String[] args) {
        PrioridadDAOImpl prioridadDao = new PrioridadDAOImpl();
        // insertar
        Prioridad prioridad = new Prioridad("Media");
        int id = prioridadDao.insertar(prioridad);
        prioridad.setIdPrioridad(id);
        System.out.println("La prioridad con id: " + id + " ha sido ingresada correctamente.");
        // modificar 
        prioridad.setNombre("MEDIA");
        boolean modificado = prioridadDao.modificar(prioridad);
        if (modificado==true)System.out.println("La prioridad "+ prioridad.getNombre() + "ha sido modificada correctamente.");
        // eliminar
        boolean eliminado = prioridadDao.eliminar(id);
        if (eliminado == true)System.out.println("La prioridad con id: " + id + " ha sido eliminada");
        // buscar
        int idB = 3;
        prioridad = prioridadDao.buscar(idB);
        if (prioridad.getIdPrioridad()==idB){
            System.out.println("Se encontró la prioridad de id: " + idB);
            System.out.println("Nombre: " + prioridad.getNombre());
        }
        // listar
        System.out.println("Todas las prioridades");
        List<Prioridad> prioridades = prioridadDao.listar();
        for(Prioridad p : prioridades){
            System.out.println(p.getIdPrioridad() + p.getNombre());
        }
    }
}
