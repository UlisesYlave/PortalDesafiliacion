package pe.edu.pucp.pdm.servicio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.Plan;
import pe.edu.pucp.pdm.servicio.dao.IPlanDAO;

public class PlanDAOImpl extends BaseDAOImpl<Plan> implements IPlanDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Plan plan) throws SQLException {
        String sql = "{CALL insertarPlan(?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombreServicio", plan.getNombreServicio());
        cmd.setString("p_beneficios", plan.getBeneficios());
        cmd.setString("p_tipoServicio",plan.getTipoServicio());
        cmd.setDouble("p_precio",plan.getPrecio());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, Plan plan) throws SQLException {
         String sql = "{CALL modificarPlan(?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlan", plan.getIdServicio());
        cmd.setString("p_nombreServicio", plan.getNombreServicio());
        cmd.setString("p_beneficios", plan.getBeneficios());
        cmd.setString("p_tipoServicio",plan.getTipoServicio());
        cmd.setDouble("p_precio",plan.getPrecio());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPlan(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlan", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPlanPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlan", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPlanes()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Plan mapearModelo(ResultSet rs) throws SQLException {
        Plan plan = new Plan(
                rs.getString("nombrePlan"),
                rs.getString("beneficios"),
                rs.getDouble("precioMensual"));
        plan.setIdServicio(rs.getInt("idPlan"));
        return plan;
    }
}
