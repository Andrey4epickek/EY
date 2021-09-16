-- MySQL Script generated by MySQL Workbench
-- Wed Sep  8 16:25:04 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EY` DEFAULT CHARACTER SET utf8 ;
USE `EY` ;

-- -----------------------------------------------------
-- Table `mydb`.`Info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EY`.`Info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dateEY` DATE NULL,
  `latinChars` VARCHAR(10) NULL,
  `rusChars` VARCHAR(10) NULL,
  `evenNumber` INT NULL,
  `materialNumber` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;