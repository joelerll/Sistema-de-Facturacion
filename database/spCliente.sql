delimiter ;
use facturacion;


delimiter //
create procedure insertar_cliente
(in cedula varchar(15),
 in fecha date,
 in nombre varchar(30),
 in apellido varchar(30),
 in dir varchar(30),
 in cel varchar(30),
 in convencional varchar(30),
 in email varchar(30))
begin
	insert into cliente values (cedula, fecha, nombre, apellido, dir, cel, convencional, email);
end
//

delimiter //
create procedure eliminar_cliente
(in cedula varchar(15)
)
begin
	delete from cliente where Cedula_C = cedula;
end
//

delimiter //
create procedure editar_cliente
(in cedula varchar(15),
 in fecha date,
 in nombre varchar(30),
 in apellido varchar(30),
 in dir varchar(30),
 in cel varchar(30),
 in convencional varchar(30),
 in email varchar(30),
 in cedu_original varchar(15)
)
begin
	update cliente set
		Cedula_C = cedula,
        Fecha_C = fecha,
        Nombre_C = nombre,
        Apellido_C = apellido,
        Direccion_C = dir,
        Celular_C = cel,
        Convencional_C = convencional,
        Email_C = email
	where
		Cedula_C = cedu_original;
end
//

delimiter //
create procedure buscar_cliente
(in ced varchar(15),
 in fecha varchar(15),
 in nombre varchar(30),
 in apellido varchar(30),
 in dir varchar(30),
 in cel varchar(30),
 in convencional varchar(30),
 in email varchar(30)
 )
begin
if(ced is null) then set ced = "";
end if;
if(fecha is null) then set fecha = "";
end if;
if(nombre is null) then set nombre = "";
end if;
if(apellido is null) then set apellido = "";
end if;
if(dir is null) then set dir = "";
end if;
if(cel is null) then set cel = "";
end if;
if(convencional is null) then set convencional = "";
end if;
if(email is null) then set email = "";
end if;
select * from cliente where 
	Cedula_C LIKE CONCAT('%', ced, '%') and
	Fecha_C LIKE CONCAT('%', fecha, '%') and
    Nombre_C LIKE CONCAT('%', nombre, '%') and
	Apellido_C LIKE CONCAT('%', apellido, '%') and
	Direccion_C LIKE CONCAT('%', dir, '%') and
	Celular_C LIKE CONCAT('%', cel, '%') and
	Convencional_C LIKE CONCAT('%', convencional, '%') and
	Email_C LIKE CONCAT('%', email, '%');
end
//

#delimiter ;
#use facturacion;
#select * from cliente;
delimiter ;
