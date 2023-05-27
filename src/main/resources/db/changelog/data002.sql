--changeset db-data:2
INSERT INTO states (state_name, state_shortcut)
VALUES ('Germany', 'DE');
INSERT INTO states (state_name, state_shortcut)
VALUES ('France', 'FR');
INSERT INTO states (state_name, state_shortcut)
VALUES ('Spain', 'ES');
INSERT INTO states (state_name, state_shortcut)
VALUES ('Italy', 'IT');
INSERT INTO states (state_name, state_shortcut)
VALUES ('Netherlands', 'NL');
INSERT INTO states (state_name, state_shortcut)
VALUES ('Czech Republic', 'CZ');

INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Wenceslas Square 321', 'Prague', '110 00', (SELECT state_id FROM states WHERE state_shortcut = 'CZ'));
INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Champs-Élysées 101', 'Paris', '75008', (SELECT state_id FROM states WHERE state_shortcut = 'FR'));
INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Karlovo náměstí 456', 'Prague', '120 00', (SELECT state_id FROM states WHERE state_shortcut = 'CZ'));
INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Masarykova 789', 'Brno', '602 00', (SELECT state_id FROM states WHERE state_shortcut = 'CZ'));
INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Náměstí Republiky 101', 'Ostrava', '702 00', (SELECT state_id FROM states WHERE state_shortcut = 'CZ'));
INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Karlovo náměstí 1', 'Prague', '110 00', (SELECT state_id FROM states WHERE state_shortcut = 'CZ'));
INSERT INTO addresses (street_address, city, zip_code, state_id)
VALUES ('Staroměstské náměstí 1', 'Prague', '110 00', (SELECT state_id FROM states WHERE state_shortcut = 'CZ'));

INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('jsmith', 'password123', 'John', 'Smith', '1990-01-01', '+33 6 12 34 56 78', 'jsmith@example.com', 2);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('jdoe', 'secret', 'Jane', 'Doe', '1985-05-05', '+33 6 87 65 43 21', 'jdoe@example.com', 2);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('mjohnson', 'pa$$w0rd', 'Mark', 'Johnson', '1982-11-15', '+33 7 89 01 23 45', 'mjohnson@example.com', 2);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('lgarcia', 'qwerty', 'Lisa', 'Garcia', '1993-02-28', '+33 7 65 43 21 98', 'lgarcia@example.com', 2);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('dbrown', 'letmein', 'David', 'Brown', '1996-07-07', '+33 6 54 32 10 98', 'dbrown@example.com', 2);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('pnovak', '$2a$10$vb5ruqMLx8iIZrYuvsXNZusUZRh8yTgXPR.iiTF0aqhL4V5Bs3h9i', 'Pavel', 'Novák', '1995-03-21',
        '+420 777 888 999', 'pavel.novak@gmail.com', 3); /* heslo123 */
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('jsvobodova', 'heslo456', 'Jana', 'Svobodová', '1990-05-12', '+420 604 555 222', 'jana.svobodova@gmail.com', 4);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('pcech', 'heslo789', 'Petr', 'Čech', '1985-11-02', '+420 773 111 222', 'petr.cech@seznam.cz', 5);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('mkovarova', 'heslo111', 'Marie', 'Kovářová', '2000-01-15', '+420 603 444 555', 'marie.kovarova@volny.cz', 6);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('tvesely', 'heslo222', 'Tomáš', 'Veselý', '1993-07-31', '+420 605 777 888', 'tomas.vesely@centrum.cz', 7);

INSERT INTO bank_accounts (user_id, account_number, balance)
VALUES (6, 'CZ001234567890', 50000.00);
INSERT INTO bank_accounts (user_id, account_number, balance)
VALUES (1, 'FR123456789012', 1000.00);
INSERT INTO bank_accounts (user_id, account_number, balance)
VALUES (7, 'CZ987654321001', 20000.00);
INSERT INTO bank_accounts (user_id, account_number, balance)
VALUES (8, 'CZ345678901234', 100000.00);
INSERT INTO bank_accounts (user_id, account_number, balance)
VALUES (2, 'FR7649312621232022970279647', 1000.00);
INSERT INTO bank_accounts (user_id, account_number, balance)
VALUES (9, 'CZ5282017482794815468065', 500.50);

INSERT INTO transactions (user_id, account_id_recipient, account_id_sender, transaction_date, amount, description)
VALUES (6, 2, 1, NOW(), 10.00, 'Blueberry');
INSERT INTO transactions (user_id, account_id_recipient, account_id_sender, transaction_date, amount, description)
VALUES (6, 3, 1, NOW(), 50.00, 'Banana');
INSERT INTO transactions (user_id, account_id_recipient, account_id_sender, transaction_date, amount, description)
VALUES (7, 1, 3, NOW(), 75.00, 'Apple');
INSERT INTO transactions (user_id, account_id_recipient, account_id_sender, transaction_date, amount, description)
VALUES (8, 1, 4, NOW(), 100.00, 'Strawberry');
INSERT INTO transactions (user_id, account_id_recipient, account_id_sender, transaction_date, amount, description)
VALUES (6, 2, 1, NOW(), 10.00, 'Strawberry'),
       (6, 3, 1, NOW(), 15.00, 'Pear'),
       (6, 4, 1, NOW(), 20.00, 'Raspberry'),
       (6, 5, 1, NOW(), 12.50, 'Peach'),
       (6, 6, 1, NOW(), 8.75, 'Apple'),
       (6, 2, 1, NOW(), 30.00, 'Plum'),
       (6, 3, 1, NOW(), 18.50, 'Cherry'),
       (6, 4, 1, NOW(), 22.75, 'Apricot'),
       (6, 5, 1, NOW(), 14.25, 'Grapes'),
       (6, 6, 1, NOW(), 9.50, 'Banana'),
       (6, 2, 1, NOW(), 7.20, 'Lemon'),
       (6, 3, 1, NOW(), 13.80, 'Blueberry'),
       (6, 4, 1, NOW(), 11.25, 'Blackberry'),
       (6, 5, 1, NOW(), 17.80, 'Kiwi'),
       (6, 6, 1, NOW(), 5.50, 'Mango'),
       (6, 2, 1, NOW(), 21.60, 'Watermelon');