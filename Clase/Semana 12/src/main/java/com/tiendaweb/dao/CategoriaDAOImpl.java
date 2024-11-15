package com.tiendaweb.dao;

import com.tiendaweb.model.Categoria;
import com.tiendaweb.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public void insertar(Categoria categoria) {
        String sql = "INSERT INTO categoria_producto (nombre) VALUES (?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    categoria.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categoria> listarTodas() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM categoria_producto";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public Categoria obtenerPorId(int id) {
        String sql = "SELECT id, nombre FROM categoria_producto WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    return categoria;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categoria_producto SET nombre = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM categoria_producto WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
