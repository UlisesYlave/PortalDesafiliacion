package pe.edu.pucp.pdm.portaldbtests.usuario;

import java.util.List;
import pe.edu.pucp.pdm.usuariomodel.TipoMotivo;
import pe.edu.pucp.pdm.usuario.impl.TipoMotivoDAOImpl;

public class PortalDBTestTipoMotivo {

    public static void main(String[] args) {
       TipoMotivoDAOImpl tipoMotivoDao = new TipoMotivoDAOImpl();
       //insertar
       TipoMotivo motivo = new TipoMotivo("CAMBIO DE OPERADORA","El Cliente se quiere cambiar de Operadora.");
       int id = tipoMotivoDao.insertar(motivo);
       motivo.setIdMotivo(id);
       System.out.println("El TipoMotivo " + motivo.getNombre() + " ha sido ingresado correctamente.");
       //modificar
       motivo.setDescripcion("El Cliente se quiere cambiar de operadora.");
       boolean modificado = tipoMotivoDao.modificar(motivo);
       if (modificado == true)System.out.println("El TipoMotivo " + motivo.getNombre() + " ha sido modificado");
       //eliminar
       boolean eliminado = tipoMotivoDao.eliminar(id);
       if (eliminado == true)System.out.println("El TipoMotivo " + motivo.getNombre() + " ha sido eliminado");
       //buscar
       int idB = 1;
       TipoMotivo motivoB = tipoMotivoDao.buscar(idB);
       System.out.println(motivoB.getIdMotivo());
       if (motivoB.getIdMotivo()==idB){
           System.out.println("El TipoMotivo con id: " + idB + " ha sido encontrado");
           System.out.println("Nombre: " + motivoB.getNombre());
           System.out.println("Descripcion: " + motivoB.getDescripcion());
       }
       //listar
       System.out.println("Todos los Tipos de Motivos: ");
       List<TipoMotivo> motivos = tipoMotivoDao.listar();
       for(TipoMotivo m : motivos){
           System.out.println(m.getNombre() + " " +m.getDescripcion());
       }
    }
    
}
