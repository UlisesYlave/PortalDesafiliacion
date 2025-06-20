<%@ Page Title="" Language="C#" MasterPageFile="~/Desafiliaciones/FlujoDesafiliaciones.Master" AutoEventWireup="true" CodeBehind="OfertaSegunda.aspx.cs" Inherits="PortalDesafiliacionWeb.Desafiliaciones.OfertaSegunda" %>

<asp:Content ID="headContent" ContentPlaceHolderID="head" runat="server">
    <style>
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.4);
            backdrop-filter: blur(4px);
            z-index: 9999;
            display: none;
            justify-content: center;
            align-items: center;
        }

        .popup {
            background-color: #fff;
            padding: 30px;
            border-radius: 20px;
            width: 360px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0,0,0,0.2);
        }

            .popup h5 {
                margin-bottom: 20px;
                font-weight: bold;
            }

            .popup .btn {
                margin: 5px;
            }

        .error-label {
            color: red;
            margin-top: 10px;
        }
    </style>
</asp:Content>

<asp:Content ID="bodyContent" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <asp:ScriptManager ID="ScriptManager1" runat="server" />
    <div class="container mt-4">
        <h4 class="text-center fw-bold mb-4">Quizás esto podría cambiar su opinión</h4>

        <div class="row row-cols-1 row-cols-md-4 g-4">
            <asp:Repeater ID="rptOfertas" runat="server">
                <ItemTemplate>
                    <div class="col">
                        <div class="oferta-card text-center p-3 shadow rounded">
                            <p><strong>Equipo:</strong> <%# Eval("Equipo") %></p>
                            <p><strong><%# Eval("TipoBeneficio") %>:</strong> <%# Eval("Beneficio") %></p>
                            <asp:Button ID="btnObtener" runat="server" Text="Obtener" CssClass="btn btn-danger rounded-pill px-4" />
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>

        <div class="d-flex justify-content-between mt-5">
            <asp:Button ID="btnRegresar" runat="server" Text="Regresar"
                CssClass="btn btn-secondary px-4 rounded-pill" OnClick="btnRegresar_Click" />
            <asp:Button ID="btnContinuar" runat="server" Text="Continuar"
                CssClass="btn btn-danger px-4 rounded-pill"
                OnClientClick="mostrarConfirmacion(); return false;" />
        </div>
    </div>

    <!-- CONFIRMACIÓN -->
    <div id="modalConfirmacion" class="overlay">
        <div class="popup">
            <h5>¿Está seguro que desea desafiliarse?</h5>
            <button type="button" class="btn btn-danger rounded-pill" onclick="mostrarCodigo()">Sí</button>
            <button type="button" class="btn btn-secondary rounded-pill" onclick="cerrarTodo()">No</button>
        </div>
    </div>

    <!-- CÓDIGO -->
    <div id="modalCodigo" class="overlay">
        <div class="popup">
            <h5>Ingrese el código recibido por SMS</h5>
            <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control mt-3" />
            <asp:Label ID="lblError" runat="server" CssClass="error-label" Visible="false" />
            <asp:Label ID="lblCodigoGenerado" runat="server" CssClass="text-muted small d-block mt-2" Visible="false" />

            <div class="d-flex justify-content-center mt-3">
                <button type="button" class="btn btn-secondary rounded-pill" onclick="cerrarTodo()">Cancelar</button>
                <asp:Button ID="btnEnviar" runat="server" Text="Enviar"
                    CssClass="btn btn-danger rounded-pill ms-2" OnClick="btnEnviar_Click" />
            </div>
        </div>
    </div>

    <script>
        function mostrarConfirmacion() {
            const modal = document.getElementById("modalConfirmacion");
            modal.classList.add("d-flex");
            modal.style.display = "flex";
        }

        function mostrarCodigo() {
            const confirmModal = document.getElementById("modalConfirmacion");
            const codigoModal = document.getElementById("modalCodigo");

            confirmModal.classList.remove("d-flex");
            confirmModal.style.display = "none";

            codigoModal.classList.add("d-flex");
            codigoModal.style.display = "flex";
        }

        function cerrarTodo() {
            const confirmModal = document.getElementById("modalConfirmacion");
            const codigoModal = document.getElementById("modalCodigo");

            confirmModal.classList.remove("d-flex");
            codigoModal.classList.remove("d-flex");

            confirmModal.style.display = "none";
            codigoModal.style.display = "none";
        }
    </script>
</asp:Content>
