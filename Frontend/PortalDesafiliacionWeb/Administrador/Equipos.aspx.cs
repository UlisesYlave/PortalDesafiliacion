using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web.UI;

namespace PortalDesafiliacionWeb.Administrador
{
    public partial class Equipos : System.Web.UI.Page
    {
        private EquipoWSClient equipoWS;
        protected void Page_Load(object sender, EventArgs e)
        {
            equipoWS = new EquipoWSClient();
            if (!IsPostBack)
            {
                CargarEquipos();
            }
        }

        private void CargarEquipos()
        {
            List<equipo> equipos = equipoWS.listarEquipos()?.ToList();

            gvEquipos.DataSource = equipos;
            gvEquipos.DataBind();
        }

        protected void btnNuevoEquipo_Click(object sender, EventArgs e)
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
            CargarEquipos();

            // Ocultar modal
            ScriptManager.RegisterStartupScript(this, GetType(), "ocultarModal", "ocultarModal();", true);
        }

        protected void btnCancelar_Click(object sender, EventArgs e)
        {
            ScriptManager.RegisterStartupScript(this, GetType(), "ocultarModal", "ocultarModal();", true);
        }
    }
}