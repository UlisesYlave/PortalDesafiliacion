-- -----------------------------------------------------
-- Schema PortalDeDesafiliacionMovilDB
-- -----------------------------------------------------
-- CREATE DATABASE IF NOT EXISTS PortalDeDesafiliacionMovilDB;
USE PortalDeDesafiliacionMovilDB;

-- -----------------------------------------------------
-- Table `Region`
-- Asumiendo que el DAO mapea a atributos como: idRegion, nombre, numeroBajasRegion, etc.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Region` (
  `idRegion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `numeroBajasRegion` INT DEFAULT 0,
  `numeroUsuariosRegion` INT DEFAULT 0,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idRegion`));

-- -----------------------------------------------------
-- Table `Prioridad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Prioridad` (
  `idPrioridad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL UNIQUE,
  `cantidadOfertas` INT DEFAULT 0,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPrioridad`));

-- -----------------------------------------------------
-- Table `TipoMotivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TipoMotivo` (
  `idTipoMotivo` INT NOT NULL AUTO_INCREMENT, -- DAO usa idTipoMotivo en mapeo
  `nombre` VARCHAR(100) NOT NULL,
  `descripcion` TEXT,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idTipoMotivo`));

-- -----------------------------------------------------
-- Table `Modalidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Modalidad` (
  `idModalidad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL UNIQUE,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idModalidad`));

-- -----------------------------------------------------
-- Table `Parametro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Parametro` (
  `idParametro` INT NOT NULL AUTO_INCREMENT,
  `nombreParametro` VARCHAR(100) NOT NULL UNIQUE,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idParametro`));

-- -----------------------------------------------------
-- Table `Equipo` (Basado en tu ejemplo de mapeo)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Equipo` (
  `idEquipo` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(100) NOT NULL,
  `modelo` VARCHAR(100) NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `stock` INT DEFAULT 0,
  `categoria` ENUM('ALTA', 'MEDIA', 'BAJA'), -- Asumiendo que el DAO mapearía "categoria"
  `estado` VARCHAR(50),
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idEquipo`));

-- -----------------------------------------------------
-- Table `Usuario`
-- Asumiendo mapeo a: idUsuario, primerNombre, segundoNombre, apellidoPaterno, etc.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `primerNombre` VARCHAR(100) NOT NULL,
  `segundoNombre` VARCHAR(100),
  `apellidoPaterno` VARCHAR(100) NOT NULL,
  `apellidoMaterno` VARCHAR(100),
  `tipoDocumento` VARCHAR(20) NOT NULL,
  `numeroDocumento` VARCHAR(20) NOT NULL,
  `correo` VARCHAR(255) UNIQUE,
  `contrasena` VARCHAR(255),
  `estado` VARCHAR(50) DEFAULT 'ACTIVO',
  `activo` BOOLEAN DEFAULT TRUE,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idx_usuario_numeroDocumento` (`tipoDocumento` ASC, `numeroDocumento` ASC));

-- -----------------------------------------------------
-- Table `Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Cliente` (
  `idCliente` INT NOT NULL, -- Hereda de Usuario
  `idRegion` INT,
  `provincia` VARCHAR(100),
  `departamento` VARCHAR(100),
  `distrito` VARCHAR(100),
  `antiguedadMeses` INT DEFAULT 0,
  `idPrioridad` INT,
  `fechaUltimaOferta` DATETIME,
  PRIMARY KEY (`idCliente`),
  CONSTRAINT `fk_Cliente_Usuario`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Cliente_Region`
    FOREIGN KEY (`idRegion`)
    REFERENCES `Region` (`idRegion`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Cliente_Prioridad`
    FOREIGN KEY (`idPrioridad`)
    REFERENCES `Prioridad` (`idPrioridad`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Administrador` (
  `idAdministrador` INT NOT NULL, -- Hereda de Usuario
  `fechaVigencia` DATE,
  PRIMARY KEY (`idAdministrador`),
  CONSTRAINT `fk_Administrador_Usuario`
    FOREIGN KEY (`idAdministrador`)
    REFERENCES `Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `PlantillaOferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlantillaOferta` (
  `idPlantillaOferta` INT NOT NULL AUTO_INCREMENT, -- Asumiendo DAO mapea a idPlantillaOferta
  `nombre` VARCHAR(255) NOT NULL,
  `formula` TEXT,
  `tipoServicio` INT,
  `idPrioridad` INT,
  `fechaValidezHasta` DATE,
  `activa` BOOLEAN DEFAULT TRUE,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPlantillaOferta`),
  CONSTRAINT `fk_PlantillaOferta_Prioridad`
    FOREIGN KEY (`idPrioridad`)
    REFERENCES `Prioridad` (`idPrioridad`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `PlantillaOferta_Modalidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlantillaOferta_Modalidad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPlantillaOferta` INT NOT NULL,
  `idModalidad` INT NOT NULL,
  `fechaAsociacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_PlantillaOfertaModalidad_Plantilla`
    FOREIGN KEY (`idPlantillaOferta`)
    REFERENCES `PlantillaOferta` (`idPlantillaOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PlantillaOfertaModalidad_Modalidad`
    FOREIGN KEY (`idModalidad`)
    REFERENCES `Modalidad` (`idModalidad`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `PlantillaOferta_Parametro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PlantillaOferta_Parametro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPlantillaOferta` INT NOT NULL,
  `idParametro` INT NOT NULL,
  `valorParametro` VARCHAR(255),
  `fechaAsociacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_PlantillaOfertaParametro_Plantilla`
    FOREIGN KEY (`idPlantillaOferta`)
    REFERENCES `PlantillaOferta` (`idPlantillaOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PlantillaOfertaParametro_Parametro`
    FOREIGN KEY (`idParametro`)
    REFERENCES `Parametro` (`idParametro`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Plan` (
  `idPlan` INT NOT NULL AUTO_INCREMENT, -- Asumiendo DAO mapea a idPlan o idServicio
  `nombrePlan` VARCHAR(100) NOT NULL,
  `beneficios` TEXT,
  `tipoServicio` VARCHAR(50),
  `precioMensual` DECIMAL(10,2) NOT NULL,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPlan`));

-- -----------------------------------------------------
-- Table `Paquete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Paquete` (
  `idPaquete` INT NOT NULL AUTO_INCREMENT, -- Asumiendo DAO mapea a idPaquete o idServicio
  `nombrePaquete` VARCHAR(100) NOT NULL,
  `beneficios` TEXT,
  `tipoServicio` VARCHAR(50),
  `precio` DECIMAL(10,2) NOT NULL,
  `duracionDias` INT,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPaquete`));

-- -----------------------------------------------------
-- Table `Linea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Linea` (
  `idLinea` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `numeroTelefono` VARCHAR(20) NOT NULL UNIQUE,
  `tipoLinea` ENUM('PREPAGO', 'POSTPAGO') NOT NULL,
  `activa` BOOLEAN DEFAULT TRUE,
  `fechaActivacion` DATE,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idLinea`),
  CONSTRAINT `fk_Linea_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Cliente` (`idCliente`) -- Referencia a Cliente.idCliente
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Oferta` (
  `idOferta` INT NOT NULL AUTO_INCREMENT,
  `idPlantillaOferta` INT,
  `idLinea` INT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT,
  `idEquipo` INT,
  `descuentoAplicado` DECIMAL(10,2),
  `fechaInicioVigencia` DATE,
  `fechaFinVigencia` DATE,
  `estado` VARCHAR(50),
  `modalidadSeleccionada` VARCHAR(100),
  `fechaCreacionOferta` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacionOferta` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idOferta`),
  CONSTRAINT `fk_Oferta_PlantillaOferta`
    FOREIGN KEY (`idPlantillaOferta`)
    REFERENCES `PlantillaOferta` (`idPlantillaOferta`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Oferta_Equipo`
    FOREIGN KEY (`idEquipo`)
    REFERENCES `Equipo` (`idEquipo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Oferta_Linea`
    FOREIGN KEY (`idLinea`)
    REFERENCES `Linea`(`idLinea`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Prepago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Prepago` (
  `idLineaPrepago` INT NOT NULL, -- Hereda de Linea
  `promedioRecargaMensual` DECIMAL(10,2) DEFAULT 0.00,
  `idPaqueteActual` INT,
  `saldoActual` DECIMAL(10,2) DEFAULT 0.00,
  `fechaUltimaRecarga` DATE,
  PRIMARY KEY (`idLineaPrepago`),
  CONSTRAINT `fk_Prepago_Linea`
    FOREIGN KEY (`idLineaPrepago`)
    REFERENCES `Linea` (`idLinea`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Prepago_Paquete`
    FOREIGN KEY (`idPaqueteActual`)
    REFERENCES `Paquete` (`idPaquete`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Postpago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Postpago` (
  `idLineaPostpago` INT NOT NULL, -- Hereda de Linea
  `deudaPendiente` DECIMAL(10,2) DEFAULT 0.00,
  `idPlanActual` INT NOT NULL,
  `diaCicloFacturacion` INT,
  PRIMARY KEY (`idLineaPostpago`),
  CONSTRAINT `fk_Postpago_Linea`
    FOREIGN KEY (`idLineaPostpago`)
    REFERENCES `Linea` (`idLinea`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Postpago_Plan`
    FOREIGN KEY (`idPlanActual`)
    REFERENCES `Plan` (`idPlan`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Linea_Oferta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Linea_Oferta` (
  `idLineaOferta` INT NOT NULL AUTO_INCREMENT,
  `idLinea` INT NOT NULL,
  `idOferta` INT NOT NULL,
  `fechaAplicacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `estadoOfertaLinea` VARCHAR(50),
  `fechaAsociacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idLineaOferta`),
  UNIQUE INDEX `idx_linea_oferta_unica` (`idLinea` ASC, `idOferta` ASC),
  CONSTRAINT `fk_LineaOferta_Linea`
    FOREIGN KEY (`idLinea`)
    REFERENCES `Linea` (`idLinea`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_LineaOferta_Oferta`
    FOREIGN KEY (`idOferta`)
    REFERENCES `Oferta` (`idOferta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Encuesta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Encuesta` (
  `idEncuesta` INT NOT NULL AUTO_INCREMENT,
  `idTipoMotivo` INT,                  -- Asumiendo DAO mapea a "idTipoMotivo"
  `calificacion` INT,
  `opinion` TEXT,
  `fechaEncuesta` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idEncuesta`),
  CONSTRAINT `fk_Encuesta_TipoMotivo`
    FOREIGN KEY (`idTipoMotivo`)
    REFERENCES `TipoMotivo` (`idTipoMotivo`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `SolicitudDesafiliacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SolicitudDesafiliacion` (
  `idSolicitudDesafiliacion` INT NOT NULL AUTO_INCREMENT, -- Asumiendo DAO mapea a "idSolicitudDesafiliacion"
  `idLinea` INT NOT NULL,
  `idCliente` INT NOT NULL,
  `idOfertaAceptada` INT,
  `idEncuesta` INT,
  `fechaSolicitud` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `resultado` ENUM('DESAFILIADO', 'NO_DESAFILIADO', 'OFERTA_ACEPTADA'),
  `observacionesAgente` TEXT,
  `fechaEfectivaDesafiliacion` DATE,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `fechaModificacion` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idSolicitudDesafiliacion`),
  CONSTRAINT `fk_Solicitud_Linea`
    FOREIGN KEY (`idLinea`)
    REFERENCES `Linea` (`idLinea`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Solicitud_Cliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `Cliente` (`idCliente`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Solicitud_OfertaAceptada`
    FOREIGN KEY (`idOfertaAceptada`)
    REFERENCES `Oferta` (`idOferta`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Solicitud_Encuesta`
    FOREIGN KEY (`idEncuesta`)
    REFERENCES `Encuesta` (`idEncuesta`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);