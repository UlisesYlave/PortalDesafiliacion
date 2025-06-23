package pe.edu.pucp.pdm.oferta.dao;

import java.util.List;
import pe.edu.pucp.pdm.dao.ICrud;
import pe.edu.pucp.pdm.ofertamodel.Parametro;

public interface IParametroDAO extends ICrud<Parametro>{
    public List<Parametro>listarParametros(List<Integer> idParametro);
}
