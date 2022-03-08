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
values ('Nguyen Duc Nam', 'HCM', 'lead@gmail.com', '123456', '09988222211' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Nguyen Duc Nam', 'HCM', 'member@gmail.com', '123456', '09988822211' , 'nam@gmail.com' );

insert into t_user(fullname, user_address, username, user_password, phone, create_by)
values ('Nguyen Duc Nam', 'HCM', 'user@gmail.com', '123456', '0944444211' , 'nam@gmail.com' );

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