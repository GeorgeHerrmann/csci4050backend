-- Sat Mar 18 21:39:28 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema csci4050
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema csci4050
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `csci4050` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `csci4050` ;

-- -----------------------------------------------------
-- Table `csci4050`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`movie` (
  `title` VARCHAR(255) NOT NULL,
  `trailer` VARCHAR(255) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `rating` DECIMAL(10,0) UNSIGNED NULL DEFAULT NULL,
  `poster` VARCHAR(45) NULL DEFAULT NULL,
  `director` VARCHAR(45) NULL DEFAULT NULL,
  `actor` VARCHAR(45) NULL DEFAULT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `recieve_promotions` TINYINT NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `street` VARCHAR(255) NULL DEFAULT NULL,
  `zipcode` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  CONSTRAINT `FKhv5pjebgk24fdyfiq6g06kuf`
    FOREIGN KEY (`id`)
    REFERENCES `csci4050`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`payment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cvv` VARCHAR(3) NOT NULL,
  `card_number` VARCHAR(16) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(2) NULL DEFAULT NULL,
  `zipcode` VARCHAR(10) NULL DEFAULT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `user_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_payment_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_payment`
    FOREIGN KEY (`user_id`)
    REFERENCES `csci4050`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`promotion` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `discount` INT UNSIGNED NULL DEFAULT NULL,
  `start_date` DATETIME NULL DEFAULT NULL,
  `end_date` DATETIME NULL DEFAULT NULL,
  `code` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`theatre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`theatre` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`show_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`show_room` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `theatre_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `theatre_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_show_room_theatre1_idx` (`theatre_id` ASC) VISIBLE,
  CONSTRAINT `show_room_theatre`
    FOREIGN KEY (`theatre_id`)
    REFERENCES `csci4050`.`theatre` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`show`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`show` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `movie_id` INT UNSIGNED NOT NULL,
  `show_time` DATETIME NOT NULL,
  `show_room_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `show_movie_idx` (`movie_id` ASC) VISIBLE,
  INDEX `show_show_room_idx` (`show_room_id` ASC) VISIBLE,
  CONSTRAINT `show_movie`
    FOREIGN KEY (`movie_id`)
    REFERENCES `csci4050`.`movie` (`id`),
  CONSTRAINT `show_show_room`
    FOREIGN KEY (`show_room_id`)
    REFERENCES `csci4050`.`show_room` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `promotion_id` INT UNSIGNED NULL DEFAULT NULL,
  `payment_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `total` DECIMAL(10,0) UNSIGNED NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `subtotal` DECIMAL(10,0) UNSIGNED NOT NULL,
  `fee` DECIMAL(10,0) UNSIGNED NOT NULL,
  `booking_id` INT NOT NULL,
  `show_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `order_promotion_idx` (`promotion_id` ASC) VISIBLE,
  INDEX `user_order_idx` (`user_id` ASC) VISIBLE,
  INDEX `order_payment_idx` (`payment_id` ASC) VISIBLE,
  INDEX `fk_order_show1_idx` (`show_id` ASC) VISIBLE,
  CONSTRAINT `order_payment`
    FOREIGN KEY (`payment_id`)
    REFERENCES `csci4050`.`payment` (`id`),
  CONSTRAINT `order_promotion`
    FOREIGN KEY (`promotion_id`)
    REFERENCES `csci4050`.`promotion` (`id`),
  CONSTRAINT `order_show`
    FOREIGN KEY (`show_id`)
    REFERENCES `csci4050`.`show` (`id`),
  CONSTRAINT `user_order`
    FOREIGN KEY (`user_id`)
    REFERENCES `csci4050`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`payment_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`payment_address` (
  `street` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(2) NULL DEFAULT NULL,
  `zipcode` VARCHAR(45) NULL DEFAULT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `payment_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_address_payment1_idx` (`payment_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_address_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `csci4050`.`payment` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`seat` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `row` CHAR(1) NOT NULL,
  `number` INT UNSIGNED NOT NULL,
  `show_room_id` INT UNSIGNED NOT NULL,
  `show_room_theatre_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `show_room_id`, `show_room_theatre_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_seat_show_room1_idx` (`show_room_id` ASC, `show_room_theatre_id` ASC) VISIBLE,
  CONSTRAINT `seat_show_room`
    FOREIGN KEY (`show_room_id` , `show_room_theatre_id`)
    REFERENCES `csci4050`.`show_room` (`id` , `theatre_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`ticket` (
  `seat_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `show_id` INT NOT NULL,
  `order_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `order_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `ticker_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `ticket_seat_idx` (`seat_id` ASC) VISIBLE,
  INDEX `fk_ticket_order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_ticket_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `csci4050`.`order` (`id`),
  CONSTRAINT `ticket_seat`
    FOREIGN KEY (`seat_id`)
    REFERENCES `csci4050`.`seat` (`id`),
  CONSTRAINT `ticket_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `csci4050`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `csci4050`.`user_seq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csci4050`.`user_seq` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;