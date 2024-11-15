<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Gestión de Matrículas</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="container my-5">
        <h1 class="text-center mb-4">Sistema de Gestión de Matrículas</h1>

        <nav>
            <ul class="nav flex-column mb-3">
                <!-- Alumnos -->
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="registroAlumno">Registrar Nuevo Alumno</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="listadoAlumnos">Ver Listado de Alumnos</a>
                </li>
                <hr>
                <!-- Docentes -->
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="registroDocente">Registrar Nuevo Docente</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="listadoDocentes">Ver Listado de Docentes</a>
                </li>
                <hr>
                <!-- Cursos -->
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="registroCurso">Registrar Nuevo Curso</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="listadoCursos">Ver Listado de Cursos</a>
                </li>
                <hr>
                <!-- Matrículas -->
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="registroMatricula">Registrar Nueva Matrícula</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-secondary mb-2" href="listadoMatriculas">Ver Listado de Matrículas</a>
                </li>
            </ul>
        </nav>

        <footer class="text-center mt-5">
            <p>&copy; 2024 Colegio XYZ. Todos los derechos reservados.</p>
        </footer>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
