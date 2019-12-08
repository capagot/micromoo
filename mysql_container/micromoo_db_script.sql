-- DROP DATABASE IF EXISTS `micromoo_db`;
-- CREATE DATABASE `micromoo_db`;
-- USE `micromoo_db`;

SET NAMES utf8;
SET character_set_client = utf8mb4;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` char(60) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users (user_name, user_password, admin) VALUE ('admin', '123', true);
