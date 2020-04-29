DROP DATABASE IF EXISTS HotelReservation;
CREATE DATABASE HotelReservation;
USE HotelReservation;




CREATE TABLE Guest(
    GuestId INT primary key auto_increment,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Address VARCHAR(75) NOT NULL,
    City VARCHAR(25) NOT NULL,
    State VARCHAR(2) NOT NULL,
    ZipCode int NOT NULL,
    Phone VARCHAR(15) not null
    );
    
CREATE TABLE Reservation(
ReservationId INT PRIMARY KEY auto_increment,
StartDate Date NOT NULL,
EndDate Date NOT NULL,
GuestId int not null,
foreign key (GuestId) references Guest(GuestId)
);

Create Table RoomType(
RoomStyle VARCHAR(20),
RoomTypeId int not null primary key auto_increment, 
StandardCapacity int not null,
MaxCapacity Int not null,
BasePrice DECIMAL(20,2) NOT NULL,
AdditionalFee Decimal(10, 2) not null
);

CREATE TABLE Room(
RoomNumber int NOT NULL primary key,
RoomTypeId int not null,
isADA BOOL NOT NULL DEFAULT 0,
foreign key (RoomTypeId) references RoomType(RoomTypeId)
);

CREATE TABLE Amenity(
AmenityId int auto_increment key,
AmenityType VarChar(50) not null,
AmenityPrice Decimal(10, 2) null
);

CREATE TABLE RoomAmen(
AmenityId Int not null,
RoomNumber int not null,
primary key (AmenityId, RoomNumber),
foreign key (AmenityId) references Amenity(AmenityId),
foreign key(RoomNumber) references Room(RoomNumber)
);

CREATE TABLE RoomRes(
RoomNumber int not null,
ReservationId INT not NULL,
Adult INT not  NULL,
Child Int null,
primary key (RoomNumber, ReservationId),
foreign key (ReservationId) references Reservation(ReservationId),
foreign key (RoomNumber) references room(RoomNumber)
);












    



