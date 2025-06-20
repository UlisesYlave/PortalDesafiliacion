package pe.edu.pucp.pdm.usuariomodel;

public class Region {
    private int idRegion;
    private String nombre;
    private int numeroBajasRegion;
    private int numeroUsuariosRegion;

    public Region(){
        
    }
    
    public Region(String nombre,int numeroBajasRegion, int numeroUsuariosRegion) {
        this.nombre = nombre;
        this.numeroBajasRegion=numeroBajasRegion;
        this.numeroUsuariosRegion=numeroUsuariosRegion;
    }

    // Getters y Setters
    public int getIdRegion() {
        return idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroBajasRegion() {
        return numeroBajasRegion;
    }

    public int getNumeroUsuariosRegion() {
        return numeroUsuariosRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroBajasRegion(int numeroBajasRegion) {
        this.numeroBajasRegion = numeroBajasRegion;
    }

    public void setNumeroUsuariosRegion(int numeroUsuariosRegion) {
        this.numeroUsuariosRegion = numeroUsuariosRegion;
    }

    // Métodos de negocio
    public void incrementarUsuarios() {
        this.numeroUsuariosRegion++;
    }

    public void decrementarUsuarios() {
        this.numeroUsuariosRegion--;
    }

    public void registrarBaja() {
        this.numeroBajasRegion++;
        decrementarUsuarios();
    }
}