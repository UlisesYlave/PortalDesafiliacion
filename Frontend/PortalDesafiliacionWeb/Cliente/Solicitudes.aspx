<%@ Page Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="Solicitudes.aspx.cs" 
    Inherits="PortalDesafiliacionWeb.Cliente.Solicitudes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <!-- Font Awesome para iconos -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <!-- jQuery y Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
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

        .btn-group {
            display: flex;
            gap: 10px;
        }

        .action-btn {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 500;
        }

        .action-btn:hover {
            background-color: #45a049;
        }
        /* Eliminar backdrop y fondo oscuro */
        .modal-backdrop {
            display: none !important;
        }

        .notification-modal {
            background-color: transparent !important;
            pointer-events: none;
        }

        /* Posición fija en esquina inferior derecha */
        .modal-dialog-fixed {
            position: fixed;
            bottom: 20px;
            right: 20px;
            margin: 0;
            z-index: 9999;
            pointer-events: auto;
        }

        /* Animaciones mejoradas */
        .notification-modal .modal-dialog-fixed {
            animation: slideIn 0.3s forwards, fadeOut 0.5s forwards 3s;
        }

        .notification-modal .modal-content {
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            border: none;
            background: white;
        }

        @keyframes slideIn {
            from { 
                transform: translateX(100%); 
                opacity: 0; 
            }
            to { 
                transform: translateX(0); 
                opacity: 1; 
            }
        }

        @keyframes fadeOut {
            from { 
                opacity: 1;
                transform: translateX(0);
            }
            to { 
                opacity: 0;
                transform: translateX(20px);
            }
        }

        /* Estilos para iconos según estado */
        .toast-success #modalIcon {
            color: #28a745 !important;
        }

        .toast-error #modalIcon {
            color: #dc3545 !important;
        }
    </style>

    <div class="container mt-4 solicitud-container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h4 class="mb-0">Mis Solicitudes</h4>
            <div class="btn-group">
                <asp:Button ID="btnDescargarExcel" runat="server" 
                    CssClass="action-btn" 
                    Text="Exportar a Excel" 
                    OnClick="btnExportarExcel_Click" />

                <asp:Button ID="btnDescargarCSV" runat="server" 
                    CssClass="action-btn" 
                    Text="Exportar a CSV" 
                    OnClick="btnExportarCsv_Click" />

            </div>
        </div>
        
        <asp:Label ID="lblMensaje" runat="server" CssClass="d-none" />
        
        <div class="card shadow-sm border-0">
            <div class="card-body p-0">
                <asp:GridView ID="gvSolicitudes" runat="server" AutoGenerateColumns="false"
                    CssClass="table table-borderless" GridLines="None" 
                    OnRowDataBound="gvSolicitudes_RowDataBound" ShowHeader="false">
                    <Columns>
                        <asp:TemplateField>
                            <ItemTemplate>
                                <div class="solicitud-card">
                                    <div class="solicitud-header">
                                        <h5 class="solicitud-title">Solicitud N°<%# Eval("idSolicitud") %></h5>
                                        <span class='solicitud-badge badge-<%# Eval("resultado").ToString().ToLower() %>'>
                                            <%# Eval("resultado") %>
                                        </span>
                                    </div>
                                    
                                    <div class="solicitud-body">
                                        <div class="solicitud-line">
                                            <span class="solicitud-label">Fecha solicitud:</span>
                                            <span class="solicitud-value"><%# FormatearFecha((DateTime?)Eval("fechaSolicitud")) %></span>
                                        </div>
                                        <div class="solicitud-line">
                                            <span class="solicitud-label">Número de línea:</span>
                                            <span class="solicitud-value"><%# Eval("idLinea") %></span>
                                        </div>
                                    </div>
                                    
                                    <div class="solicitud-divider"></div>
                                    
                                    <div class="solicitud-footer">
                                        <a href='<%# "DetalleSolicitud.aspx?id=" + Eval("idSolicitud") %>' 
                                           class="btn custom-btn custom-btn-secondary px-3 py-1">
                                            Ver detalle
                                        </a>
                                    </div>
                                </div>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
    </div>
   <!-- Modal de Notificación Modificado -->
    <div class="modal fade notification-modal" id="notificationModal" tabindex="-1" role="dialog" aria-hidden="true" data-bs-backdrop="false" style="background-color: transparent !important;">
        <div class="modal-dialog modal-dialog-fixed" role="document">
            <div class="modal-content">
                <div class="modal-body p-3">
                    <div class="d-flex align-items-center">
                        <div class="mr-3">
                            <i id="modalIcon" class="fas fa-check-circle fa-2x text-success"></i>
                        </div>
                        <div>
                            <h6 id="modalTitle" class="mb-0">Éxito</h6>
                            <p id="modalMessage" class="mb-0 small">Operación completada</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script>
        function MostrarMensaje(titulo, mensaje, esExito) {
            const modal = $('#notificationModal');

            // Configurar contenido
            $('#modalTitle').text(titulo);
            $('#modalMessage').text(mensaje);

            // Configurar estilos según estado
            modal.find('.modal-content')
                .removeClass('toast-success toast-error')
                .addClass(esExito ? 'toast-success' : 'toast-error');

            // Reiniciar animación
            modal.find('.modal-dialog-fixed')
                .css('animation', 'none')
                .trigger('reflow');

            // Mostrar modal
            modal.modal('show');

            // Ocultar automáticamente después de 3 segundos
            setTimeout(() => {
                modal.modal('hide');
            }, 3000);

            // Eliminar el modal del DOM después de ocultarse
            modal.on('hidden.bs.modal', function () {
                $(this).find('.modal-dialog-fixed').css('animation', '');
            });
        }
    </script>
</asp:Content>
