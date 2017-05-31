SET NAMES 'utf8'
COLLATE 'utf8_general_ci';

DROP DATABASE IF EXISTS `todo`;

CREATE DATABASE `todo`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE `todo`;

CREATE TABLE `todos` (
  id      INT     AUTO_INCREMENT,
  text    TEXT NOT NULL,
  session TEXT NOT NULL,
  done    TINYINT DEFAULT 0,
  PRIMARY KEY (`id`)
);
