/*Prueba facturas*/
call getFactura(1,@id,@valor,@fecha,@cedula_c,@cedula_empl,@anulada,@actualizada);
select @id,@valor,@fecha,@cedula_c,@cedula_empl,@anulada,@actualizada;

/*Prueba clientes*/
call getCliente_nombre_completo("0092314030", @nombre);
select @nombre;