package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.oferta.dao.IParametroDAO;
import pe.edu.pucp.pdm.ofertamodel.Parametro;

public class ParametroDAOImpl extends BaseDAOImpl<Parametro> implements IParametroDAO {
    @Override
    protected CallableStatement comandoInsertar(Connection conn, Parametro param) throws SQLException {
        String sql = "{CALL insertarParametro(?,?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombreParametro", param.getNombreParametro());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }
    
     @Override
    protected CallableStatement comandoModificar(Connection conn, Parametro param) throws SQLException {
        String sql = "{CALL modificarParametro(?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idParametro", param.getIdParametro());
        cmd.setString("p_nombreParametro", param.getNombreParametro());
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarParametro(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idParametro", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarParametroPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idParametro", id);
        return cmd;
    }
    
    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarParametros()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected Parametro mapearModelo(ResultSet rs) throws SQLException {
        Parametro param = new Parametro(
                rs.getString("nombreParametro")
        );
        param.setIdParametro(rs.getInt("idParametro"));
        return param;
    }
    
    @Override
    public List<Parametro>listarParametros(List<Integer> idParametro){
        List <Parametro> parametros = new ArrayList<>();
        for(Integer id:idParametro){
            Parametro nuevoParam = buscar(id);
            parametros.add(nuevoParam);
        }
        return parametros;
    }
}
