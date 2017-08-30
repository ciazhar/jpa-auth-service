insert into role (id_role, nama_role, label_role)
values (1, 'ADMIN', 'Admin');

insert into role (id_role, nama_role, label_role)
values (2, 'BASIC_USER', 'Basic User');

insert into role (id_role, nama_role, label_role)
values (3, 'CREDENTIAL_USER', 'Credential User');


insert into user (id_user, enabled, password, email, id_role)
values (1, '1', '$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq', 'admin@mail.com',1);

insert into user (id_user, enabled, password, email, id_role)
values (1, '1', '$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq', 'user@mail.com',2);

insert into user (id_user, enabled, password, email, id_role)
values (1, '1', '$2a$04$v1UZ4q0SCMTX695Wyowu/eVW9pT3giQivAElwH0A9BuvXktpd/qTq', 'credential@mail.com',3);


insert into permission (id_permission, nama_permission, label_permission, id_role)
values (1, 'SUPER_USER', 'Khusus untuk admin', 1);

insert into permission (id_permission, nama_permission, label_permission, id_role)
values (2, 'BASIC_USER', 'User biasa',2);

insert into permission (id_permission, nama_permission, label_permission, id_role)
values (3, 'CREDENTIAL_USER', 'User gak biasa',2);

insert into permission (id_permission, nama_permission, label_permission, id_role)
values (4, 'CREDENTIAL_USER', 'User gak biasa',3);

