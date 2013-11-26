CREATE DATABASE IF NOT EXISTS biblioteca;

CREATE TABLE IF NOT EXISTS `biblioteca`.`socio` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`dni` int NOT NULL,
	`nombre` varchar(255) NOT NULL,
	`apellido` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`telefono` varchar(255) NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	`estado` varchar(255) NOT NULL,
	`rango` int(2) NOT NULL,
	`idDireccion` bigint NOT NULL,
	PRIMARY KEY (`id`),
	FULLTEXT(`nombre`,`apellido`,`email`,`telefono`, `estado`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`direccion` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`calle` varchar(255) NOT NULL,
	`numero` int NOT NULL,
	`piso` int,
	`departamento` varchar(255),
	`codigoPostal` varchar(255) NOT NULL,
	`idLocalidad` bigint NOT NULL,
	PRIMARY KEY (`id`),
	FULLTEXT(`calle`,`departamento`, `codigoPostal`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`provincia` (
	`id` bigint NOT NULL AUTO_INCREMENT,	
	`nombre` varchar(255) NOT NULL,
	 PRIMARY KEY (`id`),
	 FULLTEXT(`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`localidad` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nombre` varchar(255) NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	`idProvincia` varchar(255) NOT NULL,
	`activo` boolean NOT NULL,
	 PRIMARY KEY (`id`),
	UNIQUE INDEX `nombre` (`nombre` ASC),
	FULLTEXT(`nombre`,`idProvincia`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`diasNoHabiles` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`fecha` date NOT NULL, /* fecha no habil */
	`fechaSiguiente` date, /* fecha próxima habil*/
	`motivo` char(255) NOT NULL,
	`activo` boolean NOT NULL,
	PRIMARY KEY (`id`),
	FULLTEXT (`motivo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`usuario` (
	`id` bigint NOT NULL AUTO_INCREMENT,	
	`activo` boolean NOT NULL,
	`nombre` varchar(255) NOT NULL,
	`apellido` varchar(255) NOT NULL,
	`clave` varchar(255) NOT NULL,
	`usuario` varchar(255) NOT NULL,
	`tipoUsuario` varchar (255),
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `usuario` (`usuario` ASC),
	FULLTEXT(`nombre`,`apellido`,`clave`,`usuario`,`tipoUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `biblioteca`.`editorial` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nombre` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`activo` boolean NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	 PRIMARY KEY (`id`),
	/*FULLTEXT(`nombre`,`email`, `telefono`)*/
	FULLTEXT(`nombre`,`email`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `biblioteca`.`autor` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nombre` varchar(255) NOT NULL,
	`paisOrigen` varchar(255) NOT NULL,
	`activo` boolean NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	 PRIMARY KEY (`id`),
	 FULLTEXT(`nombre`, `paisOrigen`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`ejemplar` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idLibro` bigint NOT NULL,
	`anio` int NOT NULL,
	`estante` varchar(255) NOT NULL,
	`mueble` varchar(255) NOT NULL,
	`pasillo` varchar(255) NOT NULL,
	`estado` varchar(255) NOT NULL,
	`fechaBaja` date,
	`fechaAlta`date NOT NULL,
	`motivoBaja`varchar(255),
	`numeroEjemplar` int NOT NULL,
	`precioDolares` int NOT NULL,
	PRIMARY KEY (`id`),
	FULLTEXT(`estante`, `mueble`, `pasillo`, `estado`, `motivoBaja`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`reserva` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idEjemplar` bigint NOT NULL,
	`idSocio` bigint NOT NULL,
	`fechaLimite` date NOT NULL,
	`fechaAlta` date NOT NULL,
	`activo` boolean NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`libro` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`activo` boolean NOT NULL,
	`idEditorial` bigint NOT NULL,
	`isbn` varchar(255) NOT NULL,
	`paisOrigen` varchar(255) NOT NULL,
	`titulo` varchar(255) NOT NULL,
	`descripcion` varchar(255) NOT NULL,
	`linkImagen` varchar(255) NOT NULL,
	`rango` int(2) NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	`motivoBaja` varchar(255),
	PRIMARY KEY (`id`),
	FULLTEXT(`paisOrigen`, `titulo`,`motivoBaja`,`isbn`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`prestamo` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idEjemplar` bigint NOT NULL,
	`idSocio` bigint NOT NULL,
	`fechaAcordada` date NOT NULL,
	`fechaDevolucion` date,
	`fechaPrestamo` date NOT NULL,
	`estado` varchar(255) NOT NULL,
	`nota` varchar(255),
	`prestamoLocal` boolean NOT NULL,
	PRIMARY KEY (`id`),
	FULLTEXT (`nota`, `estado`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`suspensiones` (
	`id`bigint NOT NULL AUTO_INCREMENT,
	`idPrestamo` bigint NOT NULL,
	`idSocio` bigint NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaVencimiento` date NOT NULL,
	`motivo` varchar(255) NOT NULL,
	 PRIMARY KEY (`id`),
	FULLTEXT(`motivo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`relacionAutorLibro` (
	`idAutor` bigint NOT NULL,
	`idLibro` bigint NOT NULL,
	 PRIMARY KEY (`idLibro`,`idAutor`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`relacionEditorialDireccion` (
	`idEditorial` bigint NOT NULL,
	`idDireccion` bigint NOT NULL,
	 PRIMARY KEY (`idEditorial`,`idDireccion`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`telefonosEditorial` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idEditorial` bigint NOT NULL,
	`telefono` varchar(255) NOT NULL,
	FULLTEXT(`telefono`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`movimiento` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idPrestamo` bigint NOT NULL,
	`tipoMovimiento` varchar(255) NOT NULL,
	`idUsuario` bigint NOT NULL,
	FULLTEXT(`tipoMovimiento`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`etiquetasLibro` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idLibro` bigint NOT NULL,
	`etiqueta` varchar(255) NOT NULL,
	INDEX `idLibro` (`idLibro` ASC),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `biblioteca`.`usuario`
(`clave`,`usuario`,`nombre`,`apellido`,`activo`,`tipoUsuario`,`fechaAlta`)
VALUES
("admin","admin","Lucas","Couchot",true,"administrador",CURDATE());

INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Buenos Aires');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Capital Federal');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Catamarca');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Chaco');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Chubut');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Córdoba');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Corrientes');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Entre Ríos');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Formosa');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Jujuy');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('La Pampa');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('La Rioja');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Mendoza');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Misiones');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Neuquén');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Río Negro');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Salta');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('San Juan');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('San Luis');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Santa Cruz');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Santa Fe');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Santiago del Estero');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Tierra del Fuego');
INSERT INTO `biblioteca`.`provincia` (`nombre`) VALUES ('Tucumán');




