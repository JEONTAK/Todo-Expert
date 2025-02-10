CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(20)  NOT NULL,
    email       VARCHAR(40) NOT NULL,
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

CREATE TABLE comment
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo_id     BIGINT   NOT NULL,
    user_id     BIGINT   NOT NULL,
    contents    TEXT     NOT NULL,
    created_at  DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    CONSTRAINT fk_todo FOREIGN KEY (todo_id) REFERENCES todo (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id)
);