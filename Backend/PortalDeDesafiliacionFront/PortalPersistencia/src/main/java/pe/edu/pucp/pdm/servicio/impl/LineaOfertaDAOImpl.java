package pe.edu.pucp.pdm.servicio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.servicio.dao.ILineaOfertaDAO;
import pe.edu.pucp.pdm.serviciomodel.LineaOferta;



public class LineaOfertaDAOImpl extends BaseDAOImpl<LineaOferta> implements ILineaOfertaDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, LineaOferta pop) throws SQLException {
        // PROCEDURE insertarPlantillaOfertaParametro(IN p_idPlantillaOferta INT, IN p_idParametro INT, IN p_valor VARCHAR(255), OUT p_id INT)
        String sql = "{CALL insertarLineaOferta(?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", pop.getIdLinea());
        cmd.setInt("p_idOferta", pop.getIdOferta());
        cmd.setString("p_estadoOfertaLinea", pop.getEstadoOfertaLinea());
        cmd.setTimestamp("p_fechaAsociacion", new java.sql.Timestamp(pop.getFechaAsociacion().getTime()));
        cmd.setTimestamp("p_fechaAplicacion", new java.sql.Timestamp(pop.getFechaAplicacion().getTime()));
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, LineaOferta pop) throws SQLException {
        // PROCEDURE modificarPlantillaOfertaParametro(IN p_id INT, IN p_idPlantillaOferta INT, IN p_idParametro INT, IN p_valor VARCHAR(255))
        String sql = "{CALL modificarLineaOferta(?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", pop.getIdLinea());
        cmd.setInt("p_idOferta", pop.getIdOferta());
        cmd.setString("p_estadoOfertaLinea", pop.getEstadoOfertaLinea());
        cmd.setTimestamp("p_fechaAsociacion", new java.sql.Timestamp(pop.getFechaAsociacion().getTime()));
        cmd.setTimestamp("p_fechaAplicacion", new java.sql.Timestamp(pop.getFechaAplicacion().getTime()));
        cmd.setInt("p_idLineaOferta", pop.getIdLineaOferta());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        // PROCEDURE eliminarPlantillaOfertaParametro(IN p_id INT)
        String sql = "{CALL eliminarLineaOferta(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLineaOferta", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        // PROCEDURE buscarPlantillaOfertaParametroPorId(IN p_id INT)
        String sql = "{CALL buscarLineaOfertaPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLineaOferta", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarLineasOfertas()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected LineaOferta mapearModelo(ResultSet rs) throws SQLException {
        LineaOferta pop = new LineaOferta(rs.getInt("idLinea"), rs.getInt("idOferta"), 
                rs.getString("estadoOfertaLinea"),rs.getDate("fechaAsociacion"),rs.getDate("fechaAplicacion"));
        pop.setIdLineaOferta(rs.getInt("idLineaOferta"));
        return pop;
    }
}