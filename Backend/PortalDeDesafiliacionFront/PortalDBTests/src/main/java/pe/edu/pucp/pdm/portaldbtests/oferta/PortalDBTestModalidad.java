package pe.edu.pucp.pdm.portaldbtests.oferta;

import java.util.List;
import pe.edu.pucp.pdm.oferta.impl.ModalidadDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;

public class PortalDBTestModalidad {

    public static void main(String[] args) {
        ModalidadDAOImpl modalidadDao = new ModalidadDAOImpl();
        //insertar
        Modalidad modalidad = new Modalidad("PostpagO");
        int idM = modalidadDao.insertar(modalidad);
        System.out.println("La modalidad con id: " + idM + " ha sido insertada correctamente");
        // modificar
        modalidad.setNombre("Postpago");
        boolean modificado = modalidadDao.modificar(modalidad);
        if (modificado == true) System.out.println("El modalidad con id: " + idM + " ha sido modificado");
        // eliminar
        boolean eliminado = modalidadDao.eliminar(idM);
        if (eliminado == true) System.out.println("El modalidad con id: " + idM + " ha sido eliminado");
        // buscar
        int idB = 1;
        modalidad = modalidadDao.buscar(idB);
        if (idB == modalidad.getIdModalidad()) System.out.println("El modalidad con id; " + idB + " ha sido encontrada");
        // listar
        List<Modalidad> modalidades = modalidadDao.listar();
        System.out.println("Todos las modalidades :");
        for(Modalidad m : modalidades){
            System.out.println(m.getIdModalidad() + " " + m.getNombre());
        }
    }
    
}
