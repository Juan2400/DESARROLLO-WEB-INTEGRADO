<%-- 
    Document   : registroAlumno
    Created on : 5 set. 2024, 18:44:47
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Alumno</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script src="js/validation.js"></script>
</head>
<body>
    <h1>Registro de Alumno</h1>
    <form action="registroAlumno" method="post" onsubmit="return validarRegistroAlumno()">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required><br>
        <label for="direccion">Dirección:</label>
        <textarea id="direccion" name="direccion" required></textarea><br>
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label for="idGrado">Grado:</label>
        <select id="idGrado" name="idGrado" required>
            <option value="">Seleccione...</option>
            <c:forEach var="grado" items="${grados}">
                <option value="${grado.idGrado}">${grado.nombreGrado}</option>
            </c:forEach>
        </select><br>
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" required><br>
        <input type="submit" value="Registrar Alumno">
    </form>
    <a href="index.jsp">Volver al Inicio</a>
</body>
</html>