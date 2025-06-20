package pe.edu.pucp.pdm.usuario.impl;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.config.DBManager;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Cliente;
import pe.edu.pucp.pdm.usuariomodel.Region;
import pe.edu.pucp.pdm.usuario.dao.IClienteDAO;

public class ClienteDAOImpl extends BaseDAOImpl<Cliente> implements IClienteDAO{
   @Override
    protected CallableStatement comandoInsertar(Connection conn, Cliente cliente) throws SQLException {
        String sql = "{CALL insertarCliente(?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idCliente", cliente.getIdCliente());
        cmd.setInt("p_idRegion",cliente.getRegion().getIdRegion());
        cmd.setString("p_provincia",cliente.getProvincia());
        cmd.setString("p_departamento", cliente.getDepartamento());
        cmd.setString("p_distrito", cliente.getDistrito());
        cmd.setInt("p_antiguedadMeses", cliente.getAntiguedadMeses());
        cmd.setTimestamp("p_fechaUltimaSolicitud", new java.sql.Timestamp(cliente.getFechaUltimaOferta().getTime()));
        cmd.registerOutParameter("p_id",Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, Cliente cliente) throws SQLException {
        String sql = "{CALL modificarCliente(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idRegion",cliente.getRegion().getIdRegion());
        cmd.setString("p_provincia",cliente.getProvincia());
        cmd.setString("p_departamento", cliente.getDepartamento());
        cmd.setString("p_distrito", cliente.getDistrito());
        cmd.setInt("p_antiguedadMeses", cliente.getAntiguedadMeses());
        cmd.setTimestamp("p_fechaUltimaSolicitud", new java.sql.Timestamp(cliente.getFechaUltimaOferta().getTime()));
        cmd.setInt("p_idCliente", cliente.getIdUsuario());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarCliente(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idCliente", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarClientePorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idCliente", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarClientes()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Cliente mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idUsuario");
        //buscar Region
        RegionDAOImpl regionDao = new RegionDAOImpl();
        Region region = regionDao.buscar(rs.getInt("idRegion"));
        
        Cliente cliente = new Cliente(
                rs.getString("primerNombre"),
                rs.getString("segundoNombre"),
                rs.getString("apellidoPaterno"),
                rs.getString("apellidoMaterno"),
                rs.getString("tipoDocumento"),
                rs.getString("numeroDocumento"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                region,
                rs.getInt("antiguedadMeses"),
                rs.getDate("fechaUltimaOferta"));
        cliente.setIdCliente(id);
        return cliente;
    }
    @Override
    public boolean asignarPrioridad(int idCliente, int idPrioridad) {
        String sql = "UPDATE cliente SET id_prioridad = ? WHERE id_usuario = ?";
        try (
            Connection conn = DBManager.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, idPrioridad);
            ps.setInt(2, idCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al asignar prioridad: " + e.getMessage(), e);
        }
    }
    @Override
    public Cliente obtenerClientePorId(int idUsuario){
        String sqlCliente  = "SELECT * FROM cliente WHERE idUsuario = ?";
        try (Connection conn = DBManager.getInstance().getConnection()) {
            // 1) Tratamos primero como documento
            try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, idUsuario);
                ResultSet rsDoc = psCliente.executeQuery();
                if (rsDoc.next()) {
                    return mapearModelo(rsDoc);
                }
            }
            return null;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error en busqueda de Cliente: " + e.getMessage(), e);
        }
    }
}
