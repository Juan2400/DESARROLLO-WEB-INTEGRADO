package com.colegio.dao;

import com.colegio.modelo.Alumno;
import com.colegio.modelo.Grado;
import com.colegio.modelo.Matricula;
import com.colegio.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOImpl implements MatriculaDAO {

    private AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
    private GradoDAO gradoDAO = new GradoDAOImpl();

    // Método para insertar matrícula
    @Override
    public void insertar(Matricula matricula) {
        String sql = "INSERT INTO Matriculas (id_alumno, id_grado, anio, fecha_registro, observaciones) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, matricula.getAlumno().getIdAlumno());
            pstmt.setInt(2, matricula.getGrado().getIdGrado());
            pstmt.setInt(3, matricula.getAnio());
            pstmt.setDate(4, new java.sql.Date(matricula.getFechaRegistro().getTime()));
            pstmt.setString(5, matricula.getObservaciones());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    matricula.setIdMatricula(generatedKeys.getInt(1)); // Asignar el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);  
        }
    }

    // Método para listar todas las matrículas
    @Override
    public List<Matricula> listarTodos() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT id_matricula, id_alumno, id_grado, anio, fecha_registro, observaciones FROM Matriculas";
        
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(rs.getInt("id_matricula"));
                
                // Obtenemos las relaciones de la tabla (Alumno, Grado)
                Alumno alumno = alumnoDAO.obtenerPorId(rs.getInt("id_alumno"));
                matricula.setAlumno(alumno);
                
                Grado grado = gradoDAO.obtenerPorId(rs.getInt("id_grado"));
                matricula.setGrado(grado);
                
                matricula.setAnio(rs.getInt("anio"));
                matricula.setFechaRegistro(rs.getDate("fecha_registro"));
                matricula.setObservaciones(rs.getString("observaciones"));
                
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matriculas;
    }

    // Método para obtener matrícula por ID
    @Override
    public Matricula obtenerPorId(int idMatricula) {
        String sql = "SELECT id_matricula, id_alumno, id_grado, anio, fecha_registro, observaciones FROM Matriculas WHERE id_matricula = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMatricula);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setIdMatricula(rs.getInt("id_matricula"));
                    Alumno alumno = alumnoDAO.obtenerPorId(rs.getInt("id_alumno"));
                    matricula.setAlumno(alumno);
                    
                    Grado grado = gradoDAO.obtenerPorId(rs.getInt("id_grado"));
                    matricula.setGrado(grado);
                    
                    matricula.setAnio(rs.getInt("anio"));
                    matricula.setFechaRegistro(rs.getDate("fecha_registro"));
                    matricula.setObservaciones(rs.getString("observaciones"));
                    
                    return matricula;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para actualizar matrícula
    @Override
    public void actualizar(Matricula matricula){
        String sql = "UPDATE Matriculas SET id_alumno = ?, id_grado = ?, anio = ?, fecha_registro = ?, observaciones = ? WHERE id_matricula = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, matricula.getAlumno().getIdAlumno());
            pstmt.setInt(2, matricula.getGrado().getIdGrado());
            pstmt.setInt(3, matricula.getAnio());
            pstmt.setDate(4, new java.sql.Date(matricula.getFechaRegistro().getTime()));
            pstmt.setString(5, matricula.getObservaciones());
            pstmt.setInt(6, matricula.getIdMatricula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);  
        }
    }

    // Método para eliminar matrícula
    @Override
    public void eliminar(int idMatricula) {
        String sql = "DELETE FROM Matriculas WHERE id_matricula = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMatricula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
