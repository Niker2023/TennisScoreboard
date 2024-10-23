CREATE SCHEMA if not exists tennisScoreboard;
SET SCHEMA tennisScoreboard;

CREATE TABLE if not exists players (
    id INT2 PRIMARY KEY AUTO_INCREMENT,
    player VARCHAR(100) NOT NULL);

CREATE TABLE if not exists matches (
    id INT2 PRIMARY KEY AUTO_INCREMENT,
    player1 int2,
    player2 int2,
    winner int2,
    foreign key (player1) references players(id),
    foreign key (player2) references players(id),
    foreign key (winner) references players(id)
);
