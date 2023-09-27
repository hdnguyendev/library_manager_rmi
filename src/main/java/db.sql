-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- Tạo bảng User
CREATE TABLE IF NOT EXISTS User
(
    id                   INT PRIMARY KEY AUTO_INCREMENT,
    username             VARCHAR(50)  NOT NULL,
    password             VARCHAR(50)  NOT NULL,
    fullname             VARCHAR(100) NOT NULL,
    lastAccessedServer   VARCHAR(50),
    lastAccessedDateTime TIMESTAMP,
    isLogin              BOOLEAN
);
-- Tạo bảng Book
CREATE TABLE IF NOT EXISTS Book
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(100) NOT NULL,
    author      VARCHAR(100) NOT NULL,
    description      VARCHAR(100) DEFAULT NULL,
    isAvailable BOOLEAN DEFAULT TRUE
);

-- Tạo bảng BorrowedBook
CREATE TABLE IF NOT EXISTS BorrowedBook
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    userId     INT  NOT NULL,
    bookId     INT  NOT NULL,
    borrowDate DATE NOT NULL,
    returnDate DATE,
    FOREIGN KEY (userId) REFERENCES User (id),
    FOREIGN KEY (bookId) REFERENCES Book (id)
);
-- Tạo bảng Role
CREATE TABLE IF NOT EXISTS Role
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- Tạo bảng User_Role
CREATE TABLE IF NOT EXISTS User_Role
(
    userId INT,
    roleId INT,
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) REFERENCES User (id) ON DELETE CASCADE,
    FOREIGN KEY (roleId) REFERENCES Role (id) ON DELETE CASCADE
);


-- Thêm dữ liệu mẫu vào bảng User
INSERT INTO User (username, password, fullname, lastAccessedServer)
VALUES ('21IT363', '1', 'Hồ Đăng Nguyện', 'Server1'),
       ('21IT371', '1', 'Nguyễn Anh Quân', 'Server2'),
       ('21IT000', '1', 'VKU', 'Server3');

-- Thêm dữ liệu mẫu vào bảng Book
INSERT INTO Book (title, author, isAvailable)
VALUES ('To Kill a Mockingbird', 'Harper Lee', TRUE),
       ('1984', 'George Orwell', TRUE),
       ('Pride and Prejudice', 'Jane Austen', TRUE),
       ('The Great Gatsby', 'F. Scott Fitzgerald', TRUE),
       ('Moby-Dick', 'Herman Melville', TRUE),
       ('The Catcher in the Rye', 'J.D. Salinger', TRUE),
       ('Brave New World', 'Aldous Huxley', TRUE),
       ('The Lord of the Rings', 'J.R.R. Tolkien', TRUE),
       ('The Chronicles of Narnia', 'C.S. Lewis', TRUE),
       ('Harry Potter and the Philosopher\'s Stone', 'J.K. Rowling', TRUE),
       ('To the Lighthouse', 'Virginia Woolf', TRUE),
       ('The Odyssey', 'Homer', TRUE),
       ('Crime and Punishment', 'Fyodor Dostoevsky', TRUE),
       ('One Hundred Years of Solitude', 'Gabriel Garcia Marquez', TRUE),
       ('The Hobbit', 'J.R.R. Tolkien', TRUE),
       ('Don Quixote', 'Miguel de Cervantes', TRUE),
       ('War and Peace', 'Leo Tolstoy', TRUE),
       ('The Divine Comedy', 'Dante Alighieri', TRUE),
       ('Alice\'s Adventures in Wonderland', 'Lewis Carroll', TRUE),
       ('The Adventures of Huckleberry Finn', 'Mark Twain', TRUE);

-- Thêm dữ liệu mẫu vào bảng BorrowedBook
INSERT INTO BorrowedBook (userId, bookId, borrowDate, returnDate)
VALUES (1, 1, '2023-09-01', '2023-09-10'),
       (2, 2, '2023-09-05', NULL),
       (3, 3, '2023-09-10', NULL);

-- Thêm dữ liệu mẫu vào bảng Role
INSERT INTO Role (name)
VALUES ('Admin'),
       ('User');

-- Gán vai trò cho người dùng mẫu
INSERT INTO User_Role (userId, roleId)
VALUES (1, 1), -- Người dùng 1 có vai trò Admin
       (2, 2), -- Người dùng 2 có vai trò User
       (3, 2); -- Người dùng 3 có vai trò User