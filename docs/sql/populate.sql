DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `title` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_blchllk6rtlb8h5n38wie0m5y` (`title` (100))
) ENGINE = MyISAM AUTO_INCREMENT = 8 DEFAULT CHARSET = utf8mb4;

INSERT INTO
    `categories`
VALUES
    (1, 'Informatica'),
    (2, 'Cursos'),
    (3, 'Eletrodomésticos'),
    (4, 'Eletronicos'),
    (5, 'Livros'),
    (6, 'Móveis'),
    (7, 'Cama, Mesa e Banho');

/*!40000 ALTER TABLE `categories` ENABLE KEYS */
;

UNLOCK TABLES;

DROP TABLE IF EXISTS `sales`;

CREATE TABLE `sales` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL,
    `register_date` datetime DEFAULT NULL,
    `likes` int DEFAULT '0',
    `image_link` varchar(255) DEFAULT NULL,
    `sale_link` varchar(255) NOT NULL,
    `sale_price` decimal(19, 2) NOT NULL,
    `sale_site` varchar(255) NOT NULL,
    `title` varchar(255) NOT NULL,
    `category_fk` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK3bmg0833cdjdfaxom9l46thge` (`category_fk`)
) ENGINE = MyISAM AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4;