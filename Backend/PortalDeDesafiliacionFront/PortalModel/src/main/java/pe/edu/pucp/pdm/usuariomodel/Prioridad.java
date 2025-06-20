package pe.edu.pucp.pdm.usuariomodel;

public class Prioridad {
    private int idPrioridad;
    private String nombre;
    private int cantidadOfertas;
    
    public Prioridad(String nombre){
        this.nombre=nombre;
    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombrePrioridad) {
        this.nombre = nombrePrioridad;
    }

    public int getCantidadOfertas() {
        return cantidadOfertas;
    }

    public void setCantidadOfertas(int cantidadOfertas) {
        this.cantidadOfertas = cantidadOfertas;
    }
    
}
