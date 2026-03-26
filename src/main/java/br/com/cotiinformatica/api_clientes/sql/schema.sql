CREATE TABLE CLIENTES(
    ID          SERIAL                 PRIMARY KEY,
    NOME        VARCHAR(100)           NOT NULL,
    CPF         CHAR(11)               NOT NULL UNIQUE
);

CREATE TABLE ENDERECOS(
    ID          SERIAL                 PRIMARY KEY,
    LOGRADOURO  VARCHAR(200)           NOT NULL,
    NUMERO      VARCHAR(25)            NOT NULL,
    COMPLEMENTO VARCHAR(150)           NOT NULL,
    BAIRRO      VARCHAR(100)           NOT NULL,
    CIDADE      VARCHAR(50)            NOT NULL,
    UF          CHAR(2)                NOT NULL,
    CEP         CHAR(8)                NOT NULL,
    CLIENTE_ID  INTEGER                NOT NULL,
    FOREIGN KEY (CLIENTE_ID)
        REFERENCES CLIENTES(ID)
);
