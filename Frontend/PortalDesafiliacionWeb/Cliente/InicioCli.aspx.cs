using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Web;

namespace PortalDesafiliacionWeb
{
    public partial class Inicio : System.Web.UI.Page
    {
        private usuario usuarioCli;
        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioCli = (usuario)Session["UsuarioActual"];
            lblCodigoUsuario.Text = usuarioCli.idUsuario.ToString();
            lblNombre.Text = usuarioCli.primerNombre;
            lblTipoDocumento.Text = usuarioCli.tipoDocumento;
            lblNumeroDocumento.Text = usuarioCli.numeroDocumento;
            lblCorreo.Text = usuarioCli.correo;
        }
    }
}