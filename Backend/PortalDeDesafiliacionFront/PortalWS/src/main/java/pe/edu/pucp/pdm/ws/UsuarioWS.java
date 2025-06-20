package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.pucp.edu.pdm.portalbusiness.usuariobo.IUsuarioBO;
import pe.pucp.edu.pdm.portalbusiness.usuarioimpl.UsuarioBOImpl;
import pe.edu.pucp.pdm.usuariomodel.Usuario;

@WebService(
    serviceName     = "UsuarioWS",
    targetNamespace = "http://services.softprog.pucp.edu.pe/"
)
public class UsuarioWS {

    private final IUsuarioBO usuarioBO;

    public UsuarioWS() {
        this.usuarioBO = new UsuarioBOImpl();
    }
    
    @WebMethod(operationName = "insertarUsuario")
    public int insertarUsuario(
            @WebParam(name = "primerNombre") String primerNombre,
            @WebParam(name = "segundoNombre") String segundoNombre,
            @WebParam(name = "apellidoPaterno") String apellidoPaterno,
            @WebParam(name = "apellidoMaterno") String apellidoMaterno,
            @WebParam(name = "tipoDocumento") String tipoDocumento,
            @WebParam(name = "numeroDocumento") String numeroDocumento,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "contrasena") String contrasena) {
    
    Usuario usuario = new Usuario(
            primerNombre,
            segundoNombre,
            apellidoPaterno,
            apellidoMaterno,
            tipoDocumento,
            numeroDocumento,
            correo,
            contrasena);
    
    return this.usuarioBO.insertar(usuario);
}
    // 2) ELIMINAR USUARIO
    @WebMethod(operationName = "eliminarUsuario")
    public void eliminarUsuario(
        @WebParam(name = "idUsuario") int idUsuario
    ) {
        usuarioBO.eliminar(idUsuario);
    }

    // 3) OBTENER UN USUARIO POR ID
    @WebMethod(operationName = "obtenerUsuario")
    public Usuario obtenerUsuario(
        @WebParam(name = "idUsuario") int idUsuario
    ) {
        return usuarioBO.obtener(idUsuario);
    }

    // 4) LISTAR TODOS LOS USUARIOS
    @WebMethod(operationName = "listarUsuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioBO.listar();
    }
    //********************************************************
    //REQUISITO FUNCIONAL 5 (VALIDACION EN TIEMPO REAL)
    //********************************************************
    // 5) VERIFICAR SI EXISTE POR DOCUMENTO
    @WebMethod(operationName = "existePorDocumento")
    public boolean existePorDocumento(
        @WebParam(name = "tipoDocumento")   String tipoDocumento,
        @WebParam(name = "numeroDocumento") String numeroDocumento
    ) {
        return usuarioBO.existePorDocumento(tipoDocumento, numeroDocumento);
    }

    // 6) VERIFICAR SI EXISTE POR CORREO
    @WebMethod(operationName = "existePorCorreo")
    public boolean existePorCorreo(
        @WebParam(name = "correo") String correo
    ) {
        return usuarioBO.existePorCorreo(correo);
    }
    
    //********************************************************
    //REQUISITO FUNCIONAL 2 (INICIO DE SESION)
    //********************************************************
    
    @WebMethod(operationName = "obtenerUsuarioPorCorreoODocumento")
    public Usuario obtenerUsuarioPorCorreoODocumento(
            @WebParam(name = "documentoOCorreo") String documentoOCorreo,
            @WebParam(name = "contrasena")       String contrasena
    ) {
        // En IUsuarioBO, el método se llama autenticarPorDocumentoOCorreo(...)
        return usuarioBO.autenticarPorDocumentoOCorreo(documentoOCorreo, contrasena);
    }
    @WebMethod(operationName = "buscarTipoUsuario")
    public int buscarTipoUsuario(
            @WebParam(name="idUsuario")int idUsuario){
        return usuarioBO.buscarTipoUsuario(idUsuario);
    }
    
    @WebMethod(operationName = "verificarUsuario")
    public int verificarUsuario(
            @WebParam(name = "documentoOCorreo") String documentoOCorreo,
            @WebParam(name = "contrasena")       String contrasena
    ){
        return usuarioBO.verificarUsuario(documentoOCorreo, contrasena);
    }
//    @WebMethod(operationName = "devuleveCliente")
//    public Cliente devuelveCliente(Usuario usuario){
//        return usuarioBO.devuelveCliente(usuario);
//    }
}
