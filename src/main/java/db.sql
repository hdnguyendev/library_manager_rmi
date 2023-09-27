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
    ('To Kill a Mockingbird', 'Harper Lee', TRUE),
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