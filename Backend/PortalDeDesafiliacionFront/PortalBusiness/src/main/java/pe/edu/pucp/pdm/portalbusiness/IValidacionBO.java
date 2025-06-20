/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pdm.portalbusiness;

/**
 * Operaciones de validación en “tiempo real” para formularios:
 *  - Verificar si ya existe DNI
 *  - Verificar si ya existe correo
 */
public interface IValidacionBO {
    /**
     * Retorna true si ya hay un usuario con ese tipo + número de documento registrado.
     *
     * @param tipoDocumento   Ejemplo: "DNI", "CE"
     * @param numeroDocumento Ejemplo: "12345678"
     */
    boolean existeDocumento(String tipoDocumento, String numeroDocumento);

    /**
     * Retorna true si ya hay un usuario con ese correo registrado.
     *
     * @param correo Correo a verificar.
     */
    boolean existeCorreo(String correo);
}
