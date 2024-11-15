package com.colegio.modelo;

import com.colegio.modelo.DocenteDAO;
import com.colegio.modelo.Docente;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAOImpl implements DocenteDAO {

    @Override
    public void insertar(Docente docente) throws SQLException {
        String sql = "INSERT INTO Docentes (dni, nombre, apellido, especialidad, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, docente.getDni());
            pstmt.setString(2, docente.getNombre());
            pstmt.setString(3, docente.getApellido());
            pstmt.setString(4, docente.getEspecialidad());
            pstmt.setString(5, docente.getTelefono());
            pstmt.setString(6, docente.getEmail());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    docente.setId_docente(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Docente obtenerPorId(int id_docente) throws SQLException {
        String sql = "SELECT * FROM Docentes WHERE id_docente = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_docente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Docente(
                        rs.getInt("id_docente"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Docente> listarTodos() throws SQLException {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT * FROM Docentes";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                docentes.add(new Docente(
                    rs.getInt("id_docente"),
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email")
                ));
            }
        }
        return docentes;
    }

    @Override
    public void actualizar(Docente docente) throws SQLException {
        String sql = "UPDATE Docentes SET dni = ?, nombre = ?, apellido = ?, especialidad = ?, telefono = ?, email = ? WHERE id_docente = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, docente.getDni());
            pstmt.setString(2, docente.getNombre());
            pstmt.setString(3, docente.getApellido());
            pstmt.setString(4, docente.getEspecialidad());
            pstmt.setString(5, docente.getTelefono());
            pstmt.setString(6, docente.getEmail());
            pstmt.setInt(7, docente.getId_docente());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id_docente) throws SQLException {
        String sql = "DELETE FROM Docentes WHERE id_docente = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_docente);
            pstmt.executeUpdate();
        }
    }
}
