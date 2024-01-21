CREATE DATABASE GOOD_CLOSET;
USE GOOD_CLOSET;

  drop table usuarios;
  /*truncate table usuarios;*/
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    surname VARCHAR(40),
    email VARCHAR(40),
    userName VARCHAR(18),
    fechaNacimiento varchar(20), -- Puedes ajustar el tipo de dato según tus necesidades como DATE
    hashContraseña VARCHAR(200), -- Otra forma de almacenar el hash si es necesario
    salt BINARY(16) -- Tamaño adecuado para un salt (16 bytes)
);



  drop table seguidores;
  /*truncate table seguidores;*/
CREATE TABLE seguidores(
id integer auto_increment primary key,
id_seguidor integer,
id_seguido integer,

FOREIGN KEY (id_seguidor) REFERENCES usuarios(id),
FOREIGN KEY (id_seguido) REFERENCES usuarios(id)
);
/*  OJO AQUI CON USUARIOS Y SEGUIDORES COMO LO HACEMOS*/

  /*drop table armario;*/
  /*truncate table armario;*/
CREATE TABLE armario(
id integer auto_increment primary key,
contadorDeOutfits integer
);

  /*drop table outfit_armario;*/
  /*truncate table outfit_armario;*/
CREATE TABLE outfit_armario(
id_outfit integer,
id_armario integer,

FOREIGN KEY (id_outfit) REFERENCES outfit(id),
FOREIGN KEY (id_armario) REFERENCES armario(id)
);

  /*drop table outfit;*/
  /*truncate table outfit;*/
CREATE TABLE outfit(
id integer auto_increment primary key,
Nombre varchar(50)
);

  /*drop table outfit_tags;*/
  /*truncate table outfit_tags;*/
CREATE TABLE outfit_tags(
  id int AUTO_INCREMENT PRIMARY KEY,
  id_outfit int,
  id_tag int,
  FOREIGN KEY(id_outfit) REFERENCES outfit(id),
  FOREIGN KEY(id_tag) REFERENCES tags(id)
  );
  
  /*drop table tags;*/
  /*truncate table tags;*/
CREATE TABLE tags(
  id int AUTO_INCREMENT PRIMARY KEY,
  tag varchar(31)
  );
/*   RELACIONAMOS OUTFIT CON TAGAS PARA QUE UN OUTFIT PUEDA TENER VARIAS TAGAS Y PODER FILTRAR POR TAGS
	 RELACIONAMOS LA TABLA OUTFIT CON ARMARIO PARA QUE EN UN ARMARIO PUEDA HABER VARIOS OUTFITS, PODREMOS
     FILTRAR LOS ARMARIOS POR TAGS*/
     
/*drop table prenda_outfit;*/
/*truncate table prenda_outfit;*/
CREATE TABLE prenda_outfit(
  id_prenda int,
  id_outfit int,
  FOREIGN KEY(id_prenda) REFERENCES prenda(id),
  FOREIGN KEY(id_outfit) REFERENCES outfit(id)
  );

/*drop table prenda;*/
/*truncate table prenda;*/
  CREATE TABLE prenda(
  id integer auto_increment primary key,
  marca varchar (30),
  tipo varchar(40),  /*pantalon, camiseta,camisa,blusa, etc...*/
  talla varchar(5),
  color varchar(10), /*existen IA's HECHAS que comparan colores de fotos, seria la polla implementarla para el color de la ropa.*/
  enlaceWeb text
  )
