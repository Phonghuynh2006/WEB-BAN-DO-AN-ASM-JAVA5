CREATE DATABASE WEB_BAN_DO_AN;
GO
USE WEB_BAN_DO_AN;
GO
                     
/* ---------- USERS ---------- */
CREATE TABLE users (
    id INT IDENTITY PRIMARY KEY,
    fullname NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NULL,
    phone NVARCHAR(15) NULL,
    password NVARCHAR(255) NOT NULL,
    role NVARCHAR(20) NOT NULL DEFAULT 'USER',

    CONSTRAINT CK_app_users_email_or_phone
    CHECK (email IS NOT NULL OR phone IS NOT NULL),

    CONSTRAINT CK_app_users_role
    CHECK (role IN ('USER', 'ADMIN'))
);

INSERT INTO users (fullname, email, phone, password, role) VALUES
(N'Admin System', 'admin@gmail.com', '0900000000', '123456', 'ADMIN'),
(N'Nguyễn Văn A', 'user@gmail.com', '0911111111', '123456', 'USER'),
(N'Trần Thị B', NULL, '0922222222', '123456', 'USER');


/* ---------- MENUS ---------- */
CREATE TABLE menus (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(50) NOT NULL UNIQUE,
    is_active BIT NOT NULL DEFAULT 1
);

/* ---------- MENU TYPES ---------- */
CREATE TABLE menu_types (
    id INT IDENTITY PRIMARY KEY,
    menu_id INT NOT NULL,
    name NVARCHAR(50) NOT NULL,
    is_active BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_menu_types_menu
        FOREIGN KEY (menu_id)
        REFERENCES menus(id)
        ON DELETE CASCADE
);

/* ---------- MENU ITEMS ---------- */
CREATE TABLE menu_items (
    id INT IDENTITY PRIMARY KEY,
    menu_type_id INT NOT NULL,
    name NVARCHAR(100) NOT NULL,
    image_url NVARCHAR(255) NULL,
    size NVARCHAR(20) NULL,        -- S, M, L hoặc NULL
    description NVARCHAR(255) NULL,
    price DECIMAL(10,2) NOT NULL,
    is_available BIT NOT NULL DEFAULT 1,

    CONSTRAINT CK_menu_items_price
        CHECK (price > 0),

    CONSTRAINT FK_menu_items_menu_type
        FOREIGN KEY (menu_type_id)
        REFERENCES menu_types(id)
        ON DELETE CASCADE
);

/* ---------- ORDERS ---------- */
CREATE TABLE orders (
    id INT IDENTITY PRIMARY KEY,
    user_id INT NULL,  -- NULL nếu khách vãng lai

    order_type NVARCHAR(20) NOT NULL,   -- SHIP | COUNTER
    customer_name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15) NOT NULL,
    address NVARCHAR(255) NULL,         -- chỉ dùng cho SHIP
    note NVARCHAR(255) NULL,

    total_amount DECIMAL(10,2) NOT NULL DEFAULT 0,
    order_status NVARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT CK_orders_order_type
        CHECK (order_type IN ('SHIP', 'COUNTER')),

    CONSTRAINT CK_orders_order_status
        CHECK (order_status IN ('PENDING', 'PAID', 'CANCELLED')),

    CONSTRAINT CK_orders_address_for_ship
        CHECK (
            (order_type = 'SHIP' AND address IS NOT NULL)
            OR
            (order_type = 'COUNTER' AND address IS NULL)
        ),

    CONSTRAINT FK_orders_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

/* ---------- ORDER ITEMS ---------- */
CREATE TABLE order_items (
    id INT IDENTITY PRIMARY KEY,
    order_id INT NOT NULL,
    menu_item_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,

    CONSTRAINT CK_order_items_quantity
        CHECK (quantity > 0),

    CONSTRAINT CK_order_items_price
        CHECK (price > 0),

    CONSTRAINT FK_order_items_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,

    CONSTRAINT FK_order_items_menu_item
        FOREIGN KEY (menu_item_id)
        REFERENCES menu_items(id)
);

/* ---------- PAYMENTS ---------- */
CREATE TABLE payments (
    id INT IDENTITY PRIMARY KEY,
    order_id INT NOT NULL,
    payment_method NVARCHAR(20) NOT NULL,  -- CASH | TRANSFER
    payment_status NVARCHAR(20) NOT NULL DEFAULT 'SUCCESS',
    paid_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT CK_payments_method
        CHECK (payment_method IN ('CASH', 'TRANSFER')),

    CONSTRAINT CK_payments_status
        CHECK (payment_status IN ('SUCCESS', 'FAILED')),

    CONSTRAINT FK_payments_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE
);

INSERT INTO menus (name) VALUES
(N'Combo'),
(N'Gà Rán'),
(N'Nước Uống'),
(N'Burger');

INSERT INTO menu_types (menu_id, name) VALUES
-- Combo
(1, N'Combo Bán Chạy'),
(1, N'Combo Gia Đình'),

-- Gà rán
(2, N'Gà Giòn Cay'),
(2, N'Gà Truyền Thống'),

-- Nước uống
(3, N'Nước Có Ga'),
(3, N'Nước Không Ga'),

-- Burger
(4, N'Burger Bò'),
(4, N'Burger Gà');

INSERT INTO menu_items
(menu_type_id, name, image_url, size, description, price)
VALUES
-- 🔥 COMBO BÁN CHẠY
(1, N'Combo Gà Giòn Cay A',
 'https://lh3.googleusercontent.com/xxxxx1',
 'M',
 N'2 miếng gà giòn cay, khoai tây chiên M, Pepsi mát lạnh',
 129000),

(1, N'Combo Gà Truyền Thống B',
 'https://lh3.googleusercontent.com/xxxxx2',
 'L',
 N'3 miếng gà truyền thống, khoai tây L, 2 Pepsi',
 169000),

-- 🍗 GÀ RÁN
(3, N'Gà Giòn Cay (1 miếng)',
 'https://lh3.googleusercontent.com/xxxxx3',
 NULL,
 N'Gà rán giòn cay, nóng hổi',
 35000),

(4, N'Gà Truyền Thống (1 miếng)',
 'https://lh3.googleusercontent.com/xxxxx4',
 NULL,
 N'Gà rán truyền thống giòn rụm',
 32000),

-- 🥤 NƯỚC UỐNG
(5, N'Pepsi Lon',
 'https://lh3.googleusercontent.com/xxxxx5',
 NULL,
 N'Pepsi mát lạnh sảng khoái',
 15000),

(6, N'Trà Đào',
 'https://lh3.googleusercontent.com/xxxxx6',
 NULL,
 N'Trà đào thanh mát',
 20000),

-- 🍔 BURGER
(7, N'Burger Bò Phô Mai',
 'https://lh3.googleusercontent.com/xxxxx7',
 NULL,
 N'Burger bò phô mai béo ngậy',
 45000),

(8, N'Burger Gà Giòn',
 'https://lh3.googleusercontent.com/xxxxx8',
 NULL,
 N'Burger gà giòn cay',
 42000);
