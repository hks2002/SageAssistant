-- --------------------------------------------------------
-- 主机:                           192.168.0.246
-- 服务器版本:                        5.5.68-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 fapiao 的数据库结构
DROP DATABASE IF EXISTS `fapiao`;
CREATE DATABASE IF NOT EXISTS `fapiao` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fapiao`;

-- 导出  表 fapiao.body 结构
DROP TABLE IF EXISTS `body`;
CREATE TABLE IF NOT EXISTS `body` (
  `rowid` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbdm` varchar(12) NOT NULL COMMENT '发票代码',
  `fphm` varchar(8) NOT NULL COMMENT '发票号码',
  `xh` varchar(5) NOT NULL COMMENT '序号',
  `spmc` varchar(120) NOT NULL COMMENT '商品编码',
  `ggxh` varchar(32) DEFAULT NULL COMMENT '规格型号',
  `jldw` varchar(5) DEFAULT NULL COMMENT '计量单位',
  `dj` varchar(25) DEFAULT NULL COMMENT '单价',
  `sl` varchar(10) DEFAULT NULL COMMENT '数量',
  `je` varchar(15) DEFAULT NULL COMMENT '金额',
  `slv` varchar(5) DEFAULT NULL COMMENT '税率',
  `se` varchar(15) DEFAULT NULL COMMENT '税额',
  PRIMARY KEY (`rowid`),
  UNIQUE KEY `lbdmfphmxh` (`lbdm`,`fphm`,`xh`),
  KEY `lbdm` (`lbdm`),
  KEY `fphm` (`fphm`),
  KEY `ggxh` (`ggxh`)
) ENGINE=MyISAM AUTO_INCREMENT=816 DEFAULT CHARSET=utf8mb4;

-- 数据导出被取消选择。

-- 导出  表 fapiao.header 结构
DROP TABLE IF EXISTS `header`;
CREATE TABLE IF NOT EXISTS `header` (
  `rowid` bigint(20) NOT NULL AUTO_INCREMENT,
  `fpzl` varchar(10) DEFAULT NULL COMMENT '发票种类',
  `lbdm` varchar(12) NOT NULL COMMENT '发票代码',
  `fphm` varchar(8) NOT NULL COMMENT '发票号码',
  `kprq` varchar(20) DEFAULT NULL COMMENT '开票日期',
  `gfmc` varchar(100) DEFAULT NULL COMMENT '购方名称',
  `gfsh` varchar(20) DEFAULT NULL COMMENT '购方税号',
  `gfyhzh` varchar(100) DEFAULT NULL COMMENT '购方银行账号',
  `gfdzdh` varchar(200) DEFAULT NULL COMMENT '购方地址电话',
  `xfmc` varchar(100) DEFAULT NULL COMMENT '销方名称',
  `xfsh` varchar(20) DEFAULT NULL COMMENT '销方税号',
  `xfyhzh` varchar(100) DEFAULT NULL COMMENT '销方银行账号',
  `xfdzdh` varchar(200) DEFAULT NULL COMMENT '销方地址电话',
  `hjje` varchar(15) DEFAULT NULL COMMENT '合计金额',
  `hjse` varchar(15) DEFAULT NULL COMMENT '合计税额',
  `bz` varchar(200) DEFAULT NULL COMMENT '备注',
  `kpr` varchar(15) DEFAULT NULL COMMENT '开票人',
  `fhr` varchar(15) DEFAULT NULL COMMENT '复核人',
  `skr` varchar(15) DEFAULT NULL COMMENT '收款人',
  `fpzt` varchar(10) DEFAULT NULL COMMENT '发票状态',
  PRIMARY KEY (`rowid`),
  UNIQUE KEY `lbdmfphm` (`lbdm`,`fphm`),
  KEY `lbdm` (`lbdm`),
  KEY `fphm` (`fphm`)
) ENGINE=MyISAM AUTO_INCREMENT=442 DEFAULT CHARSET=utf8mb4;

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
