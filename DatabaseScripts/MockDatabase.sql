-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 16, 2016 at 07:31 PM
-- Server version: 10.1.14-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `MockDatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `tpc_testmodel1`
--

CREATE TABLE IF NOT EXISTS `tpc_testmodel1` (
  `id` int(11) NOT NULL,
  `superclass_testfield` text NOT NULL,
  `tpctestmodel2_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tpc_testmodel1`
--

INSERT INTO `tpc_testmodel1` (`id`, `superclass_testfield`, `tpctestmodel2_id`) VALUES
(1969166, 'superclass_content', 0),
(1969167, 'superclass_content', 1969168),
(1969168, 'superclass_content', 1969169),
(1969169, 'superclass_content', 1969170),
(1969170, 'superclass_content', 1969171),
(1969171, 'superclass_content', 1969172),
(1969172, 'superclass_content', 1969173),
(1969173, 'superclass_content', 1969174),
(1969174, 'superclass_content', 1969175),
(1969175, 'superclass_content', 1969176),
(2003250, 'superclass_content', 2003251),
(2003251, 'superclass_content', 2003252),
(2128015, 'superclass_content', 2128016),
(2128016, 'superclass_content', 2128017),
(2128017, 'superclass_content', 2128018),
(2128023, 'superclass_content', 2128024);

-- --------------------------------------------------------

--
-- Table structure for table `tpc_testmodel2`
--

CREATE TABLE IF NOT EXISTS `tpc_testmodel2` (
  `id` int(11) NOT NULL,
  `testfield_subclass` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tpc_testmodel2`
--

INSERT INTO `tpc_testmodel2` (`id`, `testfield_subclass`) VALUES
(1969161, ''),
(1969162, ''),
(1969164, ''),
(1969165, 'subclass_content'),
(1969166, 'subclass_content'),
(1969167, 'subclass_content'),
(1969168, 'subclass_content'),
(1969169, 'subclass_content'),
(1969170, 'subclass_content'),
(1969171, 'subclass_content'),
(1969172, 'subclass_content'),
(1969173, 'subclass_content'),
(1969174, 'subclass_content'),
(1969175, 'subclass_content'),
(1969176, 'subclass_content'),
(2003251, 'subclass_content'),
(2003252, 'subclass_content'),
(2128016, 'subclass_content'),
(2128017, 'subclass_content'),
(2128018, 'subclass_content'),
(2128019, 'superclass_content'),
(2128021, 'superclass_content'),
(2128024, 'subclass_content'),
(2128025, 'superclass_content'),
(2128027, 'superclass_content'),
(2128028, 'superclass_content'),
(2128029, 'superclass_content'),
(2128030, 'superclass_content'),
(2128034, 'subclass_content'),
(2128036, 'subclass_content'),
(2128038, 'subclass_content'),
(2128040, 'subclass_content'),
(2128042, 'subclass_content');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
