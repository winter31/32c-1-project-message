
create table protector (
	protector varchar(30) not null,
	age number(3) not null,
	jumin varchar(15) constraint protector_pk primary key,
	phonenum varchar(15) not null,
	pet_type char(1) not null -- 'd' is dog, 'c' is cat, 'M' is mouse
);

create table dog (
	jumin varchar(15) constraint dog_pk primary key,
	dogname varchar (20) not null,
	dogage number(2) not null,
	dogtype varchar(20) not null
);

create table cat(
	jumin varchar(15) constraint cat_pk primary key,
	catname varchar (20) not null,
	catage number(2) not null,
	cattype varchar(20) not null
);


create table mouse(
	jumin varchar(15) constraint mouse_pk primary key,
	mousename varchar (20) not null,
	mouseage number(2) not null,
	mousetype varchar(20) not null
);
