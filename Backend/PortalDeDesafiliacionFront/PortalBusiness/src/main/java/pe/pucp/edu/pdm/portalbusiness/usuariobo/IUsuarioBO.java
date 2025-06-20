package pe.pucp.edu.pdm.portalbusiness.usuariobo;

import java.util.List;
import pe.edu.pucp.pdm.usuariomodel.Usuario;
import pe.edu.pucp.pdm.usuariomodel.Cliente;

public interface IUsuarioBO {

    Usuario autenticarPorDocumentoOCorreo(String documentoOCorreo, String contrasena);

    boolean existePorDocumento(String tipoDocumento, String numeroDocumento);

    boolean existePorCorreo(String correo);

    List<Usuario> listar();

    Usuario obtener(int idUsuario);

    int guardar(Usuario usuario, boolean esNuevo);

    void eliminar(int idUsuario);
    
    int insertar(Usuario usuario);
    
    int buscarTipoUsuario(int idUsuario);
    
    int verificarUsuario(String documentoOCorreo, String contrasena);

}
