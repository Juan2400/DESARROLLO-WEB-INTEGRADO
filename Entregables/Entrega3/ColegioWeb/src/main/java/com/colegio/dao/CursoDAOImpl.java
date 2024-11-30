package com.colegio.dao;

import com.colegio.modelo.Curso;
import com.colegio.modelo.Especialidad;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    private EspecialidadDAO especialidadDAO = new EspecialidadDAOImpl();

    @Override
    public void insertar(Curso curso) {
        String sql = "INSERT INTO Cursos (nombre_curso, descripcion_curso, id_especialidad) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, curso.getNombreCurso());
            pstmt.setString(2, curso.getDescripcionCurso());
            pstmt.setInt(3, curso.getEspecialidad().getIdEspecialidad());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    curso.setIdCurso(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT id_curso, nombre_curso, descripcion_curso, id_especialidad FROM Cursos";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNombreCurso(rs.getString("nombre_curso"));
                curso.setDescripcionCurso(rs.getString("descripcion_curso"));
                Especialidad especialidad = especialidadDAO.obtenerPorId(rs.getInt("id_especialidad"));
                curso.setEspecialidad(especialidad);
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public Curso obtenerPorId(int idCurso) {
        String sql = "SELECT id_curso, nombre_curso, descripcion_curso, id_especialidad FROM Cursos WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCurso);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = new Curso();
                    curso.setIdCurso(rs.getInt("id_curso"));
                    curso.setNombreCurso(rs.getString("nombre_curso"));
                    curso.setDescripcionCurso(rs.getString("descripcion_curso"));
                    Especialidad especialidad = especialidadDAO.obtenerPorId(rs.getInt("id_especialidad"));
                    curso.setEspecialidad(especialidad);
                    return curso;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE Cursos SET nombre_curso = ?, descripcion_curso = ?, id_especialidad = ? WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curso.getNombreCurso());
            pstmt.setString(2, curso.getDescripcionCurso());
            pstmt.setInt(3, curso.getEspecialidad().getIdEspecialidad());
            pstmt.setInt(4, curso.getIdCurso());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idCurso) {
        String sql = "DELETE FROM Cursos WHERE id_curso = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCurso);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
