DROP DATABASE IF EXISTS Supers;
CREATE DATABASE Supers;
USE Supers;

Create table Powers(
PowerId INT auto_increment primary key,
PowerDesc VARCHAR(100) not null,
PowerName VARCHAR(50) not null,
isDeleted boolean DEFAULT 0 not null
);


Create table Supers(
SuperId INT auto_increment primary key,
SuperName VARCHAR(50) not null,
SuperDesc VARCHAR(100) null,
PowerId INT not null,
isDeleted boolean DEFAULT 0 not null,
foreign key (PowerId) references Powers(PowerId)
);


Create table Organizations(
OrganizationId INT auto_increment primary key,
OrgName VARCHAR(50) Not null,
`Description` VARCHAR (100) NOT NULL,
Address VARCHAR (50) NOT NULL,
Phone VARCHAR (25) NOT NULL,
isDeleted boolean DEFAULT 0 not null
);



Create table SuperOrgs(
SuperId INT auto_increment not null,
OrganizationId INT not null,
primary key (SuperId, OrganizationId),
Foreign Key (OrganizationId) references Organizations(OrganizationId),
Foreign Key (SuperId) references Supers(SuperId)
);

Create table Locations(
LocationId INT primary key auto_increment,
Address VARCHAR(50) NOT NULL,
Latitude Decimal (9, 6) not null,
Longitude Decimal(9, 6) not null,
isDeleted boolean DEFAULT 0 not null
);

Select * From SuperOrgs;


Create table Sightings(
SightingId INT not null Primary Key auto_increment,
LocationId INT not null,
SightDate Date not null,
isDeleted boolean DEFAULT 0 not null,
SuperId int not null,
foreign key (LocationId) references Locations(LocationId),
foreign key (SuperId) references Supers(SuperId)
);




CREATE VIEW CurrentPowers as
SELECT *
FROM `Powers`
WHERE isDeleted = '0';

CREATE VIEW CurrentSupers as
SELECT *
FROM `Supers`
WHERE isDeleted = '0';

CREATE VIEW CurrentOrganizations as
SELECT *
FROM `Organizations`
WHERE isDeleted = '0';

CREATE VIEW CurrentLocations as
SELECT *
FROM `Locations`
WHERE isDeleted = '0';

CREATE VIEW CurrentSightings as
SELECT *
FROM `Sightings`
WHERE isDeleted = '0';

insert into Powers (PowerDesc, PowerName) values 
('Mighty beam that vaporizes Unicorns. Only Unicorns', 'Unicorn Beams'),
('Lots of Student Loans', 'Medical Degree'),
('Can make cookies out of anything', 'Home-Baked Goodness'),
('Good for making album covers', 'Psyadelic Visions'),
('Good for escaping jury duty', 'Invisibility'),
('Summons niche sea creatures', 'Otco-speak')
;

Insert into Supers (SuperName, SuperDesc, PowerId) Values 

('Lord Unicorn', 'The Lord of the Unicorns', 1),
('Doctor Surgeon', 'A Surgeon who saves lives', 2),
('Miss Bakersfield', 'Unites people with the power of Baked Goods', 3),
('Lady Luck', 'Very good at Yahtzee', 4),
('Totally Rad Dude', 'Great at parties', 4),
('Mr. Octopi', 'Always ready to lend a hand (or tentacle)', 6)
;

insert into Organizations( OrgName, `Description`, Address, Phone) values

('Fantasy League Inc', 'Only the Most Fantastical heroes allowed', '0000 Unreal Address St.', '(000) 000-0000'),
("Doctor's Conference", 'For Superheros with PHDs', '1234 Appleday Rd.', '(784) 324-7896'),
('Little Old Lady Tea Club', 'Club For Ladies to chat and fight crime', '9080 Lavender Ln', '(343) 333-4949'),
('Wildbois', 'Ladies Only', '4523 Cooties Ln', '111-111-1111'),
('Animal Friends', 'We love all animals. Humans are our favorite kind,', "Cute n' Cuddly Valley", '(898) 978-0009')
;

insert into SuperOrgs(SuperId, OrganizationId) Values
 (1,1),
 (2,2),
 (3,3),
 (1,3),
 (4, 3),
 (4, 1),
 (5, 3),
 (5, 2),
 (5, 5),
 (6, 5),
 (6, 1),
 (3, 4),
 (4, 4)
 ;
 
 
insert into Locations(Address, Latitude, Longitude) values
('600 E 4th St, Morris, MN 56267', 45.589480, -95.904600),
('Askov, MN', '46.189200', '-92.782480'),
('West Lake St, Minneapolis, MN', '44.948370', '-93.302120'),
('Helsinki, Poland', '54.534809', '18.542351'),
('Niagra Falls, NY', '43.094460', '-79.056427'),
('Chicago, Illinois', '41.878113', '-87.629799')
;

Insert into Sightings(LocationId, SightDate, SuperId) Values
(1, '2020-01-02', 1),
(1, '2020-02-01', 1),
(1, '2020-03-01', 1),
(2, '2020-01-02', 2),
(2, '2020-03-04', 2),
(3, '2020-04-10', 6),
(3, '2020-03-04', 2),
(3, '2020-01-01', 3),
(4, '2020-03-04', 3),
(4, '2020-04-09', 6),
(5, '2020-03-29', 3),
(5, '2020-01-02', 3),
(6, '2020-01-02', 3),
(6, '2020-03-04', 4)
;

select * from currentsightings;

