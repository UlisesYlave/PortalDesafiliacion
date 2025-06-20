package pe.edu.pucp.pdm.ofertamodel;

public class Parametro {
    private int idParametro;
    private String nombreParametro;

    public Parametro(String nombreParametro){
        this.nombreParametro=nombreParametro;
    }
    
    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }
    
    
}
