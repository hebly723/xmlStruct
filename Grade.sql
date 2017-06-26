-- MySQL dump 10.13  Distrib 5.5.50, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: Grade
-- ------------------------------------------------------
-- Server version       5.5.50-0ubuntu0.14.04.1-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(11) NOT NULL,
  `password` varchar(11) DEFAULT '888888',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('01','2'),('03','888888'),('06','888888'),('07','888888'),('08','888888'),('09','888888'),('10','888888'),('11','888888'),('12','888888'),('13','888888'),('14','888888'),('15','888888'),('16','888888'),('17','888888'),('18','888888'),('19','888888'),('20','888888'),('21','888888'),('22','888888'),('23','888888'),('24','888888'),('25','888888'),('27','888888'),('28','888888'),('29','888888'),('35','888888');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_grade`
--

DROP TABLE IF EXISTS `user_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_grade` (
  `id` varchar(11) NOT NULL,
  `grade` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_grade`
--

LOCK TABLES `user_grade` WRITE;
/*!40000 ALTER TABLE `user_grade` DISABLE KEYS */;
INSERT INTO `user_grade` VALUES ('01',92),('03',90),('06',99),('07',10),('08',0),('09',60),('10',22),('11',75),('12',66),('13',82),('14',74),('15',81),('16',86),('17',77),('18',65),('19',60),('20',67),('21',72),('22',89),('23',72),('24',88),('25',60),('27',90),('28',63),('29',86),('35',86);
/*!40000 ALTER TABLE `user_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` varchar(11) NOT NULL,
  `username` varchar(11) NOT NULL,
  `address` varchar(11) NOT NULL,
  `sex` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('01','耿焱','北京',0),('03','徐风','江西',0),('06','伊电瞳','青海',1),('07','辗迟','山西',0),('08','鸣人','河南',0),('09','路明非','上海',0),('10','木刀之龙','广东',0),('11','道莲','贵州',0),('12','道润','贵州',1),('13','梧桐','西藏',0),('14','雷欧力','新疆',0),('15','云谷','天津',0),('16','比丝姬','海 南',1),('17','飞段','北京',0),('18','角都','青海',0),('19','小滴','吉林',1),('20','土门','山西',0),('21','风子','山西',1),('22','万太','北京',0),('23','游不动','北京',0),('24','辰月','北京',1),('25','刘意','云南',0),('27','鹿丸','重庆',0),('28','李洛克','山西',0),('29','高月','河南',1),('35','wy','北京',0);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-26 11:23:05