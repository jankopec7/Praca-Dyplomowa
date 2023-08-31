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


