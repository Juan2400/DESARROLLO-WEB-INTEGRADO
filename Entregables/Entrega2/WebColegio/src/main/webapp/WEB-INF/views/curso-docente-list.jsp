<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Cursos Docentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <%@ include file="nav.jsp" %>
    
    <div class="container mt-5">
        <h2>Lista de Cursos Docentes</h2>
        <a href="new" class="btn btn-primary mb-3">Asignar Nuevo Curso</a>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Curso</th>
                    <th>Docente</th>
                    <th>Grado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cursoDocente" items="${listaCursosDocentes}">
                    <tr>
                        <td>${cursoDocente.id}</td>
                        <td>${cursoDocente.curso.nombre_curso}</td>
                        <td>${cursoDocente.docente.apellido} ${cursoDocente.docente.nombre} </td>
                        <td>${cursoDocente.grado.nombre_grado}</td>
                        <td>
                            <a href="edit?id=${cursoDocente.id}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="delete?id=${cursoDocente.id}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar esta asignación?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
