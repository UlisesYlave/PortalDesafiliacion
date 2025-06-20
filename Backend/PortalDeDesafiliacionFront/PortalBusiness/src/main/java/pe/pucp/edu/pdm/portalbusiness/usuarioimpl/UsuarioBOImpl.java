package pe.pucp.edu.pdm.portalbusiness.usuarioimpl;

import java.util.List;
import pe.pucp.edu.pdm.portalbusiness.usuariobo.IUsuarioBO;
import pe.edu.pucp.pdm.usuario.dao.IUsuarioDAO;
import pe.edu.pucp.pdm.usuario.impl.UsuarioDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Usuario;

/**
 * Implementación de la capa de negocio para Usuario.
 * Se limita a delegar en el DAO correspondiente.
 */
public class UsuarioBOImpl implements IUsuarioBO {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioBOImpl() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public Usuario autenticarPorDocumentoOCorreo(String documentoOCorreo, String contrasena) {
        // Delegamos 100% al DAO que ya encapsula la validación
        return usuarioDAO.autenticarPorDocumentoOCorreo(documentoOCorreo, contrasena);
    }
    @Override
    public int buscarTipoUsuario(int idUsuario){
        return usuarioDAO.buscarTipoUsuario(idUsuario);
    } 
   
    @Override
    public boolean existePorDocumento(String tipoDocumento, String numeroDocumento) {
        return usuarioDAO.existePorDocumento(tipoDocumento, numeroDocumento);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return usuarioDAO.existePorCorreo(correo);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    @Override
    public Usuario obtener(int idUsuario) {
        return usuarioDAO.buscar(idUsuario);
    }

    @Override
    public int guardar(Usuario usuario, boolean esNuevo) {
        if (esNuevo) {
            return usuarioDAO.insertar(usuario);
        } else {
            usuarioDAO.modificar(usuario);
            return usuario.getIdUsuario();
        }
    }

    @Override
    public void eliminar(int idUsuario) {
        usuarioDAO.eliminar(idUsuario);
    }
    @Override
    public int insertar(Usuario usuario){
        return this.usuarioDAO.insertar(usuario);
    }
    
    @Override
    public int verificarUsuario(String documentoOCorreo, String contrasena){
        Usuario usuario = usuarioDAO.autenticarPorDocumentoOCorreo(documentoOCorreo, contrasena);
        if (usuario == null)return -1;
        int tipo = usuarioDAO.buscarTipoUsuario(usuario.getIdUsuario());
        return tipo;
    }
    
//    @Override
//    Cliente devuelveCliente(Usuario usuario){
//        return this.usuarioDAO.devuelveCliente(usuario);
//    }
}
