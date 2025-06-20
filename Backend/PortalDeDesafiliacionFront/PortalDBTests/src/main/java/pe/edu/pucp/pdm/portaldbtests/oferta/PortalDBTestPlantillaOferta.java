package pe.edu.pucp.pdm.portaldbtests.oferta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.oferta.impl.PlantillaOfertaDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.edu.pucp.pdm.usuario.impl.PrioridadDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;

public class PortalDBTestPlantillaOferta {

    public static void main(String[] args) throws ParseException {
        PlantillaOfertaDAOImpl plantillaDao = new PlantillaOfertaDAOImpl();
        //insertar
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
        
        PrioridadDAOImpl prioridadDao = new PrioridadDAOImpl();
        Prioridad prioridad = prioridadDao.buscar(3);
        
        PlantillaOferta plantilla = new PlantillaOferta("Descuento familiar","Hola {nombre}, esta ofera es para ti", 
                                                                                       1,prioridad, formato.parse("09/06/2025"));
        int idP = plantillaDao.insertar(plantilla);
        plantilla.setIdPlantilla(idP);
        System.out.println("La PlantillaOferta con id: " + idP + " ha sido insertada correctamente");
        // modificar
        plantilla.setFormula("Hola {nombre}, esta oferta es para TI");
        boolean modificado = plantillaDao.modificar(plantilla);
        if (modificado == true) System.out.println("La Plantilla con id: " + idP + " ha sido modificada");
        // eliminar
        boolean eliminado;
        // buscar
        int idB = 1;
        plantilla = plantillaDao.buscar(idB);
        if (idB==plantilla.getIdPlantilla()) System.out.println("La Plantilla con id: " + idP + " ha sido encontrada");
        // listar
        List<PlantillaOferta> plantillas = plantillaDao.listar();
        System.out.println("Todos las plantillas de ofertas:");
        for(PlantillaOferta p : plantillas){
            System.out.println(p.getIdPlantilla() + " " + p.getFormula());
        }
    }
    
}
