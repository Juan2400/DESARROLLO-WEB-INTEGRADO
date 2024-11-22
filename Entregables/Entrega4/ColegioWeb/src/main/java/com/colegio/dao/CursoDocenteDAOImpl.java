package com.colegio.dao;

import com.colegio.modelo.CursoDocente;
import com.colegio.modelo.Curso;
import com.colegio.modelo.Docente;
import com.colegio.modelo.Grado;
import com.colegio.util.ConexionBD;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDocenteDAOImpl implements CursoDocenteDAO, Serializable {

    private static final long serialVersionUID = 1L;  
    private CursoDAO cursoDAO = new CursoDAOImpl();
    private DocenteDAO docenteDAO = new DocenteDAOImpl();
    private GradoDAO gradoDAO = new GradoDAOImpl();

    @Override
    public void insertar(CursoDocente cursoDocente){
        String sql = "INSERT INTO Curso_Docente (id_curso, id_docente, id_grado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, cursoDocente.getCurso().getIdCurso());
            pstmt.setInt(2, cursoDocente.getDocente().getIdDocente());
            pstmt.setInt(3, cursoDocente.getGrado().getIdGrado());
            pstmt.executeUpdate();

            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cursoDocente.setIdCursoDocente(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CursoDocente> listarTodos() {
        List<CursoDocente> cursoDocenteList = new ArrayList<>();
        String sql = "SELECT id, id_curso, id_docente, id_grado FROM Curso_Docente";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CursoDocente cursoDocente = new CursoDocente();
                cursoDocente.setIdCursoDocente(rs.getInt("id"));

                Curso curso = cursoDAO.obtenerPorId(rs.getInt("id_curso"));
                Docente docente = docenteDAO.obtenerPorId(rs.getInt("id_docente"));
                Grado grado = gradoDAO.obtenerPorId(rs.getInt("id_grado"));

                cursoDocente.setCurso(curso);
                cursoDocente.setDocente(docente);
                cursoDocente.setGrado(grado);

                cursoDocenteList.add(cursoDocente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursoDocenteList;
    }

    @Override
    public CursoDocente obtenerPorId(int id) {
        String sql = "SELECT id, id_curso, id_docente, id_grado FROM Curso_Docente WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CursoDocente cursoDocente = new CursoDocente();
                    cursoDocente.setIdCursoDocente(rs.getInt("id"));

                    Curso curso = cursoDAO.obtenerPorId(rs.getInt("id_curso"));
                    Docente docente = docenteDAO.obtenerPorId(rs.getInt("id_docente"));
                    Grado grado = gradoDAO.obtenerPorId(rs.getInt("id_grado"));

                    cursoDocente.setCurso(curso);
                    cursoDocente.setDocente(docente);
                    cursoDocente.setGrado(grado);

                    return cursoDocente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(CursoDocente cursoDocente) {
        String sql = "UPDATE Curso_Docente SET id_curso = ?, id_docente = ?, id_grado = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoDocente.getCurso().getIdCurso());
            pstmt.setInt(2, cursoDocente.getDocente().getIdDocente());
            pstmt.setInt(3, cursoDocente.getGrado().getIdGrado());
            pstmt.setInt(4, cursoDocente.getIdCursoDocente());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Curso_Docente WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
