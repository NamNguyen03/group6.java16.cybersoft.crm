create database CRM;
use CRM;
CREATE TABLE t_user (
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(60) NOT NULL UNIQUE,
	u_password VARCHAR(60) NOT NULL,
	fullname VARCHAR(60) NOT NULL,
    address VARCHAR(120) NOT NULL,
    phone VARCHAR(20) NOT NULL,
	create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_by VARCHAR(60) NOT NULL,
    update_by VARCHAR(60)
);

insert into t_user(username, u_password, fullname, create_by, address, phone)
value("nam@gmail.com", "123456","Nguyễn Đức Nam", "nam" ,"HCM", "030303021020");

update t_user
set u_password = "123456", update_by = "nam Nguyen"
where id =1;
