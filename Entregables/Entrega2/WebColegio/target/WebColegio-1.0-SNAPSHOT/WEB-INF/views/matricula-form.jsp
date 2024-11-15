<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Matrícula</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/validations.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>${matricula != null ? 'Editar' : 'Nueva'} Matrícula</h2>
        
       
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        
        <form action="${matricula != null ? 'update' : 'insert'}" method="post" onsubmit="return validarRegistroMatricula()">
            <c:if test="${matricula != null}">
                <input type="hidden" name="id" value="${matricula.id_matricula}">
            </c:if>
            <div class="mb-3">
                <label for="alumno" class="form-label">Alumno:</label>
                <select class="form-select" id="alumno" name="alumno" required>
                    <option value="">
                        Seleccionar opción 
                    </option>
                    <c:forEach var="alumno" items="${listaAlumnos}">
                        <option value="${alumno.id_alumno}" ${matricula != null && matricula.alumno.id_alumno == alumno.id_alumno ? 'selected' : ''}>${alumno.apellido} ${alumno.nombre}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="grado" class="form-label">Grado:</label>
                <select class="form-select" id="grado" name="grado" required>
                    <option value="">
                        Seleccionar opción 
                    </option>
                    <c:forEach var="grado" items="${listaGrados}">
                        <option value="${grado.id_grado}" ${matricula != null && matricula.grado.id_grado == grado.id_grado ? 'selected' : ''}>${grado.nombre_grado}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="tipo_matricula" class="form-label">Tipo de Matrícula:</label>
                <select class="form-select" id="tipo_matricula" name="tipo_matricula" required>
                    <option value="">
                        Seleccionar opción 
                    </option>
                    <c:forEach var="tipo" items="${listaTiposMatricula}">
                        <option value="${tipo.id_tipo_matricula}" ${matricula != null && matricula.tipo_matricula.id_tipo_matricula == tipo.id_tipo_matricula ? 'selected' : ''}>${tipo.nombre_tipo}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="anio" class="form-label">Año:</label>
                <input type="number" class="form-control" id="anio" name="anio" value="${matricula != null ? matricula.anio : ''}" required>
            </div>

            <div class="mb-3">
                <label for="fecha_matricula" class="form-label">Fecha de Matrícula:</label>
                <input type="date" class="form-control" id="fecha_matricula" name="fecha_matricula" value="${matricula != null ? matricula.fecha_matricula : ''}"required><br>
            </div>

            <div class="mb-3">
                <label for="observaciones" class="form-label">Observaciones:</label>
                <textarea class="form-control" id="observaciones" name="observaciones">${matricula != null ? matricula.observaciones : ''}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="list" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>

   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
