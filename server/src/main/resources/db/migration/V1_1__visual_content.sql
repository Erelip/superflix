CREATE TABLE IF NOT EXISTS visual_content (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    description TEXT,
    category VARCHAR(100),
    creator VARCHAR(50),
    release_at DATE
);