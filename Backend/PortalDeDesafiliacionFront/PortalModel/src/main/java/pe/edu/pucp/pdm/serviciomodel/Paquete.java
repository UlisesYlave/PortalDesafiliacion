package pe.edu.pucp.pdm.serviciomodel;

public class Paquete extends Servicio{
    private int duracionDias;

    public Paquete(String nombrePaquete, String beneficios,double precio,int duracionDias) {
        super(nombrePaquete,beneficios,"PAQUETE",precio);
        this.duracionDias=duracionDias;
    }
    public int getIdServicio() {
        return super.getIdServicio();
    }

    public void setIdServicio(int idServicio) {
        super.setIdServicio(idServicio);
    }

    public String getNombreServicio() {
        return super.getNombreServicio();
    }

    public void setNombreServicio(String nombreServicio) {
        super.setNombreServicio(nombreServicio);
    }

    public String getBeneficios() {
        return super.getBeneficios();
    }

    public void setBeneficios(String beneficios) {
        super.setBeneficios(beneficios);
    }

    public double getPrecio() {
        return super.getPrecio();
    }

    public void setPrecio(double precio) {
        super.setPrecio(precio);
    }
    
    public String getTipoServicio() {
        return super.getTipoServicio();
    }

    public void setTipoServicio(String tipoServicio) {
        super.setTipoServicio(tipoServicio);
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }
}