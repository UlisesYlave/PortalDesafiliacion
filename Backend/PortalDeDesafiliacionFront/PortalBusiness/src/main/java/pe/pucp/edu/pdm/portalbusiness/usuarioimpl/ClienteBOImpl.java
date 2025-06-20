package pe.pucp.edu.pdm.portalbusiness.usuarioimpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.pucp.edu.pdm.portalbusiness.usuariobo.IClienteBO;
import pe.edu.pucp.pdm.usuariomodel.Cliente;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;
import pe.edu.pucp.pdm.serviciomodel.Postpago;
import pe.edu.pucp.pdm.serviciomodel.Prepago;
import pe.edu.pucp.pdm.usuario.dao.IClienteDAO;
import pe.edu.pucp.pdm.usuario.impl.ClienteDAOImpl;

public class ClienteBOImpl implements IClienteBO {

    private final IClienteDAO clienteDAO;

    public ClienteBOImpl() {
        this.clienteDAO = new ClienteDAOImpl();
    }

    @Override
    public Cliente calcularYAsignarPrioridad(Cliente cliente) {
        if (cliente == null) return null;

        // 1) Obtener datos del cliente
        String regionNombre = "";
        if (cliente.getRegion() != null && cliente.getRegion().getNombre() != null) {
            regionNombre = cliente.getRegion().getNombre().trim().toLowerCase();
        }
        int antiguedadMeses = cliente.getAntiguedadMeses();
        double montoMensual = 0.0;

        // 2) Determinar el “monto de pago mensual” según tipo de línea:
        List<?> lineas = cliente.getLineas();
        if (lineas != null && !lineas.isEmpty()) {
            Object primeraLinea = lineas.get(0);
            if (primeraLinea instanceof Postpago) {
                Postpago post = (Postpago) primeraLinea;
                if (post.getPlan() != null) {
                    montoMensual = post.getPlan().getPrecio();
                }
            } else if (primeraLinea instanceof Prepago) {
                Prepago prep = (Prepago) primeraLinea;
                montoMensual = prep.getPromedioRecargaMensual();
            }
        }

        // 3) Puntaje por región
        int ptsRegion;
        switch (regionNombre) {
            case "lima":
            case "lima metropolitana":
                ptsRegion = 3; break;
            case "callao":
                ptsRegion = 2; break;
            default:
                ptsRegion = 1; break;
        }

        // 4) Puntaje por monto mensual
        int ptsMonto;
        if (montoMensual >= 100.0) {
            ptsMonto = 3;
        } else if (montoMensual >= 50.0) {
            ptsMonto = 2;
        } else {
            ptsMonto = 1;
        }

        // 5) Puntaje por antigüedad
        int ptsAntiguedad;
        if (antiguedadMeses >= 12) {
            ptsAntiguedad = 3;
        } else if (antiguedadMeses >= 6) {
            ptsAntiguedad = 2;
        } else {
            ptsAntiguedad = 1;
        }

        // 6) Sumar los puntajes (3..9)
        int suma = ptsRegion + ptsMonto + ptsAntiguedad;

        // 7) Determinar categoría de prioridad
        Prioridad p;
        if (suma >= 8) {
            p = new Prioridad("ALTA");
            p.setIdPrioridad(1);
        } else if (suma >= 5) {
            p = new Prioridad("MEDIA");
            p.setIdPrioridad(2);
        } else {
            p = new Prioridad("BAJA");
            p.setIdPrioridad(3);
        }

        // 8) Persistir el cambio en BD
        cliente.setPrioridad(p);
        clienteDAO.asignarPrioridad(cliente.getIdCliente(), p.getIdPrioridad());

        // 9) Devolver el cliente con prioridad actualizada
        return cliente;
    }
    @Override
    public Prioridad buscaYAsignarPrioridadCliente(int idUsuario){
        Cliente cliente = clienteDAO.obtenerClientePorId(idUsuario);
        if(cliente != null)
            return calcularYAsignarPrioridad(cliente).getPrioridad();
        else
            return null;
    }
    
    @Override
    public boolean valibleParaRecompensas(int idUsuario) {
        Cliente cliente = clienteDAO.obtenerClientePorId(idUsuario);
        
        if (cliente == null) return false; // No existe el cliente
        
        // Verificar si el cliente o su fecha son null
        if (cliente.getFechaUltimaOferta() == null) return true; // Todavía no acepta una oferta

        // Convertir Date a Instant y luego a LocalDateTime
        Instant instant = cliente.getFechaUltimaOferta().toInstant();
        LocalDateTime fechaUltimaOferta = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime haceCincoMeses = hoy.minusMonths(5);

        return fechaUltimaOferta.isBefore(haceCincoMeses);
    }

    @Override
    public int validarSMSVerificacion(int idUsuario) {
        Cliente cliente = clienteDAO.obtenerClientePorId(idUsuario);
        List<Linea> lineas =cliente.getLineas();
        
        int codigo=0;
        
        return codigo;
    }
}
