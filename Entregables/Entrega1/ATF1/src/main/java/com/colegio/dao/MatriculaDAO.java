package com.colegio.dao;

import com.colegio.model.Matricula;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public void registrarMatricula(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO Matriculas (id_alumno, id_curso, fecha_matricula, tipo_matricula, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula.getAlumno().getIdAlumno());
            pstmt.setInt(2, matricula.getCurso().getIdCurso());
            pstmt.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            pstmt.setString(4, matricula.getTipoMatricula());
            pstmt.setString(5, matricula.getObservaciones());
            pstmt.executeUpdate();
        }
    }

    public List<Matricula> listarMatriculas() throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, a.nombre AS nombre_alumno, a.apellido AS apellido_alumno, c.nombre_curso "
                + "FROM Matriculas m "
                + "JOIN Alumnos a ON m.id_alumno = a.id_alumno "
                + "JOIN Cursos c ON m.id_curso = c.id_curso";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("id_matricula"));

               
                String nombreCompleto = rs.getString("apellido_alumno") + " " +  rs.getString("nombre_alumno") ;
                matricula.getAlumno().setNombre(nombreCompleto);

                matricula.getCurso().setNombreCurso(rs.getString("nombre_curso"));
                matricula.setFechaMatricula(rs.getDate("fecha_matricula"));
                matricula.setTipoMatricula(rs.getString("tipo_matricula"));
                matricula.setObservaciones(rs.getString("observaciones"));

                matriculas.add(matricula);
            }
        }
        return matriculas;
    }

}
