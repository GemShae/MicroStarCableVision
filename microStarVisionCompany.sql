-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: microstarcablevision
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `account_Status` varchar(15) NOT NULL,
  `amount_due` float NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_ID_UNIQUE` (`account_id`),
  KEY `fk_account_customer_45698_idx` (`customer_id`),
  CONSTRAINT `fk_account_customer_45698` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Active',0,2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `town` varchar(45) NOT NULL,
  `parish` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_ID_UNIQUE` (`address_id`),
  KEY `fk_address_customer_97657_idx` (`customer_id`),
  CONSTRAINT `fk_address_customer_97657` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'city','Street','Country',1),(2,'town','parish','street',2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `bill_date` datetime NOT NULL,
  `amount` float NOT NULL,
  `due_date` datetime NOT NULL,
  `period_start` datetime NOT NULL,
  `period_end` datetime NOT NULL,
  `service_id` int DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  UNIQUE KEY `bill_ID_UNIQUE` (`bill_id`),
  KEY `fk_bill_service_65388_idx` (`service_id`),
  CONSTRAINT `fk_bill_service_65388` FOREIGN KEY (`service_id`) REFERENCES `service` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `complaint_id` int NOT NULL AUTO_INCREMENT,
  `type_of_issue` varchar(50) NOT NULL,
  `details_of_issue` varchar(500) DEFAULT NULL,
  `date_raised` datetime NOT NULL,
  `status` varchar(15) NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`complaint_id`),
  UNIQUE KEY `complaint_ID_UNIQUE` (`complaint_id`),
  KEY `fk_complaint_account_idx` (`account_id`),
  CONSTRAINT `fk_complaint_account_65563` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
INSERT INTO `complaint` VALUES (1,'No connection','red light on router','2021-04-05 21:52:56','unresolved',2),(2,'connection','Red dlight on box','2021-04-22 04:25:18','Open',1),(6,'connection','Red dlight on box','2021-04-22 05:11:15','Open',1),(7,'connection','Red dlight on box','2021-04-22 05:11:47','Open',1),(8,'connection','Red dlight on box','2021-04-22 08:42:42','Open',1),(9,'connection','Red dlight on box','2021-04-22 08:54:22','Open',1),(10,'connection','Red dlight on box','2021-04-22 12:14:41','Open',1),(11,'connection','Red dlight on box','2021-04-22 12:15:39','Open',1);
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaintvisit`
--

DROP TABLE IF EXISTS `complaintvisit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaintvisit` (
  `complaint_id` int NOT NULL,
  `visit_id` int NOT NULL,
  `complaintvisit_id` int NOT NULL,
  PRIMARY KEY (`complaintvisit_id`),
  KEY `fk_complaintvisit_visit_75689_idx` (`visit_id`),
  KEY `fk_complaintvisit_complaint_75365_idx` (`complaint_id`),
  CONSTRAINT `fk_complaintvisit_complaint_75365` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`complaint_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_complaintvisit_visit_75689` FOREIGN KEY (`visit_id`) REFERENCES `visit` (`visit_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaintvisit`
--

LOCK TABLES `complaintvisit` WRITE;
/*!40000 ALTER TABLE `complaintvisit` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaintvisit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactnum`
--

DROP TABLE IF EXISTS `contactnum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactnum` (
  `contact_num` varchar(15) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `contact_num_UNIQUE` (`contact_num`),
  CONSTRAINT `fk_contactnum_user_12564` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactnum`
--

LOCK TABLES `contactnum` WRITE;
/*!40000 ALTER TABLE `contactnum` DISABLE KEYS */;
INSERT INTO `contactnum` VALUES ('1876564736',2),('876-256-2384',1);
/*!40000 ALTER TABLE `contactnum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  UNIQUE KEY `customer_ID_UNIQUE` (`customer_id`),
  CONSTRAINT `fk_customer_account_15648` FOREIGN KEY (`customer_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'customer@mail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `idemployee_UNIQUE` (`emp_id`),
  CONSTRAINT `fk_employee_user_45698` FOREIGN KEY (`emp_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Admin','Active'),(3,'Technician','Offline'),(4,'Customer Service Rep','Active');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeecomplaint`
--

DROP TABLE IF EXISTS `employeecomplaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeecomplaint` (
  `emp_complaint_id` int NOT NULL AUTO_INCREMENT,
  `emp_id` int NOT NULL,
  `complaint_id` int NOT NULL,
  PRIMARY KEY (`emp_complaint_id`),
  UNIQUE KEY `emp_Complaint_ID_UNIQUE` (`emp_complaint_id`),
  KEY `fk_employeecomplaint_complaint_85469_idx` (`emp_id`),
  KEY `fk_employeecomplaint_employee_85469_idx` (`complaint_id`),
  CONSTRAINT `fk_employeecomplaint_employee_25154` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`complaint_id`),
  CONSTRAINT `fk_employeecomplaint_employee_85469` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeecomplaint`
--

LOCK TABLES `employeecomplaint` WRITE;
/*!40000 ALTER TABLE `employeecomplaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeecomplaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeevisit`
--

DROP TABLE IF EXISTS `employeevisit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeevisit` (
  `visit_id` int NOT NULL,
  `emp_id` int NOT NULL,
  `employeevisit_id` int NOT NULL,
  PRIMARY KEY (`employeevisit_id`),
  KEY `fk_employeevisit_employee_85214_idx` (`emp_id`),
  KEY `fk_employeevisit_vist_15469_idx` (`visit_id`),
  CONSTRAINT `fk_employeevisit_employee_85214` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_employeevisit_vist_15469` FOREIGN KEY (`visit_id`) REFERENCES `visit` (`visit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeevisit`
--

LOCK TABLES `employeevisit` WRITE;
/*!40000 ALTER TABLE `employeevisit` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeevisit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `is_read` bit(1) NOT NULL,
  `text` varchar(500) NOT NULL,
  `date` timestamp NOT NULL,
  `sender_id` int NOT NULL,
  `recipient_id` int NOT NULL,
  `complaint_id` int DEFAULT NULL,
  UNIQUE KEY `message_ID_UNIQUE` (`message_id`),
  KEY `fk_message_user_45698_idx` (`sender_id`),
  KEY `fk_message_user_65878_idx` (`recipient_id`),
  KEY `fk_message_complaint_15698_idx` (`complaint_id`),
  CONSTRAINT `fk_message_complaint_15698` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`complaint_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_message_user_recipient_65878` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_message_user_sender_45698` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,_binary '','text','2021-04-05 19:56:30',1,2,1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL DEFAULT '0',
  `payment_status` varchar(15) NOT NULL,
  `payment_date` datetime NOT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `payment_ID_UNIQUE` (`payment_id`),
  KEY `fk_payment_account_67464_idx` (`account_id`),
  CONSTRAINT `fk_payment_account_67464` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `response`
--

DROP TABLE IF EXISTS `response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `response` (
  `response_id` int NOT NULL AUTO_INCREMENT,
  `response_date` datetime NOT NULL,
  `response_detail` varchar(1000) NOT NULL,
  `complaint_id` int NOT NULL,
  PRIMARY KEY (`response_id`),
  UNIQUE KEY `response_ID_UNIQUE` (`response_id`),
  KEY `fk_response_complaint_47898_idx` (`complaint_id`),
  CONSTRAINT `fk_response_complaint_47898` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `response`
--

LOCK TABLES `response` WRITE;
/*!40000 ALTER TABLE `response` DISABLE KEYS */;
/*!40000 ALTER TABLE `response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `type_of_service` varchar(50) NOT NULL,
  `date_initiated` datetime NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_ID_UNIQUE` (`service_id`),
  KEY `fk_service_account_38481_idx` (`account_id`),
  CONSTRAINT `fk_service_account_38481` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `password` varchar(500) NOT NULL,
  `first_name` varchar(15) NOT NULL,
  `last_name` varchar(15) NOT NULL,
  `middle_name` varchar(15) NOT NULL,
  `age` int NOT NULL,
  `gender` varchar(5) NOT NULL,
  `online_status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','P@ssword123','abi','gordon','middleName',10,'F',_binary ''),(2,'abi','P@ssword1','Abi','Matthews','Na',20,'F',_binary '\0'),(3,'abie','P@ssword123','abi','gordon','middleName',10,'F',_binary '\0'),(4,'abir','P@ssword123','abi','gordon','middleName',10,'F',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_session`
--

DROP TABLE IF EXISTS `user_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_session` (
  `user_session_id` int NOT NULL,
  `session_uuid` binary(16) NOT NULL,
  `start` datetime NOT NULL,
  `last_active` datetime NOT NULL,
  `client_ip` varchar(35) NOT NULL,
  `user_id` int NOT NULL,
  `session_status` varchar(10) NOT NULL,
  PRIMARY KEY (`user_session_id`),
  UNIQUE KEY `user_session_id_UNIQUE` (`user_session_id`),
  UNIQUE KEY `session_uuid_UNIQUE` (`session_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_session`
--

LOCK TABLES `user_session` WRITE;
/*!40000 ALTER TABLE `user_session` DISABLE KEYS */;
INSERT INTO `user_session` VALUES (0,_binary '\�\\ֆ7�Jh���W��','2021-05-18 16:21:53','2021-05-18 16:21:53','/127.0.0.1:50058',1,'online');
/*!40000 ALTER TABLE `user_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visit` (
  `visit_id` int NOT NULL AUTO_INCREMENT,
  `date_of_visit` datetime NOT NULL,
  `schedule_date` datetime NOT NULL,
  `complete` bit(1) NOT NULL,
  PRIMARY KEY (`visit_id`),
  UNIQUE KEY `visit_ID_UNIQUE` (`visit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-18 16:57:22
