<%@ Page Title="" Language="C#" MasterPageFile="~/Desafiliaciones/FlujoDesafiliaciones.Master" AutoEventWireup="true" CodeBehind="Motivo.aspx.cs" Inherits="PortalDesafiliacionWeb.Desafiliaciones.Motivo" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        body {
            overflow: hidden !important;
        }

        .contenedor-motivo {
            height: calc(100vh - 60px);
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            padding-top: 30px;
        }

        .btn-back {
            align-self: flex-start;
            margin-left: 30px;
            font-weight: bold;
            text-decoration: none;
            color: black;
        }

        .motivo-box {
            max-width: 700px;
            width: 100%;
            background: #fff;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .motivo-title {
            background-color: #f5f5f5;
            border-radius: 12px;
            padding: 12px 16px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .option-tile {
            padding: 16px;
            border: 2px solid #ccc;
            border-radius: 12px;
            margin-bottom: 15px;
            cursor: pointer;
            transition: 0.3s;
            background-color: #f8f9fa;
        }

        .option-tile:hover {
            background-color: #e9ecef;
            border-color: #dc3545;
        }

        .option-tile input[type="radio"] {
            display: none;
        }

        .option-tile.selected {
            background-color: #ffe6e8;
            border-color: #dc3545;
        }

        .custom-btn {
            padding: 10px 28px;
            border-radius: 20px;
            font-weight: 600;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .custom-btn-danger {
            background-color: #dc3545;
            border: none;
            color: white;
        }

        .custom-btn-secondary {
            background-color: #6c757d;
            border: none;
            color: white;
        }
    </style>

    <script>
        function seleccionarMotivo(id) {
            document.querySelectorAll('.option-tile').forEach(tile => tile.classList.remove('selected'));
            document.getElementById(id).classList.add('selected');
        }
    </script>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <!-- Mis líneas alineado a la izquierda -->
    <div class="mb-3 px-4">
        <a href="javascript:history.back()" class="text-decoration-none text-dark fw-bold d-inline-block">
            &larr; Mis líneas
        </a>
    </div>

    <!-- Contenedor centrado para el formulario -->
    <div class="d-flex justify-content-center">
        <div class="container" style="max-width: 650px;">

            <div class="motivo-box p-4 shadow-sm bg-white rounded-4">
                <h4 class="motivo-title fs-5 mb-4 px-3 py-2 bg-light rounded-3">
                    <strong>Solicitud de desafiliación Línea <%= Request.QueryString["idLinea"] %></strong>
                </h4>

                <label class="fw-bold mb-3 d-block">Cuéntanos tu motivo de baja</label>

                <asp:RadioButton ID="rb1" runat="server" GroupName="motivo" CssClass="d-none" />
                <div id="tile1" class="option-tile small" onclick="document.getElementById('<%= rb1.ClientID %>').checked = true; seleccionarMotivo('tile1');">
                    Cobertura deficiente
                </div>

                <asp:RadioButton ID="rb2" runat="server" GroupName="motivo" CssClass="d-none" />
                <div id="tile2" class="option-tile small" onclick="document.getElementById('<%= rb2.ClientID %>').checked = true; seleccionarMotivo('tile2');">
                    Velocidad del servicio es lenta
                </div>

                <asp:RadioButton ID="rb3" runat="server" GroupName="motivo" CssClass="d-none" />
                <div id="tile3" class="option-tile small" onclick="document.getElementById('<%= rb3.ClientID %>').checked = true; seleccionarMotivo('tile3');">
                    Atención al cliente deficiente
                </div>

                <asp:RadioButton ID="rbOtro" runat="server" GroupName="motivo" CssClass="d-none" AutoPostBack="true" OnCheckedChanged="rbOtro_CheckedChanged" />
                <div id="tile4" class="option-tile small" onclick="document.getElementById('<%= rbOtro.ClientID %>').checked = true; seleccionarMotivo('tile4');">
                    Otros motivos
                </div>

                <asp:TextBox ID="txtOtroMotivo" runat="server" CssClass="form-control form-control-sm mt-2 mb-3" placeholder="Especifica tu motivo..." Visible="false" />

                <div class="d-flex justify-content-between mt-3">
                    <asp:Button ID="btnRegresar" runat="server" CssClass="btn custom-btn custom-btn-secondary px-4 py-2" Text="Regresar" OnClick="btnRegresar_Click" />
                    <asp:Button ID="btnContinuar" runat="server" CssClass="btn custom-btn custom-btn-danger px-4 py-2" Text="Continuar" OnClick="btnContinuar_Click" />
                </div>
            </div>

        </div>
    </div>
</asp:Content>