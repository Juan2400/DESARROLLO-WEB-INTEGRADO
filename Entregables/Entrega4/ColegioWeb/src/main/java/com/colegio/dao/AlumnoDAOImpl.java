package com.colegio.dao;

import com.colegio.modelo.Alumno;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public void insertar(Alumno alumno) {
        String sql = "INSERT INTO Alumnos (dni, nombre, apellido, direccion, telefonoApoderado, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, alumno.getDni());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellido());
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getTelefonoApoderado());
            pstmt.setString(6, alumno.getEmail());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alumno.setIdAlumno(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Alumno> listarTodos() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT id_alumno, dni, nombre, apellido, direccion, telefonoApoderado, email FROM Alumnos";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getString("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setTelefonoApoderado(rs.getString("telefonoApoderado"));
                alumno.setEmail(rs.getString("email"));
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public Alumno obtenerPorId(int idAlumno) {
        String sql = "SELECT id_alumno, dni, nombre, apellido, direccion, telefonoApoderado, email FROM Alumnos WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAlumno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("id_alumno"));
                    alumno.setDni(rs.getString("dni"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setDireccion(rs.getString("direccion"));
                    alumno.setTelefonoApoderado(rs.getString("telefonoApoderado"));
                    alumno.setEmail(rs.getString("email"));
                    return alumno;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Alumno alumno) {
        String sql = "UPDATE Alumnos SET dni = ?, nombre = ?, apellido = ?, direccion = ?, telefonoApoderado = ?, email = ? WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getDni());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellido());
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getTelefonoApoderado());
            pstmt.setString(6, alumno.getEmail());
            pstmt.setInt(7, alumno.getIdAlumno());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
