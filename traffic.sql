/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.24-0ubuntu0.16.04.1 : Database - traffic
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`traffic` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `traffic`;

/*Table structure for table `punishmentway` */

DROP TABLE IF EXISTS `punishmentway`;

CREATE TABLE `punishmentway` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `trafficId` int(10) DEFAULT NULL COMMENT '违章信息id',
  `punishmentType` int(2) DEFAULT NULL COMMENT '违章类型 1 警告 2 罚款 3 暂扣驾驶执照',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `punishmentway` */

insert  into `punishmentway`(`id`,`trafficId`,`punishmentType`) values (1,1,1),(2,1,2),(3,1,3),(4,17,1),(5,17,2),(6,18,1),(7,18,2),(8,18,3),(9,19,1),(10,20,1),(11,20,2);

/*Table structure for table `traffic` */

DROP TABLE IF EXISTS `traffic`;

CREATE TABLE `traffic` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `licenseId` varchar(30) DEFAULT NULL COMMENT '驾驶证号',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `decodes` int(10) DEFAULT NULL COMMENT '邮编',
  `phoneNumber` varchar(11) DEFAULT NULL COMMENT '电话号',
  `licencePlate` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `modelType` varchar(20) DEFAULT NULL COMMENT '型号',
  `factoryName` varchar(100) DEFAULT NULL COMMENT '制造厂',
  `expiringDate` datetime NOT NULL COMMENT '生产时间',
  `violationDate` timestamp NULL DEFAULT NULL COMMENT '违章日期',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `violationAddress` varchar(100) DEFAULT NULL COMMENT '违章地点',
  `violationContent` varchar(200) DEFAULT NULL COMMENT '违章记载',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

/*Data for the table `traffic` */

insert  into `traffic`(`id`,`name`,`licenseId`,`address`,`decodes`,`phoneNumber`,`licencePlate`,`modelType`,`factoryName`,`expiringDate`,`violationDate`,`createTime`,`violationAddress`,`violationContent`) values (20,'胡颜德','640323199411100888','宁夏吴忠市盐池县高沙窝',751501,'18195555504','宁c：x8828','小轿车','北京现代汽车有限公司','2019-05-03 00:00:00','2019-04-30 00:00:00','2019-05-03 00:00:00','宁夏银川市西夏区怀远市场西路','占用非机动车道');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
