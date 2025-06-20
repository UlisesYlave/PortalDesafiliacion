package pe.edu.pucp.pdm.ofertamodel;

import java.util.Date;

public class Oferta extends PlantillaOferta{

    private int idOferta;
    private String nombre;
    private String descripcion;
    private Integer idLinea;
    private Equipo equipo;
    private double descuento;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaCreacion;
    private String estado;
    private String modalidad;
    
    public Oferta(){
        
    }

    public Oferta(int idPlantilla, Equipo equipo, String nombre, String descripcion,double descuento,
            Date fechaInicio, Date fechaFin, Date fechaCreacion, String modalidad){
        super.setIdPlantilla(idPlantilla);
        this.equipo = equipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaCreacion = fechaCreacion;
        this.estado = "PENDIENTE";
        this.modalidad = modalidad;
    }
    
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    @Override
    public int getIdPlantilla() {
        return super.getIdPlantilla();
    }

    @Override
    public void setIdPlantilla(int idPlantilla) {
       super.setIdPlantilla(idPlantilla);
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
}