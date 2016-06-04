/*

CREATE TABLE `Keywords` (
	`ID` int auto_increment NOT NULL,
	`Name` varchar(2048) NOT NULL,
	`PersonID` int NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Persons` (
	`ID` int auto_increment NOT NULL,
	`Name` varchar(2048) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `PersonPageRank` (
	`PersonID` int NOT NULL,
	`PageID` int NOT NULL,
	`Rank` int NOT NULL,
    PRIMARY KEY (`PersonID`, `PageID`)
);

CREATE TABLE `Pages` (
	`ID` int auto_increment NOT NULL,
	`Url` varchar(2048) NOT NULL,
	`SiteID` int NOT NULL,
	`FoundDateTime` DATETIME NOT NULL,
	`LastScanDate` DATETIME,
    `HTML` LONGTEXT,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Sites` (
	`ID` int auto_increment NOT NULL,
	`Name` varchar(256) NOT NULL,
    `StartDateStatistics` DATETIME,
	PRIMARY KEY (`ID`)
);

ALTER TABLE `Keywords` ADD CONSTRAINT `Keywords_fk0` FOREIGN KEY (`PersonID`) REFERENCES `Persons`(`ID`);

ALTER TABLE `PersonPageRank` ADD CONSTRAINT `PersonPageRank_fk0` FOREIGN KEY (`PersonID`) REFERENCES `Persons`(`ID`);

ALTER TABLE `PersonPageRank` ADD CONSTRAINT `PersonPageRank_fk1` FOREIGN KEY (`PageID`) REFERENCES `Pages`(`ID`);

ALTER TABLE `Pages` ADD CONSTRAINT `Pages_fk0` FOREIGN KEY (`SiteID`) REFERENCES `Sites`(`ID`);

**/