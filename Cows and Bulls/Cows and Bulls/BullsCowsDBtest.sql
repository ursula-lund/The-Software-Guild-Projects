drop database if exists BullsCowsDBTest;
create database BullsCowsDBTest;
use BullsCowsDBTest;

Create table Games(
GameId int not null primary key auto_increment,
answerNum int not null,
isOver boolean default 0
);


Create table Guesses (
GuessId int primary key auto_increment,
GuessNum int not null,
GameId int not null,
`Match` int not null,
`Partial` int not null,
TimeOf datetime not null, 
foreign key (GameId) references Games(GameId)
);


select * from Games;




