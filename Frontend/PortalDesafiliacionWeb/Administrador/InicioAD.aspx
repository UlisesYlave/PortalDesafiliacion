<%@ Page Title="" Language="C#" MasterPageFile="~/Administrador/Admin.Master" AutoEventWireup="true" CodeBehind="InicioAD.aspx.cs" Inherits="PortalDesafiliacionWeb.Administrador.InicioAD" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <div class="container mt-4">
    <h4 class="mb-4">Mi Perfil</h4>

    <div class="card p-4 shadow-sm">
        <div class="mb-2">
            <strong>Código de administrador:</strong> 
            <asp:Label ID="lblCodigoAdmin" runat="server" />
        </div>
        <div class="mb-2">
            <strong>Nombre:</strong> 
            <asp:Label ID="lblNombre" runat="server" />
        </div>
        <div class="mb-2">
            <strong>Tipo de documento identidad:</strong> 
            <asp:Label ID="lblTipoDocumento" runat="server"/>
        </div>
        <div class="mb-2">
            <strong>Número de documento:</strong> 
            <asp:Label ID="lblNumeroDocumento" runat="server" />
        </div>
    </div>
</div>
</asp:Content>