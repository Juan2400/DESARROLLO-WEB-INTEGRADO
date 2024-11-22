package com.colegio.dao;

import com.colegio.modelo.Alumno;
import com.colegio.modelo.Grado;
import com.colegio.modelo.Matricula;
import com.colegio.modelo.TipoMatricula;
import com.colegio.util.ConexionBD;
import java.io.Serializable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOImpl implements MatriculaDAO, Serializable {

    private static final long serialVersionUID = 1L;
    private AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
    private GradoDAO gradoDAO = new GradoDAOImpl();
    private TipoMatriculaDAO tipoMatriculaDAO = new TipoMatriculaDAOImpl();

    @Override
    public void insertar(Matricula matricula) {
        String sql = "INSERT INTO Matriculas (id_alumno, id_grado, id_tipo_matricula, anio, fecha_matricula, observaciones) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, matricula.getAlumno().getIdAlumno());
            pstmt.setInt(2, matricula.getGrado().getIdGrado());
            pstmt.setInt(3, matricula.getTipoMatricula().getIdTipoMatricula());
            pstmt.setInt(4, matricula.getAnio());
            pstmt.setDate(5, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            pstmt.setString(6, matricula.getObservaciones());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    matricula.setIdMatricula(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Matricula> listarTodos() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT id_matricula, id_alumno, id_grado, id_tipo_matricula, anio, fecha_matricula, observaciones FROM Matriculas";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("id_matricula"));
                Alumno alumno = alumnoDAO.obtenerPorId(rs.getInt("id_alumno"));
                matricula.setAlumno(alumno);
                Grado grado = gradoDAO.obtenerPorId(rs.getInt("id_grado"));
                matricula.setGrado(grado);
                TipoMatricula tipoMatricula = tipoMatriculaDAO.obtenerPorId(rs.getInt("id_tipo_matricula"));
                matricula.setTipoMatricula(tipoMatricula);
                matricula.setAnio(rs.getInt("anio"));
                matricula.setFechaMatricula(rs.getDate("fecha_matricula"));
                matricula.setObservaciones(rs.getString("observaciones"));

                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matriculas;
    }

    @Override
    public Matricula obtenerPorId(int idMatricula) {
        String sql = "SELECT id_matricula, id_alumno, id_grado, id_tipo_matricula, anio, fecha_matricula, observaciones FROM Matriculas WHERE id_matricula = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMatricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setIdMatricula(rs.getInt("id_matricula"));
                    Alumno alumno = alumnoDAO.obtenerPorId(rs.getInt("id_alumno"));
                    matricula.setAlumno(alumno);
                    Grado grado = gradoDAO.obtenerPorId(rs.getInt("id_grado"));
                    matricula.setGrado(grado);
                    TipoMatricula tipoMatricula = tipoMatriculaDAO.obtenerPorId(rs.getInt("id_tipo_matricula"));
                    matricula.setTipoMatricula(tipoMatricula);
                    matricula.setAnio(rs.getInt("anio"));
                    matricula.setFechaMatricula(rs.getDate("fecha_matricula"));
                    matricula.setObservaciones(rs.getString("observaciones"));
                    return matricula;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Matricula matricula) {
        String sql = "UPDATE Matriculas SET id_alumno = ?, id_grado = ?, id_tipo_matricula = ?, anio = ?, fecha_matricula = ?, observaciones = ? WHERE id_matricula = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula.getAlumno().getIdAlumno());
            pstmt.setInt(2, matricula.getGrado().getIdGrado());
            pstmt.setInt(3, matricula.getTipoMatricula().getIdTipoMatricula());
            pstmt.setInt(4, matricula.getAnio());
            pstmt.setDate(5, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            pstmt.setString(6, matricula.getObservaciones());
            pstmt.setInt(7, matricula.getIdMatricula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idMatricula) {
        String sql = "DELETE FROM Matriculas WHERE id_matricula = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMatricula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
