using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Data;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PortalDesafiliacionWeb.Administrador
{
    public partial class Plantilla : System.Web.UI.Page
    {
        private PlantillaOfertaWSClient plantillaWS;

        protected void Page_Load(object sender, EventArgs e)
        {
            plantillaWS = new PlantillaOfertaWSClient();
            if (!IsPostBack)
            {
                CargarPlantillas();
            }
        }

        // Método para obtener la clase CSS de prioridad
        protected string GetPriorityClass(object prioridadObj)
        {
            if (prioridadObj is prioridad prioridad)
            {
                string nivel = prioridad.nombre?.ToString() ?? "";

                switch (nivel.ToUpper())
                {
                    case "ALTA": return "priority-high";
                    case "MEDIA": return "priority-medium";
                    case "BAJA": return "priority-low";
                    default: return "";
                }
            }
            return "";
        }

        protected string GetPrioridadNombre(object prioridadObj)
        {
            if (prioridadObj is prioridad prioridad)
            {
                return prioridad.nombre?.ToString() ?? "Sin prioridad";
            }
            return "Sin prioridad";
        }

        // Método para obtener el nombre del tipo de servicio
        protected string GetTipoServicioNombre(int idTipoServicio)
        {
            switch (idTipoServicio)
            {
                case 1: return "Oferta Regular";
                case 2: return "Descuento en Plan";
                case 3: return "Paquete Promocional";
                case 4: return "Beneficio Adicional";
                case 5: return "Oferta de Equipo";
                default: return "Otro Tipo";
            }
        }

        // Ejemplo de método para cargar datos
        private void CargarPlantillas()
        {
            List<plantillaOferta> plantillas = plantillaWS.listarPlantillasOfertas()?.ToList();

            rptPlantillas.DataSource = plantillas;
            rptPlantillas.DataBind();
        }

        protected void rptPlantillas_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Editar")
            {
                int idPlantilla = Convert.ToInt32(e.CommandArgument);
                // Lógica para editar
            }
            else if (e.CommandName == "Eliminar")
            {
                int idPlantilla = Convert.ToInt32(e.CommandArgument);
                // Lógica para eliminar
            }
        }

        protected void btnNuevoPlantillaOferta_Click(object sender, EventArgs e)
        {
            // Limpiar controles del modal
            txtMarca.Text = string.Empty;
            txtModelo.Text = string.Empty;
            txtPrecio.Text = string.Empty;
            txtStock.Text = string.Empty;
            chkEstado.Checked = true;

            // Mostrar modal
            ScriptManager.RegisterStartupScript(this, GetType(), "mostrarModal", "mostrarModal();", true);
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            // Aquí iría tu lógica para guardar el equipo
            // Ejemplo:
            /*
            string marca = txtMarca.Text;
            string modelo = txtModelo.Text;
            decimal precio = decimal.Parse(txtPrecio.Text);
            int stock = int.Parse(txtStock.Text);
            bool estado = chkEstado.Checked;
            string categoria = ddlCategoria.SelectedValue;
            
            // Llamar a tu servicio o capa de datos para guardar
            */

            // Recargar la lista
            CargarPlantillas();

            // Ocultar modal
            ScriptManager.RegisterStartupScript(this, GetType(), "ocultarModal", "ocultarModal();", true);
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "ocultarModal", "ocultarModal();", true);
        }
    }
}