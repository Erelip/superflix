DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) UNIQUE,
    firstname VARCHAR(25),
    lastname VARCHAR(25),
    password VARCHAR(100)
);

DROP TABLE IF EXISTS role;
CREATE TABLE IF NOT EXISTS role (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) UNIQUE
);

DROP TABLE IF EXISTS episode;
DROP TABLE IF EXISTS season;
DROP TABLE IF EXISTS serie;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS visual_content;
CREATE TABLE IF NOT EXISTS visual_content (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    description TEXT,
    category VARCHAR(100),
    creator VARCHAR(50),
    release_at DATE
);

CREATE TABLE IF NOT EXISTS movie (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    duration INT,
    path VARCHAR(100),
    visual_content_id bigint REFERENCES visual_content(id)
);

CREATE TABLE IF NOT EXISTS serie (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number_of_seasons INT,
    visual_content_id bigint UNIQUE,
    FOREIGN KEY (visual_content_id) REFERENCES visual_content(id)
);

CREATE TABLE IF NOT EXISTS season (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number_of_episode INT,
    serie_id bigint,
    release_at DATE,
    FOREIGN KEY (serie_id) REFERENCES serie(id)
);

CREATE TABLE IF NOT EXISTS episode (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    number INT,
    season_id bigint,
    path VARCHAR(100),
    release_at DATE,
    FOREIGN KEY (season_id) REFERENCES season(id)
);