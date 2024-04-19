CREATE SCHEMA IF NOT EXISTS production;

CREATE TABLE IF NOT EXISTS production.users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR        NOT NULL,
    last_name  VARCHAR        NOT NULL,
    email      VARCHAR UNIQUE NOT NULL,
    password   VARCHAR        NOT NULL
);

CREATE TABLE IF NOT EXISTS production.cards
(
    id       BIGSERIAL PRIMARY KEY,
    number   BIGINT UNIQUE       NOT NULL,
    currency VARCHAR             NOT NULL,
    balance  DECIMAL DEFAULT (0) NOT NULL,
    user_id  BIGINT              NOT NULL,
    CONSTRAINT users_cards FOREIGN KEY (user_id) REFERENCES production.users (id)
);

CREATE TABLE IF NOT EXISTS production.transactions
(
    id        BIGSERIAL PRIMARY KEY,
    card_from BIGINT    NOT NULL,
    card_to   BIGINT    NOT NULL,
    currency  VARCHAR   NOT NULL,
    date_time TIMESTAMP NOT NULL,
    amount    DECIMAL   NOT NULL
);
