package com.colegio.modelo;

import com.colegio.modelo.CursoDocenteDAO;
import com.colegio.modelo.CursoDAOImpl;
import com.colegio.modelo.Curso;
import com.colegio.modelo.CursoDocente;
import com.colegio.modelo.Docente;
import com.colegio.modelo.Grado;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDocenteDAOImpl implements CursoDocenteDAO {

    @Override
    public void insertar(CursoDocente cursoDocente) throws SQLException {
        String sql = "INSERT INTO Curso_Docente (id_curso, id_docente, id_grado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, cursoDocente.getCurso().getId_curso());
            pstmt.setInt(2, cursoDocente.getDocente().getId_docente());
            pstmt.setInt(3, cursoDocente.getGrado().getId_grado());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cursoDocente.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new SQLException("La asignación de este curso al docente ya existe.", e);
            }
            throw e; // Relanzar cualquier otra excepción
        }
    }

    @Override
    public CursoDocente obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Curso_Docente WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    Curso curso = new CursoDAOImpl().obtenerPorId(rs.getInt("id_curso"));
                    Docente docente = new DocenteDAOImpl().obtenerPorId(rs.getInt("id_docente"));
                    Grado grado = new GradoDAOImpl().obtenerPorId(rs.getInt("id_grado"));

                    CursoDocente cursoDocente = new CursoDocente();
                    cursoDocente.setId(rs.getInt("id"));
                    cursoDocente.setCurso(curso);
                    cursoDocente.setDocente(docente);
                    cursoDocente.setGrado(grado);

                    return cursoDocente;
                }
            }
        }
        return null;
    }

    @Override
    public List<CursoDocente> listarTodos() throws SQLException {
        List<CursoDocente> cursosDocentes = new ArrayList<>();
        String sql = "SELECT * FROM Curso_Docente";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Curso curso = new CursoDAOImpl().obtenerPorId(rs.getInt("id_curso"));
                Docente docente = new DocenteDAOImpl().obtenerPorId(rs.getInt("id_docente"));
                Grado grado = new GradoDAOImpl().obtenerPorId(rs.getInt("id_grado"));

                CursoDocente cursoDocente = new CursoDocente();
                cursoDocente.setId(rs.getInt("id"));
                cursoDocente.setCurso(curso);
                cursoDocente.setDocente(docente);
                cursoDocente.setGrado(grado);

                cursosDocentes.add(cursoDocente);
            }
        }
        return cursosDocentes;
    }

    @Override
    public void actualizar(CursoDocente cursoDocente) throws SQLException {
        String sql = "UPDATE Curso_Docente SET id_curso = ?, id_docente = ?, id_grado = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cursoDocente.getCurso().getId_curso());
            pstmt.setInt(2, cursoDocente.getDocente().getId_docente());
            pstmt.setInt(3, cursoDocente.getGrado().getId_grado());
            pstmt.setInt(4, cursoDocente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new SQLException("La asignación de este curso al docente ya existe.", e);
            }
            throw e; // Relanzar cualquier otra excepción
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Curso_Docente WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
