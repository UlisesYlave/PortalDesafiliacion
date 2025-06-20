package pe.edu.pucp.pdm.usuariomodel;

public class TipoMotivo {
    private int idMotivo;
    private String nombre;
    private String descripcion;
    
    public TipoMotivo(String nombre, String descripcion){
        this.nombre=nombre;
        this.descripcion = descripcion;
    }
    
    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreMotivo) {
        this.nombre = nombreMotivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
