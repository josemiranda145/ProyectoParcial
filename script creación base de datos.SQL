start transaction;

drop database if exists `egc-frontend`;
create database `egc-frontend`;
use `egc-frontend`;



/*create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';
*/

grant select, insert, update, delete
on `egc-frontend`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view
on `egc-frontend`.* to 'acme-manager'@'%';





--
-- Table structure for table `referendumrecuento`
--

DROP TABLE IF EXISTS `referendumrecuento`;
CREATE TABLE `referendumrecuento` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `idVotacionModificacion` int(11) DEFAULT NULL,
  `idVotacionRecuento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_54637unv3hexjyd3jlo81suk6` (`idVotacionModificacion`),
  UNIQUE KEY `UK_s5w2v28bcbk0n92k1l00qt6ku` (`idVotacionRecuento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
--
-- Table structure for table `propuesta`
--

DROP TABLE IF EXISTS `propuesta`;
CREATE TABLE `propuesta` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `numeroNo` int(11) DEFAULT NULL,
  `numeroSi` int(11) DEFAULT NULL,
  `pregunta` varchar(255) DEFAULT NULL,
  `referendumRecuento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7hbharynb2y72557pr80wwqqt` (`referendumRecuento_id`),
  CONSTRAINT `FK_7hbharynb2y72557pr80wwqqt` FOREIGN KEY (`referendumRecuento_id`) REFERENCES `referendumrecuento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
INSERT INTO `useraccount` VALUES (1,0,'21232f297a57a5a743894a0e4a801fc3','admin');
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
INSERT INTO `useraccount_authorities` VALUES (1,'ADMIN');
UNLOCK TABLES;


--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',2);
UNLOCK TABLES;

-- Dump completed on 2014-12-17 16:16:37
commit; 