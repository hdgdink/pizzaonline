INSERT INTO USER VALUES (0, 'Andrey', 'Vassilyev', 'admin', 'admin@mail.ru', 'admin', 'ADMIN', '0');
INSERT INTO USER VALUES (1, 'Иван', 'Колымага', 'user', 'user@mail.ru', '123', 'CLIENT', '10000');
INSERT INTO USER VALUES (2, 'Алеша', 'Колотун', 'user1', 'user1@mail.ru', '123', 'CLIENT', '10000');

INSERT INTO SIZE VALUES (0, 100);
INSERT INTO SIZE VALUES (1, 130);
INSERT INTO SIZE VALUES (2, 150);

INSERT INTO TYPE VALUES (0, 'PIZZA');
INSERT INTO TYPE VALUES (1, 'SUBS');
INSERT INTO TYPE VALUES (2, 'BEVERAGE');


INSERT INTO FOOD VALUES (0, 0, 'Гавайская пицца', 'Hawaiian', 'Ананас и ветчина.', 'Pineapple and ham', NULL, 9,
                         '../static/img/pizza/hawaiipizza.png');
INSERT INTO FOOD VALUES (1, 0, 'Пицца Крестного Отца', 'Godfather', 'Ветчина, колбаса, пепперони и лук.',
                         'Ham, sausage, pepperoni and onions', NULL, 9,
                         '../static/img/pizza/godfatherpizza.png');
INSERT INTO FOOD VALUES
  (2, 0, 'Кубинская пицца', 'Cuban Style', 'Сладкие бананы и говядина.', 'Sweet plantains and ground beef', NULL, 11,
   '../static/img/pizza/cubanpizza.png');
INSERT INTO FOOD VALUES (3, 0, 'Пицца Монна Лиза', 'Mona Lisa', 'Ветчина, грибы, лук, зеленый перец и маслины',
                         'Ham, mushrooms, onions, green peppers and black olives', NULL, 10,
                         '../static/img/pizza/monalizapizza.png');
INSERT INTO FOOD VALUES
  (4, 0, 'Белая сырная пицца', 'White Cheese', 'Рикотта, моцарелла и сыр пармезан, чесночное масло.',
   'Ricotta, mozzarella and parmesan cheese, garlic oil', NULL, 11,
   '../static/img/pizza/cheasepizza.png');
INSERT INTO FOOD VALUES (5, 0, 'Пицца Ассорти Гурман', 'Assorti Gourmet',
                         'Ассорти из 4 видов пиццы! Кубинский стиль, Мелани, Дамиан, Гавайский.',
                         'Cuban style, Melany''s, Damian''s, Hawaiian', NULL, 11,
                         '../static/img/pizza/assortipizza.png');
INSERT INTO FOOD VALUES
  (6, 0, 'Пицца Мелани', 'Melany''s', 'Ветчина, ананас, кукуруза и лук.', 'Ham, pineapple, corn and onions', NULL, 9,
   '../static/img/pizza/melonyspizza.png');
INSERT INTO FOOD VALUES
  (7, 0, 'Пицца Дамиана', 'Damian''s', 'Цыпленок, соус для барбекю и лук.', 'Chicken, BBQ sauce and onions', NULL, 9,
   '../static/img/pizza/damianpizza.png');

