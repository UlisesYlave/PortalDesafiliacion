package pe.edu.pucp.pdm.serviciomodel;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.pdm.ofertamodel.Oferta;

public class Postpago extends Linea {
    private double deudaPendiente;
    private Plan plan;
    private int diaCicloFacturacion;
    
    public Postpago(int idCliente, String numeroTelefono,Date fechaActivacion, double deudaPendiente,
            int diaCicloFacturacion) {
        super(idCliente,numeroTelefono, "POSTPAGO",fechaActivacion);
        this.deudaPendiente = deudaPendiente;
        this.diaCicloFacturacion = diaCicloFacturacion;
    }

    public String getDetalleServicio() {
        return String.format("Postpago - Deuda: S/%.2f - Plan: %s", 
               deudaPendiente, 
               (plan != null ? plan.getNombreServicio() : "Ninguno"));
    }

    // Getters y Setters específicos

    public double getDeudaPendiente() {
        return deudaPendiente;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setDeudaPendiente(double deudaPendiente) {
        this.deudaPendiente = deudaPendiente;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    // Getters y Setters
    @Override
    public int getIdLinea() {
        return super.getIdLinea();
    }
    
    
    public void setIdLinea(int id){
        super.setIdLinea(id);
    }
    
    @Override
    public String getNumeroTelefono() {
        return super.getNumeroTelefono();
    }

    @Override
    public String getTipoLinea() {
        return super.getTipoLinea();
    }

    @Override
    public void setNumeroTelefono(String numeroTelefono) {
        super.setNumeroTelefono(numeroTelefono);
    }

    @Override
    public void setTipoLinea(String tipoLinea) {
        super.setTipoLinea(tipoLinea);
    }

    public List<Oferta> getOfertas() {
        return super.getOfertas();
    }

    @Override
    public void setOfertas(List<Oferta> ofertas) {
        super.setOfertas(ofertas);
    }
    
    @Override
    public boolean isActiva() {
        return super.isActiva();
    }

    @Override
    public void setActiva(boolean activa) {
        super.setActiva(activa);
    }

    public int getDiaCicloFacturacion() {
        return diaCicloFacturacion;
    }

    public void setDiaCicloFacturacion(int diaCicloFacturacion) {
        this.diaCicloFacturacion = diaCicloFacturacion;
    }
}