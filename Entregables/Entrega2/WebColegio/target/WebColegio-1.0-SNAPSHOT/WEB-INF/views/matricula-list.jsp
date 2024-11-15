<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Matrículas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <%@ include file="nav.jsp" %>
    
    <div class="container mt-5">
        <h2>Lista de Matrículas</h2>
        <a href="new" class="btn btn-primary mb-3">Nueva Matrícula</a>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Alumno</th>
                    <th>Grado</th>
                    <th>Tipo de Matrícula</th>
                    <th>Año</th>
                    <th>Fecha Matrícula</th>
                    <th>Observación</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="matricula" items="${listaMatriculas}">
                    <tr>
                        <td>${matricula.id_matricula}</td>
                        <td>${matricula.alumno.apellido} ${matricula.alumno.nombre} </td>
                        <td>${matricula.grado.nombre_grado}</td>
                        <td>${matricula.tipo_matricula.nombre_tipo}</td>
                        <td>${matricula.anio}</td>
                        <td>${matricula.fecha_matricula}</td>
                        <td>${matricula.observaciones}</td>
                        <td>
                            <a href="edit?id=${matricula.id_matricula}" class="btn btn-sm btn-warning">Editar</a>
                            <a href="delete?id=${matricula.id_matricula}" class="btn btn-sm btn-danger" onclick="return confirm('¿Está seguro de eliminar esta matrícula?')">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
