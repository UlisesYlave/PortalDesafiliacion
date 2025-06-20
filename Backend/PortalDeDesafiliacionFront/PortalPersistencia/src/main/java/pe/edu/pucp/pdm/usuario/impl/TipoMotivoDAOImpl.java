package pe.edu.pucp.pdm.usuario.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.TipoMotivo;
import pe.edu.pucp.pdm.usuario.dao.ITipoMotivoDAO;

public class TipoMotivoDAOImpl extends BaseDAOImpl<TipoMotivo> implements ITipoMotivoDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, TipoMotivo motivo) throws SQLException {
        String sql = "{CALL insertarTipoMotivo(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", motivo.getNombre());
        cmd.setString("p_descripcion", motivo.getDescripcion());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, TipoMotivo motivo) throws SQLException {
        String sql = "{CALL modificarTipoMotivo(?, ?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", motivo.getNombre());
        cmd.setString("p_descripcion", motivo.getDescripcion());
        cmd.setInt("p_idTipoMotivo", motivo.getIdMotivo());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarTipoMotivo(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idTipoMotivo", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarTipoMotivoPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idTipoMotivo", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarTiposMotivo()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected TipoMotivo mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idTipoMotivo");
        TipoMotivo motivo = new TipoMotivo(
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
        motivo.setIdMotivo(id);
        return motivo;
    }
}
