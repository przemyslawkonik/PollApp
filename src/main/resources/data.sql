INSERT INTO role (role) VALUES ('USER');
INSERT INTO role (role) VALUES ('ADMIN');

INSERT INTO data (age, firstName, lastName, gender) VALUES (5, 'Maciej', 'Kowalski', 'MALE');
INSERT INTO data (age, firstName, lastName, gender) VALUES (5, 'Ania', 'Kowalska', 'FEMALE');

INSERT INTO account (username, email, password, role_id, data_id) VALUES ('maciek', 'maciek@gmail.com', 'pass', 1, 1);
INSERT INTO account (username, email, password, role_id, data_id) VALUES ('ania', 'ania@gmail.com', 'pass', 2, 2);
