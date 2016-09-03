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

/*Productos*/
CREATE PROCEDURE actualizarProducto(in `id_producto` CHAR(40), in `cantidad` INTEGER)
BEGIN
UPDATE producto SET producto.stock = producto.stock - `cantidad` WHERE producto.id = `id_producto`;
END :

delimiter ;