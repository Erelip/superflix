CREATE TABLE IF NOT EXISTS episode (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number INT,
    season_id bigint,
    path VARCHAR(100),
    release_at DATE,
    duration INT,
    FOREIGN KEY (season_id) REFERENCES season(id)
);