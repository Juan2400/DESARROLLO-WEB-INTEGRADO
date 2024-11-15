<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${docente != null ? 'Editar Docente' : 'Nuevo Docente'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/validations.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">${docente != null ? 'Editar Docente' : 'Nuevo Docente'}</h2>
        <form action="${docente != null ? 'update' : 'insert'}" method="post" onsubmit="return validarRegistroDocente()">
          
            <c:if test="${docente != null}">
                <input type="hidden" name="id" value="${docente.id_docente}">
            </c:if>

            
            <div class="mb-3">
                <label for="dni" class="form-label">DNI:</label>
                <input type="text" class="form-control" id="dni" name="dni" value="${docente != null ? docente.dni : ''}" required>
            </div>

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${docente != null ? docente.nombre : ''}" required>
            </div>

            <div class="mb-3">
                <label for="apellido" class="form-label">Apellido:</label>
                <input type="text" class="form-control" id="apellido" name="apellido" value="${docente != null ? docente.apellido : ''}" required>
            </div>

            <div class="mb-3">
                <label for="especialidad" class="form-label">Especialidad:</label>
                <input type="text" class="form-control" id="especialidad" name="especialidad" value="${docente != null ? docente.especialidad : ''}" required>
            </div>

            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono:</label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${docente != null ? docente.telefono : ''}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${docente != null ? docente.email : ''}" required>
            </div>

            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
