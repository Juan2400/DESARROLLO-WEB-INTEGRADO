<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Matrículas</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h1>Listado de Matrículas</h1>
    <table>
        <tr>
            <th>ID Matrícula</th>
            <th>Alumno</th>
            <th>Curso</th>
            <th>Fecha de Matrícula</th>
            <th>Tipo</th>
            <th>Observaciones</th>
        </tr>
        <c:forEach var="matricula" items="${matriculas}">
            <tr>
                <td>${matricula.idMatricula}</td>
                <td>${matricula.alumno.nombre} ${matricula.alumno.apellido}</td>
                <td>${matricula.curso.nombreCurso}</td>
                <td>${matricula.fechaMatricula}</td>
                <td>${matricula.tipoMatricula}</td>
                <td>${matricula.observaciones}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Volver al Inicio</a>
</body>
</html>
