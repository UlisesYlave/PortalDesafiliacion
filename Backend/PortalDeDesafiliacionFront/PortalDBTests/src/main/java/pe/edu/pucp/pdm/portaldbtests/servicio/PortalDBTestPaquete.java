package pe.edu.pucp.pdm.portaldbtests.servicio;

import java.util.List;
import pe.edu.pucp.pdm.servicio.impl.PaqueteDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Paquete;

public class PortalDBTestPaquete {

    public static void main(String[] args) {
        PaqueteDAOImpl paqueteDao = new PaqueteDAOImpl(); 
        //insertar
        Paquete paquete = new Paquete("Paquete 4","GB ilimitado",200,15);
        int idP = paqueteDao.insertar(paquete);
        paquete.setIdServicio(idP);
        System.out.println("El Paquete con id: " + idP + " ha sido insertado correctamente");
        // modificar
        paquete.setBeneficios("GB ilimitado 200Mbps");
        boolean modificado = paqueteDao.modificar(paquete);
        if (modificado == true) System.out.println("El Paquete con id: "+idP+" ha sido modificado");
        // eliminar
        boolean eliminado = paqueteDao.eliminar(idP);
        if (eliminado == true) System.out.println("El Paquete con id: " + idP + " ha sido eliminado");
        // buscar
        int idB = 1;
        paquete = paqueteDao.buscar(idB);
        if (idB == paquete.getIdServicio()) System.out.println("El Paquete con id: " + idB  +" ha sido encontrado");
        // listar
        List<Paquete> paquetes = paqueteDao.listar();
        System.out.println("Todos los  paquetes:");
        for(Paquete p: paquetes){
            System.out.println(p.getIdServicio() + " " + p.getNombreServicio());
        }
    }
    
}
