-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 04, 2018 at 10:43 AM
-- Server version: 5.6.38
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `pd_db`
--


-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE `note` (
  `note_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  `note` longtext NOT NULL,
  `provider_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`note_id`, `request_id`, `note`, `provider_id`) VALUES
  (1, 1, 'Well this is interesting.', 2),
  (2, 1, 'This seams a bit weird.', 1);

-- --------------------------------------------------------

--
-- Table structure for table `organization`
--

CREATE TABLE `organization` (
  `organization_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `organization_type` varchar(255) NOT NULL,
   `lat`  int(11) DEFAULT NULL,
   `long`  int(11) DEFAULT NULL,
   `min_daily_capacity`  int(11) DEFAULT 0,
   `max_daily_capacity`  int(11) DEFAULT 0,
   `price_per_unit`  int(11) DEFAULT 0,
   `total_price`  int(11)  DEFAULT 0,
   `at_home_service_radius`  int(11) DEFAULT NULL,
  `image` longblob DEFAULT NULL,
  `working_hours_id` int(11) DEFAULT NULL,
  `provider_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organization`
--

INSERT INTO `organization` (`organization_id`,`organization_type`, `provider_id`, `name`,`lat`,`long`, `min_daily_capacity`, `max_daily_capacity`, `price_per_unit`,`total_price`, `at_home_service_radius`) VALUES
  (1,'VENUE', 1, 'Event lawn 1', 28.7175947,77.1024348,null, 1000, null, 100000, null ),
  (2,'VENUE', 2, 'Event lawn 2',28.7005268,76.878112,null, 700, null, 70000, null ),
  (3,'VENUE', 3, 'Event lawn 3',28.7566421,76.9575441,null, 1500, null, 120000, null ),
  (4,'VENUE', 4, 'Event lawn 4',28.210508,77.0602773,null, 400, null, 35000, null ),
  (5,'VENUE', 2, 'Event hall 1',28.445478, 76.940545,null, 600, null, 55000, null ),
  (6,'VENUE', 3, 'Event hall 2',28.611766, 77.648382,null, 200, null, 30000, null ),
  (7,'VENUE', 4, 'Event hall 3',28.541972, 77.233456,null, 100, null, 9900, null ),
  (8,'VENUE', 1, 'Birthday venue 1',28.563012, 77.136509,null, 50, null, 2000, null ),
  (9,'VENUE', 2, 'Birthday venue 2',28.616661, 77.359777,null, 200, null, 11000, null ),
  (10,'VENUE', 11,'Birthday venue lawn 4',28.855445, 76.649048,null, 700, null, 70000, null ),
  (11, 'FOOD', 1, 'Caterer 1', 28.7175947,77.1024348,200, 1000, 1000, null, 100 ),
  (12, 'FOOD', 2, 'Caterer 2',28.7005268,76.878112,100, 700, 800, null, 100 ),
  (13, 'FOOD', 3, 'Caterer 3',28.7566421,76.9575441,500, 1500, 700, null, 100 ),
  (14, 'FOOD', 4, 'Caterer 4',28.210508,77.0602773,50, 400, 900, null, 100 ),
  (15, 'FOOD', 11, 'International cuisine 1',28.445478, 76.940545,100, 600, 1000, null, 100 ),
  (16, 'FOOD', 3, 'International cuisine 2',28.611766, 77.648382,50, 200, 800, null, 100 ),
  (17, 'FOOD', 4, 'International cuisine 3',28.541972, 77.233456,30, 100, 700, null, 100 ),
  (18, 'FOOD', 1, 'Desi tadka 1',28.563012, 77.136509,10, 50, 400, null, 100 ),
  (19, 'FOOD', 2, 'Desi tadka 2',28.616661, 77.359777,50, 200, 500, null, 100 ),
  (20, 'FOOD', 1,'Desi tadka 4',28.855445, 76.649048,100, 700, 400, null, 400 ),
  (21, 'DECOR', 1, 'Decor 1', 28.7175947,77.1024348,200, 1000, 1000, null, 100 ),
  (22, 'DECOR', 2, 'Decor 2',28.7005268,76.878112,100, 700, 800, null, 100 ),
  (23, 'DECOR', 3, 'Decor 3',28.7566421,76.9575441,500, 1500, 700, null, 100 ),
  (24, 'DECOR', 4, 'Decor 4',28.210508,77.0602773,50, 400, 900, null, 100 ),
  (25, 'DECOR', 2, 'International Decor 1',28.445478, 76.940545,100, 600, 1000, null, 100 ),
  (26, 'DECOR', 3, 'International Decor 2',28.611766, 77.648382,50, 200, 800, null, 100 ),
  (27, 'DECOR', 4, 'International Decor 3',28.541972, 77.233456,30, 100, 700, null, 100 ),
  (28, 'DECOR', 1, 'Desi Decor 1',28.563012, 77.136509,10, 50, 400, null, 100 ),
  (29, 'DECOR', 2, 'Desi Decor 2',28.616661, 77.359777,50, 200, 500, null, 100 ),
  (30, 'DECOR', 1,'Desi Decor 4',28.855445, 76.649048,100, 700, 400, null, 400 ),
  (31, 'GROOM', 11, 'Stylist 1', 28.7175947,77.1024348,200, 1000, 1000, null, 100 ),
  (32, 'GROOM', 2, 'Stylist 2',28.7005268,76.878112,100, 700, 800, null, 100 ),
  (33, 'GROOM', 3, 'Stylist 3',28.7566421,76.9575441,500, 1500, 700, null, 100 ),
  (34, 'GROOM', 4, 'Stylist 4',28.210508,77.0602773,50, 400, 900, null, 100 ),
  (35, 'GROOM', 2, 'International Stylist 1',28.445478, 76.940545,100, 600, 1000, null, 100 ),
  (36, 'GROOM', 3, 'International Stylist 2',28.611766, 77.648382,50, 200, 800, null, 100 ),
  (37, 'GROOM', 4, 'International Stylist 3',28.541972, 77.233456,30, 100, 700, null, 100 ),
  (38, 'GROOM', 11, 'Desi Stylist 1',28.563012, 77.136509,10, 50, 400, null, 100 ),
  (39, 'GROOM', 2, 'Desi Stylist 2',28.616661, 77.359777,50, 200, 500, null, 100 ),
  (40, 'GROOM', 1,'Desi Stylist 4',28.855445, 76.649048,100, 700, 400, null, 400 );


-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `name`, `type`) VALUES
  (1, 'client', '1'),
  (2, 'provider', '2');
-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL,
  `contract_id` int(11) NOT NULL,
  `start_time` datetime,
  `end_time` datetime
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`request_id`, `contract_id`, `start_time`, `end_time`) VALUES
  (1, 1, '2019-04-11 18:00:00', '2019-04-11 20:00:00'),
  (2, 1, '2019-01-11 18:00:00', '2019-01-11 21:00:00'),
  (3, 1, '2019-02-01 18:00:00', '2019-02-01 21:00:00'),
  (4, 1, '2018-12-01 18:00:00', '2018-12-01 21:00:00'),
  (5, 2, '2018-11-01 18:00:00', '2018-11-01 21:00:00'),
  (6, 2, '2019-03-01 18:00:00', NULL);


-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

CREATE TABLE `contract` (
  `contract_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `start_time` datetime,
  `end_time` datetime,
  `guests` int(11)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contract`
--

INSERT INTO `contract` (`contract_id`, `client_id`, `organization_id`) VALUES
  (1, 5, 1),
  (2, 6, 2),
  (3, 7, 2),
  (4, 8, 4),
  (5, 9, 3),
  (6, 10, 4),
  (7, 5, 11),
  (8, 6, 12),
  (9, 7, 13),
  (10, 8, 14),
  (11, 9, 14),
  (12, 10, 19),
  (13, 5, 23),
  (14, 6, 24),
  (15, 7, 23),
  (16, 8, 22),
  (17, 9, 23),
  (18, 10, 29),
  (19, 5, 31),
  (20, 6, 36),
  (21, 7, 38),
  (22, 8, 40),
  (23, 9, 40),
  (24, 10, 40);
-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `long` float DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `password_confirm` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `User`
--

INSERT INTO `user` (`user_id`, `username`, `email`, `role_id`, `lat`, `long`) VALUES
  (1, 'anuj', 'anuj@eventtitan.com', 2, null, null),
  (2, 'tinku', 'tinku@eventtitan.com', 2, null, null ),
  (3, 'ruchir', 'ruchir@eventtitan.com', 2, null, null ),
  (4, 'peeyush', 'peeyush@eventtitan.com', 2,  null, null),
  (5, 'client1', 'client1@eventtitan.com', 1, 28.704060, 77.102493),
  (6, 'client2', 'client2@eventtitan.com', 1, 28.573839, 77.219391),
  (7, 'client3', 'client3@eventtitan.com', 1, 28.459497, 77.026634),
  (8, 'client4', 'client4@eventtitan.com', 1, 28.853960, 77.091782),
  (9, 'client5', 'client5@eventtitan.com', 1, 28.704060, 77.102493),
  (10, 'client6', 'client6@eventtitan.com', 1, 28.573839, 77.219391),
  (11, 'hitender', 'hitender@eventtitan.com', 2, 28.209, 76.918);



-- --------------------------------------------------------

--
-- Table structure for table `working_hours`
--

CREATE TABLE `working_hours` (
  `working_hours_id` int(11) NOT NULL,
  `from` TIME DEFAULT NULL ,
  `to` TIME DEFAULT NULL ,
  `weekly_days_off` varchar(22) DEFAULT  NULL,
  `other_days_off` DATE  DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Indexes for dumped tables
--


--
-- Indexes for table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`note_id`),
  ADD KEY `fk_RequestID_idx` (`request_id`),
  ADD KEY `fk_UserID_idx` (`provider_id`);

--
-- Indexes for table `organization`
--
ALTER TABLE `organization`
  ADD PRIMARY KEY (`organization_id`),
  ADD KEY `fk_UserID_idx` (`provider_id`);


--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);


--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `fk_ContractID_idx` (`contract_id`);

--
-- Indexes for table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`contract_id`),
  ADD KEY `fk_UserID_Client_idx` (`client_id`),
  ADD KEY `fk_UserID_organization_idx` (`organization_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`),
  ADD KEY `roleID_idx` (`role_id`);


--
-- Indexes for table `working_hours`
--
ALTER TABLE `working_hours`
  ADD PRIMARY KEY (`working_hours_id`);


--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `note`
--
ALTER TABLE `note`
  MODIFY `note_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `organization`
--
ALTER TABLE `organization`
  MODIFY `organization_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `contract`
--
ALTER TABLE `contract`
  MODIFY `contract_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;


--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_organization`
--
ALTER TABLE `working_hours`
  MODIFY `working_hours_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `fk_RequestID` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_UserID` FOREIGN KEY (`provider_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `fk_ContractID` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `fk_UserID_Client` FOREIGN KEY (`client_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_UserID_org` FOREIGN KEY (`organization_id`) REFERENCES `organization` (`organization_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;



--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_User_Role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `organization`
--
ALTER TABLE `organization`
  ADD CONSTRAINT `fk_Organization_Working_Hours` FOREIGN KEY (`working_hours_id`) REFERENCES `working_hours` (`working_hours_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Organization_UserID` FOREIGN KEY (`provider_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;


