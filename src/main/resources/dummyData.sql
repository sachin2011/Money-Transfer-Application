--Create table in in-memory H2 database

DROP TABLE IF EXISTS Account;

CREATE TABLE Account (AccountId LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
UserName VARCHAR(20) NOT NULL,
EmailId VARCHAR(20) NOT NULL,
TelephoneNo VARCHAR(10) NOT NULL,
Address VARCHAR(50) NOT NULL,
AccountBalance DECIMAL(15,5) NOT NULL,
Currency VARCHAR(10) NOT NULL);

--Insert dummy data in the above created table for testing purpose
INSERT INTO Account (UserName,EmailId,TelephoneNo,Address,AccountBalance,Currency) VALUES ('sachin','sac@gmail.com','9013004011','nehru vihar',800.0000,'EUR');
INSERT INTO Account (UserName,EmailId,TelephoneNo,Address,AccountBalance,Currency) VALUES ('amit','amit@gmail.com','1168888','UK',260.0000,'EUR');

