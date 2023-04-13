create database cua_hang_xung_dien_nhat_ban;
use cua_hang_xung_dien_nhat_ban;
create table role (
id int primary key auto_increment,
name varchar(25)
);
create table account(
id int primary key auto_increment,
password varchar(225)
);
create table account_role (
id int  primary key auto_increment,
id_role int,
id_account int ,
foreign key (id_role) references `role`(id),
foreign key (id_account) references account(id)
);
create table position (
id int primary key auto_increment,
name varchar(45)
);
create	table employee(
id int  primary key auto_increment,
name varchar(45),
phone_number varchar(45),
id_card varchar(45) unique,
address varchar (225),
gender boolean,
is_deleted boolean,
day_of_birth date,
position_id int,
email_account varchar(45) unicode,
id_account int,
foreign key (id_account) references account(id),
foreign key (position_id) references `position`(id)
);
create table customer (
id int  primary key auto_increment,
name varchar(45),
day_of_birth varchar(45),
phone_number varchar(45),
id_card varchar(45) unicode,
address varchar (225),
gender boolean,
status boolean,
is_deleted boolean,
email varchar(45) unique,
health_condition varchar(255)
);
create table type(
id int primary key auto_increment, 
name varchar (45)
) ;
create table product (
id int  primary key auto_increment,
image varchar(500),
name varchar(45),
prices double,
description varchar(500),
date_submitted date,
quantity int ,
type_product int,
employee_id int ,
foreign key (employee_id) references employee(id),
foreign key (type_product) references type(id)
);
create table `order` (
id int primary key auto_increment ,
code_oder varchar(45) unique,
date_purchase date ,
total_pay double,
product_id int ,
customer_id int ,
foreign key (product_id) references product(id),
foreign key (customer_id) references customer(id)
);
 