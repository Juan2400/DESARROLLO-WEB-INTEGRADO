package com.utp.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.utp.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;


public class CategoriaProductoDAOImpl 
        implements CategoriaProductoDAO{

    @Override
    public void insertar(CategoriaProducto categoria) throws SQLException {
        String sql="INSERT INTO categoria_producto (nombre) VALUES (?) ";
      try (Connection conn= ConexionBD.getConnection();
              PreparedStatement pstmt = 
                      conn.prepareStatement
        (sql,Statement.RETURN_GENERATED_KEYS)
              ){
          pstmt.setString(1,categoria.getNombre());
          pstmt.execute();
      }
    
    }

    @Override
    public CategoriaProducto obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM categoria_producto WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CategoriaProducto(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public List<CategoriaProducto> listarTodos() throws SQLException {
        List<CategoriaProducto> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria_producto";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(new CategoriaProducto(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return categorias;
    }

    @Override
    public void actualizar(CategoriaProducto categoria) throws SQLException {
        String sql = "UPDATE categoria_producto SET nombre = ? WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM categoria_producto WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }



    
}
