--Script de creación de base de datos para prueba

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE 
CREATE USER ElaniinProducts IDENTIFIED BY OraPasswd1
grant create session to ElaniinProducts;
GRANT CONNECT TO ElaniinProducts;
GRANT CONNECT, RESOURCE, DBA TO ElaniinProducts;
GRANT ALL PRIVILEGES TO ElaniinProducts;

--Tabla producto
CREATE TABLE ELANIINPRODUCTS.PRODUCTO (
	ID_PRODUCTO NUMBER(10,0),
	CANTIDAD NUMBER(10,0),
	DESCRIPCION VARCHAR2(255),
	IMAGEN VARCHAR2(255),
	NOMBRE VARCHAR2(255),
	PRECIO NUMBER(19,2),
	SKU VARCHAR2(255),
	CONSTRAINT SYS_C009993 CHECK (ID_PRODUCTO),
	CONSTRAINT SYS_C009994 CHECK (CANTIDAD),
	CONSTRAINT SYS_C009995 CHECK (NOMBRE),
	CONSTRAINT SYS_C009996 CHECK (PRECIO),
	CONSTRAINT SYS_C009997 PRIMARY KEY (ID_PRODUCTO)
);
CREATE UNIQUE INDEX SYS_C009997 ON ELANIINPRODUCTS.PRODUCTO (ID_PRODUCTO);

--Tabla Usuario
CREATE TABLE ELANIINPRODUCTS.USUARIO (
	ID_USUARIO NUMBER(10,0),
	EMAIL VARCHAR2(255),
	FECHA_NACIMIENTO TIMESTAMP,
	NOMBRE VARCHAR2(255),
	PASSWORD VARCHAR2(255),
	TELEFONO VARCHAR2(255),
	USERNAME VARCHAR2(255),
	CONSTRAINT SYS_C0010000 CHECK (NOMBRE),
	CONSTRAINT SYS_C0010001 CHECK (PASSWORD),
	CONSTRAINT SYS_C0010002 CHECK (USERNAME),
	CONSTRAINT SYS_C0010003 PRIMARY KEY (ID_USUARIO),
	CONSTRAINT SYS_C009998 CHECK (ID_USUARIO),
	CONSTRAINT SYS_C009999 CHECK (EMAIL),
	CONSTRAINT UK_5171L57FAOSMJ8MYAWAUCATDW UNIQUE (EMAIL),
	CONSTRAINT UK_863N1Y3X0JALATOIR4325EHAL UNIQUE (USERNAME)
);
CREATE UNIQUE INDEX SYS_C0010003 ON ELANIINPRODUCTS.USUARIO (ID_USUARIO);
CREATE UNIQUE INDEX UK_5171L57FAOSMJ8MYAWAUCATDW ON ELANIINPRODUCTS.USUARIO (EMAIL);
CREATE UNIQUE INDEX UK_863N1Y3X0JALATOIR4325EHAL ON ELANIINPRODUCTS.USUARIO (USERNAME);

--Tabla Reset_Token
CREATE TABLE ELANIINPRODUCTS.RESET_TOKEN (
	ID_RESET_TOKEN NUMBER(10,0),
	EXPIRACION TIMESTAMP,
	TOKEN VARCHAR2(255),
	ID_USUARIO NUMBER(10,0),
	CONSTRAINT SYS_C0010022 CHECK (ID_RESET_TOKEN),
	CONSTRAINT SYS_C0010023 CHECK (EXPIRACION),
	CONSTRAINT SYS_C0010024 CHECK (TOKEN),
	CONSTRAINT SYS_C0010025 CHECK (ID_USUARIO),
	CONSTRAINT SYS_C0010026 PRIMARY KEY (ID_RESET_TOKEN),
	CONSTRAINT UK_SHIUTQGQQ3M7HDRLMCKBK4AM6 UNIQUE (TOKEN),
	CONSTRAINT FKOC8CQWNB1M8IJOBOIMHCYBRL4 FOREIGN KEY (ID_USUARIO) REFERENCES ELANIINPRODUCTS.USUARIO(ID_USUARIO)
);

--Datos de pruebas de productos
INSERT INTO ELANIINPRODUCTS.PRODUCTO (ID_PRODUCTO,CANTIDAD,DESCRIPCION,IMAGEN,NOMBRE,PRECIO,SKU) VALUES 
(53,0,'Televisor con definicion 4K, con miles de colores y tecnologia 5G','https://http2.mlstatic.com/televisores-samsungled-backlit-lcd-displaysmart-tv55-4k-D_NQ_NP_739523-MEC30433444155_052019-Q.jpg','Televisor pantalla Plasma 44 pulgadas',489.99,'MDE235121')
,(54,8,'Teléfono con alta capacidad','https://www.androidsis.com/wp-content/uploads/2017/10/Xperia-R1-y-R1-Plus-830x824.jpg','Teléfono de alta gama marca Sony',189.99,'LFH235121')
,(55,12,'Teléfono con media capacidad','https://www.androidsis.com/wp-content/uploads/2017/10/Xperia-R1-y-R1-Plus-830x824.jpg','Teléfono de gama media marca Huawei',99.99,'LQV235124')
,(56,12,'Teléfono con baja capacidad','https://www.androidsis.com/wp-content/uploads/2017/10/Xperia-R1-y-R1-Plus-830x824.jpg','Teléfono de gama baja marca Goco',99.99,'PWT283404')
,(57,185,'Cuadernos para estudio multimaterias','https://olibodegas.com.gt/wp-content/uploads/2018/04/46882.jpg','Cuaderno para estudio',2.99,'PKX873404')
,(58,58,'Cuadernos para dibujo con lienzo blanco','https://dibujo.net/wp-content/uploads/2019/09/cuaderno-de-dibujo-para-dibujantes.png','Cuaderno para dibujo',4.99,'PKX897520')
,(59,20,'Mouse para laptop bluetooth','https://www.rafenlinea.com/1321-home_default/mouse-inalambrico-gala-rojo-unno-ms6524rd.jpg','Mouse para laptop',13.99,'PKX897520')
,(1,3,'Televisor con definicion 4K, con miles de colores y tecnologia 5G','https://http2.mlstatic.com/televisores-samsungled-backlit-lcd-displaysmart-tv55-4k-D_NQ_NP_739523-MEC30433444155_052019-Q.jpg','Televisor pantalla Plasma 44 pulgadas',489.99,'MDE235121')
,(2,5,'Refrigeradora de 12 pies y 250 litros e incluye ahorrador de energia','https://cdn.shopify.com/s/files/1/0069/3164/4516/products/21_7dd06d92-9389-43dd-b5cc-510e57e3c12b_1200x1200.jpg','Refrigeradora gris',736.2,'WRG856372')
,(60,16,'Cooler para laptop económica','https://images-na.ssl-images-amazon.com/images/I/71B7RIfJEML._AC_SX466_.jpg','Cooler para laptop',13.99,'PKR526520')
;

--Datos de pruebas de usuarios
INSERT INTO ELANIINPRODUCTS.USUARIO (ID_USUARIO,EMAIL,FECHA_NACIMIENTO,NOMBRE,PASSWORD,TELEFONO,USERNAME) VALUES 
(102,'imavalos@yahoo.com',TIMESTAMP '1982-08-14 01:00:00.000000','Ileana Maria Avalos','$2a$10$0ainWCR5L5QB0BaPqkWSK.Za0QdoqLhuAq36qrWnvF1qd9Y1uXNoS','75842456','imavalos')
,(103,'ppcastillo@yahoo.com',TIMESTAMP '1989-08-14 01:00:00.000000','Pedro Pablo Castillo','$2a$10$EpghpIrWh7daoKJKWuk.zuZgQLm4CDm5jGdGAFqn7HoERv78ZyTdm','75849666','ppcastillo')
,(104,'kdcastillo@gmail.com',TIMESTAMP '1979-08-04 01:00:00.000000','Kate del Castillo','$2a$10$E.Stogl3brbElzf/uK/9ROaOi0tGvXFv/zdNLPm9wANjuNXbO1G8a','75849666','kdcastillo')
,(105,'georgemarroquin@gmail.com',TIMESTAMP '1969-04-04 01:00:00.000000','George Marroquin','$2a$10$qsJCmWP0Ku7gfW.Iuuxhd.XvXD.Via7ssejUuABUOUqxjLhj8n3.m','75849146','georgemarroquin')
,(1,'rodrigoahv@hotmail.com',TIMESTAMP '1993-06-29 13:34:00.000000','Rodrigo Alfredo Herrera Valle','$2a$10$h9Hh2U4EMZsz0AWV3tyOt.W2/I11mf79PBUdHijKsMrWFbirJAIsC','77997788','rodrigoahv')
,(2,'maritoerp@hotmail.com',TIMESTAMP '1990-06-15 01:00:00.000000','Mario Ernesto Rivas Perez','$2a$10$/3O9ii2xktO.AzZinmT7B.0/S2ySdQySWYHSsFMKp1CA7YpTlLD/C','77968574','maritorivas')
,(3,'gavidiajr@hotmail.com',TIMESTAMP '1982-08-14 01:00:00.000000','Juan Roberto Gavidia Peraza','$2a$10$y1PMULFaB7cjt9rdhaP1oe0uAJZQ.98rbe61/7XJPVkFICL1Ih/1W','77102457','rjgavidia')
,(52,'emguevara@gmail.com',TIMESTAMP '1982-08-14 01:00:00.000000','Esteban Mario Guevara','$2a$10$VfRzKR8Po/3h.N.6wkrv..HtS6hi9QNIOlGiMn.VUAAvYzLwoG1I2','77102456','emguevara')
,(53,'nahernandez@yahoo.com',TIMESTAMP '1982-08-14 01:00:00.000000','Nancy Aracely Hernandez','$2a$10$pcCtHsEt7CK9ex6QseFBseMzOa8OsRFmw0ksO0RGNLta/gK41vgTm','79102456','nahernandez')
;