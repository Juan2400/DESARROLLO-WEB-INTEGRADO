package com.colegio.dao;

import com.colegio.model.Curso;
import com.colegio.model.Docente;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void registrarCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO Cursos (nombre_curso, descripcion_curso, id_docente, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curso.getNombreCurso());
            pstmt.setString(2, curso.getDescripcionCurso());
            pstmt.setInt(3, curso.getDocente().getIdDocente());
            pstmt.setDate(4, new java.sql.Date(curso.getFechaInicio().getTime()));
            pstmt.setDate(5, new java.sql.Date(curso.getFechaFin().getTime()));
            pstmt.executeUpdate();
        }
    }

    public List<Curso> listarCursos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT c.*, d.nombre AS nombre_docente, d.apellido AS apellido_docente FROM Cursos c " +
                     "JOIN Docentes d ON c.id_docente = d.id_docente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombreCurso(rs.getString("nombre_curso"));
                curso.setDescripcionCurso(rs.getString("descripcion_curso"));
                curso.setFechaInicio(rs.getDate("fecha_inicio"));
                curso.setFechaFin(rs.getDate("fecha_fin"));

                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre_docente"));
                docente.setApellido(rs.getString("apellido_docente"));
                curso.setDocente(docente);

                cursos.add(curso);
            }
        }
        return cursos;
    }
}
