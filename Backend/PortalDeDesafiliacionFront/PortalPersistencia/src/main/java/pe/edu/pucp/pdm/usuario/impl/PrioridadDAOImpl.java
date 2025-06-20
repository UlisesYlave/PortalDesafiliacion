package pe.edu.pucp.pdm.usuario.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;
import pe.edu.pucp.pdm.usuario.dao.IPrioridadDAO;

public class PrioridadDAOImpl extends BaseDAOImpl<Prioridad> implements IPrioridadDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Prioridad prioridad) throws SQLException {
        String sql = "{CALL insertarPrioridad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", prioridad.getNombre());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, Prioridad prioridad) throws SQLException {
        String sql = "{CALL modificarPrioridad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
         cmd.setString("p_nombre", prioridad.getNombre());
        cmd.setInt("p_idPrioridad", prioridad.getIdPrioridad());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPrioridad(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPrioridad", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPrioridadPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPrioridad", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPrioridades()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Prioridad mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idPrioridad");
        Prioridad prioridad = new Prioridad(
                rs.getString("nombre")
        );
        prioridad.setIdPrioridad(id);
        return prioridad;
    }
}
