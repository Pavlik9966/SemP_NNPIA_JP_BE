--changeset db-data
/*CREATE TABLE user
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    username      VARCHAR(256) NOT NULL UNIQUE,
    password      VARCHAR(256) NOT NULL,
    creation_date TIMESTAMPTZ  NOT NULL
);

CREATE TABLE role
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (app_user_id, role_id)
);

ALTER TABLE user_role
    ADD CONSTRAINT user_role_user_id_fk FOREIGN KEY (user_id)
        REFERENCES user (id) ON DELETE CASCADE;

ALTER TABLE user_role
    ADD CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id)
        REFERENCES role (id) ON DELETE CASCADE;

ALTER TABLE user_role DROP CONSTRAINT user_role_user_id_fk;

ALTER TABLE user_role DROP CONSTRAINT user_role_role_id_fk;

INSERT INTO user (username, password, creation_date)
VALUES ('john_doe', 'password123', NOW());

INSERT INTO user (username, password, creation_date)
VALUES ('jane_doe', 'qwerty123', NOW());

INSERT INTO role (name)
VALUES ('Admin');

INSERT INTO role (name)
VALUES ('Guest');

-- Assign "Admin" role to user with id = 1
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);

-- Assign "Guest" role to user with id = 2
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2);*/