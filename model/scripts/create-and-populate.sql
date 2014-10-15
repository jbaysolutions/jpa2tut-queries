-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jpa2tut-queries
-- -----------------------------------------------------

drop schema `jpa2tut-queries` ;

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
  `created_year` INT NOT NULL,
  PRIMARY KEY (`idcompany`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpa2tut-queries`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpa2tut-queries`.`employee` (
  `idemployee` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL,
  `idcompany` INT NOT NULL,
  `birthday` DATE NOT NULL,
  PRIMARY KEY (`idemployee`),
  INDEX `fk_employee_company1_idx` (`idcompany` ASC),
  CONSTRAINT `fk_employee_company1`
    FOREIGN KEY (`idcompany`)
    REFERENCES `jpa2tut-queries`.`company` (`idcompany`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT INTO `jpa2tut-queries`.`company` (`name`, `address`, `created_year`) VALUES ('JBay Solutions', 'Somewhere in Portugal', 2009);
INSERT INTO `jpa2tut-queries`.`company` (`name`, `address`, `created_year`) VALUES ('JetBrains', 'Somewhere in Prague', 2000);
INSERT INTO `jpa2tut-queries`.`company` (`name`, `address`, `created_year`) VALUES ('Google', 'Somewhere in the US', 1998);
INSERT INTO `jpa2tut-queries`.`company` (`name`, `address`, `created_year`) VALUES ('Yahoo', 'Somewhere in the US',1994);
INSERT INTO `jpa2tut-queries`.`company` (`name`, `address`, `created_year`) VALUES ('MySQL', 'Somewhere in an Oracle Office', 1995);

INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Rui Pereira', 'Lisbon',1 , '1981-06-27' );
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Gustavo Santo', 'Peniche',1, '1979-12-19');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Maxim Shafirov', 'St.Petersburg, Russia',2, '1970-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Valentin Kipiatkov', 'St.Petersburg, Russia',2, '1975-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Sergey Brin', 'Los Altos, California',3, '1965-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Larry Page', 'Palo Alto, California',3, '1960-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('David Filo', 'California',4, '1972-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Marissa Mayer', 'California',4, '1975-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('Monty Widenius', 'Finland',5, '1971-06-01');
INSERT INTO `jpa2tut-queries`.`employee` (`name`, `address`, `idcompany`, `birthday`) VALUES ('David Axmark', 'Sweden',5, '1980-06-01');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
