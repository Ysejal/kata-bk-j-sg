-- CREATE CLIENT
INSERT INTO client VALUES (1, '2 rue du bac poissy', '1988-10-22', 'Chikar', 'Soufiane', '0627206363');


-- CREATE BANK_ACCOUNT
INSERT INTO bank_account VALUES (1, 900, NOW(), 1);

-- CREATE OPERATION
INSERT INTO operation VALUES(1, 500, NOW(), 'DEPOSIT', 1);
INSERT INTO operation VALUES(2, 100, NOW(), 'WITHDRAWAL', 1);

