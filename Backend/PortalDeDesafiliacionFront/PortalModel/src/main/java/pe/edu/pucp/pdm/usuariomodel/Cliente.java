package pe.edu.pucp.pdm.usuariomodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.pdm.serviciomodel.Linea;

public class Cliente extends Usuario{
    
    private Region region;
    private String provincia;
    private String departamento;
    private String distrito;
    private int antiguedadMeses;
    private Prioridad prioridad;
    private Date fechaUltimaOferta;
    private List<Linea> lineas = new ArrayList<>();
    private List<Encuesta> encuestas = new ArrayList<>();
    
    public Cliente(String primerNombre, String segundoNombre,String apellidoPaterno,
            String apellidoMaterno,String tipoDocumento, String numeroDocumento,String correo,
            String contrasena,Region region, int antiguedadMeses, Date fechaUltimaOferta){
        super(primerNombre,segundoNombre,apellidoPaterno,apellidoMaterno,tipoDocumento,numeroDocumento,correo,contrasena);
        this.region = region;
        region.incrementarUsuarios();
        this.antiguedadMeses = antiguedadMeses;
        this.fechaUltimaOferta = fechaUltimaOferta;
    }
//    public Cliente(Region region, int antiguedadMeses, Date fechaUltimaOferta){
//            this.region = region;
//            region.incrementarUsuarios();
//            this.antiguedadMeses = antiguedadMeses;
//            this.fechaUltimaOferta = fechaUltimaOferta;
//    }
    
    // Getters y Setters básicos
    
    public int getIdCliente(){
        return super.getIdUsuario();
    }
    
    public void setIdCliente(int id){
        super.setIdUsuario(id);
    }
    
    public Region getRegion() {
        return region;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public int getAntiguedadMeses() {
        return antiguedadMeses;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setAntiguedadMeses(int antiguedadMeses) {
        this.antiguedadMeses = antiguedadMeses;
    }

    // Métodos de relación con Líneas
    public void agregarLinea(Linea linea) {
        this.lineas.add(linea);
    }

    public boolean removerLinea(int idLinea) {
        return lineas.removeIf(l -> l.getIdLinea() == idLinea);
    }

    public List<Linea> getLineas() {
        return new ArrayList<>(lineas);
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
    
    // Métodos de relación con Ofertas
    /*
    public void agregarOferta(Oferta oferta) {
        if (oferta.esValida()) {
            this.ofertas.add(oferta);
        }
    }

    public List<Oferta> getOfertas() {
        return new ArrayList<>(ofertas);
    }

    public List<Oferta> getOfertasActivas() {
        return ofertas.stream().filter(Oferta::esValida).toList();
    }
    */
    // Métodos de negocio
    public void cambiarRegion(Region nuevaRegion) {
        this.region.decrementarUsuarios();
        this.region = nuevaRegion;
        nuevaRegion.incrementarUsuarios();
    }

    public void solicitarDesafiliacion() {
        super.setActivo(false);
        this.region.registrarBaja();
    }

    public Date getFechaUltimaOferta() {
        return fechaUltimaOferta;
    }

    public void setFechaUltimaOferta(Date fechaUltimaOferta) {
        this.fechaUltimaOferta = fechaUltimaOferta;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(List<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }
}