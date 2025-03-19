CREATE SCHEMA if not exists tennisScoreboard;
SET SCHEMA tennisScoreboard;

CREATE TABLE if not exists players (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    player VARCHAR(100) NOT NULL);

CREATE UNIQUE INDEX ON players (player);

CREATE TABLE if not exists matches (
    id INT2 PRIMARY KEY AUTO_INCREMENT,
    player1_id bigint,
    player2_id bigint,
    winner_id bigint,
    foreign key (player1_id) references players(id),
    foreign key (player2_id) references players(id),
    foreign key (winner_id) references players(id)
);