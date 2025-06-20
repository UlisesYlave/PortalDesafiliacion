package pe.edu.pucp.pdm.serviciomodel;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Oferta;

public class Prepago extends Linea {
    private double promedioRecargaMensual;
    private Date fechaUltimaRecarga;
    private Paquete paquete;
    private double saldoActual;

    public Prepago(int idCliente, String numeroTelefono,Date fechaActivacion, 
            double promedioRecargaMensual, double saldoActual,Date fechaUltimaRecarga) {
        super(idCliente,numeroTelefono, "PREPAGO",fechaActivacion);
        this.promedioRecargaMensual = promedioRecargaMensual;
        this.saldoActual = saldoActual;
        this.fechaUltimaRecarga = fechaUltimaRecarga;
    }

    public String getDetalleServicio() {
        return String.format("Prepago - Recarga promedio: S/%.2f - Paquete: %s", 
               promedioRecargaMensual, 
               (paquete != null ? paquete.getNombreServicio() : "Ninguno"));
    }

    // Getters y Setters específicos

    public double getPromedioRecargaMensual() {
        return promedioRecargaMensual;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPromedioRecargaMensual(double promedioRecargaMensual) {
        this.promedioRecargaMensual = promedioRecargaMensual;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    
    // Getters y Setters
    public int getIdLinea() {
        return super.getIdLinea();
    }

    public String getNumeroTelefono() {
        return super.getNumeroTelefono();
    }

    public String getTipoLinea() {
        return super.getTipoLinea();
    }

    public void setNumeroTelefono(String numeroTelefono) {
        super.setNumeroTelefono(numeroTelefono);
    }

    public void setTipoLinea(String tipoLinea) {
        super.setTipoLinea(tipoLinea);
    }

    public List<Oferta> getOfertas() {
        return super.getOfertas();
    }

    public void setOfertas(List<Oferta> ofertas) {
        super.setOfertas(ofertas);
    }
    
    public boolean isActiva() {
        return super.isActiva();
    }

    public void setActiva(boolean activa) {
        super.setActiva(activa);
    }

    public Date getFechaUltimaRecarga() {
        return fechaUltimaRecarga;
    }

    public void setFechaUltimaRecarga(Date fechaUltimaRecarga) {
        this.fechaUltimaRecarga = fechaUltimaRecarga;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }
}