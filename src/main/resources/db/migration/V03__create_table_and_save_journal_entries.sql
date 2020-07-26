CREATE TABLE journal_entry (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	amount DECIMAL(10,2) NOT NULL,
	observation VARCHAR(100),
	entry_type VARCHAR(20) NOT NULL,
	id_category BIGINT NOT NULL,
	id_person BIGINT NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
) ENGINE=InnoDB;

INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Salário mensal', '2020-01-27', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Supermercado', '2020-03-10', '2020-03-01', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Academia', '2020-04-10', null, 120, null, 'DESPESA', 3, 3);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Conta de luz', '2020-02-10', '2020-02-10', 110.44, null, 'DESPESA', 3, 4);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Conta de água', '2020-02-15', null, 200.30, null, 'DESPESA', 3, 5);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Restaurante', '2020-03-14', '2020-03-14', 1010.32, null, 'DESPESA', 4, 6);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Venda vídeo game', '2020-01-01', null, 500, null, 'RECEITA', 1, 7);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Clube', '2020-03-07', '2020-03-05', 400.32, null, 'DESPESA', 4, 8);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Impostos', '2020-04-10', null, 123.64, 'Multas', 'DESPESA', 3, 9);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Multa', '2020-04-10', null, 665.33, null, 'DESPESA', 5, 10);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Padaria', '2020-02-28', '2020-02-28', 8.32, null, 'DESPESA', 1, 5);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Papelaria', '2020-02-10', '2020-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Almoço', '2020-03-09', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Café', '2020-02-20', '2020-02-18', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO journal_entry (description, due_date, payment_date, amount, observation, entry_type, id_category, id_person) values ('Lanche', '2020-04-10', null, 10.20, null, 'DESPESA', 4, 1);
