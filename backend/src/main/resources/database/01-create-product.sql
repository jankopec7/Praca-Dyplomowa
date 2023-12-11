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

--changeset jkopec7:11
create table category(
                         id bigint not null auto_increment PRIMARY KEY,
                         name varchar(255) not null,
                         description text,
                         slug varchar(255) not null
);
--changeset jkopec7:12
alter table product add category_id bigint after category;
alter table product drop column category;
alter table product add constraint fk_product_category_id foreign key (category_id) references category(id);

--changeset jkopec7:13
insert into category (id, name, description, slug) values(1, 'Others', '', 'others');
update product set category_id=1;
alter table product MODIFY category_id bigint not null;

--changeset jkopec7:14
create table review(
                       id bigint not null auto_increment PRIMARY KEY,
                       product_id bigint not null,
                       author_name varchar(60) not null,
                       content text
);
--changeset jkopec7:15
alter table review add moderated boolean  default false;


--changeset jkopec7:16

create table cart(
                id bigint not null auto_increment PRIMARY KEY,
                created datetime not null);

--changeset jkopec7:17
create table cart_item (
                           id bigint not null auto_increment PRIMARY KEY,
                           product_id bigint not null,
                           quantity int,
                           cart_id bigint not null,
                           constraint fk_cart_item_product_id foreign key (product_id) references product(id),
                           constraint fk_cart_item_cart_id foreign key (cart_id) references cart(id)
);
