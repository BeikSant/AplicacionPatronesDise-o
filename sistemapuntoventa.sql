-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-02-2022 a las 03:18:17
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemapuntoventa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nombres` varchar(30) DEFAULT NULL,
  `cedula` varchar(10) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombres`, `cedula`, `direccion`) VALUES
(1, 'Beiker Sasaguay', '1950031375', 'Catamayo'),
(2, 'Jaime Paqui', '1900332212', 'Guadalupe'),
(3, 'Santiago Roman', '1134367654', 'Loja');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE `comprobante` (
  `idComprobante` int(11) NOT NULL,
  `formaPago` enum('efectivo','tarjeta') DEFAULT NULL,
  `idOrdenVenta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comprobante`
--

INSERT INTO `comprobante` (`idComprobante`, `formaPago`, `idOrdenVenta`) VALUES
(1, 'efectivo', 2),
(2, 'efectivo', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleordenventa`
--

CREATE TABLE `detalleordenventa` (
  `idDetalleOrdenVenta` int(11) NOT NULL,
  `idOrdenVenta` int(11) DEFAULT NULL,
  `idProducto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalleordenventa`
--

INSERT INTO `detalleordenventa` (`idDetalleOrdenVenta`, `idOrdenVenta`, `idProducto`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(7, 1, 2),
(8, 1, 3),
(9, 2, 2),
(10, 2, 3),
(11, 2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallepaquete`
--

CREATE TABLE `detallepaquete` (
  `idDetallePaquete` int(11) NOT NULL,
  `idProducto` int(11) DEFAULT NULL,
  `idPaquete` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ordenventa`
--

CREATE TABLE `ordenventa` (
  `idOrdenVenta` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` enum('borrador','archivado','faltaPago','aceptado') DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `total` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ordenventa`
--

INSERT INTO `ordenventa` (`idOrdenVenta`, `fecha`, `estado`, `idCliente`, `total`) VALUES
(1, '2022-02-15 00:19:53', 'borrador', 3, 11.08),
(2, '2022-02-15 01:23:01', 'aceptado', 1, 2.58);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquete`
--

CREATE TABLE `paquete` (
  `idProducto` int(11) NOT NULL,
  `peso` varchar(15) DEFAULT NULL,
  `tamannio` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `codigo` varchar(15) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `codigo`, `precio`) VALUES
(1, '202025', 1.5),
(2, '202027', 2),
(3, '202028', 1.25),
(4, '202338', 1.33),
(5, '244338', 2.25),
(6, '2445665', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosimple`
--

CREATE TABLE `productosimple` (
  `idProducto` int(11) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `marca` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productosimple`
--

INSERT INTO `productosimple` (`idProducto`, `nombre`, `marca`) VALUES
(1, 'Cola', 'CocaCola'),
(2, 'Aceite', 'LaFavorita'),
(3, 'Azucar', 'El Campo'),
(4, 'Sal', 'El Campo'),
(5, 'Queso', 'El Rancho'),
(6, 'Leche', 'El Rancho');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD PRIMARY KEY (`idComprobante`),
  ADD KEY `idOrdenVenta` (`idOrdenVenta`);

--
-- Indices de la tabla `detalleordenventa`
--
ALTER TABLE `detalleordenventa`
  ADD PRIMARY KEY (`idDetalleOrdenVenta`),
  ADD KEY `idOrdenVenta` (`idOrdenVenta`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `detallepaquete`
--
ALTER TABLE `detallepaquete`
  ADD PRIMARY KEY (`idDetallePaquete`),
  ADD KEY `idPaquete` (`idPaquete`),
  ADD KEY `idProducto` (`idProducto`);

--
-- Indices de la tabla `ordenventa`
--
ALTER TABLE `ordenventa`
  ADD PRIMARY KEY (`idOrdenVenta`),
  ADD KEY `idCliente` (`idCliente`);

--
-- Indices de la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `productosimple`
--
ALTER TABLE `productosimple`
  ADD PRIMARY KEY (`idProducto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `comprobante`
--
ALTER TABLE `comprobante`
  MODIFY `idComprobante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalleordenventa`
--
ALTER TABLE `detalleordenventa`
  MODIFY `idDetalleOrdenVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `detallepaquete`
--
ALTER TABLE `detallepaquete`
  MODIFY `idDetallePaquete` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ordenventa`
--
ALTER TABLE `ordenventa`
  MODIFY `idOrdenVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`idOrdenVenta`) REFERENCES `ordenventa` (`idOrdenVenta`);

--
-- Filtros para la tabla `detalleordenventa`
--
ALTER TABLE `detalleordenventa`
  ADD CONSTRAINT `detalleordenventa_ibfk_1` FOREIGN KEY (`idOrdenVenta`) REFERENCES `ordenventa` (`idOrdenVenta`),
  ADD CONSTRAINT `detalleordenventa_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`);

--
-- Filtros para la tabla `detallepaquete`
--
ALTER TABLE `detallepaquete`
  ADD CONSTRAINT `detallepaquete_ibfk_1` FOREIGN KEY (`idPaquete`) REFERENCES `paquete` (`idProducto`),
  ADD CONSTRAINT `detallepaquete_ibfk_2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`);

--
-- Filtros para la tabla `ordenventa`
--
ALTER TABLE `ordenventa`
  ADD CONSTRAINT `ordenventa_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
