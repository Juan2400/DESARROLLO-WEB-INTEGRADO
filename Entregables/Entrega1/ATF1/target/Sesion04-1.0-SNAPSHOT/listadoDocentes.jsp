<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Listado de Pacientes</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">
    </head>
    <body>
        <h1>Listado de Docentes</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Especialidad</th>
                <th>Teléfono</th>
                <th>Email</th>
            </tr>
            <c:forEach var="docente" items="${docentes}">
                <tr>
                    <td>${docente.idDocente}</td>
                    <td>${docente.nombre}</td>
                    <td>${docente.apellido}</td>
                    <td>${docente.especialidad}</td>
                    <td>${docente.telefono}</td>
                    <td>${docente.email}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.jsp">Volver al Inicio</a>
    </body>
</html>
