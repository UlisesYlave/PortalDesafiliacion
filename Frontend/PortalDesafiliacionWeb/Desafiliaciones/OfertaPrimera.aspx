<%@ Page Title="" Language="C#" MasterPageFile="~/Desafiliaciones/FlujoDesafiliaciones.Master" AutoEventWireup="true" CodeBehind="OfertaPrimera.aspx.cs" Inherits="PortalDesafiliacionWeb.Desafiliaciones.OfertaPrimera" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        .titulo {
            font-size: 1.5rem;
            font-weight: bold;
            margin-bottom: 30px;
        }

        .container {
            max-width: 800px; /* Ajusta este valor según necesites */
            width: 100%;
            padding: 0 15px; /* Padding para dispositivos móviles */
        }

        .oferta-btn {
            background-color: #d32f2f;
            color: white;
            padding: 10px 20px;
            border-radius: 20px;
            font-weight: 500;
            border: none;
            margin-top: 15px;
        }

        .oferta-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr); /* Siempre 2 columnas */
            gap: 20px;
            width: 100%; /* Ocupa todo el ancho disponible */
        }


        .oferta-card {
            background: white;
            padding: 25px;
            border-radius: 20px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            text-align: center;
            margin-bottom: 20px;
            width: 100%; /* Ocupa todo el ancho de la celda del grid */
            box-sizing: border-box; /* Incluye padding en el ancho total */
        }

        .acciones {
            display: flex;
            justify-content: space-between;
            margin-top: 40px;
        }

        .custom-btn {
            padding: 10px 28px;
            border-radius: 20px;
            font-weight: 600;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
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
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="d-flex justify-content-center">
        <div class="container" class="container">
            <div class="motivo-box p-4 shadow-sm bg-white rounded-4">
                <div class="titulo text-center">Esto podría ser de su agrado</div>

                <div class="oferta-grid">
                    <asp:Repeater ID="rptOfertas" runat="server">
                        <ItemTemplate>
                            <div class="oferta-card">
                                <p><strong>Equipo:</strong> <%# Eval("Equipo") %></p>
                                <p><strong><%# Eval("TipoBeneficio") %>:</strong> <%# Eval("Beneficio") %></p>
                                <asp:Button ID="btnObtener" runat="server" Text="Obtener" CssClass="oferta-btn" CommandName="Obtener" CommandArgument='<%# Eval("Id") %>' />
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>

                <div class="d-flex justify-content-between mt-5 px-4">
                    <asp:Button ID="btnRegresar" runat="server" Text="Regresar"
                        CssClass="btn custom-btn custom-btn-secondary" OnClick="btnRegresar_Click" />

                    <asp:Button ID="btnContinuar" runat="server" Text="Continuar"
                        CssClass="btn custom-btn custom-btn-danger" OnClick="btnContinuar_Click_oferta" />
                </div>
            </div>
        </div>
    </div>
</asp:Content>
