<!-- categoria-form.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Categoría</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>${categoria != null ? 'Editar' : 'Nueva'} Categoría</h2>
        <form action="${categoria != null ? 'update' : 'insert'}" method="post">
            <c:if test="${categoria != null}">
                <input type="hidden" name="id" value="${categoria.id}">
            </c:if>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre de la Categoría:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${categoria.nombre}" required>
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre de la Categoría:</label>
                
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
