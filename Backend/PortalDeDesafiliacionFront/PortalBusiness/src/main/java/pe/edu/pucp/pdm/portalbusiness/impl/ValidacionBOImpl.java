/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.pdm.portalbusiness.impl;

import pe.edu.pucp.pdm.portalbusiness.IValidacionBO;
import pe.edu.pucp.pdm.usuario.dao.IUsuarioDAO;
import pe.edu.pucp.pdm.usuario.impl.UsuarioDAOImpl;

public class ValidacionBOImpl implements IValidacionBO {

    private final IUsuarioDAO usuarioDAO;

    public ValidacionBOImpl() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public boolean existeDocumento(String tipoDocumento, String numeroDocumento) {
        return usuarioDAO.existePorDocumento(tipoDocumento, numeroDocumento);
        
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioDAO.existePorCorreo(correo);
    }
}
