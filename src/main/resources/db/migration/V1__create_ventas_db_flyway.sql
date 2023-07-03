
CREATE TABLE clientes(
	id_cliente INT UNSIGNED AUTO_INCREMENT,
	nombre VARCHAR(30),
	paterno VARCHAR(30),
	materno VARCHAR(30),
	correo VARCHAR(30) UNIQUE,
	pass VARCHAR(255),
	fecha_nacim DATE,
	genero ENUM("Femenino","Masculino"),
	telefono VARCHAR(10) UNIQUE,
	PRIMARY KEY (id_cliente)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE direcciones(
	id_direccion INT UNSIGNED AUTO_INCREMENT,
	id_cliente INT UNSIGNED,
	estado VARCHAR(30),
	municipio VARCHAR(30),
	colonia VARCHAR(30),
	calle VARCHAR(30),
	numero INT UNSIGNED,
	cp VARCHAR(5),
	
	PRIMARY KEY(id_direccion),
	FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
		ON UPDATE CASCADE
		ON DELETE CASCADE
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE categorias(
	id_categoria INT UNSIGNED AUTO_INCREMENT,
	categoria VARCHAR(15) UNIQUE,
	PRIMARY KEY (id_categoria)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;;


CREATE TABLE forma_de_pago(
	id_pago INT UNSIGNED AUTO_INCREMENT,
	pago VARCHAR(20) UNIQUE,
	descripcion VARCHAR(50),
	PRIMARY KEY(id_pago)
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE productos(
	id_producto INT UNSIGNED AUTO_INCREMENT,
	nombre VARCHAR(50),
	precio DECIMAL(8,2),
	cantidad INT,
	id_categoria INT UNSIGNED,
	PRIMARY KEY(id_producto),
	FOREIGN KEY(id_categoria) REFERENCES categorias (id_categoria)
		ON DELETE CASCADE
		ON UPDATE CASCADE
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE ventas(
	id_venta INT UNSIGNED AUTO_INCREMENT,
	id_cliente INT UNSIGNED,
	id_pago INT UNSIGNED,
	fecha DATE,
	total_articulos INT,
	subtotal DECIMAL(8,2),
	descuento DECIMAL(8,2) DEFAULT 0.00,
	iva DECIMAL(8,2) DEFAULT 16.00,
	total_neto DECIMAL(8,2),
	
	PRIMARY KEY(id_venta),
	FOREIGN KEY(id_cliente) REFERENCES clientes (id_cliente)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(id_pago) REFERENCES forma_de_pago(id_pago)
		ON DELETE CASCADE
		ON UPDATE CASCADE
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE detalle_ventas(
	id_venta INT UNSIGNED,
	id_producto INT UNSIGNED,
	cantidad INT,
	importe DECIMAL(8,2),
	
	FOREIGN KEY(id_venta) REFERENCES ventas(id_venta)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(id_producto) REFERENCES productos (id_producto)
		ON DELETE CASCADE
		ON UPDATE CASCADE
)Engine=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO 
	clientes (nombre, paterno, materno, fecha_nacim,genero, telefono)
VALUES
	('Angel', 'Torres', 'Hernandez', '1994-11-20', '2', '5585293776'),
	('Yorgos', 'Bushnell', 'Consterdine', '1976-12-27', '1', '4206636355'),
	('Dacey', 'Peplaw', 'Rodenburg', '1994-04-21', '2', '9528039548'),
	('Blanch', 'Riccetti', 'Reason', '1998-04-27', '2', '1514870683'),
	('Rowena', 'Giovannacc@i', 'Jarmain', '1978-12-25', '2',  '8642579890'),
	('Clarence', 'Mackin', 'Battman', '1995-09-03', '1',  '7014533387'),
	('Giselle', 'Adnams', 'Kemsley', '1980-03-18', '2', '5781052860'),
	('Perry', 'Joyce', 'Wolledge', '1973-08-30', '2',  '5892415596'),
	('Kingsley', 'Bentke', 'Klaffs', '1990-02-07', '2',  '9148979230'),
	('Sharl', 'Balbeck', 'Aubray', '1998-03-11', '2',  '4476464674'),
	('Royce', 'Henrych', 'Dumbar', '1979-02-19', '2',  '2906893515');

INSERT INTO forma_de_pago (pago) VALUES 
	("Efectivo"),
	("Tarjeta de credito"),
	("Tarjeta de Debito");

INSERT INTO categorias (categoria) VALUES
	("Audio y Video"),
	("Computo"),
	("Papeleria"),
	("Impresion"),
	("Smartphones"),
	("Entretimiento"),
	("Videojuegos");
	

INSERT INTO productos (nombre, precio,cantidad,id_categoria) VALUES
	("Microfono Sony",885.50,100,1),
	("Camara Logitech",1250.00,50,1),
	("MacBook Pro",35000.00,20,2),
	("HP Envy",20000.00,30,2),
	("Lapices Stadtler",350.50,50,3),
	("Pluma Bic Negra",7.50,200,3),
	("Pluma Bic Roja",7.50,200,3),
	("Pluma Bic Azul",7.50,200,3),
	("Hojas Blancas 100/P",95.50,50,4),
	("Multifuncional Epson",8999.90,15,4),
	("Copiadora Color/BN",2500.75,15,4),
	("Iphone 14",35000.00,25,5),
	("Pixel ONE 7",18000.00,30,5),
	("Pantalla Sony 50 plg",10000.00,25,6),
	("Teatro en casa Sony",1200.50,27,6),
	("Nintendo Switch OLED",9990.00,33,7),
	("Nintendo Switch Lite",4000.00,10,7),
	("PS5 1TB",9500.00,25,7),
	("Xbox Series X",6500.00,35,7);

INSERT INTO ventas (id_cliente,id_pago,fecha) VALUES
	(1,1,CURRENT_DATE());
INSERT INTO ventas (id_cliente,id_pago,fecha) VALUES
	(2,1,CURRENT_DATE());
INSERT INTO ventas (id_cliente,id_pago,fecha) VALUES
	(3,1,CURRENT_DATE());

-- -------------------------------------------------------------------------------
