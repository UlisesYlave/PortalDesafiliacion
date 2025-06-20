using System.Collections.Generic;
using System;
using PortalDesafiliacionWeb.ServicioWS;
using System.Web.UI.WebControls;
using System.Linq;

namespace PortalDesafiliacionWeb
{
    public partial class Lineas : System.Web.UI.Page
    {
        private LineaWSClient lineaWS;
        private ClienteWSClient clienteWS;
        private PostpagoWSClient postpagoWS;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["UsuarioActual"] == null)
            {
                Response.Redirect("~/Login.aspx");
                return;
            }

            // Inicializar siempre los clientes de servicio
            lineaWS = new LineaWSClient();
            clienteWS = new ClienteWSClient();
            postpagoWS = new PostpagoWSClient();

            if (!IsPostBack)
            {
                CargarSolicitudes();
            }
        }

        private void CargarSolicitudes()
        {
            try
            {
                // 1. Obtener el cliente actual
                usuario usuarioCli = (usuario)Session["UsuarioActual"];

                // 2. Obtener las solicitudes del servicio Java
                List<linea> lineas = lineaWS.listarLineasPorCliente(usuarioCli.idUsuario)?.ToList();

                // 3. Enlazar datos al GridView
                if (lineas != null)
                {
                    gvSolicitudes.DataSource = lineas;
                    gvSolicitudes.DataBind();

                }
                else
                {
                    lblMensaje.Text = "No cuenta con lineas";
                    lblMensaje.CssClass = "solicitud-card";
                }
            }
            catch (Exception ex)
            {
                // Manejo de errores
                lblMensaje.Text = "Error al cargar las lineas: " + ex.Message;
                lblMensaje.CssClass = "alert alert-danger";
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

        protected void gvLineas_RowDataBound(object sender, GridViewRowEventArgs e)
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

        protected void btnDesafiliar_Click(object sender, EventArgs e)
        {
            try
            {
                LinkButton btn = (LinkButton)sender;
                string[] args = btn.CommandArgument.Split(',');

                if (args.Length < 2)
                {
                    lblMensaje.Text = "Datos incompletos para procesar la desafiliación";
                    lblMensaje.CssClass = "alert alert-danger";
                    return;
                }

                int idLinea = Convert.ToInt32(args[0]);
                string tipoLinea = args[1]?.ToString().ToLower() ?? "";

                hdnLineaDesafiliar.Value = idLinea.ToString();

                if (tipoLinea == "postpago")
                {
                    bool tieneDeuda = VerificarDeudaLinea(idLinea);
                    if (tieneDeuda)
                    {
                        pnlModalDeuda.Visible = true;
                        return;
                    }
                }

                IniciarDesafiliacion(idLinea,tipoLinea);
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al iniciar desafiliación: " + ex.Message;
                lblMensaje.CssClass = "alert alert-danger";
            }
        }

        private bool VerificarDeudaLinea(int idLinea)
        {
            double monto = postpagoWS.obtenerDeudaPendiente(idLinea);
            if (monto > 0) return true;
            else return false;
        }

        private void IniciarDesafiliacion(int idLinea, string tipoLinea)
        {
            Response.Redirect("~/Desafiliaciones/Motivo.aspx?idLinea="+idLinea+"&tipoLinea="+tipoLinea);
        }

        protected void btnAceptarModal_Click(object sender, EventArgs e)
        {
            pnlModalDeuda.Visible = false;
        }
    }

}