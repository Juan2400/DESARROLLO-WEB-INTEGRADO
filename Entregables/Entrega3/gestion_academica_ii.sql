-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-12-2024 a las 18:22:15
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
-- Base de datos: `gestion_academica_ii`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `id_alumno` int(11) NOT NULL,
  `codigo_estudiante` char(14) NOT NULL,
  `dni` char(8) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `sexo` enum('M','F') NOT NULL,
  `telefono_referencia` varchar(20) DEFAULT NULL,
  `id_padre` int(11) DEFAULT NULL,
  `id_madre` int(11) DEFAULT NULL,
  `id_apoderado` int(11) DEFAULT NULL,
  `id_estado_estudiante` int(11) DEFAULT NULL,
  `id_grado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`id_alumno`, `codigo_estudiante`, `dni`, `nombre`, `apellido`, `direccion`, `sexo`, `telefono_referencia`, `id_padre`, `id_madre`, `id_apoderado`, `id_estado_estudiante`, `id_grado`) VALUES
(1, 'A12345678901', '23456789', 'Juan', 'López Fernández', 'Calle Principal 101', 'M', '555-2001', 1, 2, 3, 1, 1),
(2, 'A23456789012', '34567890', 'Sofía', 'Pérez Sánchez', 'Calle Sol 203', 'F', '555-2002', 3, 4, 5, 1, 2),
(3, 'A34567890123', '45678901', 'Pedro', 'Gómez Ruiz', 'Avenida Libertad 405', 'M', '555-2003', 5, 6, 7, 2, 3),
(4, 'A45678901234', '56789012', 'Mariana', 'Martínez Jiménez', 'Calle Río 607', 'F', '555-2004', 8, 9, 10, 2, 4),
(5, 'A56789012345', '67890123', 'David', 'Rodríguez García', 'Calle Mar 809', 'M', '555-2005', 2, 3, 4, 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL,
  `nombre_curso` varchar(100) NOT NULL,
  `descripcion_curso` varchar(255) DEFAULT NULL,
  `id_especialidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id_curso`, `nombre_curso`, `descripcion_curso`, `id_especialidad`) VALUES
(1, 'Desarrollo personal', 'Curso enfocado en el crecimiento integral de la persona.', 1),
(2, 'Ciudadanía y Cívica', 'Curso sobre los derechos y responsabilidades cívicas.', 3),
(3, 'Ciencias sociales', 'Curso que abarca el estudio de fenómenos sociales y humanos.', 3),
(4, 'Educación para el trabajo', 'Curso que prepara a los estudiantes para ingresar al mundo laboral.', 1),
(5, 'Educación física', 'Curso enfocado en el desarrollo físico y la actividad deportiva.', 1),
(6, 'Comunicación', 'Curso centrado en habilidades de comunicación verbal y no verbal.', 2),
(7, 'Arte y cultura', 'Curso sobre las artes y el desarrollo cultural.', 2),
(8, 'Inglés como lengua extranjera', 'Curso para el aprendizaje del idioma inglés.', 2),
(9, 'Matemática', 'Curso sobre principios y aplicaciones matemáticas.', 4),
(10, 'Ciencia y tecnología', 'Curso sobre descubrimientos científicos y su aplicación tecnológica.', 4),
(11, 'Educación religiosa', 'Curso sobre los principios de la religión y la moral.', 1),
(33, 'Historia de Peru', 'peru', 1);

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
(2, 2, 2, 3),
(16, 3, 2, 1),
(14, 9, 4, 1),
(17, 9, 17, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docentes`
--

CREATE TABLE `docentes` (
  `id_docente` int(11) NOT NULL,
  `dni` char(8) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `sexo` enum('M','F') NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `estado` enum('Activo','Licencia') DEFAULT 'Activo',
  `id_especialidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `docentes`
--

INSERT INTO `docentes` (`id_docente`, `dni`, `nombre`, `apellido`, `sexo`, `telefono`, `email`, `estado`, `id_especialidad`) VALUES
(2, '21233232', 'Luis', 'Garcia Mendez', 'M', '987789678', 'Luis@example.com', 'Activo', 3),
(4, '71818629', 'Piero', 'Crisanto Grau', 'M', '947432941', 'piero@gmail.com', 'Activo', 4),
(17, '03673839', 'Carlos', 'Crisanto', 'M', '947432941', 'carloscris@gmail.com', 'Activo', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidades`
--

CREATE TABLE `especialidades` (
  `id_especialidad` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `especialidades`
--

INSERT INTO `especialidades` (`id_especialidad`, `nombre`, `descripcion`) VALUES
(1, 'Desarrollo Humano Integral', 'Se centra en el desarrollo integral de la persona, abarcando aspectos como la formación del carácter, la salud y el bienestar, y la relación con los demás y con uno mismo.'),
(2, 'Comunicación y Expresión', 'Se enfoca en el desarrollo de habilidades comunicativas y expresivas, tanto a nivel verbal como no verbal.'),
(3, 'Ciencias Sociales', 'Se centra en el desarrollo del conocimiento de los fenómenos naturales y sociales.'),
(4, 'Matemáticas', 'Se enfoca en el desarrollo del pensamiento lógico y abstracto, así como la aplicación de métodos cuantitativos para resolver problemas.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_estudiante`
--

CREATE TABLE `estado_estudiante` (
  `id_estado_estudiante` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estado_estudiante`
--

INSERT INTO `estado_estudiante` (`id_estado_estudiante`, `nombre`, `descripcion`) VALUES
(1, 'INGRESANTE', 'Alumno que ha terminado sus estudios de primaria'),
(2, 'TRASLADADO', 'El alumno viene de otro colegio');

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
(1, 'Primero de Secundaria', 'Primer grado de secundaria'),
(2, 'Segundo de Secundaria', 'Segundo grado de secundaria'),
(3, 'Tercero de Secundaria', 'Tercer grado de secundaria'),
(4, 'Cuarto de Secundaria', 'Cuarto grado de secundaria'),
(5, 'Quinto de Secundaria', 'Quinto grado de secundaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `id_matricula` int(11) NOT NULL,
  `id_alumno` int(11) DEFAULT NULL,
  `id_grado` int(11) DEFAULT NULL,
  `anio` int(11) NOT NULL,
  `fecha_registro` date DEFAULT curdate(),
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`id_matricula`, `id_alumno`, `id_grado`, `anio`, `fecha_registro`, `observaciones`) VALUES
(4, 1, 1, 2024, '2024-11-24', ''),
(6, 2, 2, 2024, '2024-11-25', ''),
(10, 3, 3, 2024, '2024-11-26', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsables`
--

CREATE TABLE `responsables` (
  `id_responsable` int(11) NOT NULL,
  `dni` char(8) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `sexo` enum('M','F') NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `responsables`
--

INSERT INTO `responsables` (`id_responsable`, `dni`, `nombre`, `apellido`, `sexo`, `telefono`, `email`, `direccion`) VALUES
(1, '12345678', 'Carlos', 'García Martínez', 'M', '905551001', 'carlos.garcia@email.com', 'Calle Ficticia 123'),
(2, '23456789', 'Ana', 'López Fernández', 'F', '905551002', 'ana.lopez@email.com', 'Avenida Real 456'),
(3, '34567890', 'Luis', 'Pérez Sánchez', 'M', '905551003', 'luis.perez@email.com', 'Calle Sol 789'),
(4, '45678901', 'María', 'Gómez Ruiz', 'F', '905551004', 'maria.gomez@email.com', 'Calle Luna 101'),
(5, '56789012', 'Javier', 'Martínez Jiménez', 'M', '905551005', 'javier.martinez@email.com', 'Avenida Libertad 202'),
(6, '67890123', 'Laura', 'Rodríguez García', 'F', '905551006', 'laura.rodriguez@email.com', 'Calle Río 3037'),
(7, '78901234', 'José', 'Hernández Torres', 'M', '905551007', 'jose.hernandez@email.com', 'Avenida de los Árboles 404'),
(8, '89012345', 'Carmen', 'Fernández Díaz', 'F', '905551008', 'carmen.fernandez@email.com', 'Calle Verde 505'),
(9, '90123456', 'Miguel', 'Jiménez Morales', 'M', '905551009', 'miguel.jimenez@email.com', 'Calle Mar 606'),
(10, '01234567', 'Patricia', 'Sánchez Pérez', 'F', '905551010', 'patricia.sanchez@email.com', 'Avenida de la Paz 707');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`id_alumno`),
  ADD UNIQUE KEY `codigo_estudiante` (`codigo_estudiante`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD KEY `id_padre` (`id_padre`),
  ADD KEY `id_madre` (`id_madre`),
  ADD KEY `id_apoderado` (`id_apoderado`),
  ADD KEY `id_grado` (`id_grado`),
  ADD KEY `id_estado_estudiante` (`id_estado_estudiante`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id_curso`),
  ADD UNIQUE KEY `nombre_curso` (`nombre_curso`),
  ADD KEY `id_especialidad` (`id_especialidad`);

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
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_especialidad` (`id_especialidad`);

--
-- Indices de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  ADD PRIMARY KEY (`id_especialidad`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `estado_estudiante`
--
ALTER TABLE `estado_estudiante`
  ADD PRIMARY KEY (`id_estado_estudiante`),
  ADD UNIQUE KEY `nombre` (`nombre`);

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
  ADD KEY `id_grado` (`id_grado`);

--
-- Indices de la tabla `responsables`
--
ALTER TABLE `responsables`
  ADD PRIMARY KEY (`id_responsable`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `id_alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `id_curso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `curso_docente`
--
ALTER TABLE `curso_docente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `docentes`
--
ALTER TABLE `docentes`
  MODIFY `id_docente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `especialidades`
--
ALTER TABLE `especialidades`
  MODIFY `id_especialidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `estado_estudiante`
--
ALTER TABLE `estado_estudiante`
  MODIFY `id_estado_estudiante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `grados`
--
ALTER TABLE `grados`
  MODIFY `id_grado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  MODIFY `id_matricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de la tabla `responsables`
--
ALTER TABLE `responsables`
  MODIFY `id_responsable` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`id_padre`) REFERENCES `responsables` (`id_responsable`) ON UPDATE CASCADE,
  ADD CONSTRAINT `alumnos_ibfk_2` FOREIGN KEY (`id_madre`) REFERENCES `responsables` (`id_responsable`) ON UPDATE CASCADE,
  ADD CONSTRAINT `alumnos_ibfk_3` FOREIGN KEY (`id_apoderado`) REFERENCES `responsables` (`id_responsable`) ON UPDATE CASCADE,
  ADD CONSTRAINT `alumnos_ibfk_4` FOREIGN KEY (`id_grado`) REFERENCES `grados` (`id_grado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `alumnos_ibfk_5` FOREIGN KEY (`id_estado_estudiante`) REFERENCES `estado_estudiante` (`id_estado_estudiante`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `curso_docente`
--
ALTER TABLE `curso_docente`
  ADD CONSTRAINT `curso_docente_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `curso_docente_ibfk_2` FOREIGN KEY (`id_docente`) REFERENCES `docentes` (`id_docente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `curso_docente_ibfk_3` FOREIGN KEY (`id_grado`) REFERENCES `grados` (`id_grado`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `docentes`
--
ALTER TABLE `docentes`
  ADD CONSTRAINT `docentes_ibfk_1` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidades` (`id_especialidad`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `matriculas_ibfk_1` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matriculas_ibfk_2` FOREIGN KEY (`id_grado`) REFERENCES `grados` (`id_grado`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
