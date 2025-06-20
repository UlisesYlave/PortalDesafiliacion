package pe.edu.pucp.pdm.usuario.dao;

import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.usuariomodel.Cliente;
import pe.edu.pucp.pdm.usuariomodel.Usuario;

public interface IUsuarioDAO extends ICrud<Usuario>{
    /**
     * Autentica un usuario dado un documento o correo y su contraseña.
     * @param documentoOCorreo Puede ser el número de documento o el correo.
     * @param contrasena      La contraseña proporcionada.
     * @return El Usuario si las credenciales coinciden, o null en caso contrario.
     */
    Usuario autenticarPorDocumentoOCorreo(String documentoOCorreo, String contrasena);

    /**
     * Verifica en la base de datos si ya existe un usuario con ese tipo-documento + número.
     * @param tipoDocumento   Ej. "DNI", "CE", etc.
     * @param numeroDocumento El número de documento.
     * @return true si ya existe, false si no.
     */
    boolean existePorDocumento(String tipoDocumento, String numeroDocumento);

    /**
     * Verifica en la base de datos si ya existe un usuario con ese correo.
     * @param correoEmail El correo a validar.
     * @return true si existe, false en caso contrario.
     */
    boolean existePorCorreo(String correoEmail);
    int buscarTipoUsuario(int _idUsuario);
//    Cliente devuelveCliente(Usuario usuario);
}
