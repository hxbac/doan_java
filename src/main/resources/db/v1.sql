DROP DATABASE IF EXISTS doan_java;

CREATE DATABASE doan_java;

use doan_java;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    role VARCHAR(255)
);
INSERT INTO user (name, email, password, address, role) VALUES
('John Doe', 'admin@gmail.com', '25d55ad283aa400af464c76d713c07ad', '123 Main St', 'ADMIN'),
('John Doe', 'user1@gmail.com', '25d55ad283aa400af464c76d713c07ad', '123 Main St', 'USER'),
('Jane Smith', 'user2@gmail.com', '25d55ad283aa400af464c76d713c07ad', '456 Oak St', 'USER'),
('Bob Johnson', 'user3@gmail.com', '25d55ad283aa400af464c76d713c07ad', '789 Elm St', 'USER'),
('Alice Williams', 'user4@gmail.com', '25d55ad283aa400af464c76d713c07ad', '101 Pine St', 'USER'),
('Charlie Brown', 'user5@gmail.com', '25d55ad283aa400af464c76d713c07ad', '321 Cedar St', 'USER');

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    price BIGINT,
    description TEXT
);
INSERT INTO product (name, image, price, description) VALUES 
('Product1', 'image1.jpg', 10000000, 'Description 1'),
('Product2', 'image2.jpg', 20000000, 'Description 2'),
('Product3', 'image3.jpg', 30000000, 'Description 3'),
('Product4', 'image4.jpg', 40000000, 'Description 4'),
('Product5', 'image5.jpg', 50000000, 'Description 5');

DROP TABLE IF EXISTS menu;
CREATE TABLE menu (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    link VARCHAR(255),
    `orderNum` INT
);
INSERT INTO menu (name, link, `orderNum`) VALUES 
('Trang chủ', '/', 1),
('Cửa hàng', '/shop/index', 2),
('Bài  viết', '/post/index', 3),
('Item4', 'link4', 4),
('Item5', 'link5', 5);

DROP TABLE IF EXISTS post;
CREATE TABLE post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    image VARCHAR(255),
    author VARCHAR(255)
);
INSERT INTO post (title, content, image, author) VALUES 
('Post 1', 'Content of post 1.', 'image1.jpg', 'author'),
('Post 2', 'Content of post 2.', 'image2.jpg', 'author'),
('Post 3', 'Content of post 3.', 'image3.jpg', 'author'),
('Post 4', 'Content of post 4.', 'image4.jpg', 'author'),
('Post 5', 'Content of post 5.', 'image5.jpg', 'author');

DROP TABLE IF EXISTS contact;
CREATE TABLE contact (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    message TEXT
);
INSERT INTO contact (name, email, message) VALUES 
('John Doe', 'john.doe@example.com', 'Hello, this is message 1.'),
('Jane Smith', 'jane.smith@example.com', 'Message 2 from Jane.'),
('Bob Johnson', 'bob.johnson@example.com', 'This is the third message.'),
('Alice Brown', 'alice.brown@example.com', 'Message number 4.'),
('Charlie Wilson', 'charlie.wilson@example.com', 'The fifth and final message.');

DROP TABLE IF EXISTS cart;
CREATE TABLE cart (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT,
    productID BIGINT,
    quantity INT
);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    userID BIGINT,
    fullname VARCHAR(255),
    phone VARCHAR(20),
    address TEXT,
    total BIGINT,
    status INT,
    note TEXT,
    message TEXT
);

DROP TABLE IF EXISTS `orderDetail`;
CREATE TABLE orderDetail (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    orderID BIGINT,
    productID BIGINT,
    price BIGINT,
    quantity INT
);