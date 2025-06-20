<%@ Page Title="" Language="C#" MasterPageFile="~/Desafiliaciones/FlujoDesafiliaciones.Master" AutoEventWireup="true" CodeBehind="OfertaResultado.aspx.cs" Inherits="PortalDesafiliacionWeb.Desafiliaciones.OfertaResultado" %>

<asp:Content ID="headContent" ContentPlaceHolderID="head" runat="server">
    <style>
        .overlay {
            position: fixed;
            top: 0; left: 0; right: 0; bottom: 0;
            width: 100vw;
            height: 100vh;
            background: rgba(0,0,0,0.4);
            display: none;
            justify-content: center;
            align-items: center;
            z-index: 2000;
            backdrop-filter: blur(4px); /* Desenfoque del fondo */
        }

        .overlay.d-flex {
            display: flex !important;
        }

        .popup {
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(0,0,0,0.18);
            padding: 32px 24px;
            text-align: center;
            min-width: 320px;
            max-width: 90vw;
        }
        .result-box {
            max-width: 700px;
            height: 500px; 
            margin: 40px auto;
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }
        .star-rating {
            display: flex;
            justify-content: center;
            gap: 10px;
            font-size: 28px;
            cursor: pointer;
        }
        .star {
            color: #ccc;
            transition: color 0.2s;
        }
        .star.filled {
            color: #f1c40f;
        }
        .comment-box {
            width: 100%;
            height: 100px;
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 10px;
            margin-top: 20px;
        }
        .btn-submit {
            margin-top: 30px;
            float: right;
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 10px 30px;
            border-radius: 20px;
            font-weight: bold;
        }
        html, body {
            margin: 0;
            padding: 0;
            overflow: hidden; 
            height: 100vh;
        }
    </style>
</asp:Content>

<asp:Content ID="bodyContent" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="result-box">
        <h2 class="text-center fw-bold">Te has desafiliado exitosamente!</h2>
        <div class="mt-4">
            <h5 class="fw-semibold">Ayúdanos completando esta encuesta</h5>
            <p><strong>¿Cómo calificarías nuestro servicio del 1 al 10?</strong></p>
            <div id="rating" class="star-rating">
                <!-- Genera las estrellas con JavaScript para evitar errores de compilación -->
            </div>
            <asp:HiddenField ID="hdnRating" runat="server" />
            <label class="mt-4">Déjanos algún comentario de mejora (Opcional)</label>
            <asp:TextBox ID="txtComentario" runat="server" CssClass="comment-box" TextMode="MultiLine" />
            <asp:Button ID="btnEnviar" runat="server" Text="Enviar" CssClass="btn btn-submit" OnClick="btnEnviar_Click" />
        </div>
    </div>
    <div id="modalConstancia" class="overlay" style="display:none;justify-content:center;align-items:center;">
        <div class="popup" style="width:350px;">
            <h5>Descargue su constancia de desafiliación</h5>
            <img src='<%= ResolveUrl("~/Content/descargar.png") %>' alt="Descargar" style="width:64px;height:64px;margin:20px 0;" />
            <br />
            <asp:Button ID="btnDescargarConstancia" runat="server"
                Text="Descargar constancia"
                CssClass="btn btn-outline-primary rounded-pill mb-3"
                OnClick="btnDescargarConstancia_Click" />
            <div class="d-flex justify-content-center">
                <button type="button" class="btn btn-danger rounded-pill" onclick="volverInicio()">Volver al inicio</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        // Generar estrellas dinámicamente para evitar problemas con el for de ASP.NET
        document.addEventListener('DOMContentLoaded', function () {
            var ratingDiv = document.getElementById('rating');
            for (var i = 1; i <= 10; i++) {
                var span = document.createElement('span');
                span.className = 'star';
                span.setAttribute('data-value', i);
                span.innerHTML = '&#9733;';
                ratingDiv.appendChild(span);
            }

            var stars = document.querySelectorAll('.star');
            var selectedRating = 0;
            stars.forEach(function (star) {
                star.addEventListener('mouseover', function () {
                    var val = parseInt(this.getAttribute('data-value'));
                    highlightStars(val);
                });
                star.addEventListener('mouseout', function () {
                    highlightStars(selectedRating);
                });
                star.addEventListener('click', function () {
                    selectedRating = parseInt(this.getAttribute('data-value'));
                    document.getElementById('<%= hdnRating.ClientID %>').value = selectedRating;
                    highlightStars(selectedRating);
                });
            });

            function highlightStars(limit) {
                stars.forEach(function (star) {
                    var val = parseInt(star.getAttribute('data-value'));
                    if (val <= limit) {
                        star.classList.add('filled');
                    } else {
                        star.classList.remove('filled');
                    }
                });
            }
        });

        function mostrarConstancia() {
            var modal = document.getElementById("modalConstancia");
            modal.classList.add("d-flex");
            modal.style.display = "flex";
        }
        function volverInicio() {
            window.location.href = '<%= ResolveUrl("~/Cliente/InicioCli.aspx") %>';
        }
    </script>
</asp:Content>