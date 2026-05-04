create table state
(
code int primary key auto_increment,
name char(35) not null unique);
);
insert into state(name) values('Madhya Pradesh');
insert into state(name) values('Maharashtra');

create table city
(
code int primary key auto_increment,
name char(35) not null unique,
state_code int not null,
foreign key (state_code) references state(code)
);

insert into city(name,state_code) values('Ujjain',1);
insert into city(name,state_code) values('Indore',1);
insert into city(name,state_code) values('Mumbai',2);
insert into city(name,state_code) values('Pune',2);
insert into city(name,state_code) values('Satara',2);

create table employee
(
emp_id char(10) primary key,
name char(50) not null,
date_of_birth date not null,
city_code int not null,
gender char(1) not null,
salary int not null,
foreign key (city_code) references city(code)
);


insert into employee values('A101','Ramesh Sharma','2002-12-12',1,'M',400000);
insert into employee values('A102','Shivani Sharma','2001-10-11',2,'F',450000);
insert into employee values('A103','Aman Gupta','1998-02-01',3,'M',500000);
insert into employee values('B101','Sonali Pawar','1999-04-16',4,'F',650000);
insert into employee values('B102','Ajay Singh','2001-02-20',5,'M',550000);





