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

INSERT INTO players(id, player) VALUES (1, 'D. Medvedev');
INSERT INTO players(id, player) VALUES (2, 'B. Djokovic');

INSERT INTO matches(id, player1_id, player2_id, winner_id) VALUES (1, 1,2,1);