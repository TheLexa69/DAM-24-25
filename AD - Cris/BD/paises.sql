drop database if exists paises;
create database if not exists paises;
use paises;

create table if not exists Paises(
	idPais int auto_increment,
	nombrePais varchar(50) not null,
    numHabitantes int not null,
    nombreCapital varchar(50) not null,
    moneda varchar(50) not null,
    
    primary key (idPais)
);

insert into Paises(nombrePais, numHabitantes, nombreCapital, moneda) values ('Brasil', 203000000, 'Brasilia', 'Real');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Argentina', 45195777, 'Buenos Aires', 'Peso argentino');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Chile', 19116209, 'Santiago', 'Peso chileno');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Colombia', 50882891, 'Bogotá', 'Peso colombiano');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Perú', 33100000, 'Lima', 'Sol');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('México', 126190788, 'Ciudad de México', 'Peso mexicano');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Estados Unidos', 331000000, 'Washington D.C.', 'Dólar estadounidense');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Canadá', 38000000, 'Ottawa', 'Dólar canadiense');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Reino Unido', 67886011, 'Londres', 'Libra esterlina');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Francia', 65273511, 'París', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Alemania', 83783942, 'Berlín', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Italia', 60244639, 'Roma', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('España', 46754778, 'Madrid', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Portugal', 10196709, 'Lisboa', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Suecia', 10099265, 'Estocolmo', 'Corona sueca');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Noruega', 5421241, 'Oslo', 'Corona noruega');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Suiza', 8654622, 'Berna', 'Franco suizo');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, monedaidPais) VALUES ('Países Bajos', 17134872, 'Ámsterdam', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Bélgica', 11589623, 'Bruselas', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Austria', 8917205, 'Viena', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Grecia', 10423054, 'Atenas', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('República Checa', 10708981, 'Praga', 'Corona checa');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Hungría', 9660351, 'Budapest', 'Forint');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Irlanda', 4937786, 'Dublín', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Dinamarca', 5818553, 'Copenhague', 'Corona danesa');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Finlandia', 5540720, 'Helsinki', 'Euro');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Islandia', 343599, 'Reikiavik', 'Corona islandesa');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Rumanía', 19237691, 'Bucarest', 'Leu');
INSERT INTO Paises(nombrePais, numHabitantes, nombreCapital, moneda) VALUES ('Bulgaria', 6948445, 'Sofía', 'Lev');
select * from Paises;

describe paises;