INSERT INTO account (id, balance, email, first_name, is_verified, last_name, phone_number, pin, date_of_birth)
VALUES (1, 1400, 'biniamasnake@gmail.com', 'Biniam', true, 'Asnake', '+251000000000', 0, now());
INSERT INTO account (id, balance, email, first_name, is_verified, last_name, phone_number, pin, date_of_birth)
VALUES (2, 1600, 'johndoe@gmail.com', 'John', true, 'Doe', '+49555555555', 5555, now());
INSERT INTO account (id, balance, email, first_name, is_verified, last_name, phone_number, pin, date_of_birth)
VALUES (3, 0, 'tobedeleted@gmail.com', 'tobedeleted', true, 'tobedeleted', '0', 0, now());

INSERT INTO transfer (id, amount, date, status, receiver_id, sender_id, reason, uuid)
VALUES (1, 200, '2021-03-14 09:58:58.325000', 'SUCCESSFUL', 2, 1, 'repayment of loan', '8b78a7b1-5921-4dee-99d6-f86cc664ad6d');


-- Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement "INSERT INTO ACCOUNT (ID, BALANCE, EMAIL, FIRST_NAME, IS_VERIFIED, LAST_NAME, PHONE_NUMBER, PIN, DATE_OF_BIRTH) VALUES (1, 1400, 'biniamasnake@gmail.com', 'Biniam', TRUE, 'Asnake', '+251000000000', 0, (SELECT NOW() - INTERVAL '20 YEAR')[*])"; expected "[, ::, AT, FORMAT, *, /, %, +, -, ||, ~, !~, NOT, LIKE, ILIKE, REGEXP, IS, IN, BETWEEN, AND, OR, YEAR, MONTH, DAY, HOUR, MINUTE, SECOND"; SQL statement:
-- INSERT INTO account (id, balance, email, first_name, is_verified, last_name, phone_number, pin, date_of_birth) VALUES (1, 1400, 'biniamasnake@gmail.com', 'Biniam', true, 'Asnake', '+251000000000', 0, (SELECT NOW() - INTERVAL '20 YEAR')) [42001-200]
