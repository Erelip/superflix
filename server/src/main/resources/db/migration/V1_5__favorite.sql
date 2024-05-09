CREATE TABLE IF NOT EXISTS favorite (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id bigint,
    visual_content_id bigint,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (visual_content_id) REFERENCES visual_content(id)
);