SET foreign_key_checks = 0;

DELETE FROM tb_city;
DELETE FROM tb_group;
DELETE FROM tb_group_permission;
DELETE FROM tb_kitchen;
DELETE FROM tb_payment_method;
DELETE FROM tb_permission;
DELETE FROM tb_product;
DELETE FROM tb_restaurant;
DELETE FROM tb_restaurant_payment_method;
DELETE FROM tb_state;
DELETE FROM tb_user;
DELETE FROM tb_user_group;

SET foreign_key_checks = 1;

ALTER TABLE tb_city AUTO_INCREMENT = 1;
ALTER TABLE tb_group AUTO_INCREMENT = 1;
ALTER TABLE tb_kitchen AUTO_INCREMENT = 1;
ALTER TABLE tb_payment_method AUTO_INCREMENT = 1;
ALTER TABLE tb_permission AUTO_INCREMENT = 1;
ALTER TABLE tb_product AUTO_INCREMENT = 1;
ALTER TABLE tb_restaurant AUTO_INCREMENT = 1;
ALTER TABLE tb_state AUTO_INCREMENT = 1;
ALTER TABLE tb_user AUTO_INCREMENT = 1;

INSERT INTO tb_kitchen(id, name) VALUES(1, 'Tailandesa');
INSERT INTO tb_kitchen(id, name) VALUES(2, 'Indiana');
INSERT INTO tb_kitchen(id, name) VALUES(3, 'Japonesa');
INSERT INTO tb_kitchen(id, name) VALUES(4, 'Americana');
INSERT INTO tb_kitchen(id, name) VALUES(5, 'Mexicana');
INSERT INTO tb_kitchen(id, name) VALUES(6, 'Brasileira');
INSERT INTO tb_kitchen(id, name) VALUES(7, 'Espanhola');
INSERT INTO tb_kitchen(id, name) VALUES(8, 'Alemã');
INSERT INTO tb_kitchen(id, name) VALUES(9, 'Francesa');
INSERT INTO tb_kitchen(id, name) VALUES(10, 'Chinesa');

INSERT INTO tb_state(id, name) VALUES(1, 'Minas Gerais');
INSERT INTO tb_state(id, name) VALUES(2, 'São Paulo');
INSERT INTO tb_state(id, name) VALUES(3, 'Mato Grosso');
INSERT INTO tb_state(id, name) VALUES(4, 'Mato Grosso do Sul');
INSERT INTO tb_state(id, name) VALUES(5, 'Santa Catarina');
INSERT INTO tb_state(id, name) VALUES(6, 'Amazonas');
INSERT INTO tb_state(id, name) VALUES(7, 'Paraná');

INSERT INTO tb_city(id, name, state_id) VALUES(1, 'Ribeirão Preto', 2);
INSERT INTO tb_city(id, name, state_id) VALUES(2, 'São Paulo', 2);
INSERT INTO tb_city(id, name, state_id) VALUES(3, 'Curitiba', 7);
INSERT INTO tb_city(id, name, state_id) VALUES(4, 'Londrina', 7);
INSERT INTO tb_city(id, name, state_id) VALUES(5, 'Belo Horizonte', 1);
INSERT INTO tb_city(id, name, state_id) VALUES(6, 'Santos', 2);
INSERT INTO tb_city(id, name, state_id) VALUES(7, 'São José do Rio Preto', 2);
INSERT INTO tb_city(id, name, state_id) VALUES(8, 'Porecatu', 7);
INSERT INTO tb_city(id, name, state_id) VALUES(9, 'Santa Fé do Sul', 4);
INSERT INTO tb_city(id, name, state_id) VALUES(10, 'Uberlândia', 1);

INSERT INTO tb_restaurant(id, name, freight_tax, kitchen_id, register_date, last_update_date, address_city_id, address_zip_code, address_address, address_number, address_district) VALUES(1, 'Tailandesa Caseira', 10.99, 1, utc_timestamp, utc_timestamp, 1, '13178-000', 'Francisco Junqueira', '800', 'Centro');
INSERT INTO tb_restaurant(id, name, freight_tax, kitchen_id, register_date, last_update_date, address_city_id, address_zip_code, address_address, address_number, address_district) VALUES(2, 'Indiana Brasil', 5.90, 2, utc_timestamp, utc_timestamp, 2, '18204-000', 'Floriano Peixoto', '1992', 'Jardim Califórnia');
INSERT INTO tb_restaurant(id, name, freight_tax, kitchen_id, register_date, last_update_date, address_city_id, address_zip_code, address_address, address_number, address_district) VALUES(3, 'Thai Delivery', 9.50, 2, utc_timestamp, utc_timestamp, 4, '10293-000', 'Ribeirania', '1023', 'Jardim Canadá');

INSERT INTO tb_payment_method(id, description) VALUES(1, 'Cartão de crédito');
INSERT INTO tb_payment_method(id, description) VALUES(2, 'Cartão de débito');
INSERT INTO tb_payment_method(id, description) VALUES(3, 'Dinheiro');

INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(1, 1);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(1, 2);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(1, 3);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(2, 3);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(3, 2);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(3, 3);

INSERT INTO tb_product(id, name, description, price, active, restaurant_id) VALUES(1, 'Sashimi', 'Salmão não cozido', 46.99, true, 3);
INSERT INTO tb_product(id, name, description, price, active, restaurant_id) VALUES(2, 'Pão de pimenta', 'Pão levemente apimentado', 10.95, true, 2);
INSERT INTO tb_product(id, name, description, price, active, restaurant_id) VALUES(3, 'Niguiri', 'Salmão não cozido com arroz', 20.99, true, 3);
INSERT INTO tb_product(id, name, description, price, active, restaurant_id) VALUES(4, 'Macarrão a lá Tailand', 'Macarrão com shoyu', 80.99, true, 1);

INSERT INTO tb_permission(id, name, description) VALUES(1, 'Mensagens promocionais', 'Enviar mensagens promocionais aos clientes');
INSERT INTO tb_permission(id, name, description) VALUES(2, 'Dados sigilosos', 'Consultar dados sigilosos do cliente');
INSERT INTO tb_permission(id, name, description) VALUES(3, 'Score', 'Consulta do score do cliente');
INSERT INTO tb_permission(id, name, description) VALUES(4, 'Promoções', 'Visualizar promoções de restaurantes');

INSERT INTO tb_group(id, name) VALUES(1, 'Consumidores');
INSERT INTO tb_group(id, name) VALUES(2, 'Administradores');
INSERT INTO tb_group(id, name) VALUES(3, 'Fornecedores');

INSERT INTO tb_group_permission(group_id, permission_id) VALUES(1, 4);
INSERT INTO tb_group_permission(group_id, permission_id) VALUES(2, 2);
INSERT INTO tb_group_permission(group_id, permission_id) VALUES(2, 4);
INSERT INTO tb_group_permission(group_id, permission_id) VALUES(3, 1);
INSERT INTO tb_group_permission(group_id, permission_id) VALUES(3, 3);

INSERT INTO tb_user(id, name, mail, password, register_date) VALUES(1, 'Adrien Rossato', 'adrien.marcos@gmail.com', 'test_password', utc_timestamp);
INSERT INTO tb_user(id, name, mail, password, register_date) VALUES(2, 'Samara Menezes', 'samara_1609@hotmail.com', 'mexoCelularDosOutros', utc_timestamp);
INSERT INTO tb_user(id, name, mail, password, register_date) VALUES(3, 'Fulano de tal', 'fulano.tal@gmail.com', 'test_password', utc_timestamp);

INSERT INTO tb_user_group(user_id, group_id) VALUES(1, 2);
INSERT INTO tb_user_group(user_id, group_id) VALUES(3, 1);