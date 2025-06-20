package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl; 
import pe.edu.pucp.pdm.ofertamodel.PlantillaOfertaParametro;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaParametroDAO;

public class PlantillaOfertaParametroDAOImpl extends BaseDAOImpl<PlantillaOfertaParametro> implements IPlantillaOfertaParametroDAO {

    @Override
    protected CallableStatement comandoInsertar(Connection conn, PlantillaOfertaParametro pop) throws SQLException {
        // PROCEDURE insertarPlantillaOfertaParametro(IN p_idPlantillaOferta INT, IN p_idParametro INT, IN p_valor VARCHAR(255), OUT p_id INT)
        String sql = "{CALL insertarPlantillaOfertaParametro(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantillaOferta", pop.getIdPlantillaOferta());
        cmd.setInt("p_idParametro", pop.getIdParametro());
        cmd.setString("p_valor", pop.getValorParametro());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, PlantillaOfertaParametro pop) throws SQLException {
        // PROCEDURE modificarPlantillaOfertaParametro(IN p_id INT, IN p_idPlantillaOferta INT, IN p_idParametro INT, IN p_valor VARCHAR(255))
        String sql = "{CALL modificarPlantillaOfertaParametro(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", pop.getId());
        cmd.setInt("p_idPlantillaOferta", pop.getIdPlantillaOferta());
        cmd.setInt("p_idParametro", pop.getIdParametro());
        cmd.setString("p_valor", pop.getValorParametro());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        // PROCEDURE eliminarPlantillaOfertaParametro(IN p_id INT)
        String sql = "{CALL eliminarPlantillaOfertaParametro(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        // PROCEDURE buscarPlantillaOfertaParametroPorId(IN p_id INT)
        String sql = "{CALL buscarPlantillaOfertaParametroPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPlantillaOfertaParametros()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected PlantillaOfertaParametro mapearModelo(ResultSet rs) throws SQLException {
        PlantillaOfertaParametro pop = new PlantillaOfertaParametro(rs.getInt("idPlantillaOferta"), rs.getInt("idParametro"), rs.getString("valorParametro"));
        pop.setId(rs.getInt("id"));
        return pop;
    }

}