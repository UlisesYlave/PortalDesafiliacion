package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;
import pe.edu.pucp.pdm.oferta.dao.IModalidadDAO;

public class ModalidadDAOImpl  extends BaseDAOImpl<Modalidad> implements IModalidadDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Modalidad modalidad) throws SQLException {
        String sql = "{CALL insertarModalidad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", modalidad.getNombre());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, Modalidad modalidad) throws SQLException {
        String sql = "{CALL modificarModalidad(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
         cmd.setString("p_nombre", modalidad.getNombre());
        cmd.setInt("p_idModalidad", modalidad.getIdModalidad());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarModalidad(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idModalidad", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarModalidadPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idModalidad", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarModalidades()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Modalidad mapearModelo(ResultSet rs) throws SQLException {
        int id = rs.getInt("idModalidad");
        Modalidad modalidad = new Modalidad(
                rs.getString("nombre")
        );
        modalidad.setIdModalidad(id);
        return modalidad;
    }
}
