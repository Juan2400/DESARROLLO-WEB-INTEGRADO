package com.colegio.dao;

import com.colegio.model.Alumno;
import com.colegio.model.Grado;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    public void registrarAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO Alumnos (nombre, apellido, id_grado, fecha_nacimiento, direccion, telefono, email, dni) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getApellido());
            pstmt.setInt(3, alumno.getGrado().getIdGrado());
            pstmt.setDate(4, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            pstmt.setString(5, alumno.getDireccion());
            pstmt.setString(6, alumno.getTelefono());
            pstmt.setString(7, alumno.getEmail());
            pstmt.setString(8, alumno.getDni());
            pstmt.executeUpdate();
        }
    }

    public List<Alumno> listarAlumnos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT a.*, g.nombre_grado FROM Alumnos a JOIN Grados g ON a.id_grado = g.id_grado";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setEmail(rs.getString("email"));
                alumno.setDni(rs.getString("dni"));

                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_grado"));
                grado.setNombreGrado(rs.getString("nombre_grado"));
                alumno.setGrado(grado);

                alumnos.add(alumno);
            }
        }
        return alumnos;
    }
}
