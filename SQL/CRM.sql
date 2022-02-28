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
	user_address VARCHAR(120) NOT NULL,
	username VARCHAR(50) NOT NULL UNIQUE,
    user_password VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	create_by VARCHAR(50) NOT NULL,
	update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by VARCHAR(50)
);
create table project(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	project_name VARCHAR(150) NOT NULL UNIQUE,
	project_description VARCHAR(120) NOT NULL,
	project_status VARCHAR(50),
	create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	create_by VARCHAR(50) NOT NULL,
	update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by VARCHAR(50)
);
create table task(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	task_name VARCHAR(150) NOT NULL,
    task_description VARCHAR(120) NOT NULL,
    create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	create_by VARCHAR(50) NOT NULL,
	update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    update_by VARCHAR(50),
    user_id int(6) ,
    status_id int(6) ,
    project_id int(6)
);
create table status_task(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
	status_task_name VARCHAR(60) NOT NULL UNIQUE
);
create table u_role(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);
create table role_details(
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
    user_id INT(6) ,
    role_id int(6) 
);
create table project_role(	
	id INT(6)  AUTO_INCREMENT PRIMARY KEY,
    project_id int(6),
	role_details_id INT(6) 
);

ALTER TABLE task ADD FOREIGN KEY(user_id) REFERENCES t_user(id);
ALTER TABLE task ADD FOREIGN KEY(status_id) REFERENCES status_task(id);
ALTER TABLE task ADD FOREIGN KEY(project_id) REFERENCES project(id);

ALTER TABLE role_details ADD  FOREIGN KEY(user_id) REFERENCES t_user(id);
ALTER TABLE role_details ADD FOREIGN KEY(role_id) REFERENCES u_role(id);

ALTER TABLE project_role ADD FOREIGN KEY(role_details_id) REFERENCES role_details(id);
ALTER TABLE project_role ADD FOREIGN KEY(project_id) REFERENCES project(id);

DELIMITER $	
create procedure save_role_in_project(
	in email  VARCHAR(50),
    in roleId int(6),
    in projectId int(6)
)
BEGIN
	declare userId  int(6) default (select id from t_user where username = email);
	declare roleDetailsId  int(6);
	INSERT INTO role_details (user_id, role_id) SELECT * FROM 
 		(SELECT userId, roleId) 
	AS tmp WHERE NOT EXISTS 
		( SELECT id FROM role_details WHERE user_id = userId and role_id = roleId ) LIMIT 1;
	set roleDetailsId = (select id from role_details where role_id = roleId and user_id = userId) ;
 	insert into project_role(project_id, role_details_id) values( projectId , roleDetailsId );

END $
DELIMITER ; 
