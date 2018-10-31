

CREATE TABLE `bookings` (
  `ID` mediumint(9) NOT NULL,
  `room_id` mediumint(9) NOT NULL,
  `user_id` mediumint(9) NOT NULL,
  `created` date NOT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `paid` tinyint(1) NOT NULL,
  `invoice_id` mediumint(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

CREATE TABLE `invoices` (
  `ID` mediumint(9) NOT NULL,
  `sum` double NOT NULL,
  `created` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

CREATE TABLE `requests` (
  `ID` mediumint(9) NOT NULL,
  `capacity` tinyint(4) NOT NULL,
  `room_class` enum('standard','deluxe','suite') COLLATE cp1251_bin NOT NULL,
  `room_id` mediumint(9) DEFAULT NULL,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

CREATE TABLE `rooms` (
  `ID` mediumint(9) NOT NULL,
  `capacity` tinyint(4) NOT NULL,
  `cost` double NOT NULL,
  `class` enum('standard','deluxe','suite') COLLATE cp1251_bin NOT NULL,
  `status` enum('free','booked','occupied','unavailable') COLLATE cp1251_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COLLATE=cp1251_bin;

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

ALTER TABLE `bookings`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `user` (`user_id`),
  ADD KEY `room` (`room_id`),
  ADD KEY `invoice` (`invoice_id`);

ALTER TABLE `invoices`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `requests`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `rooms`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `bookings`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT;

ALTER TABLE `invoices`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT;

ALTER TABLE `requests`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT;

ALTER TABLE `rooms`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `ID` mediumint(9) NOT NULL AUTO_INCREMENT;

ALTER TABLE `bookings`
  ADD CONSTRAINT `invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;
