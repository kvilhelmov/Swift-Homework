-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema addressdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema addressdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `addressdb` DEFAULT CHARACTER SET utf8 ;
USE `addressdb` ;

-- -----------------------------------------------------
-- Table `addressdb`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`countries` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 76
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `addressdb`.`regions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`regions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_region_country_idx` (`country_id` ASC),
  CONSTRAINT `FK_region_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `addressdb`.`countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `addressdb`.`populated_area_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`populated_area_types` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `addressdb`.`populated_areas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`populated_areas` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type_id` INT(11) NOT NULL,
  `region_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_populated_area_type_idx` (`type_id` ASC),
  INDEX `FK_populated_area_region_idx` (`region_id` ASC),
  CONSTRAINT `FK_populated_area_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `addressdb`.`regions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_populated_area_type`
    FOREIGN KEY (`type_id`)
    REFERENCES `addressdb`.`populated_area_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `addressdb`.`municipalities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`municipalities` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `populated_area_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_municipality_populated_area_idx` (`populated_area_id` ASC),
  CONSTRAINT `FK_municipality_populated_area`
    FOREIGN KEY (`populated_area_id`)
    REFERENCES `addressdb`.`populated_areas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `addressdb`.`streets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`streets` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `municipality_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_streeet_municipality_idx` (`municipality_id` ASC),
  CONSTRAINT `FK_streeet_municipality`
    FOREIGN KEY (`municipality_id`)
    REFERENCES `addressdb`.`municipalities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `addressdb`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `addressdb`.`addresses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `street_id` INT(11) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `floor` SMALLINT(3) NULL DEFAULT NULL,
  `apartmentNo` SMALLINT(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_address_street_idx` (`street_id` ASC),
  CONSTRAINT `FK_address_street`
    FOREIGN KEY (`street_id`)
    REFERENCES `addressdb`.`streets` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
