delimiter ;
use facturacion;


delimiter //
create procedure ingresar_empleado
(in cedula varchar(10),
 in nombre varchar(30),
 in apellido varchar(30),
 in direccion varchar(30),
 in fecha_ing date,
 in horario_ent varchar(30),
 in horario_sal varchar(30),
 in sueldo decimal(5,2),
 in es_admin int(11),
 in telefono varchar(30),
 in usuario varchar(30))
begin
	insert into empleado values (cedula, nombre, apellido, direccion, fecha_ing, horario_ent, horario_sal, sueldo, es_admin, telefono, usuario);
end
//

delimiter //
create procedure eliminar_empleado
(in cedula varchar(30)
)
begin
	delete from empleado where empleado.cedula = cedula;
end
//

delimiter //
create procedure editar_empleado
(in cedula varchar(10),
 in nombre varchar(30),
 in apellido varchar(30),
 in direccion varchar(30),
 in fecha_ing date,
 in horario_ent varchar(30),
 in horario_sal varchar(30),
 in sueldo decimal(5,2),
 in es_admin int(11),
 in telefono varchar(30),
 in usuario varchar(30),
 in cedula_original varchar(30))
begin
	update empleado set
		empleado.cedula = cedula,
        empleado.nombre = nombre,
        empleado.apellido = apellido,
        empleado.direccion = direccion,
        empleado.fecha_ing = fecha_ing,
        empleado.horario_ent = horario_ent,
        empleado.horario_sal = horario_sal,
        empleado.sueldo = sueldo,
        empleado.es_admin = es_admin,
        empleado.telefono = telefono,
        empleado.usuario = usuario
	where
		empleado.cedula = cedula_original;
end
//

create procedure buscar_empleado_nombre
(in nombre varchar(30)
)
begin
if(nombre is null) then set nombre = "";
end if;
select * from empleado where 
	empleado.nombre LIKE CONCAT('%', nombre, '%') LIMIT 1;
end
//

create procedure buscar_empleado_user
(in usuario varchar(30)
)
begin
if(usuario is null) then set usuario = "";
end if;
select * from empleado where 
	empleado.usuario LIKE CONCAT('%', usuario, '%');
end
//


 
delimiter //
create procedure buscar_empleado
(in cedula varchar(10),
 in nombre varchar(30),
 in apellido varchar(30),
 in direccion varchar(30),
 in fecha_ing date,
 in horario_ent varchar(30),
 in horario_sal varchar(30),
 in sueldo decimal(5,2),
 in es_admin int(11),
 in telefono varchar(30),
 in usuario varchar(30)
 )
begin
if(cedula is null) then set cedula = "";
end if;
if(nombre is null) then set nombre = "";
end if;
if(apellido is null) then set apellido = "";
end if;
if(direccion is null) then set direccion = "";
end if;
if(fecha_ing is null) then set fecha_ing = "";
end if;
if(horario_ent is null) then set horario_ent = "";
end if;
if(horario_sal is null) then set horario_sal = "";
end if;
if(sueldo is null) then set sueldo = "";
end if;
if(es_admin is null) then set es_admin = "";
end if;
if(telefono is null) then set telefono = "";
end if;
if(usuario is null) then set usuario = "";
end if;
select * from empleado where 
	empleado.cedula LIKE CONCAT('%', cedula, '%') and
	empleado.nombre LIKE CONCAT('%', nombre, '%') and
    empleado.apellido LIKE CONCAT('%', apellido, '%') and
	empleado.direccion LIKE CONCAT('%', direccion, '%') and
	empleado.fecha_ing LIKE CONCAT('%', fecha_ing, '%') and
	empleado.horario_ent LIKE CONCAT('%', horario_ent, '%') and
	empleado.horario_sal LIKE CONCAT('%', horario_sal, '%') and
	empleado.sueldo = sueldo and
    empleado.es_admin = es_admin and
    empleado.telefono LIKE CONCAT('%', telefono, '%') and
    empleado.usuario LIKE CONCAT('%', usuario, '%');
end
//

delimiter ;

#delimiter ;
#use facturacion;
#select * from empleado;
