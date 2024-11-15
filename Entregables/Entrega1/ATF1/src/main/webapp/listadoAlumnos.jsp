<%-- 
    Document   : listadoAlumnos
    Created on : 5 set. 2024, 19:35:27
    Author     : HP
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Alumnos</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Listado de Alumnos</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Grado</th>
            <th>Fecha de Nacimiento</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Email</th>
            <th>DNI</th>
        </tr>
        <c:forEach var="alumno" items="${alumnos}">
            <tr>
                <td>${alumno.idAlumno}</td>
                <td>${alumno.nombre}</td>
                <td>${alumno.apellido}</td>
                <td>${alumno.grado.nombreGrado}</td>
                <td>${alumno.fechaNacimiento}</td>
                <td>${alumno.direccion}</td>
                <td>${alumno.telefono}</td>
                <td>${alumno.email}</td>
                <td>${alumno.dni}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Volver al Inicio</a>
</body>
</html>
