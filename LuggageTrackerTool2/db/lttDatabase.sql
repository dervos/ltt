CREATE DATABASE `lttDatabase` /*!40100 DEFAULT CHARACTER SET big5 */;


CREATE TABLE `User` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `privileges` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=big5;

CREATE TABLE `Address` (
  `addressid` int(11) NOT NULL AUTO_INCREMENT,
  `streetname` varchar(25) NOT NULL,
  `streetnumber` int(11) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=big5;

CREATE TABLE `passenger` (
  `passengerid` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(35) DEFAULT NULL,
  `insertion` varchar(8) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `mobphone` varchar(12) DEFAULT NULL,
  `homephone` varchar(12) DEFAULT NULL,
  `homeaddressid` int(11) NOT NULL,
  `tempaddressid` int(11) NOT NULL,
  PRIMARY KEY (`passengerid`),
  KEY `home_idx` (`homeaddressid`,`tempaddressid`),
  KEY `passenger_temp_address_key_idx` (`tempaddressid`),
  CONSTRAINT `address_fk` FOREIGN KEY (`tempaddressid`) REFERENCES `Address` (`addressid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `home_fk` FOREIGN KEY (`homeaddressid`) REFERENCES `Address` (`addressid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=big5;

CREATE TABLE `Luggage` (
  `luggageid` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `storagelocation` varchar(45) DEFAULT NULL,
  `luggagestatus` varchar(45) DEFAULT NULL,
  `passengerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`luggageid`),
  KEY `luggage_passenger_key_idx` (`passengerid`),
  CONSTRAINT `luggage_passenger_fk` FOREIGN KEY (`passengerid`) REFERENCES `passenger` (`passengerid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;
