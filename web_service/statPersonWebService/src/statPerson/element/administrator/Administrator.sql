create table ADMINISTRATOR (
	ID int not null auto_increment,
	EMAIL varchar(2048),
	PASSWORD varchar(2048) not null,
	DATEREGISTRATION date not null,
	SECONDARYADMINISTRATOR bit,
	primary key (ID)
);

INSERT INTO ADMINISTRATOR 
(EMAIL, PASSWORD, DATEREGISTRATION, SECONDARYADMINISTRATOR)
VALUES
("test@test.ru", "test" , "0000-00-00" , FALSE);

INSERT INTO ADMINISTRATOR 
(EMAIL, PASSWORD, DATEREGISTRATION, SECONDARYADMINISTRATOR)
VALUES
("root@root.ru", "root" , "0000-00-00" , FALSE);