CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(30)  NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(20)  NOT NULL,
    created_at  DATETIME     NOT NULL,
    modified_at DATETIME     NOT NULL
);

CREATE TABLE todo
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT   NOT NULL,
    title       TEXT     NOT NULL,
    contents    TEXT     NOT NULL,
    created_at  DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id)
);