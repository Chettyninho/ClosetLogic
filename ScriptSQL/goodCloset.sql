/*drop database good_closet;*/
CREATE DATABASE GOOD_CLOSET;

USE GOOD_CLOSET;

drop table usuario;
truncate table usuario;
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
SELECT distinct * FROM good_closet.usuario;
INSERT INTO usuario (nombre, surname, email, user_name)
VALUES 
('Juan', 'Pérez', 'juan@example.com', 'juanpEREzZ'),
('Alvaro', 'Del Alamo', 'jn@exampfrle.com', 'EsCrotoLamo'),
('Tomas', 'Turbao', 'juturan@eple.com', 'Turbante'),
('Casas', 'Teno', 'jcasatn@exddele.com', 'TenoRiko'),
('Elisa', 'Quiroga', 'jelisquin@extol.com', 'jElisarogaZ');

drop table seguidor;
/*truncate table seguidor;*/
CREATE TABLE seguidor(
id integer auto_increment primary key,
id_seguidor integer,
id_seguido integer,

FOREIGN KEY (id_seguidor) REFERENCES usuario(id),
FOREIGN KEY (id_seguido) REFERENCES usuario(id)
);
Select * from seguidor;
INSERT INTO seguidor (id_seguidor, id_seguido)
VALUES
    (1, 3),
    (2, 1),
    (3, 2),
    (3, 5),
    (4, 5),
    (5, 4),
    (3, 1),
    (1, 2);
    
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

  drop table armario;
  truncate table armario;
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

INSERT INTO armario (nombre, contador_de_likes, id_propietario)
VALUES
    ('primavera', 500, 1),
    ('verano', 250, 2),
    ('otoño', 300, 3),
    ('invierno', 700, 4),
    ('Party', 450, 5),
    ('OldSchool', 66, 1);
    
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
    
  drop table outfit;
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
    
CREATE TABLE armario_outfit(
id integer auto_increment primary key,
  id_armario int,
  id_outfit int,
  FOREIGN KEY(id_armario) REFERENCES armario(id),
  FOREIGN KEY(id_outfit) REFERENCES outfit(id)
  );
  drop table  armario_outfit;
  
INSERT INTO armario_outfit (id_armario, id_outfit)
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
  foto_prenda longblob
  );
   
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
 
 --------------------------------------  CONSULTAS ---------------------
 
 USE GOOD_CLOSET;
/*CONSULTA QUE RECUPERA LOS OUTFITS DE LOS ARMARIOS Y TAMBIEN LAS PRENDAS*/
SELECT * FROM armario a
LEFT JOIN armario_outfit ao ON a.id = ao.id_armario
LEFT JOIN outfit o ON ao.id_outfit = o.id
LEFT JOIN prenda_outfit po ON o.id = po.id_outfit
LEFT JOIN prenda p ON po.id_prenda = p.id
WHERE a.id_propietario = 1;

/*CONSULTA QUE RECUPERA LOS OUTFITS DE LOS ARMARIOS*/
SELECT DISTINCT * FROM armario a 
            LEFT JOIN armario_outfit ao ON a.id = ao.id_armario
            LEFT JOIN outfit o ON ao.id_outfit = o.id
            WHERE a.id_propietario = 1;
            
            select * from usuario;


----------------------------------------


