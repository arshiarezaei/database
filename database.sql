-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: phase2
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `blocked_user`
--

DROP TABLE IF EXISTS `blocked_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `blocked_user` (
  `blocked_user` varchar(20) NOT NULL,
  `blocked_by` varchar(20) NOT NULL,
  PRIMARY KEY (`blocked_by`,`blocked_user`),
  KEY `bloked_user` (`blocked_user`),
  CONSTRAINT `blocked_user_ibfk_1` FOREIGN KEY (`blocked_user`) REFERENCES `user` (`phone_number`),
  CONSTRAINT `blocked_user_ibfk_2` FOREIGN KEY (`blocked_by`) REFERENCES `user` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blocked_user`
--

LOCK TABLES `blocked_user` WRITE;
/*!40000 ALTER TABLE `blocked_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `blocked_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_members`
--

DROP TABLE IF EXISTS `channel_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `channel_members` (
  `channel_id` varchar(20) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `last_seen` datetime DEFAULT NULL,
  PRIMARY KEY (`channel_id`,`member_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `channel_members_ibfk_1` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`channel_id`),
  CONSTRAINT `channel_members_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `user` (`phone_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_members`
--

LOCK TABLES `channel_members` WRITE;
/*!40000 ALTER TABLE `channel_members` DISABLE KEYS */;
INSERT INTO `channel_members` VALUES ('a','02188600856',NULL),('a','09125509551',NULL),('a','09129999999',NULL),('aChannel','02188600856',NULL),('aChannel','09125509551',NULL),('ch1','09125509551','2018-12-05 18:43:28'),('ch1','09129999999','2018-12-05 16:49:04');
/*!40000 ALTER TABLE `channel_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_messages`
--

DROP TABLE IF EXISTS `channel_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `channel_messages` (
  `channel_id` varchar(20) NOT NULL,
  `text` text NOT NULL,
  `date` datetime DEFAULT NULL,
  KEY `channel_messages_channels_channel_id_fk` (`channel_id`),
  CONSTRAINT `channel_messages_channels_channel_id_fk` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_messages`
--

LOCK TABLES `channel_messages` WRITE;
/*!40000 ALTER TABLE `channel_messages` DISABLE KEYS */;
INSERT INTO `channel_messages` VALUES ('a','farnaz',NULL),('a','farnaz dooset daram',NULL),('ch1','hi this is a channel message',NULL),('aChannel','test date and time','2018-12-05 22:31:59');
/*!40000 ALTER TABLE `channel_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channels`
--

DROP TABLE IF EXISTS `channels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `channels` (
  `channel_id` varchar(20) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `chanel_link` varchar(100) DEFAULT NULL,
  `admin_phone_number` varchar(20) NOT NULL,
  UNIQUE KEY `channels_chanel_link_uindex` (`chanel_link`),
  KEY `channel_id` (`channel_id`),
  KEY `channels_user_phone_number_fk` (`admin_phone_number`),
  CONSTRAINT `channels_user_phone_number_fk` FOREIGN KEY (`admin_phone_number`) REFERENCES `user` (`phone_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channels`
--

LOCK TABLES `channels` WRITE;
/*!40000 ALTER TABLE `channels` DISABLE KEYS */;
INSERT INTO `channels` VALUES ('a','a channel','a.com','09213975282'),('aChannel','newChannel','www.google.com','09125509551'),('ch1','tst','ch1.com','09129999999');
/*!40000 ALTER TABLE `channels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_members` (
  `group_id` varchar(20) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `last_seen` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`,`member_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `group_members_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groupes` (`group_id`),
  CONSTRAINT `group_members_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `user` (`phone_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
INSERT INTO `group_members` VALUES ('g1','09213975282',NULL),('g2','09213975282',NULL),('g3','09213975282','2018-12-05 19:06:19');
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_messages`
--

DROP TABLE IF EXISTS `group_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_messages` (
  `group_id` varchar(20) DEFAULT NULL,
  `text` text,
  `date` datetime DEFAULT NULL,
  `sender_id` varchar(20) DEFAULT NULL,
  KEY `group_id` (`group_id`),
  KEY `sender_id` (`sender_id`),
  CONSTRAINT `group_messages_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groupes` (`group_id`),
  CONSTRAINT `group_messages_ibfk_2` FOREIGN KEY (`sender_id`) REFERENCES `group_members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_messages`
--

LOCK TABLES `group_messages` WRITE;
/*!40000 ALTER TABLE `group_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupes`
--

DROP TABLE IF EXISTS `groupes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `groupes` (
  `group_id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `link` varchar(100) DEFAULT NULL,
  `admin_phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `groupes_link_uindex` (`link`),
  KEY `groupes_user_phone_number_fk` (`admin_phone_number`),
  CONSTRAINT `groupes_user_phone_number_fk` FOREIGN KEY (`admin_phone_number`) REFERENCES `user` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupes`
--

LOCK TABLES `groupes` WRITE;
/*!40000 ALTER TABLE `groupes` DISABLE KEYS */;
INSERT INTO `groupes` VALUES ('g1','farnaz lovely','','09125509551'),('g2','DB','www.google.com','09213975282'),('g3','aGroup','g3.aaaa','09129999999');
/*!40000 ALTER TABLE `groupes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login_details` (
  `user_id` varchar(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `device` varchar(20) DEFAULT NULL,
  `os` varchar(20) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `login_details_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `private_chats`
--

DROP TABLE IF EXISTS `private_chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `private_chats` (
  `sender` varchar(20) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `text` text,
  `date` datetime DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  KEY `sender_id` (`sender`),
  KEY `receiver_id` (`receiver`),
  CONSTRAINT `private_chats_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user` (`phone_number`),
  CONSTRAINT `private_chats_ibfk_2` FOREIGN KEY (`receiver`) REFERENCES `user` (`phone_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `private_chats`
--

LOCK TABLES `private_chats` WRITE;
/*!40000 ALTER TABLE `private_chats` DISABLE KEYS */;
INSERT INTO `private_chats` VALUES ('09213975282','09125509551','i love farnaz',NULL,1),('09213975282','09213567687','farnaz i love you too much',NULL,1),('09213975282','09125509551','lovely farnaz',NULL,0),('09125509551','09213975282','farnaz mehraboon',NULL,1),('09129999999','09213975282','hi this is a test message',NULL,0),('09213975282','09129999999','1','2018-12-05 13:45:05',0),('09213975282','09129999999','1','2018-12-05 13:45:05',0),('09213975282','09129999999','1','2018-12-05 13:45:05',0),('09125509551','09129999999','2','2018-12-05 13:51:34',0),('09125509551','09129999999','3','2018-12-05 13:51:57',1),('09125509551','09129999999','test date and time','2018-12-05 22:28:39',0);
/*!40000 ALTER TABLE `private_chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` varchar(20) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `login_state` tinyint(1) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `link` varchar(100) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `user_link_uindex` (`link`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('09213975282','09213975282',NULL,'','arshiya',NULL,'2018-12-05 19:06:08'),('09125509551','09125509551',NULL,NULL,NULL,NULL,'2018-12-05 22:31:28'),('02188600856','02188600856',NULL,NULL,NULL,NULL,NULL),('02188600855','02188600855',NULL,NULL,NULL,NULL,NULL),('09213456576','09213456576',NULL,NULL,NULL,NULL,NULL),('09213567687','09213567687',NULL,NULL,NULL,NULL,NULL),(NULL,'09129999999',NULL,'123s','test a',NULL,NULL),(NULL,'09123378765',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_settings`
--

DROP TABLE IF EXISTS `user_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_settings` (
  `phone_number` varchar(20) NOT NULL,
  `bio` text,
  `self_destruct` smallint(6) DEFAULT NULL,
  `two_step_pass` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`phone_number`),
  CONSTRAINT `user_settings_ibfk_1` FOREIGN KEY (`phone_number`) REFERENCES `user` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_settings`
--

LOCK TABLES `user_settings` WRITE;
/*!40000 ALTER TABLE `user_settings` DISABLE KEYS */;
INSERT INTO `user_settings` VALUES ('02188600855',NULL,NULL,NULL),('09123378765',NULL,NULL,NULL),('09129999999','hi this is my bio',3,NULL),('09213456576',NULL,NULL,NULL),('09213567687',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-05 23:25:48
