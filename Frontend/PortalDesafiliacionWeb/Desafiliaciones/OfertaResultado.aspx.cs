using System;
using System.IO;
using System.Web.UI;
using iTextSharp.text.pdf;
using iTextSharp.text;
using iTextSharp.text.pdf.draw;
using PortalDesafiliacionWeb.ServicioWS;

namespace PortalDesafiliacionWeb.Desafiliaciones
{
    public partial class OfertaResultado : Page
    {
        protected string idLinea, nombreCliente;

        protected void Page_Load(object sender, EventArgs e)
        {
            // Puedes manejar validaciones o parámetros aquí si lo necesitas
            if (!IsPostBack)
            {
                // Recuperar ID línea desde query string
                string idLinea = Session["LineaActual"] as string ?? "N/D";

                // Recuperar usuario desde sesión
                var usuarioCli = (usuario)Session["UsuarioActual"];
                if (usuarioCli != null)
                    nombreCliente = $"{usuarioCli.primerNombre} {usuarioCli.apellidoPaterno}";
            }
        }

        protected void btnEnviar_Click(object sender, EventArgs e)
        {
            // Captura los datos
            string calificacion = Request.Form["calificacion"]; // viene del input radio
            string comentario = txtComentario.Text.Trim();

            // Aquí podrías guardar en BD o enviar a un servicio

            // Por ahora redirigimos a una página de agradecimiento (o puedes dejarlo aquí)
            ScriptManager.RegisterStartupScript(this, GetType(), "mostrarConstancia", "mostrarConstancia();", true);
        }
        protected void btnDescargarConstancia_Click(object sender, EventArgs e)
        {
            string idLinea = Session["LineaActual"] as string ?? "N/D";
            var usuarioCli = (usuario)Session["UsuarioActual"];
            string nombreCliente = usuarioCli != null
                ? $"{usuarioCli.primerNombre} {usuarioCli.apellidoPaterno}"
                : "N/D";

            string resultado = "DESAFILIADO";
            string fecha = DateTime.Now.ToString("yyyy-MM-dd HH:mm");

            using (MemoryStream ms = new MemoryStream())
            {
                Document doc = new Document(PageSize.A4, 50, 50, 80, 50);
                PdfWriter.GetInstance(doc, ms);
                doc.Open();

                // Fuente general
                Font fontTitulo = FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 18);
                Font fontTexto = FontFactory.GetFont(FontFactory.HELVETICA, 12);
                Font fontNegrita = FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12);
                Font fontValor = FontFactory.GetFont(FontFactory.HELVETICA, 12, BaseColor.DARK_GRAY);

                // Título
                Paragraph titulo = new Paragraph("CONSTANCIA DE DESAFILIACIÓN", fontTitulo)
                {
                    Alignment = Element.ALIGN_CENTER,
                    SpacingAfter = 20f
                };
                doc.Add(titulo);

                // Mensaje introductorio
                Paragraph intro = new Paragraph(
                    $"Se deja constancia que el usuario ha solicitado la desafiliación de su línea móvil bajo su consentimiento y conocimiento de las condiciones establecidas por la entidad proveedora del servicio.",
                    fontTexto
                );
                intro.Alignment = Element.ALIGN_JUSTIFIED;
                intro.SpacingAfter = 20f;
                doc.Add(intro);

                // Tabla de datos
                PdfPTable tabla = new PdfPTable(2);
                tabla.WidthPercentage = 85;
                tabla.SetWidths(new float[] { 1.5f, 3f });
                tabla.SpacingAfter = 30f;

                void AddCell(string label, string value)
                {
                    PdfPCell cell1 = new PdfPCell(new Phrase(label, fontNegrita));
                    cell1.BorderWidth = 1;
                    cell1.BackgroundColor = new BaseColor(235, 235, 235);
                    cell1.Padding = 6;

                    PdfPCell cell2 = new PdfPCell(new Phrase(value, fontValor));
                    cell2.BorderWidth = 1;
                    cell2.Padding = 6;

                    tabla.AddCell(cell1);
                    tabla.AddCell(cell2);
                }

                AddCell("ID Línea:", idLinea);
                AddCell("Cliente:", nombreCliente);
                AddCell("Resultado:", resultado);
                AddCell("Fecha:", fecha);

                doc.Add(tabla);

                // Firma visual
                Paragraph firma = new Paragraph("___________________________\nFirma del responsable", fontTexto)
                {
                    Alignment = Element.ALIGN_CENTER,
                    SpacingBefore = 40f,
                    SpacingAfter = 30f
                };
                doc.Add(firma);

                // Pie o validación legal
                Paragraph pie = new Paragraph(
                    "Este documento ha sido generado automáticamente por el sistema de gestión de líneas móviles.\n" +
                    "La constancia es válida sin necesidad de sello físico o firma manuscrita.",
                    FontFactory.GetFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY)
                );
                pie.Alignment = Element.ALIGN_CENTER;
                doc.Add(pie);

                doc.Close();

                byte[] pdfBytes = ms.ToArray();
                Response.Clear();
                Response.ContentType = "application/pdf";
                Response.AddHeader("Content-Disposition", "attachment; filename=ConstanciaDesafiliacion.pdf");
                Response.BinaryWrite(pdfBytes);
                Response.End();
            }
        }

    }
}