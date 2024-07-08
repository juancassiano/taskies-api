lock tables users write , task write, category write;

set foreign_key_checks = 0;

delete from users;
delete from task;
delete from category;

set foreign_key_checks = 1;

alter table users auto_increment = 1;
alter table task auto_increment = 1;
alter table category auto_increment = 1;

insert into category (name) values ('Trabalho');
insert into category (name) values ('Casa');
insert into category (name) values ('Estudos');
insert into category (name) values ('Pessoal');
insert into category (name) values ('Hobbies');

insert into users (email, password, name, username) values ('john.doe@example.com', '$2a$10$7QWZfxjt1R/Z0J9YZFmXO.OYmH8Z6PkjJ/rh7N2bJEVKspwKmjY3y', 'John Doe', 'johndoe');
insert into users (email, password, name, username) values ('juancassiano@example.com', '$2a$10$7QWZfxjt1R/Z0J9YZFmXO.OYmH8Z6PkjJ/rh7N2bJEVKspwKmjY3y', 'Juan Cassiano', 'juancassiano');
insert into users (email, password, name, username) values ('emersonmendes@example.com', '$2a$10$7QWZfxjt1R/Z0J9YZFmXO.OYmH8Z6PkjJ/rh7N2bJEVKspwKmjY3y', 'Emerson Mendes', 'emersonmendes');

insert into task (name, description, created_at, updated_at, category_id, done) values ('Finish project', 'Complete the project by the end of the week', '2024-07-01', '2024-07-01', 3, FALSE);
insert into task (name, description, created_at, updated_at, category_id, done) values ('Grocery shopping', 'Buy groceries for the week', '2024-07-01', '2024-07-01', 2, FALSE);
insert into task (name, description, created_at, updated_at, category_id, done) values ('Learn guitar', 'Practice guitar lessons', '2024-07-01', '2024-07-01', 5, FALSE);
