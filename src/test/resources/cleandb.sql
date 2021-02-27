delete from roles;
delete from lessons;
delete from users;
INSERT INTO users (id, first_name, last_name, user_name, email, password, is_active, date_created) VALUES (1, 'Joe','Coyne','jcoyne', 'email@email.com', 'supersecret1', true, '1964-04-01'), (2, 'Fred','Hensen','fhensen', 'email@email.com', 'supersecret2', true, '1988-05-08'), (3, 'Barney','Curry','bcurry', 'email@email.com', 'supersecret3', true, '1947-11-11'), (4, 'Karen','Mack','kmack', 'email@email.com', 'supersecret4', true, '1986-07-08'), (5, 'Dianne','Klein','dklein', 'email@email.com', 'supersecret5', true, '1991-09-22'), (6, 'Dawn','Tillman','dtillman', 'email@email.com', 'supersecret6', true, '1979-08-30');
INSERT INTO `roles` VALUES (1,'regUser','jcoyne'),(2,'regUser','fhensen'),(3,'admin','bcurry'),(4,'regUser','bcurry'),(5,'regUser','kmack'),(6,'admin','dklein');
INSERT INTO `lessons` VALUES (1, 1, 2), (2, 2, 1), (3, 4, 6), (4, 6, 5);