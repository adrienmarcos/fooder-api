INSERT INTO tb_kitchens(id, name) VALUES(1, 'Tailandesa');
INSERT INTO tb_kitchens(id, name) VALUES(2, 'Indiana');
INSERT INTO tb_kitchens(id, name) VALUES(3, 'Japonesa');

INSERT INTO tb_states(id, name) VALUES(1, 'Minas Gerais');
INSERT INTO tb_states(id, name) VALUES(2, 'São Paulo');
INSERT INTO tb_states(id, name) VALUES(3, 'Paraná');

INSERT INTO tb_cities(id, name, state_id) VALUES(1, 'Ribeirão Preto', 2);
INSERT INTO tb_cities(id, name, state_id) VALUES(2, 'São Paulo', 2);
INSERT INTO tb_cities(id, name, state_id) VALUES(3, 'Curitiba', 3);
INSERT INTO tb_cities(id, name, state_id) VALUES(4, 'Londrina', 3);
INSERT INTO tb_cities(id, name, state_id) VALUES(5, 'Belo Horizonte', 1);

INSERT INTO tb_restaurants(id, name, freight_tax, kitchen_id, register_date, last_update_date, address_city_id, address_zip_code, address_address, address_number, address_district) VALUES(1, 'Tailandesa Caseira', 10.99, 1, utc_timestamp, utc_timestamp, 1, '13178-000', 'Francisco Junqueira', '800', 'Centro');
INSERT INTO tb_restaurants(id, name, freight_tax, kitchen_id, register_date, last_update_date, address_city_id, address_zip_code, address_address, address_number, address_district) VALUES(2, 'Indiana Brasil', 5.90, 2, utc_timestamp, utc_timestamp, 2, '18204-000', 'Floriano Peixoto', '1992', 'Jardim Califórnia');
INSERT INTO tb_restaurants(id, name, freight_tax, kitchen_id, register_date, last_update_date, address_city_id, address_zip_code, address_address, address_number, address_district) VALUES(3, 'Thai Delivery', 9.50, 2, utc_timestamp, utc_timestamp, 4, '10293-000', 'Ribeirania', '1023', 'Jardim Canadá');

INSERT INTO tb_payment_methods(id, description) VALUES(1, 'Cartão de crédito');
INSERT INTO tb_payment_methods(id, description) VALUES(2, 'Cartão de débito');
INSERT INTO tb_payment_methods(id, description) VALUES(3, 'Dinheiro');

INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(1, 1);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(1, 2);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(1, 3);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(2, 3);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(3, 2);
INSERT INTO tb_restaurant_payment_method(restaurant_id, payment_method_id) VALUES(3, 3);