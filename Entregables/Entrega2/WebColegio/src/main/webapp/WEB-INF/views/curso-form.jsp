<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/validations.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>${curso != null ? 'Editar' : 'Nuevo'} Curso</h2>
        <form action="${curso != null ? 'update' : 'insert'}" method="post" onsubmit="return validarRegistroCurso()">
            <c:if test="${curso != null}">
                <input type="hidden" name="id" value="${curso.id_curso}">
            </c:if>
            <div class="mb-3">
                <label for="nombre_curso" class="form-label">Nombre del Curso:</label>
                <input type="text" class="form-control" id="nombre_curso" name="nombre_curso" value="${curso.nombre_curso}" required>
            </div>
            <div class="mb-3">
                <label for="descripcion_curso" class="form-label">Descripción:</label>
                <textarea class="form-control" id="descripcion_curso" name="descripcion_curso">${curso.descripcion_curso}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
