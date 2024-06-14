-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2024 at 03:28 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `friendflow`
--

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `authority_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `is_edited` tinyint(1) DEFAULT 0,
  `post_id` int(11) DEFAULT NULL,
  `cmt_date` datetime(6) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `friendships`
--

CREATE TABLE `friendships` (
  `friendship_date` datetime(6) DEFAULT NULL,
  `friendship_id` bigint(20) NOT NULL,
  `user1_username` varchar(255) DEFAULT NULL,
  `user2_username` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `friendships`
--

INSERT INTO `friendships` (`friendship_date`, `friendship_id`, `user1_username`, `user2_username`, `status`) VALUES
('2024-06-05 15:07:22.000000', 1, 'sumit31', 'aman22dec', b'1'),
('2024-06-05 16:01:33.000000', 2, 'darshit1234', 'sodamasala', b'1'),
('2024-06-06 14:56:04.000000', 3, 'darshit1234', 'sujal31', b'1'),
('2024-06-07 15:46:05.000000', 4, 'aman22dec', '_devxoxo_', b'1'),
('2024-06-10 14:01:12.000000', 7, 'darshit1234', 'sumit31', b'1'),
('2024-06-10 14:43:56.000000', 8, 'sumit31', 'sujal31', b'1'),
('2024-06-11 11:14:53.000000', 9, 'aman22dec', 'darshit1234', b'1'),
('2024-06-12 14:47:10.000000', 10, 'sodamasala', 'aman22dec', b'1'),
('2024-06-13 15:15:01.000000', 11, 'def46917', 'aman22dec', b'1'),
('2024-06-14 18:49:43.000000', 12, 'def46917', 'darshit1234', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `post_id` int(11) DEFAULT NULL,
  `img_id` bigint(20) NOT NULL,
  `img_name` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`post_id`, `img_id`, `img_name`, `img_url`) VALUES
(NULL, 1, '1000003500.jpg', '/img/uploads/1000003500.jpg'),
(NULL, 2, 'IMG20220516185923.jpg', '/img/uploads/IMG20220516185923.jpg'),
(NULL, 3, 'IMG20220516185923.jpg', '/img/uploads/IMG20220516185923.jpg'),
(NULL, 4, '1000003500.jpg', '/img/uploads/1000003500.jpg'),
(NULL, 5, '1000003500.jpg', '/img/uploads/1000003500.jpg'),
(NULL, 6, '1000003500.jpg', '/img/uploads/1000003500.jpg'),
(NULL, 7, '1000003500.jpg', '/img/uploads/1000003500.jpg'),
(NULL, 8, 'IMG20230115153854.jpg', '/img/uploads/IMG20230115153854.jpg'),
(5, 15, '-6176858799755866029_121.jpg', '/img/post/-6176858799755866029_121.jpg'),
(5, 16, '-6176858799755866030_121.jpg', '/img/post/-6176858799755866030_121.jpg'),
(5, 17, '-6176858799755866032_121.jpg', '/img/post/-6176858799755866032_121.jpg'),
(6, 18, '-6177005855141113944_121.jpg', '/img/post/-6177005855141113944_121.jpg'),
(NULL, 19, 'IMG_20200114_181101.jpg', '/img/uploads/IMG_20200114_181101.jpg'),
(NULL, 20, 'IMG_20200114_181101.jpg', '/img/uploads/IMG_20200114_181101.jpg'),
(NULL, 26, 'IMG-20220601-WA0000.jpg', '/img/uploads/IMG-20220601-WA0000.jpg'),
(NULL, 30, 'IMG20220630091223.jpg', '/img/uploads/IMG20220630091223.jpg'),
(NULL, 36, '1000001181.jpg', '/img/uploads/1000001181.jpg'),
(NULL, 37, 'IMG20221005172141.jpg', '/img/uploads/IMG20221005172141.jpg'),
(NULL, 38, 'IMG20230116145204.jpg', '/img/uploads/IMG20230116145204.jpg'),
(16, 39, '-6177005855141113938_121.jpg', '/img/post/-6177005855141113938_121.jpg'),
(17, 40, '20221224_163312.jpg', '/img/post/20221224_163312.jpg'),
(22, 45, 'IMG20220806124918.jpg', '/img/post/IMG20220806124918.jpg'),
(23, 46, 'IMG20220528103318.jpg', '/img/post/IMG20220528103318.jpg'),
(NULL, 47, 'IMG-20220517-WA0000.jpg', '/img/uploads/IMG-20220517-WA0000.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `like_id` int(11) NOT NULL,
  `post_id` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `likes`
--

INSERT INTO `likes` (`like_id`, `post_id`, `username`) VALUES
(1, 22, 'sumit31'),
(4, 22, 'darshit1234'),
(5, 6, 'darshit1234'),
(6, 5, 'darshit1234'),
(8, 17, 'aman22dec'),
(9, 16, 'aman22dec'),
(10, 5, 'aman22dec'),
(11, 17, 'sujal31'),
(13, 5, 'sujal31'),
(14, 5, 'sumit31'),
(16, 23, 'aman22dec'),
(17, 6, 'def46917'),
(18, 6, 'aman22dec'),
(19, 23, 'darshit1234'),
(20, 16, 'darshit1234');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL,
  `post_date` datetime(6) DEFAULT NULL,
  `caption` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`post_id`, `post_date`, `caption`, `user_id`) VALUES
(5, '2024-05-28 15:01:52.000000', 'School Farewell... ❤️❤️❤️\r\nMeeting friends one last time 🤗🤗', 'sumit31'),
(6, '2024-05-28 15:29:34.000000', 'School Farewell... ❤️❤️❤️\r\nMeeting friends one last time 🤗🤗', 'aman22dec'),
(16, '2024-06-05 14:17:09.000000', 'Farewell......', 'sodamasala'),
(17, '2024-06-10 14:40:35.000000', 'Freshers\'2k22', 'darshit1234'),
(22, '2024-06-11 10:38:31.000000', 'Achievements in School 🧑‍🎓🧑‍🎓', 'sujal31'),
(23, '2024-06-13 14:40:44.000000', 'School Holding...', 'sumit31');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `profile_img` bigint(20) DEFAULT NULL,
  `fname` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_details` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`profile_img`, `fname`, `lname`, `password`, `profile_details`, `username`) VALUES
(20, 'Aman', 'Deshani', '12345678', 'Movie Lover 💻❤️❤️', 'aman22dec'),
(36, 'Darshit', 'Talsaniya', '12345678', 'It is my profile...', 'darshit1234'),
(47, 'Abc', 'Def', 'sujal3105', 'No Bio...', 'def46917'),
(38, 'Dev', 'Shah', '12345678', 'What is Bio ????', 'sodamasala'),
(26, 'Sujal', 'Gohil', 'sumit3105', 'No Bio Needed 😃😃', 'sujal31'),
(30, 'Sumit', 'Gohil', 'sujal3105', 'Cricket Lover - 🏏🏏\r\nDDU - CE\'2026\r\nIndian ❤️', 'sumit31'),
(37, 'Dev', 'Shah', '12345678', 'Wtf ? .....', '_devxoxo_');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`authority_id`),
  ADD KEY `FKhjuy9y4fd8v5m3klig05ktofg` (`username`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
  ADD KEY `FKc71forj6rrlpbvc563oq8etd1` (`username`);

--
-- Indexes for table `friendships`
--
ALTER TABLE `friendships`
  ADD PRIMARY KEY (`friendship_id`),
  ADD KEY `FKejr84bu8wixa9w5g2ayeeqx92` (`user1_username`),
  ADD KEY `FK4yeejwtssnvw84xic523vgvwv` (`user2_username`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`img_id`),
  ADD KEY `FKcp0pycisii8ub3q4b7x5mfpn1` (`post_id`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`like_id`),
  ADD KEY `FKry8tnr4x2vwemv2bb0h5hyl0x` (`post_id`),
  ADD KEY `FK5tgmgqmu12rs6t9gv4i1laia3` (`username`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `UK_eevot7fcbeyafkljw56tsgqeo` (`profile_img`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authorities`
--
ALTER TABLE `authorities`
  MODIFY `authority_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `friendships`
--
ALTER TABLE `friendships`
  MODIFY `friendship_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `img_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `likes`
--
ALTER TABLE `likes`
  MODIFY `like_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `FKhjuy9y4fd8v5m3klig05ktofg` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FKc71forj6rrlpbvc563oq8etd1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

--
-- Constraints for table `friendships`
--
ALTER TABLE `friendships`
  ADD CONSTRAINT `FK4yeejwtssnvw84xic523vgvwv` FOREIGN KEY (`user2_username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `FKejr84bu8wixa9w5g2ayeeqx92` FOREIGN KEY (`user1_username`) REFERENCES `users` (`username`);

--
-- Constraints for table `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `FKcp0pycisii8ub3q4b7x5mfpn1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

--
-- Constraints for table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `FK5tgmgqmu12rs6t9gv4i1laia3` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  ADD CONSTRAINT `FKry8tnr4x2vwemv2bb0h5hyl0x` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK7crw1q0n0wfkj5ltwixoukvth` FOREIGN KEY (`profile_img`) REFERENCES `images` (`img_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
