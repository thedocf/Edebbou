-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 02 avr. 2020 à 21:40
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
-- Base de données :  `tunfugees`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

DROP TABLE IF EXISTS `annonce`;
CREATE TABLE IF NOT EXISTS `annonce` (
  `idannonce` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `datePub` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`idannonce`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`idannonce`, `adresse`, `type`, `description`, `datePub`) VALUES
(4, 'gabes', 'SDF', 'ddddd', '2020-02-26 08:51:20'),
(5, 'aryana', 'zdf', 'aùoajne', '2020-02-26 09:30:10'),
(6, 'aaaa', 'zdf', 'aùoajne', '2020-02-26 09:32:33');

-- --------------------------------------------------------

--
-- Structure de la table `camps`
--

DROP TABLE IF EXISTS `camps`;
CREATE TABLE IF NOT EXISTS `camps` (
  `idCamp` int(11) NOT NULL AUTO_INCREMENT,
  `nbrmax` int(11) NOT NULL,
  `nomCamp` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  PRIMARY KEY (`idCamp`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `camps`
--

INSERT INTO `camps` (`idCamp`, `nbrmax`, `nomCamp`, `adresse`) VALUES
(8, 11, 'gg', 'rrrrrr'),
(9, 40, 'jjhggh', 'rue ');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etat_commande` varchar(11) NOT NULL,
  `date_emission` varchar(30) NOT NULL,
  `id_utilisateur` varchar(30) NOT NULL,
  `prixTotal` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `donneur`
--

DROP TABLE IF EXISTS `donneur`;
CREATE TABLE IF NOT EXISTS `donneur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cin` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `don` int(11) NOT NULL,
  `numcarte` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `donneur`
--

INSERT INTO `donneur` (`id`, `cin`, `nom`, `prenom`, `mail`, `don`, `numcarte`) VALUES
(1, 99, 'zzz', 'zzzaaaa', 'mmmm', 77, 98989898),
(3, 999, 'zzz', 'zzz', 'zz', 999, 9999),
(28, 12345678, 'aymen', 'smati', 'aymen@gmail.com', 14, 2525),
(29, 55, 'aymen', 'smati', 'aymen@gmail.com', 22, 22),
(30, 1, 'aymen', 'smati', 'aymen@gmail.com', 22, 22),
(32, 12345678, 'eya', 'bellil', 'eyabellil44@gmail.com', 150, 4444);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `nomEvent` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `date` date NOT NULL,
  `description` text NOT NULL,
  `nbrMax` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id_event`),
  UNIQUE KEY `nomEvent` (`nomEvent`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id_event`, `nomEvent`, `adresse`, `date`, `description`, `nbrMax`, `image`) VALUES
(25, 'Event 4', 'gabess', '2020-02-29', 'hkhkuhui', -1, 'C:/Users/aymen/Desktop/3em/pIdev/sprint1(Java)/src/res/background_panier7.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

DROP TABLE IF EXISTS `ligne_commande`;
CREATE TABLE IF NOT EXISTS `ligne_commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_commande` int(11) NOT NULL,
  `idProd` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `prixProd` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ligne_commande`
--

INSERT INTO `ligne_commande` (`id`, `id_commande`, `idProd`, `id_utilisateur`, `prixProd`) VALUES
(31, 0, 10, 56, 145),
(30, 0, 9, 56, 152),
(28, 0, 10, 56, 145),
(27, 0, 9, 56, 152),
(29, 0, 11, 56, 153);

-- --------------------------------------------------------

--
-- Structure de la table `panier_produit`
--

DROP TABLE IF EXISTS `panier_produit`;
CREATE TABLE IF NOT EXISTS `panier_produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idProd` int(11) NOT NULL,
  `nomProd` varchar(30) NOT NULL,
  `nomRef` varchar(30) NOT NULL,
  `prix` int(11) NOT NULL,
  `img` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier_produit`
--

INSERT INTO `panier_produit` (`id`, `idProd`, `nomProd`, `nomRef`, `prix`, `img`) VALUES
(14, 11, 'table peinture', 'aymen', 153, 'bgstore.jpg'),
(12, 9, 'Tableau', 'ahmed', 152, 'tb.jpeg'),
(13, 10, 'coussin', 'zakeya', 145, 'coussin.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

DROP TABLE IF EXISTS `produits`;
CREATE TABLE IF NOT EXISTS `produits` (
  `idProd` int(11) NOT NULL AUTO_INCREMENT,
  `nomProd` varchar(30) NOT NULL,
  `nomRef` varchar(30) NOT NULL,
  `img` varchar(200) NOT NULL,
  `prix` int(11) NOT NULL,
  `dispo` varchar(30) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `likes` int(200) DEFAULT NULL,
  `views` int(200) DEFAULT NULL,
  PRIMARY KEY (`idProd`),
  UNIQUE KEY `nomProd` (`nomProd`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`idProd`, `nomProd`, `nomRef`, `img`, `prix`, `dispo`, `description`, `likes`, `views`) VALUES
(10, 'coussin', 'zakeya', 'coussin.jpg', 145, 'Disponible', '3awnouna', 7, 7),
(11, 'table peinture', 'aymen', 'bgstore.jpg', 153, 'Disponible', 'ahsen ma3ana', 0, 2),
(9, 'Tableau', 'ahmed', 'tb.jpeg', 152, 'Disponible', 'ahsen ma 3ana', 0, 0),
(13, 'krostina', 'med', 'winn.jpg', 20, 'Disponible', 'bnina', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mailCli` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `description` varchar(30) NOT NULL,
  `etat` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `refugies`
--

DROP TABLE IF EXISTS `refugies`;
CREATE TABLE IF NOT EXISTS `refugies` (
  `idRef` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `pays` varchar(30) NOT NULL,
  PRIMARY KEY (`idRef`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `refugies`
--

INSERT INTO `refugies` (`idRef`, `nom`, `prenom`, `age`, `pays`) VALUES
(5, 'med', 'yah', 21, 'nabeul'),
(7, 'ines', 'aouari', 15, 'tunisie');

-- --------------------------------------------------------

--
-- Structure de la table `sponsors`
--

DROP TABLE IF EXISTS `sponsors`;
CREATE TABLE IF NOT EXISTS `sponsors` (
  `idSponsor` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `type` varchar(11) NOT NULL,
  `numTel` int(11) NOT NULL,
  PRIMARY KEY (`idSponsor`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sponsors`
--

INSERT INTO `sponsors` (`idSponsor`, `nom`, `adresse`, `type`, `numTel`) VALUES
(1, 'aaa', 'aaa', 'type1', 22),
(2, 'vermeg', 'lac 2', 'Entreprise', 23808542),
(3, 'ggg', 'ggg@gmail.com', 'Association', 55555555);

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `idStock` int(11) NOT NULL AUTO_INCREMENT,
  `typeStock` varchar(30) NOT NULL,
  `qteStock` int(11) NOT NULL,
  `nomProd` varchar(30) NOT NULL,
  PRIMARY KEY (`idStock`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `sexe` varchar(30) NOT NULL,
  `Role` varchar(30) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `etat` int(1) NOT NULL DEFAULT 0,
  UNIQUE KEY `cin` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `age`, `numero`, `sexe`, `Role`, `photo`, `etat`) VALUES
(55, 'amine', 'sessi', 'amine@gmail.com', '1111', 32, 23808542, 'male', 'Admin', '', 1),
(5, 'aymen', 'smati', 'aymen@gmail.com', '2222', 22, 23808542, 'male', 'Utilisateur', '', 0),
(56, 'eya', 'bellil', 'eyabellil44@gmail.com', '0000', 21, 23808542, 'Female', 'Utilisateur', 'DSC_0187.JPG', 0),
(57, 'zouari', 'ines', 'ineszouari1998@gmail.com', '3333', 2, 23808542, 'Female', 'Admin', 'annonce.jpg', 1),
(61, 'yahyaoui', 'mohamed', 'mohamed.yahyaoui@esprit.tn', '0000', 22, 20282730, 'Male', 'Admin', '58376628_406857966778050_7868265410645196800_n.jpg', 0),
(62, 'yahyaoui', 'mohamed', 'mohamed.yahyaoui@esprit.tn', '0000', 22, 20282730, 'Male', 'Admin', '58376628_406857966778050_7868265410645196800_n.jpg', 0),
(63, 'test', 'test', 'test@test.com', 'test', 20, 20202020, 'Male', 'Admin', '711px-Logo_Club_africain.svg.png', 0);

-- --------------------------------------------------------

--
-- Structure de la table `volontaire`
--

DROP TABLE IF EXISTS `volontaire`;
CREATE TABLE IF NOT EXISTS `volontaire` (
  `id_vol` int(11) NOT NULL AUTO_INCREMENT,
  `cin` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `tel` int(11) NOT NULL,
  `nom_event` varchar(30) NOT NULL,
  `presence` varchar(30) DEFAULT 'Absent',
  `etat` int(11) DEFAULT 0,
  PRIMARY KEY (`id_vol`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `volontaire`
--

INSERT INTO `volontaire` (`id_vol`, `cin`, `nom`, `prenom`, `mail`, `tel`, `nom_event`, `presence`, `etat`) VALUES
(17, 0, 'eya', 'bellil', 'eyabellil44@gmail.com', 23808542, 'Event 3', NULL, 1),
(16, 0, 'eya', 'bellil', 'eyabellil44@gmail.com', 23808542, 'Event 3', NULL, 1),
(20, 0, 'eya', 'bellil', 'eyabellil44@gmail.com', 23808542, 'Event 4', NULL, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
