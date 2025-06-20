package pe.edu.pucp.pdm.servicio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.pdm.config.DBManager;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.servicio.dao.ILineaDAO;
import pe.edu.pucp.pdm.serviciomodel.Linea;

public class LineaDAOImpl extends BaseDAOImpl<Linea> implements ILineaDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Linea linea) throws SQLException {
        String sql = "{CALL insertarLinea(?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idCliente", linea.getIdCliente());
        cmd.setString("p_numeroTelefono", linea.getNumeroTelefono());
        cmd.setString("p_tipoLinea", linea.getTipoLinea());
        cmd.setBoolean("p_activa", linea.isActiva());
        cmd.setTimestamp("p_fechaActivacion", new java.sql.Timestamp(linea.getFechaActivacion().getTime()));
        cmd.registerOutParameter("p_idLinea", Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, Linea linea) throws SQLException {
        String sql = "{CALL modificarLinea(?, ?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idCliente", linea.getIdCliente());
        cmd.setString("p_numeroTelefono", linea.getNumeroTelefono());
        cmd.setString("p_tipoLinea", linea.getTipoLinea());
        cmd.setBoolean("p_activa", linea.isActiva());
        cmd.setTimestamp("p_fechaActivacion", new java.sql.Timestamp(linea.getFechaActivacion().getTime()));
        cmd.setInt("p_idLinea",linea.getIdLinea());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarLinea(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarLineaPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarLineas()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    @Override
    protected Linea mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idLinea");
        Linea linea = new Linea(
                rs.getInt("idCliente"),
                rs.getString("numeroTelefono"),
                rs.getString("tipoLinea"),
                rs.getDate("fechaActivacion")
        );
        linea.setIdLinea(id);
        return linea;
    }
    
    @Override
    public List<Linea> listarLineaPorCliente(int id){
        String sql = "{CALL listarLineasPorCliente(?)}";
        try (
            Connection conn = DBManager.getInstance().getConnection();
            CallableStatement cmd = conn.prepareCall(sql);
        ) {
            cmd.setInt("p_idCliente", id);
            
            try(PreparedStatement ps = cmd){
                ResultSet rs = ps.executeQuery();

                List<Linea> lineas = new ArrayList<>();
                while (rs.next()) {
                    lineas.add(this.mapearModelo(rs));
                }
                return lineas;
                
            }catch (SQLException e) {
                System.err.println("Error SQL durante el listado: " + e.getMessage());
                throw new RuntimeException("No se pudo listar las lineas.", e);
            }
            
        } catch (Exception e) {
                System.err.println("Error inpesperado: " + e.getMessage());
                throw new RuntimeException("Error inesperado al listar las lineas.", e);
         }
    }
}
