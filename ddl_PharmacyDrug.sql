CREATE TABLE pharmacy.pharmacy_drug
(
    name          VARCHAR(100) NOT NULL,
    description   VARCHAR(200),
    stock         INTEGER,
    price         DOUBLE PRECISION,
    reorder_level INTEGER,
    pharmacy_id   INTEGER,
    CONSTRAINT pk_pharmacy_drug PRIMARY KEY (name)
);

ALTER TABLE pharmacy.pharmacy_drug
    ADD CONSTRAINT FK_PHARMACY_DRUG_ON_PHARMACY FOREIGN KEY (pharmacy_id) REFERENCES pharmacy.pharmacy (id);