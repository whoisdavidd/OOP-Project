-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: oopdatabase
-- ------------------------------------------------------
-- Server version	8.0.31

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

DROP SCHEMA IF EXISTS `oopDatabase`;
USE `oopDatabase`;
--
-- Table structure for table `concert_performers`
--

DROP TABLE IF EXISTS `concert_performers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `concert_performers` (
  `concert_eventid` bigint NOT NULL,
  `performers` varchar(255) DEFAULT NULL,
  KEY `FKfihdmod2sdfr2jfn4eomtj21b` (`concert_eventid`),
  CONSTRAINT `FKfihdmod2sdfr2jfn4eomtj21b` FOREIGN KEY (`concert_eventid`) REFERENCES `event` (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concert_performers`
--

LOCK TABLES `concert_performers` WRITE;
/*!40000 ALTER TABLE `concert_performers` DISABLE KEYS */;
INSERT INTO `concert_performers` VALUES (2,'One Direction'),(1,'Taylor Swift'),(1,'Sabrina Carpenter');
/*!40000 ALTER TABLE `concert_performers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `cancellation_fee` double DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `eventid` bigint NOT NULL AUTO_INCREMENT,
  `event_type` varchar(31) NOT NULL,
  `event_date` varchar(255) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `event_time` varchar(255) NOT NULL,
  `event_venue` varchar(255) NOT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `sport` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`eventid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (100,NULL,1,'CONCERT','18052024','Taylor Swift Eras Tour','1900','National Stadium',NULL,NULL),(50,NULL,2,'CONCERT','08062024','Spring Music Festival','1930','Central Park',NULL,NULL),(0,NULL,3,'SPORTS_EVENT','07102024','Championship Soccer Match','1925','National Stadium',NULL,'Football'),(25,125,4,'MOVIE','13112024','The Great Gatsby','2026','Downtown Theater','PG13',NULL),(0,100,5,'MOVIE','31052024','Tech Innovations 2024','0930','Tech Hub Conference Center','PG',NULL);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_main_cast`
--

DROP TABLE IF EXISTS `movie_main_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_main_cast` (
  `movie_eventid` bigint NOT NULL,
  `main_cast` varchar(255) DEFAULT NULL,
  KEY `FK3s5qa9o3ufmov0p6khcqxmvyp` (`movie_eventid`),
  CONSTRAINT `FK3s5qa9o3ufmov0p6khcqxmvyp` FOREIGN KEY (`movie_eventid`) REFERENCES `event` (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_main_cast`
--

LOCK TABLES `movie_main_cast` WRITE;
/*!40000 ALTER TABLE `movie_main_cast` DISABLE KEYS */;
INSERT INTO `movie_main_cast` VALUES (4,'Leonardo DiCaprio'),(4,'Tobey Maguire'),(4,'Carey Mulligan'),(5,'Bill Gates');
/*!40000 ALTER TABLE `movie_main_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_record`
--

DROP TABLE IF EXISTS `payment_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_record` (
  `amount` decimal(38,2) DEFAULT NULL,
  `num_tickets` int NOT NULL,
  `id` bigint NOT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `charge_id` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `eventid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_record`
--

LOCK TABLES `payment_record` WRITE;
/*!40000 ALTER TABLE `payment_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_record_seq`
--

DROP TABLE IF EXISTS `payment_record_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_record_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_record_seq`
--

LOCK TABLES `payment_record_seq` WRITE;
/*!40000 ALTER TABLE `payment_record_seq` DISABLE KEYS */;
INSERT INTO `payment_record_seq` VALUES (1);
/*!40000 ALTER TABLE `payment_record_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports_event_participants`
--

DROP TABLE IF EXISTS `sports_event_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports_event_participants` (
  `sports_event_eventid` bigint NOT NULL,
  `participants` varchar(255) DEFAULT NULL,
  KEY `FKa1glrx52er2kkgloic964pwx3` (`sports_event_eventid`),
  CONSTRAINT `FKa1glrx52er2kkgloic964pwx3` FOREIGN KEY (`sports_event_eventid`) REFERENCES `event` (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_event_participants`
--

LOCK TABLES `sports_event_participants` WRITE;
/*!40000 ALTER TABLE `sports_event_participants` DISABLE KEYS */;
INSERT INTO `sports_event_participants` VALUES (3,'Liverpool'),(3,'Manchester United');
/*!40000 ALTER TABLE `sports_event_participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `is_attended` bit(1) NOT NULL,
  `price` double NOT NULL,
  `ticketid` int NOT NULL AUTO_INCREMENT,
  `booking_date` datetime(6) DEFAULT NULL,
  `eventid` bigint DEFAULT NULL,
  `ticketing_option_id` bigint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticketid`),
  KEY `FKdew7t6fhjgr07291e85yidb6m` (`username`),
  KEY `FK1jrdngrr3c461aaawrlhvqe7i` (`eventid`),
  KEY `FKfv0qcw0uita6h391e65w9kidw` (`ticketing_option_id`),
  CONSTRAINT `FK1jrdngrr3c461aaawrlhvqe7i` FOREIGN KEY (`eventid`) REFERENCES `event` (`eventid`),
  CONSTRAINT `FKdew7t6fhjgr07291e85yidb6m` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKfv0qcw0uita6h391e65w9kidw` FOREIGN KEY (`ticketing_option_id`) REFERENCES `ticketing_option` (`ticketing_optionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticketing_option`
--

DROP TABLE IF EXISTS `ticketing_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticketing_option` (
  `num_tickets_sold` int DEFAULT NULL,
  `tier_price` double NOT NULL,
  `tier_quantity` int NOT NULL,
  `tier_revenue` double DEFAULT NULL,
  `eventid` bigint DEFAULT NULL,
  `ticketing_optionid` bigint NOT NULL AUTO_INCREMENT,
  `tier_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticketing_optionid`),
  KEY `FK2g0g3rfqkv6rsoyb33m522kpl` (`eventid`),
  CONSTRAINT `FK2g0g3rfqkv6rsoyb33m522kpl` FOREIGN KEY (`eventid`) REFERENCES `event` (`eventid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticketing_option`
--

LOCK TABLES `ticketing_option` WRITE;
/*!40000 ALTER TABLE `ticketing_option` DISABLE KEYS */;
INSERT INTO `ticketing_option` VALUES (0,150,100,0,1,1,'CAT A'),(0,100,50,0,1,2,'CAT 2'),(0,150,50,0,2,3,'Standing Pen'),(0,200,100,0,2,4,'Sitting Pen'),(0,100,200,0,3,5,'General Admission'),(0,100,80,0,4,6,'General Admission'),(0,50,500,0,5,7,'Normal');
/*!40000 ALTER TABLE `ticketing_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `account_balance` float DEFAULT NULL,
  `user_type` varchar(31) NOT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (NULL,'EVENT_MANAGER',NULL,'Test123','eventmanager'),(1000,'CUSTOMER','bbvarshan@gmail.com','Test123','test'),(NULL,'TICKETING_OFFICER',NULL,'Test123','ticketingofficer');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-10 19:37:51
