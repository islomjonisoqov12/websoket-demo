insert into users (id, full_name, password, username) values ('c45ab69a-057f-43ae-95b4-e8e7300c4e84', 'Islomjon Isoqov', '1', 'i');
insert into users (id, full_name, password, username) values ('0a7c8fe5-1291-4043-855b-c937b9371889', 'Farrux Boboqulov', '2', 'f');
insert into users (id, full_name, password, username) values ('f1a897aa-7ce0-4695-a3d6-977eaed981f0', 'Xonzoda Intoyatova', '3', 'x');

insert into role(id, name) values ('66f4acdb-3e8c-4a83-8000-80dc1bb644db', 'user');

insert into users_roles(user_id, role_id) values ('c45ab69a-057f-43ae-95b4-e8e7300c4e84','66f4acdb-3e8c-4a83-8000-80dc1bb644db');
insert into users_roles(user_id, role_id) values ('0a7c8fe5-1291-4043-855b-c937b9371889','66f4acdb-3e8c-4a83-8000-80dc1bb644db');
insert into users_roles(user_id, role_id) values ('f1a897aa-7ce0-4695-a3d6-977eaed981f0','66f4acdb-3e8c-4a83-8000-80dc1bb644db');


