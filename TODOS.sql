
CREATE SCHEMA IF NOT EXISTS `MY_TASK_DB` DEFAULT CHARACTER SET utf8 ;
USE `MY_TASK_DB` ;

-- -----------------------------------------------------
-- Table `MY_TASK_DB`.`TASK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MY_TASK_DB`.`TASK` (
  `ID` INT(19) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` VARCHAR(100) NULL,
  `DONE` TINYINT NULL DEFAULT 0,
  `DATE` DATE NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

