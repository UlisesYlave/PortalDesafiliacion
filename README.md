# Portal de Desafiliacion

Portal de Desafiliacion Web que busca la retención de los usuarios mediante ofertas personalizadas.

## Backend
### Herramientas usadas:
* IDE: NetBeans 25
* Server: GlassFish Server 7.0.21
* Base de datos: MySQL contenido en un AWS Aurora RDS
* MysQL Workbench: Manejo de la base de datos

Para activar el proyecto ve a WS (WebServices) y ejecuta "Run"

Para la comunicación entre Backend y Frontend usamos el protocolo SOAP

## Frontend
### Herramientas Usadas:
* IDE: Visual Studio
* Bootstrap: Componentes visuales sencillos
* Visualización: ASP .NET

Se consideraron 2 stakeholders principales:
* Usuario que quiere desvincularse o mejorar su plan actual.
* Administradores que mantienen la página con plantilla de ofertas y revisan de stock para ofertas.

## Desarrollo de Plantilla de Ofertas

### Priorización
Para la personalización primero se asigna una priorización a cada usuario para poder relacionarlas con ofertas de la misma prioridad.

### Ofertas
Estas se relacionan en base a una linea del usuario. Los beneficios pueden ser:

* Descuentos de la Linea
* Equipos
* Descuento en el equipo seleccionado
