package pe.edu.pucp.pdm.portaldbtests.usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.usuario.impl.EncuestaDAOImpl;
import pe.edu.pucp.pdm.usuario.impl.TipoMotivoDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Encuesta;
import pe.edu.pucp.pdm.usuariomodel.TipoMotivo;

public class PortalDBTestEncuesta {

    public static void main(String[] args) throws ParseException {
       EncuestaDAOImpl encuestaDao = new EncuestaDAOImpl();
        // insertar
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
       TipoMotivoDAOImpl tipoMotivoDao = new TipoMotivoDAOImpl();
       TipoMotivo tipo = tipoMotivoDao.buscar(1);
        Encuesta encuesta = new Encuesta();
        encuesta.setMotivo(tipo);
        encuesta.setCalificacion(5);
        encuesta.setOpinion("El rendimiento de la linea no me ha gustado.");
        encuesta.setFechaEncuesta(formato.parse("01/02/2025"));
        int idE = encuestaDao.insertar(encuesta);
        encuesta.setIdEncuesta(idE);
        System.out.println("La encuesta con id: " + idE + " ha sido insertada");
       // modificar
       encuesta.setCalificacion(3);
       boolean modificado = encuestaDao.modificar(encuesta);
       if (modificado == true) System.out.println("La encuesta con id: " + idE + " ha sido modificada");
       // eliminar
       boolean eliminado = encuestaDao.eliminar(idE);
       if (eliminado == true) System.out.println("La encuesta con id: " + idE + " ha sido eliminada");
       // buscar
       int idB = 1;
       encuesta = encuestaDao.buscar(idB);
       if (idB == encuesta.getIdEncuesta()){
           System.out.println("La encuesta con id: " + idB + " ha sido encontrada");
       }
       // listar
       List<Encuesta> encuestas = encuestaDao.listar();
       for(Encuesta e :  encuestas){
           System.out.println(e.getIdEncuesta() + " " + e.getOpinion() + " " + e.getCalificacion());
       }
    }
    
}
