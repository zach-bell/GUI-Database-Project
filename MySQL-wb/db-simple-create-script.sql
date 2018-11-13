-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema University-DB
-- -----------------------------------------------------
-- Computer Science 430 - Final Project University Database

-- -----------------------------------------------------
-- Schema University-DB
--
-- Computer Science 430 - Final Project University Database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `University-DB` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `University-DB` ;

-- -----------------------------------------------------
-- Table `University-DB`.`Deptartment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `University-DB`.`Deptartment` (
  `dNumber` INT NOT NULL,
  `dname` VARCHAR(45) NULL,
  `office` VARCHAR(45) NULL,
  PRIMARY KEY (`dNumber`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `University-DB`.`Professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `University-DB`.`Professor` (
  `ssn` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `age` INT(3) NULL,
  `rank` VARCHAR(45) NULL,
  `speciality` VARCHAR(45) NULL,
  `dNumber` INT NOT NULL,
  PRIMARY KEY (`ssn`, `dNumber`),
  INDEX `fk_Professor_Deptartment1_idx` (`dNumber` ASC) VISIBLE,
  CONSTRAINT `fk_Professor_Deptartment1`
    FOREIGN KEY (`dNumber`)
    REFERENCES `University-DB`.`Deptartment` (`dNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `University-DB`.`Project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `University-DB`.`Project` (
  `pid` INT NOT NULL,
  `sponsor` VARCHAR(45) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `budget` DOUBLE NULL,
  PRIMARY KEY (`pid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `University-DB`.`Graduate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `University-DB`.`Graduate` (
  `ssn` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `age` INT(3) NULL,
  `Project_pid` INT NOT NULL,
  PRIMARY KEY (`ssn`, `Project_pid`),
  INDEX `fk_Graduate_Project1_idx` (`Project_pid` ASC) VISIBLE,
  CONSTRAINT `fk_Graduate_Project1`
    FOREIGN KEY (`Project_pid`)
    REFERENCES `University-DB`.`Project` (`pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `University-DB`.`Major`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `University-DB`.`Major` (
  `Graduate_ssn` INT NOT NULL,
  `Deptartment_dNumber` INT NOT NULL,
  PRIMARY KEY (`Graduate_ssn`, `Deptartment_dNumber`),
  INDEX `fk_Major_Deptartment1_idx` (`Deptartment_dNumber` ASC) VISIBLE,
  CONSTRAINT `fk_Major_Graduate1`
    FOREIGN KEY (`Graduate_ssn`)
    REFERENCES `University-DB`.`Graduate` (`ssn`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Major_Deptartment1`
    FOREIGN KEY (`Deptartment_dNumber`)
    REFERENCES `University-DB`.`Deptartment` (`dNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'admin' IDENTIFIED BY 'admin';

GRANT ALL ON `University-DB`.* TO 'admin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
