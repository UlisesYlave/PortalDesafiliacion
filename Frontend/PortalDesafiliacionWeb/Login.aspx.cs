using PortalDesafiliacionWeb.ServicioWS;
using System;
using System.Web;
using System.Web.UI;

namespace PortalDesafiliacionWeb
{
    public partial class Login : System.Web.UI.Page
    {
        private UsuarioWSClient usuarioWS;
        protected void Page_Load(object sender, EventArgs e)
        {
            usuarioWS = new UsuarioWSClient();
            if (!IsPostBack)
            {
                // Asegurarse que no se cachee la página
                Response.Cache.SetCacheability(HttpCacheability.NoCache);
                Response.Cache.SetExpires(DateTime.UtcNow.AddMinutes(-1));
                Response.Cache.SetNoStore();

                // Limpiar parámetro de URL si existe
                if (Request.QueryString["nocache"] != null)
                {
                    Response.Redirect("Login.aspx");
                }
            }
        }
        
        protected void btnLogin_Click(object sender, EventArgs e)
        {
            try
            {
                var request = new verificarUsuarioRequest
                {
                    documentoOCorreo = txtUsuario.Text,
                    contrasena = txtClave.Text
                };

                var response = usuarioWS.verificarUsuario(request.documentoOCorreo, request.contrasena);

                if (response >= 0)
                {
                    usuario usuarioAD = usuarioWS.obtenerUsuarioPorCorreoODocumento(request.documentoOCorreo,request.contrasena);
                    Session["UsuarioActual"] = usuarioAD;
                    if (response == 0) Response.Redirect("~/Cliente/InicioCli.aspx?documentoOCorreo="+ request.documentoOCorreo + "&contrasena" + request.contrasena);
                    else Response.Redirect("~/Administrador/InicioAD.aspx?documentoOCorreo="+ request.documentoOCorreo + "&contrasena" + request.contrasena);
                } else
                {
                    lblMensaje.Text = "Credenciales Incorrectas";
                }
            }
            catch (Exception ex)
            {
                lblMensaje.Text = $"Error al verificar usuario: {ex.Message}";
            }
        }
    }
}