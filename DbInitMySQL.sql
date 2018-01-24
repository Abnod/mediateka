CREATE SCHEMA IF NOT EXISTS `mediateka` ;

CREATE TABLE IF NOT EXISTS `mediateka`.`users` (
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `isadmin` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC));

CREATE TABLE IF NOT EXISTS `mediateka`.`media` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `singer` VARCHAR(45) NULL,
  `type` VARCHAR(45) NOT NULL,
  `path` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC));

INSERT INTO `mediateka`.`users` (`login`, `password`) VALUES ('user1', '$2a$10$Gurt6BFp0o8zTr.BVnnQdu9HXeeO25ZBrnKEyf8o8961brBgoda52');
INSERT INTO `mediateka`.`users` (`login`, `password`) VALUES ('user2', '$2a$10$NBDttFSkAVgpPrPgbKWDJeovr1apXHXqRZJIgvUUpyeqeYTTPMxXW');
INSERT INTO `mediateka`.`users` (`login`, `password`, `isadmin`) VALUES ('admin', '$2a$10$cG2aA30Sl6RMhhlydvByzeWr/8I4aUrRyxGhE49rDIwliBFzGV9pu', '1');
