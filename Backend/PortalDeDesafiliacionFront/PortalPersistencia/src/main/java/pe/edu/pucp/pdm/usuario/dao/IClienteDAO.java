package pe.edu.pucp.pdm.usuario.dao;

import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.usuariomodel.Cliente;

public interface IClienteDAO extends ICrud<Cliente> {
    boolean asignarPrioridad(int idCliente, int idPrioridad);
    Cliente obtenerClientePorId(int idUsuario);
}
