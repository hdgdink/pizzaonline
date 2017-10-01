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
                         'Ham, sausage, pepperoni and onions', NULL, 9, '../static/img/pizza/godfatherpizza.png');
INSERT INTO FOOD VALUES
  (2, 0, 'Кубинская пицца', 'Cuban Style', 'Сладкие бананы и говядина.', 'Sweet plantains and ground beef', NULL, 11,
   '../static/img/pizza/cubanpizza.png');
INSERT INTO FOOD VALUES (3, 0, 'Пицца Монна Лиза', 'Mona Lisa', 'Ветчина, грибы, лук, зеленый перец и маслины',
                         'Ham, mushrooms, onions, green peppers and black olives', NULL, 10,
                         '../static/img/pizza/monalizapizza.png');
INSERT INTO FOOD
VALUES (4, 0, 'Белая сырная пицца', 'White Cheese', 'Рикотта, моцарелла и сыр пармезан, чесночное масло.',
        'Ricotta, mozzarella and parmesan cheese, garlic oil', NULL, 11, '../static/img/pizza/cheasepizza.png');
INSERT INTO FOOD VALUES (5, 0, 'Пицца Ассорти Гурман', 'Assorti Gourmet',
                         '4 в 1! Кубинский стиль, Мелани, Дамиан, Гавайский.',
                         'Cuban style, Melany''s, Damian''s, Hawaiian', NULL, 11,
                         '../static/img/pizza/assortipizza.png');
INSERT INTO FOOD VALUES
  (6, 0, 'Пицца Мелани', 'Melany''s', 'Ветчина, ананас, кукуруза и лук.', 'Ham, pineapple, corn and onions', NULL, 9,
   '../static/img/pizza/melonyspizza.png');
INSERT INTO FOOD VALUES
  (7, 0, 'Пицца Дамиана', 'Damian''s', 'Цыпленок, соус для барбекю и лук.', 'Chicken, BBQ sauce and onions', NULL, 9,
   '../static/img/pizza/damianpizza.png');


INSERT INTO FOOD VALUES (8, 1, 'Грудка индейки', 'Turkey Breast',
                         'Нежная грудь индейки, сложенная небом вверх со всем, от салата и помидоров до хрустящих огурцов.',
                         'Tender turkey breast piled sky-high with everything from lettuce and tomatoes to crispy cucumbers.',
                         NULL, 9, '../static/img/subs/turkey_breast.png');
INSERT INTO FOOD VALUES (9, 1, 'Фрикаделька Маринара', 'Meatball Marinara',
                         'Мясо в итальянском стиле, пропитанным соусом маринара, на свежеиспеченном хлебе.',
                         'Italian style meatballs drenched in irresistible marinara sauce, on freshly baked bread.',
                         NULL, 9, '../static/img/subs/meatball_marinara.png');
INSERT INTO FOOD VALUES (10, 1, 'Итальянский Б.М.Т.', 'Italian B.M.T.',
                         'Эта итальянская классика наполнена салями из Генуи, пряным пепперони и «Шварцвальдом Хэм».',
                         'This all-time Italian classic is filled with Genoa salami, spicy pepperoni, and Black Forest Ham.',
                         NULL, 9, '../static/img/subs/italian_bmt.png');
INSERT INTO FOOD VALUES (11, 1, 'Комбо из холодных нарезок', 'Cold Cut Combo',
                         'Комбинация холодных нарезок сочетается с мясом на основе индейки - ветчиной, салями и болоньей. Он увенчан свежими овощами и подается на свежеиспеченном хлебе.',
                         'The Cold Cut Combo is stacked with turkey-based meats - ham, salami and bologna. It''s topped with crisp vegetables and served on freshly baked bread.',
                         NULL, 9, '../static/img/subs/cold_cut_combo.png');
INSERT INTO FOOD VALUES (12, 1, 'Печень с жареной курицей', 'Oven Roasted Chicken',
                         'Жаренная курица,сложена на свежем выпеченном хлебе с любимыми начинками, такими как хрустящие зеленые перцы и огурцы.',
                         'The Oven Roasted Chicken you love is piled high atop freshly baked bread with your favorite toppings like crispy green peppers and cucumbers.',
                         NULL, 9, '../static/img/subs/oven_roasten.png');
INSERT INTO FOOD VALUES (13, 1, 'Пряный итальянский', 'Spicy Italian',
                         'Смесь пепперони и салями, увенчанная сыром - попробуйте с банановым перцем или на выбор с хрустящими овощями и приправами, подаваемыми на свежеиспеченном хлебе.',
                         'A blend of pepperoni and salami, topped with cheese – try it with banana peppers, or your choice of crisp veggies and condiments served hot on freshly baked bread.',
                         NULL, 9, '../static/img/subs/spicy_italian.png');
INSERT INTO FOOD VALUES (14, 1, 'Стейк и сыр', 'Steak & Cheese',
                         'Два из самых неотразимых ингредиентов в мире - нагромождены на свежеиспеченный хлеб и на ваш выбор свежих овощей.',
                         'Two of the most irresistible ingredients in the world — piled high onto freshly baked bread and your choice of crisp veggies.',
                         NULL, 9, '../static/img/subs/steak_cheese.png');
INSERT INTO FOOD VALUES (15, 1, 'Жареная говядина', 'Roast Beef',
                         'Чистая жареная говядина и свежие овощи, такие как свежий зеленый перец и сочные помидоры.',
                         'Lean roast beef and your choice of fresh veggies, like crisp green peppers and juicy tomatoes.',
                         NULL, 9, '../static/img/subs/roast_beef.png');
INSERT INTO FOOD VALUES (16, 1, 'Цыпленок в стиле Ротиссери', 'Rotisserie-Style Chicken',
                         'Наш кулинарный сэндвич в стиле «Ротиссери» изготовлен из нежной, полностью выдержанной вручную куриного мяса с приправой и маринадом. Попробуйте с хрустящими овощами на свежеиспеченном хлебе.',
                         'Our Rotisserie-Style Chicken Sandwich is made with tender, hand-pulled all-white meat chicken with seasoning and marinade. Try it with crisp veggies on freshly baked bread.',
                         NULL, 9, '../static/img/subs/rotisserie_chiken.png');


INSERT INTO FOOD VALUES (17, 2, 'Coca-Cola', 'Coca-Cola','','',NULL, 9, '../static/img/bevs/coca.png');
INSERT INTO FOOD VALUES (18, 2, 'Fanta', 'Fanta','','',NULL, 9, '../static/img/bevs/fanta.png');
INSERT INTO FOOD VALUES (19, 2, 'Mirinda', 'Mirinda','','',NULL, 9, '../static/img/bevs/mirinda.png');
INSERT INTO FOOD VALUES (20, 2, 'Pepsi', 'Pepsi','','',NULL, 9, '../static/img/bevs/pepsi.png');
INSERT INTO FOOD VALUES (21, 2, '7UP', '7UP','','',NULL, 9, '../static/img/bevs/seven_up.png');