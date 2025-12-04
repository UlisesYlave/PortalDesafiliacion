# 📁 Portal de Desafiliación Front & Back  
**Versión:** 1.0  
**Estado:** Activo (última actualización hace 2 meses)  

---

## 🎯 Objetivo del Proyecto  
Portal web diseñado para **retener usuarios** mediante ofertas personalizadas cuando intentan desafiliarse o mejorar su plan actual.  

### 👥 Stakeholders Principales  
- 👤 **Usuario final**: busca desvincularse o mejorar su plan.  
- 👨‍💼 **Administrador**: gestiona plantillas de ofertas, stock y priorización.  

---

## 🏗️ Estructura del Proyecto  

```
Backend/PortalDeDesafiliacionFront/
├── 📁 Frontend/                    # Interfaz web (ASP .NET + Bootstrap)
├── 📄 .gitignore
├── 📄 CreacionTables.sql           # Creación de tablas en MySQL
├── 📄 Procedures.sql               # Procedimientos almacenados
├── 📄 README.md                    # Documentación principal
└── 📁 Backend/                     # Lógica del servidor (Java + GlassFish)
    └── 📄 DesafiliacionB0Impl.java # Implementación principal del servicio SOAP
```

---

## 🛠️ Tecnologías Utilizadas  

### 🔙 **Backend**  
- **IDE:** NetBeans 25  
- **Servidor:** GlassFish 7.0.21  
- **Base de datos:** MySQL (AWS Aurora RDS)  
- **Gestor BD:** MySQL Workbench  
- **Protocolo:** SOAP para comunicación con Frontend  

### 🎨 **Frontend**  
- **IDE:** Visual Studio  
- **Framework:** ASP .NET  
- **Estilos:** Bootstrap 5  
- **Visualización:** Componentes responsivos y modales  

---

## 🚀 Cómo Ejecutar el Proyecto  

### 1. 🗃️ Configuración de la Base de Datos  
Ejecutar en orden:  
1. `CreacionTables.sql`  
2. `Procedures.sql`  

### 2. ⚙️ Iniciar Backend  
- Abrir el proyecto en **NetBeans**.  
- Ir a `WS (WebServices)` y ejecutar **"Run"**.  
- El servicio SOAP estará activo en `http://localhost:8080/`.  

### 3. 🌐 Iniciar Frontend  
- Abrir el proyecto en **Visual Studio**.  
- Presionar el botón **"Run"** (▶️).  
- Se abrirá la pantalla de login en el navegador.  

---

## 👤 Roles de Usuario  

### 🔐 Administrador  
- Crear tipos de ofertas 🏷️  
- Gestionar plantillas de ofertas 📋  
- Revisar stock de ofertas 📦  
- Ver panel de administración 📊  

### 👤 Usuario Regular  
- Visualizar ofertas personalizadas 🎁  
- Mejorar plan o solicitar desafiliación 📄  
- Recibir priorización basada en perfil 🎯  

---

## 🧩 Desarrollo de Plantillas de Ofertas  

### 📊 Priorización de Usuarios  
Cada usuario recibe un **nivel de prioridad** que determina las ofertas a mostrar.  

### 🎁 Tipos de Ofertas  
Según la línea del usuario, los beneficios pueden ser:  
- 💸 **Descuentos en la Línea**  
- 📱 **Equipos**  
- 🔧 **Descuento en equipo seleccionado**  

---

## 📌 Notas Importantes  

✅ **Última actualización significativa:** Implementación de Modalidad para Plantilla (hace 6 meses).  
✅ **Documentación actualizada:** README.md (hace 2 meses).  
✅ **Comunicación Backend-Frontend:** SOAP.  
✅ **Base de datos:** Remota (AWS RDS).  

---

## 🖼️ Vista Previa  

### 🧑‍💼 Pantalla de Administrador  
- Gestión visual de ofertas y stock.  
- Creación de clientes y asignación de prioridad.  

### 🧑‍🤝‍🧑 Pantalla de Usuario  
- Ofertas personalizadas según perfil.  
- Interfaz limpia y amigable con Bootstrap.  

---

## 📬 Contacto y Soporte  
Para problemas o mejoras, revisa la documentación en `README.md` o contacta al equipo de desarrollo.  

---
✨ **¡Gracias por usar el Portal de Desafiliación!**
