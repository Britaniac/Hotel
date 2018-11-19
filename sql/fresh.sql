-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2018 at 07:56 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `ID` mediumint(9) NOT NULL,
  `sum` double NOT NULL,
  `created` date NOT NULL,
  `paid` tinyint(1) NOT NULL,
  `request_id` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`ID`, `sum`, `created`, `paid`, `request_id`) VALUES
(5, 900, '2018-11-19', 0, 39),
(11, 2700, '2018-11-19', 0, 41),
(12, 800, '2018-11-19', 0, 40);

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `ID` mediumint(9) NOT NULL,
  `capacity` tinyint(4) DEFAULT NULL,
  `room_class` enum('standard','deluxe','suite') COLLATE cp1251_bin DEFAULT NULL,
  `room_id` mediumint(9) DEFAULT NULL,
  `created` date NOT NULL,
  `duration` int(11) NOT NULL,
  `user_id` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`ID`, `capacity`, `room_class`, `room_id`, `created`, `duration`, `user_id`) VALUES
(39, 1, 'standard', 59, '2018-11-19', 3, 3),
(40, 3, 'deluxe', 77, '2018-11-19', 4, 6),
(41, 1, 'standard', 84, '2018-11-19', 3, 3),
(42, 2, 'deluxe', NULL, '2018-11-19', 3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `ID` mediumint(9) NOT NULL,
  `capacity` tinyint(4) NOT NULL,
  `cost` double NOT NULL,
  `class` enum('standard','deluxe','suite') COLLATE cp1251_bin NOT NULL,
  `status` enum('free','booked','occupied','unavailable') COLLATE cp1251_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`ID`, `capacity`, `cost`, `class`, `status`) VALUES
(57, 2, 100, 'deluxe', 'free'),
(58, 3, 200, 'suite', 'free'),
(59, 1, 300, 'standard', 'booked'),
(60, 2, 400, 'deluxe', 'free'),
(61, 3, 500, 'suite', 'free'),
(62, 1, 600, 'standard', 'free'),
(63, 2, 700, 'deluxe', 'free'),
(65, 1, 900, 'standard', 'free'),
(66, 2, 1000, 'deluxe', 'free'),
(67, 3, 1100, 'suite', 'free'),
(68, 1, 1200, 'standard', 'free'),
(69, 2, 1300, 'deluxe', 'free'),
(70, 3, 1400, 'suite', 'free'),
(71, 1, 1500, 'standard', 'free'),
(72, 2, 1600, 'deluxe', 'free'),
(73, 3, 1700, 'suite', 'free'),
(74, 1, 1800, 'standard', 'free'),
(75, 2, 1900, 'deluxe', 'free'),
(76, 2, 100, 'deluxe', 'free'),
(77, 3, 200, 'suite', 'booked'),
(78, 1, 300, 'standard', 'free'),
(79, 2, 400, 'deluxe', 'free'),
(80, 3, 500, 'suite', 'free'),
(81, 1, 600, 'standard', 'free'),
(82, 2, 700, 'deluxe', 'free'),
(83, 3, 800, 'suite', 'free'),
(84, 1, 900, 'standard', 'booked');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` mediumint(9) NOT NULL,
  `login` varchar(20) COLLATE cp1251_bin NOT NULL,
  `password` varchar(20) COLLATE cp1251_bin NOT NULL,
  `first_name` varchar(45) COLLATE cp1251_bin NOT NULL,
  `last_name` varchar(45) COLLATE cp1251_bin NOT NULL,
  `email` varchar(45) COLLATE cp1251_bin NOT NULL,
  `locale` varchar(3) COLLATE cp1251_bin DEFAULT NULL,
  `role` enum('manager','client') COLLATE cp1251_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `login`, `password`, `first_name`, `last_name`, `email`, `locale`, `role`) VALUES
(1, 'skov', '1234', 'Sergey', 'Koval', 'Koval@gmail.com', 'ru', 'manager'),
(3, 'skov2', '1234', 'das', 'das', 'dasd@mail.com', 'en', 'client'),
(6, 'das', 'ads', 'ads', 'ads', 'asd@m.com', 'en', 'client');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `request` (`request_id`);

--
-- Indexes for table `requests`
--
ALTER TABLE `requests`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `room` (`room_id`),
  ADD KEY `user` (`user_id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `invoices`
--
ALTER TABLE `invoices`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `request` FOREIGN KEY (`request_id`) REFERENCES `requests` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `requests`
--
ALTER TABLE `requests`
  ADD CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
