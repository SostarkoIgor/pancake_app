create table ingredient(
    id bigserial not null PRIMARY KEY,
    name varchar(255) not null,
    price decimal(10, 2) not null CONSTRAINT positive_price CHECK (price >= 0),
    category varchar(255) not null CONSTRAINT valid_category CHECK (category IN ('baza', 'nadjev', 'preljev', 'voće'))
);

create table pancake(
    id bigserial not null PRIMARY KEY
);

create table pancake_ingredient(
    pancake_id bigserial not null,
    ingredient_id bigserial not null,
    PRIMARY KEY (pancake_id, ingredient_id),
    FOREIGN KEY (pancake_id) REFERENCES pancake(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredient(id) ON DELETE CASCADE
);