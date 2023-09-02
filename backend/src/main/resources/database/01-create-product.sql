--liquibase formatted sql
--changeset jkopec7:1

create table product(
        id bigint not null auto_increment PRIMARY KEY,
        name varchar(255) not null,
        category varchar(255) not null,
        description text not null,
        price decimal(9, 2) not null,
        currency varchar(5) not null
);

--changeset jkopec7:2

alter table product add image varchar(128) after currency;

--changeset jkopec7:3

alter table product add slug varchar(255) after image;
alter table product add constraint ui_product_slug unique key(slug);

--changeset jkopec7:4
alter table product add full_description text default null after description;

--changeset jkopec7:5
create table users(
        id bigint not null auto_increment PRIMARY KEY,
        username varchar(50) not null unique,
        password varchar(500) not null,
        enabled boolean not null
);
--changeset jkopec7:6
create table authorities (
        username varchar(50) not null,
        authority varchar(50) not null,
        constraint fk_authorities_users foreign key(username) references users(username)
);
--changeset jkopec7:7
create unique index ix_auth_username on authorities (username,authority);

--changeset jkopec7:8
insert into users (id, username, password, enabled)
values (1, 'admin', '{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);
insert into authorities (username, authority) values ('admin','ROLE_ADMIN');

--changeset jkopec7:9
alter table users add hash varchar(120);
--changeset jkopec7:10
alter table users add hash_date datetime;
