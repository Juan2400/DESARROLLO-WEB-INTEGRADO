<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bienvenido al Sistema de Gestión Escolar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Sistema de Gestión Escolar</h1>
        <p class="text-center">Bienvenido al sistema de gestión de matrículas, docentes, alumnos y más.</p>
        
        <div class="row mt-5 justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-user-graduate card-icon text-primary"></i>
                        <h5 class="card-title">Gestión de Matrículas</h5>
                        <p class="card-text">Ver y gestionar las matrículas.</p>
                        <a href="matricula/list" class="btn btn-primary">Acceder</a>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-book card-icon text-success"></i>
                        <h5 class="card-title">Gestión de Cursos</h5>
                        <p class="card-text">Ver y gestionar la lista de cursos.</p>
                        <a href="curso/list" class="btn btn-primary">Acceder</a>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-chalkboard-teacher card-icon text-info"></i>
                        <h5 class="card-title">Asignación de Cursos a Docentes</h5>
                        <p class="card-text">Asignar cursos a los docentes.</p>
                        <a href="curso-docente/list" class="btn btn-primary">Acceder</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-users card-icon text-warning"></i>
                        <h5 class="card-title">Gestión de Alumnos</h5>
                        <p class="card-text">Ver y gestionar la lista de alumnos.</p>
                        <a href="alumno/list" class="btn btn-primary">Acceder</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="fas fa-user-tie card-icon text-danger"></i>
                        <h5 class="card-title">Gestión de Docentes</h5>
                        <p class="card-text">Ver y gestionar la lista de docentes.</p>
                        <a href="docente/list" class="btn btn-primary">Acceder</a>
                    </div>
                </div>
            </div>
        </div>
        
        <footer class="text-center mt-5">
            <p>&copy; 2024 Colegio XYZ. Todos los derechos reservados.</p>
        </footer>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>