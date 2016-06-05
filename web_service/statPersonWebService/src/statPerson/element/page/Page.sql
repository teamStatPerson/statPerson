
CREATE TABLE `PAGE` (
	`ID` int PRIMARY KEY auto_increment NOT NULL,
	`URL` varchar(2048) NOT NULL,
	`SITEID` int NOT NULL,
	`FOUNDDATETIME` DATETIME NOT NULL,
	`LASTSCANDATE` DATETIME,
    `HTML` LONGTEXT
);
