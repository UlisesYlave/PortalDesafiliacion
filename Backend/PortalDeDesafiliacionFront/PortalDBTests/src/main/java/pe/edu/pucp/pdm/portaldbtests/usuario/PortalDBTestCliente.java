package pe.edu.pucp.pdm.portaldbtests.usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import pe.edu.pucp.pdm.usuariomodel.Cliente;
import pe.edu.pucp.pdm.usuariomodel.Region;
import pe.edu.pucp.pdm.usuario.impl.ClienteDAOImpl;
import pe.edu.pucp.pdm.usuario.impl.RegionDAOImpl;
import pe.edu.pucp.pdm.usuario.impl.UsuarioDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Usuario;

public class PortalDBTestCliente {

    public static void main(String[] args) throws ParseException {
        ClienteDAOImpl clienteDao  = new ClienteDAOImpl();
        //insertar
        RegionDAOImpl regionDao = new RegionDAOImpl();
        Region region = regionDao.buscar(1);
        
        DateFormat formato = new SimpleDateFormat("dd/M/yy");
        UsuarioDAOImpl usuarioDao = new UsuarioDAOImpl();
        Usuario usuario = new Usuario("Julio", " ","Bullon","Ylave","DNI", "71433040","braulises1504@gmail.com","sdfsd");
        int idC = usuarioDao.insertar(usuario);
        Cliente cliente = new Cliente("Julio", " ","Bullon","Ylave","DNI", "71433040","braulises1504@gmail.com",
                "sdfsd",region, 3, formato.parse("23/02/2025"));
        cliente.setIdCliente(idC);
        idC = clienteDao.insertar(cliente);
        System.out.println("El cliente con id: " + idC + " ha sido ingresado correctamente");
        //modificar
        cliente.setActivo(false);
        boolean modificado = clienteDao.modificar(cliente);
        if (modificado == true) System.out.println("El cliente con id: " + idC + " ha sido modificado.");
        //eliminar
        boolean eliminado = clienteDao.eliminar(idC);
        if (eliminado == true) System.out.println("El cliente con id: " + idC + " ha sido eliminada");
        //buscar
        int idB = 1;
        cliente = clienteDao.buscar(idB);
        if (idB == cliente.getIdCliente()){
            System.out.println("El cliente con id: " + idB + " ha sido encontrado");
            System.out.println("Region del Cliente: " + cliente.getRegion().getNombre());
            System.out.println("Antiguedad del Cliente" + cliente.getAntiguedadMeses() + " meses");
        }
        //listar
        List<Cliente> clientes = clienteDao.listar();
        //imprimir clientes
        System.out.println("Todos los Clientes:");
        for(Cliente cli : clientes){
            System.out.println("Clientes ID: " + cli.getIdUsuario());
        }
    }
    
}
