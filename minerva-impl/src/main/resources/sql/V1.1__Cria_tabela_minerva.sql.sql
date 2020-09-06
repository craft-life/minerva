-- Cria as tabelas da API

CREATE TABLE IF NOT EXISTS firstlogin (
    username VARCHAR(16) NOT NULL,
    ts_login INT NOT NULL,
    PRIMARY KEY(ts_login)
);