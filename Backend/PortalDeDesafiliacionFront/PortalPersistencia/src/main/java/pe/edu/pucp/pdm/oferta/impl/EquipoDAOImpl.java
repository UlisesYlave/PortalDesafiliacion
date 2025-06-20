package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Equipo;
import pe.edu.pucp.pdm.oferta.dao.IEquipoDAO;

public class EquipoDAOImpl extends BaseDAOImpl<Equipo> implements IEquipoDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Equipo equipo) throws SQLException {
        String sql = "{CALL insertarEquipo(?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_marca", equipo.getMarca());
        cmd.setString("p_modelo", equipo.getModelo());
        cmd.setInt("p_stock",equipo.getStock());
        cmd.setDouble("p_precio", equipo.getPrecio());
        cmd.setString("p_estado",equipo.getEstado());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        cmd.setString("p_categoria",equipo.getCategoria().name().toUpperCase());
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, Equipo equipo) throws SQLException {
        String sql = "{CALL modificarEquipo(?, ?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_marca", equipo.getMarca());
        cmd.setString("p_modelo", equipo.getModelo());
        cmd.setInt("p_stock",equipo.getStock());
        cmd.setDouble("p_precio", equipo.getPrecio());
        cmd.setString("p_estado",equipo.getEstado());
        cmd.setInt("p_idEquipo", equipo.getIdEquipo());
        cmd.setString("p_categoria",equipo.getCategoria().name().toUpperCase());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarEquipo(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idEquipo", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarEquipoPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idEquipo", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarEquipos()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Equipo mapearModelo(ResultSet rs) throws SQLException {
        Equipo equipo = new Equipo(
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getInt("stock"),             
                rs.getDouble("precio"),
                rs.getString("estado"));
        equipo.setIdEquipo(rs.getInt("idEquipo"));
        return equipo;
    }
}
