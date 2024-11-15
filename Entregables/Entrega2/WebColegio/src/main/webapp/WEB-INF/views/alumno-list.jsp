<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Lista de Alumnos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>

        <div class="container mt-5">
            <h2>Lista de Alumnos</h2>
            <a href="new" class="btn btn-primary mb-3">Agregar Nuevo Alumno</a>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Dirección</th>
                        <th>Teléfono Apoderado</th>
                        <th>Email</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="alumno" items="${listaAlumnos}">
                        <tr>
                            <td>${alumno.id_alumno}</td>
                            <td>${alumno.dni}</td>
                            <td>${alumno.nombre}</td>
                            <td>${alumno.apellido}</td>
                            <td>${alumno.direccion}</td>
                            <td>${alumno.telefono}</td>
                            <td>${alumno.email}</td>
                            <td>
                                <a href="edit?id=${alumno.id_alumno}" class="btn btn-sm btn-warning">Editar</a>
                                <a href="delete?id=${alumno.id_alumno}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar este alumno?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
