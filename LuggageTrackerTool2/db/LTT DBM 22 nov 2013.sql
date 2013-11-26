CREATE DATABASE `lttDatabase` /*!40100 DEFAULT CHARACTER SET big5 */;


CREATE TABLE `Address` (
  `Address ID` int(11) NOT NULL,
  `Street` varchar(25) NOT NULL,
  `Street number` int(11) DEFAULT NULL,
  `Zipcode` varchar(6) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Address ID`),
  KEY `PassengerId_Relatie_idx` (`Passenger ID`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `passenger` (
  `Passenger ID` int(11) NOT NULL,
  `Surname` varchar(35) DEFAULT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Gender` varchar(1) DEFAULT NULL,
  `Date of birth` datetime DEFAULT NULL,
  `Mobile phone` varchar(12) DEFAULT NULL,
  `Home phone` varchar(12) DEFAULT NULL,
  `Home address ID` int(11) NOT NULL,
  `Temporary address ID` int(11) NOT NULL,
  `Insertion` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`Passenger ID`),
  KEY `home_idx` (`Home address ID`,`Temporary address ID`),
  KEY `passenger_temp_address_key_idx` (`Temporary address ID`),
  CONSTRAINT `passenger_home_address_key` FOREIGN KEY (`Home address ID`) REFERENCES `Address` (`Address ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `passenger_temp_address_key` FOREIGN KEY (`Temporary address ID`) REFERENCES `Address` (`Address ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `Luggage` (
  `Luggage ID` int(11) NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `Storage Location` varchar(45) DEFAULT NULL,
  `Passenger ID` int(11) NOT NULL,
  `Luggage Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Luggage ID`),
  KEY `luggage_passenger_key_idx` (`Passenger ID`),
  CONSTRAINT `luggage_passenger_key` FOREIGN KEY (`Passenger ID`) REFERENCES `passenger` (`Passenger ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=big5;

CREATE TABLE `User` (
  `User ID` int(11) NOT NULL,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `Privileges` varchar(45) NOT NULL,
  PRIMARY KEY (`User ID`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
