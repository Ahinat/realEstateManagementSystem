CREATE DATABASE  IF NOT EXISTS `realestatedatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `realestatedatabase`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: realestatedatabase
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment` (
  `balcony_policy` bit(1) DEFAULT NULL,
  `bathrooms` int DEFAULT NULL,
  `bedrooms` int DEFAULT NULL,
  `floor` int DEFAULT NULL,
  `furniture_policy` bit(1) DEFAULT NULL,
  `maintenance_services` varchar(255) DEFAULT NULL,
  `parking_policy` bit(1) DEFAULT NULL,
  `pet_policy` bit(1) DEFAULT NULL,
  `terrace_policy` bit(1) DEFAULT NULL,
  `total_floor` int DEFAULT NULL,
  `property_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `FKcihl617952x0rqgt0j8b6wee2` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (_binary '',3,3,3,_binary '','Cleaning, Garbage Disposal, Watchmen',_binary '',_binary '',_binary '',13,3);
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_place`
--

DROP TABLE IF EXISTS `business_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_place` (
  `insurance_details` varchar(255) DEFAULT NULL,
  `lighting_setup` varchar(255) DEFAULT NULL,
  `ventilation` varchar(255) DEFAULT NULL,
  `visibility_from_road` varchar(255) DEFAULT NULL,
  `waiting_area` varchar(255) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `FKm6c7aaq57y2c2u968c602elao` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_place`
--

LOCK TABLES `business_place` WRITE;
/*!40000 ALTER TABLE `business_place` DISABLE KEYS */;
/*!40000 ALTER TABLE `business_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer_preferred_locations`
--

DROP TABLE IF EXISTS `buyer_preferred_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer_preferred_locations` (
  `buyer_id` bigint NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  KEY `FK8sold7bjijaf6m2ddkssvmv8l` (`buyer_id`),
  CONSTRAINT `FK8sold7bjijaf6m2ddkssvmv8l` FOREIGN KEY (`buyer_id`) REFERENCES `buyer_profile` (`buyer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_preferred_locations`
--

LOCK TABLES `buyer_preferred_locations` WRITE;
/*!40000 ALTER TABLE `buyer_preferred_locations` DISABLE KEYS */;
INSERT INTO `buyer_preferred_locations` VALUES (1,'Halishahar'),(1,'GEC'),(1,'Agrabad'),(1,'Dewanhat'),(2,'Halishahar'),(2,'Panchlaish');
/*!40000 ALTER TABLE `buyer_preferred_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer_preferred_property_types`
--

DROP TABLE IF EXISTS `buyer_preferred_property_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer_preferred_property_types` (
  `buyer_id` bigint NOT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  KEY `FK84djpvbv46ybv555udwjcuvor` (`buyer_id`),
  CONSTRAINT `FK84djpvbv46ybv555udwjcuvor` FOREIGN KEY (`buyer_id`) REFERENCES `buyer_profile` (`buyer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_preferred_property_types`
--

LOCK TABLES `buyer_preferred_property_types` WRITE;
/*!40000 ALTER TABLE `buyer_preferred_property_types` DISABLE KEYS */;
INSERT INTO `buyer_preferred_property_types` VALUES (1,'Apartment'),(1,'Houses'),(1,'Residential Units'),(2,'Apartment'),(2,'Houses');
/*!40000 ALTER TABLE `buyer_preferred_property_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer_profile`
--

DROP TABLE IF EXISTS `buyer_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer_profile` (
  `buyer_id` bigint NOT NULL AUTO_INCREMENT,
  `max_budget` decimal(38,2) DEFAULT NULL,
  `min_budget` decimal(38,2) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`buyer_id`),
  UNIQUE KEY `UK22nbnd4dl9ykpirnps6cwygan` (`user_id`),
  CONSTRAINT `FKp9uom6bjsg9bftf2ilgtuhyev` FOREIGN KEY (`user_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_profile`
--

LOCK TABLES `buyer_profile` WRITE;
/*!40000 ALTER TABLE `buyer_profile` DISABLE KEYS */;
INSERT INTO `buyer_profile` VALUES (1,9500000000.00,5000.00,1),(2,80000.00,6000.00,2);
/*!40000 ALTER TABLE `buyer_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `message_content` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `property_id` bigint DEFAULT NULL,
  `receiver_user_id` bigint DEFAULT NULL,
  `sender_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK3oxb6med7t3kbqfdpwhdjv1ul` (`property_id`),
  KEY `FK9eoi2fkycyw8u0i4piektdehy` (`receiver_user_id`),
  KEY `FKmhrydcw9petuayonedwiw3ah2` (`sender_user_id`),
  CONSTRAINT `FK3oxb6med7t3kbqfdpwhdjv1ul` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  CONSTRAINT `FK9eoi2fkycyw8u0i4piektdehy` FOREIGN KEY (`receiver_user_id`) REFERENCES `your_property_user` (`user_id`),
  CONSTRAINT `FKmhrydcw9petuayonedwiw3ah2` FOREIGN KEY (`sender_user_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,'Hello hasib','2025-01-07 02:10:51.532289',2,1,2),(2,'How are you?','2025-01-07 02:10:58.609061',2,1,2),(3,'I am fine ariat, what about you?','2025-01-07 02:11:37.075612',2,2,1);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commercial_unit`
--

DROP TABLE IF EXISTS `commercial_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commercial_unit` (
  `air_condition` varchar(255) DEFAULT NULL,
  `facade` varchar(255) DEFAULT NULL,
  `floor_layout` varchar(255) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `FKln4fby3jtswwj89rat7bmjnb8` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commercial_unit`
--

LOCK TABLES `commercial_unit` WRITE;
/*!40000 ALTER TABLE `commercial_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `commercial_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `balcony_availability` bit(1) DEFAULT NULL,
  `basement_availability` varchar(255) DEFAULT NULL,
  `bathrooms` int DEFAULT NULL,
  `bedrooms` int DEFAULT NULL,
  `floor` int DEFAULT NULL,
  `garage_availability` bit(1) DEFAULT NULL,
  `garden_availability` bit(1) DEFAULT NULL,
  `roof_type` varchar(255) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `FKbxy0s8qtgwrljfu9mv56gxy1i` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (_binary '\0','Unfinished',6,5,2,_binary '\0',_binary '\0','Sloped',2);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `land`
--

DROP TABLE IF EXISTS `land`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `land` (
  `area` decimal(38,2) DEFAULT NULL,
  `border` varchar(255) DEFAULT NULL,
  `length` decimal(38,2) DEFAULT NULL,
  `previous_development` bit(1) NOT NULL,
  `road_access` varchar(255) DEFAULT NULL,
  `topography` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `width` decimal(38,2) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `FK9kwqpet7e2styrcegv3mld7tg` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `land`
--

LOCK TABLES `land` WRITE;
/*!40000 ALTER TABLE `land` DISABLE KEYS */;
INSERT INTO `land` VALUES (50000.00,'Boundary Markers',NULL,_binary '','Paved','Sloped','Agricultural',NULL,4);
/*!40000 ALTER TABLE `land` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `negotiation`
--

DROP TABLE IF EXISTS `negotiation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `negotiation` (
  `negotiation_id` bigint NOT NULL AUTO_INCREMENT,
  `negotiated_rent_price` decimal(38,2) DEFAULT NULL,
  `negotiated_rent_term` varchar(255) DEFAULT NULL,
  `negotiated_sale_price` decimal(38,2) DEFAULT NULL,
  `offered_at` datetime(6) NOT NULL,
  `status` enum('ACCEPTED','CANCELLED','OFFERED') NOT NULL,
  `negotiator_id` bigint NOT NULL,
  `property_id` bigint NOT NULL,
  `recipient_id` bigint NOT NULL,
  PRIMARY KEY (`negotiation_id`),
  KEY `FKo30t0b7eu65x2mafwwqatrgfu` (`negotiator_id`),
  KEY `FK4xgtwwxu8wp1049g1ynnql8j2` (`property_id`),
  KEY `FKl41rywa8faeqt5snr2hgah4x1` (`recipient_id`),
  CONSTRAINT `FK4xgtwwxu8wp1049g1ynnql8j2` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  CONSTRAINT `FKl41rywa8faeqt5snr2hgah4x1` FOREIGN KEY (`recipient_id`) REFERENCES `your_property_user` (`user_id`),
  CONSTRAINT `FKo30t0b7eu65x2mafwwqatrgfu` FOREIGN KEY (`negotiator_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `negotiation`
--

LOCK TABLES `negotiation` WRITE;
/*!40000 ALTER TABLE `negotiation` DISABLE KEYS */;
INSERT INTO `negotiation` VALUES (1,500000.00,'Yearly',NULL,'2025-01-03 18:12:10.895593','ACCEPTED',2,2,1),(2,48000.00,'Monthly',NULL,'2025-01-03 18:13:16.903080','OFFERED',1,2,2),(3,550000.00,'Yearly',NULL,'2025-01-04 01:29:02.944064','OFFERED',2,2,1);
/*!40000 ALTER TABLE `negotiation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property` (
  `property_id` bigint NOT NULL AUTO_INCREMENT,
  `property city` varchar(255) DEFAULT NULL,
  `property country` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `property offer type` varchar(255) DEFAULT NULL,
  `property postal code` varchar(255) DEFAULT NULL,
  `property description` varchar(255) DEFAULT NULL,
  `property title` varchar(255) DEFAULT NULL,
  `property type` varchar(255) DEFAULT NULL,
  `property rent availability date` varchar(255) DEFAULT NULL,
  `property rent price` decimal(38,2) DEFAULT NULL,
  `property rental term` varchar(255) DEFAULT NULL,
  `property sale price` decimal(38,2) DEFAULT NULL,
  `property state` varchar(255) DEFAULT NULL,
  `property street` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) NOT NULL,
  `seller_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  KEY `FKjkvs641cebkyo7t9pdaquonaa` (`seller_id`),
  CONSTRAINT `FKjkvs641cebkyo7t9pdaquonaa` FOREIGN KEY (`seller_id`) REFERENCES `seller_profile` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (2,'Agra','India','2024-12-05 02:00:57.882899','For rent','2104','Shahjahan built this in memory of his beloved Momtaz.\nThis is also the tomb of Momtaz Begum.','Taj Mahal','House','01/02/2025',50000.00,'Monthly',NULL,'Maharashtra','Shahjahan Momtaz Street','2024-12-05 02:00:57.882899',1),(3,'Agrabad','India','2024-12-07 13:25:16.513166','For sale','1300','A sweet home for you in the city.','MDC 3A','Apartment',NULL,NULL,NULL,60000000.00,'Chittagong','Chotopol','2024-12-07 13:25:16.514171',1),(4,'City of Toronto','Canada','2025-01-16 01:20:33.941254','For sale','2098','This land will be perfect for you to start a new business or build your own farm house.','Urban Haven','Land',NULL,NULL,NULL,70000000.00,'Ontario','10 Canlish Road','2025-01-16 01:20:33.941254',2);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property business place power supply`
--

DROP TABLE IF EXISTS `property business place power supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property business place power supply` (
  `property_id` bigint NOT NULL,
  `power_supply` varchar(255) DEFAULT NULL,
  KEY `FKefttv8vxrsts9h1dpctjrh5ij` (`property_id`),
  CONSTRAINT `FKefttv8vxrsts9h1dpctjrh5ij` FOREIGN KEY (`property_id`) REFERENCES `business_place` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property business place power supply`
--

LOCK TABLES `property business place power supply` WRITE;
/*!40000 ALTER TABLE `property business place power supply` DISABLE KEYS */;
/*!40000 ALTER TABLE `property business place power supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property business place purpose`
--

DROP TABLE IF EXISTS `property business place purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property business place purpose` (
  `property_id` bigint NOT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  KEY `FK3w21x5wgbl51e1axjhucor23s` (`property_id`),
  CONSTRAINT `FK3w21x5wgbl51e1axjhucor23s` FOREIGN KEY (`property_id`) REFERENCES `business_place` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property business place purpose`
--

LOCK TABLES `property business place purpose` WRITE;
/*!40000 ALTER TABLE `property business place purpose` DISABLE KEYS */;
/*!40000 ALTER TABLE `property business place purpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property commercial accessibility features`
--

DROP TABLE IF EXISTS `property commercial accessibility features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property commercial accessibility features` (
  `property_id` bigint NOT NULL,
  `accessibility_features` varchar(255) DEFAULT NULL,
  KEY `FKlprqhcge4uercplohe2eg0tdk` (`property_id`),
  CONSTRAINT `FKlprqhcge4uercplohe2eg0tdk` FOREIGN KEY (`property_id`) REFERENCES `commercial_unit` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property commercial accessibility features`
--

LOCK TABLES `property commercial accessibility features` WRITE;
/*!40000 ALTER TABLE `property commercial accessibility features` DISABLE KEYS */;
/*!40000 ALTER TABLE `property commercial accessibility features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property commercial fire safety`
--

DROP TABLE IF EXISTS `property commercial fire safety`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property commercial fire safety` (
  `property_id` bigint NOT NULL,
  `fire_safety` varchar(255) DEFAULT NULL,
  KEY `FKkmr4cah17kprwe1srt8p5kq` (`property_id`),
  CONSTRAINT `FKkmr4cah17kprwe1srt8p5kq` FOREIGN KEY (`property_id`) REFERENCES `commercial_unit` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property commercial fire safety`
--

LOCK TABLES `property commercial fire safety` WRITE;
/*!40000 ALTER TABLE `property commercial fire safety` DISABLE KEYS */;
/*!40000 ALTER TABLE `property commercial fire safety` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property commercial nearby attractions`
--

DROP TABLE IF EXISTS `property commercial nearby attractions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property commercial nearby attractions` (
  `property_id` bigint NOT NULL,
  `nearby_attractions` varchar(255) DEFAULT NULL,
  KEY `FKo65kqnh8l2oqn9qgf0q4ry0if` (`property_id`),
  CONSTRAINT `FKo65kqnh8l2oqn9qgf0q4ry0if` FOREIGN KEY (`property_id`) REFERENCES `commercial_unit` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property commercial nearby attractions`
--

LOCK TABLES `property commercial nearby attractions` WRITE;
/*!40000 ALTER TABLE `property commercial nearby attractions` DISABLE KEYS */;
/*!40000 ALTER TABLE `property commercial nearby attractions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property commercial unit purpose`
--

DROP TABLE IF EXISTS `property commercial unit purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property commercial unit purpose` (
  `property_id` bigint NOT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  KEY `FK15bx14obej10o6orbw4c9tor4` (`property_id`),
  CONSTRAINT `FK15bx14obej10o6orbw4c9tor4` FOREIGN KEY (`property_id`) REFERENCES `commercial_unit` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property commercial unit purpose`
--

LOCK TABLES `property commercial unit purpose` WRITE;
/*!40000 ALTER TABLE `property commercial unit purpose` DISABLE KEYS */;
/*!40000 ALTER TABLE `property commercial unit purpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property house security features`
--

DROP TABLE IF EXISTS `property house security features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property house security features` (
  `property_id` bigint NOT NULL,
  `security_features` varchar(255) DEFAULT NULL,
  KEY `FKdaay2juap1d61raf31uk83m0n` (`property_id`),
  CONSTRAINT `FKdaay2juap1d61raf31uk83m0n` FOREIGN KEY (`property_id`) REFERENCES `house` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property house security features`
--

LOCK TABLES `property house security features` WRITE;
/*!40000 ALTER TABLE `property house security features` DISABLE KEYS */;
INSERT INTO `property house security features` VALUES (2,'Gated Entry'),(2,'Cameras');
/*!40000 ALTER TABLE `property house security features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property land nearby infrastructure`
--

DROP TABLE IF EXISTS `property land nearby infrastructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property land nearby infrastructure` (
  `property_id` bigint NOT NULL,
  `nearby_infrastructure` varchar(255) DEFAULT NULL,
  KEY `FK7dglcrlbrg4bhg5c6u5maqbyy` (`property_id`),
  CONSTRAINT `FK7dglcrlbrg4bhg5c6u5maqbyy` FOREIGN KEY (`property_id`) REFERENCES `land` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property land nearby infrastructure`
--

LOCK TABLES `property land nearby infrastructure` WRITE;
/*!40000 ALTER TABLE `property land nearby infrastructure` DISABLE KEYS */;
INSERT INTO `property land nearby infrastructure` VALUES (4,'Hospitals'),(4,'Roads'),(4,'Schools');
/*!40000 ALTER TABLE `property land nearby infrastructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property land utilities`
--

DROP TABLE IF EXISTS `property land utilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property land utilities` (
  `property_id` bigint NOT NULL,
  `utilities` varchar(255) DEFAULT NULL,
  KEY `FK7bhrnh1ls9dhg83wx4fleyg33` (`property_id`),
  CONSTRAINT `FK7bhrnh1ls9dhg83wx4fleyg33` FOREIGN KEY (`property_id`) REFERENCES `land` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property land utilities`
--

LOCK TABLES `property land utilities` WRITE;
/*!40000 ALTER TABLE `property land utilities` DISABLE KEYS */;
INSERT INTO `property land utilities` VALUES (4,'Water'),(4,'Sewage');
/*!40000 ALTER TABLE `property land utilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property pictures`
--

DROP TABLE IF EXISTS `property pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property pictures` (
  `property_id` bigint NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK7iikne6oy1cglkd8ao71xbduf` (`property_id`),
  CONSTRAINT `FK7iikne6oy1cglkd8ao71xbduf` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property pictures`
--

LOCK TABLES `property pictures` WRITE;
/*!40000 ALTER TABLE `property pictures` DISABLE KEYS */;
INSERT INTO `property pictures` VALUES (3,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\apartment 1 picture 1.jpg'),(3,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\apartment 1 picture 2.jpg'),(3,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\apartment 1 picture 3.jpg'),(3,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\apartment 1 picture 4.jpg'),(2,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\house 1 picture 1.jpg'),(2,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\house 1 picture 2.jpg'),(2,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\house 1 picture 3.jpg'),(2,'D:\\Real Estate Management System\\Real Estate Management System\\src\\main\\resources\\property pictures\\house 1 picture 4.jpg'),(4,'D:\\Java project\\Real Estate Management System\\src\\main\\resources\\property pictures\\land 1 picture 1.jpg'),(4,'D:\\Java project\\Real Estate Management System\\src\\main\\resources\\property pictures\\land 1 picture 2.jpg'),(4,'D:\\Java project\\Real Estate Management System\\src\\main\\resources\\property pictures\\land 1 picture 3.jpg');
/*!40000 ALTER TABLE `property pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property residential unit amenities`
--

DROP TABLE IF EXISTS `property residential unit amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property residential unit amenities` (
  `property_id` bigint NOT NULL,
  `amenities` varchar(255) DEFAULT NULL,
  KEY `FKrmsg989k7nmt6s6x7psq332l` (`property_id`),
  CONSTRAINT `FKrmsg989k7nmt6s6x7psq332l` FOREIGN KEY (`property_id`) REFERENCES `residential_unit` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property residential unit amenities`
--

LOCK TABLES `property residential unit amenities` WRITE;
/*!40000 ALTER TABLE `property residential unit amenities` DISABLE KEYS */;
/*!40000 ALTER TABLE `property residential unit amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property residential unit shared facilities`
--

DROP TABLE IF EXISTS `property residential unit shared facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property residential unit shared facilities` (
  `property_id` bigint NOT NULL,
  `shared_facilities` varchar(255) DEFAULT NULL,
  KEY `FK8pxmx80fsb2e47k5uqokkueib` (`property_id`),
  CONSTRAINT `FK8pxmx80fsb2e47k5uqokkueib` FOREIGN KEY (`property_id`) REFERENCES `residential_unit` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property residential unit shared facilities`
--

LOCK TABLES `property residential unit shared facilities` WRITE;
/*!40000 ALTER TABLE `property residential unit shared facilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `property residential unit shared facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_confirmation`
--

DROP TABLE IF EXISTS `purchase_confirmation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_confirmation` (
  `confirmation_id` bigint NOT NULL AUTO_INCREMENT,
  `property_id` bigint DEFAULT NULL,
  `purchaser_user_id` bigint DEFAULT NULL,
  `seller_user_id` bigint DEFAULT NULL,
  `confirmed` bit(1) NOT NULL,
  `for_sale` bit(1) NOT NULL,
  PRIMARY KEY (`confirmation_id`),
  KEY `FK7eiiub8ss1g7eo062nucwxwjj` (`property_id`),
  KEY `FK8cdaxjmj031o55ri6n92qib5s` (`purchaser_user_id`),
  KEY `FKisx80u96ylca2o4t199elr68x` (`seller_user_id`),
  CONSTRAINT `FK7eiiub8ss1g7eo062nucwxwjj` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  CONSTRAINT `FK8cdaxjmj031o55ri6n92qib5s` FOREIGN KEY (`purchaser_user_id`) REFERENCES `your_property_user` (`user_id`),
  CONSTRAINT `FKisx80u96ylca2o4t199elr68x` FOREIGN KEY (`seller_user_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_confirmation`
--

LOCK TABLES `purchase_confirmation` WRITE;
/*!40000 ALTER TABLE `purchase_confirmation` DISABLE KEYS */;
INSERT INTO `purchase_confirmation` VALUES (1,2,2,1,_binary '',_binary '\0');
/*!40000 ALTER TABLE `purchase_confirmation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_payment`
--

DROP TABLE IF EXISTS `purchase_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_payment` (
  `payment_id` bigint NOT NULL AUTO_INCREMENT,
  `card_number` varchar(255) DEFAULT NULL,
  `card_type` varchar(255) DEFAULT NULL,
  `expiry_month` varchar(255) DEFAULT NULL,
  `expiry_year` int DEFAULT NULL,
  `name_on_card` varchar(255) DEFAULT NULL,
  `security_code` varchar(255) DEFAULT NULL,
  `property_id` bigint DEFAULT NULL,
  `purchaser_id` bigint NOT NULL,
  `seller_id` bigint NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FKdprwdxwob9436kjbpckb5362e` (`property_id`),
  KEY `FKdunxp5my1iwdgbrjt4j62diwu` (`purchaser_id`),
  KEY `FK864ku6pocmf76enhf7omgw6df` (`seller_id`),
  CONSTRAINT `FK864ku6pocmf76enhf7omgw6df` FOREIGN KEY (`seller_id`) REFERENCES `your_property_user` (`user_id`),
  CONSTRAINT `FKdprwdxwob9436kjbpckb5362e` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  CONSTRAINT `FKdunxp5my1iwdgbrjt4j62diwu` FOREIGN KEY (`purchaser_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_payment`
--

LOCK TABLES `purchase_payment` WRITE;
/*!40000 ALTER TABLE `purchase_payment` DISABLE KEYS */;
INSERT INTO `purchase_payment` VALUES (2,'4689-8010-1569-7692','Credit card','September',2028,'MD TANJIUL HASIB','779',2,2,1);
/*!40000 ALTER TABLE `purchase_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_apartment_features`
--

DROP TABLE IF EXISTS `renter_apartment_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_apartment_features` (
  `apartment_features_id` bigint NOT NULL AUTO_INCREMENT,
  `furnished` bit(1) DEFAULT NULL,
  `parking` bit(1) DEFAULT NULL,
  `pet_policy` bit(1) DEFAULT NULL,
  `terrace` bit(1) DEFAULT NULL,
  `renter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`apartment_features_id`),
  KEY `FKovcesqiruu8jno4bh7i37be2n` (`renter_id`),
  CONSTRAINT `FKovcesqiruu8jno4bh7i37be2n` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_apartment_features`
--

LOCK TABLES `renter_apartment_features` WRITE;
/*!40000 ALTER TABLE `renter_apartment_features` DISABLE KEYS */;
INSERT INTO `renter_apartment_features` VALUES (1,_binary '',_binary '\0',_binary '',_binary '',1),(2,_binary '',_binary '',_binary '',_binary '',2);
/*!40000 ALTER TABLE `renter_apartment_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_business_place_features`
--

DROP TABLE IF EXISTS `renter_business_place_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_business_place_features` (
  `business_place_features_id` bigint NOT NULL AUTO_INCREMENT,
  `ventilation` varchar(255) DEFAULT NULL,
  `renter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`business_place_features_id`),
  KEY `FKnd06ybrmxbuerheah2hb0gk4l` (`renter_id`),
  CONSTRAINT `FKnd06ybrmxbuerheah2hb0gk4l` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_business_place_features`
--

LOCK TABLES `renter_business_place_features` WRITE;
/*!40000 ALTER TABLE `renter_business_place_features` DISABLE KEYS */;
INSERT INTO `renter_business_place_features` VALUES (1,'Air Filtration',1);
/*!40000 ALTER TABLE `renter_business_place_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_business_place_power_supply`
--

DROP TABLE IF EXISTS `renter_business_place_power_supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_business_place_power_supply` (
  `business_place_features_id` bigint NOT NULL,
  `power_supply` varchar(255) DEFAULT NULL,
  KEY `FKr6qjnp58ogu6wg0katvg353gb` (`business_place_features_id`),
  CONSTRAINT `FKr6qjnp58ogu6wg0katvg353gb` FOREIGN KEY (`business_place_features_id`) REFERENCES `renter_business_place_features` (`business_place_features_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_business_place_power_supply`
--

LOCK TABLES `renter_business_place_power_supply` WRITE;
/*!40000 ALTER TABLE `renter_business_place_power_supply` DISABLE KEYS */;
INSERT INTO `renter_business_place_power_supply` VALUES (1,'High Voltage'),(1,'Backup Generator');
/*!40000 ALTER TABLE `renter_business_place_power_supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_business_place_purpose`
--

DROP TABLE IF EXISTS `renter_business_place_purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_business_place_purpose` (
  `business_place_features_id` bigint NOT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  KEY `FK1fpcym847mobwb1utjxei7nvc` (`business_place_features_id`),
  CONSTRAINT `FK1fpcym847mobwb1utjxei7nvc` FOREIGN KEY (`business_place_features_id`) REFERENCES `renter_business_place_features` (`business_place_features_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_business_place_purpose`
--

LOCK TABLES `renter_business_place_purpose` WRITE;
/*!40000 ALTER TABLE `renter_business_place_purpose` DISABLE KEYS */;
INSERT INTO `renter_business_place_purpose` VALUES (1,'Store');
/*!40000 ALTER TABLE `renter_business_place_purpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_commercial_unit_accessbility`
--

DROP TABLE IF EXISTS `renter_commercial_unit_accessbility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_commercial_unit_accessbility` (
  `commercial_unit_feature_id` bigint NOT NULL,
  `accessibility` varchar(255) DEFAULT NULL,
  KEY `FKcao9plam9rqpqmcavpdht4eax` (`commercial_unit_feature_id`),
  CONSTRAINT `FKcao9plam9rqpqmcavpdht4eax` FOREIGN KEY (`commercial_unit_feature_id`) REFERENCES `renter_commercial_unit_features` (`commercial_unit_feature_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_commercial_unit_accessbility`
--

LOCK TABLES `renter_commercial_unit_accessbility` WRITE;
/*!40000 ALTER TABLE `renter_commercial_unit_accessbility` DISABLE KEYS */;
INSERT INTO `renter_commercial_unit_accessbility` VALUES (1,'Elevator'),(1,'Braille Signage');
/*!40000 ALTER TABLE `renter_commercial_unit_accessbility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_commercial_unit_features`
--

DROP TABLE IF EXISTS `renter_commercial_unit_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_commercial_unit_features` (
  `commercial_unit_feature_id` bigint NOT NULL AUTO_INCREMENT,
  `floor_layout` varchar(255) DEFAULT NULL,
  `renter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`commercial_unit_feature_id`),
  KEY `FK4f0rfgpq96xqcgs4x22a9bmxr` (`renter_id`),
  CONSTRAINT `FK4f0rfgpq96xqcgs4x22a9bmxr` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_commercial_unit_features`
--

LOCK TABLES `renter_commercial_unit_features` WRITE;
/*!40000 ALTER TABLE `renter_commercial_unit_features` DISABLE KEYS */;
INSERT INTO `renter_commercial_unit_features` VALUES (1,'Separate',1);
/*!40000 ALTER TABLE `renter_commercial_unit_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_commercial_unit_purpose`
--

DROP TABLE IF EXISTS `renter_commercial_unit_purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_commercial_unit_purpose` (
  `commercial_unit_feature_id` bigint NOT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  KEY `FK5tjd0sqry51ark2rjg8ynpxnd` (`commercial_unit_feature_id`),
  CONSTRAINT `FK5tjd0sqry51ark2rjg8ynpxnd` FOREIGN KEY (`commercial_unit_feature_id`) REFERENCES `renter_commercial_unit_features` (`commercial_unit_feature_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_commercial_unit_purpose`
--

LOCK TABLES `renter_commercial_unit_purpose` WRITE;
/*!40000 ALTER TABLE `renter_commercial_unit_purpose` DISABLE KEYS */;
INSERT INTO `renter_commercial_unit_purpose` VALUES (1,'Retail'),(1,'Office');
/*!40000 ALTER TABLE `renter_commercial_unit_purpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_house_features`
--

DROP TABLE IF EXISTS `renter_house_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_house_features` (
  `house_feature_id` bigint NOT NULL AUTO_INCREMENT,
  `basement` varchar(255) DEFAULT NULL,
  `garage` bit(1) DEFAULT NULL,
  `renter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`house_feature_id`),
  KEY `FKka8dyaund5sen3wcx6op915l5` (`renter_id`),
  CONSTRAINT `FKka8dyaund5sen3wcx6op915l5` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_house_features`
--

LOCK TABLES `renter_house_features` WRITE;
/*!40000 ALTER TABLE `renter_house_features` DISABLE KEYS */;
INSERT INTO `renter_house_features` VALUES (1,'Finished',_binary '\0',1),(2,'Finished',_binary '',2);
/*!40000 ALTER TABLE `renter_house_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_house_security_features`
--

DROP TABLE IF EXISTS `renter_house_security_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_house_security_features` (
  `house_feature_id` bigint NOT NULL,
  `security_features` varchar(255) DEFAULT NULL,
  KEY `FKtgaripa269quuh8rgejo3pktk` (`house_feature_id`),
  CONSTRAINT `FKtgaripa269quuh8rgejo3pktk` FOREIGN KEY (`house_feature_id`) REFERENCES `renter_house_features` (`house_feature_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_house_security_features`
--

LOCK TABLES `renter_house_security_features` WRITE;
/*!40000 ALTER TABLE `renter_house_security_features` DISABLE KEYS */;
INSERT INTO `renter_house_security_features` VALUES (1,'Security System'),(1,'Camera'),(1,'Gated Entry'),(2,'Security System'),(2,'Gated Entry');
/*!40000 ALTER TABLE `renter_house_security_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_land_features`
--

DROP TABLE IF EXISTS `renter_land_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_land_features` (
  `land_feature_id` bigint NOT NULL AUTO_INCREMENT,
  `road_access` varchar(255) DEFAULT NULL,
  `topography` varchar(255) DEFAULT NULL,
  `renter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`land_feature_id`),
  KEY `FKrsckn5o8bylx5who8yar46qii` (`renter_id`),
  CONSTRAINT `FKrsckn5o8bylx5who8yar46qii` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_land_features`
--

LOCK TABLES `renter_land_features` WRITE;
/*!40000 ALTER TABLE `renter_land_features` DISABLE KEYS */;
INSERT INTO `renter_land_features` VALUES (1,'Unpaved','Flat',1);
/*!40000 ALTER TABLE `renter_land_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_land_utilites`
--

DROP TABLE IF EXISTS `renter_land_utilites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_land_utilites` (
  `land_feature_id` bigint NOT NULL,
  `utilites` varchar(255) DEFAULT NULL,
  KEY `FKrjokx3kklmg7a1xescnv3g1gh` (`land_feature_id`),
  CONSTRAINT `FKrjokx3kklmg7a1xescnv3g1gh` FOREIGN KEY (`land_feature_id`) REFERENCES `renter_land_features` (`land_feature_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_land_utilites`
--

LOCK TABLES `renter_land_utilites` WRITE;
/*!40000 ALTER TABLE `renter_land_utilites` DISABLE KEYS */;
INSERT INTO `renter_land_utilites` VALUES (1,'Electricity'),(1,'Water');
/*!40000 ALTER TABLE `renter_land_utilites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_preferred_locations`
--

DROP TABLE IF EXISTS `renter_preferred_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_preferred_locations` (
  `renter_id` bigint NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  KEY `FKh40p6eti963hpuh1nrfi8o2db` (`renter_id`),
  CONSTRAINT `FKh40p6eti963hpuh1nrfi8o2db` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_preferred_locations`
--

LOCK TABLES `renter_preferred_locations` WRITE;
/*!40000 ALTER TABLE `renter_preferred_locations` DISABLE KEYS */;
INSERT INTO `renter_preferred_locations` VALUES (1,'Gulshan'),(1,'Banani'),(1,'Dhanmondi'),(2,'Halishahar'),(2,'Bahaddarhat'),(2,'Chawkbazar');
/*!40000 ALTER TABLE `renter_preferred_locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_preferred_property_types`
--

DROP TABLE IF EXISTS `renter_preferred_property_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_preferred_property_types` (
  `renter_id` bigint NOT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  KEY `FKc4ko37x0e2hw6oar2qnv8a7nh` (`renter_id`),
  CONSTRAINT `FKc4ko37x0e2hw6oar2qnv8a7nh` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_preferred_property_types`
--

LOCK TABLES `renter_preferred_property_types` WRITE;
/*!40000 ALTER TABLE `renter_preferred_property_types` DISABLE KEYS */;
INSERT INTO `renter_preferred_property_types` VALUES (1,'Apartment'),(1,'Houses'),(1,'Lands'),(1,'Residential Units'),(1,'Commercial Units'),(1,'Business Places'),(2,'Apartment'),(2,'Houses');
/*!40000 ALTER TABLE `renter_preferred_property_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_profile`
--

DROP TABLE IF EXISTS `renter_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_profile` (
  `renter_id` bigint NOT NULL AUTO_INCREMENT,
  `max_budget` decimal(38,2) DEFAULT NULL,
  `min_budget` decimal(38,2) DEFAULT NULL,
  `move_in_date` varchar(255) DEFAULT NULL,
  `rental_term` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`renter_id`),
  UNIQUE KEY `UKelim2q9ssfbcnb3gno5omtopw` (`user_id`),
  CONSTRAINT `FKkl73h51o5903y8ls1wl7vhsea` FOREIGN KEY (`user_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_profile`
--

LOCK TABLES `renter_profile` WRITE;
/*!40000 ALTER TABLE `renter_profile` DISABLE KEYS */;
INSERT INTO `renter_profile` VALUES (1,25000.00,5000.00,'01/12/2024','Monthly',1),(2,30000.00,5000.00,'06/12/2024','Monthly',2);
/*!40000 ALTER TABLE `renter_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_residential_unit_amenities`
--

DROP TABLE IF EXISTS `renter_residential_unit_amenities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_residential_unit_amenities` (
  `residential_unit_features_id` bigint NOT NULL,
  `amenities` varchar(255) DEFAULT NULL,
  KEY `FKkve292v2e1466oyvawig0yeqn` (`residential_unit_features_id`),
  CONSTRAINT `FKkve292v2e1466oyvawig0yeqn` FOREIGN KEY (`residential_unit_features_id`) REFERENCES `renter_residential_unit_features` (`residential_unit_features_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_residential_unit_amenities`
--

LOCK TABLES `renter_residential_unit_amenities` WRITE;
/*!40000 ALTER TABLE `renter_residential_unit_amenities` DISABLE KEYS */;
INSERT INTO `renter_residential_unit_amenities` VALUES (1,'Gym'),(1,'Kid\'s Playground');
/*!40000 ALTER TABLE `renter_residential_unit_amenities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renter_residential_unit_features`
--

DROP TABLE IF EXISTS `renter_residential_unit_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renter_residential_unit_features` (
  `residential_unit_features_id` bigint NOT NULL AUTO_INCREMENT,
  `noise_insulation` bit(1) DEFAULT NULL,
  `storage_space` varchar(255) DEFAULT NULL,
  `renter_id` bigint DEFAULT NULL,
  PRIMARY KEY (`residential_unit_features_id`),
  KEY `FK6hmlt9ms9p61xmq6gqv9j2leh` (`renter_id`),
  CONSTRAINT `FK6hmlt9ms9p61xmq6gqv9j2leh` FOREIGN KEY (`renter_id`) REFERENCES `renter_profile` (`renter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renter_residential_unit_features`
--

LOCK TABLES `renter_residential_unit_features` WRITE;
/*!40000 ALTER TABLE `renter_residential_unit_features` DISABLE KEYS */;
INSERT INTO `renter_residential_unit_features` VALUES (1,_binary '','Built-In',1);
/*!40000 ALTER TABLE `renter_residential_unit_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residential_unit`
--

DROP TABLE IF EXISTS `residential_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `residential_unit` (
  `configuration` int DEFAULT NULL,
  `natural_light` varchar(255) DEFAULT NULL,
  `noise_insulation` bit(1) DEFAULT NULL,
  `orientation` varchar(255) DEFAULT NULL,
  `storage_type` varchar(255) DEFAULT NULL,
  `property_id` bigint NOT NULL,
  PRIMARY KEY (`property_id`),
  CONSTRAINT `FK6r48i4d9xgxamqej6vhumh9uh` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residential_unit`
--

LOCK TABLES `residential_unit` WRITE;
/*!40000 ALTER TABLE `residential_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `residential_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saved_property`
--

DROP TABLE IF EXISTS `saved_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saved_property` (
  `saved_property_id` bigint NOT NULL AUTO_INCREMENT,
  `property_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`saved_property_id`),
  KEY `FKjwt6noj2py0fyludbo8l0a9nb` (`property_id`),
  KEY `FKsuhb495lwak0w3pqxx57k400l` (`user_id`),
  CONSTRAINT `FKjwt6noj2py0fyludbo8l0a9nb` FOREIGN KEY (`property_id`) REFERENCES `property` (`property_id`),
  CONSTRAINT `FKsuhb495lwak0w3pqxx57k400l` FOREIGN KEY (`user_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saved_property`
--

LOCK TABLES `saved_property` WRITE;
/*!40000 ALTER TABLE `saved_property` DISABLE KEYS */;
INSERT INTO `saved_property` VALUES (2,3,2),(4,2,1);
/*!40000 ALTER TABLE `saved_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_opened_communication_usertype`
--

DROP TABLE IF EXISTS `seller_opened_communication_usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller_opened_communication_usertype` (
  `seler_id` bigint NOT NULL,
  `seller_opened_communiction_user_type` varchar(255) DEFAULT NULL,
  KEY `FKnc1ulbk4t56xvetmxe188nsp1` (`seler_id`),
  CONSTRAINT `FKnc1ulbk4t56xvetmxe188nsp1` FOREIGN KEY (`seler_id`) REFERENCES `seller_profile` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_opened_communication_usertype`
--

LOCK TABLES `seller_opened_communication_usertype` WRITE;
/*!40000 ALTER TABLE `seller_opened_communication_usertype` DISABLE KEYS */;
INSERT INTO `seller_opened_communication_usertype` VALUES (1,'Buyer'),(1,'Renter'),(2,'Buyer'),(2,'Renter');
/*!40000 ALTER TABLE `seller_opened_communication_usertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_owned_property_type`
--

DROP TABLE IF EXISTS `seller_owned_property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller_owned_property_type` (
  `seller_id` bigint NOT NULL,
  `seller_owned_property_type` varchar(255) DEFAULT NULL,
  KEY `FKrjl3wxoagrqd3rffh9654krdh` (`seller_id`),
  CONSTRAINT `FKrjl3wxoagrqd3rffh9654krdh` FOREIGN KEY (`seller_id`) REFERENCES `seller_profile` (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_owned_property_type`
--

LOCK TABLES `seller_owned_property_type` WRITE;
/*!40000 ALTER TABLE `seller_owned_property_type` DISABLE KEYS */;
INSERT INTO `seller_owned_property_type` VALUES (1,'Apartment'),(1,'Houses'),(1,'Residential Units'),(2,'Apartment'),(2,'Houses');
/*!40000 ALTER TABLE `seller_owned_property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_profile`
--

DROP TABLE IF EXISTS `seller_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seller_profile` (
  `seller_id` bigint NOT NULL AUTO_INCREMENT,
  `seller_license` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`seller_id`),
  UNIQUE KEY `UK8x4osw2o3lsiolxxuiloqg7ba` (`user_id`),
  CONSTRAINT `FKnlarjucqdjic09oq2278kd4b9` FOREIGN KEY (`user_id`) REFERENCES `your_property_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_profile`
--

LOCK TABLES `seller_profile` WRITE;
/*!40000 ALTER TABLE `seller_profile` DISABLE KEYS */;
INSERT INTO `seller_profile` VALUES (1,'123-4567-890',1),(2,'1234-789-098',2);
/*!40000 ALTER TABLE `seller_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `your_property_user`
--

DROP TABLE IF EXISTS `your_property_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `your_property_user` (
  `user_type` varchar(31) NOT NULL,
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `bio` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `e-mail` varchar(255) DEFAULT NULL,
  `facebook link` varchar(255) DEFAULT NULL,
  `first name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `google link` varchar(255) DEFAULT NULL,
  `last name` varchar(255) DEFAULT NULL,
  `mobile number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `security answer` varchar(255) DEFAULT NULL,
  `security question` varchar(255) DEFAULT NULL,
  `twitter link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKnfjeetjn57cmsnm5q1jc7a3b5` (`e-mail`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `your_property_user`
--

LOCK TABLES `your_property_user` WRITE;
/*!40000 ALTER TABLE `your_property_user` DISABLE KEYS */;
INSERT INTO `your_property_user` VALUES ('YourPropertyUser',1,'I\'m a student of CUET.\nI\'m currently working on my java project.','03/09/2004','tanjiulhasib@gmail.com','https://www.facebook.com/tanjiul.hasib/','Tanjiul','Male',NULL,'Hasib','+8801790501604','passdiyekamki','D:\\Downloads\\profile picture.jpg','Pussy','What\'s your favorite animal?','https://x.com/AhinatO_O'),('YourPropertyUser',2,'CUET er mare sudi','06/11/1998','ariathasan189@gmail.com',NULL,'Ariat','Male',NULL,'Hasan','+8801560002704','CUET_CDI','C:\\Users\\USER\\OneDrive\\Pictures\\memes\\extreme pain.jpg','Between Usha\'s thighs','What\'s your favorite place?',NULL),('YourPropertyUser',3,'I am just a girl. UwU','08/08/2005','taisaadhrin@gmail.com',NULL,'Taisa','Female',NULL,'Adhrin','+8801608603262','hello1234','D:\\Downloads\\Shidratul Muntaha.jpg','Pickles','What\'s your favorite food?',NULL);
/*!40000 ALTER TABLE `your_property_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-24 14:35:32
