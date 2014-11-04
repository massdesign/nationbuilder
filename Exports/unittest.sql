-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 04, 2014 at 04:44 PM
-- Server version: 10.0.14-MariaDB-log
-- PHP Version: 5.6.1

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
-- Table structure for table `currencies`
--

CREATE TABLE IF NOT EXISTS `currencies` (
`id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `convertable` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `currencies`
--

INSERT INTO `currencies` (`id`, `name`, `status`, `convertable`, `created_at`, `updated_at`) VALUES
(1, 'Dollar', NULL, '1', '2014-11-04 16:43:41', '2014-11-04 16:43:41');

-- --------------------------------------------------------

--
-- Table structure for table `energy_building_types`
--

CREATE TABLE IF NOT EXISTS `energy_building_types` (
`id` int(11) NOT NULL,
  `energySource` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `powerOutput` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `energy_buildings`
--

CREATE TABLE IF NOT EXISTS `energy_buildings` (
`id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `energy_building_type_id` int(11) DEFAULT NULL,
  `tile_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
(1, 1, 'World3.png', '/upload/World3.png', 256, 1152, '2014-11-04 16:43:43', '2014-11-04 16:43:43'),
(2, 1, 'land1nt4.png', '/upload/land1nt4.png', 384, 128, '2014-11-04 16:43:44', '2014-11-04 16:43:44');

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
(1, 1, '0', 'Water', '2014-11-04 16:43:44', '2014-11-04 16:43:44'),
(2, 1, '2', 'Coast', '2014-11-04 16:43:44', '2014-11-04 16:43:44'),
(3, 1, '1', 'Land', '2014-11-04 16:43:44', '2014-11-04 16:43:44'),
(4, 1, '3', 'Mountains', '2014-11-04 16:43:44', '2014-11-04 16:43:44');

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
(1, 10, 10, 32, 32, '2014-11-04 16:43:42', '2014-11-04 16:43:42');

-- --------------------------------------------------------

--
-- Table structure for table `resources`
--

CREATE TABLE IF NOT EXISTS `resources` (
`id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `terraintype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=20 ;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` (`id`, `created_at`, `updated_at`, `terraintype_id`) VALUES
(1, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 5),
(2, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 6),
(3, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 6),
(4, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 6),
(5, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 6),
(6, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 6),
(7, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(8, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(9, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(10, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(11, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(12, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(13, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(14, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(15, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(16, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(17, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(18, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 6),
(19, '2014-11-04 16:43:43', '2014-11-04 16:43:43', 5);

-- --------------------------------------------------------

--
-- Table structure for table `resources_resourcetypes`
--

CREATE TABLE IF NOT EXISTS `resources_resourcetypes` (
`id` int(11) NOT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `resourcetype_id` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=39 ;

--
-- Dumping data for table `resources_resourcetypes`
--

INSERT INTO `resources_resourcetypes` (`id`, `resource_id`, `resourcetype_id`) VALUES
(1, 1, 2),
(2, 1, 4),
(3, 2, 2),
(4, 2, 4),
(5, 3, 2),
(6, 3, 4),
(7, 4, 2),
(8, 4, 4),
(9, 5, 2),
(10, 5, 4),
(11, 6, 2),
(12, 6, 4),
(13, 7, 2),
(14, 7, 4),
(15, 8, 2),
(16, 8, 4),
(17, 9, 2),
(18, 9, 4),
(19, 10, 2),
(20, 10, 4),
(21, 11, 2),
(22, 11, 4),
(23, 12, 2),
(24, 12, 4),
(25, 13, 2),
(26, 13, 4),
(27, 14, 2),
(28, 14, 4),
(29, 15, 2),
(30, 15, 4),
(31, 16, 2),
(32, 16, 4),
(33, 17, 2),
(34, 17, 4),
(35, 18, 2),
(36, 18, 4),
(37, 19, 2),
(38, 19, 4);

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
(1, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 'Oil', 'SUBTERRAINIAN', 0),
(2, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 'Gold', 'SUBSURFACE', 0),
(3, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 'Iron', 'EMBEDDEDROCK', 0),
(4, '2014-11-04 16:43:42', '2014-11-04 16:43:42', 'Natural Gas', 'CRUST', 0);

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
('20140725225550'),
('20140923211844'),
('20140923213955'),
('20140930153927'),
('20141007133707'),
('20141007134946');

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

CREATE TABLE IF NOT EXISTS `states` (
`id` int(11) NOT NULL,
  `motto` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `currency_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `states`
--

INSERT INTO `states` (`id`, `motto`, `name`, `currency_id`, `created_at`, `updated_at`, `user_id`) VALUES
(1, 'Kill em all!', 'Soviet Netherlands', '1', '2014-11-04 16:43:41', '2014-11-04 16:43:41', '1'),
(2, 'Kill em all!', 'New Germany', '1', '2014-11-04 16:43:41', '2014-11-04 16:43:41', '2');

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
(1, 'WATER', '2014-11-04 16:43:41', '2014-11-04 16:43:41'),
(2, 'SEA', '2014-11-04 16:43:41', '2014-11-04 16:43:41'),
(3, 'FOREST', '2014-11-04 16:43:41', '2014-11-04 16:43:41'),
(4, 'URBAN', '2014-11-04 16:43:42', '2014-11-04 16:43:42'),
(5, 'MOUNTAIN', '2014-11-04 16:43:42', '2014-11-04 16:43:42'),
(6, 'PLAINLAND', '2014-11-04 16:43:42', '2014-11-04 16:43:42'),
(7, 'COAST', '2014-11-04 16:43:42', '2014-11-04 16:43:42'),
(8, 'NONE', '2014-11-04 16:43:42', '2014-11-04 16:43:42');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=115 ;

--
-- Dumping data for table `tiles`
--

INSERT INTO `tiles` (`id`, `gidtag`, `xposition`, `yposition`, `xoffset`, `yoffset`, `created_at`, `updated_at`, `layer_id`, `image_id`, `resource_id`) VALUES
(1, 14, 4, 4, 5, 1, '2014-11-04 16:43:44', '2014-11-04 16:43:44', 1, 1, 1),
(2, 290, 1, 5, 1, 0, '2014-11-04 16:43:44', '2014-11-04 16:43:44', 1, 2, NULL),
(3, 290, 6, 5, 1, 0, '2014-11-04 16:43:44', '2014-11-04 16:43:44', 1, 2, NULL),
(4, 290, 0, 0, 1, 0, '2014-11-04 16:43:44', '2014-11-04 16:43:44', 3, 2, NULL),
(5, 290, 1, 0, 1, 0, '2014-11-04 16:43:44', '2014-11-04 16:43:45', 3, 2, NULL),
(6, 290, 2, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(7, 290, 3, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(8, 290, 4, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(9, 290, 5, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(10, 290, 6, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(11, 290, 7, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(12, 290, 8, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(13, 290, 9, 0, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(14, 290, 0, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(15, 290, 1, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(16, 290, 2, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(17, 290, 3, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(18, 290, 4, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(19, 290, 5, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(20, 290, 6, 1, 1, 0, '2014-11-04 16:43:45', '2014-11-04 16:43:45', 3, 2, NULL),
(21, 290, 7, 1, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(22, 290, 8, 1, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(23, 290, 9, 1, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(24, 290, 0, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(25, 290, 1, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(26, 290, 2, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(27, 290, 3, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(28, 290, 4, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(29, 290, 5, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(30, 290, 6, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(31, 290, 7, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(32, 290, 8, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(33, 290, 9, 2, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(34, 290, 0, 3, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(35, 290, 1, 3, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(36, 290, 2, 3, 1, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 2, NULL),
(37, 1, 3, 3, 0, 0, '2014-11-04 16:43:46', '2014-11-04 16:43:46', 3, 1, 2),
(38, 1, 4, 3, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 3),
(39, 1, 5, 3, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 4),
(40, 290, 6, 3, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(41, 290, 7, 3, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(42, 290, 8, 3, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(43, 290, 9, 3, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(44, 290, 0, 4, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(45, 290, 1, 4, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(46, 1, 2, 4, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 5),
(47, 1, 3, 4, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 6),
(48, 1, 4, 4, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 7),
(49, 1, 5, 4, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 8),
(50, 1, 6, 4, 0, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 1, 9),
(51, 290, 7, 4, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(52, 290, 8, 4, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(53, 290, 9, 4, 1, 0, '2014-11-04 16:43:47', '2014-11-04 16:43:47', 3, 2, NULL),
(54, 290, 0, 5, 1, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 2, NULL),
(55, 1, 2, 5, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 10),
(56, 1, 3, 5, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 11),
(57, 1, 4, 5, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 12),
(58, 1, 5, 5, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 13),
(59, 1, 6, 5, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 14),
(60, 290, 7, 5, 1, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 2, NULL),
(61, 290, 8, 5, 1, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 2, NULL),
(62, 290, 9, 5, 1, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 2, NULL),
(63, 290, 0, 6, 1, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 2, NULL),
(64, 290, 1, 6, 1, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 2, NULL),
(65, 1, 2, 6, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 15),
(66, 1, 3, 6, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 16),
(67, 1, 4, 6, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 17),
(68, 1, 5, 6, 0, 0, '2014-11-04 16:43:48', '2014-11-04 16:43:48', 3, 1, 18),
(69, 290, 6, 6, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(70, 290, 7, 6, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(71, 290, 8, 6, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(72, 290, 9, 6, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(73, 290, 0, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(74, 290, 1, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(75, 290, 2, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(76, 290, 3, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(77, 290, 4, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(78, 290, 5, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(79, 290, 6, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(80, 290, 7, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(81, 290, 8, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(82, 290, 9, 7, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:49', 3, 2, NULL),
(83, 290, 0, 8, 1, 0, '2014-11-04 16:43:49', '2014-11-04 16:43:50', 3, 2, NULL),
(84, 290, 1, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(85, 290, 2, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(86, 290, 3, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(87, 290, 4, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(88, 290, 5, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(89, 290, 6, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(90, 290, 7, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(91, 290, 8, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(92, 290, 9, 8, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(93, 290, 0, 9, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(94, 290, 1, 9, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(95, 290, 2, 9, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(96, 290, 3, 9, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(97, 290, 4, 9, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(98, 290, 5, 9, 1, 0, '2014-11-04 16:43:50', '2014-11-04 16:43:50', 3, 2, NULL),
(99, 290, 6, 9, 1, 0, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 3, 2, NULL),
(100, 290, 7, 9, 1, 0, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 3, 2, NULL),
(101, 290, 8, 9, 1, 0, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 3, 2, NULL),
(102, 290, 9, 9, 1, 0, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 3, 2, NULL),
(103, 301, 3, 3, 0, 1, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(104, 302, 4, 3, 1, 1, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(105, 303, 5, 3, 2, 1, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(106, 301, 2, 4, 0, 1, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(107, 303, 6, 4, 2, 1, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(108, 313, 2, 5, 0, 2, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(109, 327, 6, 5, 2, 3, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(110, 325, 2, 6, 0, 3, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(111, 326, 3, 6, 1, 3, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(112, 326, 4, 6, 1, 3, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(113, 327, 5, 6, 2, 3, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 2, 2, NULL),
(114, 14, 4, 4, 5, 1, '2014-11-04 16:43:51', '2014-11-04 16:43:51', 4, 1, 19);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(11) NOT NULL,
  `screenname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `loginname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `passwordhash` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `registerdate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailadres` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `screenname`, `loginname`, `passwordhash`, `registerdate`, `emailadres`, `created_at`, `updated_at`) VALUES
(1, 'Henk de tester', 'test', NULL, '2014-11-04', 'henk@henk.nl', '2014-11-04 16:43:41', '2014-11-04 16:43:41'),
(2, 'Patrick de player', 'patrick', NULL, '2014-11-04', 'henk@henk.nl', '2014-11-04 16:43:41', '2014-11-04 16:43:41');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `currencies`
--
ALTER TABLE `currencies`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `energy_building_types`
--
ALTER TABLE `energy_building_types`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `energy_buildings`
--
ALTER TABLE `energy_buildings`
 ADD PRIMARY KEY (`id`);

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
-- Indexes for table `states`
--
ALTER TABLE `states`
 ADD PRIMARY KEY (`id`);

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
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `currencies`
--
ALTER TABLE `currencies`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `energy_building_types`
--
ALTER TABLE `energy_building_types`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `energy_buildings`
--
ALTER TABLE `energy_buildings`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
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
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `resources_resourcetypes`
--
ALTER TABLE `resources_resourcetypes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `resourcetypes`
--
ALTER TABLE `resourcetypes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `states`
--
ALTER TABLE `states`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `terraintypes`
--
ALTER TABLE `terraintypes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tiles`
--
ALTER TABLE `tiles`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=115;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;--
-- Database: `test`
--

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
