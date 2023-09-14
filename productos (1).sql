-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 14-09-2023 a las 15:36:48
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

DROP TABLE IF EXISTS `compra`;
CREATE TABLE IF NOT EXISTS `compra` (
  `codigo` varchar(30) NOT NULL,
  `producto` varchar(30) NOT NULL,
  `cliente` varchar(30) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `cantidad` int NOT NULL,
  `estado` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`codigo`, `producto`, `cliente`, `direccion`, `cantidad`, `estado`) VALUES
('7654321', ' apple watch', 'Alexis ', 'diagonal 10a ', 1, 'en reparto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_disponibles`
--

DROP TABLE IF EXISTS `productos_disponibles`;
CREATE TABLE IF NOT EXISTS `productos_disponibles` (
  `codigo` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `cantidad` varchar(30) NOT NULL,
  `valor` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `productos_disponibles`
--

INSERT INTO `productos_disponibles` (`codigo`, `nombre`, `cantidad`, `valor`) VALUES
('12345', 'apple watch', '0', '2500000'),
('54321', 'iPhone 15', '5', '5000000'),
('21435', 'silla gamer', '20', '800000'),
('45231', 'Airpods', '20', '950000');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
