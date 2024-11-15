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
        <h1>Listado de Cursos</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre del Curso</th>
                <th>Descripción</th>
                <th>Docente</th>
                <th>Fecha de Inicio</th>
                <th>Fecha de Fin</th>
            </tr>
            <c:forEach var="curso" items="${cursos}">
                <tr>
                    <td>${curso.idCurso}</td>
                    <td>${curso.nombreCurso}</td>
                    <td>${curso.descripcionCurso}</td>
                    <td>${curso.docente.nombre} ${curso.docente.apellido}</td>
                    <td><fmt:formatDate value="${curso.fechaInicio}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${curso.fechaFin}" pattern="dd/MM/yyyy"/></td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.jsp">Volver al Inicio</a>
    </body>
</html>
