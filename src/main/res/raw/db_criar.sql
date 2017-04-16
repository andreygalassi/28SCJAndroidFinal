
CREATE TABLE seriado (
 id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
 data date NOT NULL,
 nome varchar(255) NOT NULL
);
CREATE TABLE usuario (
 id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
 login varchar(255) NOT NULL,
 senha varchar(255) NOT NULL,
 conectado integer NOT NULL
);