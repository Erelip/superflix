CREATE TABLE IF NOT EXISTS season (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number_of_episodes INT,
    serie_id bigint,
    release_at DATE,
    FOREIGN KEY (serie_id) REFERENCES serie(id)
);