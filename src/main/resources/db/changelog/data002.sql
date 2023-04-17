CREATE SEQUENCE user_id_seq START WITH 1;
CREATE SEQUENCE state_id_seq START WITH 1;
CREATE SEQUENCE address_id_seq START WITH 1;
CREATE SEQUENCE account_type_id_seq START WITH 1;
CREATE SEQUENCE currency_id_seq START WITH 1;
CREATE SEQUENCE bank_account_id_seq START WITH 1;
CREATE SEQUENCE card_type_id_seq START WITH 1;
CREATE SEQUENCE credit_card_id_seq START WITH 1;
CREATE SEQUENCE transaction_type_id_seq START WITH 1;
CREATE SEQUENCE transaction_id_seq START WITH 1;
CREATE SEQUENCE language_id_seq START WITH 1;
CREATE SEQUENCE timezone_id_seq START WITH 1;
CREATE SEQUENCE preference_id_seq START WITH 1;

CREATE TABLE states
(
    state_id       INT PRIMARY KEY DEFAULT nextval('state_id_seq'),
    state_name     VARCHAR(50) NOT NULL,
    state_shortcut VARCHAR(8)  NOT NULL
);

CREATE TABLE addresses
(
    address_id     INT PRIMARY KEY DEFAULT nextval('address_id_seq'),
    street_address VARCHAR(200) NOT NULL,
    city           VARCHAR(100) NOT NULL,
    zip_code       VARCHAR(20)  NOT NULL,
    state_id       INT          NOT NULL REFERENCES states (state_id)
);

CREATE TABLE users
(
    user_id       INT PRIMARY KEY DEFAULT nextval('user_id_seq'),
    username      VARCHAR(50) UNIQUE NOT NULL,
    password      VARCHAR(50)        NOT NULL,
    name          VARCHAR(50)        NOT NULL,
    surname       VARCHAR(50)        NOT NULL,
    date_of_birth DATE               NOT NULL,
    phone         VARCHAR(20)        NOT NULL,
    email         VARCHAR(100)       NOT NULL,
    address_id    INT                NOT NULL REFERENCES addresses (address_id),
    created_at    TIMESTAMP       DEFAULT NOW()
);

CREATE TABLE account_types
(
    account_type_id INT PRIMARY KEY DEFAULT nextval('account_type_id_seq'),
    account_type    VARCHAR(50) NOT NULL
);

CREATE TABLE currencies
(
    currency_id   INT PRIMARY KEY DEFAULT nextval('currency_id_seq'),
    currency_code VARCHAR(10) UNIQUE NOT NULL,
    name          VARCHAR(100)       NOT NULL,
    symbol        VARCHAR(10)        NOT NULL
);

CREATE TABLE bank_accounts
(
    account_id      INT PRIMARY KEY             DEFAULT nextval('bank_account_id_seq'),
    user_id         INT                NOT NULL REFERENCES users (user_id),
    account_number  VARCHAR(50) UNIQUE NOT NULL,
    account_type_id INT                NOT NULL REFERENCES account_types (account_type_id),
    balance         DECIMAL(10, 2)     NOT NULL DEFAULT 0,
    currency_id     INT                NOT NULL REFERENCES currencies (currency_id),
    created_at      TIMESTAMP                   DEFAULT NOW()
);

CREATE TABLE card_types
(
    card_type_id INT PRIMARY KEY DEFAULT nextval('card_type_id_seq'),
    card_type    VARCHAR(50) NOT NULL
);

CREATE TABLE credit_cards
(
    card_id      INT PRIMARY KEY             DEFAULT nextval('credit_card_id_seq'),
    user_id      INT                NOT NULL REFERENCES users (user_id),
    card_number  VARCHAR(50) UNIQUE NOT NULL,
    card_type_id INT                NOT NULL REFERENCES card_types (card_type_id),
    credit_limit DECIMAL(10, 2)     NOT NULL DEFAULT 0,
    balance      DECIMAL(10, 2)     NOT NULL DEFAULT 0,
    currency_id  INT                NOT NULL REFERENCES currencies (currency_id),
    created_at   TIMESTAMP                   DEFAULT NOW()
);

CREATE TABLE transaction_types
(
    transaction_type_id INT PRIMARY KEY DEFAULT nextval('transaction_type_id_seq'),
    transaction_type    VARCHAR(20) NOT NULL
);

CREATE TABLE transactions
(
    transaction_id      INT PRIMARY KEY         DEFAULT nextval('transaction_id_seq'),
    user_id             INT            NOT NULL REFERENCES users (user_id),
    account_id          INT            NOT NULL REFERENCES bank_accounts (account_id),
    card_id             INT            NOT NULL REFERENCES credit_cards (card_id),
    transaction_date    TIMESTAMP      NOT NULL DEFAULT NOW(),
    transaction_type_id INT            NOT NULL REFERENCES transaction_types (transaction_type_id),
    amount              DECIMAL(10, 2) NOT NULL,
    balance             DECIMAL(10, 2) NOT NULL,
    currency_id         INT            NOT NULL REFERENCES currencies (currency_id),
    created_at          TIMESTAMP               DEFAULT NOW()
);

CREATE TABLE languages
(
    language_id   INT PRIMARY KEY DEFAULT nextval('language_id_seq'),
    language_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE timezones
(
    timezone_id   INT PRIMARY KEY DEFAULT nextval('timezone_id_seq'),
    timezone_code VARCHAR(50)  NOT NULL UNIQUE,
    timezone_name VARCHAR(100) NOT NULL
);

CREATE TABLE preferences
(
    preference_id       INT PRIMARY KEY DEFAULT nextval('preference_id_seq'),
    user_id             INT     NOT NULL REFERENCES users (user_id),
    language_id         INT     NOT NULL REFERENCES languages (language_id),
    timezone_id         INT     NOT NULL REFERENCES timezones (timezone_id),
    email_notifications BOOLEAN NOT NULL,
    sms_notifications   BOOLEAN NOT NULL
);