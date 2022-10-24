alter table tb_city
add constraint fk_city_state
foreign key (state_id) references tb_state (id);

alter table tb_group_permission
add constraint fk_group_permission_permission
foreign key (permission_id) references tb_permission (id);

alter table tb_group_permission
add constraint fk_group_permission_group
foreign key (group_id) references tb_group (id);

alter table tb_product
add constraint fk_product_restaurant
foreign key (restaurant_id) references tb_restaurant (id);

alter table tb_restaurant
add constraint fk_restaurant_city
foreign key (address_city_id) references tb_city (id);

alter table tb_restaurant
add constraint fk_restaurant_kitchen
foreign key (kitchen_id) references tb_kitchen (id);

alter table tb_restaurant_payment_method
add constraint fk_restaurant_payment_method_payment_method
foreign key (payment_method_id) references tb_payment_method (id);

alter table tb_restaurant_payment_method
add constraint fk_restaurant_payment_method_restaurant
foreign key (restaurant_id) references tb_restaurant (id);

alter table tb_user_group
add constraint fk_user_group_group
foreign key (group_id) references tb_group (id);

alter table tb_user_group
add constraint fk_user_group_user
foreign key (user_id) references tb_user (id);