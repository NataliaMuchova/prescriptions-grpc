SET search_path = "hospital";
DROP SCHEMA IF EXISTS "hospital" CASCADE;

CREATE SCHEMA "hospital";

CREATE TABLE "user"(
    cpr bigint PRIMARY KEY,
    role varchar(50) CHECK (role IN ('patient', 'doctor', 'pharmacist')),
    name varchar(100),
    surname varchar(100),
    password varchar(100),
    phone varchar(20),
    birthday DATE, -- birthday and gender moved to user from patient table that no longer exist
    gender char(1)
     -- how should we handle a person registered as both doctor/pharmacist and patient? how should our log in look like in front end in relation to this issue?
    -- if u just log in to the system with cpr, and it logs you into your role that will not work with our architecture?
    );

CREATE TABLE hospital.drug
(
    name          VARCHAR(100) NOT NULL,
    description VARCHAR(200) NULL,
    CONSTRAINT pk_drug PRIMARY KEY (name)
    );

CREATE TABLE prescription(
    id SERIAL PRIMARY KEY,
    expiration_date DATE,
    issue_date DATE,
    patient_cpr bigint REFERENCES "user"(cpr),
    doctor_cpr bigint REFERENCES "user"(cpr)
);

CREATE TABLE hospital.prescription_drug
(
    id                 SERIAL NOT NULL,
    drug_id            VARCHAR(100)       NULL,
    prescription_id    INT                NULL,
    note               VARCHAR(500)       NULL,
    availability_count INT                NULL,
    starting_amount    INT                NULL,
    CONSTRAINT pk_prescription_drug PRIMARY KEY (id)
);

ALTER TABLE hospital.prescription_drug
    ADD CONSTRAINT FK_PRESCRIPTION_DRUG_ON_DRUG FOREIGN KEY (drug_id) REFERENCES hospital.drug (name);

ALTER TABLE hospital.prescription_drug
    ADD CONSTRAINT FK_PRESCRIPTION_DRUG_ON_PRESCRIPTION FOREIGN KEY (prescription_id) REFERENCES hospital.prescription (id);

