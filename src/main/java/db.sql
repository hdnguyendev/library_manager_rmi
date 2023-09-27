-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- Tạo bảng User
CREATE TABLE IF NOT EXISTS User (
                                    Id INT PRIMARY KEY AUTO_INCREMENT,
                                    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Fullname VARCHAR(100) NOT NULL,
    LastAccessedServer VARCHAR(50)
    );
-- Thêm cột LastAccessedDateTime vào bảng User
ALTER TABLE User
    ADD COLUMN LastAccessedDateTime TIMESTAMP;
-- Tạo bảng Book
CREATE TABLE IF NOT EXISTS Book (
                                    Id INT PRIMARY KEY AUTO_INCREMENT,
                                    Title VARCHAR(100) NOT NULL,
    Author VARCHAR(100) NOT NULL,
    Available BOOLEAN DEFAULT TRUE
    );

-- Tạo bảng BorrowedBook
CREATE TABLE IF NOT EXISTS BorrowedBook (
                                            Id INT PRIMARY KEY AUTO_INCREMENT,
                                            UserId INT NOT NULL,
                                            BookId INT NOT NULL,
                                            BorrowDate DATE NOT NULL,
                                            ReturnDate DATE,
                                            FOREIGN KEY (UserId) REFERENCES User(Id),
    FOREIGN KEY (BookId) REFERENCES Book(Id)
    );
-- Tạo bảng Role
CREATE TABLE IF NOT EXISTS Role (
                                    Id INT PRIMARY KEY AUTO_INCREMENT,
                                    Name VARCHAR(50) NOT NULL
);

-- Tạo bảng User_Role
CREATE TABLE IF NOT EXISTS User_Role (
                                         UserId INT,
                                         RoleId INT,
                                         PRIMARY KEY (UserId, RoleId),
                                         FOREIGN KEY (UserId) REFERENCES User(Id) ON DELETE CASCADE,
                                         FOREIGN KEY (RoleId) REFERENCES Role(Id) ON DELETE CASCADE
);


-- Thêm dữ liệu mẫu vào bảng User
INSERT INTO User (Username, Password, Fullname, LastAccessedServer)
VALUES
    ('user1', 'password1', 'User 1', 'Server1'),
    ('user2', 'password2', 'User 2', 'Server2'),
    ('user3', 'password3', 'User 3', 'Server3');

-- Thêm dữ liệu mẫu vào bảng Book
INSERT INTO Book (Title, Author, Available)
VALUES
    ('Book 1', 'Author 1', TRUE),
    ('Book 2', 'Author 2', TRUE),
    ('Book 3', 'Author 3', TRUE);

-- Thêm dữ liệu mẫu vào bảng BorrowedBook
INSERT INTO BorrowedBook (UserId, BookId, BorrowDate, ReturnDate)
VALUES
    (1, 1, '2023-09-01', '2023-09-10'),
    (2, 2, '2023-09-05', NULL),
    (3, 3, '2023-09-10', NULL);

-- Thêm dữ liệu mẫu vào bảng Role
INSERT INTO Role (Name)
VALUES
    ('Admin'),
    ('User');

-- Gán vai trò cho người dùng mẫu
INSERT INTO User_Role (UserId, RoleId)
VALUES
    (1, 1), -- Người dùng 1 có vai trò Admin
    (2, 2), -- Người dùng 2 có vai trò User
    (3, 2); -- Người dùng 3 có vai trò User