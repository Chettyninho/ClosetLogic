/*drop database good_closet;*/
CREATE DATABASE GOOD_CLOSET;

USE GOOD_CLOSET;

	/*drop table usuario;*/
  /*truncate table usuario;*/
CREATE TABLE usuario (
    id integer AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    surname VARCHAR(40),
    email VARCHAR(40),
    user_name VARCHAR(18),
    contador_seguidores int,
    privado boolean,
    fecha_nacimiento varchar(20), -- Puedes ajustar el tipo de dato según tus necesidades como DATE
    hash_contrasena VARCHAR(200), -- Otra forma de almacenar el hash si es necesario
    salt BINARY(16) -- Tamaño adecuado para un salt (16 bytes)
);
SELECT * FROM good_closet.usuario;
INSERT INTO usuario (nombre, surname, email, user_name, contador_seguidores)
VALUES 
    ('Juan', 'Pérez', 'juan@example.com', 'juan_perez', 100),
    ('María', 'López', 'maria@example.com', 'maria_lopez', 150),
    ('Pedro', 'Gómez', 'pedro@example.com', 'pedro_gomez', 200),
    ('Laura', 'Martínez', 'laura@example.com', 'laura_martinez', 80),
    ('Carlos', 'Rodríguez', 'carlos@example.com', 'carlos_rodriguez', 120);


 /*drop table seguidor;*/
  /*truncate table seguidores;*/
CREATE TABLE seguidor(
id integer auto_increment primary key,
id_seguidor integer,
id_seguido integer,

FOREIGN KEY (id_seguidor) REFERENCES usuario(id),
FOREIGN KEY (id_seguido) REFERENCES usuario(id)
);
INSERT INTO seguidor (id_seguidor, id_seguido)
VALUES
    (1, 2),
    (2, 3),
    (3, 1),
    (4, 5),
    (5, 4);

  /*drop table armario;*/
  /*truncate table armario;*/
CREATE TABLE armario(
id integer auto_increment primary key,
contador_de_outfits integer,
contador_de_likes integer,
id_propietario integer,

FOREIGN KEY (id_propietario) REFERENCES usuario(id)
);
INSERT INTO armario (contador_de_outfits, contador_de_likes, id_propietario)
VALUES
    (10, 500, 1),
    (5, 250, 2),
    (8, 300, 3),
    (15, 700, 4),
    (12, 450, 5);


  /*drop table outfit;*/
  /*truncate table outfit;*/
CREATE TABLE outfit(
id integer auto_increment primary key,
Nombre varchar(50),
descripcion varchar(120),
fecha_cracion varchar(20)
);
INSERT INTO outfit (Nombre, descripcion, fecha_cracion)
VALUES
    ('Verano Casual', 'Outfit ligero para días soleados', '2023-07-10'),
    ('Noche Elegante', 'Perfecto para eventos formales', '2023-05-22'),
    ('Deportivo Activo', 'Ideal para actividades al aire libre', '2023-08-05'),
    ('Invierno Cálido', 'Abrigado y a la moda', '2023-12-15'),
    ('Primavera Floral', 'Toque fresco y colorido', '2023-04-01');

/*drop table prenda_outfit;*/
/*truncate table prenda_outfit;*/
CREATE TABLE prenda_outfit(
id integer auto_increment primary key,
  id_prenda int,
  id_outfit int,
  FOREIGN KEY(id_prenda) REFERENCES prenda(id),
  FOREIGN KEY(id_outfit) REFERENCES outfit(id)
  );
  INSERT INTO prenda_outfit (id_prenda, id_outfit)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);


/*drop table prenda;*/
/*truncate table prenda;*/
  CREATE TABLE prenda(
  id integer auto_increment primary key,
  marca varchar (30),
  tipo varchar(40),  /*pantalon, camiseta,camisa,blusa, etc...*/
  talla varchar(5),
  color varchar(10), /*existen IA's HECHAS que comparan colores de fotos, seria la polla implementarla para el color de la ropa.*/
  enlace_web text
  );
  INSERT INTO prenda (marca, tipo, talla, color, enlace_web)
VALUES
    ('Adidas', 'Zapatillas', '40', 'Negro', 'https://example.com/adidas-zapatillas'),
    ('Nike', 'Camiseta', 'L', 'Azul', 'https://example.com/nike-camiseta'),
    ('Levi''s', 'Jeans', '34', 'Gris', 'https://example.com/levis-jeans'),
    ('Zara', 'Vestido', 'M', 'Rojo', 'https://example.com/zara-vestido'),
    ('Converse', 'Zapatillas', '37', 'Blanco', 'https://example.com/converse-zapatillas');
  
    /*drop table prenda_tags;*/
  /*truncate table prenda_tags;*/
  CREATE TABLE prenda_tags(
  id integer AUTO_INCREMENT PRIMARY KEY,
  id_prenda integer,
  id_tag integer,
  
  FOREIGN KEY(id_prenda) REFERENCES prenda(id),
  FOREIGN KEY(id_tag) REFERENCES tag(id)
  );
  INSERT INTO prenda_tags (id_prenda, id_tag)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5);

    drop table tag;
  /*truncate table tag;*/
  CREATE TABLE tag(
  id integer AUTO_INCREMENT PRIMARY KEY,
  tag varchar(31)
  );
  INSERT INTO tag (tag)
VALUES
    ('Deportivo'),
    ('Casual'),
    ('Formal'),
    ('Verano'),
    ('Invierno');
 