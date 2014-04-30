use LS

if object_id('rental') is not null
	drop table rental
if object_id('properties') is not null
	drop table properties
if object_id('Type') is not null
	drop table [Type]
if object_id('users') is not null
	drop table users

create table users(
	[username] varchar(15) primary key,
	[password] varchar(20) not null,
	[email] varchar(30) unique not null,
	[fullname] varchar(50) not null 
)

create table [type](
	[type] varchar(15) primary key
)

create table properties(
	[pid] int identity(1,1) primary key,
	[type] varchar(15) not null,
	[description] varchar(200) not null,
	[price] int not null,
	[location] varchar(50) not null,
	[owner] varchar(15) not null,
	Constraint FK_owner Foreign key ([owner]) references users(username),
	Constraint FK_type Foreign key ([type]) references Type([type]),
)

create table rental(
	[property] int,
	[renter] varchar(15),
	[year] int,
	[cw] int,
	[status] varchar(15) check ([status] in ('pending', 'confirmed')),
	[reserved_date] date,
	[confirmed_date] date,
	Constraint FK_property foreign key ([property]) references properties(pid),
	Constraint FK_renter foreign key ([renter]) references users(username),
	Constraint PK primary key ([property], [renter], [year], [cw]),
	Constraint validar unique ([property], [year],[cw])
)

insert into type values('room'),('apartment'),('villa');
insert into users values('superadmin', 'ls1213','admin@alunos.isel.pt','Super Admin');


insert into users values('joao','pass','a35392@alunos.isel.pt','Joao Rodrigues');
insert into users values('nick','pass','a35466@alunos.isel.pt','Nick Laert');
insert into properties values ('apartment','apartamento nos Olivais',1000,'Lisboa, Olivais','joao');
select [type], [description], [price], [location] from properties
insert into rental values(1,'joao',2014,11,'pending','2014-01-20',null); 
--insert into rental (property,renter,[year],[cw],[status],reserved_date) values(1,'nick',2014,10,'pending','2014-01-20'); 
select property, renter, [year], cw, [status], reserved_date, confirmed_date from rental;

<<<<<<< HEAD
select [year], [cw] from rental where [year] = 2014 and [cw]=10
=======
select [property], [renter], [year], [week], [status], [reserved_date], [confirmed_date] from rental where renter = 'joao'
>>>>>>> FETCH_HEAD

--select username, password, email, fullname from users where username = 'joao';
--select username, password, email, fullname from users;
--select [type], [description], [price], [location] from properties
--select [type], [description], [price], [location] from properties where pid = 1
--select [type], [description], [price], [location] from properties where location = 'Lisboa, Olivais'
--select [type], [description], [price], [location] from properties where [owner] = 'joao'
--select [type], [description], [price], [location] from properties where [type] = 'apartment' 


