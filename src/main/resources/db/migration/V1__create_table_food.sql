CREATE TABLE tb_food(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    validate DATE,
    quantity INTEGER NOT NULL
);