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
        <h1>Registro de Docente</h1>
        <form action="registroDocente" method="post" onsubmit="return validarRegistroDocente()">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required><br>

            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required><br>

            <label for="especialidad">Especialidad:</label>
            <input type="text" id="especialidad" name="especialidad" required><br>

            <label for="telefono">Teléfono:</label>
            <input type="tel" id="telefono" name="telefono" required><br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <input type="submit" value="Registrar Docente">
        </form>

        <a href="index.jsp">Volver al Inicio</a>
    </body>
</html>
