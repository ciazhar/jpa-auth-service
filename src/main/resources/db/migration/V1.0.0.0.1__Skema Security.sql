insert into role (id_role, nama_role, label_role)
values ('r001', 'ADMIN', 'Admin');

insert into role (id_role, nama_role, label_role)
values ('r002', 'BASIC_USER', 'User');

insert into role (id_role, nama_role, label_role)
values ('r003', 'VERIVIED_USER', 'User');

insert into role (id_role, nama_role, label_role)
values ('r004', 'GOLDEN_USER', 'User');


insert into user (id_user, enabled, password, username, email, phone_number, avatar, first_name, last_name, birth_date, created_date, android_device_id,id_role)
values ('u001', '1', '$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq', 'admin','daisetsunakama@gmail.com','083838718716','blabla','Muhammad','Hafidz','1997-08-28','2017-01-01','px-01','r001');

insert into user (id_user, enabled, password, username, email, phone_number, avatar, first_name, last_name, birth_date, created_date,android_device_id, id_role)
values ('u002', '1', '$2a$12$gdXt7RKK3NteyWd2Z8K5T./ptvupi0MBG0ylW1WBO0/oEsMsGShbm', 'pm','pm@gmail.com','083838718716','blabla','Muhammad','Hafidz','1997-08-28','2017-01-01','px-01','r002');

insert into user (id_user, enabled, password, username, email, phone_number, avatar, first_name, last_name, birth_date, created_date,android_device_id,id_role)
values ('u003', '1', '$2a$12$mDfe5KMd/kni1QotPNEr6.vmGY2puAlw4uqoAziUU0lSJWg9I0lai', 'programmer','programmer@gmail.com','083838718716','blabla','Muhammad','Hafidz','1997-08-28','2017-01-01','px-01','r002');

insert into user (id_user, enabled, password, username, email, phone_number, avatar, first_name, last_name, birth_date, created_date,android_device_id,id_role)
values ('u004', '1', '$2a$12$qEco5G1EU1tF7llL7l2kf.vrWsFtwohI4I690guWEDJ.Zzh8LbG.i', 'tester','tester@gmail.com','083838718716','blabla','Muhammad','Hafidz','1997-08-28','2017-01-01','px-01','r002');


insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p001', 'SUPER_USER', 'Khusus untuk admin','r001');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p002', 'BASIC_USER', 'User biasa','r002');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p003', 'BASIC_USER', 'User biasa','r003');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p004', 'BASIC_USER', 'User biasa','r004');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p005', 'VERIVIED_USER', 'User yang sudah terdaftar','r003');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p006', 'VERIVIED_USER', 'User yang sudah terdaftar','r004');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('p007', 'GOLDEN_USER', 'User yang sudah membayar','r004');

