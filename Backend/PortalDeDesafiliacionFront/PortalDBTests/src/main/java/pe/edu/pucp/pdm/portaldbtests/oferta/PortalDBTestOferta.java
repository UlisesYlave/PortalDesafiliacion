package pe.edu.pucp.pdm.portaldbtests.oferta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.oferta.impl.EquipoDAOImpl;
import pe.edu.pucp.pdm.oferta.impl.OfertaDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Equipo;
import pe.edu.pucp.pdm.ofertamodel.Oferta;

public class PortalDBTestOferta {

    public static void main(String[] args) throws ParseException {
        OfertaDAOImpl ofertaDao = new OfertaDAOImpl();
        //insertar
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
        
        EquipoDAOImpl equipoDao = new EquipoDAOImpl();
        Equipo equipo = equipoDao.buscar(1);
        
        Oferta oferta = new Oferta(1,equipo,"Oferta 6","Hola Pinto, esta oferta es para ti",30,
                formato.parse("03/06/2025"),formato.parse("30/06/2025"),formato.parse("02/06/2025"),"POSTPAGO");
        int idO = ofertaDao.insertar(oferta);
        oferta.setIdOferta(idO);
        System.out.println("La Oferta con id: " + idO + " ha sido insertada correctamente");
        // modificar
        oferta.setDescuento(50);
        boolean modificado = ofertaDao.modificar(oferta);
        if (modificado == true) System.out.println("La Oferta con id: "+idO+" ha sido modificado");
        // eliminar
        boolean eliminado = ofertaDao.eliminar(idO);
        if (eliminado == true) System.out.println("La Oferta con id: "+idO+" ha sido eliminado");
         // buscar
        int idB = 2;
        oferta = ofertaDao.buscar(idB);
        if (idB==oferta.getIdOferta()) System.out.println("La Oferta con id: " +idB+" ha sido encontrada");
        // listar
        List<Oferta> ofertas = ofertaDao.listar();
        System.out.println("Todos las ofertas:");
        for(Oferta o : ofertas){
            System.out.println(o.getIdOferta() + " " + o.getNombre());
        }
    }
    
}
