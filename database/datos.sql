
INSERT INTO proveedor  (id,nombre,direccion) VALUES (1,"EL GORDO","SUCRE 921, GUAYAQUIL, GUAYAS") , (2,"OFIMARKET","ESMERALDAS 505 Y LUIS URDANETA ") ,
 (3,"PACINGRAF CIA. LTDA.","GARCIA MORENO 2313 Y CAPITAN NAJERA"),  (4,"SUPLIES DUPOT","BOYACA 309 ") ,  
(5,"UTYCSA","SUCRE 511 ") ,  (6,"COMSUCRE","P.MONTUFAR 109 Y BALLEN") ,  (7,"IMPORSUCRE","P.MONTUFAR 109 ") ;


INSERT INTO producto (id,nombre,marca,imagen,stock,precio_venta,precio_inicial) VALUES
('CARP-AR','CARPETA ARCHIVADORA','IDEAL','images/CARP-AR.jpg',3,'2.80','1.75'),
('PAPEL-ALU','PAPEL ALUMINIO','WRAPIT','images/PAPEL-ALU.jpg','4','2.80','1.80'),
('CALC-CASIO-FX750MS','CALCULADORA CIENTIFICA CASIO FX-570MS','CASIO','images/CALC-CASIO-FX750MS.jpg',2,'15','13'),
('CALC-KENKO-350MS','CALCULADORA CIENTIFICA KENKO KK350MS-1','KENKO','images/CALC-KENKO-350MS.jpg',3,'4.50','3.58'),
('CALC-KENKO-88MS','CALCULADORA CIENTIFICA KENKO KK-88MS-1','KENKO','images/CALC-KENKO-88MS.jpg',3 ,'4.50','3.50'),
('CALC-KENKO-105B','CALCULADORA KENKO KK-105B','KENKO','images/CALC-KENKO-105B.jpg', 2,'4.50','3.50'),
('CALC-CASIO-FX82MS','CALCULADORA CASIO FX-82MS','CASIO','images/CALC-CASIO-FX82MS.jpg', 2,'15','10'),
('LUPA','LUPA LANCE PEQUE','LANCER',NULL, 2,'1','0.5'),
('CARP-VIN-PASS','VINCHA DE CARPETA PASSOLA CAJA','PASSOLA',NULL,5 ,'1','0.45'),
('HOJAS-REF-ART','REFUERZO PARA HOJAS ARTESCO CAJA','ARTESCO',NULL,1 ,'0.5','0.25'),
('GRAPAS-26_6','GRAPAS PARA ENGRAPADORAS 26/6','ARTESCO','images/GRAPAS-26_6.jpg',8 ,'1.10','0.6'),
('ENGRAP-ARTESCO-M513','ENGRAPADORA M-513 ARTESCO 26/6','ARTESO','images/ENGRAP-ARTESCO-M513.jpg', 1,'1.80','1'),
('ENGRAP-ARTESCO-M634','ENGRAPADORA M634 ARTESCO 26/6','ARTESCO','images/ENGRAP-ARTESCO-M634.jpg',5 ,'2.25','1.50'),
('ENGRAP-CARIOCA-MIN','ENGRAPADORA CARIOCA MINI STAPLER','CARIOCA',NULL,2 ,'1.00','0.58'),
('ENGRAP-CARIOCA-GRAN','ENGRAPADORA CARIOCA GRANDE','CARIOCA',NULL, 2,'2.40','1.68'),
('HOJAS-REF-LAN','REFUERZO PARA HOJAS LANCER','LANCER',NULL,9 ,'0.65','0.30'),
('ENGRAP-ARTESCO-M527','ENGRAPADORA ARTESCO M527 26/6','ARTESCO',NULL,2 ,'4','2.90'),
('DISP-CINT-ARTESCO-GRAN','DISPENSADOR DE CINTA ARTECO GRANDE','ARTESCO',NULL, 1,'3.50','2.15'),
('PERF-ARTESCO-M73','PERFORADORA ARTESCO M-73','ARTESCO',NULL, 1,'4','2.59'),
('SACAP-ARTESCO-E08B','SACAPUNTAS ARTESCO METALICO PEQ E08B','ARTESCO',NULL, 2,'0.75','0.50'),
('PINES-COL-LANCER','PINES DE COLORES LANCER CAJA PEQ','LANCER',NULL,1 ,'0.9','0.45'),
('ATACHES-PHOENIX','ATACHES PHOENIX','PHOENIX',NULL,1 ,'0.5','0.2'),
('CLIP-COL-ARTESCO','CLIPS DE COLORES CAJA PEQ','ARTESCO','images/CLIP-COL-ARTESCO.jpg', 5,'0.50','0.20'),
('CLIP-MARI-ALEX','CLIPS DE COLORES ALEX CAJA PEQ','ALEX',NULL,1 ,'1.15','0.60'),
('CLIP-PLAT-LANCER','CLIPS LANCER PLATEADO CAJA PEQ','LANCER',NULL,8 ,'.025','0.15'),
('CLIP-PLAT-ARTESCO','CLIPS ARTESCO PLATEADO CAJA PEQ','ARTESCO',NULL,3 ,'0.75','0.50'),
('TACHU-HANJANG','TACHUELA CAJA PEQ HANJANG','HANJANG',NULL, 4,'0.35','0.15'),
('MANEC-DELIN-19MM','MANECILLA 19MM  DELIN','DELIN',NULL, 50,'0.15','0.08'),
('MANEC-LANCER-51MM','MANECILLA 51MM LANCER','LANCER',NULL,3 ,'0.35','0.20'),
('PIZAR-BOR','BORRADOR DE PIZARRA ARTESCO','ARTESCO',NULL, 8,'0.50','0.25'),
('SELLO-TINT-PELIKAN','TINTA PARA SELLO 30ML PELIKAN','PELIKAN',NULL,3,'1.50','0.80'),
('SELLO-ALMOH','ALMOHADILLA PARA SELLOS RECARGABLE ARTESCO','ARTESCO',NULL,3 ,'2.25','1.50'),
('GOMA-ESCH-CARIOCA','GOMA ESCARCHADA PAQUETE CARIOCA','CARIOCA',NULL,3 ,'1.50','0.95'),
('GOMA-UHU-PEQ','GOMA UHU PEQ','UHU',NULL, 4,'3.25','2.23'),
('GOMA-UHU-GRA','GOMA UHU GRAN','UHU',NULL, 1,'5.00','3.59');


INSERT INTO grupos (id,nombre,descripcion) VALUES
('CARP','CARPETAS','CARPETAS DE DIFERENTE TIPO'),
('PAPEL','PAPELES','PAPELES DE DIFERENTE TIPO'),
('CALC','CALCULADORAS','CALCULADORAS DE TODAS MARCAS Y MODELOS'),
('LUPA','LUPA','LUPAS DE MARCAS Y MODELOS'),
('CARP-VIN','CARPETAS VINCHA','VINCHAS DE CARPETA DE DIFERENTE MARCA'),
('HOJAS-REF','HOJAS REFUERZO','HOJAS CUADRO, LINEAS, Y OTRAS MAS'),
('GRAPAS','GRAPAS','GRAPAS DE DIFERENTE 26/6'),
('ENGRAP','ENGRAPADORAS','ENGRAPADORAS DE TODOS LOS MODELOS'),
('DISP-CINT','DISPENSADOR CINTA','DISPENSADOR DE DIFERENTE MODELO Y MARCA'),
('PERF','PERFORADORAS','PERFORADORA DE MARCAR Y MODELOS'),
('SACAP','SACAPUNTA','SACAPUNTAS DOBLES Y NORMALES'),
('PINES','PINES','PINES DE MARCAR Y MODELOS DISTINTOS'),
('ATACHES','ATACHES','ATACHES DE MODELOS'),
('CLIP','CLIPS','CLIPS PLATADOS, COLORES Y TAM, MARCA'),
('CLIP-MARI','CLIPS MARIPOSA','CLIPS MARIPOSA'),
('CLIP-PLAT','CLIPS PLATEADOS','CLIP PLATEADOS DE DIFERENTE MARCA'),
('TACHU','TACHUELA','TACHUELAS DE DIFERENTES MARCAS'),
('MANEC','MANECILLAS','MANECILLA DE DIFERENTE MARCA'),
('PIZAR-BOR','PIZARRA BORRADOR','BORRADOR DE PIZARRA DE DIFERENTE MARCA'),
('SELLO','SELLOS','SELLO DE TAM Y MARCA DIFERENTE');


INSERT INTO Cliente (Cedula_C,Nombre_C,Apellido_C,Direccion_C) VALUES
('092','JOEL','ROD','MAPASINGUE');


INSERT INTO empleado (cedula, nombre, apellido, direccion, fecha_ing, horario_ent, horario_sal, sueldo, es_admin, telefono, usuario) VALUES 
('0931823448','JOEL EDUARDO', 'RODRIGUEZ LLAMUCA', 'PUERTO AZUL', '2015-06-23', '06:00:00', '12:30:00', 340.00, 1, '0929746352', 'joelrell');

INSERT INTO factura (valor,fecha,cedula_c,cedula_empl) VALUES
(10335.6,'2014-01-06 17:03:10','092','0931823448'),
(103.6,'2013-01-06 17:03:10','092','0931823448'),
(235.36,'2012-01-06 17:03:10','092','0931823448'), 
(10335.36,'2016-01-06 17:03:10','092','0931823448');

INSERT INTO item (id,precio,nombre,descripcion,fecha) VALUES
(1,'5.00','AGUA','PAGO DEL AGUA','2016-5-20'),
(2,'250.00','SUELDO','PAGO DEL SUELDO','2016-5-10');


#INSERT INTO gastos (id,fecha,total,cedula_empl) VALUES
#(1,'2016-7-20 01:02:03','250','0931823448');

INSERT INTO item_empleado (id,id_item,cedula_empl) VALUES
('1',2,'0931823448');





