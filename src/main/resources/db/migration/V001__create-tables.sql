CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;

CREATE TABLE category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_category FOREIGN KEY (user_id) REFERENCES users(id)
) engine=InnoDB default charset=utf8;

CREATE TABLE task (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_at DATE,
    updated_at DATE,
    category_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    done BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (id),
    CONSTRAINT fk_category_task FOREIGN KEY (category_id) REFERENCES category(id),
    CONSTRAINT fk_user_task FOREIGN KEY (user_id) REFERENCES users(id)
) engine=InnoDB default charset=utf8;
