-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Gegenereerd op: 12 jun 2016 om 15:30
-- Serverversie: 5.7.9
-- PHP-versie: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deadlinegod`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `deadline`
--

DROP TABLE IF EXISTS `deadline`;
CREATE TABLE IF NOT EXISTS `deadline` (
  `naam` varchar(20) NOT NULL,
  `datum` date NOT NULL,
  `beschrijving` varchar(100) NOT NULL,
  `URI` varchar(200) NOT NULL,
  `beoordeling` varchar(2) DEFAULT NULL,
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `klasCode` varchar(3) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `KlasCode` (`klasCode`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `deadline`
--

INSERT INTO `deadline` (`naam`, `datum`, `beschrijving`, `URI`, `beoordeling`, `ID`, `klasCode`) VALUES
('IPASS afmaken', '2016-06-15', 'het project moet nu af zijn ', 'https://cursussen.sharepoint.hu.nl/fnt/51/TICT-V1IPASS-15', '8', 1, 'V1D'),
('WAC Les 10', '2016-06-13', 'les 10 afmaken , voornamelijk stuk schrijven over beveiliging. test 123 456', 'https://cursussen.sharepoint.hu.nl/fnt/53/TICT-V1WAC-15/Studiemateriaal/V1WAC-15_werkboek.pdf', '', 2, 'V1D'),
('123', '2016-06-12', '', '', '', 47, 'V1D');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `klas`
--

DROP TABLE IF EXISTS `klas`;
CREATE TABLE IF NOT EXISTS `klas` (
  `KlasCode` varchar(3) NOT NULL,
  PRIMARY KEY (`KlasCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `klas`
--

INSERT INTO `klas` (`KlasCode`) VALUES
('V1D'),
('V1P'),
('V1Z'),
('V2B');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `naam` varchar(20) NOT NULL,
  `achternaam` varchar(20) NOT NULL,
  `tussenvoegsel` varchar(20) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `isDocent` int(1) NOT NULL,
  `klasCode` varchar(3) NOT NULL,
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `KlasCode` (`klasCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `user`
--

INSERT INTO `user` (`naam`, `achternaam`, `tussenvoegsel`, `email`, `password`, `isDocent`, `klasCode`, `ID`) VALUES
('jari', 'Brink', 'van den', 'jari_brink@homail.com', '123', 0, 'V1D', 1),
('lars', 'hijfte', 'van', 'lars.vanhijfte@student.hu.nl', '123', 1, 'V1D', 2);

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `deadline`
--
ALTER TABLE `deadline`
  ADD CONSTRAINT `user_klascode_fk` FOREIGN KEY (`klasCode`) REFERENCES `klas` (`KlasCode`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `deadlne_klascode_fk` FOREIGN KEY (`klasCode`) REFERENCES `klas` (`KlasCode`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
