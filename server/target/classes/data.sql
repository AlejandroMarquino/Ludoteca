INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');
INSERT INTO CATEGORY(id, name) VALUES (4, 'Comedi');

INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');

INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);

INSERT INTO CUSTOMER(id, name) VALUES (1, 'Adrián Perez');
INSERT INTO CUSTOMER(id, name) VALUES (2, 'Alex Liam');
INSERT INTO CUSTOMER(id, name) VALUES (3, 'Sandra Calvente');
INSERT INTO CUSTOMER(id, name) VALUES (4, 'Engeru Herrera');

INSERT INTO LEASING(id, game_id, customer_id, leasing_date, end_date) VALUES (1, 1, 1, '2022-01-01', '2022-01-13');
INSERT INTO LEASING(id, game_id, customer_id, leasing_date, end_date) VALUES (2, 2, 1, '2022-02-22', '2022-02-25');
INSERT INTO LEASING(id, game_id, customer_id, leasing_date, end_date) VALUES (3, 3, 2, '2022-12-05', '2022-12-10');
INSERT INTO LEASING(id, game_id, customer_id, leasing_date, end_date) VALUES (4, 4, 2, '2022-07-25', '2022-07-29');
INSERT INTO LEASING(id, game_id, customer_id, leasing_date, end_date) VALUES (5, 5, 3, '2022-05-29', '2022-06-03');
INSERT INTO LEASING(id, game_id, customer_id, leasing_date, end_date) VALUES (6, 6, 3, '2022-03-16', '2022-03-22');