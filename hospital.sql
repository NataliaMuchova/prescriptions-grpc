SET search_path = "hospital";
DROP SCHEMA IF EXISTS "hospital" CASCADE;

CREATE SCHEMA "hospital";

CREATE TABLE "user"(
    name varchar(100),
    surname varchar(100),
    cpr bigint,
    password varchar(50),
    phone varchar(20),
    role varchar(50), -- how should we handle a person registered as both doctor/pharmacist and patient? how should our log in look like in front end in relation to this issue?
    -- if u just log in to the system with cpr, and it logs you into your role that will not work with our architecture?
    PRIMARY KEY (role, cpr)
);

CREATE TABLE doctor(
    id SERIAL PRIMARY KEY,
    cpr bigint,
    role varchar,
    FOREIGN KEY (role, cpr) REFERENCES "user"(role, cpr)
);

CREATE TABLE patient(
    id SERIAL PRIMARY KEY,
    cpr bigint,
    gender char(1),
    birthday DATE,
    role varchar,
    FOREIGN KEY (role, cpr) REFERENCES "user"(role, cpr)
);

CREATE TABLE drug(
    id SERIAL PRIMARY KEY,
    name varchar(100),
    description varchar(200),
    amount int
);


CREATE TABLE prescription(
    id SERIAL PRIMARY KEY,
    expiration_date DATE,
    issue_date DATE,
    patient_id bigint REFERENCES patient(id),
    doctor_id bigint REFERENCES doctor(id)

);

CREATE TABLE prescription_drug(
    id SERIAL PRIMARY KEY,
    drug_id int REFERENCES drug(id),
    note varchar(500),
    availability_count int,
    prescription_id int REFERENCES prescription(id)
);
