drop database if exists DBVentas_in5cm;
create database DBVentas_in5cm;
use DBVentas_in5cm;

-- tabla clientes
create table clientes (
    dpi_cliente varchar(13) primary key,
    nombre_cliente varchar(50) not null,
    apellido_cliente varchar(50) not null,
    direccion varchar(100) not null,
    estado int not null
);

-- Tabla Usuarios
create table usuarios (
    codigo_usuario int primary key auto_increment,
    username varchar(45) not null,
    password varchar(45) not null,
    email varchar(60) not null,
    rol varchar(45) not null,
    estado int not null
);

-- Tabla Productos
create table productos (
    codigo_producto int primary key auto_increment,
    nombre_producto varchar(60) not null,
    precio decimal(10,2) not null,
    stock int not null,
    estado int not null
);

-- tanla Ventas

create table ventas (
    codigo_venta int primary key auto_increment,
    fecha_venta date not null,
    total decimal(10,2) not null,
    estado int not null,
    clientes_dpi_cliente varchar(13) not null,
    usuarios_codigo_usuario int not null,
    constraint fk_ventas_clientes foreign key (clientes_dpi_cliente) 
        references clientes(dpi_cliente) on delete cascade,
    constraint fk_ventas_usuarios foreign key (usuarios_codigo_usuario) 
        references usuarios(codigo_usuario) on delete cascade
);

-- Tabla detalle venta

create table detalleventa (
    codigo_detalle_venta int primary key auto_increment,
    cantidad int not null,
    precio_unitario decimal(10,2) not null,
    subtotal decimal(10,2) not null,
    productos_codigo_producto int not null,
    ventas_codigo_venta int not null,
    constraint fk_detalle_productos foreign key (productos_codigo_producto) 
        references productos(codigo_producto) on delete cascade,
    constraint fk_detalle_ventas foreign key (ventas_codigo_venta) 
        references ventas(codigo_venta) on delete cascade
);



-- Procedimientos Almacenados

delimiter $$

-- Clientes
create procedure sp_clientes_agregar(
    in p_dpi varchar(13), in p_nombre varchar(50), in p_apellido varchar(50), in p_direccion varchar(100), in p_estado int
)
begin
    insert into clientes(dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado)
    values(p_dpi, p_nombre, p_apellido, p_direccion, p_estado);
end $$

create procedure sp_clientes_listar()
begin
    select * from clientes;
end $$

create procedure sp_clientes_eliminar(in p_dpi varchar(13))
begin
    delete from clientes where dpi_cliente = p_dpi;
end $$

-- Usuarios
create procedure sp_usuarios_agregar(
    in p_username varchar(45), in p_password varchar(45), in p_email varchar(60), in p_rol varchar(45), in p_estado int
)
begin
    insert into usuarios(username, password, email, rol, estado)
    values(p_username, p_password, p_email, p_rol, p_estado);
end $$

create procedure sp_usuarios_listar()
begin
    select * from usuarios;
end $$

-- Productos
create procedure sp_productos_agregar(
    in p_nombre varchar(60), in p_precio decimal(10,2), in p_stock int, in p_estado int
)
begin
    insert into productos(nombre_producto, precio, stock, estado)
    values(p_nombre, p_precio, p_stock, p_estado);
end $$

create procedure sp_productos_listar()
begin
    select * from productos;
end $$

-- Ventas
create procedure sp_ventas_agregar(
    in p_fecha date, in p_total decimal(10,2), in p_estado int, in p_dpi_cliente varchar(13), in p_codigo_usuario int
)
begin
    insert into ventas(fecha_venta, total, estado, clientes_dpi_cliente, usuarios_codigo_usuario)
    values(p_fecha, p_total, p_estado, p_dpi_cliente, p_codigo_usuario);
    select last_insert_id() as codigo_venta;
end $$

-- Detalle Venta
create procedure sp_detalleventa_agregar(
    in p_cantidad int, in p_precio decimal(10,2), in p_subtotal decimal(10,2), in p_codigo_producto int, in p_codigo_venta int
)
begin
    insert into detalleventa(cantidad, precio_unitario, subtotal, productos_codigo_producto, ventas_codigo_venta)
    values(p_cantidad, p_precio, p_subtotal, p_codigo_producto, p_codigo_venta);
end $$

delimiter ;


--  Usuario Admin
CALL sp_usuarios_agregar('admin', '1234', 'admin@kinal.com', 'ADMIN', 1);

--  Clientes
CALL sp_clientes_agregar('3013432170102', 'Carlos', 'Jiménez', 'Ciudad de Guatemala', 1);
CALL sp_clientes_agregar('2514896320101', 'María', 'López', 'Antigua Guatemala', 1);
CALL sp_clientes_agregar('1985632140501', 'Roberto', 'García', 'Escuintla', 1);
CALL sp_clientes_agregar('3241569870901', 'Ana', 'Martínez', 'Quetzaltenango', 1);
CALL sp_clientes_agregar('1578423691201', 'Luis', 'Rodríguez', 'Chimaltenango', 1);

--  Productos
CALL sp_productos_agregar('Laptop Gamer Pro', 8500.00, 15, 1);
CALL sp_productos_agregar('Monitor 4K 27"', 2800.00, 20, 1);
CALL sp_productos_agregar('Teclado Mecánico RGB', 450.00, 50, 1);
CALL sp_productos_agregar('Mouse Inalámbrico', 250.00, 100, 1);
CALL sp_productos_agregar('Audífonos HyperX', 750.00, 30, 1);

--  Ventas 
CALL sp_ventas_agregar(CURDATE(), 8500.00, 1, '3013432170102', 1);
CALL sp_ventas_agregar(CURDATE(), 2800.00, 1, '2514896320101', 1);
CALL sp_ventas_agregar(CURDATE(), 450.00, 1, '1985632140501', 1);
CALL sp_ventas_agregar(CURDATE(), 250.00, 1, '3241569870901', 1);
CALL sp_ventas_agregar(CURDATE(), 750.00, 1, '1578423691201', 1);

--  Detalles de Venta
CALL sp_detalleventa_agregar(1, 8500.00, 8500.00, 1, 1);
CALL sp_detalleventa_agregar(1, 2800.00, 2800.00, 2, 2);
CALL sp_detalleventa_agregar(1, 450.00, 450.00, 3, 3);
CALL sp_detalleventa_agregar(1, 250.00, 250.00, 4, 4);
CALL sp_detalleventa_agregar(1, 750.00, 750.00, 5, 5);