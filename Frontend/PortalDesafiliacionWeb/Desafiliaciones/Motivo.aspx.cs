using System;
using System.Security.Cryptography;
using System.Web;
using PortalDesafiliacionWeb.ServicioWS;

namespace PortalDesafiliacionWeb.Desafiliaciones
{
    public partial class Motivo : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string idLinea = Request.QueryString["idLinea"];
                if (!string.IsNullOrEmpty(idLinea))
                {
                    Session["LineaActual"] = idLinea;
                }
            }
        }
        protected void rbOtro_CheckedChanged(object sender, EventArgs e)
        {
            txtOtroMotivo.Visible = rbOtro.Checked;
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Cliente/Lineas.aspx");
        }

        protected void btnContinuar_Click(object sender, EventArgs e)
        {
            string motivo = "";

            if (rb1.Checked) motivo = "Cobertura deficiente";
            else if (rb2.Checked) motivo = "Velocidad del servicio es lenta";
            else if (rb3.Checked) motivo = "Atención al cliente deficiente";
            else if (rbOtro.Checked) motivo = txtOtroMotivo.Text;

            string idLinea = Request.QueryString["idLinea"];

            Response.Redirect($"~/Desafiliaciones/OfertaPrimera.aspx?idLinea={idLinea}&motivo={HttpUtility.UrlEncode(motivo)}");
            
            var usuarioCli = (usuario)Session["UsuarioActual"];


            ClienteWSClient clienteWS = new ClienteWSClient();
            bool califica = clienteWS.valibleParaRecompensas(usuarioCli.idUsuario);

            if (califica){
                Response.Redirect($"~/Desafiliaciones/OfertaPrimera.aspx?idLinea={idLinea}&motivo={HttpUtility.UrlEncode(motivo)}");
            }
            else{
                Response.Redirect($"~/Desafiliaciones/OfertaResultado.aspx?idLinea={idLinea}&motivo={HttpUtility.UrlEncode(motivo)}");
            }

            
        }
    }
}