-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema watchlistdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `watchlistdb` ;

-- -----------------------------------------------------
-- Schema watchlistdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `watchlistdb` DEFAULT CHARACTER SET utf8 ;
USE `watchlistdb` ;

-- -----------------------------------------------------
-- Table `stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `stock` ;

CREATE TABLE IF NOT EXISTS `stock` (
  `name` VARCHAR(45) NOT NULL,
  `symbol` VARCHAR(6) NOT NULL,
  `Industry` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  PRIMARY KEY (`symbol`),
  UNIQUE INDEX `symbol_UNIQUE` (`symbol` ASC))
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO watchlistdb@localhost;
 DROP USER watchlistdb@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'watchlistdb'@'localhost' IDENTIFIED BY 'watchlistdb';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'watchlistdb'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `stock`
-- -----------------------------------------------------
START TRANSACTION;
USE `watchlistdb`;
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('Microsoft', 'MSFT', 'Technology');
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('Apple', 'AAPL', 'Technology');
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('Bristol Myers Squibb', 'BMY', 'Pharmaceutical');
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('Ford', 'F', 'Automobile');
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('ExxonMobil', 'XOM', 'Energy');
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('FaceBook', 'FB', 'Technology');
INSERT INTO `stock` (`name`, `symbol`, `Industry`) VALUES ('Walmart', 'WMT', 'Retail');

COMMIT;

