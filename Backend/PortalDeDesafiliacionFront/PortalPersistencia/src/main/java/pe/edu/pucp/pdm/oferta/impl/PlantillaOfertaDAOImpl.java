package pe.edu.pucp.pdm.oferta.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pe.edu.pucp.pdm.config.DBManager;
import pe.edu.pucp.pdm.dao.BaseDAOImpl;
import pe.edu.pucp.pdm.oferta.dao.IEquipoDAO;
import pe.edu.pucp.pdm.oferta.dao.IParametroDAO;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOferta;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaDAO;
import pe.edu.pucp.pdm.oferta.dao.IPlantillaOfertaParametroDAO;
import pe.edu.pucp.pdm.ofertamodel.Equipo;
import pe.edu.pucp.pdm.ofertamodel.Oferta;
import pe.edu.pucp.pdm.ofertamodel.Parametro;
import pe.edu.pucp.pdm.ofertamodel.PlantillaOfertaParametro;
import pe.edu.pucp.pdm.serviciomodel.Linea;
import pe.edu.pucp.pdm.usuario.dao.IPrioridadDAO;
import pe.edu.pucp.pdm.usuario.impl.PrioridadDAOImpl;
import pe.edu.pucp.pdm.usuariomodel.Prioridad;

public class PlantillaOfertaDAOImpl  extends BaseDAOImpl<PlantillaOferta> implements IPlantillaOfertaDAO{
    @Override
    protected CallableStatement comandoInsertar(Connection conn, PlantillaOferta plantilla) throws SQLException {
        String sql = "{CALL insertarPlantillaOferta(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setString("p_nombre", plantilla.getNombre());
        cmd.setString("p_descripcion", plantilla.getFormula()); // Asumo que formula corresponde a descripcion
        cmd.setTimestamp("p_fechaCreacion", new java.sql.Timestamp(plantilla.getPeriodoValidez().getTime()));
        cmd.setBoolean("p_activa", plantilla.isActiva());
        cmd.setInt("p_tipoServicio", plantilla.getTipoServicio());
        cmd.setInt("p_idPrioridad", plantilla.getPrioridad().getIdPrioridad());
        cmd.registerOutParameter("p_id", Types.INTEGER);
        return cmd;
    }

    @Override
    protected CallableStatement comandoModificar(Connection conn, PlantillaOferta plantilla) throws SQLException {
        String sql = "{CALL modificarPlantillaOferta(?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_id", plantilla.getIdPlantilla());
        cmd.setString("p_nombre", plantilla.getNombre());
        cmd.setString("p_descripcion", plantilla.getFormula()); // Asumo que formula corresponde a descripcion
        cmd.setTimestamp("p_fechaCreacion", new java.sql.Timestamp(plantilla.getPeriodoValidez().getTime()));
        cmd.setBoolean("p_activa", plantilla.isActiva());
        cmd.setInt("p_tipoServicio", plantilla.getTipoServicio());
        cmd.setInt("p_idPrioridad", plantilla.getPrioridad().getIdPrioridad());
        return cmd;
    }

    @Override
    protected CallableStatement comandoEliminar(Connection conn, int id) throws SQLException {
        String sql = "{CALL eliminarPlantillaOferta(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantilla", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoBuscar(Connection conn, int id) throws SQLException {
        String sql = "{CALL buscarPlantillaOfertaPorId(?)}";
        CallableStatement cmd = conn.prepareCall(sql);
        cmd.setInt("p_idPlantilla", id);
        return cmd;
    }

    @Override
    protected CallableStatement comandoListar(Connection conn) throws SQLException {
        String sql = "{CALL listarPlantillasOferta()}";
        CallableStatement cmd = conn.prepareCall(sql);
        return cmd;
    }
    
    @Override
    protected PlantillaOferta mapearModelo(ResultSet rs) throws SQLException {
        IPrioridadDAO prioridadDAO = new PrioridadDAOImpl();
        Prioridad prioridad = prioridadDAO.buscar(rs.getInt("idPrioridad"));
        
        PlantillaOferta plantilla = new PlantillaOferta();
        plantilla.setIdPlantilla(rs.getInt("idPlantillaOferta"));
        plantilla.setNombre(rs.getString("nombre"));
        plantilla.setFormula(rs.getString("formula")); // Asumiendo mapeo
        plantilla.setPeriodoValidez(new Date(rs.getTimestamp("fechaValidezHasta").getTime()));
        plantilla.setActiva(rs.getBoolean("activa"));
        plantilla.setTipoServicio(rs.getInt("tipoServicio"));
        plantilla.setActiva(rs.getBoolean("activa"));
        plantilla.setPrioridad(prioridad);
        // Los campos tipoServicio, prioridad, modalidades y parametros no están en el RS
        return plantilla;
    }
    
    @Override
    public List<Oferta> generarOfertas(Prioridad prioridadCliente, Linea linea) {
        //listar las plantillas:
        List<PlantillaOferta> platillas = listarPorPrioridad(prioridadCliente.getIdPrioridad(),linea.getTipoLinea());
        //segun las 4 plantillas listadas se convierten el ofertas y se hace return
        return convertirPlantillaAOferta(platillas, prioridadCliente, linea);
    }

    public List<PlantillaOferta> listarPorPrioridad(int idPrioridad, String tipoLinea) {
        List<PlantillaOferta> lista = new ArrayList<>();
        List<PlantillaOferta> randomPlantilla = new ArrayList<>();
        try (
                Connection conn = DBManager.getInstance().getConnection(); CallableStatement cmd = conn.prepareCall("{CALL SP_ListarPlantillasOfertaPorPrioridadModalidad(?,?)}");) {
            cmd.setInt(1, idPrioridad);
            cmd.setString(2, tipoLinea);
            ResultSet rs = cmd.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.err.println("No se encontraron platillas con el id: " + idPrioridad);
                return lista;
            }
            while (rs.next()) {
                PlantillaOferta plantilla = mapearModelo1(rs);
                lista.add(plantilla);
            }
            // Si hay 4 o menos plantillas, devolver todas
            if (lista.size() <= 4) {
                return lista;
            } else {
                // Generar 4 índices aleatorios únicos y copiar directamente
                Random random = new Random();
                // HashSet para evitar índices duplicados
                Set<Integer> indicesUsados = new HashSet<>();
                // Generar 4 índices únicos y copiar plantillas directamente
                while (randomPlantilla.size() < 4) {
                    // Generar índice aleatorio dentro del rango de la lista
                    int indiceAleatorio = random.nextInt(lista.size());
                    // Si el índice es nuevo (no duplicado), agregar la plantilla
                    if (indicesUsados.add(indiceAleatorio)) {
                        randomPlantilla.add(lista.get(indiceAleatorio));
                    }
                }
            }
            return randomPlantilla;
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            throw new RuntimeException("Error inesperado al listar plantillas por prioridad.", e);
        }

    }
//mapearModelo1 es necesario para poder asignar los parametros a cada plantilla 
//(en el metodo por defecto no se asignan los parametros)
    protected PlantillaOferta mapearModelo1(ResultSet rs) throws SQLException {
        PlantillaOferta plantilla = new PlantillaOferta();
        plantilla.setIdPlantilla(rs.getInt("idPlantillaOferta"));
        plantilla.setNombre(rs.getString("nombre"));
        plantilla.setFormula(rs.getString("formula")); // Asumiendo mapeo
        plantilla.setPeriodoValidez(new Date(rs.getTimestamp("fechaValidezHasta").getTime()));
        plantilla.setActiva(rs.getBoolean("activa"));
        plantilla.setTipoServicio(rs.getInt("tipoServicio"));
        plantilla.setActiva(rs.getBoolean("activa"));
        //obtiene los parámetros y los asigna a la plantilla:
        List<Parametro> parametros = listaParametros(plantilla.getIdPlantilla());
        plantilla.setParametros(parametros);

        // Los campos tipoServicio, prioridad, modalidades y parametros no están en el RS
        return plantilla;
    }
    //este método lo que hace es encontrar los parametros de la plantilla y devolverlos en una lista
    public List<Parametro> listaParametros(int idPlantilla) {
        //debido a que no hay una relacion directa entre metodos y plantillas, sino a traves de na tabla intermedia
        //llamda PlantillaOferta_Parametros, primero se sacan los indices de los parametros asociados a esa plantilla
        IParametroDAO parametro = new ParametroDAOImpl();
        IPlantillaOfertaParametroDAO platillaParametro = new PlantillaOfertaParametroDAOImpl();
        List<PlantillaOfertaParametro> listaPlantParam;
        //este metdodo lista todos los plantillaOferta_Parametro que tienen el id de la plantilla
        listaPlantParam = platillaParametro.listarPlantillaOfertaParametros(idPlantilla);
        //en esta lista de int se guardan los indices de los parametros 
        List<Integer> indices = new ArrayList<>();
        //se obtienen los indices de los parametros en esta lista
        for (PlantillaOfertaParametro p : listaPlantParam) {
            indices.add(p.getIdParametro());
        }
        //este metodo recibe la lista de indices de los parametros y devuelve una lista de parametros de acuerdo a esos indices
        List<Parametro> listaParam = parametro.listarParametros(indices);
        return listaParam;
    }
//*********************************************************************************************************************************
    //Este método lo que hace es convertir las plantillas a ofertas, convierte el texto  de la plantilla en la descripcion de la oferta
    //cambiando los campos enntre llaves (...{<escuento}... --> ..."30% de descuento"...) por el valor del parámetro
    //si una plantilla incluye un equipo o un descuento, este se asigna a la oferta, si no simplemente se inserta un equipo null o un 
    //descuento de 0.0.
    
    public List<Oferta> convertirPlantillaAOferta(List<PlantillaOferta> plantillas, Prioridad prioridadCliente, Linea linea) {
        List<Oferta> ofertas = new ArrayList<>();
        //verificar que si hayan plantillas
        if (plantillas != null && !plantillas.isEmpty()) {
            for (PlantillaOferta plantilla : plantillas) {
                //inicializacion para insertar los valores correctos en caso no haya equipo o descuento
                Equipo nuevoEquipo = null;
                double descuento = 0.0;
                
                //si tiene un parámetro que es equipo se tiene que adjuntar el equipo en cuestio, si no no
                //gestion de los equipos (sin optimizar)
                Equipo eqp = tieneEquipo(plantilla.getParametros(),plantilla);
                if (eqp != null) {
                    nuevoEquipo = eqp;
                }
                //si la plantilla tiene un parámetro descuento lo asigna a la variable descuento
                //gestion de los descuentos (sin optimizar)
                
                double desc = tieneDescuento(plantilla.getParametros());
                if (desc != -1) {
                    descuento = desc;
                }
                //este metodo completa el texto de la plantilla, convirtiendolo en la descripcion de la oferta
                String textoCompleto = completaPlantilla(plantilla.getFormula(), plantilla.getParametros());
                //genera Oferta
                Oferta nuevaOferta = new Oferta(
                        plantilla.getIdPlantilla(), // idPlantilla
                        nuevoEquipo, // equipo asigna random de la tabla equipo, solo si la platilla lo requiere
                        plantilla.getNombre(), // nombre
                        textoCompleto, //(descripcion)
                        descuento, //(FALTA CALCULAR DESCUENTO)
                        new Date(System.currentTimeMillis()), // fechaInicio (fecha actual)
                        plantilla.getPeriodoValidez(), // fechaFin (periodo de validez de la plantilla)
                        new Date(System.currentTimeMillis()), // fechaCreacion (fecha actual)
                        linea.getTipoLinea()
                );
                //agregar tipoServicio, prioridad, modalidades y parametros a cada oferta que se crea
                
                //agregar la oferta generada a la lista de ofertas
                ofertas.add(nuevaOferta);
            }
            
        }
        //devolver la lista de ofertas
        return ofertas;
    }
//**********************************************************************************************************************************
    public double tieneDescuento(List<Parametro> listaParam) {
        
        String descuentoString = reemplazaPalabra("Descuento", listaParam);
        if (descuentoString == null || descuentoString.trim().isEmpty()) {
            return -1.0;
        }
        Pattern patron = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = patron.matcher(descuentoString);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }
        return -1.0;
    }


    public Equipo tieneEquipo(List<Parametro> listaParam,PlantillaOferta plantilla) {
        //se asume que si un parámetro es equipo, en el nombre parametro se tiene "celular"
        //pero en la tabla plantillaOferta_Parametro no se tiene nada en valorParámetro, 
        //pues el equipo será asignado de forma aleatoria desde la tabla Equipo
        //en esta funcion es donde se le da el valor a valorParametro con el nombre del equipo asignado
        IEquipoDAO equipoDAO = new EquipoDAOImpl();
        String nombreCelular = "Celular";
        for (Parametro p : listaParam) {
            //si encuentra al parametro con nombre "Celular":
            if (nombreCelular.compareTo(p.getNombreParametro()) == 0) {
                //Genera una lista de Equipos
                List<Equipo>listaEquipos = equipoDAO.listar();
                Random random = new Random();
                int indiceAleatorio = random.nextInt(listaEquipos.size());
                //obtiene un equipo random de la lista de Equipos
                Equipo equipoSeleccionado = listaEquipos.get(indiceAleatorio);
                //genera la descripcion del equipo. Ejem: descripcion --> "HUAWEI Redmi 8"
                String descripcion = equipoSeleccionado.getMarca() + " " + equipoSeleccionado.getModelo();
                //obtiene la formula de la plantilla para moificarla
                String nuevoTexto = plantilla.getFormula();
                //******************************************************************************************
                //reemplaza {Celular} por descripcion creada.
                //si bien es cierto que despues se actualiza la formula de la plantilla, 
                //es necesario que este campo se actualice acá porque el 
                //VALOR DEL PARAMETRO(que se encuentra en la tabla PlantillaOferta_Parametro)
                //no tiene ningun valor puesto que la asignacion de los equipos cambia cada ve que se elije la plantilla actual
                //******************************************************************************************
                //reemplaza {Celular} por descripcion creada.
                nuevoTexto = nuevoTexto.replace("{Celular}", descripcion);
                //se cambia la formula de la plantilla
                plantilla.setFormula(nuevoTexto);
                //se devuelve el equipo seleccionado aleatoriamente
                return equipoSeleccionado;
            }
        }
        return null;
    }

    public String completaPlantilla(String base, List<Parametro> listaParam) {
        Pattern patron = Pattern.compile("\\{(.*?)\\}");
        Matcher buscador = patron.matcher(base);
        StringBuffer resultado = new StringBuffer();

        while (buscador.find()) {
            String palabraOriginal = buscador.group(1);
            String nuevaPalabra = reemplazaPalabra(palabraOriginal, listaParam);
            // appendReplacement reemplaza automáticamente y mantiene la posición
            buscador.appendReplacement(resultado, nuevaPalabra);
        }
        // Agregar el resto del texto
        buscador.appendTail(resultado);

        return resultado.toString();
    }

    public String reemplazaPalabra(String palabraOriginal, List<Parametro> listaParam) {
        IPlantillaOfertaParametroDAO plantillaOfertaParametro = new PlantillaOfertaParametroDAOImpl();

        for (Parametro p : listaParam) {
            if (palabraOriginal.compareTo(p.getNombreParametro()) == 0) {
                return plantillaOfertaParametro.obtenerValorParametro(palabraOriginal);
            }
        }
        return null;
    }
}
