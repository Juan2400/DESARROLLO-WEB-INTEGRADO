package com.colegio.dao;

import com.colegio.modelo.Docente;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAOImpl implements DocenteDAO {

    @Override
    public void insertar(Docente docente) {
        String sql = "INSERT INTO docentes (dni, nombre, apellido, especialidad, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
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
                    docente.setIdDocente(generatedKeys.getInt(1));  // Establecer el ID del Docente
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Docente> listarTodos() {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT id_docente, dni, nombre, apellido, especialidad, telefono, email FROM docentes";
        
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setDni(rs.getString("dni"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docente.setTelefono(rs.getString("telefono"));
                docente.setEmail(rs.getString("email"));
                docentes.add(docente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    @Override
    public Docente obtenerPorId(int idDocente) {
        String sql = "SELECT id_docente, dni, nombre, apellido, especialidad, telefono, email FROM docentes WHERE id_docente = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idDocente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Docente docente = new Docente();
                    docente.setIdDocente(rs.getInt("id_docente"));
                    docente.setDni(rs.getString("dni"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setApellido(rs.getString("apellido"));
                    docente.setEspecialidad(rs.getString("especialidad"));
                    docente.setTelefono(rs.getString("telefono"));
                    docente.setEmail(rs.getString("email"));
                    return docente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Docente docente) {
        String sql = "UPDATE docentes SET dni = ?, nombre = ?, apellido = ?, especialidad = ?, telefono = ?, email = ? WHERE id_docente = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, docente.getDni());
            pstmt.setString(2, docente.getNombre());
            pstmt.setString(3, docente.getApellido());
            pstmt.setString(4, docente.getEspecialidad());
            pstmt.setString(5, docente.getTelefono());
            pstmt.setString(6, docente.getEmail());
            pstmt.setInt(7, docente.getIdDocente());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idDocente) {
        String sql = "DELETE FROM docentes WHERE id_docente = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idDocente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
