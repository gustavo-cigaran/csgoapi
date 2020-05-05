DROP TABLE IF EXISTS grenade;

CREATE TABLE grenade (
        id_grenade BIGINT AUTO_INCREMENT NOT NULL,
        model VARCHAR(255) NULL,
        team VARCHAR(255) NULL,
        price VARCHAR(255) NULL,
        munition VARCHAR(255) NULL,
        PRIMARY KEY (id_grenade));

DROP TABLE IF EXISTS heavy;

CREATE TABLE heavy (
        id_heavy BIGINT AUTO_INCREMENT NOT NULL,
        model VARCHAR(255) NULL,
        team VARCHAR(255) NULL,
        price VARCHAR(255) NULL,
        munition VARCHAR(255) NULL,
        PRIMARY KEY (id_heavy));

DROP TABLE IF EXISTS pistol;

CREATE TABLE pistol (
        id_pistol BIGINT AUTO_INCREMENT NOT NULL,
        model VARCHAR(255) NULL,
        team VARCHAR(255) NULL,
        price VARCHAR(255) NULL,
        munition VARCHAR(255) NULL,
        PRIMARY KEY (id_pistol));

DROP TABLE IF EXISTS rifle;

CREATE TABLE rifle (
        id_rifle BIGINT AUTO_INCREMENT NOT NULL,
        model VARCHAR(255) NULL,
        team VARCHAR(255) NULL,
        price VARCHAR(255) NULL,
        munition VARCHAR(255) NULL,
        PRIMARY KEY (id_rifle));

DROP TABLE IF EXISTS smg;

CREATE TABLE smg (
        id_smg BIGINT AUTO_INCREMENT NOT NULL,
        model VARCHAR(255) NULL,
        team VARCHAR(255) NULL,
        price VARCHAR(255) NULL,
        munition VARCHAR(255) NULL,
        PRIMARY KEY (id_smg));