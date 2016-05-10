-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ssh 的数据库结构
CREATE DATABASE IF NOT EXISTS `ssh` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ssh`;


-- 导出  表 ssh.person 结构
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `test` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- 正在导出表  ssh.person 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT IGNORE INTO `person` (`id`, `date`, `name`, `password`, `test`) VALUES
	(17, '2016-04-22 21:40:19', 'zhang17', NULL, NULL),
	(18, '2016-04-22 21:40:25', 'zhang18', NULL, NULL),
	(19, '2016-04-22 21:41:46', 'zhang19', NULL, NULL),
	(20, '2016-04-22 21:44:09', 'zhang20', NULL, NULL),
	(21, '2016-04-23 17:32:15', 'zhang21', NULL, NULL),
	(22, '2016-04-23 17:49:04', 'zhang22', NULL, NULL),
	(23, '2016-04-23 17:59:28', 'zhang23', NULL, NULL),
	(24, '2016-04-23 18:03:49', 'zhang24', NULL, NULL),
	(25, '2016-04-23 18:05:47', 'zhang25', NULL, NULL),
	(26, '2016-04-26 16:27:34', 'zhang1', NULL, NULL),
	(27, '2016-04-26 16:27:59', 'zhang1', NULL, NULL),
	(28, '2016-05-07 15:00:00', 'zhang1', NULL, NULL),
	(29, '2016-05-07 15:46:54', 'zhang1', NULL, NULL),
	(30, '2016-05-07 15:47:25', 'zhang1', NULL, NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


-- 导出  表 ssh.test 结构
CREATE TABLE IF NOT EXISTS `test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ssh.test 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
