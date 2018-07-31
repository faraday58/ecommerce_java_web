CREATE DATABASE ecommerce_db;
USE ecommerce_db;

CREATE TABLE tbl_categoria(codigo INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(30) unique,
visible BOOLEAN default true,
categoria_superior INT, 
FOREIGN KEY(categoria_superior) REFERENCES tbl_categoria(codigo)
   );


CREATE TABLE tbl_marca(codigo INT AUTO_INCREMENT PRIMARY KEY, 
nombre VARCHAR(30) UNIQUE,
visible BOOLEAN DEFAULT TRUE
);

CREATE TABLE tbl_producto(
webid INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30),
precio DECIMAL(10,2),
precio_nuevo DECIMAL(10,2),
stock INT DEFAULT 1,
nuevo BOOLEAN DEFAULT TRUE,
recomendado BOOLEAN DEFAULT FALSE,
descripcion VARCHAR(255),
visible BOOLEAN DEFAULT TRUE,
CHECK(precio > precionuevo),
codigo_marca INT,
codigo_categoria INT,
FOREIGN KEY (codigo_marca) REFERENCES tbl_marca(codigo),
FOREIGN KEY (codigo_categoria) REFERENCES tbl_categoria(codigo),
img VARCHAR(100) DEFAULT 'demo.png'
);

CREATE TABLE tbl_revision(
codigo INT  AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(30) NOT NULL,
correo VARCHAR (60),
comentario VARCHAR(200),
estrellas INT DEFAULT 3,
fecha  DATETIME,
webid INT,
FOREIGN KEY (webid) REFERENCES tbl_producto(webid)
);

CREATE TABLE tbl_producto_moneda(
moneda CHAR(3),
precio DECIMAL(10,2),
precionuevo DECIMAL(10,2),
check(precionuevo<precio),
webid INT,
FOREIGN KEY(webid) REFERENCES tbl_producto(webid),
PRIMARY KEY(moneda,webid)
);


INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('ROPA DEPORTIVA', DEFAULT, 1);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('NIKE', DEFAULT, 1);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('ADIDAS', DEFAULT, 1);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('PUMA', DEFAULT, 1);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('HOMBRES', DEFAULT, 5);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('SACOS', DEFAULT, 5);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('PANTALONES', DEFAULT, 5);
INSERT INTO ecommerce_db.tbl_marca (nombre, visible) 
	VALUES ('NIKE', DEFAULT);
INSERT INTO ecommerce_db.tbl_marca (nombre, visible) 
	VALUES ('ADIDAS', DEFAULT);
INSERT INTO ecommerce_db.tbl_marca (nombre, visible) 
	VALUES ('PUMA', DEFAULT);
INSERT INTO ecommerce_db.tbl_marca (nombre, visible) 
	VALUES ('LACOSTE', DEFAULT);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('MUJERES', true, 8);
INSERT INTO ecommerce_db.tbl_categoria (nombre, visible, categoria_superior) 
	VALUES ('NIÑOS', true, 9);

DELIMITER $$
CREATE PROCEDURE sp_listarCategoriaSuperior()
BEGIN
    SELECT codigo,nombre FROM tbl_categoria  
    WHERE codigo=categoria_superior AND visible=TRUE;

END$$

DELIMITER $$
CREATE PROCEDURE sp_listarSubCategoria(p_csuperior INT)
BEGIN 
    SELECT codigo,nombre FROM tbl_categoria
    WHERE codigo <> categoria_superior AND visible=TRUE AND categoria_superior=p_csuperior;
END $$

DELIMITER $$
CREATE PROCEDURE sp_contarSubCategoria(cod_cad INT )
BEGIN
    SELECT COUNT(*) AS cantidad FROM tbl_categoria
    WHERE  categoria_superior=cod_cad AND cod_cad <> codigo;
END $$


DELIMITER $$
CREATE PROCEDURE sp_listarTodoCategoria() 
    BEGIN
        SELECT codigo,nombre FROM tbl_categoria WHERE visible=true ORDER BY nombre;
    END $$

DELIMITER $$
  CREATE PROCEDURE sp_listarTodoMarca()
   BEGIN
        SELECT codigo,nombre FROM tbl_marca WHERE visible=true ORDER BY nombre;
   END $$

DELIMITER $$
    CREATE PROCEDURE sp_registrar_producto(
    p_nombre VARCHAR(30),
    p_precio DECIMAL(10,2),
    p_precio_nuevo DECIMAL(10,2),
    p_stock INT,
    p_nuevo BOOLEAN,
    p_recomendado BOOLEAN,
    p_descripcion TEXT,
    p_visible BOOLEAN,
    p_codigo_marca INT,
    p_codigo_categoria INT,
    p_img VARCHAR(100),
    p_moneda_eur CHAR(3),
    p_precio_eur DECIMAL(10,2),
    p_precionuevo_eur DECIMAL(10,2),
    p_moneda_usd CHAR(3),
    p_precio_usd DECIMAL(10,2),
    p_precionuevo_usd DECIMAL(10,2),
    p_moneda_cup CHAR(3),
    p_precio_cup DECIMAL(10,2),
    p_precionuevo_cup DECIMAL(10,2)    
    )
    BEGIN
    DECLARE v_webid INT;
        INSERT INTO tbl_producto VALUES( null, p_nombre, p_precio, p_precio_nuevo,p_stock, p_nuevo, p_recomendado,
        p_descripcion, p_visible, p_codigo_marca, p_codigo_categoria,p_img
        );
        
        SET v_webid=(SELECT LAST_INSERT_ID());
        INSERT INTO tbl_producto_moneda VALUES (p_moneda_eur,p_precio_eur,p_precionuevo_eur,v_webid);
        INSERT INTO tbl_producto_moneda VALUES (p_moneda_usd,p_precio_usd,p_precionuevo_usd,v_webid);
        INSERT INTO tbl_producto_moneda VALUES (p_moneda_cup,p_precio_cup,p_precionuevo_cup,v_webid);
    END $$