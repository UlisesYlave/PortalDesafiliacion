package pe.edu.pucp.pdm.serviciomodel;

public abstract class Servicio {

    private int idServicio;
    private String nombreServicio;
    private String beneficios;
    private String tipoServicio;
    private double precio;
    
    public Servicio(String nombreServicio, String beneficios, 
            String tipoServicio, double precio){
        this.nombreServicio=nombreServicio;
        this.beneficios=beneficios;
        this.tipoServicio=tipoServicio;
        this.precio=precio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}
