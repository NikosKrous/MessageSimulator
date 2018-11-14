CREATE DATABASE  IF NOT EXISTS `db_pp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_pp`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: db_pp
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messages` (
  `mes_id` int(11) NOT NULL AUTO_INCREMENT,
  `mes_date` timestamp NOT NULL,
  `mes_data` varchar(250) NOT NULL,
  PRIMARY KEY (`mes_id`),
  UNIQUE KEY `mes_id_UNIQUE` (`mes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (14,'2018-09-04 14:09:05','Hello there'),(15,'2018-09-04 14:10:28','Hello there'),(16,'2018-09-07 12:22:19','Hello there'),(17,'2018-09-08 15:26:42','Hello there'),(18,'2018-09-08 15:00:42','It\'s sunny bruhhh'),(19,'2018-09-08 15:26:42','Ela under my umbrella'),(20,'2018-09-08 15:28:18','Hello there'),(21,'2018-09-08 15:28:18','It\'s raining'),(22,'2018-09-08 15:28:18','Ela under my umbrella'),(23,'2018-09-08 15:42:33','It\'s raining'),(24,'2018-09-08 15:43:18','Ela under my umbrella'),(25,'2018-09-08 16:31:48','You\'re fired!'),(26,'2018-09-08 16:54:57','Good morning'),(27,'2018-09-08 16:56:08','Good morning'),(28,'2018-09-08 16:57:43','Good morning'),(29,'2018-09-13 12:31:04','hihihihihi'),(30,'2018-09-15 13:18:42','You\'re da man'),(31,'2018-09-15 13:23:28','Kosssstaaaki'),(32,'2018-09-15 14:38:02','Doganitooooo'),(33,'2018-09-15 14:55:07','Psile mou ti leei?'),(34,'2018-09-15 15:00:06','boooi'),(36,'2018-09-15 17:23:55','kiki'),(37,'2018-09-15 17:21:00','d'),(38,'2018-09-15 17:18:59','hoho'),(39,'2018-09-15 16:37:18','Dogito'),(41,'2018-09-18 11:24:32','Suuuuuup mate?'),(42,'2018-09-18 12:11:26','Hi admin'),(43,'2018-09-18 12:25:28',' fdd'),(44,'2018-09-18 13:00:43','hi'),(46,'2018-09-18 13:04:11',''),(47,'2018-09-18 13:06:45',''),(48,'2018-09-18 13:12:32',''),(49,'2018-09-18 13:13:48','hi'),(50,'2018-09-19 11:21:01','Hi Leonida'),(51,'2018-09-19 15:32:14','Hi Kostas'),(52,'2018-09-19 15:33:43','Hi boi'),(53,'2018-09-19 15:37:08','holla'),(54,'2018-09-19 15:38:04','Hi adminnnnnn'),(55,'2018-09-19 15:41:05','hihi'),(56,'2018-09-19 15:51:00','ep? ti leei??'),(57,'2018-09-19 15:52:04','hi'),(58,'2018-09-19 16:28:46','hi kostas'),(59,'2018-09-19 16:38:43','Yo'),(60,'2018-09-19 17:27:14','hi'),(61,'2018-09-19 17:27:43','hi Teo'),(62,'2018-09-19 17:58:40','geia'),(63,'2018-09-19 18:00:02','Tha vgoume?'),(64,'2018-09-19 18:00:48','hi'),(65,'2018-09-19 18:01:26','Nikoliiiii'),(66,'2018-09-19 18:03:07','hkjdnfld'),(67,'2018-09-19 18:11:09','Hi Flash'),(68,'2018-09-19 18:12:24','hi'),(69,'2018-09-19 18:24:37','Kosta telos re'),(70,'2018-09-20 15:37:03','Hi Batman');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (4,'Low_User'),(2,'PowerUser'),(1,'SuperAdmin'),(3,'User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `send`
--

DROP TABLE IF EXISTS `send`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `send` (
  `send_id` int(11) NOT NULL AUTO_INCREMENT,
  `send_from` int(11) NOT NULL,
  `send_to` int(11) NOT NULL,
  `mes_id` int(11) NOT NULL,
  PRIMARY KEY (`send_id`),
  UNIQUE KEY `send_id_UNIQUE` (`send_id`),
  KEY `send_from_idx` (`send_from`),
  KEY `send_to_idx` (`send_to`),
  KEY `mes_id_idx` (`mes_id`),
  CONSTRAINT `mes_id` FOREIGN KEY (`mes_id`) REFERENCES `messages` (`mes_id`),
  CONSTRAINT `send_from` FOREIGN KEY (`send_from`) REFERENCES `users` (`id`),
  CONSTRAINT `send_to` FOREIGN KEY (`send_to`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `send`
--

LOCK TABLES `send` WRITE;
/*!40000 ALTER TABLE `send` DISABLE KEYS */;
INSERT INTO `send` VALUES (1,1,2,14),(2,1,2,15),(3,1,2,16),(4,1,2,17),(5,3,4,17),(6,1,3,17),(10,3,4,23),(11,2,3,24),(12,5,2,25),(13,3,5,26),(14,3,5,27),(15,3,5,28),(17,5,3,29),(18,5,1,30),(19,5,2,31),(20,5,3,32),(21,5,4,33),(22,5,1,34),(29,5,3,41),(30,4,5,42),(31,1,2,43),(32,5,2,44),(35,5,2,47),(36,5,2,48),(37,5,2,49),(38,1,3,50),(39,1,2,51),(40,1,3,52),(41,1,2,53),(42,1,5,54),(43,1,2,55),(44,2,3,56),(45,1,2,57),(46,1,2,58),(47,1,2,59),(48,1,2,60),(49,2,4,61),(50,1,4,62),(51,1,3,63),(52,2,1,64),(53,3,1,65),(54,3,1,66),(55,1,9,67),(56,1,9,68),(57,1,2,69),(58,1,5,70);
/*!40000 ALTER TABLE `send` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nikos','Krous','Zouz','123',1),(2,'Kostas','Verv','Croc','231',2),(3,'Leo','Dogan','Dog','312',3),(4,'Teo','Gal','Psilos','444',4),(5,'Bruce','Wayne','admin','admin',1),(9,'Barry','Alen','Flash','111',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-20 20:13:03
