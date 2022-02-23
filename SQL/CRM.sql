create database CRM;
use CRM;
create table company(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	company_name VARCHAR(120) NOT NULL,
	address VARCHAR(120) NOT NULL,
	email VARCHAR(50),
    phone VARCHAR(20)
);
create table t_user(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	fullname VARCHAR(20) NOT NULL,
	address VARCHAR(120) NOT NULL,
	username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL ,
    user_createdate date NOT NULL,
	user_createby VARCHAR(50) NOT NULL,
	user_updatedate date NOT NULL,
    user_updateby VARCHAR(50) NOT NULL
);
create table project(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	project_name VARCHAR(150) NOT NULL,
	project_description VARCHAR(120) NOT NULL,
	project_status VARCHAR(50),
	project_createdate date NOT NULL,
	project_createby VARCHAR(50) NOT NULL, -- ngay mac dinh kieu du lieu 
	project_updatedate date NOT NULL,
    project_updateby VARCHAR(50) not null
);
create table task(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	task_name VARCHAR(150) NOT NULL,
    task_description VARCHAR(120) NOT NULL,
    task_createdate date NOT NULL,
	task_createby VARCHAR(50) NOT NULL,
	task_updatedate date NOT NULL,
    task_updateby VARCHAR(50) NOT NULL,
    user_id int(6) ,
    status_id int(6) ,
    project_id int(6)
);
create table status_task(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	status_task_name VARCHAR(60) NOT NULL
);
create table u_role(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
    role_name int(6)
);
create table role_details(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
    user_id INT(6) ,
    role_id int(6) ,
    project_role_id int(6)
);
create table project_role(	
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
    project_id int(6)
);

ALTER TABLE task ADD  FOREIGN KEY(user_id) REFERENCES t_user(id);
ALTER TABLE task ADD FOREIGN KEY(status_id) REFERENCES status_task(id);
ALTER TABLE task ADD FOREIGN KEY(project_id) REFERENCES project(id);

ALTER TABLE role_details ADD  FOREIGN KEY(user_id) REFERENCES t_user(id);
ALTER TABLE role_details ADD FOREIGN KEY(role_id) REFERENCES u_role(id);
ALTER TABLE role_details ADD FOREIGN KEY(project_role_id) REFERENCES project_role(id);

ALTER TABLE project_role ADD FOREIGN KEY(project_id) REFERENCES project(id);




