/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import pe.pucp.edu.pdm.portalbusiness.usuariobo.IClienteBO;
import pe.pucp.edu.pdm.portalbusiness.usuarioimpl.ClienteBOImpl;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;
import pe.edu.pucp.pdm.usuariomodel.Cliente;

/**
 *
 * @author alvma
 */
@WebService(serviceName = "ClienteWS",
        targetNamespace = "http://services.softprog.pucp.edu.pe/"
)
public class ClienteWS {
    private final IClienteBO clienteBO;
    
    public ClienteWS(){
        this.clienteBO = new ClienteBOImpl();
    }
    //********************************************************
    //REQUISITO FUNCIONAL 6 (PRIORIZACION DE USUARIOS)
    //********************************************************
    @WebMethod(operationName = "asignarPrioridadCliente")
    public Prioridad asignarPrioridadCliente(@WebParam(name = "idUsuario") int idUsuario) {
        return clienteBO.buscaYAsignarPrioridadCliente(idUsuario);
    }

    @WebMethod(operationName = "valibleParaRecompensas")
    public boolean valibleParaRecompensas(@WebParam(name = "idUsuario") int idUsuario){
        return clienteBO.valibleParaRecompensas(idUsuario);
    }
//    @WebMethod(operationName = "validarSMSVerificacion")
//    public int validarSMSVerificacion(@WebParam(name = "idUsuario") int idUsuario){
//        return clienteBO.validarSMSVerificacion(idUsuario);
//    }
//    
}
