using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PortalDesafiliacionWeb
{
    public partial class MainLayout : System.Web.UI.MasterPage
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