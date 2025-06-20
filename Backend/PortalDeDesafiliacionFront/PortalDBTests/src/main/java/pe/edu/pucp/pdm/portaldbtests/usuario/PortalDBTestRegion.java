
package pe.edu.pucp.pdm.portaldbtests.usuario;

import java.util.List;
import pe.edu.pucp.pdm.usuariomodel.Region;
import pe.edu.pucp.pdm.usuario.impl.RegionDAOImpl;

public class PortalDBTestRegion {

    public static void main(String[] args) {
        RegionDAOImpl regionDao = new RegionDAOImpl();

        // insertar
        Region region = new Region("Sur",300,12000);
        int id = regionDao.insertar(region);
        region.setIdRegion(id);
        System.out.println("La region con id: " + id + " ha sido ingresada correctamente.");
        // modificar
        region.setNumeroBajasRegion(500);
        boolean modificado = regionDao.modificar(region);
        if (modificado==true) System.out.println("La region ha sido modificado correctamente.");
        //eliminar
        boolean eliminado = regionDao.eliminar(id);
        if (eliminado==true) System.out.println("La region " + region.getNombre() + " ha sido eliminada.");
        //buscar
        int idB = 1;
        region = regionDao.buscar(idB);
        if (region.getIdRegion()==idB){
            System.out.println("La region con id:" + idB + " ha sido encontrada");
            System.out.println("Nombre: " + region.getNombre());
            System.out.println("Numero de Bajas: " + region.getNumeroBajasRegion());
            System.out.println("Numero de Usuarios: " + region.getNumeroUsuariosRegion());
        }
        //listar
        System.out.println("Todas las regiones: ");
        List<Region> regiones = regionDao.listar();
        for(Region r : regiones){
            System.out.println(r.getNombre() + "  " + r.getNumeroBajasRegion() +"  "+  r.getNumeroUsuariosRegion());
        }
    }
    
}
