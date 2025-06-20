<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="PortalDesafiliacionWeb.Login" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Iniciar sesión</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #f9f9f9;
            height: 100vh;
            margin: 0;
        }

        .topbar {
            background-color: #c62828;
            color: white;
            padding: 15px 20px;
            font-size: 20px;
            font-weight: bold;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            height: 60px;
            z-index: 1000;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .login-box {
            background-color: #6c757d;
            border-radius: 20px;
            padding: 40px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
        }

        .form-control {
            border-radius: 10px;
        }

        .btn-login:hover {
            background-color: #880e4f;
        }

        .login-title {
            color: white;
            font-weight: bold;
        }

        .login-subtext {
            color: #fff5f5;
            font-size: 14px;
            margin-bottom: 25px;
        }
        .custom-btn {
            padding: 10px 28px;
            border-radius: 20px;
            font-weight: 600;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
        }

        .custom-btn-danger {
            background-color: #dc3545;
            border: none;
            color: white;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .warning {
            color: white;
            font-weight: normal;
        }

    </style>
</head>
<body>
    <form id="form1" runat="server">

        <div class="topbar">
            Portal de Desafiliación Web
        </div>
        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="login-box text-center">
                <h2 class="login-title">Iniciar sesión</h2>
                <p class="login-subtext">Ingresa tus credenciales para acceder a tu cuenta</p>
                <div class="mb-3">
                    <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control" placeholder="Número usuario/correo" />
                </div>
                <div class="mb-3">
                    <asp:TextBox ID="txtClave" runat="server" TextMode="Password" CssClass="form-control" placeholder="Contraseña" />
                </div>
                <div class="d-grid mb-2">
                    <asp:Button ID="btnLogin" runat="server" Text="Ingresar" CssClass="btn custom-btn custom-btn-danger" OnClick="btnLogin_Click" />
                </div>
                <asp:Label ID="lblMensaje" runat="server" CssClass="warning" />
            </div>
        </div>
    </form>
</body>
</html>