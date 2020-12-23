CREATE TABLE state (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO state (id, name) VALUES(1, 'Acre');
INSERT INTO state (id, name) VALUES(2, 'Alagoas');
INSERT INTO state (id, name) VALUES(3, 'Amapá');
INSERT INTO state (id, name) VALUES(4, 'Amazonas');
INSERT INTO state (id, name) VALUES(5, 'Bahia');
INSERT INTO state (id, name) VALUES(6, 'Ceará');
INSERT INTO state (id, name) VALUES(7, 'Distrito Federal');
INSERT INTO state (id, name) VALUES(8, 'Espírito Santo');
INSERT INTO state (id, name) VALUES(9, 'Goiás');
INSERT INTO state (id, name) VALUES(10, 'Maranhão');
INSERT INTO state (id, name) VALUES(11, 'Mato Grosso');
INSERT INTO state (id, name) VALUES(12, 'Mato Grosso do Sul');
INSERT INTO state (id, name) VALUES(13, 'Minas Gerais');
INSERT INTO state (id, name) VALUES(14, 'Pará');
INSERT INTO state (id, name) VALUES(15, 'Paraíba');
INSERT INTO state (id, name) VALUES(16, 'Paraná');
INSERT INTO state (id, name) VALUES(17, 'Pernambuco');
INSERT INTO state (id, name) VALUES(18, 'Piauí');
INSERT INTO state (id, name) VALUES(19, 'Rio de Janeiro');
INSERT INTO state (id, name) VALUES(20, 'Rio Grande do Norte');
INSERT INTO state (id, name) VALUES(21, 'Rio Grande do Sul');
INSERT INTO state (id, name) VALUES(22, 'Rondônia');
INSERT INTO state (id, name) VALUES(23, 'Roraima');
INSERT INTO state (id, name) VALUES(24, 'Santa Catarina');
INSERT INTO state (id, name) VALUES(25, 'São Paulo');
INSERT INTO state (id, name) VALUES(26, 'Sergipe');
INSERT INTO state (id, name) VALUES(27, 'Tocantins');

CREATE TABLE city (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
  	id_state BIGINT NOT NULL,
  	FOREIGN KEY (id_state) REFERENCES state(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO city (id, name, id_state) VALUES (1, 'Rio Branco', 1);
INSERT INTO city (id, name, id_state) VALUES (2, 'Maceió', 2);
INSERT INTO city (id, name, id_state) VALUES (3, 'Macapá', 3);
INSERT INTO city (id, name, id_state) VALUES (4, 'Manaus', 4);
INSERT INTO city (id, name, id_state) VALUES (5, 'Salvador', 5);
INSERT INTO city (id, name, id_state) VALUES (6, 'Fortaleza', 6);
INSERT INTO city (id, name, id_state) VALUES (7, 'Brasília', 7);
INSERT INTO city (id, name, id_state) VALUES (8, 'Vitória', 8);
INSERT INTO city (id, name, id_state) VALUES (9, 'Goiânia', 9);
INSERT INTO city (id, name, id_state) VALUES (10, 'São Luís', 10);
INSERT INTO city (id, name, id_state) VALUES (11, 'Cuiabá', 11);
INSERT INTO city (id, name, id_state) VALUES (12, 'Campo Grande', 12);
INSERT INTO city (id, name, id_state) VALUES (13, 'Belo Horizonte', 13);
INSERT INTO city (id, name, id_state) VALUES (14, 'Belém', 14);
INSERT INTO city (id, name, id_state) VALUES (15, 'João Pessoa', 15);
INSERT INTO city (id, name, id_state) VALUES (16, 'Curitiba', 16);
INSERT INTO city (id, name, id_state) VALUES (17, 'Recife', 17);
INSERT INTO city (id, name, id_state) VALUES (18, 'Teresina', 18);
INSERT INTO city (id, name, id_state) VALUES (19, 'Rio de Janeiro', 19);
INSERT INTO city (id, name, id_state) VALUES (20, 'Natal', 20);
INSERT INTO city (id, name, id_state) VALUES (21, 'Porto Alegre', 21);
INSERT INTO city (id, name, id_state) VALUES (22, 'Porto Velho', 22);
INSERT INTO city (id, name, id_state) VALUES (23, 'Boa Vista', 23);
INSERT INTO city (id, name, id_state) VALUES (24, 'Florianópolis', 24);
INSERT INTO city (id, name, id_state) VALUES (25, 'São Paulo', 25);
INSERT INTO city (id, name, id_state) VALUES (26, 'Aracaju', 26);
INSERT INTO city (id, name, id_state) VALUES (27, 'Palmas', 27);


ALTER TABLE person DROP COLUMN city;
ALTER TABLE person DROP COLUMN state;
ALTER TABLE person ADD COLUMN id_city BIGINT;
ALTER TABLE person ADD CONSTRAINT fk_person_city FOREIGN KEY (id_city) REFERENCES city(id);

UPDATE person SET id_city = 25;
