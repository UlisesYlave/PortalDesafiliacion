using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PortalDesafiliacionWeb.Administrador
{
    public partial class Reporte : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnGenerar_Click(object sender, EventArgs e)
        {
            string tipo = ddlTipoReporte.SelectedValue;

            if (tipo == "BajasServicios")
            {
                string fi = txtFechaInicio.Text;  // yyyy-MM-dd
                string ff = txtFechaFin.Text;

                // Cambia aquí:
                // - 8080 por el puerto de GlassFish
                // - PortalReportes por el context path de tu WAR
                string javaBase = "http://localhost:8080/PortalReportes";
                string url = $"{javaBase}/ReporteBajas?fechaInicio={fi}&fechaFin={ff}";

                Response.Redirect(url);
                return;


            }

            // Si no es BajasServicios, entonces llenas el GridView:
            DataTable dt = new DataTable();
            switch (tipo)
            {
                case "EfectividadOfertas":
                    dt.Columns.Add("Col1", typeof(string));
                    dt.Columns.Add("Col2", typeof(string));
                    dt.Columns.Add("Col3", typeof(string));
                    dt.Rows.Add("Oferta 1", "Aceptadas", "15");
                    dt.Rows.Add("Oferta 2", "Rechazadas", "5");
                    gvReporte.Columns[0].HeaderText = "Oferta";
                    gvReporte.Columns[1].HeaderText = "Estado";
                    gvReporte.Columns[2].HeaderText = "Cantidad";
                    break;

                case "MotivosBaja":
                    dt.Columns.Add("Col1", typeof(string));
                    dt.Columns.Add("Col2", typeof(string));
                    dt.Columns.Add("Col3", typeof(string));
                    dt.Rows.Add("Motivo 1", "10", "Comentario 1");
                    dt.Rows.Add("Motivo 2", "7", "Comentario 2");
                    gvReporte.Columns[0].HeaderText = "Motivo";
                    gvReporte.Columns[1].HeaderText = "Cantidad";
                    gvReporte.Columns[2].HeaderText = "Observación";
                    break;

                default:
                    gvReporte.Visible = false;
                    return;
            }

            // Bind solo para esos dos casos:
            gvReporte.DataSource = dt;
            gvReporte.DataBind();
            gvReporte.Visible = true;
        }
    }
}