CREATE DATABASE if not EXISTS TableStore;
use TableStore;

create Table categories(
    id SMALLINT ZEROFILL AUTO_INCREMENT not null,
    name varchar(100) NOT NULL,
    create_at Date DEFAULT CURRENT_DATE(),
    delete_at Date DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE(name)
);
create Table providers(
    id SMALLINT ZEROFILL AUTO_INCREMENT Not NULL,
    name varchar(200) NOT NULL,
    create_at Date DEFAULT CURRENT_DATE(),
    delete_at Date DEFAULT NULL,
    PRIMARY KEY(id),
    UNIQUE(name)

);
create Table products(
    id Int ZEROFILL AUTO_INCREMENT not NULL,
    name VARCHAR(300) NOT NULL,
    color varchar(50)NOT NULL,
    price int NOT NULL check(price >0),
    in_stock int not NULL check(in_stock >= 0),
    status varchar(50) not NULL,
    category_id SMALLINT ZEROFILL NOT NULL,
    provider_id SMALLINT ZEROFILL NOT NULL,
    create_at Date DEFAULT CURRENT_DATE(),
    delete_at Date DEFAULT NULL,
    UNIQUE(name),
    PRIMARY KEY (id),
    FOREIGN KEY(category_id) REFERENCES categories(id),
    FOREIGN KEY(provider_id) REFERENCES providers(id)
);   
create Table users(
    id INT ZEROFILL AUTO_INCREMENT NOT NULL,
    username varchar(200) NOT  NULL,
    password VARCHAR(500) NOT NULL,
    role VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL,
    create_at Date DEFAULT CURRENT_DATE(),
    delete_at Date DEFAULT NULL,
    UNIQUE(phone),
    UNIQUE(userName),
    PRIMARY KEY(id)
);
CREATE TABLE cart_infos(
    user_id INT ZEROFILL NOT NULL,
    product_id INT ZEROFILL NOT NULL,
    qty INT NOT NULL CHECK( qty > 0),
    PRIMARY KEY(user_id, product_id),
    FOREIGN KEY(product_id) REFERENCES products(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);
CREATE TABLE orders(
    id INT ZEROFILL AUTO_INCREMENT NOT NULL,
    total_price INT NOT NULL check(total_price > 0),
    bill_file_name VARCHAR(100) DEFAULT NULL,
    create_at Date DEFAULT CURRENT_DATE(),
    delete_at Date DEFAULT NULL,
    place VARCHAR(500) NOT Null,
    confirm_id INT ZEROFILL NOT NULL,
    confirm_date DATE DEFAULT NULL,
    user_id INT ZEROFILL NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(confirm_id) REFERENCES users(id)
);
create table orderDetails(
    order_id Int ZEROFILL NOT NULL,
    product_id int ZEROFILL NOT NULL,
    qty INT NOT NULL check(qty > 0),
    PRIMARY KEY(order_id, product_id),
    FOREIGN KEY(order_id) REFERENCES orders(id),
    FOREIGN KEY(product_id) REFERENCES products(id)
);
