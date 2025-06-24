package pe.edu.pucp.pdm.oferta.impl;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.pdm.config.DBManager;

import pe.edu.pucp.pdm.dao.BaseDAOImpl; // Tu BaseDAOImpl genérico
import pe.edu.pucp.pdm.ofertamodel.PlantillaOfertaModalidad;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaModalidadDAO;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;

public class PlantillaOfertaModalidadDAOImpl extends BaseDAOImpl<PlantillaOfertaModalidad> implements IPlantillaOfertaModalidadDAO {

    @Override
    protected CallableStatement comandoInsertar(Connection conn, PlantillaOfertaModalidad pom) throws SQLException {
        // PROCEDURE insertarPlantillaOfertaModalidad(IN p_idPlantilla INT, IN p_idModalidad INT, OUT p_id INT)
        String sql = "{CALL insertarPlantillaOfertaModalidad(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantilla", pom.getIdPlantillaOferta());
        cmd.setInt("p_idModalidad", pom.getIdModalidad());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, PlantillaOfertaModalidad pom) throws SQLException {
        // PROCEDURE modificarPlantillaOfertaModalidad(IN p_id INT, IN p_idPlantilla INT, IN p_idModalidad INT)
        String sql = "{CALL modificarPlantillaOfertaModalidad(?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", pom.getId());
        cmd.setInt("p_idPlantilla", pom.getIdPlantillaOferta());
        cmd.setInt("p_idModalidad", pom.getIdModalidad());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        // PROCEDURE eliminarPlantillaOfertaModalidad(IN p_id INT)
        String sql = "{CALL eliminarPlantillaOfertaModalidad(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        // PROCEDURE buscarPlantillaOfertaModalidadPorId(IN p_id INT)
        String sql = "{CALL buscarPlantillaOfertaModalidadPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPlantillaOfertaModalidades()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }

    @Override
    protected PlantillaOfertaModalidad mapearModelo(ResultSet rs) throws SQLException {
        PlantillaOfertaModalidad pom = new PlantillaOfertaModalidad(rs.getInt("idPlantillaOferta"),rs.getInt("idModalidad"));
        pom.setId(rs.getInt("id"));
        return pom;
    }
    
    @Override
    public List<Modalidad> listarModalidadesPorPlantilla(int idPlantilla) {
        String sql = "{CALL listarModalidadesPorPlantilla(?)}";
        List<Modalidad> modalidades = new ArrayList<>();

        try (Connection conn = DBManager.getInstance().getConnection();
             CallableStatement cmd = conn.prepareCall(sql)) {

            cmd.setInt(1, idPlantilla); // Usar índice en lugar de nombre para mayor compatibilidad
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Modalidad modalidad = new Modalidad(rs.getString("nombre"));
                modalidad.setIdModalidad(rs.getInt("idModalidad"));
                modalidades.add(modalidad);
            }
            
            
            
            return modalidades;
        } catch (SQLException e) {
            System.err.println("Error SQL durante el listado: " + e.getMessage());
            e.printStackTrace(); // Esto mostrará el stack trace completo
            throw new RuntimeException("Error al listar modalidades por plantilla", e);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            throw new RuntimeException("Error de conexión con la base de datos", e);
        }
    }
}