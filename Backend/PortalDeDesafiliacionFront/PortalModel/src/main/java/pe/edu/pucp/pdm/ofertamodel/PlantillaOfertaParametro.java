package pe.edu.pucp.pdm.ofertamodel;

public class PlantillaOfertaParametro {
    private int id;
    private int idPlantillaOferta;
    private int idParametro;
    private String valorParametro;
    
    public PlantillaOfertaParametro(int idPl,int idPa,String valorParametro){
        this.idParametro = idPa;
        this.idPlantillaOferta = idPl;
        this.valorParametro = valorParametro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlantillaOferta() {
        return idPlantillaOferta;
    }

    public void setIdPlantillaOferta(int idPlantillaOferta) {
        this.idPlantillaOferta = idPlantillaOferta;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }
    
}
