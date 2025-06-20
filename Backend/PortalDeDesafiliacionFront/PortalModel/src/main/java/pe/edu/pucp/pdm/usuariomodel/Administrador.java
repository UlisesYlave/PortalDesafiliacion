package pe.edu.pucp.pdm.usuariomodel;

import java.util.Date;

public class Administrador extends Usuario{
    private Date fechaVigencia;
    
    
    
    public Administrador(String primerNombre, String segundoNombre,String apellidoPaterno,
            String apellidoMaterno,String tipoDocumento, String numeroDocumento,String correo,
            String contrasena,Date fechaVigencia) {
        super(primerNombre,segundoNombre,apellidoPaterno,apellidoMaterno,tipoDocumento,numeroDocumento,correo,contrasena);
        this.fechaVigencia=fechaVigencia;
    }

    public int getIdAdministrador(){
        return super.getIdUsuario();
    }
    
    public void setIdAdministrador(int id){
        super.setIdUsuario(id);
    }
    
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

}
