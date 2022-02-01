-- Adminer 4.7.7 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `command`;
CREATE TABLE `command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `command` (`id`, `name`) VALUES
(1,	'east'),
(2,	'south'),
(3,	'west'),
(4,	'north'),
(5,	'use'),
(6,	'open'),
(7,	'exit'),
(8,	'quit'),
(9,	'look');

DROP TABLE IF EXISTS `direction_command`;
CREATE TABLE `direction_command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `direction_command_ibfk_1` FOREIGN KEY (`id`) REFERENCES `command` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `direction_command` (`id`) VALUES
(1),
(2),
(3),
(4);

DROP TABLE IF EXISTS `exit_command`;
CREATE TABLE `exit_command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `exit_command_ibfk_1` FOREIGN KEY (`id`) REFERENCES `command` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `exit_command` (`id`) VALUES
(7),
(8);

DROP TABLE IF EXISTS `item_command`;
CREATE TABLE `item_command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `default_message` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `item_command_ibfk_1` FOREIGN KEY (`id`) REFERENCES `command` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `item_command` (`id`, `default_message`) VALUES
(5,	'You have no idea how to use that.'),
(6,	'This doesn\'t seem to open.');

DROP TABLE IF EXISTS `look_command`;
CREATE TABLE `look_command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `look_command_ibfk_1` FOREIGN KEY (`id`) REFERENCES `command` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `look_command` (`id`) VALUES
(9);

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `room` (`id`, `name`, `description`) VALUES
(1,	'bedroom',	'This is where you usually sleep. It\'s quite small, but at least the bed is comfy.'),
(2,	'bathroom',	'This is the bathroom. There\'s no windows in there, so it tends to get easily dank.'),
(3,	'kitchen',	'This is the kitchen. It still smells of yesterday\'s dinner.'),
(4,	'attic',	'Dusty.');

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT 1,
  `room_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `item` (`id`, `name`, `visible`, `room_id`) VALUES
(1,	'bed',	1,	1),
(3,	'curtains',	1,	1),
(4,	'shower',	1,	2),
(5,	'toothbrush',	1,	2),
(6,	'cookie',	1,	3),
(7,	'faucet',	1,	3),
(8,	'lift',	1,	4),
(9,	'lift',	1,	1);

DROP TABLE IF EXISTS `room_connection`;
CREATE TABLE `room_connection` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_room_id` int(10) unsigned NOT NULL,
  `to_room_id` int(10) unsigned NOT NULL,
  `direction_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `from_room_id_direction_id` (`from_room_id`,`direction_id`),
  KEY `to_room_id` (`to_room_id`),
  KEY `direction_id` (`direction_id`),
  CONSTRAINT `room_connection_ibfk_1` FOREIGN KEY (`from_room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_connection_ibfk_2` FOREIGN KEY (`to_room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE,
  CONSTRAINT `room_connection_ibfk_3` FOREIGN KEY (`direction_id`) REFERENCES `direction_command` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `room_connection` (`id`, `from_room_id`, `to_room_id`, `direction_id`) VALUES
(1,	1,	2,	1),
(2,	2,	1,	3),
(3,	1,	3,	4),
(4,	3,	1,	2);

DROP TABLE IF EXISTS `effect`;
CREATE TABLE `effect` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dtype` varchar(255) NOT NULL,
  `command_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `target_room_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `command_id` (`command_id`),
  KEY `item_id` (`item_id`),
  KEY `target_room_id` (`target_room_id`),
  CONSTRAINT `effect_ibfk_1` FOREIGN KEY (`command_id`) REFERENCES `item_command` (`id`) ON DELETE CASCADE,
  CONSTRAINT `effect_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE,
  CONSTRAINT `effect_ibfk_3` FOREIGN KEY (`target_room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `effect` (`id`, `dtype`, `command_id`, `item_id`, `message`, `target_room_id`) VALUES
(1,	'Message',	1,	1,	'You take a quick nap. You feel refreshed!',	NULL),
(2,	'Message',	2,	3,	'You open the curtains and take a look outside.',	NULL),
(3,	'ChangeRoom',	1,	9,	NULL,	4),
(4,	'ChangeRoom',	1,	8,	NULL,	1),
(5,	'Message',	1,	9,	'You call the lift.',	NULL);

-- 2022-01-24 10:44:14
