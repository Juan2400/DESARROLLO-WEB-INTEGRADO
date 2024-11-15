package com.colegio.modelo;

import com.colegio.modelo.TipoMatriculaDAOImpl;
import com.colegio.modelo.MatriculaDAO;
import com.colegio.modelo.GradoDAOImpl;
import com.colegio.modelo.AlumnoDAOImpl;
import com.colegio.modelo.Alumno;
import com.colegio.modelo.Grado;
import com.colegio.modelo.Matricula;
import com.colegio.modelo.TipoMatricula;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOImpl implements MatriculaDAO {

    @Override
    public void insertar(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO Matriculas (id_alumno, id_grado, id_tipo_matricula, anio, fecha_matricula, observaciones) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, matricula.getAlumno().getId_alumno());
            pstmt.setInt(2, matricula.getGrado().getId_grado());
            pstmt.setInt(3, matricula.getTipo_matricula().getId_tipo_matricula());
            pstmt.setInt(4, matricula.getAnio());
            pstmt.setDate(5, new java.sql.Date(matricula.getFecha_matricula().getTime()));
            pstmt.setString(6, matricula.getObservaciones());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    matricula.setId_matricula(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new SQLException("El alumno ya está matriculado en el año actual.", e);
            }
            throw e; // Relanzar cualquier otra excepción
        }
    }

    @Override
    public Matricula obtenerPorId(int id_matricula) throws SQLException {
        String sql = "SELECT * FROM Matriculas WHERE id_matricula = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {

                    Alumno alumno = new AlumnoDAOImpl().obtenerPorId(rs.getInt("id_alumno"));
                    Grado grado = new GradoDAOImpl().obtenerPorId(rs.getInt("id_grado"));
                    TipoMatricula tipoMatricula = new TipoMatriculaDAOImpl().obtenerPorId(rs.getInt("id_tipo_matricula"));

                    Matricula matricula = new Matricula();
                    matricula.setId_matricula(rs.getInt("id_matricula"));
                    matricula.setAlumno(alumno);
                    matricula.setGrado(grado);
                    matricula.setTipo_matricula(tipoMatricula);
                    matricula.setAnio(rs.getInt("anio"));
                    matricula.setFecha_matricula(rs.getDate("fecha_matricula"));
                    matricula.setObservaciones(rs.getString("observaciones"));
                    return matricula;
                }
            }
        }
        return null;
    }

    @Override
    public List<Matricula> listarTodos() throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM Matriculas";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alumno alumno = new AlumnoDAOImpl().obtenerPorId(rs.getInt("id_alumno"));
                Grado grado = new GradoDAOImpl().obtenerPorId(rs.getInt("id_grado"));
                TipoMatricula tipoMatricula = new TipoMatriculaDAOImpl().obtenerPorId(rs.getInt("id_tipo_matricula"));

                Matricula matricula = new Matricula();
                matricula.setId_matricula(rs.getInt("id_matricula"));
                matricula.setAlumno(alumno);
                matricula.setGrado(grado);
                matricula.setTipo_matricula(tipoMatricula);
                matricula.setAnio(rs.getInt("anio"));
                matricula.setFecha_matricula(rs.getDate("fecha_matricula"));
                matricula.setObservaciones(rs.getString("observaciones"));

                matriculas.add(matricula);
            }
        }
        return matriculas;
    }

    @Override
    public void actualizar(Matricula matricula) throws SQLException {
        String sql = "UPDATE Matriculas SET id_alumno = ?, id_grado = ?, id_tipo_matricula = ?, anio = ?, fecha_matricula = ?, observaciones = ? WHERE id_matricula = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, matricula.getAlumno().getId_alumno());
            pstmt.setInt(2, matricula.getGrado().getId_grado());
            pstmt.setInt(3, matricula.getTipo_matricula().getId_tipo_matricula());
            pstmt.setInt(4, matricula.getAnio());
            pstmt.setDate(5, new java.sql.Date(matricula.getFecha_matricula().getTime()));
            pstmt.setString(6, matricula.getObservaciones());
            pstmt.setInt(7, matricula.getId_matricula());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new SQLException("El alumno ya está matriculado en el año actual.", e);
            }
            throw e; // Relanzar cualquier otra excepción
        }
    }

    @Override
    public void eliminar(int id_matricula) throws SQLException {
        String sql = "DELETE FROM Matriculas WHERE id_matricula = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_matricula);
            pstmt.executeUpdate();
        }
    }
}
