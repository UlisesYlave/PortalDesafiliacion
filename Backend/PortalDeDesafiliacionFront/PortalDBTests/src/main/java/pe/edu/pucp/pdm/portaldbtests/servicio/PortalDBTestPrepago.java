package pe.edu.pucp.pdm.portaldbtests.servicio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.servicio.impl.LineaDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PaqueteDAOImpl;
import pe.edu.pucp.pdm.servicio.impl.PrepagoDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.serviciomodel.Paquete;
import pe.edu.pucp.pdm.serviciomodel.Prepago;

public class PortalDBTestPrepago {

    public static void main(String[] args) throws ParseException {
        PrepagoDAOImpl prepagoDao = new PrepagoDAOImpl();
        //insertar
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
        LineaDAOImpl lineaDao = new LineaDAOImpl();
        Linea linea = lineaDao.buscar(1);
        int idL = linea.getIdCliente();
        PaqueteDAOImpl paqueteDao = new PaqueteDAOImpl();
        Paquete paquete = paqueteDao.buscar(1);
        
        Prepago prepago = new Prepago(1,"953444687",formato.parse("03/02/2025"),50,40,formato.parse("04/02/2025"));
        prepago.setIdLinea(idL);
        prepago.setPaquete(paquete);
        
        idL = prepagoDao.insertar(prepago);
        System.out.println("El Prepago con id: "+idL+" ha sido insertado correctamente");
        // modificar
        prepago.setSaldoActual(60);
        boolean modificado=prepagoDao.modificar(prepago);
        if (modificado == true) System.out.println("El Prepago con id: "+idL+" ha sido modificado");
        // eliminar
        boolean eliminado=prepagoDao.eliminar(idL);
        if (eliminado == true) System.out.println("El Prepago con id: " + idL + " ha sido eliminado");
        // buscar
        int idB = 1;
        prepago = prepagoDao.buscar(idB);
        if (idB == prepago.getIdLinea())System.out.println("El Prepago con id: " + idB + " ha sido encontrado");
        // listar
        List<Prepago> prepagos = prepagoDao.listar();
        System.out.println("Todos los prepagos:");
        for(Prepago p : prepagos){
            System.out.println(p.getIdLinea() + " " + p.getDetalleServicio());
        }
    }
    
}
