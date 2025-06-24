<%@ Page Title="" Language="C#" MasterPageFile="~/Administrador/Admin.Master" AutoEventWireup="true" CodeBehind="Plantilla.aspx.cs" Inherits="PortalDesafiliacionWeb.Administrador.Plantilla" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

<style>
    /* Estilos base de la tarjeta */
    .plantillas-container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 20px;
    }

    .header-section {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 25px;
    }

    .search-section {
        display: flex;
        gap: 10px;
    }

    .search-box {
        padding: 8px 12px;
        border: 1px solid #ddd;
        border-radius: 4px;
        width: 300px;
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

    .plantilla-card {
        background: #fff;
        border-radius: 12px;
        border: 1px solid #e0e0e0;
        box-shadow: 0 4px 12px rgba(0,0,0,0.08);
        padding: 24px;
        margin-bottom: 24px;
        transition: all 0.3s ease;
        position: relative;
        overflow: hidden;
    }
    
    .plantilla-card:hover {
        transform: translateY(-3px);
        box-shadow: 0 8px 20px rgba(0,0,0,0.12);
    }
    
    /* Encabezado */
    .plantilla-header {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        margin-bottom: 16px;
        padding-bottom: 16px;
        border-bottom: 1px solid #f0f0f0;
    }
    
    .plantilla-title {
        font-size: 1.5em;
        font-weight: 700;
        color: #2c3e50;
        margin: 0;
        display: flex;
        align-items: center;
        gap: 10px;
    }
    
    .plantilla-type {
        background-color: #e3f2fd;
        color: #1976d2;
        padding: 4px 10px;
        border-radius: 4px;
        font-size: 0.8em;
        font-weight: 600;
    }
    
    /* Cuerpo */
    .plantilla-content {
        margin-bottom: 20px;
        color: #546e7a;
        line-height: 1.6;
    }
    
    /* Sección de Modalidades */
    .modalidades-section {
        background: #f8f9fa;
        border-radius: 8px;
        padding: 12px 16px;
        margin-bottom: 16px;
    }
    
    .modalidades-title {
        font-weight: 600;
        margin-bottom: 8px;
        color: #37474f;
        display: flex;
        align-items: center;
        gap: 8px;
    }
    
    .modalidades-list {
        display: flex;
        flex-wrap: wrap;
        gap: 8px;
    }
    
    .modalidad-tag {
        background: #e0e0e0;
        padding: 4px 10px;
        border-radius: 16px;
        font-size: 0.8em;
        font-weight: 500;
    }
    
    /* Sección de Beneficios */
    .beneficios-section {
        background: #f1f8e9;
        border-radius: 8px;
        padding: 12px 16px;
        margin-bottom: 16px;
    }
    
    .beneficios-title {
        font-weight: 600;
        margin-bottom: 8px;
        color: #2e7d32;
        display: flex;
        align-items: center;
        gap: 8px;
    }
    
    .beneficios-list {
        display: flex;
        flex-direction: column;
        gap: 8px;
    }
    
    .beneficio-item {
        display: flex;
        align-items: flex-start;
        gap: 8px;
    }
    
    /* Pie de tarjeta */
    .plantilla-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-top: 16px;
        border-top: 1px solid #eee;
        font-size: 0.9em;
    }
    
    .plantilla-meta {
        color: #78909c;
        display: flex;
        flex-direction: column;
        gap: 4px;
    }
    
    .plantilla-actions {
        display: flex;
        gap: 10px;
    }
    
    .edit-btn {
        background-color: #2196f3;
        color: white;
    }
    
    .delete-btn {
        background-color: #f44336;
        color: white;
    }
    
    /* Etiquetas de estado */
    .status-badge {
        position: absolute;
        top: 16px;
        right: 16px;
        padding: 4px 10px;
        border-radius: 12px;
        font-size: 0.75em;
        font-weight: 600;
    }
    
    .status-active {
        background-color: #e8f5e9;
        color: #2e7d32;
    }
    
    .status-inactive {
        background-color: #ffebee;
        color: #c62828;
    }
    
    /* Prioridad */
    .priority-badge {
        position: absolute;
        top: 16px;
        right: 100px;
        padding: 4px 10px;
        border-radius: 12px;
        font-size: 0.75em;
        font-weight: 600;
    }
    
    .priority-high {
        background-color: #fff3cd;
        color: #856404;
    }
    
    .priority-medium {
        background-color: #e2e3e5;
        color: #383d41;
    }
    
    .priority-low {
        background-color: #d4edda;
        color: #155724;
    }
    /* Modal styles */
    .modal-blur-bg {
        position: fixed;
        top: 0; left: 0; right: 0; bottom: 0;
        background: rgba(0,0,0,0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 1000;
        display: none;
    }

    .modal-content {
        background: white;
        padding: 30px;
        border-radius: 8px;
        width: 500px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: 500;
    }

    .form-control {
        width: 100%;
        padding: 8px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .modal-footer {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
        margin-top: 20px;
    }

    .btn {
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
    }

    .btn-primary {
        background-color: #2196F3;
        color: white;
        border: none;
    }

    .btn-secondary {
        background-color: #f44336;
        color: white;
        border: none;
    }
</style>

    <div class="plantillas-container">
        <div class="header-section">
            <h2>Gestión de Plantillas de Ofertas</h2>
            <div class="search-section">
                <asp:TextBox ID="txtBuscarPlantilla" runat="server" CssClass="search-box" placeholder="Buscar plantillas..."></asp:TextBox>
                <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="action-btn" />
                <asp:Button ID="btnNuevoEquipo" runat="server" Text="+ Nueva Plantilla" CssClass="action-btn" OnClick="btnNuevoPlantillaOferta_Click" />
            </div>
        </div>

        <asp:Repeater ID="rptPlantillas" runat="server" OnItemCommand="rptPlantillas_ItemCommand">
            <ItemTemplate>
                <div class="plantilla-card">
                    <!-- Badges de estado y prioridad -->
                    <span class='priority-badge <%# GetPriorityClass(Eval("prioridad")) %>'>
                        <%# GetPrioridadNombre(Eval("prioridad")) %>
                    </span>
            
                    <span class='status-badge <%# (bool)Eval("activa") ? "status-active" : "status-inactive" %>'>
                        <%# (bool)Eval("activa") ? "ACTIVO" : "INACTIVO" %>
                    </span>
            
                    <!-- Encabezado -->
                    <div class="plantilla-header">
                        <h3 class="plantilla-title">
                            <%# Eval("nombre") %>
                            <span class="plantilla-type"><%# GetTipoServicioNombre(Convert.ToInt32(Eval("TipoServicio"))) %></span>
                        </h3>
                    </div>
            
                    <!-- Descripción -->
                    <div class="plantilla-content">
                        <p><%# Eval("formula") %></p>
                    </div>
            
                    <!-- Modalidades -->
                    <div class="modalidades-section">
                        <div class="modalidades-title">
                            <i class="fas fa-tags"></i> Modalidades aplicables
                        </div>
                        <div class="modalidades-list">
                            <asp:Repeater ID="rptModalidades" runat="server" 
                                DataSource='<%# CargarModalidades(Convert.ToInt32(Eval("idPlantilla"))) %>'>
                                <ItemTemplate>
                                    <span class="modalidad-tag">
                                        <%# Eval("nombre") %>  <!-- Nombre de la modalidad -->
                                    </span>
                                </ItemTemplate>
                            </asp:Repeater>
                        </div>
                    </div>
            
                    <!-- Beneficios -->
                    <div class="beneficios-section">
                        <div class="beneficios-title">
                            <i class="fas fa-gift"></i> Beneficios incluidos
                        </div>
                        <div class="beneficios-list">
                            <div class="beneficio-item">
                                <i class="fas fa-check-circle" style="color: #4caf50;"></i>
                                <span>Telefono</span>
                            </div>
                        </div>
                    </div>
            
                    <!-- Pie de página -->
                    <div class="plantilla-footer">
                        <div class="plantilla-meta">
                            <div><strong>Validez hasta:</strong> <%# Eval("PeriodoValidez") %></div>
                        </div>
                        <div class="plantilla-actions">
                            <asp:Button ID="btnEditar" runat="server" Text="Modificar" CssClass="action-btn edit-btn" 
                                       CommandName="Editar" CommandArgument='<%# Eval("IdPlantilla") %>' />
                            <asp:Button ID="btnEliminar" runat="server" Text="Eliminar" CssClass="action-btn delete-btn" 
                                       CommandName="Eliminar" CommandArgument='<%# Eval("IdPlantilla") %>' 
                                       OnClientClick="return confirm('¿Está seguro que desea eliminar esta plantilla?');" />
                        </div>
                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>

     <!-- Modal para agregar/editar equipo -->
 <asp:Panel ID="pnlModalPlantillaOferta" runat="server" CssClass="modal-blur-bg">
     <div class="modal-content">
         <h3><asp:Literal ID="ltModalTitle" runat="server" Text="Nueva Plantilla"></asp:Literal></h3>
         
         <div class="form-group">
             <label for="ddlCategoria">Categoría:</label>
             <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-control">
                 <asp:ListItem Text="Teléfono" Value="Telefono"></asp:ListItem>
                 <asp:ListItem Text="Tablet" Value="Tablet"></asp:ListItem>
                 <asp:ListItem Text="Accesorio" Value="Accesorio"></asp:ListItem>
             </asp:DropDownList>
         </div>
         
         <div class="form-group">
             <label for="txtMarca">Marca:</label>
             <asp:TextBox ID="txtMarca" runat="server" CssClass="form-control"></asp:TextBox>
         </div>
         
         <div class="form-group">
             <label for="txtModelo">Modelo:</label>
             <asp:TextBox ID="txtModelo" runat="server" CssClass="form-control"></asp:TextBox>
         </div>
         
         <div class="form-group">
             <label for="txtPrecio">Precio:</label>
             <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control" TextMode="Number" step="0.01"></asp:TextBox>
         </div>
         
         <div class="form-group">
             <label for="txtStock">Stock:</label>
             <asp:TextBox ID="txtStock" runat="server" CssClass="form-control" TextMode="Number"></asp:TextBox>
         </div>
         
         <div class="form-group">
             <label for="chkEstado">Estado:</label>
             <asp:CheckBox ID="chkEstado" runat="server" Text=" Activo" />
         </div>
         
         <div class="modal-footer">
             <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" OnClick="btnCancelar_Click" />
             <asp:Button ID="btnGuardar" runat="server" Text="Guardar" CssClass="btn btn-primary" OnClick="btnGuardar_Click" />
         </div>
     </div>
 </asp:Panel>
<!-- Incluir Font Awesome para iconos -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<script type="text/javascript">
    function mostrarModal() {
        document.getElementById('<%= pnlModalPlantillaOferta.ClientID %>').style.display = 'flex';
    }
    
    function ocultarModal() {
        document.getElementById('<%= pnlModalPlantillaOferta.ClientID %>').style.display = 'none';
    }
</script>
</asp:Content>
