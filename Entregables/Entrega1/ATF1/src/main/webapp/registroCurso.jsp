<%-- 
    Document   : registroCurso
    Created on : 5 set. 2024, 18:49:11
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registro de Paciente</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <script src="js/validation.js"></script>
    </head>
    <body>
        <h1>Registro de Cursos</h1>
        <form action="registroCurso" method="post" onsubmit="return validarRegistroCurso()">
            <label for="nombreCurso">Nombre del Curso:</label>
            <input type="text" id="nombreCurso" name="nombreCurso" required><br>

            <label for="descripcionCurso">Descripción del Curso:</label>
            <textarea id="descripcionCurso" name="descripcionCurso" required></textarea><br>

            <label for="idDocente">Docente:</label>
            <select id="idDocente" name="idDocente" required>
                <option value="">Seleccione...</option>
                <c:forEach var="docente" items="${docentes}">
                    <option value="${docente.idDocente}">${docente.nombre} ${docente.apellido}</option>
                </c:forEach>
            </select><br>

            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required><br>

            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" required><br>

            <input type="submit" value="Registrar Curso">
        </form>

        <a href="index.jsp">Volver al Inicio</a>
    </body>
</html>
