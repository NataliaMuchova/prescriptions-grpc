SET search_path = "hospital";

TRUNCATE "user" CASCADE;
TRUNCATE drug CASCADE;

INSERT INTO "user" VALUES(1234567890, 'patient', 'Millie','Magenta', '123', '58694725', '2000-09-19', 'M');
INSERT INTO "user" VALUES(1122334455, 'patient', 'Jane','Doe', '112', '23548625', '1999-11-05', 'F');
INSERT INTO "user" VALUES(123, 'doctor', 'John','Travolta', '998', '55224488', '1985-04-30', 'M');
INSERT INTO "user" VALUES(111, 'doctor', 'Mike','Wazowski', '987', '42548745', '1995-06-15', 'M');

INSERT INTO drug VALUES (1,'Xanax', 'It is supposed to help', 21);
INSERT INTO drug VALUES (2,'Aulin', 'For severe pain', 42);

INSERT INTO prescription VALUES (1,'2028-11-18', now(), 1234567890, 123);
INSERT INTO prescription VALUES (3,'2029-02-03',now(), 1122334455, 111);

INSERT INTO prescription_drug VALUES (3, 1, 'It is supposed to help', 23, 1);
INSERT INTO prescription_drug VALUES (5,2, 'For severe pain', 9,3);