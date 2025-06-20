using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PortalDesafiliacionWeb.Administrador
{
    public partial class InicioAD : System.Web.UI.Page
    {
        private usuario usuarioAD;
        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioAD = (usuario)Session["UsuarioActual"];
            lblCodigoAdmin.Text = usuarioAD.idUsuario.ToString();
            lblNombre.Text = usuarioAD.primerNombre;
            lblTipoDocumento.Text = usuarioAD.tipoDocumento;
            lblNumeroDocumento.Text = usuarioAD.numeroDocumento;
        }

    }
}