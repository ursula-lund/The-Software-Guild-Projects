use HotelReservation;


insert into Amenity(AmenityType, AmenityPrice) VALUES
('FridgeOrMicrowave', 0),
('FridgeAndMicrowave', 0),
('Kitchen', 0),
('jacuzzi', '25');

insert into RoomType(RoomStyle, StandardCapacity, MaxCapacity, BasePrice, AdditionalFee) VALUES
( 'Single', 2, 2, 149.99, 0),
( 'Double', 2, 4, 174.99, 10),
('Suite', 3, 8, 399.99, 20);


insert into Room(RoomNumber, isADA, RoomTypeId) VALUES
(201, 0, 2),
(202, 1, 2),
(203, 0, 2),
(204, 1, 2),
(205, 0, 1),
(206, 1, 1),
(207, 0, 1),
(208, 1, 1),
(301, 0, 2),
(302, 1, 2),
(303, 0, 2),
(304, 1, 2),
(305, 0, 1),
(306, 1, 1),
(307, 0, 1),
(308, 1, 1),
(401, 1, 3),
(402, 1, 3);


insert into Guest (FirstName, LastName, Address, City, State, ZipCode, Phone) VALUES
('Ursula', 'Lundberg', '5555 5th Street', 'Minncity', 'MN', 55355, '(555) 555-5555'),
('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', 51501, '(291) 552-0508'),
('Bettyann',  'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', 99654, '(487) 277-9632'),
('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', 08096, '(214) 730-0298'),
('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', 78552, '(214) 730-0298'),
('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MN', 48601, '(377) 507-0974'),
('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', 80003,  '(814) 485-2615'),
('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', 60099, '(279) 491-0960'),
('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', 02864, '(446) 396-6785'),
('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', 13126, '(834) 727-1001'),
('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', 22015, '(446) 351-6860'), 
('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', 19026, '(231) 893-2755');

insert into Reservation (StartDate, EndDate, GuestId) VALUES
('2023/02/02', '2023/02/04', 2),
('2023/02/05', '2023/02/10', 3),
('2023/02/22', '2023/02/24', 5),
('2023/03/06', '2023/03/07', 4),
('2023/03/17', '2023/02/20', 1),
('2023/03/18', '2023/03/21', 6),
('2023/03/29', '2023/03/31', 7),
('2023/03/31', '2023/04/05', 8),
('2023/04/09', '2023/04/13', 9),
('2023/04/23', '2023/04/24', 10),
('2023/05/30', '2023/06/02', 11),
('2023/06/10', '2023/06/14', 12),
('2023/06/10', '2023/06/14', 12),
('2023/06/28', '2023/07/02', 6),
('2023/06/28', '2023/07/02', 1),
('2023/07/13', '2023/07/14', 9),
('2023/07/18', '2023/07/21', 10),
('2023/07/28', '2023/07/29', 3),
('2023/08/30', '2023/09/01', 3),
('2023/09/16', '2023/09/17', 2),
('2023/09/13', '2023/09/15', 4),
('2023/11/22', '2023/11/25', 5),
('2023/11/22', '2023/11/25', 2),
('2023/11/22', '2023/11/25', 2),
('2023/12/24', '2023/12/28', 11); 


insert into RoomAmen(RoomNumber, AmenityId) values
(201, 1),
(202, 1),
(203, 1),
(204, 1),
(205, 2),
(206, 2),
(207, 2), 
(208, 2), 
(301, 1),
(303, 1),
(304, 1),
(305, 2),
(306, 2),
(307, 2),
(308, 2),
(401, 3),
(402, 3);

insert into RoomAmen(RoomNumber, AmenityId) values
(201, 4),
(203, 4),
(205, 4),
(207, 4),
(301, 4), 
(303, 4),
(305, 4),
(307, 4);



insert into RoomRes(RoomNumber, Adult, Child, ReservationId) Values
(308, 1, 0, 1),
(203, 2, 1, 2),
(305, 2, 0, 3),
(201, 2, 2, 4),
(307, 1, 1, 5),
(302, 3, 0, 6), 
(202, 2, 2, 7), 
(304, 2, 0, 8), 
(301, 1, 0, 9), 
(207, 1, 1, 10), 
(401, 2, 4, 11), 
(206, 2, 0, 12),
(208, 1, 0, 13), 
(304, 3, 0, 14), 
(205, 2, 0, 15), 
(204, 3, 1, 16),
(401, 4, 2, 17),
(303, 2, 1, 18),
(305, 1, 0, 19),
(208, 2, 0, 20),
(203, 2, 2, 21), 
(401, 2, 2, 22),
(206, 2, 0, 23),
(301, 2, 2, 24),
(302, 2, 0, 25);

SET SQL_SAFE_UPDATES = 0;

DELETE FROM RoomRes
WHERE ReservationId = 8;

DELETE FROM Reservation
WHERE GuestId = 8;

DELETE FROM Guest 
WHERE GuestId = 8;

SET SQL_SAFE_UPDATES = 1;





