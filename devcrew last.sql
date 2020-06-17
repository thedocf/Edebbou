-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 17 juin 2020 à 15:56
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `devcrew`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
CREATE TABLE IF NOT EXISTS `annonce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `postdate` timestamp NULL DEFAULT current_timestamp(),
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator` (`creator`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`id`, `creator`, `title`, `description`, `photo`, `postdate`, `rating`) VALUES
(29, 19, 'Le contenu des petites annonces est fourni par leurs éditeurs. Malgré les vérifications effectuées', 'location', 'file:/C:/Users/Fida/Pictures/10857827_1557021504513570_510892592854204211_n.jpg', '2020-04-02 11:00:33', 3),
(43, 2, 'Locdation des entrepots eddebou', 'entrepot', 'file:/C:/Users/Fida/Pictures/247730_476626292359296_1441486331_n.jpg', '2020-04-10 10:28:41', 2),
(44, 1, 'Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.\nle', 'corone', 'file:/C:/Users/Fida/Desktop/71323383_427839664502404_2870763755623415808_n.jpg', '2020-04-15 12:28:35', 4);

-- --------------------------------------------------------

--
-- Structure de la table `categ`
--

DROP TABLE IF EXISTS `categ`;
CREATE TABLE IF NOT EXISTS `categ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categ`
--

INSERT INTO `categ` (`id`, `label`) VALUES
(3, 'Reclamation'),
(4, 'Avis'),
(5, 'Support'),
(6, 'Sponsor');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `label`) VALUES
(2, 'Food product'),
(3, 'Beverages'),
(4, 'Vitamine');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_u` int(11) DEFAULT NULL,
  `id_livreur` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `etatLivraison` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'en attente',
  PRIMARY KEY (`id`),
  KEY `IDX_6EEAA67D35F8C041` (`id_u`),
  KEY `IDX_6EEAA67D35E7E71D` (`id_livreur`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `id_u`, `id_livreur`, `date`, `etatLivraison`) VALUES
(1, 2, 1, '2020-02-28', 'Livré'),
(2, 2, 1, '2020-02-28', 'Livré'),
(3, 2, NULL, '2020-02-28', 'en attente'),
(4, 2, 2, '2020-02-28', 'Livré'),
(5, 53, 3, '2020-02-18', 'en attente'),
(6, 1, NULL, '2020-02-18', 'en attente');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `id_commentaire` int(100) NOT NULL AUTO_INCREMENT,
  `id_user` int(100) NOT NULL,
  `id_annonce` int(100) NOT NULL,
  `commentaire` varchar(255) NOT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `id_annonce` (`id_annonce`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `depot`
--

DROP TABLE IF EXISTS `depot`;
CREATE TABLE IF NOT EXISTS `depot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entreprise` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `surface` int(11) NOT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `capacite` int(11) NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `depot`
--

INSERT INTO `depot` (`id`, `entreprise`, `surface`, `ville`, `capacite`, `description`, `image`) VALUES
(19, 'Sicam', 500, 'El Kef', 2, 'Société Industrielle des Conserves Alimentaires', '60f59dda8c6eddd15a7c938bf11ce42e.jpeg'),
(20, 'L\'Epi d\'Or', 900, 'Jendouba', 2, 'Fabricant de pâtes alimentaires en Tunisie', '083def755d6dee2b826e3beaccac0cd2.jpeg'),
(21, 'S F B T', 600, 'Tunis', 0, 'Sté. Frigorifique et Brasserie de Tunis', 'b34544b998430ef45f74a7231f7f823b.jpeg'),
(22, 'Vitalait', 700, 'Gabes', 0, 'Fourinisseur de produits laitiers', '57dbfaf6ebb4409d50fbb81ec4ac872f.jpeg'),
(23, 'test', 500, 'Gabes', 4, 'TTTTTTTTT', '22403cdd1d5106577f73d2bc769a2c81.jpeg'),
(24, 'Testt', 600, 'Nabeul', 3, 'Test integration', 'a852c4586c7fc0884081aa2a9a53bb25.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomEvent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `nbPlaces` int(11) NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nomEvent`, `description`, `dateDebut`, `dateFin`, `nbPlaces`, `image`) VALUES
(113, 'Visite', 'porte ouvrte', '2015-01-01 12:00:00', '2015-01-01 18:00:00', 15, 'f6f6bfdbd4fc83f41dae1814168a4e2b.jpeg'),
(114, 'aaaaa', 'bbb', '2015-01-01 15:00:00', '2015-01-01 19:00:00', 12, 'b079cdd0a41da443278d5a35b8d2fe1d.png'),
(116, 'porte ouverte', 'PO', '2015-01-01 16:00:00', '2015-01-01 18:00:00', 10, 'ce95385db5822b151fa4f5f9cb20e05c.png'),
(118, 'bbbb', 'aaa', '2020-02-20 12:00:00', '2020-10-01 00:00:00', 44, 'f98a4a67ca0109efd84357a4fa8b654c.png'),
(119, 'testttt', 'hhhhh', '2020-02-28 00:00:00', '2020-03-01 00:00:00', 122, 'db710ff106e6ab7f6dfcb82d2f8d4f21.jpeg'),
(120, 'visite', 'dddd', '2020-02-28 00:00:00', '2021-01-01 00:00:00', 145, '9ed6843d445d6a2444209128afdc174e.jpeg'),
(121, 'visite', 'dddd', '2020-04-28 00:00:00', '2021-01-01 00:00:00', 14, 'f126becf0f7274e88e7d829ffc5134c8.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `eventcours`
--

DROP TABLE IF EXISTS `eventcours`;
CREATE TABLE IF NOT EXISTS `eventcours` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomEvent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbPlaces` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `datenaiss` date DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `phone`, `datenaiss`, `photo`) VALUES
(1, 'ikbel', 'ikbel', 'ikbel@ikbel.com', 'ikbel@ikbel.com', 1, NULL, '$2y$13$CvKHogHIf6Iohnw1sd07guM.Aalfuq.RoWPawdBA8beQhffLcChUG', '2020-06-14 23:45:31', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', NULL, NULL, NULL, '2020-03-10', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(2, 'Eyaa', 'Eyaa', 'eya@eya.com', 'eya@eya.com', 1, NULL, '$2y$13$qykJ0BcGBhpFleWptW7C9uQ/2psHBSYGmfhIsyz3uJT.hIVXU8pJy', '2020-02-28 08:44:55', NULL, NULL, 'a:0:{}', 'Eyaa', 'ksouri', '23156489', '1990-03-08', 'file:/C:/Users/Fida/Pictures/10906002_10203385213490801_5210925143719888242_n.jpg'),
(3, 'yahyaoui', 'yahyaoui', 'mohamed.yahyaoui@esprit.tn', 'mohamed.yahyaoui@esprit.tn', 1, NULL, '$2y$13$CvKHogHIf6Iohnw1sd07guM.Aalfuq.RoWPawdBA8beQhffLcChUG', '2020-06-14 23:48:34', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', NULL, NULL, NULL, '2020-03-10', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(5, 'fida9999', 'fida', 'fida.essghaier@esprit.tn', 'fida.essghaier@esprit.tn', 1, NULL, '$2y$10$5gcZH4TUgNCNLpXu7UTJA.3z5qNTmRxv4e83XJxOGjuR.19EXc9ny', '2020-02-09 20:23:35', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, '2020-03-10', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(6, 'fida2', 'fida2', 'fida.essghaie1r@esprit.tn', 'fida.essghaie1r@esprit.tn', 1, NULL, '$2y$13$CGU1PJISkW06JoFSy2ef6uH2Stdk1ZhzbTiwMGVeTKcAWInI2n6au', '2020-02-12 23:01:25', NULL, NULL, 'a:0:{}', NULL, NULL, NULL, '2020-03-10', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(8, 'fadouch', 'fadouch', 'fida@gmail.fr', 'fida@gmail.fr', 1, NULL, '$2y$13$tqk.TTVGNwoYUlKzbgMTmu1tx3eX32lo4oA0apIP.pliEqwX2JE6.', '2020-02-13 13:03:00', NULL, NULL, 'a:0:{}', 'ext', 'exp', NULL, '2020-03-10', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(9, 'fiiiida', 'fiiiida', 'fida@eya.com', 'fida@eya.com', 1, NULL, '$2y$13$qykJ0BcGBhpFleWptW7C9uQ/2psHBSYGmfhIsyz3uJT.hIVXU8pJy', '2020-02-21 09:09:57', NULL, NULL, 'a:0:{}', 'kadha', 'koukou', '5643216', '2020-03-10', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(19, 'khemiri', 'khemiri', 'khemiri@gmail.com', 'khemiri@gmail.com', 1, NULL, '$2y$10$RxLgLvlQ4VovjyUxC7KrJO6bLTA8zkVQs7RJECZSG2aQnuqkseSr2', NULL, NULL, NULL, 'a:0:{}', 'khemiri', 'sgh', '29058311', '2020-04-18', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(20, 'covid', 'covid', 'covid@gmail.com', 'covid@gmail.com', 1, NULL, '$2y$10$n250FTLhpzR8bYUN8SSZDeWgp.OId/S52c3AxgzhIl4OzDox3Y.OC', NULL, NULL, NULL, 'a:0:{}', 'covid', 'corona', '29058311', '2020-04-11', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(25, 'coonaa', 'coonaa', 'fida.essghaier@esprit.coom', 'fida.essghaier@esprit.coom', 1, NULL, '$2y$10$US4w.u59sU9NRkN6wyskIO0bAZQyi6iFQn.BfizBagI.iSn5W5ga.', NULL, NULL, NULL, 'a:0:{}', 'coonaa', 'fidddda', '29058311', '2020-04-16', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png	'),
(51, 'jaceeem', 'jaceeem', 'sghfida@gmail.com', 'sghfida@gmail.com', 1, NULL, '$2y$10$ETjkinjvxBd8J5jGcA4T6.eMyaP0GS8yRSgJxw.dY.BmbcXVqb1U.', NULL, NULL, NULL, 'a:0:{}', 'jaceeem', 'sgh', '29058311', '1997-04-03', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png'),
(53, 'firas', 'firas', 'ff@ff.ff', 'ffff@ffff.ff', 1, NULL, '$2y$13$CvKHogHIf6Iohnw1sd07guM.Aalfuq.RoWPawdBA8beQhffLcChUG', NULL, NULL, NULL, 'a:0:{}', 'firas', 'selmen', '2222', '2020-06-11', 'file:/C:/Users/Fida/Documents/NetBeansProjects/JavaFXApplication5/src/ressources/profile.png');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depot_id` int(11) DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `numTel` int(11) NOT NULL,
  `disponible` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_369ECA328510D4DE` (`depot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id`, `depot_id`, `nom`, `prenom`, `numTel`, `disponible`) VALUES
(16, 20, 'Iheb', 'Khiari', 23456789, 1),
(17, 20, 'Amine', 'Romdhani', 21345432, 0),
(18, 21, 'Iyed', 'Bouhsina', 90345455, 0),
(19, 21, 'Abdelhamid', 'ryahi', 56745432, 1),
(20, 21, 'Fathi', 'khayati', 56213445, 0),
(21, 19, 'Yahyaoui', 'Mohamed', 55456800, 1),
(22, 21, 'Ridha', 'Jmaldine', 22345300, 0),
(23, NULL, 'Jamel', 'Bouraoui', 24678223, 0),
(24, NULL, 'Fahd', 'Jaballah', 98443500, 1);

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

DROP TABLE IF EXISTS `ligne_commande`;
CREATE TABLE IF NOT EXISTS `ligne_commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_commande` int(11) DEFAULT NULL,
  `qte` int(11) NOT NULL,
  `productId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_3170B74B3E314AE8` (`id_commande`),
  KEY `IDX_3170B74B36799605` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `ligne_commande`
--

INSERT INTO `ligne_commande` (`id`, `id_commande`, `qte`, `productId`) VALUES
(1, 1, 1, 6),
(2, 2, 1, 8),
(3, 2, 1, 9),
(4, 3, 1, 6),
(5, 4, 3, 6),
(6, 4, 1, 7);

-- --------------------------------------------------------

--
-- Structure de la table `livreur2`
--

DROP TABLE IF EXISTS `livreur2`;
CREATE TABLE IF NOT EXISTS `livreur2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `livreur2`
--

INSERT INTO `livreur2` (`id`, `nom`) VALUES
(1, 'foulen'),
(2, 'foulen'),
(3, 'test');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B6BD307FE2904019` (`thread_id`),
  KEY `IDX_B6BD307FF624B39D` (`sender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(1, 3, 3, 'chnahwelek', '2020-02-27 02:44:03');

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) DEFAULT NULL,
  `createur` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `postdate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_DB021E96E6ADA943` (`cat_id`),
  KEY `IDX_DB021E96FAD8DA84` (`createur`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `cat_id`, `createur`, `title`, `description`, `photo`, `postdate`) VALUES
(6, 3, 3, 'Reclamation du Commande', 'Bonjour Jai besoin du support', 'b12b4dc5d39aca37217a24b4a9c0f611.jpeg', '2020-02-27'),
(7, 6, 3, 'Sponsor', 'Je voulais vous proposer notre service', '231bb01301e5a6a8586bd181e8644a18.png', '2020-02-27'),
(8, 4, 3, 'Good site', 'Nice work good job keep on', 'c58969ddc6c8720c06bf11fedf70c553.png', '2020-02-27'),
(9, 4, 3, 'Nice job', 'Good work ladies and gentlemen', 'ad97178830214014e8c03fee7cb57135.jpeg', '2020-02-27'),
(10, 3, 2, 'Test Reclamation', 'Testing Reclamation', '7586bd3ac9c45f6c25419e7952676517.jpeg', '2020-02-28');

-- --------------------------------------------------------

--
-- Structure de la table `message_metadata`
--

DROP TABLE IF EXISTS `message_metadata`;
CREATE TABLE IF NOT EXISTS `message_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_4632F005537A1329` (`message_id`),
  KEY `IDX_4632F0059D1C3019` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message_metadata`
--

INSERT INTO `message_metadata` (`id`, `message_id`, `participant_id`, `is_read`) VALUES
(1, 1, 2, 1),
(2, 1, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route_parameters` longtext COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '(DC2Type:array)',
  `notification_date` datetime NOT NULL,
  `seen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `title`, `description`, `icon`, `route`, `route_parameters`, `notification_date`, `seen`) VALUES
(1, 'Newpost', 'fida', NULL, '1', 'a:1:{s:2:\"id\";i:17;}', '2020-02-19 14:09:06', 0),
(2, 'Newpost', 'fida', NULL, '21', 'a:1:{s:2:\"id\";i:18;}', '2020-02-19 14:11:19', 0),
(3, 'Newpost', 'notif', NULL, '19', 'a:1:{s:2:\"id\";i:19;}', '2020-02-19 15:21:08', 0),
(4, 'New post', 'Blog 1', NULL, '17', 'a:1:{s:2:\"id\";i:20;}', '2020-02-19 16:29:15', 0),
(5, 'New post', 'never', NULL, '16', 'a:1:{s:2:\"id\";i:21;}', '2020-02-20 15:13:03', 0),
(6, 'Update post', 'fida', NULL, '13', 'a:1:{s:2:\"id\";i:13;}', '2020-02-20 17:49:01', 0),
(7, 'New post', 'fida ess', NULL, '12', 'a:1:{s:2:\"id\";i:22;}', '2020-02-21 09:07:19', 0),
(8, 'New post', 'Le meilleur', NULL, '1', 'a:1:{s:2:\"id\";i:1;}', '2020-02-27 22:41:38', 0);

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

DROP TABLE IF EXISTS `participant`;
CREATE TABLE IF NOT EXISTS `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evenement` int(11) DEFAULT NULL,
  `confirmation` tinyint(1) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_inscrit` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D79F6B11B26681E` (`evenement`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `participant`
--

INSERT INTO `participant` (`id`, `evenement`, `confirmation`, `nom`, `mail`, `prenom`, `date_inscrit`) VALUES
(15, NULL, 1, 'qaaa', '', 'linaaaaaaa', '2019-01-01 02:00:00'),
(17, NULL, 1, 'ffff', '', 'dsss', '2017-01-01 00:00:00'),
(18, NULL, 1, 'ffffnnn', '', 'dssshhhhhhh', '2017-02-01 00:00:00'),
(19, NULL, 1, 'ffffnnn', '', 'dssshhhhhhh', '2017-02-01 00:00:00'),
(20, NULL, 1, 'dsqqsd', '', 'qsdfdxsw', '2015-01-01 00:00:00'),
(23, NULL, 1, 'aaa', '', 'amina', '2020-12-01 00:00:00'),
(24, NULL, 1, 'aaa', '', 'amina', '2020-12-01 00:00:00'),
(25, NULL, 1, 'aaa', '', 'amina', '2020-12-01 00:00:00'),
(27, NULL, 1, 'thraya', '', 'lina', '2020-12-01 00:00:00'),
(28, NULL, 1, 'thraya', '', 'lina', '2020-12-01 00:00:00'),
(29, NULL, 1, 'thraya', '', 'lina', '2020-12-01 00:00:00'),
(30, NULL, 1, 'thraya', '', 'lina', '2020-12-01 00:00:00'),
(31, NULL, 1, 'aaaaa', '', 'aaaaaaaaaaaaùmm', '2020-12-01 00:00:00'),
(32, NULL, 1, 'aaaaa', '', 'aaaaaaaaaaaaùmm', '2020-12-01 00:00:00'),
(33, NULL, 1, 'aaaaa', '', 'aaaaaaaaaaaaùmm', '2020-12-01 00:00:00'),
(34, NULL, 1, 'aaaaa', '', 'aaaaaaaaaaaaùmm', '2020-12-01 00:00:00'),
(35, NULL, 1, 'aaaaa', '', 'aaaaaaaaaaaaùmm', '2020-12-01 00:00:00'),
(36, NULL, 1, 'aaaaa', '', 'aaaaaaaaaaaaùmm', '2020-12-01 00:00:00'),
(37, NULL, 1, 'amina', 'lina.thraya@esprit.tn', 'mtiri', '2023-01-01 00:00:00'),
(38, NULL, 1, 'amina', 'lina.thraya@esprit.tn', 'mtiri', '2023-01-01 00:00:00'),
(39, NULL, 1, 'amina', 'lina.thraya@esprit.tn', 'mtiri', '2023-01-01 00:00:00'),
(40, NULL, 1, 'amina', 'amina.mtiri@esprit.tn', 'mtiri', '2023-01-01 00:00:00'),
(41, NULL, 1, 'amina', 'amina.mtiri@esprit.tn', 'mtiri', '2023-01-01 00:00:00'),
(42, NULL, 1, 'amina', 'amina.mtiri@esprit.tn', 'mtiri', '2023-01-01 00:00:00'),
(43, NULL, 1, 'lina', 'lina.thraya@esprit.tn', 'thraya', '2023-01-01 00:00:00'),
(44, NULL, 1, 'Test', 'sandicax@gmail.com', 'Test', '2022-01-01 00:00:00'),
(45, NULL, 1, 'testtt', 'massoudiradhouene@gmail.com', 'testtt', '2015-01-01 06:00:00'),
(46, NULL, 1, 'testtt', 'massoudiradhouene@gmail.com', 'testtt', '2015-01-01 06:00:00'),
(47, NULL, 1, 'testttttt', 'massoudiradhouene@gmail.com', 'testttaa', '2020-01-01 06:00:00'),
(48, NULL, 1, 'testttttt', 'massoudiradhouene@gmail.com', 'testttaa', '2020-01-01 06:00:00'),
(49, NULL, 1, 'inaaa', 'massoudiradhouene@gmail.com', 'lina', '2020-01-01 06:00:00'),
(50, NULL, 1, 'inaaa', 'massoudiradhouene@gmail.com', 'lina', '2020-01-01 06:00:00'),
(51, NULL, 1, 'inaaa', 'massoudiradhouene@gmail.com', 'lina', '2020-01-01 06:00:00'),
(52, NULL, 1, 'inaaalllll', 'massoudiradhouene@gmail.com', 'linaaaa', '2020-01-01 06:00:00'),
(53, NULL, 1, 'inaaalllll', 'massoudiradhouene@gmail.com', 'linaaaa', '2020-01-01 06:00:00'),
(54, NULL, 1, 'inaaalllll', 'massoudiradhouene@gmail.com', 'linaaaalll', '2020-01-04 06:00:00'),
(55, NULL, 1, 'inaaalllll', 'massoudiradhouene@gmail.com', 'linaaaalll', '2020-04-04 06:00:00'),
(56, NULL, 1, 'inaaa', 'massoudiradhouene@gmail.com', 'linaaaa', '2020-01-01 06:00:00'),
(57, NULL, 1, 'inaaa', 'massoudiradhouene@gmail.com', 'linaaaa', '2020-01-01 06:00:00'),
(58, NULL, 1, 'inaaa', 'massoudiradhouene@gmail.com', 'linaaaa', '2020-01-01 06:00:00'),
(59, NULL, 1, 'fida', 'massoudiradhouene@gmail.com', 'essghaier', '2020-01-01 06:00:00'),
(60, NULL, 1, 'fida', 'massoudiradhouene@gmail.com', 'essghaier', '2020-01-01 06:00:00'),
(61, NULL, 1, 'testtt', 'massoudiradhouene@gmail.com', 'testtt', '2020-01-01 00:00:00'),
(62, NULL, 1, 'fida', 'fida.essghaier@esprit.tn', 'essghaier', '2020-03-01 00:00:00'),
(63, NULL, 1, 'linabbb', 'massoudiradhouene@gmail.com', 'linaaa', '2020-01-01 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `postdate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5A8A6C8DBC06EA63` (`creator`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `post`
--

INSERT INTO `post` (`id`, `creator`, `title`, `description`, `photo`, `postdate`) VALUES
(12, 6, 'INFOS ATELIERS', 'L\'atelier de l\'entrePot est ouvert uniquement sur les horaires des ateliers.\r\nMerci de nous contacter par mail ou téléphone pour information et inscription.', '2cd7994f42e3b8fd16cbe4d945abbda9.jpeg', '2020-02-14'),
(13, 8, 'Activités poterie à La Journée Médiévale de Saint Macaire', 'L\'entrePot a répondu présent à l\'invitation de l\'association Les Médiévales.\r\nSamedi 31 Aout 2019 à Saint Macaire.\r\nDémonstration le matin, ateliers pour petits et grands l\'après-midi.\r\nVenez nombreux', '308682a2bcafbc272bed9b948a4b02bd.jpeg', '2020-02-20'),
(16, 8, 'fidad', 'dqkjd', 'a8ae1fdc4c8eff3bc34a131d9eb677b4.jpeg', '2020-02-19'),
(17, 8, 'fida', 'fksfksq', '3535d952371fcea39800cb914c311b52.jpeg', '2020-02-19'),
(19, 8, 'notif', 'inshallah tkhdem', '501dc446322fc8f2f080a917268a36bb.jpeg', '2020-02-19'),
(21, 8, 'never', 'nedfghjklmù*sdfgchjbk,lm;:ùhjbn cshjklmfver lose hope', '0f26c98a858442d0d4a7b8498d9d3bc9.jpeg', '2020-02-20'),
(22, 5, 'fida ess', 'dfghjklmkjhgfdvhjklmkuygfdghjklmkjhs856dds dsdqjdskdoksdksnhqddsqdsdsdqsqdsqdsdqsdq', '260d91634a1a718574fe69a7f74c4452.jpeg', '2020-02-21');

-- --------------------------------------------------------

--
-- Structure de la table `postcomment`
--

DROP TABLE IF EXISTS `postcomment`;
CREATE TABLE IF NOT EXISTS `postcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5D65518A4B89032C` (`post_id`),
  KEY `IDX_5D65518AA76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `postcomment`
--

INSERT INTO `postcomment` (`id`, `post_id`, `user_id`, `content`) VALUES
(12, 12, 8, 'fidaa'),
(17, 16, 8, 'doc '),
(22, 12, 8, '****');

-- --------------------------------------------------------

--
-- Structure de la table `postlike`
--

DROP TABLE IF EXISTS `postlike`;
CREATE TABLE IF NOT EXISTS `postlike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B84FD43A4B89032C` (`post_id`),
  KEY `IDX_B84FD43AA76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `postlike`
--

INSERT INTO `postlike` (`id`, `post_id`, `user_id`) VALUES
(10, 19, 8),
(12, 12, 8),
(19, 13, 5),
(21, 12, 5),
(22, 21, 5),
(24, 17, 5),
(25, 19, 5),
(27, 16, 8),
(28, 19, 6),
(29, 16, 6),
(31, 19, 1),
(32, 13, 2),
(33, 16, 2),
(34, 12, 2);

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) DEFAULT NULL,
  `prix` double NOT NULL,
  `qte` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_expiration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stars` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D34A04ADE6ADA943` (`cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `cat_id`, `prix`, `qte`, `description`, `date_expiration`, `nom`, `image`, `stars`) VALUES
(6, 2, 2, 50, 'Tomate fraiche', '2024-05-01', 'Tomate pelees entieres', 'sicam.jpg', 3),
(7, 2, 1.22, 31, 'spicy spicy spicy', '2024-11-01', 'Harissa', 'harissa.jpg', 4),
(8, 3, 1, 230, 'Lait Lait', '2017-03-17', 'Lait Vitalait', 'vitalait.jpg', 2),
(9, 3, 0.5, 600, 'Yaghortt', '2023-01-17', 'Yaought Vitalait', 'vitup.jpg', 2),
(10, 3, 0.4, 400, 'Fell num 4', '2020-06-01', 'Makrouna', 'fell.jpg', 4),
(13, 3, 1.8, 700, 'Boisson', '2020-06-01', 'CocaCola', 'coca.jpg', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createur` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `postdate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5FB6DEC7FAD8DA84` (`createur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_31204C83B03A8386` (`created_by_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(1, 3, 'Test', '2020-02-25 20:23:44', 0),
(2, 2, 'vvv', '2020-02-25 22:20:57', 0),
(3, 3, 'marhba', '2020-02-27 02:44:03', 0);

-- --------------------------------------------------------

--
-- Structure de la table `thread_metadata`
--

DROP TABLE IF EXISTS `thread_metadata`;
CREATE TABLE IF NOT EXISTS `thread_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_40A577C8E2904019` (`thread_id`),
  KEY `IDX_40A577C89D1C3019` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread_metadata`
--

INSERT INTO `thread_metadata` (`id`, `thread_id`, `participant_id`, `is_deleted`, `last_participant_message_date`, `last_message_date`) VALUES
(1, 1, 2, 0, NULL, '2020-02-25 20:23:44'),
(2, 1, 3, 0, '2020-02-25 20:23:44', NULL),
(3, 2, 3, 0, NULL, '2020-02-25 22:20:57'),
(4, 2, 2, 0, '2020-02-25 22:20:57', NULL),
(5, 3, 2, 1, NULL, '2020-02-27 02:44:03'),
(6, 3, 3, 0, '2020-02-27 02:44:03', NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `annonce_ibfk_1` FOREIGN KEY (`creator`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_6EEAA67D35E7E71D` FOREIGN KEY (`id_livreur`) REFERENCES `livreur2` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_6EEAA67D35F8C041` FOREIGN KEY (`id_u`) REFERENCES `fos_user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD CONSTRAINT `FK_369ECA328510D4DE` FOREIGN KEY (`depot_id`) REFERENCES `depot` (`id`);

--
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `FK_3170B74B36799605` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_3170B74B3E314AE8` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `FK_DB021E96E6ADA943` FOREIGN KEY (`cat_id`) REFERENCES `categ` (`id`),
  ADD CONSTRAINT `FK_DB021E96FAD8DA84` FOREIGN KEY (`createur`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `FK_D79F6B11B26681E` FOREIGN KEY (`evenement`) REFERENCES `evenement` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK_5A8A6C8DBC06EA63` FOREIGN KEY (`creator`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `postcomment`
--
ALTER TABLE `postcomment`
  ADD CONSTRAINT `FK_5D65518A4B89032C` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `FK_5D65518AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `postlike`
--
ALTER TABLE `postlike`
  ADD CONSTRAINT `FK_B84FD43A4B89032C` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `FK_B84FD43AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_D34A04ADE6ADA943` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `FK_5FB6DEC7FAD8DA84` FOREIGN KEY (`createur`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
