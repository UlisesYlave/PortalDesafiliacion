using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PortalDesafiliacionWeb.Desafiliaciones
{
    public partial class FlujoDesafiliaciones : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Verificación reforzada
                if (Session["UsuarioActual"] == null || Request.Cookies["ASP.NET_SessionId"] == null)
                {
                    Response.Redirect("~/Login.aspx?invalid=attempt");
                }

                // Token adicional de seguridad
                if (ViewState["SessionToken"] == null)
                {
                    ViewState["SessionToken"] = Guid.NewGuid().ToString();
                    Session["SessionToken"] = ViewState["SessionToken"];
                }
                else if (Session["SessionToken"] == null || Session["SessionToken"].ToString() != ViewState["SessionToken"].ToString())
                {
                    Session.Abandon();
                    Response.Redirect("~/Login.aspx?token=mismatch");
                }
            }
        }
    }
}