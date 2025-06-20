package pe.edu.pucp.pdm.serviciomodel;

import java.util.Date;
import pe.edu.pucp.pdm.ofertamodel.Oferta;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.usuariomodel.Encuesta;

public class SolicitudDesafiliacion {

    private int idSolicitud;
    private int idLinea;
    private int idCliente;
    private Integer idOferta;
    private Encuesta encuesta;
    private Date fechaSolicitud;
    private Resultado resultado;
    private String observacionAgente;
    
    public SolicitudDesafiliacion(int idLinea, int idCliente, Encuesta encuesta, Date fechaSolicitud, 
            Resultado resultado, String observacionAgente){
        this.idLinea = idLinea;
        this.idCliente = idCliente;
        this.encuesta = encuesta;
        this.fechaSolicitud = fechaSolicitud;
        this.resultado = resultado;
        this.observacionAgente = observacionAgente;
    }
    public SolicitudDesafiliacion(int idLinea, int idCliente, Date fechaSolicitud, 
            Resultado resultado, String observacionAgente){
        this.idLinea = idLinea;
        this.idCliente = idCliente;
//        this.encuesta = encuesta;
        this.fechaSolicitud = fechaSolicitud;
        this.resultado = resultado;
        this.observacionAgente = observacionAgente;
    }
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getObservacionAgente() {
        return observacionAgente;
    }

    public void setObservacionAgente(String observacionAgente) {
        this.observacionAgente = observacionAgente;
    }
    
}
