CREATE DATABASE PRUEBA_CLIENTE_SERVIDOR;
USE PRUEBA_CLIENTE_SERVIDOR;

CREATE TABLE USUARIO(
ID integer auto_increment primary key,
Usuario varchar(30),
Contrasena varchar(30)
);

INSERT INTO USUARIO (Usuario, Contrasena)
  VALUES (User001, aaaa),
  (User002, bbbb),
  (User003, cccc);