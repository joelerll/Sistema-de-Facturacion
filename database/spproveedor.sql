delimiter ;
use facturacion;


delimiter //
create procedure ingresar_proveedor
(in nombre varchar(30),
 in direccion varchar(30))
begin
	insert into proveedor (nombre, direccion) values (nombre, direccion);
end
//

delimiter //
create procedure eliminar_proveedor
(in id int(11))
begin
	delete from proveedor where proveedor.id = id;
end
//

delimiter //
create procedure editar_proveedor
(in id int(11),
 in nombre varchar(30),
 in direccion varchar(30),
 in id_original int(11))
begin
	update proveedor set
		proveedor.id = id,
        proveedor.nombre = nombre,
        proveedor.direccion = direccion
	where
		proveedor.id = id_original;
end
//

create procedure buscar_proveedor_nombre
(in nombre varchar(30)
)
begin
if(nombre is null) then set nombre = "";
end if;
select * from proveedor where 
	proveedor.nombre LIKE CONCAT('%', nombre, '%');
end
//

delimiter //
create procedure buscar_proveedor
(in id int(11),
 in nombre varchar(30),
 in direccion varchar(30))
begin
if(id is null) then set id = "";
end if;
if(nombre is null) then set nombre = "";
end if;
if(direccion is null) then set direccion = "";
end if;
select * from proveedor where 
	proveedor.id = id and
	proveedor.nombre LIKE CONCAT('%', nombre, '%') and
    proveedor.direccion LIKE CONCAT('%', direccion, '%');
end
//

#delimiter ;
#use facturacion;
#select * from empleado;
