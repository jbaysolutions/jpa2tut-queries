-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jpa2tut-queries
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jpa2tut-queries
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jpa2tut-queries` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `jpa2tut-queries` ;

-- -----------------------------------------------------
-- Table `jpa2tut-queries`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa2tut-queries`.`company` (
  `idcompany` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcompany`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa2tut-queries`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa2tut-queries`.`client` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`idclient`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa2tut-queries`.`company_has_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa2tut-queries`.`company_has_client` (
  `company_idcompany` INT NOT NULL,
  `client_idclient` INT NOT NULL,
  PRIMARY KEY (`company_idcompany`, `client_idclient`),
  INDEX `fk_company_has_client_client1_idx` (`client_idclient` ASC),
  INDEX `fk_company_has_client_company1_idx` (`company_idcompany` ASC),
  CONSTRAINT `fk_company_has_client_company1`
    FOREIGN KEY (`company_idcompany`)
    REFERENCES `jpa2tut-queries`.`company` (`idcompany`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_has_client_client1`
    FOREIGN KEY (`client_idclient`)
    REFERENCES `jpa2tut-queries`.`client` (`idclient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
