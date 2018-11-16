-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2018 at 10:54 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

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
(2, 400, '2018-11-15', 0, 14);

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
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `user_id` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`ID`, `capacity`, `room_class`, `room_id`, `created`, `date_from`, `date_to`, `user_id`) VALUES
(7, 0, NULL, 63, '2018-11-05', '2019-01-10', '2019-01-12', 3),
(8, 0, NULL, 63, '2018-11-05', '2019-01-10', '2019-01-12', 6),
(13, 3, 'suite', NULL, '2018-11-05', '2018-11-06', '2018-11-07', 3),
(14, 1, 'standard', NULL, '2018-11-05', '2018-11-07', '2018-11-08', 6),
(16, 3, 'suite', NULL, '2018-11-05', '2018-11-09', '2018-11-10', 3),
(17, 1, 'standard', NULL, '2018-11-05', '2018-11-10', '2018-11-11', 6);

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
(57, 2, 100, 'deluxe', 'booked'),
(58, 3, 200, 'suite', 'occupied'),
(59, 1, 300, 'standard', 'unavailable'),
(60, 2, 400, 'deluxe', 'free'),
(61, 3, 500, 'suite', 'booked'),
(62, 1, 600, 'standard', 'occupied'),
(63, 2, 700, 'deluxe', 'unavailable'),
(65, 1, 900, 'standard', 'booked'),
(66, 2, 1000, 'deluxe', 'occupied'),
(67, 3, 1100, 'suite', 'unavailable'),
(68, 1, 1200, 'standard', 'free'),
(69, 2, 1300, 'deluxe', 'booked'),
(70, 3, 1400, 'suite', 'occupied'),
(71, 1, 1500, 'standard', 'unavailable'),
(72, 2, 1600, 'deluxe', 'free'),
(73, 3, 1700, 'suite', 'booked'),
(74, 1, 1800, 'standard', 'occupied'),
(75, 2, 1900, 'deluxe', 'unavailable');

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
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `requests`
--
ALTER TABLE `requests`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

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
