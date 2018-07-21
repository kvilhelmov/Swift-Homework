-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema school
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema school
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `school` DEFAULT CHARACTER SET utf8 ;
USE `school` ;

-- -----------------------------------------------------
-- Table `school`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`address` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `number` VARCHAR(10) NOT NULL,
  `floor` SMALLINT(3) NULL DEFAULT NULL,
  `apartmentNo` SMALLINT(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `school`.`disciplines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`disciplines` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `school`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`students` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `enrollmentDate` DATE NOT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_student_address_idx` (`address_id` ASC),
  CONSTRAINT `FK_student_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `school`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `school`.`disciplines_studied`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`disciplines_studied` (
  `discipline_id` INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,
  PRIMARY KEY (`discipline_id`, `student_id`),
  INDEX `discipline_idx` (`discipline_id` ASC),
  INDEX `student_idx` (`student_id` ASC),
  CONSTRAINT `FK_discipline_discipline_student`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `school`.`disciplines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_discipline_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `school`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `school`.`teachers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`teachers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `salary` DECIMAL(10,0) NOT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC),
  INDEX `FK_teacher_address_idx` (`address_id` ASC),
  CONSTRAINT `FK_teacher_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `school`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `school`.`disciplines_taught`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `school`.`disciplines_taught` (
  `discipline_id` INT(11) NOT NULL,
  `teacher_id` INT(11) NOT NULL,
  PRIMARY KEY (`discipline_id`, `teacher_id`),
  INDEX `teacher_idx` (`teacher_id` ASC),
  CONSTRAINT `FK_discipline_discipline_teacher`
    FOREIGN KEY (`discipline_id`)
    REFERENCES `school`.`disciplines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_teacher_discipline_teacher`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `school`.`teachers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
