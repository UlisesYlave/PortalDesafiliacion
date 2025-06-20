package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaDAO;

public class PlantillaOfertaDAOImpl  extends BaseDAOImpl<PlantillaOferta> implements IPlantillaOfertaDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, PlantillaOferta plantilla) throws SQLException {
        String sql = "{CALL insertarPlantillaOferta(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", plantilla.getNombre());
        cmd.setString("p_descripcion", plantilla.getFormula()); // Asumo que formula corresponde a descripcion
        cmd.setTimestamp("p_fechaCreacion", new java.sql.Timestamp(plantilla.getPeriodoValidez().getTime()));
        cmd.setBoolean("p_activa", plantilla.isActiva());
        cmd.setInt("p_tipoServicio", plantilla.getTipoServicio());
        cmd.setInt("p_idPrioridad", plantilla.getPrioridad().getIdPrioridad());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, PlantillaOferta plantilla) throws SQLException {
        String sql = "{CALL modificarPlantillaOferta(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", plantilla.getIdPlantilla());
        cmd.setString("p_nombre", plantilla.getNombre());
        cmd.setString("p_descripcion", plantilla.getFormula()); // Asumo que formula corresponde a descripcion
        cmd.setTimestamp("p_fechaCreacion", new java.sql.Timestamp(plantilla.getPeriodoValidez().getTime()));
        cmd.setBoolean("p_activa", plantilla.isActiva());
        cmd.setInt("p_tipoServicio", plantilla.getTipoServicio());
        cmd.setInt("p_idPrioridad", plantilla.getPrioridad().getIdPrioridad());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPlantillaOferta(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantilla", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPlantillaOfertaPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantilla", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPlantillasOferta()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected PlantillaOferta mapearModelo(ResultSet rs) throws SQLException {
        PlantillaOferta plantilla = new PlantillaOferta();
        plantilla.setIdPlantilla(rs.getInt("idPlantillaOferta"));
        plantilla.setNombre(rs.getString("nombre"));
        plantilla.setFormula(rs.getString("formula")); // Asumiendo mapeo
        plantilla.setPeriodoValidez(new Date(rs.getTimestamp("fechaValidezHasta").getTime()));
        plantilla.setActiva(rs.getBoolean("activa"));
        plantilla.setTipoServicio(rs.getInt("tipoServicio"));
        plantilla.setActiva(rs.getBoolean("activa"));
        // Los campos tipoServicio, prioridad, modalidades y parametros no están en el RS
        return plantilla;
    }
}
