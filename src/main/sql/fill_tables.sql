INSERT INTO USER VALUES (0, 'Andrey', 'Vassilyev', 'admin', 'admin@mail.ru', 'admin', 'ADMIN', '0');
INSERT INTO USER VALUES (1, 'Иван', 'Колымага', 'user', 'user@mail.ru', '123', 'CLIENT', '10000');
INSERT INTO USER VALUES (2, 'Алеша', 'Колотун', 'user1', 'user1@mail.ru', '123', 'CLIENT', '10000');

INSERT INTO FOOD VALUES (0, 0, 'Hawaiian', 'Pineapple and ham', NULL, 9, NULL);
INSERT INTO FOOD VALUES (1, 0, 'Godfather', 'Ham, sausage, pepperoni and onions', NULL, 9, NULL);
INSERT INTO FOOD VALUES (2, 0, 'Cuban Style', 'Sweet plantains and ground beef', NULL, 11, NULL);
INSERT INTO FOOD VALUES (3, 0, 'Mona Lisa', 'Ham, mushrooms, onions, green peppers and black olives', NULL, 10, NULL);
INSERT INTO FOOD VALUES (4, 0, 'White Cheese', 'Ricotta, mozzarella and parmesan cheese, garlic oil', NULL, 11, NULL);
INSERT INTO FOOD VALUES (5, 0, 'Melany''s', 'Ham, pineapple, corn and onions', NULL, 9, NULL);
INSERT INTO FOOD VALUES (6, 0, 'Damian''s', 'Chicken, BBQ sauce and onions', NULL, 9, NULL);
INSERT INTO FOOD VALUES (7, 0, 'Assorti Gourmet', 'Cuban style, Melany''s, Damian''s, Hawaiian', NULL, 11, NULL);

INSERT INTO ACCESS VALUES (0, 'ADMIN');
INSERT INTO ACCESS VALUES (1, 'USER');

INSERT INTO SIZE VALUES (0, 100);
INSERT INTO SIZE VALUES (1, 130);
INSERT INTO SIZE VALUES (2, 150);

INSERT INTO TYPE VALUES (0, 'PIZZA');
INSERT INTO TYPE VALUES (1, 'SALAD');
INSERT INTO TYPE VALUES (2, 'BEVERAGE');
INSERT INTO TYPE VALUES (3, 'SUBS');