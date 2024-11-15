<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Matrícula</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Registro de Matrícula</h1>
    <form action="registroMatricula" method="post" onsubmit="return validarRegistroMatricula()">
        <label for="idAlumno">Alumno:</label>
        <select id="idAlumno" name="idAlumno" required>
            <option value="">Seleccione...</option>
            <c:forEach var="alumno" items="${alumnos}">
                <option value="${alumno.idAlumno}">${alumno.apellido} ${alumno.nombre}</option>
            </c:forEach>
        </select><br>

        <label for="idCurso">Curso:</label>
        <select id="idCurso" name="idCurso" required>
            <option value="">Seleccione...</option>
            <c:forEach var="curso" items="${cursos}">
                <option value="${curso.idCurso}">${curso.nombreCurso}</option>
            </c:forEach>
        </select><br>

        <label for="fechaMatricula">Fecha de Matrícula:</label>
        <input type="date" id="fechaMatricula" name="fechaMatricula" required><br>

        <label for="tipoMatricula">Tipo de Matrícula:</label>
        <select id="tipoMatricula" name="tipoMatricula" required>
            <option value="regular">Regular</option>
            <option value="extraordinaria">Extraordinaria</option>
        </select><br>

        <label for="observaciones">Observaciones:</label>
        <textarea id="observaciones" name="observaciones"></textarea><br>

        <input type="submit" value="Registrar Matrícula">
    </form>
    <a href="index.jsp">Volver al Inicio</a>
</body>
</html>
