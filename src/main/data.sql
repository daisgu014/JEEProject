CREATE DATABASE if not EXISTS tablestore;
use tablestore;

create Table Category(
    Id SMALLINT ZEROFILL AUTO_INCREMENT not null,
    Name varchar(100) NOT NULL,
    CreateAt Date DEFAULT CURRENT_DATE(),
    DeleteAt Date DEFAULT NULL,
    PRIMARY KEY (Id),
    UNIQUE(Name)
);
create Table Provider(
    Id SMALLINT ZEROFILL AUTO_INCREMENT Not NULL,
    Name varchar(200) NOT NULL,
    CreateAt Date DEFAULT CURRENT_DATE(),
    DeleteAt Date DEFAULT NULL,
    PRIMARY KEY(Id),
    UNIQUE(Name)

);
create Table Product(
    Id Int ZEROFILL AUTO_INCREMENT not NULL,
    Name VARCHAR(300) NOT NULL,
    Color varchar(50)NOT NULL,
    Price int NOT NULL check(Price >0),
    Instock int not NULL check(Instock >= 0),
    Status varchar(50) not NULL,
    CategoryId SMALLINT ZEROFILL NOT NULL,
    ProviderId SMALLINT ZEROFILL NOT NULL,
    CreateAt Date DEFAULT CURRENT_DATE(),
    DeleteAt Date DEFAULT NULL,
    UNIQUE(Name),
    PRIMARY KEY (Id),
    FOREIGN KEY(CategoryId) REFERENCES Category(Id),
    FOREIGN KEY(ProviderId) REFERENCES Provider(Id)
);   
create Table User(
    Id INT ZEROFILL AUTO_INCREMENT NOT NULL,
    UserName varchar(200) NOT  NULL,
    Password VARCHAR(500) NOT NULL,
    Role VARCHAR(50) NOT NULL,
    Phone VARCHAR(12) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Address VARCHAR(500) NOT NULL,
    CreateAt Date DEFAULT CURRENT_DATE(),
    DeleteAt Date DEFAULT NULL,
    UNIQUE(Phone),
    UNIQUE(userName),
    PRIMARY KEY(Id)
);
CREATE TABLE CartInfo(
    UserId INT ZEROFILL NOT NULL,
    ProductId INT ZEROFILL NOT NULL,
    Qty INT NOT NULL CHECK( Qty > 0),
    PRIMARY KEY(ProductId, UserId),
    FOREIGN KEY(ProductId) REFERENCES Product(Id),
    FOREIGN KEY(UserId) REFERENCES User(Id)
);
CREATE TABLE Orders(
    Id INT ZEROFILL AUTO_INCREMENT NOT NULL,
    TotalPrice INT NOT NULL check(TotalPrice > 0),
    BillFileName VARCHAR(100) DEFAULT NULL,
    createAt DATETIME DEFAULT CURRENT_DATE(),
    DeleteAt Date DEFAULT NULL,
    Place VARCHAR(500) NOT Null,
    ConfirmId INT ZEROFILL NOT NULL,
    ConfirmDate DATE DEFAULT NULL,
    UserId INT ZEROFILL NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(UserId) REFERENCES User(Id),
    FOREIGN KEY(ConfirmId) REFERENCES User(Id)
);
create table OrderDetails(
    OrderId Int ZEROFILL NOT NULL,
    ProductId int ZEROFILL NOT NULL,
    Qty INT NOT NULL check(Qty > 0),
    PRIMARY KEY(OrderId, ProductId),
    FOREIGN KEY(OrderId) REFERENCES Orders(Id),
    FOREIGN KEY(ProductId) REFERENCES Product(Id)
);
