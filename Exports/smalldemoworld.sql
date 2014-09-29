-- phpMyAdmin SQL Dump
-- version 4.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 09, 2014 at 11:09 PM
-- Server version: 5.5.37-MariaDB-log
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `NationBuilder`
--

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE IF NOT EXISTS `images` (
`id` int(11) NOT NULL,
  `map_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `map_id`, `name`, `url`, `width`, `height`, `created_at`, `updated_at`) VALUES
(1, 1, 'World3.png', '/upload/World3.png', 256, 1152, '2014-09-09 22:50:15', '2014-09-09 22:50:15'),
(2, 1, 'land1nt4.png', '/upload/land1nt4.png', 384, 128, '2014-09-09 22:50:16', '2014-09-09 22:50:16');

-- --------------------------------------------------------

--
-- Table structure for table `layers`
--

CREATE TABLE IF NOT EXISTS `layers` (
`id` int(11) NOT NULL,
  `map_id` int(11) DEFAULT NULL,
  `zindex` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `layers`
--

INSERT INTO `layers` (`id`, `map_id`, `zindex`, `name`, `created_at`, `updated_at`) VALUES
(1, 1, '0', 'Water', '2014-09-09 22:50:16', '2014-09-09 22:50:16'),
(2, 1, '2', 'Coast', '2014-09-09 22:50:16', '2014-09-09 22:50:16'),
(3, 1, '3', 'blabla', '2014-09-09 22:50:16', '2014-09-09 22:50:16'),
(4, 1, '1', 'Land', '2014-09-09 22:50:16', '2014-09-09 22:50:16');

-- --------------------------------------------------------

--
-- Table structure for table `maps`
--

CREATE TABLE IF NOT EXISTS `maps` (
`id` int(11) NOT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `tileWidth` int(11) DEFAULT NULL,
  `tileHeight` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `maps`
--

INSERT INTO `maps` (`id`, `width`, `height`, `tileWidth`, `tileHeight`, `created_at`, `updated_at`) VALUES
(1, 10, 10, 32, 32, '2014-09-09 22:50:13', '2014-09-09 22:50:13');

-- --------------------------------------------------------

--
-- Table structure for table `resources`
--

CREATE TABLE IF NOT EXISTS `resources` (
`id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `terraintype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` (`id`, `created_at`, `updated_at`, `terraintype_id`) VALUES
(1, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 6),
(2, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 6),
(3, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 6),
(4, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(5, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(6, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(7, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(8, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(9, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(10, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(11, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(12, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(13, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(14, '2014-09-09 22:50:14', '2014-09-09 22:50:14', 6),
(15, '2014-09-09 22:50:15', '2014-09-09 22:50:15', 6),
(16, '2014-09-09 22:50:15', '2014-09-09 22:50:15', 6),
(17, '2014-09-09 22:50:15', '2014-09-09 22:50:15', 6),
(18, '2014-09-09 22:50:15', '2014-09-09 22:50:15', 7);

-- --------------------------------------------------------

--
-- Table structure for table `resources_resourcetypes`
--

CREATE TABLE IF NOT EXISTS `resources_resourcetypes` (
`id` int(11) NOT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `resourcetype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=37 ;

--
-- Dumping data for table `resources_resourcetypes`
--

INSERT INTO `resources_resourcetypes` (`id`, `resource_id`, `resourcetype_id`) VALUES
(1, 1, 3),
(2, 1, 4),
(3, 2, 3),
(4, 2, 4),
(5, 3, 3),
(6, 3, 4),
(7, 4, 3),
(8, 4, 4),
(9, 5, 3),
(10, 5, 4),
(11, 6, 3),
(12, 6, 4),
(13, 7, 3),
(14, 7, 4),
(15, 8, 3),
(16, 8, 4),
(17, 9, 3),
(18, 9, 4),
(19, 10, 3),
(20, 10, 4),
(21, 11, 3),
(22, 11, 4),
(23, 12, 3),
(24, 12, 4),
(25, 13, 3),
(26, 13, 4),
(27, 14, 3),
(28, 14, 4),
(29, 15, 3),
(30, 15, 4),
(31, 16, 3),
(32, 16, 4),
(33, 17, 3),
(34, 17, 4),
(35, 18, 3),
(36, 18, 4);

-- --------------------------------------------------------

--
-- Table structure for table `resourcetypes`
--

CREATE TABLE IF NOT EXISTS `resourcetypes` (
`id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regenerating` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `resourcetypes`
--

INSERT INTO `resourcetypes` (`id`, `created_at`, `updated_at`, `name`, `location`, `regenerating`) VALUES
(1, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 'Oil', 'SUBTERRAINIAN', 0),
(2, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 'Gold', 'SUBSURFACE', 0),
(3, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 'Iron', 'EMBEDDEDROCK', 0),
(4, '2014-09-09 22:50:13', '2014-09-09 22:50:13', 'Natural Gas', 'CRUST', 0);

-- --------------------------------------------------------

--
-- Table structure for table `schema_migrations`
--

CREATE TABLE IF NOT EXISTS `schema_migrations` (
  `version` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `schema_migrations`
--

INSERT INTO `schema_migrations` (`version`) VALUES
('20140316104918'),
('20140316114200'),
('20140316114240'),
('20140316124716'),
('20140707231843'),
('20140714233950'),
('20140715214838'),
('20140725225550');

-- --------------------------------------------------------

--
-- Table structure for table `terraintypes`
--

CREATE TABLE IF NOT EXISTS `terraintypes` (
`id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `terraintypes`
--

INSERT INTO `terraintypes` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'WATER', '2014-09-09 22:50:12', '2014-09-09 22:50:12'),
(2, 'SEA', '2014-09-09 22:50:12', '2014-09-09 22:50:12'),
(3, 'FOREST', '2014-09-09 22:50:12', '2014-09-09 22:50:12'),
(4, 'URBAN', '2014-09-09 22:50:12', '2014-09-09 22:50:12'),
(5, 'MOUNTAIN', '2014-09-09 22:50:12', '2014-09-09 22:50:12'),
(6, 'PLAINLAND', '2014-09-09 22:50:13', '2014-09-09 22:50:13'),
(7, 'COAST', '2014-09-09 22:50:13', '2014-09-09 22:50:13'),
(8, 'NONE', '2014-09-09 22:50:13', '2014-09-09 22:50:13');

-- --------------------------------------------------------

--
-- Table structure for table `tiles`
--

CREATE TABLE IF NOT EXISTS `tiles` (
`id` int(11) NOT NULL,
  `gidtag` int(11) DEFAULT NULL,
  `xposition` int(11) DEFAULT NULL,
  `yposition` int(11) DEFAULT NULL,
  `xoffset` int(11) DEFAULT NULL,
  `yoffset` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `layer_id` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=114 ;

--
-- Dumping data for table `tiles`
--

INSERT INTO `tiles` (`id`, `gidtag`, `xposition`, `yposition`, `xoffset`, `yoffset`, `created_at`, `updated_at`, `layer_id`, `image_id`, `resource_id`) VALUES
(1, 290, 1, 5, 1, 0, '2014-09-09 22:50:16', '2014-09-09 22:50:16', 1, 2, NULL),
(2, 290, 6, 5, 1, 0, '2014-09-09 22:50:16', '2014-09-09 22:50:16', 1, 2, NULL),
(3, 290, 0, 0, 1, 0, '2014-09-09 22:50:16', '2014-09-09 22:50:16', 4, 2, NULL),
(4, 290, 1, 0, 1, 0, '2014-09-09 22:50:16', '2014-09-09 22:50:16', 4, 2, NULL),
(5, 290, 2, 0, 1, 0, '2014-09-09 22:50:16', '2014-09-09 22:50:16', 4, 2, NULL),
(6, 290, 3, 0, 1, 0, '2014-09-09 22:50:16', '2014-09-09 22:50:16', 4, 2, NULL),
(7, 290, 4, 0, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(8, 290, 5, 0, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(9, 290, 6, 0, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(10, 290, 7, 0, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(11, 290, 8, 0, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(12, 290, 9, 0, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(13, 290, 0, 1, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(14, 290, 1, 1, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(15, 290, 2, 1, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(16, 290, 3, 1, 1, 0, '2014-09-09 22:50:17', '2014-09-09 22:50:17', 4, 2, NULL),
(17, 290, 4, 1, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(18, 290, 5, 1, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(19, 290, 6, 1, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(20, 290, 7, 1, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(21, 290, 8, 1, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(22, 290, 9, 1, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(23, 290, 0, 2, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(24, 290, 1, 2, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(25, 290, 2, 2, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(26, 290, 3, 2, 1, 0, '2014-09-09 22:50:18', '2014-09-09 22:50:18', 4, 2, NULL),
(27, 290, 4, 2, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(28, 290, 5, 2, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(29, 290, 6, 2, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(30, 290, 7, 2, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(31, 290, 8, 2, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(32, 290, 9, 2, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(33, 290, 0, 3, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(34, 290, 1, 3, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(35, 290, 2, 3, 1, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 2, NULL),
(36, 1, 3, 3, 0, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 1, 1),
(37, 1, 4, 3, 0, 0, '2014-09-09 22:50:19', '2014-09-09 22:50:19', 4, 1, 2),
(38, 1, 5, 3, 0, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 1, 3),
(39, 290, 6, 3, 1, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 2, NULL),
(40, 290, 7, 3, 1, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 2, NULL),
(41, 290, 8, 3, 1, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 2, NULL),
(42, 290, 9, 3, 1, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 2, NULL),
(43, 290, 0, 4, 1, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 2, NULL),
(44, 290, 1, 4, 1, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 2, NULL),
(45, 1, 2, 4, 0, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 1, 4),
(46, 1, 3, 4, 0, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 1, 5),
(47, 1, 4, 4, 0, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 1, 6),
(48, 1, 5, 4, 0, 0, '2014-09-09 22:50:20', '2014-09-09 22:50:20', 4, 1, 7),
(49, 1, 6, 4, 0, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 1, 8),
(50, 290, 7, 4, 1, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 2, NULL),
(51, 290, 8, 4, 1, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 2, NULL),
(52, 290, 9, 4, 1, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 2, NULL),
(53, 290, 0, 5, 1, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 2, NULL),
(54, 1, 2, 5, 0, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 1, 9),
(55, 1, 3, 5, 0, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 1, 10),
(56, 1, 4, 5, 0, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 1, 11),
(57, 1, 5, 5, 0, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 1, 12),
(58, 1, 6, 5, 0, 0, '2014-09-09 22:50:21', '2014-09-09 22:50:21', 4, 1, 13),
(59, 290, 7, 5, 1, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 2, NULL),
(60, 290, 8, 5, 1, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 2, NULL),
(61, 290, 9, 5, 1, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 2, NULL),
(62, 290, 0, 6, 1, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 2, NULL),
(63, 290, 1, 6, 1, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 2, NULL),
(64, 1, 2, 6, 0, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 1, 14),
(65, 1, 3, 6, 0, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 1, 15),
(66, 1, 4, 6, 0, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 1, 16),
(67, 1, 5, 6, 0, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:22', 4, 1, 17),
(68, 290, 6, 6, 1, 0, '2014-09-09 22:50:22', '2014-09-09 22:50:23', 4, 2, NULL),
(69, 290, 7, 6, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(70, 290, 8, 6, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(71, 290, 9, 6, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(72, 290, 0, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(73, 290, 1, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(74, 290, 2, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(75, 290, 3, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(76, 290, 4, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(77, 290, 5, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(78, 290, 6, 7, 1, 0, '2014-09-09 22:50:23', '2014-09-09 22:50:23', 4, 2, NULL),
(79, 290, 7, 7, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(80, 290, 8, 7, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(81, 290, 9, 7, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(82, 290, 0, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(83, 290, 1, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(84, 290, 2, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(85, 290, 3, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(86, 290, 4, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(87, 290, 5, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(88, 290, 6, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(89, 290, 7, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:24', 4, 2, NULL),
(90, 290, 8, 8, 1, 0, '2014-09-09 22:50:24', '2014-09-09 22:50:25', 4, 2, NULL),
(91, 290, 9, 8, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(92, 290, 0, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(93, 290, 1, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(94, 290, 2, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(95, 290, 3, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(96, 290, 4, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(97, 290, 5, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(98, 290, 6, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(99, 290, 7, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(100, 290, 8, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:25', 4, 2, NULL),
(101, 290, 9, 9, 1, 0, '2014-09-09 22:50:25', '2014-09-09 22:50:26', 4, 2, NULL),
(102, 301, 3, 3, 0, 1, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(103, 302, 4, 3, 1, 1, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(104, 303, 5, 3, 2, 1, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(105, 301, 2, 4, 0, 1, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(106, 303, 6, 4, 2, 1, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(107, 313, 2, 5, 0, 2, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(108, 327, 6, 5, 2, 3, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(109, 325, 2, 6, 0, 3, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(110, 326, 3, 6, 1, 3, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(111, 326, 4, 6, 1, 3, '2014-09-09 22:50:26', '2014-09-09 22:50:26', 2, 2, NULL),
(112, 327, 5, 6, 2, 3, '2014-09-09 22:50:27', '2014-09-09 22:50:27', 2, 2, NULL),
(113, 15, 4, 4, 6, 1, '2014-09-09 22:50:27', '2014-09-09 22:50:27', 3, 1, 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `images`
--
ALTER TABLE `images`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `layers`
--
ALTER TABLE `layers`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `maps`
--
ALTER TABLE `maps`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `resources`
--
ALTER TABLE `resources`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `resources_resourcetypes`
--
ALTER TABLE `resources_resourcetypes`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `resourcetypes`
--
ALTER TABLE `resourcetypes`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schema_migrations`
--
ALTER TABLE `schema_migrations`
 ADD UNIQUE KEY `unique_schema_migrations` (`version`);

--
-- Indexes for table `terraintypes`
--
ALTER TABLE `terraintypes`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tiles`
--
ALTER TABLE `tiles`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `layers`
--
ALTER TABLE `layers`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `maps`
--
ALTER TABLE `maps`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `resources`
--
ALTER TABLE `resources`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `resources_resourcetypes`
--
ALTER TABLE `resources_resourcetypes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `resourcetypes`
--
ALTER TABLE `resourcetypes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `terraintypes`
--
ALTER TABLE `terraintypes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tiles`
--
ALTER TABLE `tiles`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=114;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
