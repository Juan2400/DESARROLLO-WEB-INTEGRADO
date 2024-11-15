package com.colegio.modelo;

import com.colegio.modelo.CursoDAO;
import com.colegio.modelo.Curso;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public void insertar(Curso curso) throws SQLException {
        String sql = "INSERT INTO Cursos (nombre_curso, descripcion_curso) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, curso.getNombre_curso());
            pstmt.setString(2, curso.getDescripcion_curso());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    curso.setId_curso(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Curso obtenerPorId(int id_curso) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_curso);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("nombre_curso"),
                        rs.getString("descripcion_curso")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Curso> listarTodos() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM Cursos";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cursos.add(new Curso(
                    rs.getInt("id_curso"),
                    rs.getString("nombre_curso"),
                    rs.getString("descripcion_curso")
                ));
            }
        }
        return cursos;
    }

    @Override
    public void actualizar(Curso curso) throws SQLException {
        String sql = "UPDATE Cursos SET nombre_curso = ?, descripcion_curso = ? WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curso.getNombre_curso());
            pstmt.setString(2, curso.getDescripcion_curso());
            pstmt.setInt(3, curso.getId_curso());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id_curso) throws SQLException {
        String sql = "DELETE FROM Cursos WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_curso);
            pstmt.executeUpdate();
        }
    }
}
