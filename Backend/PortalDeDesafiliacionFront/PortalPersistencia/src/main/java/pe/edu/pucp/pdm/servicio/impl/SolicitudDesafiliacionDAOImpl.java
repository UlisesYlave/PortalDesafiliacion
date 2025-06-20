package pe.edu.pucp.pdm.servicio.impl;

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
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.serviciomodel.SolicitudDesafiliacion;
import pe.edu.pucp.pdm.servicio.dao.ISolicitudDesafiliacionDAO;
import pe.edu.pucp.pdm.serviciomodel.Resultado;
import pe.edu.pucp.pdm.usuariomodel.Encuesta;
import pe.edu.pucp.pdm.usuariomodel.TipoMotivo;

public class SolicitudDesafiliacionDAOImpl extends BaseDAOImpl<SolicitudDesafiliacion> implements ISolicitudDesafiliacionDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, SolicitudDesafiliacion solicitud) throws SQLException {
        String sql = "{CALL insertarSolicitudDesafiliacion(?, ?, ?, ?,?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idLinea", solicitud.getIdLinea());
        cmd.setTimestamp("p_fechaSolicitud", new java.sql.Timestamp(solicitud.getFechaSolicitud().getTime()));
        cmd.setString("p_resultado", solicitud.getResultado().toString());
        cmd.setInt("p_idCliente",solicitud.getIdCliente());
        // Manejo correcto del parámetro nullable
        if (solicitud.getIdOferta() != null) {
            cmd.setInt("p_idOfertaAceptada", solicitud.getIdOferta());
        } else {
            cmd.setNull("p_idOfertaAceptada", Types.INTEGER);
        }
        cmd.setInt("p_idEncuesta",solicitud.getEncuesta().getIdEncuesta());
        cmd.setString("p_observacionesAgente", solicitud.getObservacionAgente());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, SolicitudDesafiliacion solicitud) throws SQLException {
        String sql = "{CALL modificarSolicitudDesafiliacion(?, ?, ?, ?,?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", solicitud.getIdSolicitud());
        cmd.setInt("p_idLinea", solicitud.getIdLinea());
        cmd.setTimestamp("p_fechaSolicitud", new java.sql.Timestamp(solicitud.getFechaSolicitud().getTime()));
        cmd.setString("p_resultado", solicitud.getResultado().toString());
        cmd.setInt("p_idCliente",solicitud.getIdCliente());
        // Manejo correcto del parámetro nullable
        if (solicitud.getIdOferta() != null) {
            cmd.setInt("p_idOfertaAceptada", solicitud.getIdOferta());
        } else {
            cmd.setNull("p_idOfertaAceptada", Types.INTEGER);
        }
        cmd.setInt("p_idEncuesta",solicitud.getEncuesta().getIdEncuesta());
        cmd.setString("p_observacionesAgente", solicitud.getObservacionAgente());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarSolicitudDesafiliacion(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idSolicitud", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarSolicitudDesafiliacionPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idSolicitud", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarSolicitudesDesafiliacion()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected SolicitudDesafiliacion mapearModelo(ResultSet rs) throws SQLException {
        TipoMotivo motivo = new TipoMotivo(rs.getString("motivoNombre")," ");
        
        Encuesta encuesta = new Encuesta();
        encuesta.setIdEncuesta(rs.getInt("idEncuesta"));
        encuesta.setCalificacion(rs.getInt("encuestaCalificacion"));
        encuesta.setMotivo(motivo);
        encuesta.setOpinion(rs.getString("encuestaOpinion"));
        
        SolicitudDesafiliacion solicitud = new SolicitudDesafiliacion(
                rs.getInt("idLinea"),
                rs.getInt("idCliente"),
                encuesta,
                rs.getDate("fechaSolicitud"),
                Resultado.valueOf(rs.getString("resultado")),
                rs.getString("observacionesAgente")
        );
        solicitud.setIdSolicitud(rs.getInt("idSolicitudDesafiliacion"));

        return solicitud;
    }
    
    @Override
    public List<SolicitudDesafiliacion>listarSolicitudesDesafiliacionPorCliente(int id){
        String sql = "{CALL listarSolicitudesDesafiliacionPorCliente(?)}";
        try (
            Connection conn = DBManager.getInstance().getConnection();
            CallableStatement cmd = conn.prepareCall(sql);
        ) {
            cmd.setInt("p_idCliente", id);
            
            try(PreparedStatement ps = cmd){
                ResultSet rs = ps.executeQuery();

                List<SolicitudDesafiliacion> solicitudes = new ArrayList<>();
                while (rs.next()) {
                    solicitudes.add(this.mapearModelo(rs));
                }
                return solicitudes;
                
            }catch (SQLException e) {
                System.err.println("Error SQL durante el listado: " + e.getMessage());
                throw new RuntimeException("No se pudo listar el registro.", e);
            }
            
        } catch (Exception e) {
                System.err.println("Error inpesperado: " + e.getMessage());
                throw new RuntimeException("Error inesperado al listar los registros.", e);
         }
    }
    
    @Override
    public boolean actualizarResultadoDesafiliacion(int idLinea) {
        String sql = "{CALL actualizarEstadoLinea(?, ?)}";
        try (
            Connection conn = DBManager.getInstance().getConnection();
            CallableStatement cmd = conn.prepareCall(sql);
        ) {
            cmd.setInt(1, idLinea);
            cmd.setBoolean(2, false); // Desactivar la línea
            cmd.execute();
            return true;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


}