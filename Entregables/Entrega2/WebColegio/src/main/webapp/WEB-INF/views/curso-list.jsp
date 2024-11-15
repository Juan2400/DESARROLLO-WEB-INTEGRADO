<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Cursos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <%@ include file="nav.jsp" %>
    
    <div class="container mt-5">
        <h2>Lista de Cursos</h2>
        <a href="new" class="btn btn-primary mb-3">Agregar Nuevo Curso</a>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="curso" items="${listaCursos}">
                    <tr>
                        <td>${curso.id_curso}</td>
                        <td>${curso.nombre_curso}</td>
                        <td>${curso.descripcion_curso}</td>
                        <td>
                            <a href="edit?id=${curso.id_curso}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="delete?id=${curso.id_curso}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar este curso?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
