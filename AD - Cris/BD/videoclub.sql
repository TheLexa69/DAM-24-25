drop database if exists VideoClub;
create database if not exists VideoClub;
use VideoClub;

drop table if exists Pelicula;
create table if not exists Pelicula(
	identificador integer not null auto_increment,
    titulo varchar(100) not null,
    actor varchar(100) not null,
    tematica enum("Accion", "Aventura", "Ciencia_Ficcion", "Romance", "Terror") not null,
    guion varchar(900),
    disponible boolean not null,
    primary key (identificador)
);

INSERT INTO Pelicula (identificador, titulo, actor, tematica, guion, disponible) VALUES
(1, 'Inception', 'Leonardo DiCaprio', 'Ciencia_Ficcion', 'Un ladrón que roba secretos a través de los sueños.', true),
(2, 'The Dark Knight', 'Christian Bale', 'Accion', 'Batman debe enfrentarse al Joker, un criminal que amenaza Gotham.', true),
(3, 'Titanic', 'Leonardo DiCaprio', 'Romance', 'Una historia de amor en el contexto del hundimiento del Titanic.', true),
(4, 'Jurassic Park', 'Sam Neill', 'Aventura', 'Un parque temático con dinosaurios que se descontrola.', true),
(5, 'The Shining', 'Jack Nicholson', 'Terror', 'Una familia se enfrenta a fuerzas sobrenaturales en un hotel aislado.', true),
(6, 'Avatar', 'Sam Worthington', 'Ciencia_Ficcion', 'Un exmarine en un mundo alienígena lucha por su nuevo hogar.', true),
(7, 'Mad Max: Fury Road', 'Tom Hardy', 'Accion', 'En un futuro post-apocalíptico, una mujer rebelde se une a un grupo de guerreros.', true),
(8, 'Finding Nemo', 'Ellen DeGeneres', 'Aventura', 'Un pez payaso busca a su hijo perdido en el océano.', true),
(9, 'The Notebook', 'Ryan Gosling', 'Romance', 'La historia de un amor que perdura a través del tiempo.', true),
(10, 'A Nightmare on Elm Street', 'Robert Englund', 'Terror', 'Un asesino ataca a los adolescentes en sus sueños.', true);

select * from pelicula;