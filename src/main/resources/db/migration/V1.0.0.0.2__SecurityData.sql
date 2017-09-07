insert into role (id_role, nama_role, label_role)
values ('1', 'ADMIN', 'Admin');

insert into role (id_role, nama_role, label_role)
values ('2', 'BASIC_USER', 'Basic User');

insert into role (id_role, nama_role, label_role)
values ('3', 'CREDENTIAL_USER', 'Credential User');


insert into user (id_user, enabled, id_role, email, username, password)
values ('1', '1', '1', 'admin@mail.com', 'admin','$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq');

insert into user (id_user, enabled, id_role, email, username, password)
values ('2', '1', '2', 'user@mail.com', 'user','$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq');

insert into user (id_user, enabled, id_role, email, username, password)
values ('3', '1', '3', 'credential@mail.com', 'credential','$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq');


insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('1', 'SUPER_USER', 'Khusus untuk admin', '1');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('2', 'BASIC_USER', 'User biasa','2');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('3', 'CREDENTIAL_USER', 'User gak biasa','2');

insert into permission (id_permission, nama_permission, label_permission, id_role)
values ('4', 'CREDENTIAL_USER', 'User gak biasa','3');

