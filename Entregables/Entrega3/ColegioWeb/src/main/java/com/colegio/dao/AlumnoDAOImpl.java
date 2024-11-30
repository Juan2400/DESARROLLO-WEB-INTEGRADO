package com.colegio.dao;

import com.colegio.modelo.*;
import com.colegio.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public void insertar(Alumno alumno) {
        String sql = "INSERT INTO Alumnos (codigo_estudiante, dni, nombre, apellido, direccion, sexo, telefono_referencia, id_padre, id_madre, id_apoderado, id_estado_estudiante, id_grado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, alumno.getCodigoEstudiante());
            pstmt.setString(2, alumno.getDni());
            pstmt.setString(3, alumno.getNombre());
            pstmt.setString(4, alumno.getApellido());
            pstmt.setString(5, alumno.getDireccion());
            pstmt.setString(6, alumno.getSexo());
            pstmt.setString(7, alumno.getTelefonoReferencia());
            pstmt.setInt(8, alumno.getPadre().getIdResponsable());
            pstmt.setInt(9, alumno.getMadre().getIdResponsable());
            pstmt.setInt(10, alumno.getApoderado().getIdResponsable());
            pstmt.setInt(11, alumno.getEstadoEstudiante().getIdEstadoEstudiante());
            pstmt.setInt(12, alumno.getGrado().getIdGrado());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alumno.setIdAlumno(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Alumno obtenerPorId(int idAlumno) {
        String sql = "SELECT * FROM Alumnos WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAlumno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("id_alumno"));
                    alumno.setCodigoEstudiante(rs.getString("codigo_estudiante"));
                    alumno.setDni(rs.getString("dni"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setDireccion(rs.getString("direccion"));
                    alumno.setSexo(rs.getString("sexo"));
                    alumno.setTelefonoReferencia(rs.getString("telefono_referencia"));

                    // Relación con otras clases
                    ResponsableDAO responsableDAO = new ResponsableDAOImpl();
                    alumno.setPadre(responsableDAO.obtenerPorId(rs.getInt("id_padre")));
                    alumno.setMadre(responsableDAO.obtenerPorId(rs.getInt("id_madre")));
                    alumno.setApoderado(responsableDAO.obtenerPorId(rs.getInt("id_apoderado")));

                    EstadoEstudianteDAO estadoEstudianteDAO = new EstadoEstudianteDAOImpl();
                    alumno.setEstadoEstudiante(estadoEstudianteDAO.obtenerPorId(rs.getInt("id_estado_estudiante")));

                    GradoDAO gradoDAO = new GradoDAOImpl();
                    alumno.setGrado(gradoDAO.obtenerPorId(rs.getInt("id_grado")));

                    return alumno;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alumno> listarTodos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumnos";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setCodigoEstudiante(rs.getString("codigo_estudiante"));
                alumno.setDni(rs.getString("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setSexo(rs.getString("sexo"));
                alumno.setTelefonoReferencia(rs.getString("telefono_referencia"));

                // Relación con otras clases
                ResponsableDAO responsableDAO = new ResponsableDAOImpl();
                alumno.setPadre(responsableDAO.obtenerPorId(rs.getInt("id_padre")));
                alumno.setMadre(responsableDAO.obtenerPorId(rs.getInt("id_madre")));
                alumno.setApoderado(responsableDAO.obtenerPorId(rs.getInt("id_apoderado")));

                EstadoEstudianteDAO estadoEstudianteDAO = new EstadoEstudianteDAOImpl();
                alumno.setEstadoEstudiante(estadoEstudianteDAO.obtenerPorId(rs.getInt("id_estado_estudiante")));

                GradoDAO gradoDAO = new GradoDAOImpl();
                alumno.setGrado(gradoDAO.obtenerPorId(rs.getInt("id_grado")));

                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void actualizar(Alumno alumno) {
        String sql = "UPDATE Alumnos SET codigo_estudiante = ?, dni = ?, nombre = ?, apellido = ?, direccion = ?, sexo = ?, telefono_referencia = ?, id_padre = ?, id_madre = ?, id_apoderado = ?, id_estado_estudiante = ?, id_grado = ? WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, alumno.getCodigoEstudiante());
            pstmt.setString(2, alumno.getDni());
            pstmt.setString(3, alumno.getNombre());
            pstmt.setString(4, alumno.getApellido());
            pstmt.setString(5, alumno.getDireccion());
            pstmt.setString(6, alumno.getSexo());
            pstmt.setString(7, alumno.getTelefonoReferencia());
            pstmt.setInt(8, alumno.getPadre().getIdResponsable());
            pstmt.setInt(9, alumno.getMadre().getIdResponsable());
            pstmt.setInt(10, alumno.getApoderado().getIdResponsable());
            pstmt.setInt(11, alumno.getEstadoEstudiante().getIdEstadoEstudiante());
            pstmt.setInt(12, alumno.getGrado().getIdGrado());
            pstmt.setInt(13, alumno.getIdAlumno());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);  
        }
    }

    @Override
    public void eliminar(int idAlumno) {
        String sql = "DELETE FROM Alumnos WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAlumno);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}