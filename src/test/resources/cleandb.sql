delete from users;
INSERT INTO users (first_name, last_name, username, email, password, is_active, role_id, date_created)
VALUES
('Joe','Coyne','jcoyne', 'email@email.com', 'supersecret1', true, 1, '1964-04-01'),
('Fred','Hensen','fhensen', 'email@email.com', 'supersecret2', true, 1, '1988-05-08'),
('Barney','Curry','bcurry', 'email@email.com', 'supersecret3', true, 2, '1947-11-11'),
('Karen','Mack','kmack', 'email@email.com', 'supersecret4', true, 1, '1986-07-08'),
('Dianne','Klein','dklein', 'email@email.com', 'supersecret5', true, 1, '1991-09-22'),
('Dawn','Tillman','dtillman', 'email@email.com', 'supersecret6', true, 1, '1979-08-30');