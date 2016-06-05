create table ACCOUNT (
	ID int not null auto_increment,
	EMAIL varchar(2048),
	PASSWORD varchar(2048) not null,
	DATEREGISTRATION date not null,
	TYPEACCOUNT int not null,
	IDLINKEDADMINISTRATOR int,
	primary key (ID)
);


INSERT INTO ACCOUNT 
(ID, EMAIL, PASSWORD, DATEREGISTRATION, TYPEACCOUNT)
VALUES
(500, "root_primary@root.ru", "root" , "2016-02-02" , 0);

INSERT INTO ACCOUNT 
(ID, EMAIL, PASSWORD, DATEREGISTRATION, TYPEACCOUNT, IDLINKEDADMINISTRATOR)
VALUES
(501, "user@user.ru", "user" , "2016-01-01" , 100, 500);