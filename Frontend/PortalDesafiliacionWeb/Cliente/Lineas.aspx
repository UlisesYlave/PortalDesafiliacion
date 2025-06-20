<%@ Page Title="" Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="Lineas.aspx.cs" Inherits="PortalDesafiliacionWeb.Lineas" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <style type="text/css">
        .solicitud-container {
            font-family: 'Segoe UI', Arial, sans-serif;
        }
        .solicitud-card {
            background-color: #ffffff;
            border: 1px solid #e0e0e0;
            border-left: 4px solid #6c757d;
            border-radius: 4px;
            padding: 1.25rem;
            margin-bottom: 1rem;
            transition: all 0.3s ease;
        }
        .solicitud-card:hover {
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transform: translateY(-2px);
        }
        .solicitud-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 0.75rem;
        }
        .solicitud-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: #2e3a4d;
            margin: 0;
        }
        .solicitud-badge {
            font-size: 0.75rem;
            font-weight: 600;
            padding: 0.35em 0.65em;
            border-radius: 50rem;
        }
        .badge-procesada {
            background-color: #1cc88a;
            color: white;
        }
        .badge-pendiente {
            background-color: #f6c23e;
            color: #2e3a4d;
        }
        .badge-rechazada {
            background-color: #e74a3b;
            color: white;
        }
        .solicitud-body {
            margin-bottom: 0.75rem;
        }
        .solicitud-line {
            display: flex;
            margin-bottom: 0.5rem;
        }
        .solicitud-label {
            font-size: 0.85rem;
            color: #858796;
            min-width: 120px;
        }
        .solicitud-value {
            font-size: 0.9rem;
            color: #5a5c69;
            font-weight: 500;
        }
        .solicitud-divider {
            border-top: 1px solid #e3e6f0;
            margin: 0.75rem 0;
        } 
        .solicitud-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .solicitud-observacion {
            font-size: 0.85rem;
            color: #858796;
            font-style: italic;
        }
        .btn-detalle {
            font-size: 0.75rem;
            padding: 0.25rem 0.75rem;
            border-radius: 4px;
        }
    </style>

    <div class="container mt-4 solicitud-container">
        <h4 class="mb-4">Lineas Asociadas</h4>
        
        <asp:Label ID="lblMensaje" runat="server" CssClass="d-none" />
        
        <div class="card shadow-sm border-0">
            <div class="card-body p-0">
                <asp:GridView ID="gvSolicitudes" runat="server" AutoGenerateColumns="false"
                    CssClass="table table-borderless" GridLines="None" 
                    OnRowDataBound="gvLineas_RowDataBound" ShowHeader="false">
                    <Columns>
                        <asp:TemplateField>
                            <ItemTemplate>
                                <div class="solicitud-card">
                                    <div class="solicitud-header">
                                        <h5 class="solicitud-title">Linea N°<%# Eval("idLinea") %></h5>
                                        <span class='solicitud-badge badge-<%# Eval("tipoLinea").ToString().ToLower() %>'>
                                            <%# Eval("tipoLinea") %>
                                        </span>
                                    </div>
                                    
                                    <div class="solicitud-body">
                                        <div class="solicitud-line">
                                            <span class="solicitud-label">Fecha solicitud:</span>
                                            <span class="solicitud-value"><%# FormatearFecha((DateTime?)Eval("FechaActivacion")) %></span>
                                        </div>
                                        <div class="solicitud-line">
                                            <span class="solicitud-label">Número asociado:</span>
                                            <span class="solicitud-value"><%# Eval("numeroTelefono") %></span>
                                        </div>
                                    </div>
                                    
                                    <div class="solicitud-divider"></div>
                                    
                                    <div class="solicitud-footer">
                                        <a href='<%# "DetalleLinea.aspx?id=" + Eval("IdLinea") %>' 
                                           class="btn custom-btn custom-btn-secondary px-3 py-1">
                                            Ver detalle
                                        </a>
                                        <asp:LinkButton ID="btnDesafiliar" runat="server" 
                                            CssClass="btn custom-btn custom-btn-danger px-3 py-1"
                                            CommandArgument='<%# Eval("IdLinea") + "," + Eval("tipoLinea") %>'
                                            OnClick="btnDesafiliar_Click"
                                            Text="Iniciar Solicitud" />
                                    </div>
                                </div>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
    </div>

    <!-- Modal de deuda -->
    <asp:Panel ID="pnlModalDeuda" runat="server" Visible="false">
        <div style="position: fixed; inset: 0; background: rgba(0,0,0,0.5); display: flex; justify-content: center; align-items: center; z-index: 9999;">
            <div style="background-color: white; padding: 40px; border-radius: 20px; width: 350px; text-align: center; box-shadow: 0 0 20px rgba(0,0,0,0.3);">
                <h5 class="mb-4">Regulariza tus deudas para acceder a la desafiliación</h5>
                <asp:HiddenField ID="hdnLineaDesafiliar" runat="server" />
                <asp:Button ID="btnAceptarModal" runat="server" Text="Aceptar" CssClass="btn btn-danger" OnClick="btnAceptarModal_Click" />
            </div>
        </div>
    </asp:Panel>
</asp:Content>
