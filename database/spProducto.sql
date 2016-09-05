use facturacion;
drop procedure insertar_producto;

delimiter //
create procedure insertar_producto
(in id varchar(40),
 in nombre varchar(60),
 in marca varchar(30),
 in imagen varchar(40),
 in stock int(10),
 in precio_venta decimal(5,2),
 in precio_inicial decimal(5,2)
 )
begin
	insert into producto values (id, nombre, marca, null, stock, precio_venta, precio_inicial);
end
//

delimiter //
create procedure eliminar_producto
(in idP varchar(40)
)
begin
	delete from producto where id = idP;
end
//


delimiter //
create procedure editar_producto
(in pIdOriginal varchar(15),
 in pId varchar(15),
 in pNombre varchar(30)
 )
begin
	update producto set
		id = pId,
        nombre = pNombre
	where
		id = pIdOriginal;
end
//