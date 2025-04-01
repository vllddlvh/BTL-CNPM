-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinemaweb
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `Booking`
--

DROP TABLE IF EXISTS `Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Booking` (
  `booking_id` int NOT NULL,
  `user_id` int NOT NULL,
  `schedule_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `price` double NOT NULL,
  `booking_day` date NOT NULL,
  `fd_id` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `fk_Booking_User1_idx` (`user_id`),
  KEY `fk_Booking_Schedule1_idx` (`schedule_id`),
  KEY `fk_Booking_Seat1_idx` (`seat_id`),
  KEY `fk_Booking_FoodAndDrink1_idx` (`fd_id`),
  CONSTRAINT `fk_Booking_FoodAndDrink1` FOREIGN KEY (`fd_id`) REFERENCES `FoodAndDrink` (`fd_id`),
  CONSTRAINT `fk_Booking_Schedule1` FOREIGN KEY (`schedule_id`) REFERENCES `Schedule` (`schedule_id`),
  CONSTRAINT `fk_Booking_Seat1` FOREIGN KEY (`seat_id`) REFERENCES `Seat` (`seat_id`),
  CONSTRAINT `fk_Booking_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Booking`
--

LOCK TABLES `Booking` WRITE;
/*!40000 ALTER TABLE `Booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `Booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cinema`
--

DROP TABLE IF EXISTS `Cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cinema` (
  `cinema_id` int NOT NULL,
  `cinema_name` text NOT NULL,
  `cinema_address` text NOT NULL,
  PRIMARY KEY (`cinema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cinema`
--

LOCK TABLES `Cinema` WRITE;
/*!40000 ALTER TABLE `Cinema` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Film`
--

DROP TABLE IF EXISTS `Film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Film` (
  `film_id` int NOT NULL,
  `film_name` text NOT NULL,
  `film_poster` text NOT NULL,
  `film_genre` text NOT NULL,
  `film_length` time NOT NULL,
  `film_description` text NOT NULL,
  `film_review` int DEFAULT NULL,
  PRIMARY KEY (`film_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Film`
--

LOCK TABLES `Film` WRITE;
/*!40000 ALTER TABLE `Film` DISABLE KEYS */;
/*!40000 ALTER TABLE `Film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FoodAndDrink`
--

DROP TABLE IF EXISTS `FoodAndDrink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FoodAndDrink` (
  `fd_id` int NOT NULL,
  `fd_name` text NOT NULL,
  `room_id` int NOT NULL,
  `fd_price` double NOT NULL,
  PRIMARY KEY (`fd_id`),
  KEY `fk_FoodAndDrink_Room1_idx` (`room_id`),
  CONSTRAINT `fk_FoodAndDrink_Room1` FOREIGN KEY (`room_id`) REFERENCES `Room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FoodAndDrink`
--

LOCK TABLES `FoodAndDrink` WRITE;
/*!40000 ALTER TABLE `FoodAndDrink` DISABLE KEYS */;
/*!40000 ALTER TABLE `FoodAndDrink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role` (
  `role_id` int NOT NULL,
  `role` text NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Room` (
  `room_id` int NOT NULL,
  `cinema_id` int NOT NULL,
  `room_name` text NOT NULL,
  PRIMARY KEY (`room_id`),
  KEY `fk_Room_Cinema1_idx` (`cinema_id`),
  CONSTRAINT `fk_Room_Cinema1` FOREIGN KEY (`cinema_id`) REFERENCES `Cinema` (`cinema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Schedule`
--

DROP TABLE IF EXISTS `Schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Schedule` (
  `schedule_id` int NOT NULL,
  `film_id` int NOT NULL,
  `room_id` int NOT NULL,
  `schedule_date` date NOT NULL,
  `schedule_start` time NOT NULL,
  `schedule_end` time NOT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `fk_Schedule_Room1_idx` (`room_id`),
  KEY `fk_Schedule_Film1_idx` (`film_id`),
  CONSTRAINT `fk_Schedule_Film1` FOREIGN KEY (`film_id`) REFERENCES `Film` (`film_id`),
  CONSTRAINT `fk_Schedule_Room1` FOREIGN KEY (`room_id`) REFERENCES `Room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Schedule`
--

LOCK TABLES `Schedule` WRITE;
/*!40000 ALTER TABLE `Schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `Schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seat`
--

DROP TABLE IF EXISTS `Seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Seat` (
  `seat_id` int NOT NULL,
  `seat_type` text NOT NULL,
  `room_id` int NOT NULL,
  `row` text NOT NULL,
  `number` int NOT NULL,
  `seat_price` double DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `fk_Seat_Room1_idx` (`room_id`),
  CONSTRAINT `fk_Seat_Room1` FOREIGN KEY (`room_id`) REFERENCES `Room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seat`
--

LOCK TABLES `Seat` WRITE;
/*!40000 ALTER TABLE `Seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `Seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `user_id` int NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `avatar` text,
  `fullname` text NOT NULL,
  `birthday` date NOT NULL,
  `gender` int NOT NULL,
  `email` text NOT NULL,
  `phone_number` text NOT NULL,
  `point` double NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_role`
--

DROP TABLE IF EXISTS `User_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  KEY `fk_User_role_Role1_idx` (`role_id`),
  KEY `fk_User_role_User1_idx` (`user_id`),
  CONSTRAINT `fk_User_role_Role1` FOREIGN KEY (`role_id`) REFERENCES `Role` (`role_id`),
  CONSTRAINT `fk_User_role_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_role`
--

LOCK TABLES `User_role` WRITE;
/*!40000 ALTER TABLE `User_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-01 18:46:49
