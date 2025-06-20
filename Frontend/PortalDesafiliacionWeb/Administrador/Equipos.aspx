<%@ Page Title="" Language="C#" MasterPageFile="~/Administrador/Admin.Master" AutoEventWireup="true" CodeBehind="Equipos.aspx.cs" Inherits="PortalDesafiliacionWeb.Administrador.Equipos" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">

    <style>
        .equipos-container {
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

        /* Estilo principal para el GridView */
        .custom-gridview {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            border: none;
            margin: 20px 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            box-shadow: 0 4px 16px rgba(0,0,0,0.08), 0 1.5px 4px rgba(0,0,0,0.04);
        }
    
        /* Cabeceras */
        .custom-gridview th {
            background-color: #6c757d;
            color: white;
            font-weight: 600;
            padding: 12px 15px;
            text-align: left;
            border-bottom: 2px solid #dee2e6;
            border-top: none;
        }
    
        /* Celdas */
        .custom-gridview td {
            padding: 12px 15px;
            border-bottom: 1px solid #e9ecef;
            vertical-align: middle;
        }
    
        /* Filas */
        .custom-gridview tr {
            transition: background-color 0.2s;
        }
    
        /* Efecto hover */
        .custom-gridview tr:hover {
            background-color: #f8f9fa;
        }
    
        /* Primera fila (sin borde superior) */
        .custom-gridview tr:first-child th {
            border-top: none;
        }
    
        /* Esquinas redondeadas */
        .custom-gridview {
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
        }
    
        /* Estado ACTIVO */
        .status-active {
            color: #28a745;
            font-weight: 500;
        }
    
        /* Estado INACTIVO */
        .status-inactive {
            color: #dc3545;
            font-weight: 500;
        }
    
        /* Acciones */
        .action-icons {
            display: flex;
            gap: 10px;
        }
    
        .action-icon {
            color: #6c757d;
            cursor: pointer;
            transition: color 0.2s;
        }
    
        .action-icon:hover {
            color: #007bff;
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

    <div class="equipos-container">
        <div class="header-section">
            <h2>Gestión de Equipos</h2>
            <div class="search-section">
                <asp:TextBox ID="txtBuscarEquipo" runat="server" CssClass="search-box" placeholder="Buscar equipos..."></asp:TextBox>
                <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="action-btn" />
                <asp:Button ID="btnNuevoEquipo" runat="server" Text="+ Nuevo Equipo" CssClass="action-btn" OnClick="btnNuevoEquipo_Click" />
            </div>
        </div>
        
        <asp:GridView ID="gvEquipos" runat="server" AutoGenerateColumns="False" CssClass="custom-gridview"
            HeaderStyle-CssClass="gridview-header" RowStyle-CssClass="gridview-row">
            <Columns>
                <asp:BoundField DataField="Modelo" HeaderText="Modelo" />
                <asp:BoundField DataField="Marca" HeaderText="Marca" />
                <asp:BoundField DataField="Categoria" HeaderText="Categoría" />
                <asp:BoundField DataField="Precio" HeaderText="Precio" DataFormatString="{0:S/ #.#0}" />
                <asp:BoundField DataField="Stock" HeaderText="Stock" />
                <asp:TemplateField HeaderText="Estado">
                    <ItemTemplate>
                        <span class='<%# Eval("Estado").ToString() == "ACTIVO" ? "status-active" : "status-inactive" %>'>
                            <%# Eval("Estado") %>
                        </span>
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <div class="action-icons">
                            <i class="fas fa-edit action-icon" title="Editar"></i>
                            <i class="fas fa-trash-alt action-icon" title="Eliminar"></i>
                        </div>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
    
    <!-- Modal para agregar/editar equipo -->
    <asp:Panel ID="pnlModalEquipo" runat="server" CssClass="modal-blur-bg">
        <div class="modal-content">
            <h3><asp:Literal ID="ltModalTitle" runat="server" Text="Nuevo Equipo"></asp:Literal></h3>
            
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
            document.getElementById('<%= pnlModalEquipo.ClientID %>').style.display = 'flex';
        }
        
        function ocultarModal() {
            document.getElementById('<%= pnlModalEquipo.ClientID %>').style.display = 'none';
        }
    </script>
</asp:Content>


