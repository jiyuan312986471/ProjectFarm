-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-03-18 14:08:33
-- 服务器版本： 5.6.15-log
-- PHP Version: 5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `projectfarm`
--

-- --------------------------------------------------------

--
-- 表的结构 `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `description` varchar(20) NOT NULL,
  PRIMARY KEY (`description`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `document`
--

CREATE TABLE IF NOT EXISTS `document` (
  `idDocument` int(11) NOT NULL AUTO_INCREMENT,
  `documentPath` varchar(1000) NOT NULL,
  `added` date NOT NULL,
  PRIMARY KEY (`idDocument`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
  `idProject` int(11) NOT NULL,
  `riskLevel` int(11) NOT NULL,
  `atractiveness` int(11) NOT NULL,
  `emailEvaluator` varchar(100) NOT NULL,
  PRIMARY KEY (`idProject`,`emailEvaluator`),
  KEY `emailEvaluator` (`emailEvaluator`),
  KEY `idProject` (`idProject`,`emailEvaluator`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- 表的结构 `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ssn` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` longblob,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `idProject` int(11) NOT NULL AUTO_INCREMENT,
  `acronym` varchar(100) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `fundingDurationDays` int(11) NOT NULL,
  `budget` int(11) NOT NULL,
  `created` date NOT NULL,
  `emailOwner` varchar(100) NOT NULL,
  `category` varchar(20) NOT NULL,
  PRIMARY KEY (`idProject`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `project`
--

INSERT INTO `project` (`idProject`, `acronym`, `description`, `fundingDurationDays`, `budget`, `created`, `emailOwner`, `category`) VALUES
(1, 'test', 'test 2015.03.17 23:55', 0, 100, '2015-03-17', 'john@acme.com', 'Information Systems');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `userType` int(11) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`email`, `name`, `password`, `userType`) VALUES
('john@acme.com', 'John Silver', '123', 0),
('mary@acme.com', 'Mary Moon', '123', 0),
('paul@acme.com', 'Paul McDonalds', '123', 0),
('sarah@geek.com', 'Sarah Logan', '456', 0),
('thibault@geek.com', 'Thibault Moulin', '456', 0),
('george@geek.com', 'George Papalodeminus', '456', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
