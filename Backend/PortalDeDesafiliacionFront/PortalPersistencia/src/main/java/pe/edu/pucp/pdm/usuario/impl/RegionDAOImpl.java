package pe.edu.pucp.pdm.usuario.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Region;
import pe.edu.pucp.pdm.usuario.dao.IRegionDAO;

public class RegionDAOImpl extends BaseDAOImpl<Region> implements IRegionDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Region region) throws SQLException {
        String sql = "{CALL insertarRegion(?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", region.getNombre());
        cmd.setInt("p_numeroBajasRegion", region.getNumeroBajasRegion());
        cmd.setInt("p_numeroUsuariosRegion", region.getNumeroUsuariosRegion());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, Region region) throws SQLException {
        String sql = "{CALL modificarRegion(?,?,?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", region.getNombre());
        cmd.setInt("p_numeroBajasRegion", region.getNumeroBajasRegion());
        cmd.setInt("p_numeroUsuariosRegion", region.getNumeroUsuariosRegion());
        cmd.setInt("p_idRegion", region.getIdRegion());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarRegion(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idRegion", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarRegionPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idRegion", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarRegiones()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Region mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idRegion");
        Region region = new Region(
                rs.getString("nombre"),
                rs.getInt("numeroBajasRegion"),
                rs.getInt("numeroUsuariosRegion")
        );
        region.setIdRegion(id);
        return region;
    }
}
