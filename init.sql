CREATE DATABASE IF NOT EXISTS sp_ts1
    COLLATE utf8_general_ci;

USE sp_ts1;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS ship;

CREATE TABLE ship
(
    id       BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NULL,
    planet   VARCHAR(50) NULL,
    shipType VARCHAR(9)  NULL,
    prodDate date        NULL,
    isUsed   BIT(1)      NULL,
    speed    DOUBLE      NULL,
    crewSize INT(4)      NULL,
    rating   DOUBLE      NULL,
    price    DOUBLE      NULL,
    PRIMARY KEY (id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE orders
(
    id       BIGINT(20)  NOT NULL AUTO_INCREMENT,
    ship_id  BIGINT(20)  NULL,
    state    BIT(1)      NULL,
    distance INT(5)      NULL,
    price    DOUBLE      NULL,
    PRIMARY KEY (id),
    CONSTRAINT ship_id
        FOREIGN KEY(ship_id) REFERENCES ship(id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

insert into ship(name, planet, shipType, prodDate, isUsed, speed, crewSize, rating, price)
values ('Orion III', 'Mars', 'MERCHANT', '2995-01-01', true, 0.82, 617, 1.31, 100.1)
     , ('Daedalus', 'Jupiter', 'MERCHANT', '3001-01-01', true, 0.94, 1619, 1.98, 10.1)
     , ('Eagle Transporter', 'Earth', 'TRANSPORT', '2989-01-01', true, 0.79, 4527, 1.02, 200.5)
     , ('F-302 Mongoose', 'Neptune', 'MILITARY', '3011-01-01', false, 0.24, 2170, 2.13, 340.5)
     , ('Excalibur', 'Mercury', 'MILITARY', '3011-01-01', false, 0.64, 128, 5.69, 20.2)
     , ('Explorer', 'Saturn', 'MERCHANT', '3007-01-01', false, 0.69, 4495, 4.25, 100.0)
     , ('Icarus I', 'Mercury', 'TRANSPORT', '2999-01-01', false, 0.07, 826, 0.27, 700.5)
     , ('Hermes', 'Venus', 'MERCHANT', '3010-01-01', false, 0.05, 445, 0.40, 10.2)
     , ('Odyssey', 'Neptune', 'TRANSPORT', '2988-01-01', false, 0.44, 1436, 1.10, 30.5)
     , ('Orbit Jet', 'Venus', 'TRANSPORT', '3011-01-01', false, 0.55, 1931, 4.89, 290.2)
     , ('Aries Ib', 'Saturn', 'MILITARY', '3013-01-01', true, 0.37, 3562, 2.11, 900.5)
     , ('Hunter IV', 'Jupiter', 'MILITARY', '3010-01-01', false, 0.71, 4379, 5.68, 600.1)
     , ('Serenity', 'Saturn', 'TRANSPORT', '3008-01-01', false, 0.92, 1588, 6.13, 80.9)
     , ('Scorpio E-X-1', 'Mars', 'MERCHANT', '3014-01-01', false, 0.03, 682, 0.40, 10000.0)
     , ('Mark IX Hawk', 'Jupiter', 'MILITARY', '3003-01-01', true, 0.58, 927, 1.36, 70.1)
     , ('Excelsior', 'Venus', 'MILITARY', '3013-01-01', true, 0.45, 3488, 2.57, 800.5)
     , ('Amaterasu', 'Saturn', 'MILITARY', '3007-01-01', true, 0.88, 1517, 2.71, 89.1)
     , ('USS Cygnus', 'Jupiter', 'TRANSPORT', '3005-01-01', false, 0.74, 3129, 3.95, 30.3)
     , ('Argonaut', 'Jupiter', 'MERCHANT', '3002-01-01', false, 0.53, 4897, 2.36, 2000.1)
     , ('Avalon', 'Mars', 'TRANSPORT', '3000-01-01', false, 0.91, 4660, 3.64, 207.4);