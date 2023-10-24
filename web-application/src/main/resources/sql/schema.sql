drop table user_account_entity if exists;
create table user_account_entity (
id varchar(255) not null,
email varchar(255) not null unique,
password varchar(255) not null,
enabled boolean not null,
account_non_expired boolean not null,
credentials_non_expired boolean not null,
account_non_locked boolean not null,
primary key (id)
);