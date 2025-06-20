package pe.edu.pucp.pdm.usuario.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Encuesta;
import pe.edu.pucp.pdm.usuariomodel.TipoMotivo;
import pe.edu.pucp.pdm.usuario.dao.IEncuestaDAO;

public class EncuestaDAOImpl extends BaseDAOImpl<Encuesta> implements IEncuestaDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Encuesta encuesta) throws SQLException {
        String sql = "{CALL insertarEncuesta(?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idTipoMotivo", encuesta.getMotivo().getIdMotivo());
        cmd.setTimestamp("p_fechaEncuesta", new java.sql.Timestamp(encuesta.getFechaEncuesta().getTime()));
        cmd.setInt("p_calificacion", encuesta.getCalificacion());
        cmd.setString("p_comentarios", encuesta.getOpinion());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, Encuesta encuesta) throws SQLException {
        String sql = "{CALL modificarEncuesta(?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idTipoMotivo", encuesta.getMotivo().getIdMotivo());
        cmd.setTimestamp("p_fechaEncuesta", new java.sql.Timestamp(encuesta.getFechaEncuesta().getTime()));
        cmd.setInt("p_calificacion", encuesta.getCalificacion());
        cmd.setString("p_comentarios", encuesta.getOpinion());
        cmd.setInt("p_idEncuesta", encuesta.getIdEncuesta());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarEncuesta(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idEncuesta", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarEncuestaPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idEncuesta", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarEncuestas()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Encuesta mapearModelo(ResultSet rs) throws SQLException {
        //buscar TipoMotivo
        TipoMotivoDAOImpl motivoDao = new TipoMotivoDAOImpl();
        TipoMotivo motivo = motivoDao.buscar(rs.getInt("idTipoMotivo"));
        
        Encuesta encuesta = new Encuesta();
        encuesta.setIdEncuesta(rs.getInt("IdEncuesta"));
        encuesta.setMotivo(motivo);
        encuesta.setCalificacion(rs.getInt("calificacion"));
        encuesta.setOpinion(rs.getString("opinion"));
        encuesta.setFechaEncuesta(rs.getDate("fechaEncuesta"));
        return encuesta;
    }
}
