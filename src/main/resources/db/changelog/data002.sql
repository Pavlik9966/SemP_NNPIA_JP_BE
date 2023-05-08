--changeset db-data:2
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

/*CREATE TABLE account_types
(
    account_type_id SERIAL PRIMARY KEY,
    account_type    VARCHAR(50) UNIQUE NOT NULL
);*/

/*CREATE TABLE currencies
(
    currency_id   SERIAL PRIMARY KEY,
    currency_code VARCHAR(10)  UNIQUE NOT NULL,
    name          VARCHAR(100) UNIQUE NOT NULL,
    symbol        VARCHAR(10)  NOT NULL
);*/

CREATE TABLE bank_accounts
(
    account_id     SERIAL PRIMARY KEY,
    user_id        INTEGER            NOT NULL REFERENCES users (user_id),
    account_number VARCHAR(50) UNIQUE NOT NULL,
    /*account_type_id INTEGER      NOT NULL REFERENCES account_types (account_type_id),*/
    balance        REAL               NOT NULL DEFAULT 0,
    /*currency_id     INTEGER      NOT NULL REFERENCES currencies (currency_id),*/
    created_at     TIMESTAMP          NOT NULL DEFAULT NOW()
);

/*CREATE TABLE card_types
(
    card_type_id SERIAL PRIMARY KEY,
    card_type    VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE credit_cards
(
    card_id      SERIAL PRIMARY KEY,
    user_id      INTEGER      NOT NULL REFERENCES users (user_id),
    card_number  VARCHAR(50) UNIQUE NOT NULL,
    card_type_id INTEGER      NOT NULL REFERENCES card_types (card_type_id),
    credit_limit REAL        NOT NULL DEFAULT 0,
    balance      REAL        NOT NULL DEFAULT 0,
    currency_id  INTEGER      NOT NULL REFERENCES currencies (currency_id),
    created_at   TIMESTAMP   NOT NULL DEFAULT NOW()
);*/

/*CREATE TABLE transaction_types
(
    transaction_type_id SERIAL PRIMARY KEY,
    transaction_type    VARCHAR(20) UNIQUE NOT NULL
);*/

CREATE TABLE transactions
(
    transaction_id   SERIAL PRIMARY KEY,
    user_id          INTEGER   NOT NULL REFERENCES users (user_id),
    account_id       INTEGER   NOT NULL REFERENCES bank_accounts (account_id),
    /*card_id             INTEGER    NOT NULL REFERENCES credit_cards (card_id),*/
    transaction_date TIMESTAMP NOT NULL DEFAULT NOW(),
    /*transaction_type_id INTEGER    NOT NULL REFERENCES transaction_types (transaction_type_id),*/
    amount           REAL      NOT NULL DEFAULT 0,
    /*balance             REAL      NOT NULL DEFAULT 0,*/
    /*currency_id         INTEGER    NOT NULL REFERENCES currencies (currency_id),*/
    created_at       TIMESTAMP NOT NULL DEFAULT NOW()
);

-- CREATE TABLE languages
-- (
--     language_id   SERIAL PRIMARY KEY,
--     language_name VARCHAR(50) UNIQUE NOT NULL
-- );
--
-- CREATE TABLE timezones
-- (
--     timezone_id   SERIAL PRIMARY KEY,
--     timezone_code VARCHAR(50)  UNIQUE NOT NULL,
--     timezone_name VARCHAR(100) UNIQUE NOT NULL
-- );
--
-- CREATE TABLE preferences
-- (
--     preference_id       SERIAL PRIMARY KEY,
--     user_id             INTEGER  NOT NULL REFERENCES users (user_id),
--     language_id         INTEGER  NOT NULL REFERENCES languages (language_id),
--     timezone_id         INTEGER  NOT NULL REFERENCES timezones (timezone_id),
--     email_notifications BOOLEAN NOT NULL,
--     sms_notifications   BOOLEAN NOT NULL
-- );