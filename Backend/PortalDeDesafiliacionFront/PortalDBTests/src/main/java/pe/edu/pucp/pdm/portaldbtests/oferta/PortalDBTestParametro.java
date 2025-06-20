package pe.edu.pucp.pdm.portaldbtests.oferta;

import java.util.List;
import pe.edu.pucp.pdm.oferta.impl.ParametroDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Parametro;

public class PortalDBTestParametro {

    public static void main(String[] args) {
         ParametroDAOImpl parametroDao = new ParametroDAOImpl();
        //insertar
        Parametro param = new Parametro("YT Premium");
        int idP = parametroDao.insertar(param);
        param.setIdParametro(idP);
        System.out.println("El Parametro con id: " + idP + " ha sido insertado correctamente");
        // modificar
        param.setNombreParametro("Youtube Premium");
        boolean modificado = parametroDao.modificar(param);
        if (modificado == true) System.out.println("El Parametro con id: " + idP + " ha sido modificado");
        // eliminar
        boolean eliminado = parametroDao.eliminar(idP);
        if (eliminado == true) System.out.println("El Parametro con id: " + idP + " ha sido eliminado");
        // buscar
        int idB = 1;
        param = parametroDao.buscar(idB);
        if (idB == param.getIdParametro()) System.out.println("El Parametro con id: " + idB + " ha sido encontrado");
        // listar
        List<Parametro> params = parametroDao.listar();
        for(Parametro p : params){
            System.out.println(p.getIdParametro() + " " +  p.getNombreParametro());
        }
    }
    
}
