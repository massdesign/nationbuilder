-- MySQL dump 10.16  Distrib 10.1.11-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: NationBuilder
-- ------------------------------------------------------
-- Server version	10.1.11-MariaDB-log

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
-- Table structure for table `buildings`
--

DROP TABLE IF EXISTS `buildings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buildings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `warehouse_id` int(11) DEFAULT NULL,
  `power_relay_station_id` int(11) DEFAULT NULL,
  `military_base_id` int(11) DEFAULT NULL,
  `game_entity_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildings`
--

LOCK TABLES `buildings` WRITE;
/*!40000 ALTER TABLE `buildings` DISABLE KEYS */;
INSERT INTO `buildings` VALUES (1,'Standard warehouse',1,NULL,NULL,NULL,'2016-02-10 15:51:40','2016-02-10 15:51:40');
/*!40000 ALTER TABLE `buildings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `population` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'2016-02-10 15:51:40','2016-02-10 15:51:40',300),(2,'2016-02-10 15:51:40','2016-02-10 15:51:40',900),(3,'2016-02-10 15:51:40','2016-02-10 15:51:40',12300);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claims`
--

DROP TABLE IF EXISTS `claims`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `claims` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tile_id` int(11) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claims`
--

LOCK TABLES `claims` WRITE;
/*!40000 ALTER TABLE `claims` DISABLE KEYS */;
/*!40000 ALTER TABLE `claims` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencies`
--

DROP TABLE IF EXISTS `currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `convertable` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'Dollar',NULL,'1','2016-02-10 15:51:39','2016-02-10 15:51:39');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energy_building_types`
--

DROP TABLE IF EXISTS `energy_building_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `energy_building_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `energySource` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `powerOutput` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energy_building_types`
--

LOCK TABLES `energy_building_types` WRITE;
/*!40000 ALTER TABLE `energy_building_types` DISABLE KEYS */;
INSERT INTO `energy_building_types` VALUES (1,'Nuclear',400,'Nuclear power plant MK 1','2016-02-10 15:51:39','2016-02-10 15:51:39'),(2,'Nuclear',1000,'Nuclear power plant MK 2','2016-02-10 15:51:39','2016-02-10 15:51:39'),(3,'Nuclear',3550,'Nuclear power plant MK 3','2016-02-10 15:51:39','2016-02-10 15:51:39'),(4,'Nuclear',6520,'Nuclear power plant MK 4','2016-02-10 15:51:39','2016-02-10 15:51:39'),(5,'Nuclear',7100,'Nuclear power plant MK 5','2016-02-10 15:51:39','2016-02-10 15:51:39'),(6,'Natural gas',30,'Gas Turbine Generator MK 1','2016-02-10 15:51:39','2016-02-10 15:51:39'),(7,'Natural gas',100,'Gas Turbine Generator MK 2','2016-02-10 15:51:39','2016-02-10 15:51:39'),(8,'Natural gas',250,'Gas Turbine Generator MK 3','2016-02-10 15:51:39','2016-02-10 15:51:39'),(9,'Natural gas',450,'Gas Turbine Generator MK 4','2016-02-10 15:51:39','2016-02-10 15:51:39'),(10,'Natural gas',682,'Gas Turbine Generator MK 5','2016-02-10 15:51:39','2016-02-10 15:51:39'),(11,'Natural gas',782,'Gas Turbine Generator MK 6','2016-02-10 15:51:39','2016-02-10 15:51:39'),(12,'Coal',800,'Coal Power Station MK 1','2016-02-10 15:51:39','2016-02-10 15:51:39'),(13,'Coal',1600,'Coal Power Station MK 2','2016-02-10 15:51:39','2016-02-10 15:51:39'),(14,'Coal',2000,'Coal Power Station MK 3','2016-02-10 15:51:39','2016-02-10 15:51:39'),(15,'Coal',4000,'Coal Power Station MK 4','2016-02-10 15:51:40','2016-02-10 15:51:40'),(16,'Coal',5600,'Coal Power Station MK 5','2016-02-10 15:51:40','2016-02-10 15:51:40'),(17,'Biomass',100,'Biomass Power Station MK 1','2016-02-10 15:51:40','2016-02-10 15:51:40'),(18,'Biomass',225,'Biomass Power Station MK 2','2016-02-10 15:51:40','2016-02-10 15:51:40'),(19,'Biomass',450,'Biomass Power Station MK 3','2016-02-10 15:51:40','2016-02-10 15:51:40'),(20,'Biomass',600,'Biomass Power Station MK 4','2016-02-10 15:51:40','2016-02-10 15:51:40'),(21,'Biomass',760,'Biomass Power Station MK 5','2016-02-10 15:51:40','2016-02-10 15:51:40'),(22,NULL,NULL,'IjsselCentrale','2016-02-10 15:51:40','2016-02-10 15:51:40'),(23,NULL,NULL,'MoerdijkCentrale','2016-02-10 15:51:40','2016-02-10 15:51:40');
/*!40000 ALTER TABLE `energy_building_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energy_buildings`
--

DROP TABLE IF EXISTS `energy_buildings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `energy_buildings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `energy_building_type_id` int(11) DEFAULT NULL,
  `tile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energy_buildings`
--

LOCK TABLES `energy_buildings` WRITE;
/*!40000 ALTER TABLE `energy_buildings` DISABLE KEYS */;
/*!40000 ALTER TABLE `energy_buildings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_entities`
--

DROP TABLE IF EXISTS `game_entities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_entities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `node_type_id` int(11) DEFAULT NULL,
  `militarystronghold_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_entities`
--

LOCK TABLES `game_entities` WRITE;
/*!40000 ALTER TABLE `game_entities` DISABLE KEYS */;
INSERT INTO `game_entities` VALUES (1,'Utrecht',1,NULL,NULL,'2016-02-10 15:51:40','2016-02-10 15:51:40'),(2,'Paris',2,NULL,NULL,'2016-02-10 15:51:40','2016-02-10 15:51:40'),(3,'Sankt Augustin',3,NULL,NULL,'2016-02-10 15:51:40','2016-02-10 15:51:40');
/*!40000 ALTER TABLE `game_entities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `map_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,1,'World3.png','/upload/World3.png',256,1152,'2016-02-10 15:51:47','2016-02-10 15:51:47'),(2,1,'land1nt4.png','/upload/land1nt4.png',384,128,'2016-02-10 15:51:47','2016-02-10 15:51:47');
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layers`
--

DROP TABLE IF EXISTS `layers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `layers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `map_id` int(11) DEFAULT NULL,
  `zindex` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layers`
--

LOCK TABLES `layers` WRITE;
/*!40000 ALTER TABLE `layers` DISABLE KEYS */;
INSERT INTO `layers` VALUES (1,1,'1','Land','2016-02-10 15:51:47','2016-02-10 15:51:47'),(2,1,'2','Coast','2016-02-10 15:51:47','2016-02-10 15:51:47'),(3,1,'3','Mountains','2016-02-10 15:51:47','2016-02-10 15:51:47'),(4,1,'0','Water','2016-02-10 15:51:47','2016-02-10 15:51:47');
/*!40000 ALTER TABLE `layers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maps`
--

DROP TABLE IF EXISTS `maps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `tileWidth` int(11) DEFAULT NULL,
  `tileHeight` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maps`
--

LOCK TABLES `maps` WRITE;
/*!40000 ALTER TABLE `maps` DISABLE KEYS */;
INSERT INTO `maps` VALUES (1,10,10,32,32,'2016-02-10 15:51:46','2016-02-10 15:51:46');
/*!40000 ALTER TABLE `maps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `militarystrongholds`
--

DROP TABLE IF EXISTS `militarystrongholds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `militarystrongholds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `health` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `militarystrongholds`
--

LOCK TABLES `militarystrongholds` WRITE;
/*!40000 ALTER TABLE `militarystrongholds` DISABLE KEYS */;
/*!40000 ALTER TABLE `militarystrongholds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `militarystrongholds_tiles`
--

DROP TABLE IF EXISTS `militarystrongholds_tiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `militarystrongholds_tiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tile_id` int(11) DEFAULT NULL,
  `militarystronghold_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `militarystrongholds_tiles`
--

LOCK TABLES `militarystrongholds_tiles` WRITE;
/*!40000 ALTER TABLE `militarystrongholds_tiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `militarystrongholds_tiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node_types`
--

DROP TABLE IF EXISTS `node_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `node_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `destroyable` tinyint(1) DEFAULT NULL,
  `power_grid_node_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_types`
--

LOCK TABLES `node_types` WRITE;
/*!40000 ALTER TABLE `node_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `node_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power_connections`
--

DROP TABLE IF EXISTS `power_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power_connections` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `load` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `power_grid_node_a_id` int(11) DEFAULT NULL,
  `power_grid_node_b_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power_connections`
--

LOCK TABLES `power_connections` WRITE;
/*!40000 ALTER TABLE `power_connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `power_connections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power_grid_nodes`
--

DROP TABLE IF EXISTS `power_grid_nodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power_grid_nodes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `power_relay_station_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power_grid_nodes`
--

LOCK TABLES `power_grid_nodes` WRITE;
/*!40000 ALTER TABLE `power_grid_nodes` DISABLE KEYS */;
/*!40000 ALTER TABLE `power_grid_nodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power_relay_station_types`
--

DROP TABLE IF EXISTS `power_relay_station_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power_relay_station_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power_relay_station_types`
--

LOCK TABLES `power_relay_station_types` WRITE;
/*!40000 ALTER TABLE `power_relay_station_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `power_relay_station_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power_relay_stations`
--

DROP TABLE IF EXISTS `power_relay_stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power_relay_stations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `power_relay_station_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power_relay_stations`
--

LOCK TABLES `power_relay_stations` WRITE;
/*!40000 ALTER TABLE `power_relay_stations` DISABLE KEYS */;
/*!40000 ALTER TABLE `power_relay_stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `tile_id` int(11) DEFAULT NULL,
  `resourcetype_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'2016-02-10 15:51:41','2016-02-10 15:51:47',1,1),(2,'2016-02-10 15:51:41','2016-02-10 15:51:47',2,1),(3,'2016-02-10 15:51:41','2016-02-10 15:51:47',3,1),(4,'2016-02-10 15:51:41','2016-02-10 15:51:47',4,1),(5,'2016-02-10 15:51:41','2016-02-10 15:51:47',5,1),(6,'2016-02-10 15:51:41','2016-02-10 15:51:47',6,1),(7,'2016-02-10 15:51:41','2016-02-10 15:51:47',7,1),(8,'2016-02-10 15:51:41','2016-02-10 15:51:47',8,1),(9,'2016-02-10 15:51:41','2016-02-10 15:51:47',9,1),(10,'2016-02-10 15:51:41','2016-02-10 15:51:47',10,1),(11,'2016-02-10 15:51:41','2016-02-10 15:51:48',11,1),(12,'2016-02-10 15:51:41','2016-02-10 15:51:48',12,1),(13,'2016-02-10 15:51:42','2016-02-10 15:51:48',13,1),(14,'2016-02-10 15:51:42','2016-02-10 15:51:48',14,1),(15,'2016-02-10 15:51:42','2016-02-10 15:51:48',15,1),(16,'2016-02-10 15:51:42','2016-02-10 15:51:48',16,1),(17,'2016-02-10 15:51:42','2016-02-10 15:51:48',17,1),(18,'2016-02-10 15:51:42','2016-02-10 15:51:48',18,1),(19,'2016-02-10 15:51:42','2016-02-10 15:51:48',19,1),(20,'2016-02-10 15:51:42','2016-02-10 15:51:48',20,1),(21,'2016-02-10 15:51:42','2016-02-10 15:51:48',21,1),(22,'2016-02-10 15:51:42','2016-02-10 15:51:48',22,1),(23,'2016-02-10 15:51:42','2016-02-10 15:51:48',23,1),(24,'2016-02-10 15:51:42','2016-02-10 15:51:48',24,1),(25,'2016-02-10 15:51:42','2016-02-10 15:51:48',25,1),(26,'2016-02-10 15:51:42','2016-02-10 15:51:48',26,1),(27,'2016-02-10 15:51:42','2016-02-10 15:51:49',27,1),(28,'2016-02-10 15:51:42','2016-02-10 15:51:49',28,1),(29,'2016-02-10 15:51:42','2016-02-10 15:51:49',29,1),(30,'2016-02-10 15:51:42','2016-02-10 15:51:49',30,1),(31,'2016-02-10 15:51:42','2016-02-10 15:51:49',31,1),(32,'2016-02-10 15:51:42','2016-02-10 15:51:49',32,1),(33,'2016-02-10 15:51:43','2016-02-10 15:51:49',33,1),(34,'2016-02-10 15:51:43','2016-02-10 15:51:49',34,1),(35,'2016-02-10 15:51:43','2016-02-10 15:51:49',35,1),(36,'2016-02-10 15:51:43','2016-02-10 15:51:49',36,1),(37,'2016-02-10 15:51:43','2016-02-10 15:51:49',37,1),(38,'2016-02-10 15:51:43','2016-02-10 15:51:49',38,1),(39,'2016-02-10 15:51:43','2016-02-10 15:51:49',39,1),(40,'2016-02-10 15:51:43','2016-02-10 15:51:49',40,1),(41,'2016-02-10 15:51:43','2016-02-10 15:51:49',41,1),(42,'2016-02-10 15:51:43','2016-02-10 15:51:49',42,1),(43,'2016-02-10 15:51:43','2016-02-10 15:51:49',43,1),(44,'2016-02-10 15:51:43','2016-02-10 15:51:50',44,1),(45,'2016-02-10 15:51:43','2016-02-10 15:51:50',45,1),(46,'2016-02-10 15:51:43','2016-02-10 15:51:50',46,1),(47,'2016-02-10 15:51:43','2016-02-10 15:51:50',47,1),(48,'2016-02-10 15:51:43','2016-02-10 15:51:50',48,1),(49,'2016-02-10 15:51:43','2016-02-10 15:51:50',49,1),(50,'2016-02-10 15:51:43','2016-02-10 15:51:50',50,1),(51,'2016-02-10 15:51:43','2016-02-10 15:51:50',51,1),(52,'2016-02-10 15:51:43','2016-02-10 15:51:50',52,1),(53,'2016-02-10 15:51:43','2016-02-10 15:51:50',53,1),(54,'2016-02-10 15:51:44','2016-02-10 15:51:50',54,1),(55,'2016-02-10 15:51:44','2016-02-10 15:51:50',55,1),(56,'2016-02-10 15:51:44','2016-02-10 15:51:50',56,1),(57,'2016-02-10 15:51:44','2016-02-10 15:51:50',57,1),(58,'2016-02-10 15:51:44','2016-02-10 15:51:50',58,1),(59,'2016-02-10 15:51:44','2016-02-10 15:51:50',59,1),(60,'2016-02-10 15:51:44','2016-02-10 15:51:51',60,1),(61,'2016-02-10 15:51:44','2016-02-10 15:51:51',61,1),(62,'2016-02-10 15:51:44','2016-02-10 15:51:51',62,1),(63,'2016-02-10 15:51:44','2016-02-10 15:51:51',63,1),(64,'2016-02-10 15:51:44','2016-02-10 15:51:51',64,1),(65,'2016-02-10 15:51:44','2016-02-10 15:51:51',65,1),(66,'2016-02-10 15:51:44','2016-02-10 15:51:51',66,1),(67,'2016-02-10 15:51:44','2016-02-10 15:51:51',67,1),(68,'2016-02-10 15:51:44','2016-02-10 15:51:51',68,1),(69,'2016-02-10 15:51:44','2016-02-10 15:51:51',69,1),(70,'2016-02-10 15:51:44','2016-02-10 15:51:51',70,1),(71,'2016-02-10 15:51:44','2016-02-10 15:51:51',71,1),(72,'2016-02-10 15:51:44','2016-02-10 15:51:51',72,1),(73,'2016-02-10 15:51:44','2016-02-10 15:51:51',73,1),(74,'2016-02-10 15:51:45','2016-02-10 15:51:51',74,1),(75,'2016-02-10 15:51:45','2016-02-10 15:51:51',75,1),(76,'2016-02-10 15:51:45','2016-02-10 15:51:52',76,1),(77,'2016-02-10 15:51:45','2016-02-10 15:51:52',77,1),(78,'2016-02-10 15:51:45','2016-02-10 15:51:52',78,1),(79,'2016-02-10 15:51:45','2016-02-10 15:51:52',79,1),(80,'2016-02-10 15:51:45','2016-02-10 15:51:52',80,1),(81,'2016-02-10 15:51:45','2016-02-10 15:51:52',81,1),(82,'2016-02-10 15:51:45','2016-02-10 15:51:52',82,1),(83,'2016-02-10 15:51:45','2016-02-10 15:51:52',83,1),(84,'2016-02-10 15:51:45','2016-02-10 15:51:52',84,1),(85,'2016-02-10 15:51:45','2016-02-10 15:51:52',85,1),(86,'2016-02-10 15:51:45','2016-02-10 15:51:52',86,1),(87,'2016-02-10 15:51:45','2016-02-10 15:51:52',87,1),(88,'2016-02-10 15:51:45','2016-02-10 15:51:52',88,1),(89,'2016-02-10 15:51:45','2016-02-10 15:51:52',89,1),(90,'2016-02-10 15:51:45','2016-02-10 15:51:52',90,1),(91,'2016-02-10 15:51:45','2016-02-10 15:51:52',91,1),(92,'2016-02-10 15:51:45','2016-02-10 15:51:52',92,1),(93,'2016-02-10 15:51:45','2016-02-10 15:51:53',93,1),(94,'2016-02-10 15:51:45','2016-02-10 15:51:53',94,1),(95,'2016-02-10 15:51:46','2016-02-10 15:51:53',95,1),(96,'2016-02-10 15:51:46','2016-02-10 15:51:53',96,1),(97,'2016-02-10 15:51:46','2016-02-10 15:51:53',97,1),(98,'2016-02-10 15:51:46','2016-02-10 15:51:53',98,1),(99,'2016-02-10 15:51:46','2016-02-10 15:51:53',99,1),(100,'2016-02-10 15:51:46','2016-02-10 15:51:53',100,1),(101,'2016-02-10 15:51:46','2016-02-10 15:51:53',101,1),(102,'2016-02-10 15:51:46','2016-02-10 15:51:53',102,1),(103,'2016-02-10 15:51:46','2016-02-10 15:51:53',103,1),(104,'2016-02-10 15:51:46','2016-02-10 15:51:53',104,1),(105,'2016-02-10 15:51:46','2016-02-10 15:51:53',105,1),(106,'2016-02-10 15:51:46','2016-02-10 15:51:53',106,1),(107,'2016-02-10 15:51:46','2016-02-10 15:51:53',107,1),(108,'2016-02-10 15:51:46','2016-02-10 15:51:53',108,1),(109,'2016-02-10 15:51:46','2016-02-10 15:51:54',109,1),(110,'2016-02-10 15:51:46','2016-02-10 15:51:54',110,1),(111,'2016-02-10 15:51:46','2016-02-10 15:51:54',111,1),(112,'2016-02-10 15:51:46','2016-02-10 15:51:54',112,1),(113,'2016-02-10 15:51:46','2016-02-10 15:51:54',113,1),(114,'2016-02-10 15:51:46','2016-02-10 15:51:54',114,1);
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resourcetypes`
--

DROP TABLE IF EXISTS `resourcetypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resourcetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regenerating` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourcetypes`
--

LOCK TABLES `resourcetypes` WRITE;
/*!40000 ALTER TABLE `resourcetypes` DISABLE KEYS */;
INSERT INTO `resourcetypes` VALUES (1,'2016-02-10 15:51:41','2016-02-10 15:51:41','Oil','SUBTERRAINIAN',0),(2,'2016-02-10 15:51:41','2016-02-10 15:51:41','Gold','SUBSURFACE',0),(3,'2016-02-10 15:51:41','2016-02-10 15:51:41','Iron','EMBEDDEDROCK',0),(4,'2016-02-10 15:51:41','2016-02-10 15:51:41','Natural Gas','CRUST',0);
/*!40000 ALTER TABLE `resourcetypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schema_migrations`
--

DROP TABLE IF EXISTS `schema_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema_migrations` (
  `version` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `unique_schema_migrations` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schema_migrations`
--

LOCK TABLES `schema_migrations` WRITE;
/*!40000 ALTER TABLE `schema_migrations` DISABLE KEYS */;
INSERT INTO `schema_migrations` VALUES ('20140316104918'),('20140316114200'),('20140316114240'),('20140316124716'),('20140707231843'),('20140714233950'),('20140715214838'),('20140923211844'),('20140923213955'),('20140930153927'),('20141007133707'),('20141007134946'),('20141102213014'),('20150302100318'),('20150302141909'),('20150302165155'),('20150302165417'),('20150319214854'),('20150320000857'),('20160107083030'),('20160111174610'),('20160114082025'),('20160119172211'),('20160120123638');
/*!40000 ALTER TABLE `schema_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `motto` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `currency_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,'Kill em all!','Soviet Netherlands','1','2016-02-10 15:51:39','2016-02-10 15:51:39','1'),(2,'Kill em all!','New Germany','1','2016-02-10 15:51:39','2016-02-10 15:51:39','2');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terraintypes`
--

DROP TABLE IF EXISTS `terraintypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terraintypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terraintypes`
--

LOCK TABLES `terraintypes` WRITE;
/*!40000 ALTER TABLE `terraintypes` DISABLE KEYS */;
INSERT INTO `terraintypes` VALUES (1,'WATER','2016-02-10 15:51:40','2016-02-10 15:51:40'),(2,'SEA','2016-02-10 15:51:40','2016-02-10 15:51:40'),(3,'INLANDCOAST','2016-02-10 15:51:40','2016-02-10 15:51:40'),(4,'INLANDSEA','2016-02-10 15:51:40','2016-02-10 15:51:40'),(5,'RIVER','2016-02-10 15:51:40','2016-02-10 15:51:40'),(6,'FOREST','2016-02-10 15:51:40','2016-02-10 15:51:40'),(7,'URBAN','2016-02-10 15:51:40','2016-02-10 15:51:40'),(8,'MOUNTAIN','2016-02-10 15:51:41','2016-02-10 15:51:41'),(9,'PLAINLAND','2016-02-10 15:51:41','2016-02-10 15:51:41'),(10,'COAST','2016-02-10 15:51:41','2016-02-10 15:51:41'),(11,'NONE','2016-02-10 15:51:41','2016-02-10 15:51:41');
/*!40000 ALTER TABLE `terraintypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiles`
--

DROP TABLE IF EXISTS `tiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gidtag` int(11) DEFAULT NULL,
  `xposition` int(11) DEFAULT NULL,
  `yposition` int(11) DEFAULT NULL,
  `xoffset` int(11) DEFAULT NULL,
  `yoffset` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `layer_id` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL,
  `terraintype_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiles`
--

LOCK TABLES `tiles` WRITE;
/*!40000 ALTER TABLE `tiles` DISABLE KEYS */;
INSERT INTO `tiles` VALUES (1,14,4,4,5,1,'2016-02-10 15:51:47','2016-02-10 15:51:47',4,1,8),(2,290,1,5,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',4,2,2),(3,290,6,5,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',4,2,2),(4,290,0,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(5,290,1,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(6,290,2,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(7,290,3,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(8,290,4,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(9,290,5,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(10,290,6,0,1,0,'2016-02-10 15:51:47','2016-02-10 15:51:47',1,2,2),(11,290,7,0,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(12,290,8,0,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(13,290,9,0,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(14,290,0,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(15,290,1,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(16,290,2,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(17,290,3,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(18,290,4,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(19,290,5,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(20,290,6,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(21,290,7,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(22,290,8,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(23,290,9,1,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(24,290,0,2,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(25,290,1,2,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(26,290,2,2,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(27,290,3,2,1,0,'2016-02-10 15:51:48','2016-02-10 15:51:48',1,2,2),(28,290,4,2,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(29,290,5,2,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(30,290,6,2,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(31,290,7,2,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(32,290,8,2,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(33,290,9,2,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(34,290,0,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(35,290,1,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(36,290,2,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(37,1,3,3,0,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,1,9),(38,1,4,3,0,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,1,9),(39,1,5,3,0,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,1,9),(40,290,6,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(41,290,7,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(42,290,8,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(43,290,9,3,1,0,'2016-02-10 15:51:49','2016-02-10 15:51:49',1,2,2),(44,290,0,4,1,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,2,2),(45,290,1,4,1,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,2,2),(46,1,2,4,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(47,1,3,4,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(48,1,4,4,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(49,1,5,4,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(50,1,6,4,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(51,290,7,4,1,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,2,2),(52,290,8,4,1,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,2,2),(53,290,9,4,1,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,2,2),(54,290,0,5,1,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,2,2),(55,1,2,5,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(56,1,3,5,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(57,1,4,5,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(58,1,5,5,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(59,1,6,5,0,0,'2016-02-10 15:51:50','2016-02-10 15:51:50',1,1,9),(60,290,7,5,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(61,290,8,5,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(62,290,9,5,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(63,290,0,6,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(64,290,1,6,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(65,1,2,6,0,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,1,9),(66,1,3,6,0,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,1,9),(67,1,4,6,0,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,1,9),(68,1,5,6,0,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,1,9),(69,290,6,6,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(70,290,7,6,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(71,290,8,6,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(72,290,9,6,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(73,290,0,7,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(74,290,1,7,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(75,290,2,7,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:51',1,2,2),(76,290,3,7,1,0,'2016-02-10 15:51:51','2016-02-10 15:51:52',1,2,2),(77,290,4,7,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(78,290,5,7,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(79,290,6,7,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(80,290,7,7,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(81,290,8,7,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(82,290,9,7,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(83,290,0,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(84,290,1,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(85,290,2,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(86,290,3,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(87,290,4,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(88,290,5,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(89,290,6,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(90,290,7,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(91,290,8,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(92,290,9,8,1,0,'2016-02-10 15:51:52','2016-02-10 15:51:52',1,2,2),(93,290,0,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(94,290,1,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(95,290,2,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(96,290,3,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(97,290,4,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(98,290,5,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(99,290,6,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(100,290,7,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(101,290,8,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(102,290,9,9,1,0,'2016-02-10 15:51:53','2016-02-10 15:51:53',1,2,2),(103,301,3,3,0,1,'2016-02-10 15:51:53','2016-02-10 15:51:53',2,2,10),(104,302,4,3,1,1,'2016-02-10 15:51:53','2016-02-10 15:51:53',2,2,10),(105,303,5,3,2,1,'2016-02-10 15:51:53','2016-02-10 15:51:53',2,2,10),(106,301,2,4,0,1,'2016-02-10 15:51:53','2016-02-10 15:51:53',2,2,10),(107,303,6,4,2,1,'2016-02-10 15:51:53','2016-02-10 15:51:53',2,2,10),(108,313,2,5,0,2,'2016-02-10 15:51:53','2016-02-10 15:51:53',2,2,10),(109,327,6,5,2,3,'2016-02-10 15:51:54','2016-02-10 15:51:54',2,2,10),(110,325,2,6,0,3,'2016-02-10 15:51:54','2016-02-10 15:51:54',2,2,10),(111,326,3,6,1,3,'2016-02-10 15:51:54','2016-02-10 15:51:54',2,2,10),(112,326,4,6,1,3,'2016-02-10 15:51:54','2016-02-10 15:51:54',2,2,10),(113,327,5,6,2,3,'2016-02-10 15:51:54','2016-02-10 15:51:54',2,2,10),(114,14,4,4,5,1,'2016-02-10 15:51:54','2016-02-10 15:51:54',3,1,8);
/*!40000 ALTER TABLE `tiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `screenname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `loginname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `passwordhash` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `registerdate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailadres` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Henk de tester','test',NULL,'2016-02-10','henk@henk.nl','2016-02-10 15:51:39','2016-02-10 15:51:39'),(2,'Patrick de player','patrick',NULL,'2016-02-10','henk@henk.nl','2016-02-10 15:51:39','2016-02-10 15:51:39');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouses`
--

DROP TABLE IF EXISTS `warehouses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouses`
--

LOCK TABLES `warehouses` WRITE;
/*!40000 ALTER TABLE `warehouses` DISABLE KEYS */;
INSERT INTO `warehouses` VALUES (1,'2016-02-10 15:51:40','2016-02-10 15:51:40');
/*!40000 ALTER TABLE `warehouses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 16:55:36
