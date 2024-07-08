LOCK TABLES users WRITE, task WRITE, category WRITE;

SET foreign_key_checks = 0;

DELETE FROM users;
DELETE FROM task;
DELETE FROM category;

SET foreign_key_checks = 1;

ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE task AUTO_INCREMENT = 1;
ALTER TABLE category AUTO_INCREMENT = 1;

INSERT INTO users (email, password, name, username) VALUES ('john.doe@example.com', '$$2a$10$hIp7ZnM/l2uq9RHMFEWL1OnGrqmXUbrr2JOarWqZb0MGY7t/6Z696', 'John Doe', 'johndoe');
INSERT INTO users (email, password, name, username) VALUES ('juancassiano@example.com', '$$2a$10$hIp7ZnM/l2uq9RHMFEWL1OnGrqmXUbrr2JOarWqZb0MGY7t/6Z696', 'Juan Cassiano', 'juancassiano');
INSERT INTO users (email, password, name, username) VALUES ('emersonmendes@example.com', '$$2a$10$hIp7ZnM/l2uq9RHMFEWL1OnGrqmXUbrr2JOarWqZb0MGY7t/6Z696', 'Emerson Mendes', 'emersonmendes');

INSERT INTO category (name, user_id) VALUES ('Trabalho', 1);
INSERT INTO category (name, user_id) VALUES ('Casa', 1);
INSERT INTO category (name, user_id) VALUES ('Estudos', 2);
INSERT INTO category (name, user_id) VALUES ('Pessoal', 2);
INSERT INTO category (name, user_id) VALUES ('Hobbies', 3);

INSERT INTO task (name, description, created_at, updated_at, category_id, user_id, done) VALUES ('Finish project', 'Complete the project by the end of the week', '2024-07-01', '2024-07-01', 3, 1, 0);
INSERT INTO task (name, description, created_at, updated_at, category_id, user_id, done) VALUES ('Grocery shopping', 'Buy groceries for the week', '2024-07-01', '2024-07-01', 2, 1, 0);
INSERT INTO task (name, description, created_at, updated_at, category_id, user_id, done) VALUES ('Learn guitar', 'Practice guitar lessons', '2024-07-01', '2024-07-01', 5, 1, 0);

UNLOCK TABLES;
