/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.pdm.oferta.dao;

import java.util.List;
import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.ofertamodel.Modalidad;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOfertaModalidad;

/**
 *
 * @author BRAULIO
 */
public interface IPlantillaOfertaModalidadDAO extends ICrud<PlantillaOfertaModalidad>{
    List<Modalidad> listarModalidadesPorPlantilla(int id);
}
