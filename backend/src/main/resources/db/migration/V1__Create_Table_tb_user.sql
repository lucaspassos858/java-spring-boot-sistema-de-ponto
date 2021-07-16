CREATE TABLE `tb_user` (
  `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  UNIQUE KEY `UK_4vih17mube9j7cqyjlfbcrk4m` (`email`));