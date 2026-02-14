Drop database if exists DBRepuestosAutomotriz_in5cm;

create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;

create table Proveedores(
	id_proveedor int auto_increment not null,
	nombre_proveedor varchar(60) not null,
	telefono_proveedor int not null,
	direccion varchar(100) not null,
	email_proveedor varchar(100) not null,
	primary key PK_id_proveedor(id_proveedor)
	);
    
create table Empleados(
	id_empleado int auto_increment not null,
	nombre_empleado varchar(60) not null,
	apellido_empleado varchar(60) not null,
	puesto_empleado varchar(20) null,
	email_empleado varchar(100) not null,
	primary key PK_id_empleado(id_empleado)
	);

create table Repuestos(
	id_repuesto int auto_increment not null,
	nombre_repuesto varchar(60) not null,
	categoria_repuesto varchar(60) not null,
	precio_compra double not null,
	precio_venta double not null,
	id_proveedor int not null,
	primary key PK_id_repuesto(id_repuesto),
	constraint FK_repuesto_proveedor foreign key (id_proveedor)
	references proveedores(id_proveedor) on delete cascade
	);
    
create table Ventas(
	id_venta int auto_increment not null,
	fecha_venta date not null,
	cantidad int not null,
	total double not null,
	id_empleado int not null,
	id_repuesto int not null,
	primary key PK_id_venta(id_venta),
	constraint FK_ventas_empleado foreign key (id_empleado)
	references Empleados(id_empleado) on delete cascade,
	constraint FK_ventas_repuestos foreign key (id_repuesto)
	references Repuestos(id_repuesto) on delete cascade
	);
    
delimiter $$
create procedure sp_AgregarProveedor(
    in p_nombre varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_email varchar(100)
	)
	begin
		insert into proveedores (nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
		values (p_nombre, p_telefono, p_direccion, p_email);
	end$$
delimiter ;

call sp_AgregarProveedor("Luis", 23914824, "zona 12, 6 Calle 1-25", "luis@gmail.com");
call sp_AgregarProveedor("Ana", 90581351, "zona 9, 3 Calle 9-21", "ana@gmail.com");
call sp_AgregarProveedor("Marcos", 69028641, "zona 6, 9 Calle 4-11", "marcos@gmail.com");
call sp_AgregarProveedor("María", 71074958, "zona 2, 2 Calle 6-20", "maria@gmail.com");
call sp_AgregarProveedor("Marta", 67491224, "zona 1, 4 Calle 9-01", "marta@gmail.com");
call sp_AgregarProveedor("Daniel", 33909414, "zona 5, 5 Calle 4-04", "daniel@gmail.com");
call sp_AgregarProveedor("Lucas", 66821253, "zona 7, 7 Calle 5-10", "lucas@gmail.com");
call sp_AgregarProveedor("David", 9081342, "zona 5, 9 Calle 4-29", "david@gmail.com");
call sp_AgregarProveedor("Manuel", 98012414, "zona 11, 12 Calle 11-03", "manuel@gmail.com");
call sp_AgregarProveedor("Fernando", 69492550, "zona 10, 8 Calle 12-1", "fernando@gmail.com");

delimiter $$
create procedure sp_LeerProveedores()
	begin
		select * from proveedores;
	end$$
delimiter ;

delimiter $$
create procedure sp_ActualizarProveedor(
    in p_id int,
    in p_nombre varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_email varchar(100)
	)
	begin
		update proveedores
		set nombre_proveedor = p_nombre,
			telefono_proveedor = p_telefono,
			direccion = p_direccion,
			email_proveedor = p_email
		where id_proveedor = p_id;
	end$$
delimiter ;

delimiter $$
create procedure sp_EliminarProveedor(
    in p_id int
	)
	begin
		delete from proveedores
		where id_proveedor = p_id;
	end$$
delimiter ;


-- Empleados
delimiter $$
create procedure sp_AgregarEmpleados(
    in p_nombre varchar(60),
    in p_apellido varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
    insert into empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
    values (p_nombre, p_apellido, p_puesto, p_email);
end$$
delimiter ;

call sp_AgregarEmpleados("Juan","Pérez","Gerente","juanperez@gmail.com");
call sp_AgregarEmpleados("Diego","López","Cajero","diegolopez@gmail.com");
call sp_AgregarEmpleados("Carlos","Vásquez","Vendedor","carlosvasquez@gmail.com");
call sp_AgregarEmpleados("Pedro","Méndez","Administrador","pedromendez@gmail.com");
call sp_AgregarEmpleados("Luis","Hernández","Supervisor","luishernández@gmail.com");
call sp_AgregarEmpleados("Ana","Castillo","Supervisora","anacastillo@gmail.com");
call sp_AgregarEmpleados("Jorge","Gómez","Gerente","jorgegomez@gmail.com");
call sp_AgregarEmpleados("Andrea","Castro","Gerente","andreacastro@gmail.com");
call sp_AgregarEmpleados("Alejandro","Ramírez","Gerente","alejandroramirez@gmail.com");
call sp_AgregarEmpleados("Miguel","Morales","Gerente","miguelmorales@gmail.com");

delimiter $$
create procedure sp_LeerEmpleados()
	begin
		select * from empleados;
	end$$
delimiter ;

delimiter $$
create procedure sp_ActualizarEmpleado(
    in p_id int,
    in p_nombre varchar(60),
    in p_apellido varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
	)
	begin
		update empleados
		set nombre_empleado = p_nombre,
			apellido_empleado = p_apellido,
			puesto_empleado = p_puesto,
			email_empleado = p_email
		where id_empleado = p_id;
	end$$
delimiter ;

delimiter $$
create procedure sp_EliminarEmpleado(
    in p_id int
)
begin
    delete from empleados
    where id_empleado = p_id;
end$$
delimiter ;

-- Repuestos

delimiter $$
create procedure sp_AgregarRepuesto(
    in p_nombre varchar(60),
    in p_categoria varchar(60),
    in p_precio_compra double,
    in p_precio_venta double,
    in p_id_proveedor int
	)
	begin
		insert into repuestos (nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor)
		values (p_nombre, p_categoria, p_precio_compra, p_precio_venta, p_id_proveedor);
	end$$
delimiter ;

call sp_AgregarRepuesto("Filtro de Aceite","Motor",25.00,40.00,1);
call sp_AgregarRepuesto("Bujía","Encendido",15.00,30.50,2);
call sp_AgregarRepuesto("Pastillas de Freno","Frenos",80.00,120.50,3);
call sp_AgregarRepuesto("Amortiguador","Suspensión",150.00,220.00,4);
call sp_AgregarRepuesto("Batería 12V","Eléctrico",350.50,480.00,5);
call sp_AgregarRepuesto("Correa de Tiempo","Motor",90.00,140.50,6);
call sp_AgregarRepuesto("Radiador","Enfriamiento",400.50,600.50,7);
call sp_AgregarRepuesto("Alternador","Eléctrico",500.00,720.00,8);
call sp_AgregarRepuesto("Disco de Freno","Frenos",110.00,170.50,9);
call sp_AgregarRepuesto("Filtro de Aire","Motor",30.50,50.00,10);

delimiter $$
create procedure sp_LeerRepuestos()
	begin
		select * from repuestos;
	end$$
delimiter ;

delimiter $$
create procedure sp_ActualizarRepuesto(
    in p_id int,
    in p_nombre varchar(60),
    in p_categoria varchar(60),
    in p_precio_compra double,
    in p_precio_venta double,
    in p_id_proveedor int
	)
	begin
		update repuestos
		set nombre_repuesto = p_nombre,
			categoria_repuesto = p_categoria,
			precio_compra = p_precio_compra,
			precio_venta = p_precio_venta,
			id_proveedor = p_id_proveedor
		where id_repuesto = p_id;
	end$$
delimiter ;

delimiter $$
create procedure sp_EliminarRepuesto(
    in p_id int
	)	
	begin
		delete from repuestos
		where id_repuesto = p_id;
	end$$
delimiter ;

-- Ventas

delimiter $$
create procedure sp_AgregarVenta(
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
	)
	begin
		insert into ventas (fecha_venta, cantidad, total, id_empleado, id_repuesto)
		values (p_fecha, p_cantidad, p_total, p_id_empleado, p_id_repuesto);
	end$$
delimiter ;

call sp_AgregarVenta("2026-01-10",2,80.00,1,1);
call sp_AgregarVenta("2026-01-11",4,120.00,2,2);
call sp_AgregarVenta("2026-01-12",1,120.00,3,3);
call sp_AgregarVenta("2026-01-13",1,220.00,4,4);
call sp_AgregarVenta("2026-01-14",1,480.00,5,5);
call sp_AgregarVenta("2026-01-15",2,280.00,6,6);
call sp_AgregarVenta("2026-01-16",1,600.00,7,7);
call sp_AgregarVenta("2026-01-17",1,720.00,8,8);
call sp_AgregarVenta("2026-01-18",2,340.00,9,9);
call sp_AgregarVenta("2026-01-19",3,150.00,10,10);

delimiter $$
create procedure sp_LeerVentas()
	begin
		select * from ventas;
	end$$
delimiter ;

delimiter $$
create procedure sp_ActualizarVenta(
    in p_id int,
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
	)
	begin
		update ventas
		set fecha_venta = p_fecha,
			cantidad = p_cantidad,
			total = p_total,
			id_empleado = p_id_empleado,
			id_repuesto = p_id_repuesto
		where id_venta = p_id;
	end$$
delimiter ;

delimiter $$
create procedure sp_EliminarVenta(
    in p_id int
	)
	begin
		delete from ventas
		where id_venta = p_id;
	end$$
delimiter ;
select * from Empleados;