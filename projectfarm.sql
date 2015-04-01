-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2015-03-31 20:28:12
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
-- 表的结构 `document`
--

CREATE TABLE IF NOT EXISTS `document` (
  `documentPath` varchar(1000) NOT NULL,
  `added` datetime NOT NULL,
  `projectAcronym` varchar(100) NOT NULL,
  PRIMARY KEY (`documentPath`),
  KEY `projectAcronym` (`projectAcronym`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `document`
--

INSERT INTO `document` (`documentPath`, `added`, `projectAcronym`) VALUES
('E:\\Lernen\\ESIGELEC\\2eme annee\\J2EE_Fuckner\\JEECourse\\ProjectFarm\\WebContent\\documents\\upload\\test 0327 2208\\LAC AALS SEPT 2013 YUAN JI0001.pdf', '2015-03-28 00:26:15', 'test 0327 2208'),
('E:\\Lernen\\ESIGELEC\\2eme annee\\J2EE_Fuckner\\JEECourse\\ProjectFarm\\WebContent\\documents\\upload\\test 0331 1143\\18mA4.pdf', '2015-03-31 11:44:54', 'test 0331 1143'),
('E:\\Lernen\\ESIGELEC\\2eme annee\\J2EE_Fuckner\\JEECourse\\ProjectFarm\\WebContent\\documents\\upload\\test 0328 0010\\LAC AALS SEPT 2013 YUAN JI0001.pdf', '2015-03-28 00:11:00', 'test 0328 0010');

-- --------------------------------------------------------

--
-- 表的结构 `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
  `acronymProj` varchar(100) NOT NULL,
  `riskLevel` int(11) NOT NULL,
  `atractiveness` int(11) NOT NULL,
  `emailEvaluator` varchar(100) NOT NULL,
  PRIMARY KEY (`acronymProj`,`emailEvaluator`),
  KEY `emailEvaluator` (`emailEvaluator`),
  KEY `idProject` (`acronymProj`,`emailEvaluator`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `evaluation`
--

INSERT INTO `evaluation` (`acronymProj`, `riskLevel`, `atractiveness`, `emailEvaluator`) VALUES
('test 0328 0010', 1, 3, '0@geek.com'),
('test 0331 1143', 5, 2, '0@geek.com'),
('test 0327 2208', 2, 4, '0@geek.com'),
('test 0331 1143', 3, 1, 'sarah@geek.com'),
('test 0328 0010', 5, 1, 'sarah@geek.com'),
('test 0327 2208', 3, 2, 'sarah@geek.com');

-- --------------------------------------------------------

--
-- 表的结构 `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `acronym` varchar(100) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `budget` int(11) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `emailOwner` varchar(100) NOT NULL,
  `category` varchar(20) NOT NULL,
  PRIMARY KEY (`acronym`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `project`
--

INSERT INTO `project` (`acronym`, `description`, `budget`, `created`, `emailOwner`, `category`) VALUES
('test 0327 2208', 'test', 100, '2015-03-27 22:09:36', 'john@acme.com', 'Apps'),
('test 0331 1143', 'test', 7000000, '2015-03-31 11:43:40', 'john@acme.com', 'Information Systems'),
('test 0328 0010', 'test', 4564564, '2015-03-28 00:10:51', 'john@acme.com', 'Apps'),
('test', 'teststst', 5000000, '2015-03-31 12:15:34', 'john@acme.com', 'Apps'),
('tesafadsf', 'adsfasdfsda', 4564564, '2015-03-31 12:15:49', 'john@acme.com', 'Apps'),
('dafadsfewr', 'adsfadsfasdfsa', 123123, '2015-03-31 12:16:06', 'john@acme.com', 'Apps'),
('tesawtesat', 'dfasfadsfdsaf', 123, '2015-03-31 12:16:13', 'john@acme.com', 'Apps'),
('testeastesatastas', 'fdsafsdaf', 4564564, '2015-03-31 12:16:22', 'john@acme.com', 'Apps'),
('ewrwqrwqer', 'sdafdsafdfd', 100, '2015-03-31 12:16:28', 'john@acme.com', 'Apps'),
('adfasdf', 'dfsdafdsafdsa', 123, '2015-03-31 12:16:38', 'john@acme.com', 'Apps'),
('dfdfdfd', 'dsfasdfdsafadsfasdfsdaf', 100, '2015-03-31 12:16:45', 'john@acme.com', 'Apps'),
('sdfsdaf', 'sdafsdafsdaf', 123, '2015-03-31 12:16:51', 'john@acme.com', 'Apps'),
('dsafadsf', 'sdafasdfsadfds', 123, '2015-03-31 12:16:57', 'john@acme.com', 'Apps'),
('sdafdsaf', 'sdafadsfa', 100, '2015-03-31 12:17:02', 'john@acme.com', 'Apps'),
('dsfd', 'dsf', 100, '2015-03-31 12:17:08', 'john@acme.com', 'Apps'),
('dsfdfd', 'dfdf', 100, '2015-03-31 12:17:17', 'john@acme.com', 'Apps'),
('dfdsfd', 'dsfdfdf', 100, '2015-03-31 12:17:23', 'john@acme.com', 'Apps'),
('dfd', 'dfdfdfdf', 100, '2015-03-31 12:17:30', 'john@acme.com', 'Apps'),
('ewqrqwe', 'eqrew', 100, '2015-03-31 12:17:36', 'john@acme.com', 'Apps'),
('ere', 'erqwrwer', 123, '2015-03-31 12:17:42', 'john@acme.com', 'Apps'),
('eqreqwr', 'ewrwqer', 123, '2015-03-31 12:18:01', 'john@acme.com', 'Apps'),
('qewr', 'eqwrqwe', 123, '2015-03-31 12:18:06', 'john@acme.com', 'Apps'),
('ewreqw', 'weqrqwe', 123, '2015-03-31 12:18:11', 'john@acme.com', 'Apps'),
('eqwrqwe', 'ereqwr', 4564564, '2015-03-31 12:18:17', 'john@acme.com', 'Apps'),
('ewrwqer', 'eqrqwerwqe', 100, '2015-03-31 12:18:22', 'john@acme.com', 'Apps'),
('erwe', 'weqrqwerqw', 100, '2015-03-31 12:18:29', 'john@acme.com', 'Apps'),
('ewrer', 'ewrqwerqw', 123, '2015-03-31 12:18:34', 'john@acme.com', 'Apps'),
('eqwrqwerer', 'eqwrqwerqwerqew', 123, '2015-03-31 12:18:43', 'john@acme.com', 'Apps'),
('erre', 'eqwrqerqewreqw', 123, '2015-03-31 12:18:50', 'john@acme.com', 'Apps'),
('re', 'reqreqwrqwe', 100, '2015-03-31 12:18:55', 'john@acme.com', 'Apps'),
('q', 'reqqwe', 123, '2015-03-31 12:19:00', 'john@acme.com', 'Apps'),
('r', 'eqwrreq', 100, '2015-03-31 12:19:07', 'john@acme.com', 'Apps'),
('qqtqerqe', 'asadfd', 100, '2015-03-31 12:19:13', 'john@acme.com', 'Apps'),
('23423', 'qwerqwe', 12412, '2015-03-31 12:19:20', 'john@acme.com', 'Apps');

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
('sarah@geek.com', 'Sarah Logan', '456', 1),
('thibault@geek.com', 'Thibault Moulin', '456', 1),
('george@geek.com', 'George Papalodeminus', '456', 1),
('0@geek.com', 'Ji Yuan', '456', 1),
('1@geek.com', 'Ji Yuan', '456', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
