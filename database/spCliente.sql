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
(in cedu_original varchar(15),
 in cedula varchar(15),
 in fecha date,
 in nombre varchar(30),
 in apellido varchar(30),
 in dir varchar(30),
 in cel varchar(30),
 in convencional varchar(30),
 in email varchar(30)
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