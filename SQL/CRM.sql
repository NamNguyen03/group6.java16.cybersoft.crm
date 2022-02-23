create database CRM;
use CRM;
create table company(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	company_name VARCHAR(120) NOT NULL,
	address VARCHAR(120) NOT NULL,
	email VARCHAR(50),
    phone VARCHAR(20)
);
create table t_user(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	fullname VARCHAR(20) NOT NULL,
	address VARCHAR(120) NOT NULL,
	username VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL ,
    user_createdate date NOT NULL,
	user_createby VARCHAR(50) NOT NULL,
	user_updatedate date NOT NULL,
    user_updateby VARCHAR(50) NOT NULL
);
create table project(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	project_name VARCHAR(150) NOT NULL,
	project_description VARCHAR(120) NOT NULL,
	project_status VARCHAR(50),
	project_createdate date NOT NULL,
	project_createby VARCHAR(50) NOT NULL, -- ngay mac dinh kieu du lieu 
	project_updatedate date NOT NULL,
    project_updateby VARCHAR(50) not null
);
create table task(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	task_name VARCHAR(150) NOT NULL,
    task_createdate date NOT NULL,
	task_createby VARCHAR(50) NOT NULL,
	task_updatedate date NOT NULL,
    task_updateby VARCHAR(50) NOT NULL,
    project int(6) NOT  NULL
);
create table status_task(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	status_task_name VARCHAR(60) NOT NULL
);
create table u_role(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    role_details int 
);
create table role_details(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY
);
create table prọect_role(	
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY
);





