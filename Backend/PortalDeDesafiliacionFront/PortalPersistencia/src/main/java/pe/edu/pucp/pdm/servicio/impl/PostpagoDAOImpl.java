package pe.edu.pucp.pdm.servicio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Postpago;
import pe.edu.pucp.pdm.servicio.dao.IPostpagoDAO;

public class PostpagoDAOImpl  extends BaseDAOImpl<Postpago> implements IPostpagoDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Postpago postpago) throws SQLException {
        String sql = "{CALL insertarPostpago(?, ?, ?, ?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea",postpago.getIdLinea());
        cmd.setDouble("p_deudaPendiente",postpago.getDeudaPendiente());
        cmd.setInt("p_idPlan",postpago.getPlan().getIdServicio());
        cmd.setInt("p_diaCicloFacturacion", postpago.getDiaCicloFacturacion());
        cmd.registerOutParameter("p_id",Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, Postpago postpago) throws SQLException {
        String sql = "{CALL modificarPostpago(?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPostpago",postpago.getIdLinea());
        cmd.setInt("p_deudaPendiente",postpago.getIdCliente());
        cmd.setInt("p_idPlan",postpago.getPlan().getIdServicio());
        cmd.setInt("p_diaCicloFacturacion", postpago.getDiaCicloFacturacion());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPostpago(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPostpagoPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPostpagos()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Postpago mapearModelo(ResultSet rs) throws SQLException {
        Postpago postpago = new Postpago(
                rs.getInt("idCliente"),
                rs.getString("numeroTelefono"),
                rs.getDate("fechaActivacion"),
                rs.getDouble("deudaPendiente"),
                rs.getInt("diaCicloFacturacion"));
        postpago.setIdLinea(rs.getInt("idLineaPostpago"));
        return postpago;
    }
}
