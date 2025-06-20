package pe.edu.pucp.pdm.usuario.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Administrador;
import pe.edu.pucp.pdm.usuario.dao.IAdministradorDAO;

public class AdministradorDAOImpl extends BaseDAOImpl<Administrador> implements IAdministradorDAO{
   
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Administrador admin) throws SQLException {
        String sql = "{CALL insertarAdministrador(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idAdministrador", admin.getIdAdministrador());
        cmd.setTimestamp("p_fechaVigencia", new java.sql.Timestamp(admin.getFechaVigencia().getTime()));
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, Administrador admin) throws SQLException {
        String sql = "{CALL modificarAdministrador(?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setTimestamp("p_fechaVigencia", new java.sql.Timestamp(admin.getFechaVigencia().getTime()));
        cmd.setInt("p_id", admin.getIdUsuario());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarAdministrador(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarAdminstradorPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarAdministrador()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    @Override
    protected Administrador mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idUsuario");
        Administrador admin = new Administrador(
                rs.getString("primerNombre"),
                rs.getString("segundoNombre"),
                rs.getString("apellidoPaterno"),
                rs.getString("apellidoMaterno"),
                rs.getString("tipoDocumento"),
                rs.getString("numeroDocumento"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                rs.getDate("fechaVigencia"));
        admin.setIdAdministrador(id);
        return admin;
    }
}
