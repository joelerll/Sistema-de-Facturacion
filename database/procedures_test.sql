/*Prueba facturas*/
call getFactura(1,@id,@valor,@fecha,@cedula_c,@cedula_empl,@anulada,@actualizada);
select @id,@valor,@fecha,@cedula_c,@cedula_empl,@anulada,@actualizada;

/*Prueba clientes*/
call getCliente_nombre_completo("0092314030", @nombre);
select @nombre;

/*Empleador*/
call getAllEmpleadorJ(2,@a,@n,@m,@l,@k,@j,@g,@f,@t,@p);

/*Productos*/
call buscarProductoFormatoUnoJ('%PAPEL%',@a,@d,@f,@g,@h,@j);