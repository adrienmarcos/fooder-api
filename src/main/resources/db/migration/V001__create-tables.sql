create table tb_city (
    id bigint not null auto_increment,
    name varchar(80) not null,
    state_id bigint not null,

    primary key (id)
) engine=InnoDB;

create table tb_group (
    id bigint not null auto_increment,
    name varchar(80) not null,

    primary key (id)
) engine=InnoDB;

create table tb_group_permission (
    group_id bigint not null,
    permission_id bigint not null
) engine=InnoDB;

create table tb_kitchen (
    id bigint not null auto_increment,
    name varchar(80) not null,

    primary key (id)
) engine=InnoDB;

create table tb_payment_method (
    id bigint not null auto_increment,
    description varchar(120) not null,

    primary key (id)
) engine=InnoDB;

create table tb_permission (
    id bigint not null auto_increment,
    description varchar(120) not null,
    name varchar(80) not null,

    primary key (id)
) engine=InnoDB;

create table tb_product (
    id bigint not null auto_increment,
    active bit not null,
    description varchar(120) not null,
    name varchar(80) not null,
    price decimal(19,2),
    restaurant_id bigint,

    primary key (id)
) engine=InnoDB;

create table tb_restaurant (
    id bigint not null auto_increment,
    address_address varchar(80),
    address_complement varchar(120),
    address_district varchar(80),
    address_number varchar(80),
    address_zip_code varchar(80),
    freight_tax decimal(19,2) not null,
    last_update_date datetime not null,
    name varchar(80) not null,
    register_date datetime not null,
    address_city_id bigint,
    kitchen_id bigint not null,

    primary key (id)
) engine=InnoDB;

create table tb_restaurant_payment_method (
    restaurant_id bigint not null,
    payment_method_id bigint not null
) engine=InnoDB;

create table tb_state (
    id bigint not null auto_increment,
    name varchar(80) not null,

    primary key (id)
) engine=InnoDB;

create table tb_user (
    id bigint not null auto_increment,
    mail varchar(80) not null,
    name varchar(80) not null,
    password varchar(20) not null,
    register_date datetime not null,

    primary key (id)
) engine=InnoDB;

create table tb_user_group (
    user_id bigint not null,
    group_id bigint not null
) engine=InnoDB;