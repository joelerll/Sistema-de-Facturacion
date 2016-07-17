CREATE DATABASE IF NOT EXISTS Facturacion;
USE Facturacion;

CREATE TABLE Proveedor(
  Id_Proveedor CHAR(30) NOT NULL UNIQUE,
  Nombre CHAR(30) NOT NULL,
  Direccion CHAR(50) NOT NULL,
  PRIMARY KEY (Id_Proveedor)
)ENGINE = InnoDB;

CREATE TABLE Producto(
  Id_Producto CHAR(30) NOT NULL UNIQUE,
  Nombre_Producto CHAR(30) NOT NULL,
  Imagen LONGBLOB,
  Stock INTEGER NOT NULL,
  Precio_Venta FLOAT UNSIGNED NOT NULL,
  Precio_Inicial FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (Id_Producto)
)ENGINE = InnoDB;

CREATE TABLE Grupos(
  Id_Grupo CHAR (30) NOT NULL UNIQUE,
  Nombre_G CHAR (30) NOT NULL,
  Descripcion CHAR (200) NOT NULL,
  PRIMARY KEY (Id_Grupo)
)ENGINE = InnoDB;

CREATE TABLE Cliente(
  Cedula_C CHAR(30) NOT NULL UNIQUE,
  Nombre_C CHAR(30) NOT NULL,
  Direccion_C CHAR(30) NOT NULL,
  PRIMARY KEY (Cedula_C)
)ENGINE = InnoDB;

CREATE TABLE Empleado(
  Cedula_Empl CHAR(30) NOT NULL UNIQUE,
  Nombre_E CHAR(30) NOT NULL,
  Horario_Ent CHAR(30) NOT NULL,
  Horario_Sal CHAR(30) NOT NULL,
  Es_Admin INTEGER NOT NULL,
  Sueldo  FLOAT UNSIGNED NOT NULL DEFAULT '320',
  PRIMARY KEY (Cedula_Empl)
)ENGINE = InnoDB;

CREATE TABLE Item(
  Id_Item Char(30) NOT NULL UNIQUE,
  Precio  FLOAT UNSIGNED NOT NULL,
  Nombre CHAR(30) NOT NULL,
  Descripcion Char(200),
  PRIMARY KEY (Id_Item)
)ENGINE = InnoDB;

CREATE TABLE Gastos(
  Id_Gasto CHAR(30) NOT NULL UNIQUE,
  Fecha DATE NOT NULL,
  Total  FLOAT UNSIGNED NOT NULL,
  Cedula_Empl CHAR(30) NOT NULL,
  PRIMARY KEY (Id_Gasto),
  CONSTRAINT FOREIGN KEY (Cedula_Empl) REFERENCES Empleado(Cedula_Empl)
)ENGINE = InnoDB;

CREATE TABLE Factura(
  Id_Orden CHAR (30) NOT NULL UNIQUE,
  Tipo CHAR (30) NOT NULL,
  Valor  FLOAt UNSIGNED NOT NULL,
  Fecha DATE NOT NULL,
  Cedula_C CHAR(30) NOT NULL,
  Cedula_Empl CHAR(30) NOT NULL,
  PRIMARY KEY (Id_Orden),
  CONSTRAINT FOREIGN KEY (Cedula_C) REFERENCES Cliente(Cedula_C),
  CONSTRAINT FOREIGN KEY (Cedula_Empl) REFERENCES Empleado(Cedula_Empl)
)ENGINE = InnoDB;

CREATE TABLE Proveedor_Producto(
  Id_PP CHAR(30) NOT NULL,
  Id_Proveedor CHAR(30) NOT NULL,
  Id_Producto CHAR(30) NOT NULL,
  Cantidad INTEGER NOT NULL,
  Precio FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (Id_PP),
  FOREIGN KEY (Id_Proveedor) REFERENCES Proveedor(Id_Proveedor),
  FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto)
)ENGINE = InnoDB;


CREATE TABLE Producto_Grupos(
  Id_PerteneceA CHAR(30) NOT NULL,
  Id_Producto CHAR(30) NOT NULL,
  Id_Grupo CHAR(30) NOT NULL,
  PRIMARY KEY (Id_PerteneceA),
  CONSTRAINT FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
  CONSTRAINT FOREIGN KEY (Id_Grupo) REFERENCES Grupos(Id_Grupo)
)ENGINE = InnoDB;

CREATE TABLE Producto_Factura(
  Id_PF CHAR (30) NOT NULL,
  Id_Producto CHAR(30) NOT NULL,
  Id_Orden CHAR(30) NOT NULL,
  PRIMARY KEY (Id_PF),
  CONSTRAINT FOREIGN KEY (Id_Producto) REFERENCES Producto(Id_Producto),
  CONSTRAINT FOREIGN KEY (Id_Orden) REFERENCES Factura(Id_Orden)
)ENGINE = InnoDB;

CREATE TABLE Telefonos_Cliente(
  Cedula_C CHAR(30) NOT NULL,
  Telefono CHAR(30) NOT NULL,
  CONSTRAINT FOREIGN KEY (Cedula_C) REFERENCES Cliente(Cedula_C)
)ENGINE = InnoDB;

CREATE TABLE Item_Gastos(
  Id_ITG CHAR(30) NOT NULL,
  Id_Item CHAR(30) NOT NULL,
  Id_Gasto CHAR(30) NOT NULL,
  PRIMARY KEY (Id_ITG),
  CONSTRAINT FOREIGN KEY (Id_Item) REFERENCES Item (Id_Item),
  CONSTRAINT FOREIGN KEY (Id_Gasto) REFERENCES Gastos (Id_Gasto)
)ENGINE = InnoDB;

CREATE TABLE Telefono_Proveedor(
  Id_Proveedor CHAR(30) NOT NULL,
  Telefono CHAR(30) NOT NULL,
  CONSTRAINT FOREIGN KEY (Id_Proveedor) REFERENCES Proveedor (Id_Proveedor)
)ENGINE = InnoDB;