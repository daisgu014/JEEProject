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
('lengohau','$2a$10$UndjvYOSrlNG1XkJC1fP4OpEXigvJmFfXuoTNs4qqcT0TEH/knacS','Lê Ngô Hậu','ADMIN','09876543212','hau@gmail.com','TP. Hồ Chí Minh'),
('daisgu014','$2a$10$UndjvYOSrlNG1XkJC1fP4OpEXigvJmFfXuoTNs4qqcT0TEH/knacS','Nuyễn Hữu Đại','ADMIN','09876543222','dai@gmail.com','Bình Định'),
('daisgu013','$2a$10$UndjvYOSrlNG1XkJC1fP4OpEXigvJmFfXuoTNs4qqcT0TEH/knacS','Nuyễn Hữu Đại','USER','09876542122','dai@gmail.com','Bình Định')
