drop schema if exists series;
create schema if not exists series;
use series;

create table if not exists image (
    id int auto_increment primary key,
    url varchar(255) not null,
    votes int not null
);

INSERT INTO image (url, votes) VALUES ('/images/ironman.jpg', 0);
INSERT INTO image (url, votes) VALUES ('/images/jocker.jpg', 0);
INSERT INTO image (url, votes) VALUES ('/images/spiderman.webp', 0);