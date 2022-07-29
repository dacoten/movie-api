# Movie API

The movie APIs that users can sign up and sign in and use JWT to access to resources of the application.
The application also has the vote or unvote function for each movie you like

## Technical

-   Spring Boot (Spring JPA, Spring security)
-   MySQl

## Schema

```sql
CREATE TABLE `votes` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`created_at` datetime DEFAULT NULL,
	`updated_at` datetime DEFAULT NULL,
	`voted_name` varchar(60) DEFAULT NULL,
	`movie_id` bigint NOT NULL,
	`user_id` bigint NOT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UKn4vslqrdxtpvlejdsnb49cnta` (`movie_id`,`user_id`),
KEY `FKli4uj3ic2vypf5pialchj925e` (`user_id`),
CONSTRAINT `FKli4uj3ic2vypf5pialchj925e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
CONSTRAINT `FKtc6nlbt0oupxovthgkwph25vm` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `movies` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`created_at` datetime DEFAULT NULL,
	`updated_at` datetime DEFAULT NULL,
	`created_by` bigint DEFAULT NULL,
	`updated_by` bigint DEFAULT NULL,
	`description` varchar(255) DEFAULT NULL,
	`movie_url` varchar(255) DEFAULT NULL,
	`title` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`created_at` datetime DEFAULT NULL,
	`updated_at` datetime DEFAULT NULL,
	`email` varchar(40) DEFAULT NULL,
	`password` varchar(100) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

> CREATE TABLE `roles` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(60) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_roles` (
	`user_id` bigint NOT NULL,
	`role_id` bigint NOT NULL,
PRIMARY KEY (`user_id`,`role_id`),
KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## Default Roles

The spring boot app uses role based authorization powered by spring security. To add the default roles in the database, you should run command after running the application -

    ```sql
    INSERT IGNORE INTO roles(name) VALUES('ROLE_USER');
    INSERT IGNORE INTO roles(name) VALUES('ROLE_ADMIN');
    ```

Any new user who signs up to the app is assigned the `ROLE_USER` by default.
