drop table users;
drop table hotels;
drop table roomdetails;
drop table bookingdetails;

create table users(
	user_id int NOT NULL AUTO_INCREMENT primary key,
	password varchar(32), 
	role varchar(10),
	user_name varchar(20) unique,
	mobile_no varchar(10),
	phone varchar(10),
	address varchar(25),
	email varchar(255)
);

insert into users values(0,'admin','admin','asortey','9960309828','8076465459','Bangalore','ashu.sortey@gmail.com');

create table hotels(
	hotel_id int NOT NULL AUTO_INCREMENT primary key,
	city varchar(10),
	hotel_name varchar(20),
	address varchar(25),
	description varchar(50),
	avg_rate_per_night int,
	phone_no1 varchar(10),
	phone_no2 varchar(10),
	rating varchar(4),
	email varchar(255),
	fax varchar(15)
);

create table roomdetails(
	room_id int NOT NULL AUTO_INCREMENT primary key,
	hotel_id varchar(4) references hotels(hotel_id),
	room_no varchar(3),
	room_type varchar(20),
	per_night_rate int,
	availability char
);

create table bookingdetails(
	booking_id int NOT NULL AUTO_INCREMENT primary key,
	room_id varchar(4) references roomdetails(room_id),
	user_id varchar(4) references users(user_id),
	booked_from date,
	booked_to date,
	no_of_adults int,
	no_of_children int,
	amount int
);


select * from users;
select * from hotels;
select * from roomdetails;
select * from bookingdetails;
