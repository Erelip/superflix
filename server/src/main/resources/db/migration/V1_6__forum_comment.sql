CREATE TABLE IF NOT EXISTS forum (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    episode_id bigint,
    FOREIGN KEY (episode_id) REFERENCES episode(id)
);

CREATE TABLE IF NOT EXISTS comment (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    forum_id bigint,
    user_id bigint,
    content TEXT,
    FOREIGN KEY (forum_id) REFERENCES forum(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);