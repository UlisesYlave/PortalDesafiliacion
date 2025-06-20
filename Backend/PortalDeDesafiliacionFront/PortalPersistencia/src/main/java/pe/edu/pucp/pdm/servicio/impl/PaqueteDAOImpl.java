package pe.edu.pucp.pdm.servicio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Paquete;
import pe.edu.pucp.pdm.servicio.dao.IPaqueteDAO;

public class PaqueteDAOImpl extends BaseDAOImpl<Paquete> implements IPaqueteDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Paquete paquete) throws SQLException {
        String sql = "{CALL insertarPaquete(?, ?, ?, ?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", paquete.getNombreServicio());
        cmd.setString("p_beneficio", paquete.getBeneficios());
        cmd.setDouble("p_precio",paquete.getPrecio());
        cmd.setString("p_tipoServicio", paquete.getTipoServicio());
        cmd.setInt("p_duracionDias", paquete.getDuracionDias());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, Paquete paquete) throws SQLException {
        String sql = "{CALL modificarPaquete(?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", paquete.getNombreServicio());
        cmd.setString("p_beneficio", paquete.getBeneficios());
        cmd.setDouble("p_precio",paquete.getPrecio());
        cmd.setString("p_tipoServicio", paquete.getTipoServicio());
        cmd.setInt("p_duracionDias", paquete.getDuracionDias());
        cmd.setInt("p_id", paquete.getIdServicio());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPaquete(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPaquete", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPaquetePorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPaquete", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPaquetes()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Paquete mapearModelo(ResultSet rs) throws SQLException {
        Paquete paquete = new Paquete(
                rs.getString("nombrePaquete"),
                rs.getString("beneficios"),
                rs.getDouble("precio"),
                rs.getInt("duracionDias"));
        paquete.setIdServicio(rs.getInt("idPaquete"));
        return paquete;
    }
}
