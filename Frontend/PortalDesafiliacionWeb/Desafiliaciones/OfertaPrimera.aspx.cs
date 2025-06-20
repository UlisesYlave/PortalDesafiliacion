using System;
using System.Collections.Generic;

namespace PortalDesafiliacionWeb.Desafiliaciones
{
    public partial class OfertaPrimera : System.Web.UI.Page
    {
        public class Oferta
        {
            public int Id { get; set; }
            public string Equipo { get; set; }
            public string TipoBeneficio { get; set; } // "Beneficio" o "Descuento"
            public string Beneficio { get; set; }
        }

        protected void Page_Load(object sender, EventArgs e)
        {

            if (!IsPostBack)
            {
                if (Request.QueryString["idLinea"] != null)
                {
                    int idLinea = int.Parse(Request.QueryString["idLinea"]);
                    Session["idLinea"] = idLinea;
                }

                List<Oferta> ofertas = new List<Oferta>
                {
                    new Oferta { Id = 1, Equipo = "iPhone 13", TipoBeneficio = "Beneficio", Beneficio = "6 meses gratis de datos" },
                    new Oferta { Id = 2, Equipo = "Samsung A54", TipoBeneficio = "Beneficio", Beneficio = "Accesorio gratis" },
                    new Oferta { Id = 3, Equipo = "Xiaomi Note 12", TipoBeneficio = "Descuento", Beneficio = "20% descuento" },
                    new Oferta { Id = 4, Equipo = "Motorola G60", TipoBeneficio = "Beneficio", Beneficio = "Cambio inmediato" }
                };

                rptOfertas.DataSource = ofertas;
                rptOfertas.DataBind();
            }
        }

        protected void btnRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Desafiliaciones/Motivo.aspx");
        }

        protected void btnContinuar_Click_oferta(object sender, EventArgs e)
        {
            Response.Redirect("~/Desafiliaciones/OfertaSegunda.aspx");
        }
    }
}