package com.colegio.dao;

import com.colegio.model.Docente;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO {

    public void registrarDocente(Docente docente) throws SQLException {
        String sql = "INSERT INTO Docentes (nombre, apellido, especialidad, telefono, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getApellido());
            pstmt.setString(3, docente.getEspecialidad());
            pstmt.setString(4, docente.getTelefono());
            pstmt.setString(5, docente.getEmail());
            pstmt.executeUpdate();
        }
    }

    public List<Docente> listarDocentes() throws SQLException {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT * FROM Docentes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docente.setTelefono(rs.getString("telefono"));
                docente.setEmail(rs.getString("email"));

                docentes.add(docente);
            }
        }
        return docentes;
    }
}
