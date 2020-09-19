START TRANSACTION;
DROP DATABASE IF EXISTS `hibernate`;
CREATE DATABASE `hibernate`;

DROP TABLE IF EXISTS `hibernate`.`users`;
CREATE TABLE `hibernate`.`users` (`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NOT NULL, PRIMARY KEY (`id`));
  
INSERT INTO `hibernate`.`users` (`name`) VALUES ('Bob'), ('John'), ('Den');

DROP TABLE IF EXISTS `hibernate`.`products`;
CREATE TABLE `hibernate`.`products` (`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NOT NULL, `cost` INTEGER NOT NULL, PRIMARY KEY (`id`));
  
INSERT INTO `hibernate`.`products` (`name`, `cost`) VALUES ('Milk', 60), ('Cheese', 80), ('Bread', 25), ('Chocolate', 100), ('Meat', 250);

DROP TABLE IF EXISTS `hibernate`.`users_products`;
CREATE TABLE `hibernate`.`users_products` (`user_id` BIGINT UNSIGNED NOT NULL, `product_id` BIGINT UNSIGNED NOT NULL, PRIMARY KEY (`user_id`, `product_id`), FOREIGN KEY (`user_id`) REFERENCES `hibernate`.`users` (`id`), FOREIGN KEY (`product_id`) REFERENCES `hibernate`.`products` (`id`));
  
INSERT INTO `hibernate`.`users_products` (`user_id`, `product_id`) VALUES (1, 4), (1, 2), (2, 2), (2, 5), (2, 4), (2, 1), (3, 2), (3, 4), (3, 5);
COMMIT;