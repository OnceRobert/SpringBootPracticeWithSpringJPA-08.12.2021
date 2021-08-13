create table company
(
Id int primary key auto_increment,
company_name varchar(255) not null
);

create table Employee
(
id int primary key auto_increment,
name varchar(255) not null,
age int,
gender varchar(255),
salary int,
company_Id int,
foreign key (company_Id) references company(Id)
);