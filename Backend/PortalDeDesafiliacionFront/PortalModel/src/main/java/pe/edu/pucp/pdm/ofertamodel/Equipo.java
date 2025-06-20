package pe.edu.pucp.pdm.ofertamodel;

public class Equipo {

    private Integer idEquipo;
    private String marca;
    private String modelo;
    private double precio;
    private int stock;
    private Categoria categoria;
    private String estado;
    
    public enum Categoria{
        ALTA,
        MEDIA,
        BAJA
    }
    
    public Equipo(String marca,String modelo,int stock,
            double precio,String estado){
        this.marca = marca;
        this.modelo=modelo;
        this.precio=precio;
//        this.categoria=categoria;
        this.stock=stock;
        this.estado=estado;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
