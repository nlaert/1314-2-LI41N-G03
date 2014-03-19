use LS

if object_id('properties') is not null
	drop table properties
if object_id('Type') is not null
	drop table [Type]
if object_id('users') is not null
	drop table users

create table users(
	username varchar(15) primary key,
	[password] varchar(20) not null,
	email varchar(30) unique not null,
	fullname varchar(50) not null 
	)
create table [Type](
	[type] varchar(15) primary key
	)

create table properties (
	id int identity(1,1) primary key,
	[description] varchar(200) not null,
	price money not null,
	location varchar(50) not null,
	[owner] varchar(15) not null,
	[type] varchar(15) not null,
	Constraint FK_owner Foreign key (owner) references users(username),
	Constraint FK_type Foreign key (type) references Type(type),
	
	)
