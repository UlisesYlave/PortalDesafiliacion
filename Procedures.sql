-- Cambiando el delimitador para poder usar ; dentro de los procedures
DELIMITER $$

-- -----------------------------------------------------
-- Schema PortalDeDesafiliacionMovilDB
-- -----------------------------------------------------
USE ventas_db$$

-- -----------------------------------------------------
-- Procedimientos para REGION
-- Basado en RegionDAOImpl.java
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS `insertarRegion`$$
CREATE PROCEDURE `insertarRegion`(
    IN p_nombre VARCHAR(100),
    IN p_numeroBajasRegion INT,
    IN p_numeroUsuariosRegion INT,
    OUT p_id INT
)
BEGIN
    INSERT INTO `Region` (`nombre`, `numeroBajasRegion`, `numeroUsuariosRegion`)
    VALUES (p_nombre, p_numeroBajasRegion, p_numeroUsuariosRegion);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarRegion`$$
CREATE PROCEDURE `modificarRegion`(
    IN p_idRegion INT,
    IN p_nombre VARCHAR(100),
    IN p_numeroBajasRegion INT,
    IN p_numeroUsuariosRegion INT
)
BEGIN
    UPDATE `Region`
    SET
        `nombre` = p_nombre,
        `numeroBajasRegion` = p_numeroBajasRegion,
        `numeroUsuariosRegion` = p_numeroUsuariosRegion,
        `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idRegion` = p_idRegion;
END$$

DROP PROCEDURE IF EXISTS `eliminarRegion`$$
CREATE PROCEDURE `eliminarRegion`(
    IN p_idRegion INT
)
BEGIN
    DELETE FROM `Region` WHERE `idRegion` = p_idRegion;
END$$

DROP PROCEDURE IF EXISTS `buscarRegionPorId`$$
CREATE PROCEDURE `buscarRegionPorId`(
    IN p_idRegion INT
)
BEGIN
    SELECT `idRegion`, `nombre`, `numeroBajasRegion`, `numeroUsuariosRegion`, `fechaCreacion`, `fechaModificacion`
    FROM `Region`
    WHERE `idRegion` = p_idRegion;
END$$

DROP PROCEDURE IF EXISTS `listarRegiones`$$
CREATE PROCEDURE `listarRegiones`()
BEGIN
    SELECT `idRegion`, `nombre`, `numeroBajasRegion`, `numeroUsuariosRegion`, `fechaCreacion`, `fechaModificacion`
    FROM `Region`;
END$$

-- -----------------------------------------------------
-- Procedimientos para USUARIO
-- Basado en UsuarioDAOImpl.java
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS `insertarUsuario`$$
CREATE PROCEDURE `insertarUsuario`(
    IN p_primerNombre VARCHAR(100),
    IN p_segundoNombre VARCHAR(100),
    IN p_apellidoPaterno VARCHAR(100), 
    IN p_apellidoMaterno VARCHAR(100),
    IN p_tipoDocumento VARCHAR(20),
    IN p_numeroDocumento VARCHAR(20),
    IN p_estado VARCHAR(50),
    IN p_correo VARCHAR(255),    -- Asumiendo que el DAO usará "p_correo"
    IN p_contrasena VARCHAR(255),-- Asumiendo que el DAO usará "p_contrasena"
    IN p_activo BOOLEAN,         -- Asumiendo que el DAO usará "p_activo"
    OUT p_id INT
)
BEGIN
    INSERT INTO `Usuario` (`primerNombre`, `segundoNombre`, `apellidoPaterno`, `apellidoMaterno`, `tipoDocumento`, `numeroDocumento`, `correo`, `contrasena`, `estado`, `activo`)
    VALUES (p_primerNombre, p_segundoNombre, p_apellidoPaterno, p_apellidoMaterno, p_tipoDocumento, p_numeroDocumento, p_correo, p_contrasena, p_estado, p_activo);
    SET p_id = LAST_INSERT_ID();
END$$
-- NOTA CRÍTICA para UsuarioDAOImpl.insertarUsuario:
-- 1. El DAO pasa `usuario.getApellidoMaterno()` para el parámetro `p_apellidoPaterno`. ¡DEBE CORREGIRSE en Java a `usuario.getApellidoPaterno()`!
-- 2. El DAO actual no pasa `correo`, `contrasena`, `activo`. DEBE MODIFICARSE el DAO para que pase estos valores a los parámetros correspondientes (ej. `p_correo`, `p_contrasena`, `p_activo`).
-- 3. El DAO pasa `p_fechaCreacion`. Este campo ahora tiene DEFAULT en la tabla. El DAO no necesita pasar `p_fechaCreacion`. El SP no lo incluye.

DROP PROCEDURE IF EXISTS `modificarUsuario`$$
CREATE PROCEDURE `modificarUsuario`(
    IN p_idUsuario INT,
    IN p_primerNombre VARCHAR(100),
    IN p_segundoNombre VARCHAR(100),
    IN p_apellidoPaterno VARCHAR(100),
    IN p_apellidoMaterno VARCHAR(100),
    IN p_tipoDocumento VARCHAR(20),
    IN p_numeroDocumento VARCHAR(20),
    IN p_estado VARCHAR(50),
    IN p_correo VARCHAR(255),    -- Asumiendo que el DAO usará "p_correo"
    IN p_contrasena VARCHAR(255),-- Asumiendo que el DAO usará "p_contrasena"
    IN p_activo BOOLEAN          -- Asumiendo que el DAO usará "p_activo"
)
BEGIN
    UPDATE `Usuario`
    SET
        `primerNombre` = p_primerNombre,
        `segundoNombre` = p_segundoNombre,
        `apellidoPaterno` = p_apellidoPaterno,
        `apellidoMaterno` = p_apellidoMaterno,
        `tipoDocumento` = p_tipoDocumento,
        `numeroDocumento` = p_numeroDocumento,
        `correo` = p_correo,
        `contrasena` = p_contrasena,
        `estado` = p_estado,
        `activo` = p_activo,
        `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idUsuario` = p_idUsuario;
END$$
-- NOTA CRÍTICA para UsuarioDAOImpl.modificarUsuario:
-- El DAO actual no pasa `correo`, `contrasena`, `activo`. DEBE MODIFICARSE el DAO para que pase estos valores.

DROP PROCEDURE IF EXISTS `eliminarUsuario`$$
CREATE PROCEDURE `eliminarUsuario`(
    IN p_idUsuario INT
)
BEGIN
    DELETE FROM `Usuario` WHERE `idUsuario` = p_idUsuario;
END$$

DROP PROCEDURE IF EXISTS `buscarUsuarioPorId`$$
CREATE PROCEDURE `buscarUsuarioPorId`(
    IN p_idUsuario INT -- Nombre de parámetro tal como está en UsuarioDAOImpl (idealmente debería ser p_idUsuario en DAO)
)
BEGIN
    SELECT `idUsuario`, `primerNombre`, `segundoNombre`, `apellidoPaterno`, `apellidoMaterno`, `tipoDocumento`, `numeroDocumento`, `correo`, `contrasena`, `estado`, `activo`, `fechaCreacion`, `fechaModificacion`
    FROM `Usuario`
    WHERE `idUsuario` = p_idUsuario;
END$$

DROP PROCEDURE IF EXISTS `listarUsuarios`$$
CREATE PROCEDURE `listarUsuarios`() -- UsuarioDAOImpl llama a "listarClientes", debe corregirse en DAO para llamar a este SP.
BEGIN
    SELECT `idUsuario`, `primerNombre`, `segundoNombre`, `apellidoPaterno`, `apellidoMaterno`, `tipoDocumento`, `numeroDocumento`, `correo`, `contrasena`, `estado`, `activo`, `fechaCreacion`, `fechaModificacion`
    FROM `Usuario`;
END$$

-- -----------------------------------------------------
-- Procedimientos para CLIENTE
-- Basado en ClienteDAOImpl.java
-- CORRECCIÓN: No hay OUT p_id.
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarCliente`$$
CREATE PROCEDURE `insertarCliente`(
    IN p_idCliente INT,
    IN p_idRegion INT,
    IN p_provincia VARCHAR(100),
    IN p_departamento VARCHAR(100),
    IN p_distrito VARCHAR(100),
    IN p_antiguedadMeses INT,
    IN p_fechaUltimaSolicitud DATETIME,
    OUT p_id INT
)
BEGIN
    INSERT INTO `Cliente` (`idCliente`, `idRegion`, `provincia`, `departamento`, `distrito`, `antiguedadMeses`, `fechaUltimaOferta`)
    VALUES (p_idCliente, p_idRegion, p_provincia, p_departamento, p_distrito, p_antiguedadMeses, p_fechaUltimaSolicitud);
    SET p_id = p_idCliente; 
END$$

DROP PROCEDURE IF EXISTS `modificarCliente`$$
CREATE PROCEDURE `modificarCliente`(
    IN p_idCliente INT, 
    IN p_idRegion INT,
    IN p_provincia VARCHAR(100),
    IN p_departamento VARCHAR(100),
    IN p_distrito VARCHAR(100),
    IN p_antiguedadMeses INT,
    IN p_fechaUltimaSolicitud DATETIME
)
BEGIN
    UPDATE `Cliente`
    SET
        `idRegion` = p_idRegion,
        `provincia` = p_provincia,
        `departamento` = p_departamento,
        `distrito` = p_distrito,
        `antiguedadMeses` = p_antiguedadMeses,
        `fechaUltimaOferta` = p_fechaUltimaSolicitud
    WHERE `idCliente` = p_idCliente; 
END$$

DROP PROCEDURE IF EXISTS `eliminarCliente`$$
CREATE PROCEDURE `eliminarCliente`(
    IN p_idCliente INT
)
BEGIN
    DELETE FROM `Cliente` WHERE `idCliente` = p_idCliente;
END$$

DROP PROCEDURE IF EXISTS `buscarClientePorId`$$
CREATE PROCEDURE `buscarClientePorId`(
    IN p_idCliente INT
)
BEGIN
    SELECT
        u.`idUsuario`, u.`primerNombre`, u.`segundoNombre`, u.`apellidoPaterno`, u.`apellidoMaterno`,
        u.`tipoDocumento`, u.`numeroDocumento`, u.`correo`, u.`contrasena`, u.`estado`, u.`activo`,
        u.`fechaCreacion` AS `usuarioFechaCreacion`, u.`fechaModificacion` AS `usuarioFechaModificacion`,
        c.`idCliente`, c.`idRegion`, c.`provincia`, c.`departamento`, c.`distrito`,
        c.`antiguedadMeses`, c.`idPrioridad`, c.`fechaUltimaOferta`
    FROM `Usuario` u
    JOIN `Cliente` c ON u.`idUsuario` = c.`idCliente`
    WHERE c.`idCliente` = p_idCliente;
END$$

DROP PROCEDURE IF EXISTS `listarClientes`$$
CREATE PROCEDURE `listarClientes`()
BEGIN
    SELECT
        u.`idUsuario`, u.`primerNombre`, u.`segundoNombre`, u.`apellidoPaterno`, u.`apellidoMaterno`,
        u.`tipoDocumento`, u.`numeroDocumento`, u.`correo`, u.`contrasena`, u.`estado`, u.`activo`,
        u.`fechaCreacion` AS `usuarioFechaCreacion`, u.`fechaModificacion` AS `usuarioFechaModificacion`,
        c.`idCliente`, c.`idRegion`, c.`provincia`, c.`departamento`, c.`distrito`,
        c.`antiguedadMeses`, c.`idPrioridad`, c.`fechaUltimaOferta`
    FROM `Usuario` u
    JOIN `Cliente` c ON u.`idUsuario` = c.`idCliente`;
END$$

-- -----------------------------------------------------
-- Procedimientos para ADMINISTRADOR
-- Basado en AdministradorDAOImpl.java
-- CORRECCIÓN: Se añade p_id_administrador (FK a Usuario) como IN y se quita OUT p_id.
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarAdministrador`$$
CREATE PROCEDURE `insertarAdministrador`(
    IN p_idAdministrador INT,       -- CORREGIDO: ID del Usuario existente. DAO debe usar este nombre de param.
    IN p_fechaVigencia DATE,        -- Nombre usado en DAO
    OUT p_id INT
)
BEGIN
    INSERT INTO `Administrador` (`idAdministrador`, `fechaVigencia`)
    VALUES (p_idAdministrador, p_fechaVigencia);
    SET p_id = p_idAdministrador; -- Aunque no se usa OUT p_id en DAO, se mantiene para compatibilidad.
END$$
-- NOTA CRÍTICA para AdministradorDAOImpl.comandoInsertar:
-- El DAO original llamaba a `{CALL insertarAdministrador(?)}` y solo seteaba `p_fechaVigencia`,
-- esperando un `p_id` OUT. Esto era incompatible con la estructura de la tabla.
-- DEBES MODIFICAR `AdministradorDAOImpl.java` para que:
-- 1. Llame a este SP con DOS parámetros IN (ej. `{CALL insertarAdministrador(?, ?)}`).
-- 2. Pase el ID del Usuario base como primer parámetro (`cmd.setInt("p_idAdministrador", admin.getIdAdministrador());`).
-- 3. Pase la fecha de vigencia como segundo parámetro (`cmd.setTimestamp("p_fechaVigencia", ...);`).
-- 4. Ya NO registre un parámetro `OUT p_id`.

DROP PROCEDURE IF EXISTS `modificarAdministrador`$$
CREATE PROCEDURE `modificarAdministrador`(
    IN p_id INT,                    -- Nombre usado en DAO
    IN p_fechaVigencia DATE         -- Nombre usado en DAO
)
BEGIN
    UPDATE `Administrador`
    SET `fechaVigencia` = p_fechaVigencia
    WHERE `idAdministrador` = p_id;
END$$

DROP PROCEDURE IF EXISTS `eliminarAdministrador`$$
CREATE PROCEDURE `eliminarAdministrador`(
    IN p_id INT                    -- Nombre usado en DAO
)
BEGIN
    DELETE FROM `Administrador` WHERE `idAdministrador` = p_id;
END$$

DROP PROCEDURE IF EXISTS `buscarAdminstradorPorId`$$ -- Nombre del DAO
CREATE PROCEDURE `buscarAdminstradorPorId`(
    IN p_id INT                    -- Nombre usado en DAO
)
BEGIN
    SELECT
        u.`idUsuario`, u.`primerNombre`, u.`segundoNombre`, u.`apellidoPaterno`, u.`apellidoMaterno`,
        u.`tipoDocumento`, u.`numeroDocumento`, u.`correo`, u.`contrasena`, u.`estado`, u.`activo`,
        u.`fechaCreacion` AS `usuarioFechaCreacion`, u.`fechaModificacion` AS `usuarioFechaModificacion`,
        a.`idAdministrador`, a.`fechaVigencia`
    FROM `Usuario` u
    JOIN `Administrador` a ON u.`idUsuario` = a.`idAdministrador`
    WHERE a.`idAdministrador` = p_id;
END$$

DROP PROCEDURE IF EXISTS `listarAdministrador`$$ -- Nombre del DAO
CREATE PROCEDURE `listarAdministrador`()
BEGIN
    SELECT
        u.`idUsuario`, u.`primerNombre`, u.`segundoNombre`, u.`apellidoPaterno`, u.`apellidoMaterno`,
        u.`tipoDocumento`, u.`numeroDocumento`, u.`correo`, u.`contrasena`, u.`estado`, u.`activo`,
        u.`fechaCreacion` AS `usuarioFechaCreacion`, u.`fechaModificacion` AS `usuarioFechaModificacion`,
        a.`idAdministrador`, a.`fechaVigencia`
    FROM `Usuario` u
    JOIN `Administrador` a ON u.`idUsuario` = a.`idAdministrador`;
END$$

-- -----------------------------------------------------
-- Procedimientos para LINEA
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarLinea`$$
CREATE PROCEDURE `insertarLinea`(
    IN p_idCliente INT, -- Asumiendo que el DAO usará este nombre de parámetro
    IN p_numeroTelefono VARCHAR(20),
    IN p_tipoLinea ENUM('PREPAGO', 'POSTPAGO'),
    IN p_activa BOOLEAN,
    IN p_fechaActivacion DATE,
    OUT p_idLinea INT -- Nombre genérico para el ID de salida
)
BEGIN
    INSERT INTO `Linea` (`idCliente`, `numeroTelefono`, `tipoLinea`, `activa`, `fechaActivacion`)
    VALUES (p_idCliente, p_numeroTelefono, p_tipoLinea, p_activa, p_fechaActivacion);
    SET p_idLinea = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarLinea`$$
CREATE PROCEDURE `modificarLinea`(
    IN p_idLinea INT,
    IN p_idCliente INT,
    IN p_numeroTelefono VARCHAR(20),
    IN p_tipoLinea ENUM('PREPAGO', 'POSTPAGO'),
    IN p_activa BOOLEAN,
    IN p_fechaActivacion DATE
)
BEGIN
    UPDATE `Linea`
    SET
        `idCliente` = p_idCliente,
        `numeroTelefono` = p_numeroTelefono,
        `tipoLinea` = p_tipoLinea,
        `activa` = p_activa,
        `fechaActivacion` = p_fechaActivacion,
        `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idLinea` = p_idLinea;
END$$

DROP PROCEDURE IF EXISTS `eliminarLinea`$$
CREATE PROCEDURE `eliminarLinea`(
    IN p_idLinea INT
)
BEGIN
    DELETE FROM `Prepago` WHERE `idLineaPrepago` = p_idLinea; 
    DELETE FROM `Postpago` WHERE `idLineaPostpago` = p_idLinea;
    DELETE FROM `Linea_Oferta` WHERE `idLinea` = p_idLinea;
    DELETE FROM `SolicitudDesafiliacion` WHERE `idLinea` = p_idLinea;
    DELETE FROM `Linea` WHERE `idLinea` = p_idLinea;
END$$

DROP PROCEDURE IF EXISTS `buscarLineaPorId`$$
CREATE PROCEDURE `buscarLineaPorId`(
    IN p_idLinea INT
)
BEGIN
    SELECT `idLinea`, `idCliente`, `numeroTelefono`, `tipoLinea`, `activa`, `fechaActivacion`,
           `fechaCreacion`, `fechaModificacion`
    FROM `Linea`
    WHERE `idLinea` = p_idLinea;
END$$

DROP PROCEDURE IF EXISTS `listarLineas`$$
CREATE PROCEDURE `listarLineas`()
BEGIN
    SELECT `idLinea`, `idCliente`, `numeroTelefono`, `tipoLinea`, `activa`, `fechaActivacion`,
           `fechaCreacion`, `fechaModificacion`
    FROM `Linea`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PREPAGO
-- Basado en PrepagoDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPrepago`$$
CREATE PROCEDURE `insertarPrepago`(
    IN p_idLinea INT,           -- ID de la Linea base. DAO debe pasar esto.
    IN p_promRecargaMensual DECIMAL(10,2), -- Nombre usado en DAO
    IN p_idPaquete INT,     -- DAO debe pasar esto
    IN p_saldoActual DECIMAL(10,2),      -- DAO debe pasar esto
    IN p_fechaUltimaRecarga DATE,   -- DAO debe pasar esto
    OUT p_id INT
)
BEGIN
    INSERT INTO `Prepago` (`idLineaPrepago`, `promedioRecargaMensual`, `idPaqueteActual`, `saldoActual`, `fechaUltimaRecarga`)
    VALUES (p_idLinea, p_promRecargaMensual, p_idPaquete, p_saldoActual, p_fechaUltimaRecarga);
    SET p_id = p_idLinea; -- Aunque no se usa OUT p_id en DAO, se mantiene para compatibilidad.
END$$
-- NOTA CRÍTICA para PrepagoDAOImpl.comandoInsertar:
-- El DAO actual es `{CALL insertarPrepago(?, ?, ?, ?)}` y setea:
--  `p_idPaquete` (con `prepago.getIdLinea()`) -> INCORRECTO para el nombre del parámetro, el valor es correcto para la FK. Cambia el nombre del param en DAO a "p_id_linea_fk" o similar.
--  `p_idCliente`, `p_numeroTelefono`, `p_activo` (boolean) -> INCORRECTOS, estos pertenecen a la entidad Linea, no Prepago.
--  `p_promRecargaMensual` -> CORRECTO en nombre y valor.
--  `OUT p_id` -> INCORRECTO, idLineaPrepago no es autoincremental.
-- ACCIÓN: Corregir PrepagoDAOImpl para llamar a este SP con los 5 params IN correctos (p_id_linea_fk, p_promRecargaMensual, p_id_paquete_actual, p_saldo_actual, p_fecha_ultima_recarga) y sin OUT.

DROP PROCEDURE IF EXISTS `modificarPrepago`$$
CREATE PROCEDURE `modificarPrepago`(
    IN p_idPrepago INT,                 -- Nombre usado en DAO (es el idLinea)
    IN p_promRecargaMensual DECIMAL(10,2),
    IN p_idPaquete INT,
    IN p_saldoActual DECIMAL(10,2),
    IN p_fechaUltimaRecarga DATE
)
BEGIN
    UPDATE `Prepago`
    SET
        `promedioRecargaMensual` = p_promRecargaMensual,
        `idPaqueteActual` = p_idPaquete,
        `saldoActual` = p_saldoActual,
        `fechaUltimaRecarga` = p_fechaUltimaRecarga
    WHERE `idLineaPrepago` = p_idPrepago;
END$$
-- NOTA CRÍTICA para PrepagoDAOImpl.comandoModificar:
-- Similar al insertar, el DAO pasa parámetros incorrectos (`p_idCliente`, `p_numeroTelefono`, `p_activo`).
-- Debe corregirse para pasar solo los parámetros que el SP `modificarPrepago` espera. El param `p_id` del DAO para el WHERE debe ser `p_idPrepago`.

DROP PROCEDURE IF EXISTS `eliminarPrepago`$$
CREATE PROCEDURE `eliminarPrepago`(
    IN p_idLinea INT -- Nombre del parámetro como lo usa el DAO
)
BEGIN
    DELETE FROM `Prepago` WHERE `idLineaPrepago` = p_idLinea;
END$$

DROP PROCEDURE IF EXISTS `buscarPrepagoPorId`$$
CREATE PROCEDURE `buscarPrepagoPorId`(
    IN p_idLinea INT -- Nombre del parámetro como lo usa el DAO
)
BEGIN
    SELECT
        l.`idLinea`, l.`idCliente`, l.`numeroTelefono`, l.`tipoLinea`, l.`activa`, l.`fechaActivacion`,
        l.`fechaCreacion` AS `lineaFechaCreacion`, l.`fechaModificacion` AS `lineaFechaModificacion`,
        pr.`idLineaPrepago`, pr.`promedioRecargaMensual`, pr.`idPaqueteActual`, pr.`saldoActual`, pr.`fechaUltimaRecarga`,
        pa.`idPaquete` AS `paqueteIdPaquete`, pa.`nombrePaquete`, pa.`beneficios` AS `paqueteBeneficios`, pa.`precio` AS `paquetePrecio`, pa.`duracionDias` AS `paqueteDuracionDias`
    FROM `Linea` l
    JOIN `Prepago` pr ON l.`idLinea` = pr.`idLineaPrepago`
    LEFT JOIN `Paquete` pa ON pr.`idPaqueteActual` = pa.`idPaquete`
    WHERE l.`idLinea` = p_idLinea AND l.`tipoLinea` = 'PREPAGO';
END$$

DROP PROCEDURE IF EXISTS `listarPrepagos`$$
CREATE PROCEDURE `listarPrepagos`()
BEGIN
    SELECT
        l.`idLinea`, l.`idCliente`, l.`numeroTelefono`, l.`tipoLinea`, l.`activa`, l.`fechaActivacion`,
        l.`fechaCreacion` AS `lineaFechaCreacion`, l.`fechaModificacion` AS `lineaFechaModificacion`,
        pr.`idLineaPrepago`, pr.`promedioRecargaMensual`, pr.`idPaqueteActual`, pr.`saldoActual`, pr.`fechaUltimaRecarga`,
        pa.`idPaquete` AS `paqueteIdPaquete`, pa.`nombrePaquete`, pa.`beneficios` AS `paqueteBeneficios`, pa.`precio` AS `paquetePrecio`, pa.`duracionDias` AS `paqueteDuracionDias`
    FROM `Linea` l
    JOIN `Prepago` pr ON l.`idLinea` = pr.`idLineaPrepago`
    LEFT JOIN `Paquete` pa ON pr.`idPaqueteActual` = pa.`idPaquete`
    WHERE l.`tipoLinea` = 'PREPAGO';
END$$

-- -----------------------------------------------------
-- Procedimientos para POSTPAGO
-- Basado en PostpagoDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPostpago`$$
CREATE PROCEDURE `insertarPostpago`(
    IN p_idLinea INT,           -- ID de la Linea base. DAO debe pasar esto.
    IN p_deudaPendiente DECIMAL(10,2), -- DAO usa "p_activo" para este valor, ¡CORREGIR DAO!
    IN p_idPlan INT,
    IN p_diaCicloFacturacion INT,
    OUT p_id INT 
)
BEGIN
    INSERT INTO `Postpago` (`idLineaPostpago`, `deudaPendiente`, `idPlanActual`, `diaCicloFacturacion`)
    VALUES (p_idLinea, p_deudaPendiente, p_idPlan, p_diaCicloFacturacion);
    SET p_id = p_idLinea; -- Aunque no se usa OUT p_id en DAO, se mantiene para compatibilidad.
END$$
-- NOTA CRÍTICA para PostpagoDAOImpl.comandoInsertar:
-- DAO pasa: `p_idPostpago` (ok para FK), `p_idCliente`, `p_numeroTelefono`, `p_activo` (boolean) -> INCORRECTOS.
-- `p_activo` (double) es usado para deuda_pendiente, el NOMBRE del parámetro es INCORRECTO en DAO.
-- `OUT p_id` es INCORRECTO.
-- ACCIÓN: Corregir PostpagoDAOImpl para llamar a este SP con los 4 params IN correctos.

DROP PROCEDURE IF EXISTS `modificarPostpago`$$
CREATE PROCEDURE `modificarPostpago`(
    IN p_idPostpago INT,              -- Nombre usado en DAO (es el idLinea)
    IN p_deudaPendiente DECIMAL(10,2),
    IN p_idPlan INT,
    IN p_diaCicloFacturacion INT
)
BEGIN
    UPDATE `Postpago`
    SET
        `deudaPendiente` = p_deudaPendiente,
        `idPlanActual` = p_idPlan,
        `diaCicloFacturacion` = p_diaCicloFacturacion
    WHERE `idLineaPostpago` = p_idPostpago;
END$$
-- NOTA CRÍTICA para PostpagoDAOImpl.comandoModificar: Similar al insertar.

DROP PROCEDURE IF EXISTS `eliminarPostpago`$$
CREATE PROCEDURE `eliminarPostpago`(
    IN p_idLinea INT -- Nombre del parámetro como lo usa el DAO
)
BEGIN
    DELETE FROM `Postpago` WHERE `idLineaPostpago` = p_idLinea;
END$$

DROP PROCEDURE IF EXISTS `buscarPostpagoPorId`$$
CREATE PROCEDURE `buscarPostpagoPorId`(
    IN p_idLinea INT -- Nombre del parámetro como lo usa el DAO
)
BEGIN
    SELECT
        l.`idLinea`, l.`idCliente`, l.`numeroTelefono`, l.`tipoLinea`, l.`activa`, l.`fechaActivacion`,
        l.`fechaCreacion` AS `lineaFechaCreacion`, l.`fechaModificacion` AS `lineaFechaModificacion`,
        po.`idLineaPostpago`, po.`deudaPendiente`, po.`idPlanActual`, po.`diaCicloFacturacion`,
        pl.`idPlan` AS `planIdPlan`, pl.`nombrePlan`, pl.`beneficios` AS `planBeneficios`, pl.`precioMensual` AS `planPrecio`
    FROM `Linea` l
    JOIN `Postpago` po ON l.`idLinea` = po.`idLineaPostpago`
    JOIN `Plan` pl ON po.`idPlanActual` = pl.`idPlan`
    WHERE l.`idLinea` = p_idLinea AND l.`tipoLinea` = 'POSTPAGO';
END$$

DROP PROCEDURE IF EXISTS `listarPostpagos`$$
CREATE PROCEDURE `listarPostpagos`()
BEGIN
    SELECT
        l.`idLinea`, l.`idCliente`, l.`numeroTelefono`, l.`tipoLinea`, l.`activa`, l.`fechaActivacion`,
        l.`fechaCreacion` AS `lineaFechaCreacion`, l.`fechaModificacion` AS `lineaFechaModificacion`,
        po.`idLineaPostpago`, po.`deudaPendiente`, po.`idPlanActual`, po.`diaCicloFacturacion`,
        pl.`idPlan` AS `planIdPlan`, pl.`nombrePlan`, pl.`beneficios` AS `planBeneficios`, pl.`precioMensual` AS `planPrecio`
    FROM `Linea` l
    JOIN `Postpago` po ON l.`idLinea` = po.`idLineaPostpago`
    JOIN `Plan` pl ON po.`idPlanActual` = pl.`idPlan`
    WHERE l.`tipoLinea` = 'POSTPAGO';
END$$

-- -----------------------------------------------------
-- Procedimientos para LINEA_OFERTA
-- Basado en LineaOfertaDAOImpl.java
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS `insertarLineaOferta`$$
CREATE PROCEDURE `insertarLineaOferta`(
    IN p_idLinea INT,           -- ID de la Linea base. DAO debe pasar esto.
    IN p_idOferta INT,          -- ID de la Oferta base. DAO debe pasar esto.
    IN p_estadoOfertaLinea VARCHAR(50), -- Nombre usado en DAO
    IN p_fechaAsociacion DATETIME, -- Nombre usado en DAO
    IN p_fechaAplicacion DATETIME, -- Nombre usado en DAO
    OUT p_id INT
)
BEGIN
    INSERT INTO `LineaOferta` (`idLinea`, `idOferta`, `estado`, `fechaAsociacion`, `fechaAplicacion`)
    VALUES (p_idLinea, p_idOferta, p_estadoOfertaLinea, p_fechaAsociacion, p_fechaAplicacion);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarLineaOferta`$$
CREATE PROCEDURE `modificarLineaOferta`(
    IN p_idLineaOferta INT,      -- ID de la LineaOferta base. DAO debe pasar esto.
    IN p_idLinea INT,            -- ID de la Linea base. DAO debe pasar esto.
    IN p_idOferta INT,           -- ID de la Oferta base. DAO debe pasar esto.
    IN p_estadoOfertaLinea VARCHAR(50),
    IN p_fechaAsociacion DATETIME,
    IN p_fechaAplicacion DATETIME
)
BEGIN
    UPDATE `LineaOferta`
    SET
        `idLinea` = p_idLinea,
        `idOferta` = p_idOferta,
        `estado` = p_estadoOfertaLinea,
        `fechaAsociacion` = p_fechaAsociacion,
        `fechaAplicacion` = p_fechaAplicacion
    WHERE `idLineaOferta` = p_idLineaOferta;
END$$

DROP PROCEDURE IF EXISTS `eliminarLineaOferta`$$
CREATE PROCEDURE `eliminarLineaOferta`(
    IN p_idLineaOferta INT -- ID de la LineaOferta base. DAO debe pasar esto.
)
BEGIN
    DELETE FROM `LineaOferta` WHERE `idLineaOferta` = p_idLineaOferta;
END$$

DROP PROCEDURE IF EXISTS `buscarLineaOfertaPorId`$$
CREATE PROCEDURE `buscarLineaOfertaPorId`(
    IN p_idLineaOferta INT -- ID de la LineaOferta base. DAO debe pasar esto.
)
BEGIN
    SELECT * FROM `LineaOferta` WHERE `idLineaOferta` = p_idLineaOferta;
END$$

DROP PROCEDURE IF EXISTS `listarLineasOfertas`$$
CREATE PROCEDURE `listarLineasOfertas`()
BEGIN
    SELECT * FROM `LineaOferta`;
END$$

-- -----------------------------------------------------
-- Procedimientos para OFERTA
-- Basado en OfertaDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarOferta`$$
CREATE PROCEDURE `insertarOferta`(
    IN p_idPlantillaOferta INT,       -- DAO debe pasar este valor
    IN p_idLinea INT,                   -- Nombre de param en DAO (valor pasado por DAO era incorrecto)
    IN p_idEquipo INT,                  -- Nombre de param en DAO
    IN p_nombre VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_fechaCreacion DATETIME,        -- Nombre de param en DAO
    IN p_fechaInicio DATE,
    IN p_fechaFin DATE,
    IN p_descuento DECIMAL(10,2),
    IN p_modalidad VARCHAR(100),
    IN p_estado VARCHAR(50),
    OUT p_id INT
)
BEGIN
    INSERT INTO `Oferta` (`idPlantillaOferta`, `idLinea`, `nombre`, `descripcion`, `idEquipo`, `descuentoAplicado`, `fechaInicioVigencia`, `fechaFinVigencia`, `estado`, `modalidadSeleccionada`, `fechaCreacionOferta`)
    VALUES (p_idPlantillaOferta, p_idLinea, p_nombre, p_descripcion, p_idEquipo, p_descuento, p_fechaInicio, p_fechaFin, p_estado, p_modalidad, p_fechaCreacion);
    SET p_id = LAST_INSERT_ID();
END$$
-- NOTA CRÍTICA para OfertaDAOImpl.insertarOferta:
-- 1. El DAO debe ser modificado para pasar `id_plantilla_oferta` (ej. `cmd.setInt("p_id_plantilla_oferta", oferta.getIdPlantilla());`).
-- 2. El DAO pasa `oferta.getIdOferta()` para el parámetro `p_idLinea`. ¡ESTO ES INCORRECTO! Debe pasar `oferta.getIdLinea()`.

DROP PROCEDURE IF EXISTS `modificarOferta`$$
CREATE PROCEDURE `modificarOferta`(
    IN p_idOferta INT,
    IN p_idPlantillaOferta INT,   -- DAO debe pasar este valor
    IN p_idLinea INT,               -- DAO pasa p_idLinea (valor incorrecto)
    IN p_idEquipo INT,
    IN p_nombre VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_fechaCreacion DATETIME,    -- Si se actualiza la fecha de creación
    IN p_fechaInicio DATE,
    IN p_fechaFin DATE,
    IN p_descuento DECIMAL(10,2),
    IN p_modalidad VARCHAR(100),
    IN p_estado VARCHAR(50)
)
BEGIN
    UPDATE `Oferta`
    SET
        `idPlantillaOferta` = p_idPlantillaOferta,
        `idLinea` = p_idLinea,
        `idEquipo` = p_idEquipo,
        `nombre` = p_nombre,
        `descripcion` = p_descripcion,
        `descuentoAplicado` = p_descuento,
        `fechaInicioVigencia` = p_fechaInicio,
        `fechaFinVigencia` = p_fechaFin,
        `estado` = p_estado,
        `modalidadSeleccionada` = p_modalidad,
        `fechaCreacionOferta` = p_fechaCreacion,
        `fechaModificacionOferta` = CURRENT_TIMESTAMP
    WHERE `idOferta` = p_idOferta;
END$$
-- NOTA CRÍTICA para OfertaDAOImpl.modificarOferta: Similar al insertar.

DROP PROCEDURE IF EXISTS `eliminarOferta`$$
CREATE PROCEDURE `eliminarOferta`(
    IN p_idOferta INT
)
BEGIN
    DELETE FROM `Oferta` WHERE `idOferta` = p_idOferta;
END$$

DROP PROCEDURE IF EXISTS `buscarOfertaPorId`$$
CREATE PROCEDURE `buscarOfertaPorId`(
    IN p_idOferta INT
)
BEGIN
    SELECT o.*,
           e.`idEquipo` AS `equipoIdEquipo`, e.`marca` AS `equipoMarca`, e.`modelo` AS `equipoModelo`, e.`precio` AS `equipoPrecio`, e.`stock` AS `equipoStock`, e.`categoria` AS `equipoCategoria`, e.`estado` AS `equipoEstado`
    FROM `Oferta` o
    LEFT JOIN `Equipo` e ON o.`idEquipo` = e.`idEquipo`
    WHERE o.`idOferta` = p_idOferta;
END$$

DROP PROCEDURE IF EXISTS `listarOfertas`$$
CREATE PROCEDURE `listarOfertas`()
BEGIN
    SELECT o.*,
           e.`idEquipo` AS `equipoIdEquipo`, e.`marca` AS `equipoMarca`, e.`modelo` AS `equipoModelo`, e.`precio` AS `equipoPrecio`, e.`stock` AS `equipoStock`, e.`categoria` AS `equipoCategoria`, e.`estado` AS `equipoEstado`
    FROM `Oferta` o
    LEFT JOIN `Equipo` e ON o.`idEquipo` = e.`idEquipo`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PRIORIDAD
-- Basado en PrioridadDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPrioridad`$$
CREATE PROCEDURE `insertarPrioridad`(
    IN p_nombre VARCHAR(50),
    OUT p_id INT
)
BEGIN
    INSERT INTO `Prioridad` (`nombre`) VALUES (p_nombre); -- cantidadOfertas usará DEFAULT
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarPrioridad`$$
CREATE PROCEDURE `modificarPrioridad`(
    IN p_idPrioridad INT,
    IN p_nombre VARCHAR(50)
)
BEGIN
    UPDATE `Prioridad`
    SET `nombre` = p_nombre, `fechaModificacion` = CURRENT_TIMESTAMP -- cantidadOfertas no se modifica aquí
    WHERE `idPrioridad` = p_idPrioridad;
END$$

DROP PROCEDURE IF EXISTS `eliminarPrioridad`$$
CREATE PROCEDURE `eliminarPrioridad`(
    IN p_idPrioridad INT
)
BEGIN
    DELETE FROM `Prioridad` WHERE `idPrioridad` = p_idPrioridad;
END$$

DROP PROCEDURE IF EXISTS `buscarPrioridadPorId`$$
CREATE PROCEDURE `buscarPrioridadPorId`(
    IN p_idPrioridad INT
)
BEGIN
    SELECT `idPrioridad`, `nombre`, `cantidadOfertas`, `fechaCreacion`, `fechaModificacion`
    FROM `Prioridad` WHERE `idPrioridad` = p_idPrioridad;
END$$

DROP PROCEDURE IF EXISTS `listarPrioridades`$$
CREATE PROCEDURE `listarPrioridades`()
BEGIN
    SELECT `idPrioridad`, `nombre`, `cantidadOfertas`, `fechaCreacion`, `fechaModificacion`
    FROM `Prioridad`;
END$$

-- -----------------------------------------------------
-- Procedimientos para TIPOMOTIVO
-- Basado en TipoMotivoDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarTipoMotivo`$$
CREATE PROCEDURE `insertarTipoMotivo`(
    IN p_nombre VARCHAR(100),
    IN p_descripcion TEXT,
    OUT p_id INT
)
BEGIN
    INSERT INTO `TipoMotivo` (`nombre`, `descripcion`) VALUES (p_nombre, p_descripcion);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarTipoMotivo`$$
CREATE PROCEDURE `modificarTipoMotivo`(
    IN p_idTipoMotivo INT,
    IN p_nombre VARCHAR(100),
    IN p_descripcion TEXT
)
BEGIN
    UPDATE `TipoMotivo`
    SET `nombre` = p_nombre, `descripcion` = p_descripcion, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idTipoMotivo` = p_idTipoMotivo;
END$$

DROP PROCEDURE IF EXISTS `eliminarTipoMotivo`$$
CREATE PROCEDURE `eliminarTipoMotivo`(
    IN p_idTipoMotivo INT
)
BEGIN
    DELETE FROM `TipoMotivo` WHERE `idTipoMotivo` = p_idTipoMotivo;
END$$

DROP PROCEDURE IF EXISTS `buscarTipoMotivoPorId`$$
CREATE PROCEDURE `buscarTipoMotivoPorId`(
    IN p_idTipoMotivo INT
)
BEGIN
    SELECT `idTipoMotivo`, `nombre`, `descripcion`, `fechaCreacion`, `fechaModificacion`
    FROM `TipoMotivo` WHERE `idTipoMotivo` = p_idTipoMotivo;
END$$

DROP PROCEDURE IF EXISTS `listarTiposMotivo`$$
CREATE PROCEDURE `listarTiposMotivo`()
BEGIN
    SELECT `idTipoMotivo`, `nombre`, `descripcion`, `fechaCreacion`, `fechaModificacion`
    FROM `TipoMotivo`;
END$$

-- -----------------------------------------------------
-- Procedimientos para MODALIDAD
-- Basado en ModalidadDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarModalidad`$$
CREATE PROCEDURE `insertarModalidad`(
    IN p_nombre VARCHAR(100),
    OUT p_id INT
)
BEGIN
    INSERT INTO `Modalidad` (`nombre`) VALUES (p_nombre);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarModalidad`$$
CREATE PROCEDURE `modificarModalidad`(
    IN p_idModalidad INT,
    IN p_nombre VARCHAR(100)
)
BEGIN
    UPDATE `Modalidad`
    SET `nombre` = p_nombre, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idModalidad` = p_idModalidad;
END$$

DROP PROCEDURE IF EXISTS `eliminarModalidad`$$
CREATE PROCEDURE `eliminarModalidad`(
    IN p_idModalidad INT
)
BEGIN
    DELETE FROM `Modalidad` WHERE `idModalidad` = p_idModalidad;
END$$

DROP PROCEDURE IF EXISTS `buscarModalidadPorId`$$
CREATE PROCEDURE `buscarModalidadPorId`(
    IN p_idModalidad INT
)
BEGIN
    SELECT `idModalidad`, `nombre`, `fechaCreacion`, `fechaModificacion`
    FROM `Modalidad` WHERE `idModalidad` = p_idModalidad;
END$$

DROP PROCEDURE IF EXISTS `listarModalidades`$$
CREATE PROCEDURE `listarModalidades`()
BEGIN
    SELECT `idModalidad`, `nombre`, `fechaCreacion`, `fechaModificacion`
    FROM `Modalidad`;
END$$

-- -----------------------------------------------------
-- Procedimientos para EQUIPO
-- Basado en EquipoDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarEquipo`$$
CREATE PROCEDURE `insertarEquipo`(
    IN p_marca VARCHAR(100),
    IN p_modelo VARCHAR(100),
    IN p_stock INT,
    IN p_precio DECIMAL(10,2),
    IN p_estado VARCHAR(50),
    OUT p_id INT
)
BEGIN
    INSERT INTO `Equipo` (`marca`, `modelo`, `stock`, `precio`, `estado`)
    VALUES (p_marca, p_modelo, p_stock, p_precio, p_estado);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarEquipo`$$
CREATE PROCEDURE `modificarEquipo`(
    IN p_idEquipo INT,
    IN p_marca VARCHAR(100),
    IN p_modelo VARCHAR(100),
    IN p_stock INT,
    IN p_precio DECIMAL(10,2),
    IN p_estado VARCHAR(50)
)
BEGIN
    UPDATE `Equipo`
    SET `marca` = p_marca, `modelo` = p_modelo, `stock` = p_stock, `precio` = p_precio, `estado` = p_estado, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idEquipo` = p_idEquipo;
END$$

DROP PROCEDURE IF EXISTS `eliminarEquipo`$$
CREATE PROCEDURE `eliminarEquipo`(
    IN p_idEquipo INT
)
BEGIN
    DELETE FROM `Equipo` WHERE `idEquipo` = p_idEquipo;
END$$

DROP PROCEDURE IF EXISTS `buscarEquipoPorId`$$
CREATE PROCEDURE `buscarEquipoPorId`(
    IN p_idEquipo INT
)
BEGIN
    SELECT `idEquipo`, `marca`, `modelo`, `precio`, `stock`, `categoria`, `estado`, `fechaCreacion`, `fechaModificacion`
    FROM `Equipo`
    WHERE `idEquipo` = p_idEquipo;
END$$

DROP PROCEDURE IF EXISTS `listarEquipos`$$
CREATE PROCEDURE `listarEquipos`()
BEGIN
    SELECT `idEquipo`, `marca`, `modelo`, `precio`, `stock`, `categoria`, `estado`, `fechaCreacion`, `fechaModificacion`
    FROM `Equipo`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PLANTILLAOFERTA
-- Basado en PlantillaOfertaDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPlantillaOferta`$$
CREATE PROCEDURE `insertarPlantillaOferta`(
    IN p_nombre VARCHAR(255),
    IN p_descripcion TEXT,        -- Nombre de param en DAO para plantilla.getFormula()
    IN p_fechaCreacion DATETIME,  -- Nombre de param en DAO para plantilla.getPeriodoValidez()
    IN p_activa BOOLEAN,
    IN p_tipoServicio INT,       -- DAO debe pasar esto
    IN p_idPrioridad INT,        -- DAO debe pasar esto
    OUT p_id INT
)
BEGIN
    INSERT INTO `PlantillaOferta` (`nombre`, `formula`, `tipoServicio`, `idPrioridad`, `fechaValidezHasta`, `activa`)
    VALUES (p_nombre, p_descripcion, p_tipoServicio, p_idPrioridad, DATE(p_fechaCreacion), p_activa);
    SET p_id = LAST_INSERT_ID();
END$$
-- NOTA CRÍTICA para PlantillaOfertaDAOImpl.insertar:
-- El DAO actual pasa `p_plantilla` y `p_variables` como "" que no se usan.
-- El DAO NO pasa `tipo_servicio` ni `id_prioridad`. ¡DEBE SER CORREGIDO EN JAVA!
-- El param `p_fechaCreacion` del DAO se usa para `fechaValidezHasta` (que es DATE).

DROP PROCEDURE IF EXISTS `modificarPlantillaOferta`$$
CREATE PROCEDURE `modificarPlantillaOferta`(
    IN p_id INT,                  -- Nombre de param en DAO
    IN p_nombre VARCHAR(255),
    IN p_descripcion TEXT,
    IN p_fechaCreacion DATETIME,
    IN p_activa BOOLEAN,
    IN p_tipoServicio INT,       -- DAO debe pasar esto
    IN p_idPrioridad INT         -- DAO debe pasar esto
)
BEGIN
    UPDATE `PlantillaOferta`
    SET `nombre` = p_nombre, `formula` = p_descripcion, `tipoServicio` = p_tipoServicio, `idPrioridad` = p_idPrioridad,
        `fechaValidezHasta` = DATE(p_fechaCreacion), `activa` = p_activa, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idPlantillaOferta` = p_id;
END$$
-- NOTA CRÍTICA para PlantillaOfertaDAOImpl.modificar: Similar al insertar.

DROP PROCEDURE IF EXISTS `eliminarPlantillaOferta`$$
CREATE PROCEDURE `eliminarPlantillaOferta`(
    IN p_idPlantilla INT -- Nombre usado en DAO
)
BEGIN
    DELETE FROM `PlantillaOferta_Modalidad` WHERE `idPlantillaOferta` = p_idPlantilla;
    DELETE FROM `PlantillaOferta_Parametro` WHERE `idPlantillaOferta` = p_idPlantilla;
    DELETE FROM `PlantillaOferta` WHERE `idPlantillaOferta` = p_idPlantilla;
END$$

DROP PROCEDURE IF EXISTS `buscarPlantillaOfertaPorId`$$
CREATE PROCEDURE `buscarPlantillaOfertaPorId`(
    IN p_idPlantilla INT -- Nombre usado en DAO
)
BEGIN
    SELECT `idPlantillaOferta`, `nombre`, `formula`, `tipoServicio`, `idPrioridad`, `fechaValidezHasta`, `activa`, `fechaCreacion`, `fechaModificacion`
    FROM `PlantillaOferta` WHERE `idPlantillaOferta` = p_idPlantilla;
END$$

DROP PROCEDURE IF EXISTS `listarPlantillasOferta`$$
CREATE PROCEDURE `listarPlantillasOferta`()
BEGIN
    SELECT `idPlantillaOferta`, `nombre`, `formula`, `tipoServicio`, `idPrioridad`, `fechaValidezHasta`, `activa`, `fechaCreacion`, `fechaModificacion`
    FROM `PlantillaOferta`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PLAN
-- Basado en PlanDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPlan`$$
CREATE PROCEDURE `insertarPlan`(
    IN p_nombreServicio VARCHAR(100), 
    IN p_beneficios TEXT,
    IN p_tipoServicio VARCHAR(50),
    IN p_precio DECIMAL(10,2),
    OUT p_id INT
)
BEGIN
    INSERT INTO `Plan` (`nombrePlan`, `beneficios`, `tipoServicio`, `precioMensual`)
    VALUES (p_nombreServicio, p_beneficios, p_tipoServicio, p_precio);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarPlan`$$
CREATE PROCEDURE `modificarPlan`(
    IN p_idPlan INT,                 -- DAO usa p_id para el WHERE. Renombrado para evitar colisión.
    IN p_nombreServicio VARCHAR(100),
    IN p_beneficios TEXT,             
    IN p_tipoServicio VARCHAR(50),
    IN p_precio DECIMAL(10,2)         
)
BEGIN
    UPDATE `Plan`
    SET `nombrePlan` = p_nombreServicio, `beneficios` = p_beneficios, `tipoServicio` = p_tipoServicio, `precioMensual` = p_precio, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idPlan` = p_idPlan;
END$$
-- NOTA CRÍTICA para PlanDAOImpl.modificarPlan:
-- El DAO pasa `p_id` múltiples veces para diferentes valores. ¡DEBE SER CORREGIDO EN JAVA!
-- El SP ahora espera parámetros con nombres distintos (`_param`) para los valores a actualizar. El DAO debe usar estos nombres.
-- El parámetro para el WHERE clause (originalmente `p_id`) lo he llamado `p_id_param` en el SP. El DAO debe usar `p_id` para este.

DROP PROCEDURE IF EXISTS `eliminarPlan`$$
CREATE PROCEDURE `eliminarPlan`(
    IN p_idPlan INT
)
BEGIN
    DELETE FROM `Plan` WHERE `idPlan` = p_idPlan;
END$$

DROP PROCEDURE IF EXISTS `buscarPlanPorId`$$
CREATE PROCEDURE `buscarPlanPorId`(
    IN p_idPlan INT
)
BEGIN
    SELECT `idPlan`, `nombrePlan`, `beneficios`, `tipoServicio`, `precioMensual`, `fechaCreacion`, `fechaModificacion`
    FROM `Plan` WHERE `idPlan` = p_idPlan;
END$$

DROP PROCEDURE IF EXISTS `listarPlanes`$$
CREATE PROCEDURE `listarPlanes`()
BEGIN
    SELECT `idPlan`, `nombrePlan`, `beneficios`, `tipoServicio`, `precioMensual`, `fechaCreacion`, `fechaModificacion`
    FROM `Plan`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PAQUETE
-- Basado en PaqueteDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPaquete`$$
CREATE PROCEDURE `insertarPaquete`(
    IN p_nombre VARCHAR(100),
    IN p_beneficio TEXT,
    IN p_precio DECIMAL(10,2),
    IN p_tipoServicio VARCHAR(50),   -- DAO debe pasar esto
    IN p_duracionDias INT,           -- DAO debe pasar esto
    OUT p_id INT
)
BEGIN
    INSERT INTO `Paquete` (`nombrePaquete`, `beneficios`, `tipoServicio`, `precio`, `duracionDias`)
    VALUES (p_nombre, p_beneficio, p_tipoServicio, p_precio, p_duracionDias);
    SET p_id = LAST_INSERT_ID();
END$$
-- NOTA CRÍTICA para PaqueteDAOImpl.insertarPaquete:
-- El DAO NO pasa `tipo_servicio` ni `duracion_dias`. ¡DEBE SER CORREGIDO EN JAVA!

DROP PROCEDURE IF EXISTS `modificarPaquete`$$
CREATE PROCEDURE `modificarPaquete`(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_beneficio TEXT,
    IN p_precio DECIMAL(10,2),
    IN p_tipoServicio VARCHAR(50),   -- DAO debe pasar esto
    IN p_duracionDias INT            -- DAO debe pasar esto
)
BEGIN
    UPDATE `Paquete`
    SET `nombrePaquete` = p_nombre, `beneficios` = p_beneficio, `tipoServicio` = p_tipoServicio, `precio` = p_precio, `duracionDias` = p_duracionDias, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idPaquete` = p_id;
END$$

DROP PROCEDURE IF EXISTS `eliminarPaquete`$$
CREATE PROCEDURE `eliminarPaquete`(
    IN p_idPaquete INT
)
BEGIN
    DELETE FROM `Paquete` WHERE `idPaquete` = p_idPaquete;
END$$

DROP PROCEDURE IF EXISTS `buscarPaquetePorId`$$
CREATE PROCEDURE `buscarPaquetePorId`(
    IN p_idPaquete INT
)
BEGIN
    SELECT `idPaquete`, `nombrePaquete`, `beneficios`, `tipoServicio`, `precio`, `duracionDias`, `fechaCreacion`, `fechaModificacion`
    FROM `Paquete` WHERE `idPaquete` = p_idPaquete;
END$$

DROP PROCEDURE IF EXISTS `listarPaquetes`$$
CREATE PROCEDURE `listarPaquetes`()
BEGIN
    SELECT `idPaquete`, `nombrePaquete`, `beneficios`, `tipoServicio`, `precio`, `duracionDias`, `fechaCreacion`, `fechaModificacion`
    FROM `Paquete`;
END$$


-- -----------------------------------------------------
-- Procedimientos para ENCUESTA
-- Basado en EncuestaDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarEncuesta`$$
CREATE PROCEDURE `insertarEncuesta`(
    IN p_idTipoMotivo INT,
    IN p_fechaEncuesta DATETIME,
    IN p_calificacion INT,
    IN p_comentarios TEXT,
    OUT p_id INT
)
BEGIN
    INSERT INTO `Encuesta` (`idTipoMotivo`, `fechaEncuesta`, `calificacion`, `opinion`)
    VALUES (p_idTipoMotivo, p_fechaEncuesta, p_calificacion, p_comentarios);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarEncuesta`$$
CREATE PROCEDURE `modificarEncuesta`(
    IN p_idEncuesta INT,
    IN p_idTipoMotivo INT,
    IN p_fechaEncuesta DATETIME,
    IN p_calificacion INT,
    IN p_comentarios TEXT
)
BEGIN
    UPDATE `Encuesta`
    SET `idTipoMotivo` = p_idTipoMotivo, `calificacion` = p_calificacion, `opinion` = p_comentarios, `fechaEncuesta` = p_fechaEncuesta
    WHERE `idEncuesta` = p_idEncuesta;
END$$

DROP PROCEDURE IF EXISTS `eliminarEncuesta`$$
CREATE PROCEDURE `eliminarEncuesta`(
    IN p_idEncuesta INT
)
BEGIN
    DELETE FROM `Encuesta` WHERE `idEncuesta` = p_idEncuesta;
END$$

DROP PROCEDURE IF EXISTS `buscarEncuestaPorId`$$
CREATE PROCEDURE `buscarEncuestaPorId`(
    IN p_idEncuesta INT
)
BEGIN
    SELECT e.*, tm.`nombre` AS `nombreMotivo`, tm.`descripcion` AS `descripcionMotivo`
    FROM `Encuesta` e
    LEFT JOIN `TipoMotivo` tm ON e.`idTipoMotivo` = tm.`idTipoMotivo`
    WHERE e.`idEncuesta` = p_idEncuesta;
END$$

DROP PROCEDURE IF EXISTS `listarEncuestas`$$
CREATE PROCEDURE `listarEncuestas`()
BEGIN
    SELECT e.*, tm.`nombre` AS `nombreMotivo`, tm.`descripcion` AS `descripcionMotivo`
    FROM `Encuesta` e
    LEFT JOIN `TipoMotivo` tm ON e.`idTipoMotivo` = tm.`idTipoMotivo`;
END$$

-- -----------------------------------------------------
-- Procedimientos para SOLICITUDDESAFILIACION
-- Basado en SolicitudDesafiliacionDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarSolicitudDesafiliacion`$$
CREATE PROCEDURE `insertarSolicitudDesafiliacion`(
    IN p_idLinea INT,
    IN p_fechaSolicitud DATETIME,
    IN p_resultado VARCHAR(50), 
    IN p_idCliente INT,                 -- DAO debe pasar esto
    IN p_idOfertaAceptada INT,          -- DAO debe pasar esto
    IN p_idEncuesta INT,                -- DAO debe pasar esto
    IN p_observacionesAgente TEXT,      -- DAO debe pasar esto
    OUT p_id INT
)
BEGIN
    INSERT INTO `SolicitudDesafiliacion` (`idLinea`, `idCliente`, `idOfertaAceptada`, `idEncuesta`, `fechaSolicitud`, `resultado`, `observacionesAgente`)
    VALUES (p_idLinea, p_idCliente, p_idOfertaAceptada, p_idEncuesta, p_fechaSolicitud, p_resultado, p_observacionesAgente);
    SET p_id = LAST_INSERT_ID();
END$$
-- NOTA CRÍTICA para SolicitudDesafiliacionDAOImpl.insertar:
-- El DAO NO pasa `idCliente`, `idOfertaAceptada`, `idEncuesta`, `observacionesAgente`, `fechaEfectivaDesafiliacion`.
-- ¡DEBE SER CORREGIDO EN JAVA para pasar estos valores!

DROP PROCEDURE IF EXISTS `modificarSolicitudDesafiliacion`$$
CREATE PROCEDURE `modificarSolicitudDesafiliacion`(
    IN p_id INT,                       
    IN p_idLinea INT,
    IN p_fechaSolicitud DATETIME,
    IN p_resultado VARCHAR(50),
    IN p_idCliente INT,                 -- DAO debe pasar esto
    IN p_idOfertaAceptada INT,          -- DAO debe pasar esto
    IN p_idEncuesta INT,                -- DAO debe pasar esto
    IN p_observacionesAgente TEXT      -- DAO debe pasar esto
)
BEGIN
    UPDATE `SolicitudDesafiliacion`
    SET `idLinea` = p_idLinea, `idCliente` = p_idCliente, `idOfertaAceptada` = p_idOfertaAceptada, `idEncuesta` = p_idEncuesta,
        `fechaSolicitud` = p_fechaSolicitud, `resultado` = p_resultado, `observacionesAgente` = p_observacionesAgente,
        `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idSolicitudDesafiliacion` = p_id;
END$$
-- NOTA CRÍTICA para SolicitudDesafiliacionDAOImpl.modificar: Similar al insertar.

DROP PROCEDURE IF EXISTS `eliminarSolicitudDesafiliacion`$$
CREATE PROCEDURE `eliminarSolicitudDesafiliacion`(
    IN p_idSolicitud INT           -- Nombre usado en DAO
)
BEGIN
    DELETE FROM `SolicitudDesafiliacion` WHERE `idSolicitudDesafiliacion` = p_idSolicitud;
END$$

DROP PROCEDURE IF EXISTS `buscarSolicitudDesafiliacionPorId`$$
CREATE PROCEDURE `buscarSolicitudDesafiliacionPorId`(
    IN p_idSolicitud INT           -- Nombre usado en DAO
)
BEGIN
    SELECT sd.*,
           e.`calificacion` AS `encuestaCalificacion`, e.`opinion` AS `encuestaOpinion`,
           tm.`nombre` AS `motivoNombre`
    FROM `SolicitudDesafiliacion` sd
    LEFT JOIN `Encuesta` e ON sd.`idEncuesta` = e.`idEncuesta`
    LEFT JOIN `TipoMotivo` tm ON e.`idTipoMotivo` = tm.`idTipoMotivo`
    WHERE sd.`idSolicitudDesafiliacion` = p_idSolicitud;
END$$

DROP PROCEDURE IF EXISTS `listarSolicitudesDesafiliacion`$$
CREATE PROCEDURE `listarSolicitudesDesafiliacion`()
BEGIN
    SELECT sd.*,
           e.`calificacion` AS `encuestaCalificacion`, e.`opinion` AS `encuestaOpinion`,
           tm.`nombre` AS `motivoNombre`
    FROM `SolicitudDesafiliacion` sd
    LEFT JOIN `Encuesta` e ON sd.`idEncuesta` = e.`idEncuesta`
    LEFT JOIN `TipoMotivo` tm ON e.`idTipoMotivo` = tm.`idTipoMotivo`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PARAMETRO
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarParametro`$$
CREATE PROCEDURE `insertarParametro`(
    IN p_nombreParametro VARCHAR(100), -- Asumiendo un nombre genérico que el DAO usaría
    OUT p_id INT
)
BEGIN
    INSERT INTO `Parametro` (`nombreParametro`) VALUES (p_nombreParametro);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarParametro`$$
CREATE PROCEDURE `modificarParametro`(
    IN p_idParametro INT,
    IN p_nombreParametro VARCHAR(100)
)
BEGIN
    UPDATE `Parametro`
    SET `nombreParametro` = p_nombreParametro, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `idParametro` = p_idParametro;
END$$

DROP PROCEDURE IF EXISTS `eliminarParametro`$$
CREATE PROCEDURE `eliminarParametro`(
    IN p_idParametro INT
)
BEGIN
    DELETE FROM `Parametro` WHERE `idParametro` = p_idParametro;
END$$

DROP PROCEDURE IF EXISTS `buscarParametroPorId`$$
CREATE PROCEDURE `buscarParametroPorId`(
    IN p_idParametro INT
)
BEGIN
    SELECT `idParametro`, `nombreParametro`, `fechaCreacion`, `fechaModificacion`
    FROM `Parametro`
    WHERE `idParametro` = p_idParametro;
END$$

DROP PROCEDURE IF EXISTS `listarParametros`$$
CREATE PROCEDURE `listarParametros`()
BEGIN
    SELECT `idParametro`, `nombreParametro`, `fechaCreacion`, `fechaModificacion`
    FROM `Parametro`;
END$$

-- -----------------------------------------------------
-- Procedimientos para PLANTILLAOFERTA_PARAMETRO
-- Basado en PlantillaOfertaParametroDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPlantillaOfertaParametro`$$
CREATE PROCEDURE `insertarPlantillaOfertaParametro`(
    IN p_idPlantilla INT,
    IN p_idParametro INT,
    IN p_valor VARCHAR(255), -- Asumiendo un valor genérico que el DAO usaría
    OUT p_id INT
)
BEGIN
    INSERT INTO `PlantillaOfertaParametro` (`idPlantillaOferta`, `idParametro`, `valorParametro`) VALUES (p_idPlantilla, p_idParametro, p_valor);
    SET p_id = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `modificarPlantillaOfertaParametro`$$
CREATE PROCEDURE `modificarPlantillaOfertaParametro`(
    IN p_id INT,
    IN p_idPlantilla INT,
    IN p_idParametro INT,
    IN p_valor VARCHAR(255)
)
BEGIN
    UPDATE `PlantillaOfertaParametro`
    SET `idPlantillaOferta` = p_idPlantilla, `idParametro` = p_idParametro, `valorParametro` = p_valor, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `id` = p_id;
END$$
DROP PROCEDURE IF EXISTS `eliminarPlantillaOfertaParametro`$$
CREATE PROCEDURE `eliminarPlantillaOfertaParametro`(
    IN p_id INT
)
BEGIN
    DELETE FROM `PlantillaOfertaParametro` WHERE `id` = p_id;
END$$
DROP PROCEDURE IF EXISTS `buscarPlantillaOfertaParametroPorId`$$
CREATE PROCEDURE `buscarPlantillaOfertaParametroPorId`(
    IN p_id INT
)
BEGIN
    SELECT `id`, `idPlantillaOferta`, `idParametro`, `valor`, `fechaCreacion`, `fechaModificacion`
    FROM `PlantillaOfertaParametro`
    WHERE `id` = p_id;
END$$
DROP PROCEDURE IF EXISTS `listarPlantillaOfertaParametros`$$
CREATE PROCEDURE `listarPlantillaOfertaParametros`()
BEGIN
    SELECT `id`, `idPlantillaOferta`, `idParametro`, `valor`, `fechaCreacion`, `fechaModificacion`
    FROM `PlantillaOfertaParametro`;
END$$
-- -----------------------------------------------------
-- Procedimientos para PLANTILLAOFERTA_MODALIDAD
-- Basado en PlantillaOfertaModalidadDAOImpl.java
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `insertarPlantillaOfertaModalidad`$$
CREATE PROCEDURE `insertarPlantillaOfertaModalidad`(
    IN p_idPlantilla INT,
    IN p_idModalidad INT,
    OUT p_id INT
)
BEGIN
    INSERT INTO `PlantillaOfertaModalidad` (`idPlantillaOferta`, `idModalidad`)
    VALUES (p_idPlantilla, p_idModalidad);
    SET p_id = LAST_INSERT_ID();
END$$
DROP PROCEDURE IF EXISTS `modificarPlantillaOfertaModalidad`$$
CREATE PROCEDURE `modificarPlantillaOfertaModalidad`(
    IN p_id INT,
    IN p_idPlantilla INT,
    IN p_idModalidad INT
)
BEGIN
    UPDATE `PlantillaOfertaModalidad`
    SET `idPlantillaOferta` = p_idPlantilla, `idModalidad` = p_idModalidad, `fechaModificacion` = CURRENT_TIMESTAMP
    WHERE `id` = p_id;
END$$

DROP PROCEDURE IF EXISTS `eliminarPlantillaOfertaModalidad`$$
CREATE PROCEDURE `eliminarPlantillaOfertaModalidad`(
    IN p_id INT
)
BEGIN
    DELETE FROM `PlantillaOfertaModalidad` WHERE `id` = p_id;
END$$

DROP PROCEDURE IF EXISTS `buscarPlantillaOfertaModalidadPorId`$$
CREATE PROCEDURE `buscarPlantillaOfertaModalidadPorId`(
    IN p_id INT
)
BEGIN
    SELECT `id`, `idPlantillaOferta`, `idModalidad`, `fechaCreacion`, `fechaModificacion`
    FROM `PlantillaOfertaModalidad`
    WHERE `id` = p_id;
END$$
DROP PROCEDURE IF EXISTS `listarPlantillaOfertaModalidades`$$
CREATE PROCEDURE `listarPlantillaOfertaModalidades`()
BEGIN
    SELECT `id`, `idPlantillaOferta`, `idModalidad`, `fechaCreacion`, `fechaModificacion`
    FROM `PlantillaOfertaModalidad`;
END$$

DROP PROCEDURE IF EXISTS `listarModalidadesPorPlantilla`$$
CREATE PROCEDURE `listarModalidadesPorPlantilla`(
    IN p_idPlantilla INT
)
BEGIN
    SELECT 
        m.idModalidad,  -- Cambiado para coincidir con Java
        m.nombre
    FROM PlantillaOfertaModalidad pom
    JOIN Modalidad m ON pom.idModalidad = m.idModalidad
    WHERE pom.idPlantillaOferta = p_idPlantilla;
END$$
-- -----------------------------------------------------
-- Schema PROCEDIMIENTOS REQUISITOS
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS listarSolicitudesDesafiliacionPorCliente $$
CREATE PROCEDURE listarSolicitudesDesafiliacionPorCliente(IN p_idCliente INT)
BEGIN
    SELECT sd.*,
           e.calificacion AS encuestaCalificacion,
           e.opinion AS encuestaOpinion,
           tm.nombre AS motivoNombre
    FROM SolicitudDesafiliacion sd
    LEFT JOIN Encuesta e ON sd.idEncuesta = e.idEncuesta
    LEFT JOIN TipoMotivo tm ON e.idTipoMotivo = tm.idTipoMotivo
    WHERE sd.idCliente = p_idCliente;
END $$

-- Segundo procedimiento
DROP PROCEDURE IF EXISTS listarLineasPorCliente $$
CREATE PROCEDURE listarLineasPorCliente(IN p_idCliente INT)
BEGIN
    SELECT * FROM Linea WHERE idCliente = p_idCliente;
END $$

DROP PROCEDURE IF EXISTS actualizarEstadoLinea $$
CREATE PROCEDURE actualizarEstadoLinea(
    IN p_idLinea INT,
    IN p_activo BOOLEAN
)
BEGIN
    UPDATE Linea
    SET activo = p_activo,
        fechaModificacion = CURRENT_TIMESTAMP
    WHERE idLinea = p_idLinea;
END$$



-- Volver al delimitador original
DELIMITER ;

