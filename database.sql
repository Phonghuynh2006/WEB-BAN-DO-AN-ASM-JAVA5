/* =========================================
   TẠO DATABASE
========================================= */
CREATE DATABASE WEB_BAN_DO_AN;
GO
USE WEB_BAN_DO_AN;
GO
                     

CREATE TABLE users (
    id INT IDENTITY PRIMARY KEY,
    fullname NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NULL,
    phone NVARCHAR(15) NULL,
    password NVARCHAR(255) NOT NULL,
    role NVARCHAR(20) NOT NULL DEFAULT 'USER',

    CONSTRAINT CK_users_email_or_phone
    CHECK (
        email IS NOT NULL OR phone IS NOT NULL
    )
);

CREATE TABLE menus (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(50) NOT NULL UNIQUE,
    is_active BIT NOT NULL DEFAULT 1
);


CREATE TABLE menu_types (
    id INT IDENTITY PRIMARY KEY,
    menu_id INT NOT NULL,
    name NVARCHAR(50) NOT NULL,
    is_active BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_menu_types_menu
        FOREIGN KEY (menu_id)
        REFERENCES menus(id)
);

CREATE TABLE menu_items (
    id INT IDENTITY PRIMARY KEY,

    menu_type_id INT NOT NULL,

    name NVARCHAR(100) NOT NULL,

    image_url NVARCHAR(255) NULL,

    size NVARCHAR(20) NULL,      -- S, M, L (hoặc NULL nếu không có size)

    description NVARCHAR(255) NULL,

    price DECIMAL(10,2) NOT NULL CHECK (price > 0),

    is_available BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_menu_items_menu_type
        FOREIGN KEY (menu_type_id)
        REFERENCES menu_types(id)
);

CREATE TABLE orders (
    id INT IDENTITY PRIMARY KEY,

    user_id INT NULL,  -- có thể NULL nếu khách vãng lai

    order_type NVARCHAR(20) NOT NULL,
    -- SHIP | COUNTER

    customer_name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15) NOT NULL,

    address NVARCHAR(255) NULL,   -- chỉ dùng cho SHIP

    note NVARCHAR(255) NULL,

    total_amount DECIMAL(10,2) NOT NULL CHECK (total_amount >= 0),

    order_status NVARCHAR(20) NOT NULL DEFAULT 'PENDING',
    -- PENDING | PAID | CANCELLED

    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT CK_orders_address_for_ship
    CHECK (
        (order_type = 'SHIP' AND address IS NOT NULL)
        OR
        (order_type = 'COUNTER' AND address IS NULL)
    )
);
CREATE TABLE payments (
    id INT IDENTITY PRIMARY KEY,

    order_id INT NOT NULL,

    payment_method NVARCHAR(20) NOT NULL,
    -- CASH | TRANSFER

    payment_status NVARCHAR(20) NOT NULL DEFAULT 'SUCCESS',
    -- SUCCESS | FAILED

    paid_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT FK_payments_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
);


/* =========================
   DATABASE STRUCTURE
   ========================= */

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
