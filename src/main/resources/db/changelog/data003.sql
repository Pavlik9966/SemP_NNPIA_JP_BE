--changeset db-data:3
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

/*INSERT INTO account_types (account_type)
VALUES ('Savings');
INSERT INTO account_types (account_type)
VALUES ('Checking');
INSERT INTO account_types (account_type)
VALUES ('Money Market');
INSERT INTO account_types (account_type)
VALUES ('CD');
INSERT INTO account_types (account_type)
VALUES ('IRA');*/

/*INSERT INTO currencies (currency_code, name, symbol)
VALUES ('USD', 'United States Dollar', '$');
INSERT INTO currencies (currency_code, name, symbol)
VALUES ('EUR', 'Euro', '€');
INSERT INTO currencies (currency_code, name, symbol)
VALUES ('GBP', 'British Pound', '£');
INSERT INTO currencies (currency_code, name, symbol)
VALUES ('JPY', 'Japanese Yen', '¥');
INSERT INTO currencies (currency_code, name, symbol)
VALUES ('CAD', 'Canadian Dollar', '$');
INSERT INTO currencies (currency_code, name, symbol)
VALUES ('CZK', 'Czech crown', 'Kč');*/

/*INSERT INTO card_types (card_type)
VALUES ('Visa');
INSERT INTO card_types (card_type)
VALUES ('Mastercard');
INSERT INTO card_types (card_type)
VALUES ('American Express');
INSERT INTO card_types (card_type)
VALUES ('Discover');
INSERT INTO card_types (card_type)
VALUES ('Diners Club');*/

/*INSERT INTO transaction_types (transaction_type)
VALUES ('Deposit');
INSERT INTO transaction_types (transaction_type)
VALUES ('Withdrawal');
INSERT INTO transaction_types (transaction_type)
VALUES ('Transfer');
INSERT INTO transaction_types (transaction_type)
VALUES ('Payment');
INSERT INTO transaction_types (transaction_type)
VALUES ('Refund');*/

/*INSERT INTO languages (language_name)
VALUES ('English');
INSERT INTO languages (language_name)
VALUES ('Spanish');
INSERT INTO languages (language_name)
VALUES ('French');
INSERT INTO languages (language_name)
VALUES ('German');
INSERT INTO languages (language_name)
VALUES ('Italian');
INSERT INTO languages (language_name)
VALUES ('Czech');*/

/*INSERT INTO timezones (timezone_code, timezone_name)
VALUES ('UTC+01:00', 'Central European Time');
INSERT INTO timezones (timezone_code, timezone_name)
VALUES ('UTC+02:00', 'Central European Summer Time');
INSERT INTO timezones (timezone_code, timezone_name)
VALUES ('UTC+03:00', 'Eastern European Time');
INSERT INTO timezones (timezone_code, timezone_name)
VALUES ('UTC+04:00', 'Eastern European Summer Time');
INSERT INTO timezones (timezone_code, timezone_name)
VALUES ('UTC+05:00', 'Further-eastern European Time');*/

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
VALUES ('pnovak', 'heslo123', 'Pavel', 'Novák', '1995-03-21', '+420 777 888 999', 'pavel.novak@gmail.com', 3);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('jsvobodova', 'heslo456', 'Jana', 'Svobodová', '1990-05-12', '+420 604 555 222', 'jana.svobodova@gmail.com', 4);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('pcech', 'heslo789', 'Petr', 'Čech', '1985-11-02', '+420 773 111 222', 'petr.cech@seznam.cz', 5);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('mkovarova', 'heslo111', 'Marie', 'Kovářová', '2000-01-15', '+420 603 444 555', 'marie.kovarova@volny.cz', 6);
INSERT INTO users (username, password, name, surname, date_of_birth, phone, email, address_id)
VALUES ('tvesely', 'heslo222', 'Tomáš', 'Veselý', '1993-07-31', '+420 605 777 888', 'tomas.vesely@centrum.cz', 7);

INSERT INTO bank_accounts (user_id, account_number/*, account_type_id*/, balance/*, currency_id*/)
VALUES (6, 'CZ001234567890'/*, 1*/, 50000.00/*, 6*/);
INSERT INTO bank_accounts (user_id, account_number/*, account_type_id*/, balance/*, currency_id*/)
VALUES (1, 'FR123456789012'/*, 2*/, 1000.00/*, 2*/);
INSERT INTO bank_accounts (user_id, account_number/*, account_type_id*/, balance/*, currency_id*/)
VALUES (7, 'CZ987654321001'/*, 1*/, 20000.00/*, 6*/);
INSERT INTO bank_accounts (user_id, account_number/*, account_type_id*/, balance/*, currency_id*/)
VALUES (8, 'CZ345678901234'/*, 3*/, 100000.00/*, 6*/);
INSERT INTO bank_accounts (user_id, account_number/*, account_type_id*/, balance/*, currency_id*/)
VALUES (2, 'FR7649312621232022970279647'/*, 1*/, 1000.00/*, 2*/);
INSERT INTO bank_accounts (user_id, account_number/*, account_type_id*/, balance/*, currency_id*/)
VALUES (9, 'CZ5282017482794815468065'/*, 2*/, 500.50/*, 6*/);

/*INSERT INTO credit_cards (user_id, card_number, card_type_id, credit_limit, balance, currency_id)
VALUES (1, '4539318009347412', 1, 5000.00, 1000.00, 2);
INSERT INTO credit_cards (user_id, card_number, card_type_id, credit_limit, balance, currency_id)
VALUES (2, '5571191671658009', 2, 3000.00, 1000.00, 2);
INSERT INTO credit_cards (user_id, card_number, card_type_id, credit_limit, balance, currency_id)
VALUES (6, '6011256752553484', 3, 10000.00, 50000.00, 6);
INSERT INTO credit_cards (user_id, card_number, card_type_id, credit_limit, balance, currency_id)
VALUES (7, '4556053037826242', 1, 8000.00, 20000.00, 6);
INSERT INTO credit_cards (user_id, card_number, card_type_id, credit_limit, balance, currency_id)
VALUES (8, '5122304560398771', 2, 5000.00, 100000.00, 6);*/

INSERT INTO transactions (user_id, account_id/*, card_id*/, transaction_date/*, transaction_type_id*/, amount/*, balance,
                          currency_id*/)
VALUES (6, 1/*, 3*/, NOW()/*, 4*/, 10.00/*, 50050.00, 6*/);
INSERT INTO transactions (user_id, account_id/*, card_id*/, transaction_date/*, transaction_type_id*/, amount/*, balance,
                          currency_id*/)
VALUES (6, 1/*, 3*/, NOW()/*, 4*/, 50.00/*, 50000.00, 6*/);
INSERT INTO transactions (user_id, account_id/*, card_id*/, transaction_date/*, transaction_type_id*/, amount/*, balance,
                          currency_id*/)
VALUES (7, 3/*, 4*/, NOW()/*, 4*/, 75.00/*, 20000.00, 6*/);
INSERT INTO transactions (user_id, account_id/*, card_id*/, transaction_date/*, transaction_type_id*/, amount/*, balance,
                          currency_id*/)
VALUES (8, 4/*, 5*/, NOW()/*, 4*/, 100.00/*, 100000.00, 6*/);

/*INSERT INTO preferences (user_id, language_id, timezone_id, email_notifications, sms_notifications)
VALUES (5, 3, 1, true, false);
INSERT INTO preferences (user_id, language_id, timezone_id, email_notifications, sms_notifications)
VALUES (6, 6, 1, false, false);
INSERT INTO preferences (user_id, language_id, timezone_id, email_notifications, sms_notifications)
VALUES (7, 6, 1, true, true);
INSERT INTO preferences (user_id, language_id, timezone_id, email_notifications, sms_notifications)
VALUES (8, 6, 1, true, false);*/