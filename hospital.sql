SET search_path = "hospital";
DROP SCHEMA IF EXISTS "hospital" CASCADE;

CREATE SCHEMA "hospital";

CREATE TABLE "user"(
    cpr bigint PRIMARY KEY,
    role varchar(50) CHECK (role IN ('patient', 'doctor', 'pharmacist')),
    name varchar(100),
    surname varchar(100),
    password varchar(50),
    phone varchar(20),
    birthday DATE, -- birthday and gender moved to user from patient table that no longer exist
    gender char(1)
     -- how should we handle a person registered as both doctor/pharmacist and patient? how should our log in look like in front end in relation to this issue?
    -- if u just log in to the system with cpr, and it logs you into your role that will not work with our architecture?
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
    patient_cpr bigint REFERENCES "user"(cpr),
    doctor_cpr bigint REFERENCES "user"(cpr)
);

CREATE TABLE prescription_drug(
    id SERIAL PRIMARY KEY,
    drug_id int REFERENCES drug(id),
    note varchar(500),
    availability_count int,
    prescription_id int REFERENCES prescription(id)
);
