
create database slg_corporation_japan;
use slg_corporation_japan;
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
day_of_birth date,
position_id int,
email_account varchar(45) unicode,
is_deleted boolean,
id_account int,
foreign key (id_account) references account(id),
foreign key (position_id) references `position`(id)
);
create table customer (
id int  primary key auto_increment,
name varchar(45),
day_of_birth varchar(45),
phone_number varchar(45) unicode,
address varchar (225),
gender boolean,
status boolean,
health_condition varchar(255),
email varchar(45) unique,
id_account int,
foreign key (id_account) references account(id)
);
create table product_type(
id int primary key auto_increment, 
name varchar (45)
) ;
create table product (
id int  primary key auto_increment,
name varchar(45),
img varchar(255),
prices double,
is_deleted boolean,
description varchar(255),
date_submitted date,
quantity int ,
type_product int,
employee_id int ,
foreign key (employee_id) references employee(id),
foreign key (type_product) references product_type(id)
);

create table `order` (
id int primary key auto_increment ,
code_oder varchar(45) unique,
date_purchase date ,
total_pay double,
customer_id int ,
foreign key (customer_id) references customer(id)
);
create table order_detail(
id bigint auto_increment primary key,
amount int,
order_id int,
product_id int,
foreign key (product_id) references product(id),
foreign key (order_id) references `order`(id)
)