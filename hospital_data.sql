SET search_path = "hospital";

TRUNCATE "user" CASCADE;
TRUNCATE drug CASCADE;

INSERT INTO "user" VALUES(1234567890, 'patient', 'Millie','Magenta', '123', '58694725', '2000-09-19', 'M');
INSERT INTO "user" VALUES(1122334455, 'patient', 'Jane','Doe', '112', '23548625', '1999-11-05', 'F');
INSERT INTO "user" VALUES(1231231231, 'doctor', 'John','Travolta', '998', '55224488', '1985-04-30', 'M');
INSERT INTO "user" VALUES(1111111111, 'doctor', 'Mike','Wazowski', '987', '42548745', '1995-06-15', 'M');
INSERT INTO "user" VALUES(1212121212, 'pharmacist', 'John','Travolta', '1', '55224488', '1985-04-30', 'M');
INSERT INTO "user" VALUES(2121212121, 'pharmacist', 'Mike','Wazowski', '2', '42548745', '1995-06-15', 'M');

INSERT INTO drug VALUES ('Xanax', 'It is supposed to help');
INSERT INTO drug VALUES ('Aulin', 'For severe pain');

INSERT INTO prescription (expiration_date, issue_date, patient_cpr, doctor_cpr) VALUES ('2028-11-18', now(), 111, 123);
INSERT INTO prescription (expiration_date, issue_date, patient_cpr, doctor_cpr) VALUES ('2029-02-03',now(), 123, 111);

INSERT INTO prescription_drug (drug_id, prescription_id, note, availability_count, starting_amount) VALUES ('Xanax',1, 'siema', 5, 5);
INSERT INTO prescription_drug (drug_id, prescription_id, note, availability_count, starting_amount) VALUES ('Aulin', 2, 'test', 10, 15);