<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib 
    uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Paciente</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script src="js/validation.js"></script>
</head>
<body>
    <h1>Registro de Paciente</h1>
    <form action="registroPaciente" method="post" onsubmit="return validarFormulario()">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required><br>
        <label for="genero">Género:</label>
        <select id="genero" name="genero" required>
            <option value="">Seleccione...</option>
            <option value="masculino">Masculino</option>
            <option value="femenino">Femenino</option>
            <option value="otro">Otro</option>
        </select><br>
        <label for="direccion">Dirección:</label>
        <textarea id="direccion" name="direccion" required></textarea><br>
        <label for="telefono">Teléfono:</label>
        <input type="tel" id="telefono" name="telefono" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label for="grupoSanguineo">Grupo Sanguíneo:</label>
        <select id="grupoSanguineo" name="grupoSanguineo" required>
            <option value="">Seleccione...</option>
            <option value="A+">A+</option>
            <option value="A-">A-</option>
            <option value="B+">B+</option>
            <option value="B-">B-</option>
            <option value="AB+">AB+</option>
            <option value="AB-">AB-</option>
            <option value="O+">O+</option>
            <option value="O-">O-</option>
        </select><br>
        <label for="codigoSeguro">Tipo de Seguro:</label>
        <select id="codigoSeguro" name="codigoSeguro" required>
            <option value="">Seleccione...</option>
            <c:forEach var="seguro" items="${seguros}">
                <option value="${seguro.codigoTipo}">
                    ${seguro.nombre}</option>
            </c:forEach>


        </select><br>
        <input type="submit" value="Registrar Paciente">
    </form>
    <a href="index.jsp">Volver al Inicio</a>
</body>
