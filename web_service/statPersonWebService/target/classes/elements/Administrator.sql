create table ADMINISTRATOR (
	ID int not null auto_increment,
	EMAIL varchar(2048),
	PASSWORD varchar(2048) not null,
	DATEREGISTRATION date not null,
	SECONDARYADMINISTRATOR bit,
	primary key (ID)
);