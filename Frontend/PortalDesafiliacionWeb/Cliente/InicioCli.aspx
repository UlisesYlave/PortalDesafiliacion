<%@ Page Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="InicioCli.aspx.cs" Inherits="PortalDesafiliacionWeb.Inicio" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="container mt-4">
        <h4 class="mb-4">Mi Perfil</h4>

        <div class="card p-4 shadow-sm">
            <div class="mb-2">
                <strong>Código de usuario:</strong> 
                <asp:Label ID="lblCodigoUsuario" runat="server" />
            </div>
            <div class="mb-2">
                <strong>Nombre:</strong> 
                <asp:Label ID="lblNombre" runat="server" />
            </div>
            <div class="mb-2">
                <strong>Número de líneas:</strong> 
                <asp:Label ID="lblNumeroLineas" runat="server" />
            </div>
            <div class="mb-2">
                <strong>Tipo de documento identidad:</strong> 
                <asp:Label ID="lblTipoDocumento" runat="server" />
            </div>
            <div class="mb-2">
                <strong>Número de documento:</strong> 
                <asp:Label ID="lblNumeroDocumento" runat="server" />
            </div>
            <div class="mb-2">
                <strong>Correo electrónico:</strong> 
                <asp:Label ID="lblCorreo" runat="server" />
            </div>
        </div>
    </div>
</asp:Content>