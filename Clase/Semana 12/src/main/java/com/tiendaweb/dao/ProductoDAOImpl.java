package com.tiendaweb.dao;

import java.io.Serializable;
import com.tiendaweb.model.Producto;
import com.tiendaweb.model.Categoria;
import com.tiendaweb.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class ProductoDAOImpl implements ProductoDAO, Serializable{
     private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    @Override
    public void insertar(Producto producto) {
        String sql = "INSERT INTO producto (nombre, precio, categoria_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getCategoria().getId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, precio, categoria_id FROM producto";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                int categoriaId = rs.getInt("categoria_id");
                Categoria categoria = categoriaDAO.obtenerPorId(categoriaId);
                producto.setCategoria(categoria);

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto obtenerPorId(int id) {
        String sql = "SELECT id, nombre, precio, categoria_id FROM producto WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    int categoriaId = rs.getInt("categoria_id");
                    Categoria categoria = categoriaDAO.obtenerPorId(categoriaId);
                    producto.setCategoria(categoria);

                    return producto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getCategoria().getId());
            pstmt.setInt(4, producto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listarPorCategoria(int categoriaId) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, precio  FROM producto WHERE categoria_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoriaId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getDouble("precio"));
                    Categoria categoria = categoriaDAO.obtenerPorId(categoriaId);
                    producto.setCategoria(categoria);

                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

}
