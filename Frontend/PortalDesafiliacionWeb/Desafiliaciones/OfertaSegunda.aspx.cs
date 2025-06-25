using System;
using System.Collections.Generic;
using System.Web.UI;

namespace PortalDesafiliacionWeb.Desafiliaciones
{
    public partial class OfertaSegunda : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            

            if (!IsPostBack)
            {
                rptOfertas.DataSource = new List<Oferta>
                {
                    new Oferta { Id = 1, Equipo = "iPhone 16", TipoBeneficio = "Beneficio", Beneficio = "6 meses ilimitados" },
                    new Oferta { Id = 2, Equipo = "Samsung S24", TipoBeneficio = "Descuento", Beneficio = "20% descuento" },
                    new Oferta { Id = 3, Equipo = "Motorola G90", TipoBeneficio = "Beneficio", Beneficio = "Cambio inmediato" },
                    new Oferta { Id = 4, Equipo = "Xiaomi Pro", TipoBeneficio = "Beneficio", Beneficio = "Migración de plan" }
                };
                rptOfertas.DataBind();

                if (Session["idLinea"] != null)
                {
                    int idLinea = (int)Session["idLinea"];
                    var client = new ServicioWS.SolicitudDesafiliacionWSClient();
                    client.enviarCodigoSMS(idLinea);

                }
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Desafiliaciones/OfertaPrimera.aspx");
        }
        protected void btnEnviar_Click(object sender, EventArgs e)
        {
            if (Session["idLinea"] == null)
            {
                lblError.Text = "No se pudo identificar la línea.";
                lblError.Visible = true;
                return;
            }

            int idLinea = (int)Session["idLinea"];
            string codigoIngresado = txtCodigo.Text.Trim();

            try
            {
                var client = new ServicioWS.SolicitudDesafiliacionWSClient();
                bool valido = client.validarCodigoSMS(idLinea, codigoIngresado);

                if (valido)
                {
                    // ✅ Código correcto → actualizar resultado
                    client.actualizarResultadoDesafiliacion(idLinea);
                    Response.Redirect("~/Desafiliaciones/OfertaResultado.aspx");
                }
                else
                {
                    lblError.Text = "Código incorrecto.";
                    lblError.Visible = true;

                    // Volver a mostrar el popup
                    ScriptManager.RegisterStartupScript(this, GetType(), "mostrarModalCodigo",
                        "document.getElementById('modalCodigo').classList.add('d-flex'); document.getElementById('modalCodigo').style.display = 'flex';", true);
                }
            }
            catch (Exception ex)
            {
                lblError.Text = "Ocurrió un error al validar el código.";
                lblError.Visible = true;
                Console.WriteLine("ERROR: " + ex.Message);
            }
        }

        public class Oferta
        {
            public int Id { get; set; }
            public string Equipo { get; set; }
            public string TipoBeneficio { get; set; }
            public string Beneficio { get; set; }
        }
    }
}
