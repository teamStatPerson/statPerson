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

CREATE TABLE `Keywords` (
	`ID` int NOT NULL,
	`Name` varchar(2048) NOT NULL,
	`PersonID` int NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Persons` (
	`ID` int NOT NULL,
	`Name` varchar(2048) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `PersonPageRank` (
	`PersonID` int NOT NULL,
	`PageID` int NOT NULL,
	`Rank` int NOT NULL
);

CREATE TABLE `Pages` (
	`ID` int NOT NULL,
	`Url` varchar(2048) NOT NULL,
	`SiteID` int NOT NULL,
	`FoundDateTime` DATETIME NOT NULL,
	`LastScanDate` DATETIME NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Sites` (
	`ID` int NOT NULL,
	`Name` varchar(256) NOT NULL,
	PRIMARY KEY (`ID`)
);

CREATE TABLE `Users` (
	`UserID` int NOT NULL,
	`password` varchar(2048) NOT NULL,
	`DateRegistration` DATETIME NOT NULL,
	`email` varchar(2048) NOT NULL,
	PRIMARY KEY (`UserID`)
);

CREATE TABLE `UserAdministrator` (
	`AdministratorID` int NOT NULL,
	`UserID` int NOT NULL
);

CREATE TABLE `Administrators` (
	`AdministratorID` int NOT NULL,
	`password` varchar(2048) NOT NULL,
	`email` varchar(2048) NOT NULL,
	`DateRegistration` DATETIME NOT NULL,
	`SecondaryAdministrator` BINARY NOT NULL,
	PRIMARY KEY (`AdministratorID`)
);


CREATE TABLE `AdministratorsPersons` (
	`AdministratorID` int NOT NULL,
	`PersonID` int NOT NULL
);

CREATE TABLE `AdministratorsSites` (
	`AdministratorID` int NOT NULL,
	`SiteID` int NOT NULL
);

CREATE TABLE `AdministratorPrices` (
	`AdministratorID` int NOT NULL,
	`PriceID` int NOT NULL,
	`DateOfPay` DATETIME NOT NULL
);

CREATE TABLE `AdministratorsConnect` (
	`IdPrimaryAdministrator` int NOT NULL,
	`IdSecondaryAdministrator` int NOT NULL
);

CREATE TABLE `WebServiceStateAdministrator` (
	`AdministratirID` int NOT NULL,
	`UserID` int NOT NULL,
	`State` varchar(4) NOT NULL DEFAULT 'REST'
);

ALTER TABLE `Keywords` ADD CONSTRAINT `Keywords_fk0` FOREIGN KEY (`PersonID`) REFERENCES `Persons`(`ID`);

ALTER TABLE `PersonPageRank` ADD CONSTRAINT `PersonPageRank_fk0` FOREIGN KEY (`PersonID`) REFERENCES `Persons`(`ID`);

ALTER TABLE `PersonPageRank` ADD CONSTRAINT `PersonPageRank_fk1` FOREIGN KEY (`PageID`) REFERENCES `Pages`(`ID`);

ALTER TABLE `Pages` ADD CONSTRAINT `Pages_fk0` FOREIGN KEY (`SiteID`) REFERENCES `Sites`(`ID`);

ALTER TABLE `UserAdministrator` ADD CONSTRAINT `UserAdministrator_fk0` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `UserAdministrator` ADD CONSTRAINT `UserAdministrator_fk1` FOREIGN KEY (`UserID`) REFERENCES `Users`(`UserID`);

ALTER TABLE `AdministratorsPersons` ADD CONSTRAINT `AdministratorsPersons_fk0` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `AdministratorsPersons` ADD CONSTRAINT `AdministratorsPersons_fk1` FOREIGN KEY (`PersonID`) REFERENCES `Persons`(`ID`);

ALTER TABLE `AdministratorsSites` ADD CONSTRAINT `AdministratorsSites_fk0` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `AdministratorsSites` ADD CONSTRAINT `AdministratorsSites_fk1` FOREIGN KEY (`SiteID`) REFERENCES `Sites`(`ID`);

ALTER TABLE `AdministratorPrices` ADD CONSTRAINT `AdministratorPrices_fk0` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `AdministratorPrices` ADD CONSTRAINT `AdministratorPrices_fk1` FOREIGN KEY (`PriceID`) REFERENCES `Prices`(`PriceID`);

ALTER TABLE `AdministratorsConnect` ADD CONSTRAINT `AdministratorsConnect_fk0` FOREIGN KEY (`IdPrimaryAdministrator`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `AdministratorsConnect` ADD CONSTRAINT `AdministratorsConnect_fk1` FOREIGN KEY (`IdSecondaryAdministrator`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `WebServiceStateAdministrator` ADD CONSTRAINT `WebServiceStateAdministrator_fk0` FOREIGN KEY (`AdministratirID`) REFERENCES `Administrators`(`AdministratorID`);

ALTER TABLE `WebServiceStateAdministrator` ADD CONSTRAINT `WebServiceStateAdministrator_fk1` FOREIGN KEY (`UserID`) REFERENCES `Users`(`UserID`);

**/