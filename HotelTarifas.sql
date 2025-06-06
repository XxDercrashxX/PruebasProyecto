DROP DATABASE IF EXISTS freedb_freehoteldb;
CREATE DATABASE freedb_freehoteldb;
USE freedb_freehoteldb;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS Pagos;
DROP TABLE IF EXISTS Servicios_Consumidos;
DROP TABLE IF EXISTS Rentas;
DROP TABLE IF EXISTS Reservas;
DROP TABLE IF EXISTS Servicios;
DROP TABLE IF EXISTS Habitaciones;
DROP TABLE IF EXISTS Clientes;
DROP TABLE IF EXISTS Tarifas;
DROP TABLE IF EXISTS Usuarios;

-- --------------------------------------------------------
-- Creación de Tablas
-- --------------------------------------------------------

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS Usuarios (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contraseñaHash VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

-- Tabla de Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

-- Tabla de Tarifas
CREATE TABLE IF NOT EXISTS Tarifas (
    idTarifa INT PRIMARY KEY AUTO_INCREMENT,
    tipoHabitacion VARCHAR(50) NOT NULL UNIQUE,
    precioBase DECIMAL(10, 2) NOT NULL,
    descuentoPorcentaje DECIMAL(5, 2) DEFAULT 0.00,
    descripcion TEXT,             
    tipoCondicion VARCHAR(50)     
);

CREATE TABLE IF NOT EXISTS Habitaciones (
    idHabitacion INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(50) NOT NULL UNIQUE,
    tipo VARCHAR(50) NOT NULL,
    numeroCamas INT NOT NULL,
    ocupada BOOLEAN DEFAULT FALSE,
    precioNoche DECIMAL(10, 2) NOT NULL,
    descripcion TEXT,
    caracteristicas TEXT
);

-- Tabla de Servicios
CREATE TABLE IF NOT EXISTS Servicios (
    idServicio INT PRIMARY KEY AUTO_INCREMENT,
    nombreServicio VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    descripcion TEXT
);

-- Tabla de Reservas
CREATE TABLE IF NOT EXISTS Reservas (
    idReserva INT PRIMARY KEY AUTO_INCREMENT,
    idCliente INT NOT NULL,
    idHabitacion INT NOT NULL,
    fechaEntrada DATE NOT NULL,
    fechaSalida DATE NOT NULL,
    estado VARCHAR(50) NOT NULL DEFAULT 'Pendiente',
    costoTotalEstimado DECIMAL(10, 2),
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente),
    FOREIGN KEY (idHabitacion) REFERENCES Habitaciones(idHabitacion)
);

-- Tabla de Rentas (o Estancias)
CREATE TABLE IF NOT EXISTS Rentas (
    idRenta INT PRIMARY KEY AUTO_INCREMENT,
    idReserva INT,
    idHabitacion INT NOT NULL,
    idCliente INT NOT NULL,
    fechaCheckIn DATETIME NOT NULL,
    fechaCheckOut DATETIME,
    estado VARCHAR(50) NOT NULL DEFAULT 'Activa',
    costoTotal DECIMAL(10, 2),
    notas TEXT,
    FOREIGN KEY (idReserva) REFERENCES Reservas(idReserva),
    FOREIGN KEY (idHabitacion) REFERENCES Habitaciones(idHabitacion),
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente)
);

-- Tabla de Servicios_Consumidos
CREATE TABLE IF NOT EXISTS Servicios_Consumidos (
    idServicioConsumido INT PRIMARY KEY AUTO_INCREMENT,
    idRenta INT NOT NULL,
    idServicio INT NOT NULL,
    cantidad INT NOT NULL DEFAULT 1,
    fechaConsumo DATETIME NOT NULL,
    precioUnitarioAlConsumo DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idRenta) REFERENCES Rentas(idRenta),
    FOREIGN KEY (idServicio) REFERENCES Servicios(idServicio)
);

-- Tabla de Pagos
CREATE TABLE IF NOT EXISTS Pagos (
    idPago INT PRIMARY KEY AUTO_INCREMENT,
    idRenta INT NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fechaPago DATETIME NOT NULL,
    metodoPago VARCHAR(100) NOT NULL,
    FOREIGN KEY (idRenta) REFERENCES Rentas(idRenta)
);

-- --------------------------------------------------------
-- Inserción de Datos de Prueba
-- --------------------------------------------------------
-- Datos de ejemplo para Usuarios
INSERT INTO Usuarios (nombreUsuario, contraseñaHash, rol) VALUES
('admin', '$2a$10$.uJl.5Es/6jL7y0RXUFCVO66rKixK0.i7Qxa0r.UtUJ7Q84a7gX8W', 'administrador'),
('recepcionista1', '$2a$10$abcdefghijklmnopqrstuvwxyza1234567890abcdefghijkl', 'recepcionista');

-- Datos de ejemplo para Tarifas (ahora con descripcion y tipoCondicion)
INSERT INTO Tarifas (tipoHabitacion, precioBase, descuentoPorcentaje, descripcion, tipoCondicion) VALUES
('Individual', 100.00, 0.00, 'Tarifa estándar para una persona.', 'Flexible'),
('Doble', 150.00, 5.00, 'Tarifa con descuento para dos personas.', 'No Reembolsable'),
('Suite', 300.00, 10.00, 'Tarifa premium con amenidades extra.', 'Flexible'),
('Familiar', 200.00, 2.50, 'Tarifa para familias con niños.', 'Promocional');

-- Habilitar nuevamente la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;

-- Confirmación de la creación de la base de datos y tablas
SELECT 'Base de datos y tablas creadas y pobladas exitosamente.' AS Mensaje;