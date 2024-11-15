<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Alumno</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/validations.js"></script>
</head>
<body>
    
    <div class="container mt-5">
        <h2>${alumno != null ? 'Editar' : 'Nuevo'} Alumno</h2>
        <form action="${alumno != null ? 'update' : 'insert'}" method="post" onsubmit="return validarRegistroAlumno()">
            <c:if test="${alumno != null}">
                <input type="hidden" name="id" value="${alumno.id_alumno}">
            </c:if>
            <div class="mb-3">
                <label for="dni" class="form-label">DNI:</label>
                <input type="text" class="form-control" id="dni" name="dni" value="${alumno.dni}" required>
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${alumno.nombre}" required>
            </div>
            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" class="form-control" id="apellido" name="apellido" value="${alumno.apellido}" required>
            </div>
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección:</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="${alumno.direccion}">
            </div>
            <div class="mb-3">
                <label for="telefonoApoderado" class="form-label">Teléfono del Apoderado:</label>
                <input type="text" class="form-control" id="telefonoApoderado" name="telefonoApoderado" value="${alumno.telefono}">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${alumno.email}">
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
