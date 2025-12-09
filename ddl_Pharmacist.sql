CREATE TABLE pharmacy.pharmacist
(
    cpr         BIGINT NOT NULL,
    name        VARCHAR(100),
    surname     VARCHAR(100),
    password    VARCHAR(50),
    phone       VARCHAR(20),
    birthday    date,
    gender      TEXT,
    pharmacy_id INTEGER,
    CONSTRAINT pk_pharmacist PRIMARY KEY (cpr)
);

ALTER TABLE pharmacy.pharmacist
    ADD CONSTRAINT FK_PHARMACIST_ON_PHARMACY FOREIGN KEY (pharmacy_id) REFERENCES pharmacy.pharmacy (id);