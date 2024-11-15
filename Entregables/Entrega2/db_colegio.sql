-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-10-2024 a las 22:19:57
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_colegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id_alumno` int(11) NOT NULL,
  `dni` char(8) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefonoApoderado` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id_alumno`, `dni`, `nombre`, `apellido`, `direccion`, `telefonoApoderado`, `email`) VALUES
(4, '23456789', 'Carlos', 'Torres Calle', 'Calle 123', '912345678', 'carlos.torres@example.com'),
(5, '11223344', 'Luis', 'Calle Cruz', 'Av. Siempre Viva 123', '987654321', 'Luis@example.com'),
(6, '89012345', 'Ana', 'Matinez Sanchez', 'Calle Falsa 456', '912345678', 'anamartinez@example.com'),
(7, '12224344', 'Rene', 'Garcia Mendez', 'Av. Siempre ', '945678901', 'rene@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL,
  `nombre_curso` varchar(100) NOT NULL,
  `descripcion_curso` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id_curso`, `nombre_curso`, `descripcion_curso`) VALUES
(1, 'Desarrollo personal, ciudadanía y cívica', 'Curso de desarrollo personal y ciudadanía'),
(2, 'Ciencias sociales', 'Curso de ciencias sociales '),
(3, 'Educación para el trabajo', 'Curso de educación para el trabajo'),
(4, 'Educación física', 'Curso de educación física'),
(5, 'Comunicación', 'Curso de comunicación'),
(6, 'Arte y cultura', 'Curso de arte y cultura'),
(7, 'Inglés como lengua extranjera', 'Curso de inglés'),
(8, 'Matemática', 'Curso de matemáticas'),
(9, 'Ciencia y tecnología', 'Curso de ciencia y tecnología'),
(10, 'Educación religiosa', 'Curso de educación religiosa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_docente`
--

CREATE TABLE `curso_docente` (
  `id` int(11) NOT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `id_docente` int(11) DEFAULT NULL,
  `id_grado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `curso_docente`
--

INSERT INTO `curso_docente` (`id`, `id_curso`, `id_docente`, `id_grado`) VALUES
(6, 5, 4, 3),
(1, 8, 1, 1),
(3, 8, 1, 3),
(4, 9, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docentes`
--

CREATE TABLE `docentes` (
  `id_docente` int(11) NOT NULL,
  `dni` char(8) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `docentes`
--

INSERT INTO `docentes` (`id_docente`, `dni`, `nombre`, `apellido`, `especialidad`, `telefono`, `email`) VALUES
(1, '12345678', 'Jhonatha', 'Perez Garcia', 'Matematicas', '987654321', 'perezgarcia@example.com'),
(2, '87654321', 'María', 'González López', 'Ciencias', '912345678', 'maria.gonzalezlopez@example.com'),
(3, '11223344', 'Carlos', 'Martínez Torres', 'Historia', '998877665', 'carlos.martineztorres@example.com'),
(4, '44332211', 'Ana', 'Sánchez Fernández', 'Literatura', '987123456', 'ana.sanchezfernandez@example.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grados`
--

CREATE TABLE `grados` (
  `id_grado` int(11) NOT NULL,
  `nombre_grado` varchar(100) NOT NULL,
  `descripcion_grado` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `grados`
--

INSERT INTO `grados` (`id_grado`, `nombre_grado`, `descripcion_grado`) VALUES
(1, 'Primero de Secundaria', 'Primer año de la educación secundaria'),
(2, 'Segundo de Secundaria', 'Segundo año de la educación secundaria'),
(3, 'Tercero de Secundaria', 'Tercer año de la educación secundaria'),
(4, 'Cuarto de Secundaria', 'Cuarto año de la educación secundaria'),
(5, 'Quinto de Secundaria', 'Quinto año de la educación secundaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `id_matricula` int(11) NOT NULL,
  `id_alumno` int(11) DEFAULT NULL,
  `id_grado` int(11) DEFAULT NULL,
  `id_tipo_matricula` int(11) DEFAULT NULL,
  `anio` int(11) NOT NULL,
  `fecha_matricula` date DEFAULT curdate(),
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`id_matricula`, `id_alumno`, `id_grado`, `id_tipo_matricula`, `anio`, `fecha_matricula`, `observaciones`) VALUES
(2, 4, 2, 1, 2024, '2024-10-11', ''),
(4, 6, 2, 1, 2024, '2024-10-11', ''),
(6, 5, 1, 1, 2024, '2024-10-12', ''),
(10, 7, 3, 1, 2024, '2024-10-12', ''),
(12, 4, 1, 1, 20, '2024-10-12', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_matricula`
--

CREATE TABLE `tipos_matricula` (
  `id_tipo_matricula` int(11) NOT NULL,
  `nombre_tipo` varchar(50) NOT NULL,
  `descripcion_tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipos_matricula`
--

INSERT INTO `tipos_matricula` (`id_tipo_matricula`, `nombre_tipo`, `descripcion_tipo`) VALUES
(1, 'Regular', 'Matrícula regular para alumnos que siguen el curso dentro del calendario normal.'),
(2, 'Extraordinaria', 'Matrícula extraordinaria para casos especiales o fuera del calendario habitual.');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id_alumno`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id_curso`),
  ADD UNIQUE KEY `nombre_curso` (`nombre_curso`);

--
-- Indices de la tabla `curso_docente`
--
ALTER TABLE `curso_docente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UC_CursoDocenteGrado` (`id_curso`,`id_docente`,`id_grado`),
  ADD KEY `id_docente` (`id_docente`),
  ADD KEY `id_grado` (`id_grado`);

--
-- Indices de la tabla `docentes`
--
ALTER TABLE `docentes`
  ADD PRIMARY KEY (`id_docente`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `grados`
--
ALTER TABLE `grados`
  ADD PRIMARY KEY (`id_grado`);

--
-- Indices de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD PRIMARY KEY (`id_matricula`),
  ADD UNIQUE KEY `UC_AlumnoAnio` (`id_alumno`,`anio`),
  ADD KEY `id_grado` (`id_grado`),
  ADD KEY `id_tipo_matricula` (`id_tipo_matricula`);

--
-- Indices de la tabla `tipos_matricula`
--
ALTER TABLE `tipos_matricula`
  ADD PRIMARY KEY (`id_tipo_matricula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id_alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `curso_docente`
--
ALTER TABLE `curso_docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `docentes`
--
ALTER TABLE `docentes`
  MODIFY `id_docente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `grados`
--
ALTER TABLE `grados`
  MODIFY `id_grado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  MODIFY `id_matricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `tipos_matricula`
--
ALTER TABLE `tipos_matricula`
  MODIFY `id_tipo_matricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `curso_docente`
--
ALTER TABLE `curso_docente`
  ADD CONSTRAINT `curso_docente_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `curso_docente_ibfk_2` FOREIGN KEY (`id_docente`) REFERENCES `docentes` (`id_docente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `curso_docente_ibfk_3` FOREIGN KEY (`id_grado`) REFERENCES `grados` (`id_grado`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `matriculas_ibfk_1` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matriculas_ibfk_2` FOREIGN KEY (`id_grado`) REFERENCES `grados` (`id_grado`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matriculas_ibfk_3` FOREIGN KEY (`id_tipo_matricula`) REFERENCES `tipos_matricula` (`id_tipo_matricula`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
