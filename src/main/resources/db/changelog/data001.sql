--changeset db-data:1
CREATE TABLE states
(
    state_id       SERIAL PRIMARY KEY,
    state_name     VARCHAR(50) UNIQUE NOT NULL,
    state_shortcut VARCHAR(8) UNIQUE  NOT NULL
);

CREATE TABLE addresses
(
    address_id     SERIAL PRIMARY KEY,
    street_address VARCHAR(200) NOT NULL,
    city           VARCHAR(100) NOT NULL,
    zip_code       VARCHAR(20)  NOT NULL,
    state_id       INTEGER      NOT NULL REFERENCES states (state_id)
);

CREATE TABLE users
(
    user_id       SERIAL PRIMARY KEY,
    username      VARCHAR(256) UNIQUE NOT NULL,
    password      VARCHAR(256)        NOT NULL,
    name          VARCHAR(256)        NOT NULL,
    surname       VARCHAR(256)        NOT NULL,
    date_of_birth DATE                NOT NULL,
    phone         VARCHAR(20)         NOT NULL,
    email         VARCHAR(100)        NOT NULL,
    address_id    INTEGER             NOT NULL REFERENCES addresses (address_id),
    created_at    TIMESTAMP           NOT NULL DEFAULT NOW()
);

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    name    VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL REFERENCES users (user_id),
    role_id INTEGER NOT NULL REFERENCES roles (role_id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE bank_accounts
(
    account_id     SERIAL PRIMARY KEY,
    user_id        INTEGER            NOT NULL REFERENCES users (user_id),
    account_number VARCHAR(50) UNIQUE NOT NULL,
    balance        REAL               NOT NULL DEFAULT 0,
    created_at     TIMESTAMP          NOT NULL DEFAULT NOW()
);

CREATE TABLE transactions
(
    transaction_id       SERIAL PRIMARY KEY,
    user_id              INTEGER      NOT NULL REFERENCES users (user_id),
    account_id_recipient INTEGER      NOT NULL REFERENCES bank_accounts (account_id),
    account_id_sender    INTEGER      NOT NULL REFERENCES bank_accounts (account_id),
    transaction_date     TIMESTAMP    NOT NULL DEFAULT NOW(),
    amount               REAL         NOT NULL DEFAULT 0,
    created_at           TIMESTAMP    NOT NULL DEFAULT NOW(),
    description          VARCHAR(256) NOT NULL
);