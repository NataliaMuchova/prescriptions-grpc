SET search_path = "hospital";

TRUNCATE "user" CASCADE;
TRUNCATE drug CASCADE;

INSERT INTO "user" VALUES ('Jena', 'Magenta', 1233445566, '123', '23346789', 'patient');
INSERT INTO patient VALUES (111, 1233445566, 'F', '2000-09-19', 'patient');
INSERT INTO "user" VALUES ('John', 'Pork', 1122445533, '456', '11223355', 'patient');
INSERT INTO patient VALUES (122,1122445533, 'M', '1978-01-16', 'patient');

INSERT INTO "user" VALUES ('Joseph', 'PRO', 1344557788, '789', '9988776655', 'doctor');
INSERT INTO doctor VALUES (123, 1344557788, 'doctor');
INSERT INTO "user" VALUES ('Jena', 'Magenta', 1233445566, 'test', '23346789', 'doctor');
INSERT INTO doctor VALUES (111,1233445566, 'doctor');

INSERT INTO drug VALUES (1,'Xanax', 'It is supposed to help', 21);
INSERT INTO drug VALUES (2,'Aulin', 'For severe pain', 42);

INSERT INTO prescription VALUES (1,'2028-11-18', now(), 111, 111);
INSERT INTO prescription VALUES (3,'2029-02-03',now(), 122, 123);

INSERT INTO prescription_drug VALUES (3, 1, 'It is supposed to help', 23, 1);
INSERT INTO prescription_drug VALUES (5,2, 'For severe pain', 9,3);