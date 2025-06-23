package pe.edu.pucp.pdm.ofertamodel;

import java.util.Date;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;
import java.util.*;

public class PlantillaOferta {
    private int idPlantilla;
    private String nombre;
    private String formula;
    private int tipoServicio; 
    private Prioridad prioridad;
    private Date periodoValidez;
    private boolean activa;
    private List<Modalidad> modalidades = new ArrayList();
    private List<Parametro> parametros = new ArrayList();
    
    public PlantillaOferta(){}
    
    public PlantillaOferta(String nombre,String formula,
            int tipoServicio, Prioridad prioridad,Date periodoValidez){
        this.nombre=nombre;
        this.formula=formula;
        this.tipoServicio=tipoServicio;
        this.prioridad=prioridad;
        this.periodoValidez=periodoValidez;
        this.activa=true;
    }

    public int getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(int idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Date getPeriodoValidez() {
        return periodoValidez;
    }

    public void setPeriodoValidez(Date periodoValidez) {
        this.periodoValidez = periodoValidez;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<Modalidad> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<Modalidad> modalidades) {
        this.modalidades = modalidades;
    }
    
    public List<Parametro> getParametros(){
        return this.parametros;
    }
    
    public void setParametros(List<Parametro> parametros){
        this.parametros = parametros;
    }
}