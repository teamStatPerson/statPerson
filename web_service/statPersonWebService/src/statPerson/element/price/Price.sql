CREATE TABLE PRICE (
	ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
	NAME varchar(2048),
	MAXAMOUNTUSERS int NOT NULL,
	MAXAMOUNTSITES int NOT NULL,
	DURATIONOFPRICEDAY int NOT NULL
);

INSERT INTO PRICE 
(NAME, MAXAMOUNTUSERS, MAXAMOUNTSITES, DURATIONOFPRICEDAY)
VALUES
("FREE", 3 ,5 , 100);


INSERT INTO PRICE 
(NAME,MAXAMOUNTUSERS, MAXAMOUNTSITES, DURATIONOFPRICEDAY)
VALUES
("VIP", 200 , 200 , 365);