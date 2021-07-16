CREATE TABLE `tb_timesheet` (
  `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `end` time NOT NULL,
  `start` time NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  CONSTRAINT `FKnu2y66l5lb7an7j1i8em6xywb` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`));