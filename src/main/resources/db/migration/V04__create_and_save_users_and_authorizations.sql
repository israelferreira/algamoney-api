CREATE TABLE user (
	id BIGINT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE authorization (
	id BIGINT PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_authorization (
	id_user BIGINT NOT NULL,
	id_authorization BIGINT NOT NULL,
	PRIMARY KEY (id_user, id_authorization),
	FOREIGN KEY (id_user) REFERENCES user(id),
	FOREIGN KEY (id_authorization) REFERENCES authorization(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO user (id, name, email, password) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO user (id, name, email, password) values (2, 'Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO authorization (id, description) values (1, 'ROLE_REGISTER_CATEGORY');
INSERT INTO authorization (id, description) values (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO authorization (id, description) values (3, 'ROLE_REGISTER_PERSON');
INSERT INTO authorization (id, description) values (4, 'ROLE_REMOVE_PERSON');
INSERT INTO authorization (id, description) values (5, 'ROLE_SEARCH_PERSON');

INSERT INTO authorization (id, description) values (6, 'ROLE_REGISTER_JOURNALENTRY');
INSERT INTO authorization (id, description) values (7, 'ROLE_REMOVE_JOURNALENTRY');
INSERT INTO authorization (id, description) values (8, 'ROLE_SEARCH_JOURNALENTRY');

-- admin
INSERT INTO user_authorization (id_user, id_authorization) values (1, 1);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 2);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 3);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 4);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 5);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 6);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 7);
INSERT INTO user_authorization (id_user, id_authorization) values (1, 8);

-- maria
INSERT INTO user_authorization (id_user, id_authorization) values (2, 2);
INSERT INTO user_authorization (id_user, id_authorization) values (2, 5);
INSERT INTO user_authorization (id_user, id_authorization) values (2, 8);
