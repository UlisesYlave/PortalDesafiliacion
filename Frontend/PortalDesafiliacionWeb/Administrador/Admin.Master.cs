using PortalDesafiliacionWeb.ServicioWS;
using System;


namespace PortalDesafiliacionWeb.Administrador
{
    public partial class Admin : System.Web.UI.MasterPage
    {
        private usuario usuarioCli;
        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioCli = (usuario)Session["UsuarioActual"];
            lblnombreCompletoUsuario.Text = usuarioCli.primerNombre + " " + usuarioCli.apellidoPaterno;

        }
        protected void btnCerrarSesion_Click(object sender, EventArgs e)
        {

            Response.Redirect("~/Login.aspx");
        }
    }
}