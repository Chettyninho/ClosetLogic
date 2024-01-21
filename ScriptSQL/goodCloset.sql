/*drop database good_closet;*/
CREATE DATABASE GOOD_CLOSET;

USE GOOD_CLOSET;

  /*drop table usuarios;*/
  /*truncate table usuarios;*/
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    surname VARCHAR(40),
    email VARCHAR(40),
    userName VARCHAR(18),
    contadorSeguidores integer,
    privado boolean,
    fechaNacimiento varchar(20), -- Puedes ajustar el tipo de dato según tus necesidades como DATE
    hashContraseña VARCHAR(200), -- Otra forma de almacenar el hash si es necesario
    salt BINARY(16) -- Tamaño adecuado para un salt (16 bytes)
);

  /*drop table seguidores;*/
  /*truncate table seguidores;*/
CREATE TABLE seguidor(
id integer auto_increment primary key,
id_seguidor integer,
id_seguido integer,

FOREIGN KEY (id_seguidor) REFERENCES usuarios(id),
FOREIGN KEY (id_seguido) REFERENCES usuarios(id)
);

  /*drop table armario;*/
  /*truncate table armario;*/
CREATE TABLE armario(
id integer auto_increment primary key,
contadorDeOutfits integer,
contadorDeLikes integer
);

  /*drop table outfit;*/
  /*truncate table outfit;*/
CREATE TABLE outfit(
id integer auto_increment primary key,
Nombre varchar(50),
descripcion varchar(120)
);

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
  );
  
    /*drop table prenda_tags;*/
  /*truncate table prenda_tags;*/
  CREATE TABLE prenda_tags(
  id int AUTO_INCREMENT PRIMARY KEY,
  id_prenda integer,
  id_tag integer,
  
  FOREIGN KEY(id_prenda) REFERENCES prenda(id),
  FOREIGN KEY(id_tag) REFERENCES tag(id)
  );

    /*drop table tag;*/
  /*truncate table tag;*/
  CREATE TABLE tag(
  id int AUTO_INCREMENT PRIMARY KEY,
  tag varchar(31)
  );
 