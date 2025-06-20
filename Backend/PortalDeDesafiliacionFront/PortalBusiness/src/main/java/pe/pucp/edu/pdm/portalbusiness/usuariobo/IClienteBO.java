package pe.pucp.edu.pdm.portalbusiness.usuariobo;

import pe.edu.pucp.pdm.usuariomodel.Cliente;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;

public interface IClienteBO {
    
    Cliente calcularYAsignarPrioridad(Cliente cliente);
    
    Prioridad buscaYAsignarPrioridadCliente(int idUsuario);
    
    boolean valibleParaRecompensas(int idUsuario);
    
    int validarSMSVerificacion(int idUsuario);
}
