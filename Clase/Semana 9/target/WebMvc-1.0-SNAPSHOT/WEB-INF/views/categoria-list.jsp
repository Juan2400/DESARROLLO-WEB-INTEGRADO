<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista de Categorías</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Gestión de Productos</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/categoria/list">Categorías</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/producto/list">Productos</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Lista de Categorías</h2>
            <a href="${pageContext.request.contextPath}/categoria/new" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Nueva Categoría
            </a>
        </div>

        <c:if test="${!empty mensaje}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="categoria" items="${listaCategorias}">
                    <tr>
                        <td>${categoria.id}</td>
                        <td>${categoria.nombre}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/categoria/edit?id=${categoria.id}" class="btn btn-warning btn-sm">
                                <i class="bi bi-pencil"></i> Editar
                            </a>
                            <a href="${pageContext.request.contextPath}/categoria/delete?id=${categoria.id}" class="btn btn-danger btn-sm" 
                               onclick="return confirm('¿Está seguro de que desea eliminar esta categoría?')">
                                <i class="bi bi-trash"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
