-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2024 at 04:19 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dauvan_transport`
--

-- --------------------------------------------------------

--
-- Table structure for table `major_vehicle_services`
--

CREATE TABLE `major_vehicle_services` (
  `major_service_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vehicle_make` varchar(255) DEFAULT NULL,
  `vehicle_model` varchar(255) DEFAULT NULL,
  `vehicle_year` varchar(255) DEFAULT NULL,
  `vin_number` varchar(17) DEFAULT NULL,
  `issue_description` varchar(255) DEFAULT NULL,
  `scheduled_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `major_vehicle_services`
--

INSERT INTO `major_vehicle_services` (`major_service_id`, `user_id`, `vehicle_make`, `vehicle_model`, `vehicle_year`, `vin_number`, `issue_description`, `scheduled_date`, `created_at`) VALUES
(1, NULL, 'BMW', 'X3', '2009', 'HFKYIU56K78893J34', 'The headlights have been damaged and need replacing.', '2024-08-18', '2024-11-19 17:51:49'),
(2, NULL, 'Ferrari', 'F40', '1993', '3fe5w1few6f4ew6f8', 'The headlights of the Ferrari is not working anymore. So I need it to be fixed or even replaced.', '2024-10-22', '2024-11-20 09:43:37'),
(3, 3, 'Bugatti', 'Veyron', '2008', 'c653r44c15f65cfc6', 'The rear rims are damaged and the breaks also needs to be replaced. Please fix this issue.', '2024-11-21', '2024-11-20 11:32:29');

-- --------------------------------------------------------

--
-- Table structure for table `transportation`
--

CREATE TABLE `transportation` (
  `transportID` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `items` text NOT NULL,
  `weight_of_items` decimal(10,2) NOT NULL,
  `size_of_items` varchar(255) NOT NULL,
  `pickup_location` varchar(255) NOT NULL,
  `dropoff_location` varchar(255) NOT NULL,
  `scheduled_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` enum('pending','processing','completed') DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transportation`
--

INSERT INTO `transportation` (`transportID`, `user_id`, `items`, `weight_of_items`, `size_of_items`, `pickup_location`, `dropoff_location`, `scheduled_date`, `created_at`, `status`) VALUES
(1, NULL, 'Metal Pipes', 2000.00, '10/20/5', 'Boksburg', 'Bedfordview', '2024-08-19', '2024-11-19 16:02:00', 'pending'),
(2, NULL, 'Porsche 911 Turbo S', 1800.00, '1.5/3/2', 'Boksburg', 'Cape town', '2024-10-03', '2024-11-19 21:56:36', 'pending'),
(3, NULL, 'Air Force One', 100.00, '2/3/2', 'Durban', 'Port Elizabeth', '2024-05-16', '2024-11-20 08:30:21', 'pending'),
(4, NULL, 'Lego', 2000.00, '5/10/3', 'Centurion', 'Kempton Park', '2024-12-10', '2024-11-20 09:18:13', 'pending'),
(5, NULL, 'Oil Containers', 5000.00, '3/5/3', 'Boksburg', 'Durban', '2024-05-06', '2024-11-20 10:45:29', 'pending'),
(6, 5, 'Lego Bricks', 3000.00, '5/11/30', 'Bloemfontein', 'Heildleburg', '2024-05-13', '2024-11-20 10:48:28', 'pending'),
(7, 3, 'Make Up Products', 1000.00, '10/2/5', 'Boksburg', 'Brakpan', '2024-05-06', '2024-11-20 11:00:03', 'pending'),
(8, 3, 'Barbie dolls', 1000.00, '5/3/5', 'Pretoria', 'Springs', '2024-11-21', '2024-11-20 15:15:17', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `VATNumber` varchar(9) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `Name`, `VATNumber`, `phone`, `Email`, `password`, `created_at`) VALUES
(1, 'timmy', '012345678', '0769816886', 'timmy@gmail.com', 'timmy123', '2024-11-16 11:38:04'),
(2, 'dom', '569835421', '0769819988', 'dom@gmail.com', '$2y$10$0ynbpsTQqUPE2EgzDG6hnuTwFWE4tO35TmFTzTlj5IG4AG.uk6e6C', '2024-11-16 13:21:42'),
(3, 'Domie', '051032105', '0769817554', 'dom1@gmail.com', 'Dominique123', '2024-11-16 13:27:48'),
(4, 'micaela', '663355221', '0254696633', 'micaela@gmail.com', '$2y$10$qKcw.Oro9gs2.cAuar6xG.X0OQHj055.QFH5seMxUGuckcpbRRL1O', '2024-11-16 13:37:14'),
(5, 'claudio', '325641078', '0769816552', 'claudio180801@gmail.com', '$2y$10$1LUe8B/VCLrzKrgEgwVaa.BMTLNwxNa.qIYfX0fewJg7YRFTbmrna', '2024-11-16 19:59:39'),
(6, 'something', '458763201', '5468953366', 'something@gmail.com', '$2y$10$W3Nv8Y6N0Ufvd8z6.03koez8nnSXFcvns2Tq9Rhq7Q10HgUkmcsIy', '2024-11-16 20:03:54'),
(7, 'something2', '336652013', '5462585577', 'something2@gmail.com', '$2y$10$1E1bd2Yb0ej2wmYY5VEA9.rv9GSRVxh8lTAdfjkdZENen0Fo1XGc.', '2024-11-16 20:15:17'),
(8, 'newUser', '354128566', '1542365522', 'newuser@gmail.com', '$2y$10$9y0/5.OiWWKdG21D/NPT3.NNWT8z.AYoGzuANqpoL/k2RYgwKwZca', '2024-11-20 08:28:36');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_services`
--

CREATE TABLE `vehicle_services` (
  `service_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vehicle_make` varchar(255) DEFAULT NULL,
  `vehicle_model` varchar(255) DEFAULT NULL,
  `vehicle_year` varchar(255) DEFAULT NULL,
  `vin_number` varchar(17) DEFAULT NULL,
  `scheduled_date` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicle_services`
--

INSERT INTO `vehicle_services` (`service_id`, `user_id`, `vehicle_make`, `vehicle_model`, `vehicle_year`, `vin_number`, `scheduled_date`, `created_at`) VALUES
(1, NULL, 'BMW', 'X3', '2007', 'H4B86J6H34GFNVHJG', '2021-08-15', '2024-11-19 17:45:16'),
(2, NULL, 'Ferrari', 'F40', '1995', 'd5234d23d16231d52', '2024-08-18', '2024-11-20 09:42:50'),
(3, 3, 'Ferrari', 'California', '2008', 'b543h5u35793 t79c', '2024-11-21', '2024-11-20 11:31:28');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `major_vehicle_services`
--
ALTER TABLE `major_vehicle_services`
  ADD PRIMARY KEY (`major_service_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transportation`
--
ALTER TABLE `transportation`
  ADD PRIMARY KEY (`transportID`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`Email`);

--
-- Indexes for table `vehicle_services`
--
ALTER TABLE `vehicle_services`
  ADD PRIMARY KEY (`service_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `major_vehicle_services`
--
ALTER TABLE `major_vehicle_services`
  MODIFY `major_service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transportation`
--
ALTER TABLE `transportation`
  MODIFY `transportID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `vehicle_services`
--
ALTER TABLE `vehicle_services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `major_vehicle_services`
--
ALTER TABLE `major_vehicle_services`
  ADD CONSTRAINT `major_vehicle_services_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transportation`
--
ALTER TABLE `transportation`
  ADD CONSTRAINT `transportation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vehicle_services`
--
ALTER TABLE `vehicle_services`
  ADD CONSTRAINT `vehicle_services_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
