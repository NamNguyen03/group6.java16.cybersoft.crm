use CRM;

-- insert role
insert into u_role(role_name) 
values('ADMIN');

insert into u_role(role_name) 
values('LEADER');

insert into u_role(role_name) 
values('MEMBER');

-- insert user
insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Nguyen Duc Nam ADMIN', 'Q1, HCM', 'nam-admin@gmail.com', '123456', '09988772111' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Nguyen Duc Nam', 'HCM', 'nam@gmail.com', '123456', '09988222111' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Nam Nguyen', 'HCM', 'lead@gmail.com', '123456', '09988222211' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Duc Nam Nguyen', 'HCM', 'member@gmail.com', '123456', '09988822211' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Nguyen Duc Nam', 'HCM', 'user@gmail.com', '123456', '0944444211' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Le Trung Hau', 'HCM', 'hau@gmail.com', '123456', '062123123' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Hau Le', 'HCM', 'hau_le@gmail.com', '123456', '02121212' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Trung Hau Le', 'HCM', 'hau1@gmail.com', '123456', '0999222255' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Pham Huy Phan', 'HCM', 'phan@gmail.com', '123456', '0999522255' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Phan Pham', 'HCM', 'phan_pham@gmail.com', '123456', '09934355' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Huy Phan Pham', 'HCM', 'phan1@gmail.com', '123456', '0999522255' , 'nam@gmail.com' );

-- insert role details
insert into role_details(user_id, role_id)
value (1,1);

insert into role_details(user_id, role_id)
value (2,1);

-- insert project
insert into  project(project_name, project_description, project_status, create_by)
values ('CRM', 'CRM project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('elearning', 'elearning project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('ecommerce', 'ecommerce project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('CRM 2', 'CRM 2 project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('elearning 2', 'elearning 2 project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('ecommerce 2', 'ecommerce 2 project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('CRM 3', 'CRM 3 project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('elearning 3', 'elearning 3 project', 'pending', 'nam');

insert into  project(project_name, project_description, project_status, create_by)
values ('ecommerce 3', 'ecommerce 3 project', 'pending', 'nam');