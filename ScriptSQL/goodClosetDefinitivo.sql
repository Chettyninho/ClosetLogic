drop database GOOD_CLOSETDEFINITIVO;
CREATE DATABASE GOOD_CLOSETDEFINITIVO;

USE GOOD_CLOSETDEFINITIVO;
CREATE TABLE usuario (
    id integer AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30),
    surname VARCHAR(40),
    email VARCHAR(40),
    user_name VARCHAR(18),
    contador_seguidores int,
    contador_seguidos int,
    contador_armarios int,
    privado boolean,
    fecha_nacimiento varchar(20), -- Puedes ajustar el tipo de dato según tus necesidades como DATE
    hash_contrasena VARCHAR(200), -- Otra forma de almacenar el hash si es necesario
    salt BINARY(16), -- Tamaño adecuado para un salt (16 bytes)
	foto_usuario LONGBLOB 
);

CREATE TABLE seguidor(
id integer auto_increment primary key,
id_seguidor integer,
id_seguido integer,

FOREIGN KEY (id_seguidor) REFERENCES usuario(id),
FOREIGN KEY (id_seguido) REFERENCES usuario(id)
);

DELIMITER //
CREATE TRIGGER actualizar_contador_seguidores
AFTER INSERT ON seguidor
FOR EACH ROW
BEGIN
    UPDATE usuario
    SET contador_seguidores = (SELECT COUNT(*) FROM seguidor WHERE id_seguido = NEW.id_seguido)
    WHERE id = NEW.id_seguido;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER actualizar_contador_seguidos
AFTER INSERT ON seguidor
FOR EACH ROW
BEGIN
    UPDATE usuario
    SET contador_seguidos = (SELECT COUNT(*) FROM seguidor WHERE id_seguidor = NEW.id_seguidor)
    WHERE id = NEW.id_seguidor;
END;
//DELIMITER ;
    
CREATE TABLE armario(
id integer auto_increment primary key,
nombre varchar(40),
contador_de_outfits int,
contador_de_likes int,
id_propietario integer,

FOREIGN KEY (id_propietario) REFERENCES usuario(id)
);

DELIMITER //
CREATE TRIGGER actualizar_contador_armarios
AFTER INSERT ON armario
FOR EACH ROW
BEGIN
    UPDATE usuario
    SET contador_armarios = (SELECT COUNT(*) FROM armario WHERE id_propietario = NEW.id_propietario)
    WHERE id = NEW.id_propietario;
END;
//DELIMITER ;
    
CREATE TABLE outfit(
id integer auto_increment primary key,
Nombre varchar(50),
descripcion varchar(120),
fecha_cracion varchar(20)
);

CREATE TABLE armario_outfit(
id integer auto_increment primary key,
  id_armario int,
  id_outfit int,
  FOREIGN KEY(id_armario) REFERENCES armario(id),
  FOREIGN KEY(id_outfit) REFERENCES outfit(id)
  );
 
    
DELIMITER //
CREATE TRIGGER actualizar_contador_outfits
AFTER INSERT ON armario_outfit
FOR EACH ROW
BEGIN
    UPDATE armario
    SET contador_de_outfits = (SELECT COUNT(*) FROM armario_outfit WHERE id_armario = NEW.id_armario)
    WHERE id = NEW.id_armario;
END;

//DELIMITER ;


CREATE TABLE prenda(
  id integer auto_increment primary key,
  foto_prenda longblob
  );
   
CREATE TABLE prenda_outfit(
id integer auto_increment primary key,
  id_prenda int,
  id_outfit int,
  FOREIGN KEY(id_prenda) REFERENCES prenda(id),
  FOREIGN KEY(id_outfit) REFERENCES outfit(id)
  );


CREATE TABLE usuario_like_armario(
id integer auto_increment primary key,
  id_armario int,
  id_usuario int,
  FOREIGN KEY(id_armario) REFERENCES armario(id),
  FOREIGN KEY(id_usuario) REFERENCES usuario(id)
  );
DELIMITER //
CREATE TRIGGER update_armario_likes
AFTER INSERT ON usuario_like_armario
FOR EACH ROW
BEGIN
  UPDATE armario SET contador_de_likes = contador_de_likes + 1 WHERE id = NEW.id_armario;
END;
//DELIMITER ; 
