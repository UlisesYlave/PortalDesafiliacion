package pe.edu.pucp.pdm.usuariomodel;

import java.util.Date;

public class Encuesta {
    private int idEncuesta;
    private TipoMotivo motivo;
    private int calificacion;
    private String opinion;
    private Date fechaEncuesta;
    
    public Encuesta(){
        
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public TipoMotivo getMotivo() {
        return motivo;
    }

    public void setMotivo(TipoMotivo motivo) {
        this.motivo = motivo;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getFechaEncuesta() {
        return fechaEncuesta;
    }

    public void setFechaEncuesta(Date fechaEncuesta) {
        this.fechaEncuesta = fechaEncuesta;
    }
}
