package pe.edu.pucp.pdm.ofertamodel;

public class Modalidad {

    private int idModalidad;
    private String nombre;
    
    public Modalidad(String nombre){
        this.nombre=nombre;
    }

    public int getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(int idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombreModalidad(String nombreModalidad) {
        this.nombre = nombreModalidad;
    }
}
