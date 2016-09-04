delimiter :
/*Factura*/

#Joel
create procedure getFactura(in `i_id` INTEGER, out `o_id` INTEGER, out `o_valor` DECIMAL(8,2), out `o_fecha` TIMESTAMP, out `o_cedula_c` VARCHAR(15), out `o_cedula_empl` VARCHAR(30), out `o_anulada` BOOLEAN, out `o_actulizada` TIMESTAMP)
begin 
select factura.id,factura.valor,factura.fecha,factura.cedula_c,factura.cedula_empl,factura.anulada,factura.actualizada into `o_id`,`o_valor`,`o_fecha`,`o_cedula_c`,`o_cedula_empl`,`o_anulada`,`o_actulizada`
from factura 
where factura.id = `i_id`;
end :

CREATE PROCEDURE anularFactura(in `id_factura` INTEGER)
BEGIN
DECLARE time TIMESTAMP;
select current_timestamp() into time;
UPDATE factura SET factura.anulada = 1, factura.actualizada = time WHERE factura.id = id_factura;
END :

/*Cliente*/

#Joel
create procedure getCliente_nombre_completo(in `i_Cedula_C` VARCHAR(15), out `o_Nombres_C` VARCHAR(100))
begin
select concat(cliente.Nombre_C, ' ', cliente.Apellido_C) into `o_Nombres_C`
from cliente
where cliente.Cedula_C = `i_Cedula_C`;
end :

create procedure buscarClienteJ(in `i_Cedula_C` VARCHAR(15), out `o_Cedula_C` VARCHAR(15), out `o_Fecha` DATE, out `o_Nombre_C` VARCHAR(30),out `o_Apellido_C` VARCHAR(30), out `o_Direccion` VARCHAR(30), out `o_Celular` VARCHAR(30), out `o_Convencional` VARCHAR(30), out `o_Email` VARCHAR(30))
begin
select c.Cedula_C,c.Fecha_C,c.Nombre_C,c.Apellido_C,c.Direccion_C,c.Celular_C,c.Convencional_C,c.Email_C INTO `o_Cedula_C`,`o_Fecha`,`o_Nombre_C`, `o_Apellido_C`,`o_Direccion`,`o_Celular`,`o_Convencional`,`o_Email` 
from cliente c 	WHERE c.Cedula_C = `i_Cedula_C`;
END :


/*Productos*/
#Joel
CREATE PROCEDURE actualizarProducto(in `id_producto` CHAR(40), in `cantidad` INTEGER)
BEGIN
UPDATE producto SET producto.stock = producto.stock - `cantidad` WHERE producto.id = `id_producto`;
END :

CREATE PROCEDURE buscarProductoFormatoUnoJ(in `i_id` CHAR(40), in `i_nombre` CHAR(60), in `i_marca` CHAR(30))
BEGIN
if(`i_id` is null) then set `i_id` = '';
end if;
if(`i_nombre` is null) then set `i_nombre` = '';
end if;
if(`i_marca` is null) then set `i_marca` = '';
end if;
SELECT * 
FROM producto p WHERE id LIKE concat('%',`i_id`,'%') AND nombre LIKE concat('%',`i_nombre`,'%') AND marca LIKE concat('%',`i_marca`,'%');
END :

/*CREATE PROCEDURE actualizarProductoListJ(in `i_stock` INTEGER, in `i_id_producto` CHAR(40))
BEGIN
UPDATE producto SET stock = `i_stock` WHERE id = `i_id_producto`;
END :*/

/*Empleado*/
CREATE PROCEDURE getAllEmpleadorJ(in `maximo` INTEGER, out `o_cedula` VARCHAR(30), out `o_nombre` VARCHAR(30), out `o_apellido` VARCHAR(30),out `o_direccion` VARCHAR(30), out `o_fecha_ing` DATE, out `o_horario_ent` VARCHAR(30), out `o_horario_sal` VARCHAR(30), out `o_sueldo` DECIMAL(5,2), out `o_es_admin` INTEGER, out `o_telefono` VARCHAR(30))
BEGIN
select e.cedula,e.nombre,e.apellido,e.direccion,e.fecha_ing,e.horario_ent,e.horario_sal,e.sueldo,e.es_admin,e.telefono into `o_cedula`,`o_nombre`,`o_apellido`,`o_direccion`,`o_fecha_ing`,`o_horario_ent`,`o_horario_sal`,`o_sueldo`,`o_es_admin`,`o_telefono`
from empleado e LIMIT `maximo`,1;
END :

CREATE PROCEDURE maxEmpleadosJ(out `max` INTEGER)
BEGIN
select count(*) INTO `max` from empleado;
end :

/*Factura*/
CREATE PROCEDURE getIdLastFacturaJ(out `max` INTEGER)
BEGIN
SELECT max(id) INTO `max` from factura;
END :

CREATE PROCEDURE setFacturaJ(in `i_valor` DECIMAL(8,2), in `i_fecha` TIMESTAMP, in `i_cedula_c` VARCHAR(15), in `i_cedula_empl` VARCHAR(30))
BEGIN
INSERT INTO factura(valor,fecha,cedula_c,cedula_empl) VALUES (`i_valor`,`i_fecha`,`i_cedula_c`,`i_cedula_empl`);
END :

/*Producto_Factura*/
CREATE PROCEDURE setProducto_FacturaJ(in `i_id_producto` CHAR(40), in `i_id_factura` INTEGER)
BEGIN
INSERT INTO producto_factura(id_producto,id_factura) VALUES (`i_id_producto`,`i_id_factura`);
END :

/*Item*/
#Eliminar item
CREATE PROCEDURE buscarItem(in `nombre` VARCHAR(50))
BEGIN
SELECT * FROM item WHERE nombre LIKE CONCAT('%',`nombre`,'%') LIMIT 1;
END :

#Empleado
CREATE PROCEDURE buscarNombreEmplado(in `i_cedula` VARCHAR(15), out `o_nombre` VARCHAR(30))
BEGIN
SELECT nombre FROM empleado WHERE cedula LIKE CONCAT('%',`i_cedula`,'%') LIMIT 1;
END :

CREATE PROCEDURE eliminarItem_emplado(in `i_id_item` INTEGER, in `i_cedula_empl` VARCHAR(30))
BEGIN
DELETE FROM item_empleado WHERE id_item = `i_id_item` AND cedula_empl = `cedula_empl`;
END :

CREATE PROCEDURE buscarItem_Empleado(in `id_item` INTEGER)
BEGIN
SELECT * FROM item_empleado WHERE id_item LIKE id_item LIMIT 1;
END :

delimiter ;

