package com.colegio.dao;

import com.colegio.modelo.Especialidad;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadDAOImpl implements EspecialidadDAO {

    @Override
    public void insertar(Especialidad especialidad) {
        String sql = "INSERT INTO Especialidades (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, especialidad.getNombre());
            pstmt.setString(2, especialidad.getDescripcion());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    especialidad.setIdEspecialidad(generatedKeys.getInt(1)); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw new RuntimeException("Error al insertar especialidad", e);  
        }
    }

    @Override
    public Especialidad obtenerPorId(int idEspecialidad) {
        String sql = "SELECT id_especialidad, nombre, descripcion FROM Especialidades WHERE id_especialidad = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEspecialidad);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Especialidad especialidad = new Especialidad();
                    especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                    especialidad.setNombre(rs.getString("nombre"));
                    especialidad.setDescripcion(rs.getString("descripcion"));
                    return especialidad;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Para depuración
            throw new RuntimeException("Error al obtener especialidad por ID", e);  // Relanzar la excepción
        }
        return null;  // Retorna null si no se encuentra la especialidad
    }

    @Override
    public List<Especialidad> listarTodos() {
        List<Especialidad> especialidades = new ArrayList<>();
        String sql = "SELECT id_especialidad, nombre, descripcion FROM Especialidades";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Especialidad especialidad = new Especialidad();
                especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                especialidades.add(especialidad);
            }
        } catch (SQLException e) {
            // Registrar el error
            e.printStackTrace();
        }
        return especialidades;  // Devuelve la lista de especialidades
    }

    @Override
    public void actualizar(Especialidad especialidad) {
        String sql = "UPDATE Especialidades SET nombre = ?, descripcion = ? WHERE id_especialidad = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, especialidad.getNombre());
            pstmt.setString(2, especialidad.getDescripcion());
            pstmt.setInt(3, especialidad.getIdEspecialidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Registrar el error
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idEspecialidad) {
        String sql = "DELETE FROM Especialidades WHERE id_especialidad = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEspecialidad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
