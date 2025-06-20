package pe.edu.pucp.pdm.servicio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Prepago;
import pe.edu.pucp.pdm.servicio.dao.IPrepagoDAO;

public class PrepagoDAOImpl extends BaseDAOImpl<Prepago> implements IPrepagoDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Prepago prepago) throws SQLException {
        String sql = "{CALL insertarPrepago(?, ?, ?, ?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea",prepago.getIdLinea());
        cmd.setInt("p_idPaquete",prepago.getPaquete().getIdServicio());
        cmd.setDouble("p_saldoActual", prepago.getSaldoActual());
        cmd.setDouble("p_promRecargaMensual",prepago.getPromedioRecargaMensual());
        cmd.setTimestamp("p_fechaUltimaRecarga", new java.sql.Timestamp(prepago.getFechaUltimaRecarga().getTime()));
        cmd.registerOutParameter("p_id",Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, Prepago prepago) throws SQLException {
        String sql = "{CALL modificarPrepago(?, ?, ?, ?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPrepago",prepago.getIdLinea());
        cmd.setInt("p_idPaquete",prepago.getPaquete().getIdServicio());
        cmd.setDouble("p_saldoActual", prepago.getSaldoActual());
        cmd.setDouble("p_promRecargaMensual",prepago.getPromedioRecargaMensual());
        cmd.setTimestamp("p_fechaUltimaRecarga", new java.sql.Timestamp(prepago.getFechaUltimaRecarga().getTime()));
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPrepago(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPrepagoPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPrepagos()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Prepago mapearModelo(ResultSet rs) throws SQLException {
        Prepago prepago = new Prepago(
                rs.getInt("idCliente"),
                rs.getString("numeroTelefono"),
                rs.getDate("fechaActivacion"),
                rs.getDouble("promedioRecargaMensual"),
                rs.getDouble("saldoActual"),
                rs.getDate("fechaUltimaRecarga"));
        return prepago;
    }
}
