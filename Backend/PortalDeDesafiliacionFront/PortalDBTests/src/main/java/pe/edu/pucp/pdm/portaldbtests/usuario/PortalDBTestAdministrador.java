package pe.edu.pucp.pdm.portaldbtests.usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.usuario.impl.AdministradorDAOImpl;
import pe.edu.pucp.pdm.usuario.impl.UsuarioDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Administrador;
import pe.edu.pucp.pdm.usuariomodel.Usuario;

public class PortalDBTestAdministrador {

    public static void main(String[] args) throws ParseException {
        AdministradorDAOImpl administradorDao = new AdministradorDAOImpl();
        // insertar
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
        UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
        Usuario usuario = new Usuario("Julio", " ","Bullon"," ","DNI", "71433035","braulises1234@gmail.com","sdfsd");
        int idA = usuarioDao.insertar(usuario);
        Administrador admin = new Administrador("Julio", " ","Bullon"," ","DNI", "71433035",
                                                                                  "braulises1234@gmail.com","sdfsd",formato.parse("23/08/2025"));
        admin.setIdAdministrador(idA);
        idA = administradorDao.insertar(admin);
        System.out.println("El cliente con id: " + idA + " ha sido ingresado correctamente");
         // modificar
         admin.setActivo(false);
        boolean modificado = administradorDao.modificar(admin);
       
         if (modificado == true) System.out.println("El administrador con id: " + idA + " ha sido modificado");
       
         // eliminar
        boolean eliminado = administradorDao.eliminar(idA);
        if (eliminado == true) System.out.println("El administrador con id: " + idA + " ha sido eliminado");
        
        // buscar
        int idB = 1;
        admin = administradorDao.buscar(idB);
        if (idB == admin.getIdAdministrador()){
            System.out.println("El administrador con id: " + idB + " se ha encontrado");
            System.out.println("Fecha Vigencia: " + admin.getFechaVigencia().toString());
        }
       
        // listar
        System.out.println("Todos los administradores");
        List<Administrador> admins = administradorDao.listar();
        for(Administrador a : admins){
            System.out.println(a.getIdUsuario() + " " + a.getFechaVigencia());
        }
    }
    
}
