CREATE DATABASE  IF NOT EXISTS `rmi_manager_lib` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rmi_manager_lib`;
--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_category_idx` (`category_id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
CREATE TABLE `book_author` (
  `book_id` int NOT NULL,
  `author_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FK_author_idx` (`author_id`),
  CONSTRAINT `FK_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FK_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `book_copy`
--

DROP TABLE IF EXISTS `book_copy`;
CREATE TABLE `book_copy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year_published` int NOT NULL,
  `book_id` int NOT NULL,
  `published_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_published_book_copy_idx` (`published_id`),
  KEY `FK_book_book_copy` (`book_id`),
  CONSTRAINT `FK_book_book_copy` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_published_book_copy` FOREIGN KEY (`published_id`) REFERENCES `published` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `is_returned` tinyint NOT NULL,
  `patron_id` int NOT NULL,
  `book_copy_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_checkout_patron_idx` (`patron_id`),
  KEY `FK_checkout_book_copy_idx` (`book_copy_id`),
  CONSTRAINT `FK_checkout_book_copy` FOREIGN KEY (`book_copy_id`) REFERENCES `book_copy` (`id`),
  CONSTRAINT `FK_checkout_patron` FOREIGN KEY (`patron_id`) REFERENCES `patron_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `hold`
--

DROP TABLE IF EXISTS `hold`;
CREATE TABLE `hold` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `patron_id` int NOT NULL,
  `book_copy_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hold_book_copy_idx` (`book_copy_id`),
  KEY `FK_hold_patron_idx` (`patron_id`),
  CONSTRAINT `FK_hold_book_copy` FOREIGN KEY (`book_copy_id`) REFERENCES `book_copy` (`id`),
  CONSTRAINT `FK_hold_patron` FOREIGN KEY (`patron_id`) REFERENCES `patron_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sent_at` timestamp NOT NULL,
  `message` text NOT NULL,
  `patron_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_notification_patron_idx` (`patron_id`),
  CONSTRAINT `FK_notification_patron` FOREIGN KEY (`patron_id`) REFERENCES `patron_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `patron_account`
--

DROP TABLE IF EXISTS `patron_account`;
CREATE TABLE `patron_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `published`
--

DROP TABLE IF EXISTS `published`;
CREATE TABLE `published` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
