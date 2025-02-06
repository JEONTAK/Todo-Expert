CREATE TABLE todo
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(40) NOT NULL,
    title       TEXT        NOT NULL,
    contents    TEXT        NOT NULL,
    created_at  DATETIME    NOT NULL,
    modified_at DATETIME    NOT NULL
);

