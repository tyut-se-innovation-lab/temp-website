-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: selab_ry
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `rule_score`
--

DROP TABLE IF EXISTS `rule_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rule_score` (
  `score_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '每个用户的id',
  `scores` int NOT NULL COMMENT '记录被执行人的分数',
  `rule_status` int NOT NULL DEFAULT '1' COMMENT '是否弹出规章制度 0为不弹出 1为弹出 默认为1',
  `operation_status` int DEFAULT '0' COMMENT '近期对其有没有经行操作 0为没有 1为有 默认为0',
  PRIMARY KEY (`score_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人员分数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_score`
--

LOCK TABLES `rule_score` WRITE;
/*!40000 ALTER TABLE `rule_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `rule_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule_log`
--

DROP TABLE IF EXISTS `rule_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rule_log` (
  `rule_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `target_user_id` bigint DEFAULT NULL COMMENT '目标用户id',
  `reason_content` varchar(256) DEFAULT NULL COMMENT '奖惩原因描述',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user_id` bigint DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '受害者是否查看过信息',
  `update_time` timestamp NULL DEFAULT NULL,
  `update_user_id` bigint DEFAULT NULL,
  `image` varchar(256) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`rule_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_log`
--

LOCK TABLES `rule_log` WRITE;
/*!40000 ALTER TABLE `rule_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `rule_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-12 19:38:25
