insert into categories (name) values
                                  ("Bàn Gaming E-DRA"), ("Ghế gaming"), ("Bàn chữ K");

insert into providers (name) values ("GearHCM");

insert into products (name, color, price, in_stock, status, category_id, provider_id, img_path) values
                                                                                                    ("Bàn Gaming E-DRA EGT1610AR", "Đen", 2690000, 100, "Đang kinh doanh", 1, 1, "BanGamingE-DRAEGT1610AR.png"),
                                                                                                    ("Bàn Gaming E-DRA EGT1610AR-Grey", "Xám", 2690000, 100, "Đang kinh doanh", 1, 1, "BanGamingE-DRAEGT1610AR.png"),
                                                                                                    ("Ghế công thái học Sihoo M57-Black", "Đen", 3990000, 100, "Đang kinh doanh", 2, 1, "GhecongthaihocSihooM57.png"),
                                                                                                    ("Ghế công thái học Sihoo M57-Grey", "Xám", 3990000, 100, "Đang kinh doanh", 2, 1, "GhecongthaihocSihooM57.png"),
                                                                                                    ("Ghế AKRacing Core Series LX Plus - Pink", "Hồng", 5690000, 100, "Đang kinh doanh", 2, 1, "GheAKRacingCoreSeriesLXPlus.png");


INSERT INTO users(username, password, full_name, role, phone, email, address) VALUES
('DieterWalker', '$2a$10$5BPjlQFVTHk8e8VSN219WuI6MbNYG1TOtarUvptpA93S9QZOk6eJe', 'Nguyễn Quốc Duệ', 'USER', '0946883860', 'nguyenquocdue32@gmail.com', 'Cà Mau'),
('lengohau','$2a$10$UndjvYOSrlNG1XkJC1fP4OpEXigvJmFfXuoTNs4qqcT0TEH/knacS','Lê Ngô Hậu','ADMIN','09876543212','hau@gmail.com','TP. Hồ Chí Minh'),
('daisgu014','$2a$10$UndjvYOSrlNG1XkJC1fP4OpEXigvJmFfXuoTNs4qqcT0TEH/knacS','Nuyễn Hữu Đại','ADMIN','09876543222','dai@gmail.com','Bình Định'),
('daisgu013','$2a$10$UndjvYOSrlNG1XkJC1fP4OpEXigvJmFfXuoTNs4qqcT0TEH/knacS','Nuyễn Hữu Đại','USER','09876542122','dai@gmail.com','Bình Định');


insert into orders (total_price, create_at, user_id, place) values (10670000, '2023-8-7', 1, 'null'),
                                                                    (11070000, '2023-9-5', 1, 'null'),
                                                                    (3990000, '2023-11-8', 1, 'null');

insert into orderDetails (order_id, product_id, qty) values (1, 0000000001, 1),
                                                           (1, 0000000003, 2),
                                                           (2, 0000000002, 1),
                                                           (2, 0000000001, 1),
                                                           (2, 0000000005, 1),
                                                           (3, 0000000004, 1);

