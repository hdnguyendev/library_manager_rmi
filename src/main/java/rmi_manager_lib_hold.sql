CREATE DATABASE  IF NOT EXISTS `rmi_manager_lib` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
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
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hold`
--

DROP TABLE IF EXISTS hold;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE hold (
  id int NOT NULL AUTO_INCREMENT,
  start_time timestamp NOT NULL,
  end_time timestamp NOT NULL,
  patron_id int NOT NULL,
  book_copy_id int NOT NULL,
  PRIMARY KEY (id),
  KEY FK_hold_book_copy_idx (book_copy_id),
  KEY FK_hold_patron_idx (patron_id),
  CONSTRAINT FK_hold_book_copy FOREIGN KEY (book_copy_id) REFERENCES book_copy (id),
  CONSTRAINT FK_hold_patron FOREIGN KEY (patron_id) REFERENCES patron_account (id)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hold`
--

INSERT INTO hold VALUES (16,'2023-10-15 14:00:00','2023-10-16 14:00:00',1,1);
INSERT INTO hold VALUES (17,'2023-10-15 14:30:00','2023-10-16 14:30:00',2,2);
INSERT INTO hold VALUES (18,'2023-10-15 15:00:00','2023-10-16 15:00:00',3,3);
INSERT INTO hold VALUES (19,'2023-10-15 15:30:00','2023-10-16 15:30:00',4,4);
INSERT INTO hold VALUES (20,'2023-10-15 16:00:00','2023-10-16 16:00:00',5,5);
INSERT INTO hold VALUES (21,'2023-10-15 16:30:00','2023-10-16 16:30:00',6,6);
INSERT INTO hold VALUES (22,'2023-10-15 17:00:00','2023-10-16 17:00:00',7,7);
INSERT INTO hold VALUES (23,'2023-10-15 17:30:00','2023-10-16 17:30:00',8,8);
INSERT INTO hold VALUES (24,'2023-10-15 18:00:00','2023-10-16 18:00:00',9,9);
INSERT INTO hold VALUES (25,'2023-10-15 18:30:00','2023-10-16 18:30:00',10,10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed
