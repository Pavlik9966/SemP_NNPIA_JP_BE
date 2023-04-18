CREATE TABLE states
(
    state_id       SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    state_name     VARCHAR(50) NOT NULL UNIQUE,
    state_shortcut VARCHAR(8)  NOT NULL UNIQUE
);

CREATE TABLE addresses
(
    address_id     SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    street_address VARCHAR(200) NOT NULL,
    city           VARCHAR(100) NOT NULL,
    zip_code       VARCHAR(20)  NOT NULL UNIQUE,
    state_id       SERIAL       NOT NULL REFERENCES states (state_id)
);

CREATE TABLE users
(
    user_id       SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    username      VARCHAR(256) NOT NULL UNIQUE,
    password      VARCHAR(50)  NOT NULL,
    name          VARCHAR(256) NOT NULL,
    surname       VARCHAR(256) NOT NULL,
    date_of_birth DATE         NOT NULL,
    phone         VARCHAR(20)  NOT NULL,
    email         VARCHAR(100) NOT NULL,
    address_id    SERIAL       NOT NULL REFERENCES addresses (address_id),
    created_at    TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    name    VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles
(
    user_id SERIAL NOT NULL REFERENCES users (user_id),
    role_id SERIAL NOT NULL REFERENCES roles (role_id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE account_types
(
    account_type_id SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    account_type    VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE currencies
(
    currency_id   SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    currency_code VARCHAR(10)  NOT NULL UNIQUE,
    name          VARCHAR(100) NOT NULL UNIQUE,
    symbol        VARCHAR(10)  NOT NULL
);

CREATE TABLE bank_accounts
(
    account_id      SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    user_id         SERIAL      NOT NULL REFERENCES users (user_id),
    account_number  VARCHAR(50) NOT NULL UNIQUE,
    account_type_id SERIAL      NOT NULL REFERENCES account_types (account_type_id),
    balance         REAL        NOT NULL DEFAULT 0,
    currency_id     SERIAL      NOT NULL REFERENCES currencies (currency_id),
    created_at      TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE TABLE card_types
(
    card_type_id SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    card_type    VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE credit_cards
(
    card_id      SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    user_id      SERIAL      NOT NULL REFERENCES users (user_id),
    card_number  VARCHAR(50) NOT NULL UNIQUE,
    card_type_id SERIAL      NOT NULL REFERENCES card_types (card_type_id),
    credit_limit REAL        NOT NULL DEFAULT 0,
    balance      REAL        NOT NULL DEFAULT 0,
    currency_id  SERIAL      NOT NULL REFERENCES currencies (currency_id),
    created_at   TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE TABLE transaction_types
(
    transaction_type_id SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    transaction_type    VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE transactions
(
    transaction_id      SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
    user_id             SERIAL    NOT NULL REFERENCES users (user_id),
    account_id          SERIAL    NOT NULL REFERENCES bank_accounts (account_id),
    card_id             SERIAL    NOT NULL REFERENCES credit_cards (card_id),
    transaction_date    TIMESTAMP NOT NULL DEFAULT NOW(),
    transaction_type_id SERIAL    NOT NULL REFERENCES transaction_types (transaction_type_id),
    amount              REAL      NOT NULL DEFAULT 0,
    balance             REAL      NOT NULL DEFAULT 0,
    currency_id         SERIAL    NOT NULL REFERENCES currencies (currency_id),
    created_at          TIMESTAMP NOT NULL DEFAULT NOW()
);

-- CREATE TABLE languages
-- (
--     language_id   SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
--     language_name VARCHAR(50) NOT NULL UNIQUE
-- );
--
-- CREATE TABLE timezones
-- (
--     timezone_id   SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
--     timezone_code VARCHAR(50)  NOT NULL UNIQUE,
--     timezone_name VARCHAR(100) NOT NULL UNIQUE
-- );
--
-- CREATE TABLE preferences
-- (
--     preference_id       SERIAL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (INCREMENT BY 1 START WITH 0),
--     user_id             SERIAL  NOT NULL REFERENCES users (user_id),
--     language_id         SERIAL  NOT NULL REFERENCES languages (language_id),
--     timezone_id         SERIAL  NOT NULL REFERENCES timezones (timezone_id),
--     email_notifications BOOLEAN NOT NULL,
--     sms_notifications   BOOLEAN NOT NULL
-- );