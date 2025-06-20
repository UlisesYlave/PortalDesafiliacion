package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Equipo;
import pe.edu.pucp.pdm.ofertamodel.Oferta;
import pe.edu.pucp.pdm.oferta.dao.IOfertaDAO;

public class OfertaDAOImpl  extends BaseDAOImpl<Oferta> implements IOfertaDAO{
    
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Oferta oferta) throws SQLException {
        String sql = "{CALL insertarOferta(?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantillaOferta", oferta.getIdPlantilla());
        if (oferta.getIdLinea() != null){
           cmd.setInt("p_idLinea",oferta.getIdLinea()); 
        }else{
            cmd.setNull("p_idLinea", Types.INTEGER);
        }
        if (oferta.getEquipo().getIdEquipo() != null){
            cmd.setInt("p_idEquipo",oferta.getEquipo().getIdEquipo());
        }else{
            cmd.setNull("p_idEquipo", Types.INTEGER);
        }
        cmd.setString("p_nombre", oferta.getNombre());
        cmd.setString("p_descripcion",oferta.getDescripcion());
        cmd.setTimestamp("p_fechaCreacion", new java.sql.Timestamp(oferta.getFechaCreacion().getTime()));
        cmd.setTimestamp("p_fechaInicio", new java.sql.Timestamp(oferta.getFechaInicio().getTime()));
        cmd.setTimestamp("p_fechaFin", new java.sql.Timestamp(oferta.getFechaFin().getTime()));
        cmd.setDouble("p_descuento", oferta.getDescuento());
        cmd.setString("p_modalidad",oferta.getModalidad());
        cmd.setString("p_estado",oferta.getEstado());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoModificar(Connection conn, Oferta oferta) throws SQLException {
        String sql = "{CALL modificarOferta(?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantillaOferta", oferta.getIdPlantilla());
        if (oferta.getIdLinea() != null){
           cmd.setInt("p_idLinea",oferta.getIdLinea()); 
        }else{
            cmd.setNull("p_idLinea", Types.INTEGER);
        }
        
        if (oferta.getEquipo().getIdEquipo() != null){
            cmd.setInt("p_idEquipo",oferta.getEquipo().getIdEquipo());
        }else{
            cmd.setNull("p_idEquipo", Types.INTEGER);
        }
        cmd.setString("p_nombre", oferta.getNombre());
        cmd.setString("p_descripcion",oferta.getDescripcion());
        cmd.setTimestamp("p_fechaCreacion", new java.sql.Timestamp(oferta.getFechaCreacion().getTime()));
        cmd.setTimestamp("p_fechaInicio", new java.sql.Timestamp(oferta.getFechaInicio().getTime()));
        cmd.setTimestamp("p_fechaFin", new java.sql.Timestamp(oferta.getFechaFin().getTime()));
        cmd.setDouble("p_descuento", oferta.getDescuento());
        cmd.setString("p_modalidad",oferta.getModalidad());
        cmd.setString("p_estado",oferta.getEstado()); 
        cmd.setInt("p_idOferta", oferta.getIdOferta());
        return cmd;
    }
    
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarOferta(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idOferta", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarOfertaPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idOferta", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarOfertas()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Oferta mapearModelo(ResultSet rs) throws SQLException {
        EquipoDAOImpl equipoDao = new EquipoDAOImpl();
        Equipo equipo = equipoDao.buscar(rs.getInt("equipoIdEquipo"));
        
        Oferta oferta = new Oferta();
        oferta.setIdPlantilla(rs.getInt("idPlantillaOferta"));
        oferta.setIdOferta(rs.getInt("idOferta"));
        oferta.setIdLinea(rs.getInt("idLinea"));
        oferta.setEquipo(equipo);
        oferta.setNombre(rs.getString("nombre"));
        oferta.setDescripcion(rs.getString("descripcion"));
        oferta.setFechaCreacion(rs.getDate("fechaCreacionOferta"));
        oferta.setFechaInicio(rs.getDate("fechaInicioVigencia"));
        oferta.setFechaFin(rs.getDate("fechaFinVigencia"));
        oferta.setDescuento(rs.getDouble("descuentoAplicado"));
        oferta.setEstado(rs.getString("estado"));
        oferta.setModalidad(rs.getString("modalidadSeleccionada"));
        return oferta;
    }
}
