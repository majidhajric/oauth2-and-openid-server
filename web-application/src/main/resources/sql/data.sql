insert into
user_account_entity(id, email, password, enabled, account_non_expired, credentials_non_expired, account_non_locked)
values ('00000000-0000-0000-0000-000000000000', 'user@email.com', '{noop}password', true, true, true, true);

insert into privilege_entity(id, authority) values(1, 'READ');
insert into privilege_entity(id, authority) values(2, 'WRITE');
insert into privilege_entity(id, authority) values(3, 'DELETE');
insert into privilege_entity(id, authority) values(4, 'EXECUTE');

insert into role_entity(id, authority) values(1, 'USER');
insert into role_entity(id, authority) values(2, 'ADVANCED_USER');
insert into role_entity(id, authority) values(3, 'ADMIN');

insert into role_privilege(role_id, privilege_id) values(1, 1);
insert into role_privilege(role_id, privilege_id) values(1, 2);

insert into role_privilege(role_id, privilege_id) values(2, 1);
insert into role_privilege(role_id, privilege_id) values(2, 2);
insert into role_privilege(role_id, privilege_id) values(2, 4);

insert into role_privilege(role_id, privilege_id) values(3, 1);
insert into role_privilege(role_id, privilege_id) values(3, 2);
insert into role_privilege(role_id, privilege_id) values(3, 3);
insert into role_privilege(role_id, privilege_id) values(3, 4);

insert into user_account_role (user_account_id, role_id) values ('00000000-0000-0000-0000-000000000000', 1);


