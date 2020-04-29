use HotelReservation;



-- 1.
-- Write a query that returns a list of reservations that end in July 2023, including
-- the name of the guest, the room number(s), and the reservation dates.

select 
CONCAT(r.StartDate, ' to ', r.EndDate) TimeStayed,
CONCAT(g.FirstName, ' ', g.LastName) GuestName,
rr.RoomNumber
FROM Guest g
JOIN Reservation r
on g.GuestId = r.GuestId
join RoomRes rr
on r.ReservationId = rr.ReservationId
where r.EndDate like '2023-07%';

-- 2.
-- Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.

SELECT
CONCAT(g.FirstName, ' ', g.LastName) as GuestName,
CONCAT(r.StartDate, ' to ', r.EndDate) as TimeStayed,
 ra.RoomNumber
 From Guest g
 join Reservation r
 on g.GuestId = r.GuestId
 join RoomRes rr
 on rr.ReservationId = r.ReservationId
 join Room room
 on rr.RoomNumber = room.RoomNumber
 join roomamen ra
 on ra.RoomNumber = room.RoomNumber
where ra.AmenityId = '4';


-- 3.
-- Write a query that returns all the rooms reserved for a specific guest, including the 
-- guest's name, the room(s) reserved, the starting date of the reservation, and how many 
-- people were included in the reservation. (Choose a guest's name from the existing data.)
SELECT 
(rr.Adult + rr.Child) As GuestsTotal,
CONCAT(g.FirstName, ' ', g.LastName) AS GuestName,
r.StartDate, room.RoomNumber
FROM Room room
join RoomRes rr
on room.RoomNumber = rr.RoomNumber
join Reservation r
on rr.ReservationId = r.ReservationId
join Guest g
on r.GuestId = g.GuestId
where g.GuestId = '3';

-- 4.
-- Write a query that returns a list of rooms, reservation ID, and per-room cost for each 
-- reservation. The results should include all rooms, whether or not there is a reservation associated with the room.

Select
room.RoomNumber, r.ReservationId, 
(rt.BasePrice + sum(a.AmenityPrice)) as 'per-room cost'
from Room room
left outer join RoomRes rr
on room.RoomNumber = rr.RoomNumber
left outer join Reservation r
on rr.ReservationId = r.ReservationId
left outer join  RoomType rt
on room.RoomTypeId = rt.RoomTypeId
left outer join RoomAmen ra
on room.Roomnumber = ra.Roomnumber
left outer join amenity a
on ra.AmenityId = a.AmenityId
Group by room.RoomNumber,  r.ReservationId;




-- 5.
-- Write a query that returns all the rooms accommodating at least three guests and that
-- are reserved on any date in April 2023.


SELECT room.roomNumber, 
r.StartDate, r.EndDate, rt.MaxCapacity
From Room room
join RoomType rt
on room.roomTypeId = rt.roomTypeId
join RoomRes rr
on room.roomNumber = rr.roomNumber
join Reservation r
on rr.ReservationId = r.ReservationId
where MaxCapacity > 2
and (r.EndDate > '2023-04-01') and (r.StartDate <='2023-04-30');



-- 6.    
-- Write a query that returns a list of all guest names and the number of reservations per 
-- guest, sorted starting with the guest with the most reservations and then by the guest's last name.
-- !! fix order by LastName
select 
count(r.ReservationId) as NumberRes,
g.LastName, g.FirstName
from Guest g
join Reservation r
on g.GuestId = r.GuestId
where ReservationId is not null
Group by g.GuestId
HAVING Max(NumberRes)
Order by 
NumberRes DESC,
LastName Desc; 
 



-- 7.
-- Write a query that displays the name, address, and phone number of a guest based 
-- on their phone number. (Choose a phone number from the existing data.)

SELECT 
FirstName, LastName, Address, Phone
from Guest
where Phone = '(377) 507-0974';