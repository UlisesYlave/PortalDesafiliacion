<%@ Page Title="" Language="C#" MasterPageFile="~/Administrador/Admin.Master" AutoEventWireup="true" CodeBehind="Reporte.aspx.cs" Inherits="PortalDesafiliacionWeb.Administrador.Reporte" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

      <style>
    .reporte-container {
        max-width: 700px;
        margin: 48px auto 0 auto;
        background: #fff;
        border: 1px solid #e0e0e0;
        border-radius: 12px;
        padding: 40px 36px 32px 36px;
        box-shadow: 0 4px 16px rgba(0,0,0,0.06);
        font-family: inherit;
    }
    .reporte-form-row {
        display: flex;
        align-items: flex-end;
        margin-bottom: 24px;
        gap: 24px;
    }
    .reporte-group {
        display: flex;
        flex-direction: column;
        flex: 1 1 0;
        min-width: 0;
    }
    .reporte-label {
        font-weight: 500;
        margin-bottom: 6px;
        min-width: 110px;
    }
    .reporte-input, .reporte-dropdown {
        min-width: 150px;
        max-width: 100%;
        padding: 7px 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 1em;
        box-sizing: border-box;
    }
    .reporte-btn {
        padding: 10px 32px;
        border: none;
        border-radius: 20px;
        font-size: 1em;
        font-weight: 600;
        color: #fff;
        background: #b71c1c;
        cursor: pointer;
        transition: background 0.2s;
        margin-top: 12px;
    }
    .reporte-btn:disabled {
        background: #ccc;
        cursor: not-allowed;
    }
    @media (max-width: 700px) {
        .reporte-container {
            padding: 18px 4vw;
        }
        .reporte-form-row {
            flex-direction: column;
            align-items: stretch;
            gap: 8px;
        }
        .reporte-group {
            width: 100%;
        }
    }
</style>

<div class="reporte-container">
    <div class="reporte-form-row">
        <div class="reporte-group">
            <label for="ddlTipoReporte" class="reporte-label">Tipo de Reporte:</label>
            <asp:DropDownList ID="ddlTipoReporte" runat="server" CssClass="reporte-input reporte-dropdown">
                <asp:ListItem Text="Seleccione" Value="" />
                <asp:ListItem Text="Bajas de Servicios" Value="BajasServicios" />
                <asp:ListItem Text="Efectividad de ofertas" Value="EfectividadOfertas" />
                <asp:ListItem Text="Motivos de baja" Value="MotivosBaja" />
            </asp:DropDownList>
        </div>
        <div class="reporte-group">
            <label for="txtFechaInicio" class="reporte-label">Fecha Inicio:</label>
            <asp:TextBox ID="txtFechaInicio" runat="server" CssClass="reporte-input" TextMode="Date" />
        </div>
        <div class="reporte-group">
            <label for="txtFechaFin" class="reporte-label">Fecha Fin:</label>
            <asp:TextBox ID="txtFechaFin" runat="server" CssClass="reporte-input" TextMode="Date" />
        </div>
    </div>
    <div class="reporte-form-row">
        <div class="reporte-group">
            <label for="ddlRegion" class="reporte-label">Región:</label>
            <asp:DropDownList ID="ddlRegion" runat="server" CssClass="reporte-input reporte-dropdown">
                <asp:ListItem Text="Arequipa" Value="Arequipa" />
                <asp:ListItem Text="Lima" Value="Lima" />
                <asp:ListItem Text="Cusco" Value="Cusco" />
            </asp:DropDownList>
        </div>
    </div>
    <asp:Button ID="btnGenerar" runat="server" Text="Generar Reporte" CssClass="reporte-btn" OnClick="btnGenerar_Click"/>

    <asp:GridView ID="gvReporte" runat="server" AutoGenerateColumns="false" CssClass="reporte-grid" Visible="false">
        <Columns>
            <asp:BoundField DataField="Col1" HeaderText="Columna 1" />
            <asp:BoundField DataField="Col2" HeaderText="Columna 2" />
            <asp:BoundField DataField="Col3" HeaderText="Columna 3" />
        </Columns>
    </asp:GridView>
    <style>
        .reporte-grid {
            margin-top: 32px;
            width: 100%;
            border-collapse: collapse;
            font-size: 1em;
        }
        .reporte-grid th, .reporte-grid td {
            border: 1px solid #ddd;
            padding: 10px 8px;
            text-align: left;
        }
        .reporte-grid th {
            background: #f5f5f5;
            font-weight: 600;
        }
    </style>

</div>
</asp:Content>
