package com.colegio.dao;

import com.colegio.modelo.EstadoEstudiante;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoEstudianteDAOImpl implements EstadoEstudianteDAO {

    @Override
    public void insertar(EstadoEstudiante estadoEstudiante){
        String sql = "INSERT INTO Estado_Estudiante (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, estadoEstudiante.getNombre());
            pstmt.setString(2, estadoEstudiante.getDescripcion());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    estadoEstudiante.setIdEstadoEstudiante(generatedKeys.getInt(1)); // Establecer el ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EstadoEstudiante> listarTodos() {
        List<EstadoEstudiante> estadoEstudiantes = new ArrayList<>();
        String sql = "SELECT id_estado_estudiante, nombre, descripcion FROM Estado_Estudiante";
        
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                EstadoEstudiante estadoEstudiante = new EstadoEstudiante();
                estadoEstudiante.setIdEstadoEstudiante(rs.getInt("id_estado_estudiante"));
                estadoEstudiante.setNombre(rs.getString("nombre"));
                estadoEstudiante.setDescripcion(rs.getString("descripcion"));
                estadoEstudiantes.add(estadoEstudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estadoEstudiantes;
    }

    @Override
    public EstadoEstudiante obtenerPorId(int idEstadoEstudiante) {
        String sql = "SELECT id_estado_estudiante, nombre, descripcion FROM Estado_Estudiante WHERE id_estado_estudiante = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEstadoEstudiante);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    EstadoEstudiante estadoEstudiante = new EstadoEstudiante();
                    estadoEstudiante.setIdEstadoEstudiante(rs.getInt("id_estado_estudiante"));
                    estadoEstudiante.setNombre(rs.getString("nombre"));
                    estadoEstudiante.setDescripcion(rs.getString("descripcion"));
                    return estadoEstudiante;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(EstadoEstudiante estadoEstudiante){
        String sql = "UPDATE Estado_Estudiante SET nombre = ?, descripcion = ? WHERE id_estado_estudiante = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estadoEstudiante.getNombre());
            pstmt.setString(2, estadoEstudiante.getDescripcion());
            pstmt.setInt(3, estadoEstudiante.getIdEstadoEstudiante());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idEstadoEstudiante) {
        String sql = "DELETE FROM Estado_Estudiante WHERE id_estado_estudiante = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEstadoEstudiante);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
