create table orders (
    id bigserial primary key,
    description varchar(255),
    order_time timestamp not null
);

alter table pancake
add column order_id bigint;

alter table pancake
add constraint fk_pancake_order
foreign key (order_id) references orders(id);