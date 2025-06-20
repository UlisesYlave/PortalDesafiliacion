package pe.edu.pucp.pdm.ofertamodel;

public class PlantillaOfertaModalidad {
    private int id;
    private int idPlantillaOferta;
    private int idModalidad;
    
    public PlantillaOfertaModalidad(int idP,int idM){
        this.idModalidad = idM;
        this.idPlantillaOferta = idP;
    }

    public int getIdPlantillaOferta() {
        return idPlantillaOferta;
    }

    public void setIdPlantillaOferta(int idPlantillaOferta) {
        this.idPlantillaOferta = idPlantillaOferta;
    }

    public int getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(int idModalidad) {
        this.idModalidad = idModalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
