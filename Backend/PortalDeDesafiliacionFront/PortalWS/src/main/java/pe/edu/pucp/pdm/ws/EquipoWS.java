package pe.edu.pucp.pdm.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Equipo;
import pe.pucp.edu.pdm.portalbusiness.ofertabo.IEquipoBO;
import pe.pucp.edu.pdm.portalbusiness.ofertaimpl.EquipoBOImpl;

@WebService(serviceName = "EquipoWS")
public class EquipoWS {
    private final IEquipoBO equipoBO;

    public EquipoWS() {
        this.equipoBO = new EquipoBOImpl();
    }
    
    @WebMethod(operationName = "listarEquipos")
    public List<Equipo> listarEquipos() {
        return equipoBO.listar();
    }
    
    @WebMethod(operationName = "insertarEquipo")
    public int insertarEquipo(@WebParam(name = "equipo") Equipo equipo) {
        return equipoBO.insertar(equipo);
    }

    
    @WebMethod(operationName = "modificarEquipo")
    public boolean modificarEquipo(@WebParam(name = "equipo") Equipo equipo) {
        return equipoBO.modificar(equipo);
    }

    @WebMethod(operationName = "eliminarEquipo")
    public boolean eliminarEquipo(@WebParam(name = "idEquipo") int idEquipo) {
        return equipoBO.eliminar(idEquipo);
    }

    @WebMethod(operationName = "buscarEquipoPorId")
    public Equipo buscarEquipoPorId(@WebParam(name = "idEquipo") int idEquipo) {
        return equipoBO.buscarPorId(idEquipo);
    }
}