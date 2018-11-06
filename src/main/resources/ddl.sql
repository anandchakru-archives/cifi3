drop
	database if exists cifi3;
create
	database cifi3;
use cifi3;

CREATE TABLE `app` (
	`appId` bigint(20) NOT NULL AUTO_INCREMENT,
	`appName` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `appPipe` (
	`appPipeId` bigint(20) NOT NULL AUTO_INCREMENT,
	`appId` bigint(20) NOT NULL,
	`agent` varchar(50) DEFAULT 'JENKINS',
	`url` varchar(255) DEFAULT NULL,
	`name` varchar(255) DEFAULT NULL,
	`user` varchar(255) DEFAULT NULL,
	`apiToken` varchar(255) DEFAULT NULL,
	`buildTriggerToken` varchar(255) DEFAULT NULL,
	`signVerifyToken` varchar(255) DEFAULT NULL,
	`botBuild` bit(1) DEFAULT b'1',
    `botBuildRegex` varchar(255) DEFAULT NULL,
    `botDeploy` bit(1) DEFAULT b'0',
    `botDeployCondition` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`appPipeId`),
	KEY `FKappPipeAppId` (`appId`),
	CONSTRAINT `FKappIdAppPipeAppId` FOREIGN KEY (`appId`) REFERENCES `app` (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `appRepo` (
	`appRepoId` bigint(20) NOT NULL AUTO_INCREMENT,
	`appId` bigint(20) NOT NULL,
	`agent` varchar(50) DEFAULT 'GITHUB',
	`url` varchar(255) DEFAULT NULL,
	`apiUrl` varchar(255) DEFAULT NULL,
	`apiToken` varchar(255) DEFAULT NULL,
	`signVerifyToken` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`appRepoId`),
	KEY `FKappRepoAppId` (`appId`),
	CONSTRAINT `FKappIdAppRepoAppId` FOREIGN KEY (`appId`) REFERENCES `app` (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `appNode` (
	`appNodeId` bigint(20) NOT NULL AUTO_INCREMENT,
	`appId` bigint(20) NOT NULL,
	`name` varchar(255) DEFAULT NULL,
	`ip` varchar(50) DEFAULT NULL,
	`liveCommit` varchar(50) DEFAULT NULL,
	`cluster` varchar(50) DEFAULT NULL,
	`startScript` varchar(255) DEFAULT NULL,
	`shutdownScript` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`appNodeId`),
	KEY `FKappNodeAppId` (`appId`),
	CONSTRAINT `FKappIdAppNodeAppId` FOREIGN KEY (`appId`) REFERENCES `app` (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `appHistory` (
	`appHistoryId` bigint(20) NOT NULL AUTO_INCREMENT,
	`appId` bigint(20) NOT NULL,
	`version` varchar(50) DEFAULT NULL,
	`tag` varchar(50) DEFAULT NULL,
	`commitId` varchar(150) DEFAULT NULL,
	`status` varchar(50) DEFAULT NULL,
	`assetUrl` varchar(2100) DEFAULT NULL,
	`assetId` varchar(50) DEFAULT NULL,
	PRIMARY KEY (`appHistoryId`),
	KEY `FKappNodeAppId` (`appId`),
	CONSTRAINT `FKappIdAppHistoryAppId` FOREIGN KEY (`appId`) REFERENCES `app` (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

