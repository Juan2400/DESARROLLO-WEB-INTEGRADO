package com.colegio.dao;

import com.colegio.modelo.Responsable;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsableDAOImpl implements ResponsableDAO {

    @Override
    public void insertar(Responsable responsable){
        String sql = "INSERT INTO Responsables (dni, nombre, apellido, sexo, telefono, email, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, responsable.getDni());
            pstmt.setString(2, responsable.getNombre());
            pstmt.setString(3, responsable.getApellido());
            pstmt.setString(4, responsable.getSexo());
            pstmt.setString(5, responsable.getTelefono());
            pstmt.setString(6, responsable.getEmail());
            pstmt.setString(7, responsable.getDireccion());

            pstmt.executeUpdate();

            // Recuperar el ID generado automáticamente
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    responsable.setIdResponsable(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);  
        }
    }

    @Override
    public Responsable obtenerPorId(int idResponsable) {
        String sql = "SELECT * FROM Responsables WHERE id_responsable = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idResponsable);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Asignación directa de los valores del ResultSet al objeto Responsable
                    Responsable responsable = new Responsable();
                    responsable.setIdResponsable(rs.getInt("id_responsable"));
                    responsable.setDni(rs.getString("dni"));
                    responsable.setNombre(rs.getString("nombre"));
                    responsable.setApellido(rs.getString("apellido"));
                    responsable.setSexo(rs.getString("sexo"));
                    responsable.setTelefono(rs.getString("telefono"));
                    responsable.setEmail(rs.getString("email"));
                    responsable.setDireccion(rs.getString("direccion"));
                    return responsable;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Responsable> listarTodos() {
        List<Responsable> responsables = new ArrayList<>();
        String sql = "SELECT * FROM Responsables";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Asignación directa de los valores del ResultSet al objeto Responsable
                Responsable responsable = new Responsable();
                responsable.setIdResponsable(rs.getInt("id_responsable"));
                responsable.setDni(rs.getString("dni"));
                responsable.setNombre(rs.getString("nombre"));
                responsable.setApellido(rs.getString("apellido"));
                responsable.setSexo(rs.getString("sexo"));
                responsable.setTelefono(rs.getString("telefono"));
                responsable.setEmail(rs.getString("email"));
                responsable.setDireccion(rs.getString("direccion"));
                responsables.add(responsable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return responsables;
    }

    @Override
    public void actualizar(Responsable responsable) {
        String sql = "UPDATE Responsables SET dni = ?, nombre = ?, apellido = ?, sexo = ?, telefono = ?, email = ?, direccion = ? WHERE id_responsable = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, responsable.getDni());
            pstmt.setString(2, responsable.getNombre());
            pstmt.setString(3, responsable.getApellido());
            pstmt.setString(4, responsable.getSexo());
            pstmt.setString(5, responsable.getTelefono());
            pstmt.setString(6, responsable.getEmail());
            pstmt.setString(7, responsable.getDireccion());
            pstmt.setInt(8, responsable.getIdResponsable());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);  
        }
    }

    @Override
    public void eliminar(int idResponsable) {
        String sql = "DELETE FROM Responsables WHERE id_responsable = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idResponsable);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
