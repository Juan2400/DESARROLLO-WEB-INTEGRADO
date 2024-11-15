package com.colegio.modelo;

import com.colegio.modelo.AlumnoDAO;
import com.colegio.modelo.Alumno;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public void insertar(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO Alumnos (dni, nombre, apellido, direccion, telefonoApoderado, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, alumno.getDni());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellido());
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getTelefono());
            pstmt.setString(6, alumno.getEmail());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    alumno.setId_alumno(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Alumno obtenerPorId(int id_alumno) throws SQLException {
        String sql = "SELECT * FROM Alumnos WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_alumno);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Alumno(
                        rs.getInt("id_alumno"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefonoApoderado"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Alumno> listarTodos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumnos";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alumnos.add(new Alumno(
                    rs.getInt("id_alumno"),
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("direccion"),
                    rs.getString("telefonoApoderado"),
                    rs.getString("email")
                ));
            }
        }
        return alumnos;
    }

    @Override
    public void actualizar(Alumno alumno) throws SQLException {
        String sql = "UPDATE Alumnos SET dni = ?, nombre = ?, apellido = ?, direccion = ?, telefonoApoderado = ?, email = ? WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getDni());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellido());
            pstmt.setString(4, alumno.getDireccion());
            pstmt.setString(5, alumno.getTelefono());
            pstmt.setString(6, alumno.getEmail());
            pstmt.setInt(7, alumno.getId_alumno());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id_alumno) throws SQLException {
        String sql = "DELETE FROM Alumnos WHERE id_alumno = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_alumno);
            pstmt.executeUpdate();
        }
    }
}
