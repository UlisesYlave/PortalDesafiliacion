package pe.edu.pucp.pdm.serviciomodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Oferta;

public class Linea {
    private int idLinea;
    private int idCliente;
    private String numeroTelefono;
    private String tipoLinea;
    private Date fechaActivacion;
    private boolean activa;
    private List<Oferta> ofertas = new ArrayList<>();

    public Linea(int idCliente, String numeroTelefono, String tipoLinea, Date fechaActivacion) {
        this.idCliente = idCliente;
        this.numeroTelefono = numeroTelefono;
        this.tipoLinea = tipoLinea;
        this.fechaActivacion = fechaActivacion;
        this.activa = true;
    }

    // Getters y Setters
    public int getIdLinea() {
        return idLinea;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getTipoLinea() {
        return tipoLinea;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setTipoLinea(String tipoLinea) {
        this.tipoLinea = tipoLinea;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

}