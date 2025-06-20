package pe.edu.pucp.pdm.serviciomodel;

public class Plan extends Servicio{

    public Plan(String nombrePlan, String beneficios,double precio) {
        super(nombrePlan,beneficios,"PLAN",precio);
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
}