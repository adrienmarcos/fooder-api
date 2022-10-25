create table tb_request(
    id bigint not null auto_increment,
    subtotal decimal(10, 2) not null,
    freight_tax decimal(10, 2) not null,
    total_price decimal(10, 2) not null,
    creation_date datetime not null,
    confirmation_date datetime null,
    cancellation_date datetime null,
    delivery_date datetime null,
    address_zip_code varchar(9) not null,
    address_address varchar(80) not null,
    address_number varchar(80) not null,
    address_complement varchar(80) null,
    address_district varchar(80) not null,
    address_city_id bigint(20) not null,
    status varchar(10) not null,
    restaurant_id bigint not null,
    user_client_id bigint not null,
    payment_method_id bigint not null,

    primary key(id),
    constraint fk_request_address_city foreign key (address_city_id) references tb_city(id),
    constraint fk_request_restaurant foreign key (restaurant_id) references tb_restaurant(id),
    constraint fk_request_user_client foreign key (user_client_id) references tb_user(id),
    constraint fk_request_payment_menthod foreign key (payment_method_id) references tb_payment_method(id)
)engine=InnoDB;

    create table tb_ordered_item(
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    unit_price decimal(10, 2) not null,
    total_price decimal(10, 2) not null,
    note varchar(255) null,
    request_id bigint not null,
    product_id bigint not null,

    primary key(id),
    unique key uk_ordered_item_product (request_id, product_id),
    constraint fk_ordered_item_request foreign key (request_id) references tb_request(id),
    constraint fk_ordered_item_product foreign key (product_id) references tb_product(id)
    )engine=InnoDB;