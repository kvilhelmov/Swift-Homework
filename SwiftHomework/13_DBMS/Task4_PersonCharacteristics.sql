-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema citizen_registrations
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema citizen_registrations
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `citizen_registrations` DEFAULT CHARACTER SET utf8 ;
USE `citizen_registrations` ;

-- -----------------------------------------------------
-- Table `citizen_registrations`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`addresses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `municipality` VARCHAR(45) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `floor` INT(11) NULL DEFAULT NULL,
  `apartmentNo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`education_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`education_types` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`educations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`educations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type_id` INT(11) NOT NULL,
  `institution_name` VARCHAR(45) NOT NULL,
  `enrollment_date` DATE NOT NULL,
  `graduation_date` DATE NULL DEFAULT NULL,
  `graduated` BIT(1) NULL DEFAULT NULL,
  `final_grade` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_education_education_types_idx` (`type_id` ASC),
  CONSTRAINT `FK_education_education_types`
    FOREIGN KEY (`type_id`)
    REFERENCES `citizen_registrations`.`education_types` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`genders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`genders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`people`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`people` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `middle_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `gender_id` INT(11) NOT NULL,
  `height` SMALLINT(3) NULL DEFAULT NULL,
  `birth_date` DATE NOT NULL,
  `current_address` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_people_gender_idx` (`gender_id` ASC),
  INDEX `FK_people_current_address_idx` (`current_address` ASC),
  CONSTRAINT `FK_people_current_address`
    FOREIGN KEY (`current_address`)
    REFERENCES `citizen_registrations`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_people_gender`
    FOREIGN KEY (`gender_id`)
    REFERENCES `citizen_registrations`.`genders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `citizen_registrations`.`people_addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`people_addresses` (
  `person_id` INT(11) NOT NULL,
  `address_id` INT(11) NOT NULL,
  PRIMARY KEY (`person_id`, `address_id`),
  INDEX `FK_address_addresses_people_idx` (`address_id` ASC),
  CONSTRAINT `FK_address_addresses_people`
    FOREIGN KEY (`address_id`)
    REFERENCES `citizen_registrations`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_person_addresses_people`
    FOREIGN KEY (`person_id`)
    REFERENCES `citizen_registrations`.`people` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '	';


-- -----------------------------------------------------
-- Table `citizen_registrations`.`people_educations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citizen_registrations`.`people_educations` (
  `person_id` INT(11) NOT NULL,
  `education_id` INT(11) NOT NULL,
  PRIMARY KEY (`person_id`, `education_id`),
  INDEX `FK_education_people_educations_idx` (`education_id` ASC),
  CONSTRAINT `FK_education_people_educations`
    FOREIGN KEY (`education_id`)
    REFERENCES `citizen_registrations`.`educations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_person_people_educations`
    FOREIGN KEY (`person_id`)
    REFERENCES `citizen_registrations`.`people` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
