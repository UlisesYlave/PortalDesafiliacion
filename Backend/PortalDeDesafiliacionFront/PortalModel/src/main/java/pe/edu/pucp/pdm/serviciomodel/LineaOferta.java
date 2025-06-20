package pe.edu.pucp.pdm.serviciomodel;

import java.util.Date;

public class LineaOferta {
    private int idLineaOferta;
    private int idLinea;
    private int idOferta;
    private String estadoOfertaLinea;
    private Date fechaAplicacion;
    private Date fechaAsociacion;

    public LineaOferta(int idLinea, int idOferta, String estadoOfertaLinea, Date fechaAplicacion, Date fechaAsociacion){
        this.idLinea = idLinea;
        this.idOferta = idOferta;
        this.estadoOfertaLinea = estadoOfertaLinea;
        this.fechaAplicacion = fechaAplicacion;
        this.fechaAsociacion = fechaAsociacion;
    }
    
    public int getIdLineaOferta() {
        return idLineaOferta;
    }

    public void setIdLineaOferta(int idLineaOferta) {
        this.idLineaOferta = idLineaOferta;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getEstadoOfertaLinea() {
        return estadoOfertaLinea;
    }

    public void setEstadoOfertaLinea(String estadoOfertaLinea) {
        this.estadoOfertaLinea = estadoOfertaLinea;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }
    
    
    
}
