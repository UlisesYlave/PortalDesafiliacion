package pe.edu.pucp.pdm.portaldbtests.oferta;

import java.util.List;
import pe.edu.pucp.pdm.oferta.impl.EquipoDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Equipo;

public class PortalDBTestEquipo {

    public static void main(String[] args) {
        EquipoDAOImpl equipoDao = new EquipoDAOImpl();
        //insertar
        Equipo equipo = new Equipo("HUAWEI","Redmi 9",10000,499.9,"ACTIVO");
        int idE = equipoDao.insertar(equipo);
        System.out.println("El equipo con id: " + idE + " ha sido insertado correctamente");
        // modificar
        equipo.setPrecio(699.9);
        boolean modificado = equipoDao.modificar(equipo);
        if (modificado == true) System.out.println("El equipo con id: "+idE + " ha sido modificado");
        // eliminar
        boolean eliminado = equipoDao.eliminar(idE);
        if (eliminado == true) System.out.println("El equipo con id: "+idE+" ha sido eliminado");
        // buscar
        int idB = 1;
        equipo = equipoDao.buscar(idB);
        if (idB==equipo.getIdEquipo()) System.out.println("El equipo con id: "+idB + " ha sido encontrado");
        // listar
        List<Equipo> equipos = equipoDao.listar();
        System.out.println("Todos los  equipos:");
        for(Equipo e : equipos){
            System.out.println(e.getIdEquipo() + " " + e.getMarca() + " " + e.getModelo());
        }
    }
    
}
