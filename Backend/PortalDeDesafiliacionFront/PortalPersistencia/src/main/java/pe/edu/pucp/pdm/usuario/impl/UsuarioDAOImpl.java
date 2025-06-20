package pe.edu.pucp.pdm.usuario.impl;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.config.DBManager;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuario.dao.IUsuarioDAO;
import pe.edu.pucp.pdm.usuariomodel.Usuario;
import pe.edu.pucp.pdm.usuariomodel.Cliente;

public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements IUsuarioDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{CALL insertarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_primerNombre",usuario.getPrimerNombre());
        cmd.setString("p_segundoNombre", usuario.getSegundoNombre());
        cmd.setString("p_apellidoPaterno",usuario.getApellidoMaterno());
        cmd.setString("p_apellidoMaterno", usuario.getApellidoMaterno());
        cmd.setString("p_tipoDocumento",usuario.getTipoDocumento());
        cmd.setString("p_numeroDocumento", usuario.getNumeroDocumento());
        cmd.setString("p_estado", usuario.getEstado());
        cmd.setString("p_correo", usuario.getCorreo());
        cmd.setString("p_contrasena", usuario.getContrasena());
        cmd.setBoolean("p_activo", usuario.isActivo());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, Usuario usuario) throws SQLException {
        String sql = "{CALL modificarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_primerNombre",usuario.getPrimerNombre());
        cmd.setString("p_segundoNombre", usuario.getSegundoNombre());
        cmd.setString("p_apellidoPaterno",usuario.getApellidoMaterno());
        cmd.setString("p_apellidoMaterno", usuario.getApellidoMaterno());
        cmd.setString("p_tipoDocumento",usuario.getTipoDocumento());
        cmd.setString("p_numeroDocumento", usuario.getNumeroDocumento());
        cmd.setString("p_correo", usuario.getCorreo());
        cmd.setString("p_contrasena", usuario.getContrasena());
        cmd.setString("p_estado", usuario.getEstado());
        cmd.setBoolean("p_activo", usuario.isActivo());
        cmd.setInt("p_idUsuario", usuario.getIdUsuario());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarUsuario(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarUsuarioPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idUsuario", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarUsuarios()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Usuario mapearModelo(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(
                rs.getString("primerNombre"),
                rs.getString("segundoNombre"),
                rs.getString("apellidoPaterno"),
                rs.getString("apellidoMaterno"),
                rs.getString("tipoDocumento"),
                rs.getString("numeroDocumento"),
                rs.getString("correo"),
                rs.getString("contrasena")
        );
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        return usuario;
    }
    // ================================
    // Métodos extra de IUsuarioDAO:
    // ================================

    @Override
    public Usuario autenticarPorDocumentoOCorreo(String documentoOCorreo, String contrasena) {
        String sqlParaDoc  = "SELECT * FROM Usuario WHERE numeroDocumento = ? AND contrasena = ?";
        String sqlParaMail = "SELECT * FROM Usuario WHERE correo = ? AND contrasena = ?";
        try (Connection conn = DBManager.getInstance().getConnection()) {
            // 1) Tratamos primero como documento
            try (PreparedStatement psDoc = conn.prepareStatement(sqlParaDoc)) {
                psDoc.setString(1, documentoOCorreo);
                psDoc.setString(2, contrasena);
                ResultSet rsDoc = psDoc.executeQuery();
                if (rsDoc.next()) {
                    return mapearModelo(rsDoc);
                }
            }
            // 2) Si no vino como documento, tratamos como correo
            try (PreparedStatement psMail = conn.prepareStatement(sqlParaMail)) {
                psMail.setString(1, documentoOCorreo);
                psMail.setString(2, contrasena);
                ResultSet rsMail = psMail.executeQuery();
                if (rsMail.next()) {
                    return mapearModelo(rsMail);
                }
            }
            return null;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error en autenticarPorDocumentoOCorreo: " + e.getMessage(), e);
        }
    }
    @Override
    public int buscarTipoUsuario(int _idUsuario){
        String sqlBuscarAdmin = "SELECT * FROM Administrador WHERE idAdministrador = ?";
        String sqlBuscarCliente = "SELECT * FROM Cliente WHERE idCliente = ?";
        try(Connection conn = DBManager.getInstance().getConnection()){
            try(PreparedStatement psAdmin = conn.prepareStatement(sqlBuscarAdmin)){
                psAdmin.setInt(1,_idUsuario);
                ResultSet rs = psAdmin.executeQuery();
                if(rs.next())
                    return 1;
            }
            try(PreparedStatement psCliente = conn.prepareStatement(sqlBuscarCliente)){
                psCliente.setInt(1,_idUsuario);
                ResultSet rs = psCliente.executeQuery();
                if(rs.next())
                    return 0;
            }
        }catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error en buscarTipoUsuario: " + e.getMessage(), e);
        }
        return -1;
    }
    @Override
    public boolean existePorDocumento(String tipoDocumento, String numeroDocumento) {
        String sql = "SELECT COUNT(*) AS total FROM Usuario WHERE tipoDocumento = ? AND numeroDocumento = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoDocumento);
            ps.setString(2, numeroDocumento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error en existePorDocumento: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existePorCorreo(String correoEmail) {
        String sql = "SELECT COUNT(*) AS total FROM Usuario WHERE correo = ?";
        try (Connection conn = DBManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correoEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
            return false;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error en existePorCorreo: " + e.getMessage(), e);
        }
    }
//    @Override
//    public Cliente devuelveCliente(Usuario usuario){
//        Cliente cliente = new Cliente(usuario.ge);
//    }
}
