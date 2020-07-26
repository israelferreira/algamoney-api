CREATE TABLE state (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO state (id, name) VALUES(1, 'Acre');
INSERT INTO state (id, name) VALUES(2, 'Alagoas');
INSERT INTO state (id, name) VALUES(3, 'Amazonas');
INSERT INTO state (id, name) VALUES(4, 'Amapá');
INSERT INTO state (id, name) VALUES(5, 'Bahia');
INSERT INTO state (id, name) VALUES(6, 'Ceará');
INSERT INTO state (id, name) VALUES(7, 'Distrito Federal');
INSERT INTO state (id, name) VALUES(8, 'Espírito Santo');
INSERT INTO state (id, name) VALUES(9, 'Goiás');
INSERT INTO state (id, name) VALUES(10, 'Maranhão');
INSERT INTO state (id, name) VALUES(11, 'Minas Gerais');
INSERT INTO state (id, name) VALUES(12, 'Mato Grosso do Sul');
INSERT INTO state (id, name) VALUES(13, 'Mato Grosso');
INSERT INTO state (id, name) VALUES(14, 'Pará');
INSERT INTO state (id, name) VALUES(15, 'Paraíba');
INSERT INTO state (id, name) VALUES(16, 'Pernambuco');
INSERT INTO state (id, name) VALUES(17, 'Piauí');
INSERT INTO state (id, name) VALUES(18, 'Paraná');
INSERT INTO state (id, name) VALUES(19, 'Rio de Janeiro');
INSERT INTO state (id, name) VALUES(20, 'Rio Grande do Norte');
INSERT INTO state (id, name) VALUES(21, 'Rondônia');
INSERT INTO state (id, name) VALUES(22, 'Roraima');
INSERT INTO state (id, name) VALUES(23, 'Rio Grande do Sul');
INSERT INTO state (id, name) VALUES(24, 'Santa Catarina');
INSERT INTO state (id, name) VALUES(25, 'Sergipe');
INSERT INTO state (id, name) VALUES(26, 'São Paulo');
INSERT INTO state (id, name) VALUES(27, 'Tocantins');

CREATE TABLE city (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
  	id_state BIGINT NOT NULL,
  	FOREIGN KEY (id_state) REFERENCES state(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO city (id, name, id_state) VALUES (1, 'Belo Horizonte', 11);
INSERT INTO city (id, name, id_state) VALUES (2, 'Uberlândia', 11);
INSERT INTO city (id, name, id_state) VALUES (3, 'Uberaba', 11);
INSERT INTO city (id, name, id_state) VALUES (4, 'São Paulo', 26);
INSERT INTO city (id, name, id_state) VALUES (5, 'Campinas', 26);
INSERT INTO city (id, name, id_state) VALUES (6, 'Rio de Janeiro', 19);
INSERT INTO city (id, name, id_state) VALUES (7, 'Angra dos Reis', 19);
INSERT INTO city (id, name, id_state) VALUES (8, 'Goiânia', 9);
INSERT INTO city (id, name, id_state) VALUES (9, 'Caldas Novas', 9);

ALTER TABLE person DROP COLUMN city;
ALTER TABLE person DROP COLUMN state;
ALTER TABLE person ADD COLUMN id_city BIGINT;
ALTER TABLE person ADD CONSTRAINT fk_person_city FOREIGN KEY (id_city) REFERENCES city(id);

UPDATE person SET id_city = 2;
