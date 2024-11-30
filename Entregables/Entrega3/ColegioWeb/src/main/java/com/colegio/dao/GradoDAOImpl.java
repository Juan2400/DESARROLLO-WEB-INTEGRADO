package com.colegio.dao;

import com.colegio.modelo.Grado;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradoDAOImpl implements GradoDAO {

    @Override
    public Grado obtenerPorId(int idGrado) {
        String sql = "SELECT * FROM Grados WHERE id_grado = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idGrado);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Grado grado = new Grado();
                    grado.setIdGrado(rs.getInt("id_grado"));
                    grado.setNombreGrado(rs.getString("nombre_grado"));
                    grado.setDescripcionGrado(rs.getString("descripcion_grado"));
                    return grado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Grado> listarTodos() {
        List<Grado> grados = new ArrayList<>();
        String sql = "SELECT * FROM Grados";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_grado"));
                grado.setNombreGrado(rs.getString("nombre_grado"));
                grado.setDescripcionGrado(rs.getString("descripcion_grado"));
                grados.add(grado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grados;
    }

    /**
     * Obtiene todos los grados superiores a un grado específico.
     *
     * @param gradoActual ID del grado actual.
     * @return Lista de grados superiores al grado actual.
     */
    public List<Grado> obtenerGradosMayoresA(int gradoActual) {
        List<Grado> grados = new ArrayList<>();
        String sql = "SELECT * FROM Grados WHERE id_grado > ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, gradoActual);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Grado grado = new Grado();
                    grado.setIdGrado(rs.getInt("id_grado"));
                    grado.setNombreGrado(rs.getString("nombre_grado"));
                    grado.setDescripcionGrado(rs.getString("descripcion_grado"));
                    grados.add(grado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grados;
    }

    /**
     * Obtiene el grado inicial (primer grado) para alumnos ingresantes.
     *
     * @return El grado inicial (primer grado).
     */
    public Grado obtenerPrimerGrado() {
        String sql = "SELECT * FROM Grados WHERE id_grado = 1";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_grado"));
                grado.setNombreGrado(rs.getString("nombre_grado"));
                grado.setDescripcionGrado(rs.getString("descripcion_grado"));
                return grado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
