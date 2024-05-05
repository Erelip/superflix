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