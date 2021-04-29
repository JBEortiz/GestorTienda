INSERT INTO `users` (username, password, enabled, name, lastname, direction) VALUES ('juan','$2a$10$hdTaz3vfnc2ikq7EiUyjXO1ImAN6BrEomAno0hToE7bhBAKTeJyhC',2,'juan','berenguer','juan@juan');
INSERT INTO `users` (username, password, enabled, name, lastname, direction) VALUES ('sara','$2a$10$23ZdWFN.RVCJ9MTZl52l1.sLjGo7iqsp75vjtocb5AhUmfFrpM1ZK',2,'sara','vidal','vida@vida');

INSERT INTO `roles` (name) VALUES ('ROL_USER');
INSERT INTO `roles` (name) VALUES ('ROL_ADMIN');

INSERT INTO `users_roles` (users_id, roles_id) VALUES (1,1);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2,2);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2,1);
