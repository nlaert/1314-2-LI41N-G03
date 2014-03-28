use LS

if (OBJECT_ID('Aluno') is not null) drop table Aluno

create table Aluno(
	bi int primary key,
	nome varchar(100)
	)
	
insert into Aluno values
			(1,'Nick'), 
			(2,'Joao')
