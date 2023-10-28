insert into
user_account_entity(id, email, password, enabled, account_non_expired, credentials_non_expired, account_non_locked)
values ('00000000-0000-0000-0000-000000000000', 'user@email.com', '{noop}password', true, true, true, true);

insert into privilege_entity(id, authority) values(1, 'READ');
insert into privilege_entity(id, authority) values(2, 'WRITE');
insert into privilege_entity(id, authority) values(3, 'DELETE');
insert into privilege_entity(id, authority) values(4, 'EXECUTE');

insert into role_entity(id, authority) values(0, 'ROLE_NEW_USER');
insert into role_entity(id, authority) values(1, 'ROLE_USER');
insert into role_entity(id, authority) values(2, 'ROLE_ADVANCED_USER');
insert into role_entity(id, authority) values(3, 'ROLE_ADMIN');

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

insert into client_registration_entity (
                id,
                authorization_grant_type ,
                authorization_uri ,
                client_authentication_method ,
                client_id,
                client_name ,
                client_secret,
                issuer_uri ,
                jwk_set_uri ,
                redirect_uri ,
                registration_id ,
                token_uri ,
                user_info_uri ,
                user_name_attribute_name
            ) values (
            0,
            'AUTHORIZATION_CODE',
            'https://accounts.google.com/o/oauth2/v2/auth',
            'CLIENT_SECRET_BASIC',
            '931830073995-vpp9ns0qv4hu76nvogt9au6mcehaaif4.apps.googleusercontent.com',
            'Google',
            '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',
            'https://accounts.google.com',
            'https://www.googleapis.com/oauth2/v3/certs',
            'http://localhost:9000/login/oauth2/code/google',
            'google',
            'https://www.googleapis.com/oauth2/v4/token',
            'https://www.googleapis.com/oauth2/v3/userinfo',
            'sub'
            );

insert into client_registration_entity_scopes(client_registration_entity_id, scopes)
values
(0,'openid');
insert into client_registration_entity_scopes(client_registration_entity_id, scopes)
values
(0,'profile');
insert into client_registration_entity_scopes(client_registration_entity_id, scopes)
values
(0,'email');