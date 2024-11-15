<!-- producto-form.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>${producto != null ? 'Editar' : 'Nuevo'} Producto</h2>
        <form action="${producto != null ? 'update' : 'insert'}" method="post">
            <c:if test="${producto != null}">
                <input type="hidden" name="id" value="${producto.id}">
            </c:if>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre del Producto:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${producto.nombre}" required>
            </div>
            <div class="mb-3">
                <label for="precio" class="form-label">Precio:</label>
                <input type="number" class="form-control" id="precio" name="precio" value="${producto.precio}" step="0.01" required>
            </div>
            <div class="mb-3">
                <label for="categoria" class="form-label">Categoría:</label>
                <select class="form-select" id="categoria" name="categoria" required>
                        <option value="">
                            Seleccionar Categoria
                        </option>
                    <c:forEach var="categoria" items="${listaCategorias}">
                        <option value="${categoria.id}" ${producto.categoria.id == categoria.id ? 'selected' : ''}>
                            ${categoria.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
