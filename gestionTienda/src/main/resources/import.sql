INSERT INTO `users` (username, password, enabled, name, lastname, direction, amount_product) VALUES ('juan','$2a$10$hdTaz3vfnc2ikq7EiUyjXO1ImAN6BrEomAno0hToE7bhBAKTeJyhC',2,'juan','berenguer','juan@juan',1);

INSERT INTO `roles` (name) VALUES ('ROL_USER');
INSERT INTO `roles` (name) VALUES ('ROL_ADMIN');

INSERT INTO `users_roles` (users_id, roles_id) VALUES (1,1);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2,2);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2,1);

INSERT INTO product (name, price, create_at) VALUES('Cascos inalambricos', 2.99, NOW());
INSERT INTO product (name, price, create_at) VALUES('Bateria portable', 7, NOW());
INSERT INTO product (name, price, create_at) VALUES('relojes', 5.90, NOW());


INSERT INTO `inventory` (amount, total) VALUES (21, 2.99);
INSERT INTO `inventory` (amount, total) VALUES (21, 2.99);
INSERT INTO `inventory` (amount, total) VALUES (21, 2.99);

INSERT INTO `detail` (description) VALUES ('Detalles de compra');
