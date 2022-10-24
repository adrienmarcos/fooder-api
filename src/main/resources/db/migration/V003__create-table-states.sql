CREATE TABLE tb_states (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,

    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tb_cities (name_city, name_state) VALUES('Uberlândia', 'Minas Gerais');
INSERT INTO tb_cities (name_city, name_state) VALUES('Belo Horizonte', 'Minas Gerais');
INSERT INTO tb_cities (name_city, name_state) VALUES('São Paulo', 'São Paulo');
INSERT INTO tb_cities (name_city, name_state) VALUES('Londrinha', 'Paraná');
INSERT INTO tb_cities (name_city, name_state) VALUES('Curitiba', 'Paraná');
INSERT INTO tb_cities (name_city, name_state) VALUES('Ribeirão Preto', 'São Paulo');
INSERT INTO tb_cities (name_city, name_state) VALUES('São José do Rio Preto', 'São Paulo');

INSERT INTO tb_states (name) SELECT DISTINCT name_state FROM tb_cities;

ALTER TABLE tb_cities
ADD COLUMN state_id BIGINT NOT NULL;

UPDATE tb_cities tc
SET tc.state_id = (SELECT ts.id
                   FROM tb_states ts
                   WHERE ts.name = tc.name_state);

ALTER TABLE tb_cities
ADD CONSTRAINT fk_city_state
FOREIGN KEY (state_id) REFERENCES tb_states(id);

ALTER TABLE tb_cities
DROP COLUMN name_state;

ALTER TABLE tb_cities
CHANGE name_city name VARCHAR(80) NOT NULL;