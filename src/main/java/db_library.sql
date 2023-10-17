CREATE DATABASE  IF NOT EXISTS `rmi_manager_lib` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rmi_manager_lib`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: rmi_manager_lib
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Author 1'),(2,'Author 2'),(3,'Author 3'),(4,'Author 4'),(5,'Author 5'),(6,'Author 6'),(7,'Author 7'),(8,'Author 8'),(9,'Author 9'),(10,'Author 10');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_category_idx` (`category_id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Book 1',1),(2,'Book 2',2),(3,'Book 3',1),(4,'Book 4',3),(5,'Book 5',2),(6,'Book 6',1),(7,'Book 7',4),(8,'Book 8',3),(9,'Book 9',2),(10,'Book 10',4);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `book_id` int NOT NULL,
  `author_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FK_author_idx` (`author_id`),
  CONSTRAINT `FK_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FK_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (1,1),(3,1),(6,1),(2,2),(5,2),(9,2),(4,3),(8,3),(7,4),(10,4);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_copy`
--

DROP TABLE IF EXISTS `book_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_copy`
--

LOCK TABLES `book_copy` WRITE;
/*!40000 ALTER TABLE `book_copy` DISABLE KEYS */;
INSERT INTO `book_copy` VALUES (1,2000,1,1),(2,2001,2,2),(3,2002,3,1),(4,2003,4,3),(5,2004,5,2),(6,2005,6,1),(7,2006,7,4),(8,2007,8,3),(9,2008,9,2),(10,2009,10,4);
/*!40000 ALTER TABLE `book_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Category 1'),(2,'Category 2'),(3,'Category 3'),(4,'Category 4'),(5,'Category 5'),(6,'Category 6'),(7,'Category 7'),(8,'Category 8'),(9,'Category 9'),(10,'Category 10');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout`
--

LOCK TABLES `checkout` WRITE;
/*!40000 ALTER TABLE `checkout` DISABLE KEYS */;
INSERT INTO `checkout` VALUES (1,'2023-10-15 07:00:00','2023-10-16 07:00:00',1,1,1),(2,'2023-10-15 07:30:00','2023-10-16 07:30:00',1,2,2),(3,'2023-10-15 08:00:00','2023-10-16 08:00:00',1,3,3),(4,'2023-10-15 08:30:00','2023-10-16 08:30:00',1,4,4),(5,'2023-10-15 09:00:00','2023-10-16 09:00:00',0,5,5),(6,'2023-10-15 09:30:00','2023-10-16 09:30:00',0,6,6),(7,'2023-10-15 10:00:00','2023-10-16 10:00:00',1,7,7),(8,'2023-10-15 10:30:00','2023-10-16 10:30:00',1,8,8),(9,'2023-10-15 11:00:00','2023-10-16 11:00:00',0,9,9),(10,'2023-10-15 11:30:00','2023-10-16 11:30:00',0,10,10);
/*!40000 ALTER TABLE `checkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hold`
--

DROP TABLE IF EXISTS `hold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hold`
--

LOCK TABLES `hold` WRITE;
/*!40000 ALTER TABLE `hold` DISABLE KEYS */;
INSERT INTO `hold` VALUES (16,'2023-10-15 07:00:00','2023-10-16 07:00:00',1,1),(17,'2023-10-15 07:30:00','2023-10-16 07:30:00',2,2),(18,'2023-10-15 08:00:00','2023-10-16 08:00:00',3,3),(19,'2023-10-15 08:30:00','2023-10-16 08:30:00',4,4),(20,'2023-10-15 09:00:00','2023-10-16 09:00:00',5,5),(21,'2023-10-15 09:30:00','2023-10-16 09:30:00',6,6),(22,'2023-10-15 10:00:00','2023-10-16 10:00:00',7,7),(23,'2023-10-15 10:30:00','2023-10-16 10:30:00',8,8),(24,'2023-10-15 11:00:00','2023-10-16 11:00:00',9,9),(25,'2023-10-15 11:30:00','2023-10-16 11:30:00',10,10);
/*!40000 ALTER TABLE `hold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sent_at` timestamp NOT NULL,
  `message` text NOT NULL,
  `patron_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_notification_patron_idx` (`patron_id`),
  CONSTRAINT `FK_notification_patron` FOREIGN KEY (`patron_id`) REFERENCES `patron_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'2023-10-15 07:00:00','Notification 1',1),(2,'2023-10-15 07:30:00','Notification 2',2),(3,'2023-10-15 08:00:00','Notification 3',3),(4,'2023-10-15 08:30:00','Notification 4',4),(5,'2023-10-15 09:00:00','Notification 5',5),(6,'2023-10-15 09:30:00','Notification 6',6),(7,'2023-10-15 10:00:00','Notification 7',7),(8,'2023-10-15 10:30:00','Notification 8',8),(9,'2023-10-15 11:00:00','Notification 9',9),(10,'2023-10-15 11:30:00','Notification 10',10);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patron_account`
--

DROP TABLE IF EXISTS `patron_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patron_account`
--

LOCK TABLES `patron_account` WRITE;
/*!40000 ALTER TABLE `patron_account` DISABLE KEYS */;
INSERT INTO `patron_account` VALUES (1,'First 1','Last 1','1@gmail.com','1',1),(2,'First 2','Last 2','2@gmail.com','1',1),(3,'First 3','Last 3','3@gmail.com','1',1),(4,'First 4','Last 4','4@gmail.com','1',1),(5,'First 5','Last 5','5@gmail.com','1',1),(6,'First 6','Last 6','6@gmail.com','1',1),(7,'First 7','Last 7','7@gmail.com','1',1),(8,'First 8','Last 8','8@gmail.com','1',1),(9,'First 9','Last 9','9@gmail.com','1',1),(10,'First 10','Last 10','10@gmail.com','1',1);
/*!40000 ALTER TABLE `patron_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `published`
--

DROP TABLE IF EXISTS `published`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `published` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `published`
--

LOCK TABLES `published` WRITE;
/*!40000 ALTER TABLE `published` DISABLE KEYS */;
INSERT INTO `published` VALUES (1,'Publisher 1'),(2,'Publisher 2'),(3,'Publisher 3'),(4,'Publisher 4'),(5,'Publisher 5'),(6,'Publisher 6'),(7,'Publisher 7'),(8,'Publisher 8'),(9,'Publisher 9'),(10,'Publisher 10');
/*!40000 ALTER TABLE `published` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-17 18:11:43
