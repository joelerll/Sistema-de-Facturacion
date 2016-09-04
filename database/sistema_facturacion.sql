CREATE DATABASE facturacion;
USE facturacion;

CREATE TABLE proveedor(
  id INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
  nombre CHAR(30) NOT NULL,
  direccion CHAR(50) NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;

CREATE TABLE producto(
  id CHAR(40) NOT NULL UNIQUE,
  nombre CHAR(60) NOT NULL,
  marca CHAR(30),
  imagen VARCHAR(40),
  stock INTEGER UNSIGNED NOT NULL,
  precio_venta DECIMAL(5,2) NOT NULL,
  precio_inicial DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;

CREATE TABLE grupos(
  id CHAR (30) NOT NULL UNIQUE,
  nombre CHAR (30) NOT NULL,
  descripcion CHAR (200) NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;

CREATE TABLE cliente(
  Cedula_C VARCHAR(15) NOT NULL UNIQUE,
  Fecha_C DATE,
  Nombre_C VARCHAR(30) NOT NULL,
  Apellido_C VARCHAR(30) NOT NULL,
  Direccion_C VARCHAR(30) NOT NULL,
  Celular_C VARCHAR(30),
  Convencional_C VARCHAR(30),
  Email_C VARCHAR(30),
  PRIMARY KEY (Cedula_C)
)ENGINE = InnoDB;

CREATE TABLE empleado(
  cedula VARCHAR(30) NOT NULL UNIQUE,
  nombre VARCHAR(30) NOT NULL,
  apellido VARCHAR(30) NOT NULL,
  direccion VARCHAR(30) NOT NULL,
  fecha_ing DATE NOT NULL,
  horario_ent VARCHAR(30) NOT NULL,
  horario_sal VARCHAR(30) NOT NULL,
  sueldo  DECIMAL(5,2) NOT NULL,
  es_admin INTEGER NOT NULL,
  telefono VARCHAR(30) NOT NULL,
  usuario VARCHAR(30) NOT NULL,
  PRIMARY KEY (cedula)
)ENGINE = InnoDB;

CREATE TABLE item(
  id INTEGER NOT NULL AUTO_INCREMENT,
  precio  DECIMAL(5,2)  NOT NULL,
  nombre CHAR(30) NOT NULL,
  descripcion CHAR(200),
  fecha DATE NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;

CREATE TABLE factura(
  id  INT UNSIGNED  NOT NULL AUTO_INCREMENT,
  valor  DECIMAL(8,2) NOT NULL,
  fecha TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  cedula_c VARCHAR(15) NOT NULL,
  cedula_empl VARCHAR(30) NOT NULL,
  anulada BOOL NOT NULL DEFAULT 0,
  actualizada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (cedula_c) REFERENCES cliente(Cedula_C),
  CONSTRAINT FOREIGN KEY (cedula_empl) REFERENCES empleado(cedula)
)ENGINE = InnoDB;

CREATE TABLE proveedor_producto(
  id CHAR(30) NOT NULL,
  id_proveedor INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
  id_producto CHAR(40) NOT NULL,
  cantidad INTEGER NOT NULL,
  precio FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_proveedor) REFERENCES proveedor(id),
  FOREIGN KEY (id_producto) REFERENCES producto(id)
)ENGINE = InnoDB;


CREATE TABLE producto_grupos(
  id_perteneceA CHAR(30) NOT NULL,
  id_producto CHAR(40) NOT NULL,
  id_grupo CHAR(30) NOT NULL,
  PRIMARY KEY (id_perteneceA),
  CONSTRAINT FOREIGN KEY (id_producto) REFERENCES producto(id),
  CONSTRAINT FOREIGN KEY (id_grupo) REFERENCES grupos(id)
)ENGINE = InnoDB;

CREATE TABLE producto_factura(
  id INTEGER AUTO_INCREMENT NOT NULL,
  id_producto CHAR(40) NOT NULL,
  id_factura INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (id_producto) REFERENCES producto(id),
  CONSTRAINT FOREIGN KEY (id_factura) REFERENCES factura(id)
)ENGINE = InnoDB;

CREATE TABLE telefonos_cliente(
  cedula_c CHAR(30) NOT NULL,
  telefono CHAR(30) NOT NULL,
  CONSTRAINT FOREIGN KEY (cedula_c) REFERENCES cliente(Cedula_C)
)ENGINE = InnoDB;

CREATE TABLE item_empleado(
  id INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
  id_item INTEGER NOT NULL,
  cedula_empl VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (id_item) REFERENCES item (id),
  CONSTRAINT FOREIGN KEY (cedula_empl) REFERENCES empleado (cedula)
)ENGINE = InnoDB;

CREATE TABLE telefono_proveedor(
  id_proveedor INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
  telefono CHAR(30) NOT NULL,
  CONSTRAINT FOREIGN KEY (id_proveedor) REFERENCES proveedor (id)
)ENGINE = InnoDB;
