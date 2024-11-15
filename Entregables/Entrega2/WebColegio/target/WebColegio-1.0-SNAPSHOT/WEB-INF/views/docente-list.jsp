<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Docentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <%@ include file="nav.jsp" %>
    
    <div class="container mt-5">
        <h2>Lista de Docentes</h2>
        <a href="new" class="btn btn-primary mb-3">Agregar Nuevo Docente</a>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Especialidad</th>
                    <th>Teléfono</th>
                    <th>Email</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="docente" items="${listaDocentes}">
                    <tr>
                        <td>${docente.id_docente}</td>
                        <td>${docente.dni}</td>
                        <td>${docente.nombre}</td>
                        <td>${docente.apellido}</td>
                        <td>${docente.especialidad}</td>
                        <td>${docente.telefono}</td>
                        <td>${docente.email}</td>
                        <td>
                            <a href="edit?id=${docente.id_docente}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="delete?id=${docente.id_docente}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar este docente?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
