insert into users (username, password, full_name, role, phone, email, address) values
("AetherGS", "paimon", "Aether Tervat", "customer", "0917862112", "AetherGS@gmail.com", "1 Starfell Valley, Mondstadt, Tervat");

insert into orders (total_price, create_at, user_id, place) values (10670000, '2023-8-7', 1, "null"),
                                                                    (11070000, '2023-9-5', 1, "null"),
                                                                    (3990000, '2023-11-8', 1, "null");

insert into orderDetails (order_id, product_id, qty) values (1, 0000000001, 1),
                                                           (1, 0000000003, 2),
                                                           (2, 0000000002, 1),
                                                           (2, 0000000001, 1),
                                                           (2, 0000000005, 1),
                                                           (3, 0000000004, 1);
