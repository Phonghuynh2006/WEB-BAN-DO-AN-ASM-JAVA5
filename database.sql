CREATE DATABASE WEB_BAN_DO_AN;

USE WEB_BAN_DO_AN;
GO

/* ===== B?NG Users ===== */
CREATE TABLE Users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NULL,
    phone VARCHAR(20) UNIQUE NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
        CHECK (role IN ('admin', 'customer'))
);

INSERT INTO Users (full_name, email, phone, password, role)
VALUES
(N'Nguy?n V?n Admin', 'admin@gmail.com', NULL, '123456', 'admin'),
(N'Tr?n Th? Lan', 'lan@gmail.com', '0901234567', '123456', 'customer'),
(N'Lê Minh Tu?n', NULL, '0912345678', '123456', 'customer');


/* ===== B?NG Menus ===== */
CREATE TABLE Menus (
    menu_id INT IDENTITY(1,1) PRIMARY KEY,
    menu_name NVARCHAR(100) NOT NULL
);

INSERT INTO Menus (menu_name)
VALUES
(N'?? ?n'),
(N'N??c u?ng'),
(N'Combo');


/* ===== B?NG Menu_Items ===== */
CREATE TABLE Menu_Items (
    item_id INT IDENTITY(1,1) PRIMARY KEY,
    menu_id INT NOT NULL,
    item_name NVARCHAR(150) NOT NULL,
    description NVARCHAR(255),
    image VARCHAR(255),
    size VARCHAR(5) CHECK (size IN ('S', 'M', 'L')),
    price DECIMAL(10,2) NOT NULL,
    status BIT DEFAULT 1,

    CONSTRAINT fk_menu_items_menu
        FOREIGN KEY (menu_id)
        REFERENCES Menus(menu_id)
);

INSERT INTO Menu_Items (menu_id, item_name, description, image, size, price, status)
VALUES
(1, N'C?m gà x?i m?', N'C?m gà giòn r?m, ?n kèm n??c m?m', 'comga.jpg', 'M', 35000, 1),
(1, N'C?m s??n n??ng', N'S??n n??ng than hoa', 'comsuon.jpg', 'L', 40000, 1),
(2, N'Trà s?a trân châu', N'Trà s?a truy?n th?ng', 'trasua.jpg', 'M', 30000, 1);


/* ===== B?NG Orders ===== */
CREATE TABLE Orders (
    order_id INT IDENTITY(1,1) PRIMARY KEY,
    order_type VARCHAR(20) CHECK (order_type IN ('SHIP', 'COUNTER')),
    payment_method VARCHAR(20) CHECK (payment_method IN ('CASH', 'ATM')),
    note NVARCHAR(255),
    total_amount DECIMAL(10,2),
    created_at DATETIME DEFAULT GETDATE()
);

INSERT INTO Orders (order_type, payment_method, note, total_amount)
VALUES
('SHIP', 'ATM', N'Giao gi? hành chính', 95000),
('COUNTER', 'CASH', N'Mua t?i qu?y', 40000),
('SHIP', 'CASH', N'G?i tr??c khi giao', 65000);


/* ===== B?NG Order_Items ===== */
CREATE TABLE Order_Items (
    order_item_id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    item_name NVARCHAR(150),
    size VARCHAR(5),
    price DECIMAL(10,2),
    quantity INT,

    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id) REFERENCES Orders(order_id),

    CONSTRAINT fk_order_items_item
        FOREIGN KEY (item_id) REFERENCES Menu_Items(item_id)
);

INSERT INTO Order_Items (order_id, item_id, item_name, size, price, quantity)
VALUES
(1, 3, N'Trà s?a trân châu', 'M', 30000, 2),
(1, 1, N'C?m gà x?i m?', 'M', 35000, 1),
(2, 2, N'C?m s??n n??ng', 'L', 40000, 1),
(3, 3, N'Trà s?a trân châu', 'L', 35000, 1),
(3, 1, N'C?m gà x?i m?', 'M', 30000, 1);


/* ===== B?NG Order_Customers ===== */
CREATE TABLE Order_Customers (
    order_id INT PRIMARY KEY,
    customer_name NVARCHAR(100),
    phone VARCHAR(20),
    address NVARCHAR(255),

    CONSTRAINT fk_order_customers_order
        FOREIGN KEY (order_id) REFERENCES Orders(order_id)
);

INSERT INTO Order_Customers (order_id, customer_name, phone, address)
VALUES
(1, N'Nguy?n V?n A', '0909123456', N'123 Lê L?i, Q1'),
(2, N'Tr?n Th? B', '0912345678', NULL),
(3, N'Lê Minh C', '0988777666', N'45 Nguy?n Trãi, Q5');
