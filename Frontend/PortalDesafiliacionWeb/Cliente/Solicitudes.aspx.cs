using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PortalDesafiliacionWeb.Cliente
{
    public partial class Solicitudes : System.Web.UI.Page
    {
        private SolicitudDesafiliacionWSClient ObtenerServicio()
        {
            var servicio = new SolicitudDesafiliacionWSClient();
            // Configurar timeout (opcional pero recomendado)
            servicio.InnerChannel.OperationTimeout = TimeSpan.FromMinutes(3);
            return servicio;
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (Session["UsuarioActual"] == null)
                {
                    Response.Redirect("~/Login.aspx");
                    return;
                }
                CargarSolicitudes();
            }

        }

        private void CargarSolicitudes()
        {
            using (var servicio = ObtenerServicio())
            {
                try
                {
                    usuario usuarioCli = (usuario)Session["UsuarioActual"];
                    var solicitudes = servicio.listarSolicitudesPorCliente(usuarioCli.idUsuario);

                    gvSolicitudes.DataSource = solicitudes ?? Array.Empty<solicitudDesafiliacion>();
                    gvSolicitudes.DataBind();

                    if (solicitudes == null || solicitudes.Length == 0)
                    {
                        lblMensaje.Text = "No cuenta con solicitudes";
                        lblMensaje.CssClass = "alert alert-info";
                    }
                }
                catch (Exception ex)
                {
                    lblMensaje.Text = "Error al cargar solicitudes: " + ex.Message;
                    lblMensaje.CssClass = "alert alert-danger";
                }
            }
        }

        // Método para determinar la clase CSS del badge según el estado
        public string GetBadgeClass(string estado)
        {
            if (string.IsNullOrEmpty(estado)) return "";

            switch (estado.ToLower())
            {
                case "procesada": return "badge-procesada";
                case "aprobada": return "badge-procesada";
                case "pendiente": return "badge-pendiente";
                case "rechazada": return "badge-rechazada";
                case "cancelada": return "badge-rechazada";
                default: return "";
            }
        }

        protected void gvSolicitudes_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                // Puedes manipular los controles aquí si es necesario
                var lblEstado = e.Row.FindControl("lblEstado") as Label;
                if (lblEstado != null)
                {
                    lblEstado.CssClass = GetBadgeClass(lblEstado.Text);
                }
            }
        }

        // Método para formatear la fecha
        public string FormatearFecha(DateTime? fecha)
        {
            return fecha?.ToString("dd/MM/yyyy HH:mm") ?? "N/A";
        }

        protected void MostrarMensaje(string titulo, string mensaje, bool esExito)
        {
            // Registrar script para mostrar el modal
            string script = $"mostrarMensaje('{titulo}', '{mensaje}', {(esExito ? "true" : "false")});";
            ScriptManager.RegisterStartupScript(this, GetType(), "MostrarModal", script, true);

            // Opcional: También mostrar en el label como respaldo
            lblMensaje.Text = mensaje;
            lblMensaje.CssClass = esExito ? "alert alert-success" : "alert alert-danger";
            lblMensaje.Visible = true;
        }

        protected void btnExportarExcel_Click(object sender, EventArgs e)
        {
            using (var servicio = ObtenerServicio())
            {
                try
                {
                    if (Session["UsuarioActual"] == null)
                    {
                        Response.Redirect("~/Login.aspx");
                        return;
                    }

                    usuario usuarioActual = (usuario)Session["UsuarioActual"];

                    bool success = servicio.exportToExcelPorCliente(usuarioActual.idUsuario);
                    if (success)
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowToast",
                            "MostrarMensaje('Éxito', 'Archivo Excel generado correctamente', true);", true);
                    }
                    else
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowError",
                            "MostrarMensaje('Error', 'No se pudo generar el archivo', false);", true);
                    }
                }
                catch (Exception ex)
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "ShowError",
                        $"MostrarMensaje('Error', '{ex.Message.Replace("'", "\\'")}', false);", true);
                }
            }
        }

        protected void btnExportarCsv_Click(object sender, EventArgs e)
        {
            using (var servicio = ObtenerServicio())
            {
                try
                {
                    if (Session["UsuarioActual"] == null)
                    {
                        Response.Redirect("~/Login.aspx");
                        return;
                    }

                    usuario usuarioActual = (usuario)Session["UsuarioActual"];

                    bool success = servicio.exportToCsvPorCliente(usuarioActual.idUsuario);
                    if (success)
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowToast",
                            "MostrarMensaje('Éxito', 'Archivo CSV generado correctamente', true);", true);
                    }
                    else
                    {
                        ScriptManager.RegisterStartupScript(this, GetType(), "ShowError",
                            "MostrarMensaje('Error', 'No se pudo generar el archivo', false);", true);
                    }
                }
                catch (Exception ex)
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "ShowError",
                        $"MostrarMensaje('Error', '{ex.Message.Replace("'", "\\'")}', false);", true);
                }
            }
        }
    }
}