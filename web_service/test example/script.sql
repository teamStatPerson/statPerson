show databases;
create database db_test_4;
use db_test_4;

CREATE TABLE `Persons` (
	`ID` int NOT NULL,
	`Name` varchar(2048) NOT NULL,
	PRIMARY KEY (`ID`)
);
INSERT INTO `Persons` (`ID`, `Name`) VALUES 
(1, 'Путин'),
(2, 'Медведев'),
(3, 'Навальный');

show tables;
desc Persons;
