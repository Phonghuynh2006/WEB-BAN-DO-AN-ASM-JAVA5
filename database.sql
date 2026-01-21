/* =========================================
   TẠO DATABASE
========================================= */
CREATE DATABASE WEB_BAN_DO_AN;
GO
USE WEB_BAN_DO_AN;
GO

/* =========================================
   1. USERS – NGƯỜI DÙNG
========================================= */
CREATE TABLE Users (
    id INT IDENTITY PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    password NVARCHAR(255) NOT NULL,
    role NVARCHAR(10) CHECK (role IN ('ADMIN','USER')) NOT NULL
);
GO

/* DỮ LIỆU MẪU USERS (5 USER + 1 ADMIN) */
INSERT INTO Users (full_name, phone, email, password, role) VALUES
(N'Nguyễn Văn A', '090000001', 'user1@gmail.com', '123456', 'USER'),
(N'Trần Văn B', '090000002', 'user2@gmail.com', '123456', 'USER'),
(N'Lê Văn C', '090000003', 'user3@gmail.com', '123456', 'USER'),
(N'Phạm Văn D', '090000004', 'user4@gmail.com', '123456', 'USER'),
(N'Hoàng Văn E', '090000005', 'user5@gmail.com', '123456', 'USER'),
(N'Quản Trị Viên', '099999999', 'admin@gmail.com', 'admin123', 'ADMIN');
GO

/* =========================================
   2. MENU_TYPES – NHÓM THỰC ĐƠN (CẤP 1)
========================================= */
CREATE TABLE Menu_Types (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(50) NOT NULL
);
GO

INSERT INTO Menu_Types (name) VALUES
(N'Đồ ăn'),
(N'Nước uống'),
(N'Combo');
GO

/* =========================================
   3. CATEGORIES – LOẠI (CẤP 2)
========================================= */
CREATE TABLE Categories (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    menu_type_id INT NOT NULL,
    FOREIGN KEY (menu_type_id) REFERENCES Menu_Types(id)
);
GO

/* 10 LOẠI */
INSERT INTO Categories (name, menu_type_id) VALUES
-- ĐỒ ĂN
(N'Gà', 1),
(N'Mì', 1),
(N'Pizza', 1),

-- NƯỚC UỐNG
(N'Trà sữa', 2),
(N'Nước ngọt', 2),
(N'Cà phê', 2),

-- COMBO
(N'Combo gia đình', 3),
(N'Combo cặp đôi', 3),
(N'Combo trẻ em', 3),
(N'Combo tiết kiệm', 3);
GO

/* =========================================
   4. PRODUCTS – 20 SẢN PHẨM
========================================= */
CREATE TABLE Products (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(150) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description NVARCHAR(255),
    image NVARCHAR(255),
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Categories(id)
);
GO

/* 20 SẢN PHẨM MẪU */
INSERT INTO Products (name, price, description, image, category_id) VALUES
-- GÀ
(N'Gà rán', 45000, N'Gà rán giòn', 'ga_ran.jpg', 1),
(N'Gà sốt cay', 48000, N'Gà sốt cay Hàn', 'ga_cay.jpg', 1),

-- MÌ
(N'Mì Ý bò', 55000, N'Mì Ý sốt bò', 'mi_y_bo.jpg', 2),
(N'Mì Indo', 40000, N'Mì cay Indo', 'mi_indo.jpg', 2),

-- PIZZA
(N'Pizza bò', 89000, N'Pizza bò phô mai', 'pizza_bo.jpg', 3),
(N'Pizza hải sản', 99000, N'Pizza hải sản', 'pizza_hs.jpg', 3),

-- TRÀ SỮA
(N'Trà sữa trân châu', 30000, N'Trà sữa truyền thống', 'ts_tc.jpg', 4),
(N'Trà sữa matcha', 32000, N'Trà sữa matcha', 'ts_matcha.jpg', 4),

-- NƯỚC NGỌT
(N'Coca Cola', 15000, N'Nước ngọt Coca', 'coca.jpg', 5),
(N'Pepsi', 15000, N'Nước ngọt Pepsi', 'pepsi.jpg', 5),

-- CÀ PHÊ
(N'Cà phê đen', 25000, N'Cà phê đen đá', 'cf_den.jpg', 6),
(N'Cà phê sữa', 28000, N'Cà phê sữa đá', 'cf_sua.jpg', 6),

-- COMBO
(N'Combo gia đình A', 199000, N'Combo cho gia đình', 'combo_gd.jpg', 7),
(N'Combo gia đình B', 219000, N'Combo lớn', 'combo_gd2.jpg', 7),

(N'Combo cặp đôi A', 129000, N'Combo 2 người', 'combo_cd.jpg', 8),
(N'Combo cặp đôi B', 149000, N'Combo lãng mạn', 'combo_cd2.jpg', 8),

(N'Combo trẻ em A', 79000, N'Combo cho bé', 'combo_te.jpg', 9),
(N'Combo trẻ em B', 89000, N'Combo đồ chơi', 'combo_te2.jpg', 9),

(N'Combo tiết kiệm A', 99000, N'Combo giá rẻ', 'combo_tk.jpg', 10),
(N'Combo tiết kiệm B', 109000, N'Combo tiết kiệm', 'combo_tk2.jpg', 10);
GO
