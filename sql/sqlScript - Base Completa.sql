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
	`nombre` varchar(255) NOT NULL,
	 PRIMARY KEY (`nombre`),
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
	`activo` boolean NOT NULL,
	`nombre` varchar(255) NOT NULL,
	`apellido` varchar(255) NOT NULL,
	`clave` varchar(255) NOT NULL,
	`usuario` varchar(255) NOT NULL,
	`tipoUsuario` varchar (255),
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	PRIMARY KEY (`usuario`),
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
	`numeroEjemplar` int NOT NULL,
	`precioDolares` int NOT NULL,
	PRIMARY KEY (`id`),
	FULLTEXT(`estante`, `mueble`, `pasillo`, `estado`)
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
	/*`idAutor` bigint NOT NULL,*/
	`idEditorial` bigint NOT NULL,
	`isbn` varchar(255) NOT NULL,
	`paisOrigen` varchar(255) NOT NULL,
	`titulo` varchar(255) NOT NULL,
	`etiquetas` varchar(255) NOT NULL,
	`rango` int(2) NOT NULL,
	`fechaAlta` date NOT NULL,
	`fechaBaja` date,
	`motivoBaja` varchar(255),
	PRIMARY KEY (`id`),
	FULLTEXT(`paisOrigen`, `titulo`,`etiquetas`,`motivoBaja`,`isbn`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `biblioteca`.`prestamo` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`idEjemplar` bigint NOT NULL,
	`idSocio` bigint NOT NULL,
	`usuario` varchar(255) NOT NULL,
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

CREATE TABLE IF NOT EXISTS `biblioteca`.`telefonosDeEditorial` (
`id` bigint NOT NULL AUTO_INCREMENT,
`idEditorial` bigint NOT NULL,
`telefono` varchar(255) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `biblioteca`.`usuario`
(`clave`,`usuario`,`nombre`,`apellido`,`activo`,`tipoUsuario`,`fechaAlta`)
VALUES
("admin","admin","Lucas","Couchot",true,"administrador",CURDATE());




