<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${cursoDocente != null ? 'Editar' : 'Nuevo'} Curso Docente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/validations.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>${cursoDocente != null ? 'Editar' : 'Nuevo'} Curso Docente</h2>

        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <form action="${cursoDocente != null ? 'update' : 'insert'}" method="post" onsubmit="return validarRegistroCursoDocente()">
            <c:if test="${cursoDocente != null}">
                <input type="hidden" name="id" value="${cursoDocente.id}">
            </c:if>
            <div class="mb-3">
                <label for="curso" class="form-label">Curso:</label>
                <select class="form-select" id="curso" name="curso" required>
                    <option value="">Seleccionar opción</option>
                    <c:forEach var="curso" items="${listaCursos}">
                        <option value="${curso.id_curso}" ${cursoDocente != null && cursoDocente.curso.id_curso == curso.id_curso ? 'selected' : ''}>${curso.nombre_curso}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="docente" class="form-label">Docente:</label>
                <select class="form-select" id="docente" name="docente" required>
                    <option value="">Seleccionar opción</option>
                    <c:forEach var="docente" items="${listaDocentes}">
                        <option value="${docente.id_docente}" ${cursoDocente != null && cursoDocente.docente.id_docente == docente.id_docente ? 'selected' : ''}>${docente.apellido} ${docente.nombre}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="grado" class="form-label">Grado:</label>
                <select class="form-select" id="grado" name="grado" required>
                    <option value="">Seleccionar opción</option>
                    <c:forEach var="grado" items="${listaGrados}">
                        <option value="${grado.id_grado}" ${cursoDocente != null && cursoDocente.grado.id_grado == grado.id_grado ? 'selected' : ''}>${grado.nombre_grado}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">${cursoDocente != null ? 'Actualizar' : 'Guardar'}</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
