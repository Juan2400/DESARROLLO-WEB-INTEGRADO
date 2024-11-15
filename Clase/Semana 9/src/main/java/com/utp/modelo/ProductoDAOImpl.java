/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.modelo;

import com.utp.util.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author C25170
 */
public class ProductoDAOImpl implements ProductoDAO{

    @Override
    public void insertar(Producto producto) throws SQLException {
        String sql="INSERT INTO producto "
                + "(nombre,precio,categoria_id ) "
                + "VALUES (?,?,?) ";
      try (Connection conn= ConexionBD.getConnection();
              PreparedStatement pstmt = 
                      conn.prepareStatement
        (sql,Statement.RETURN_GENERATED_KEYS)
              ){
          pstmt.setString(1,producto.getNombre());
          pstmt.setDouble(2,producto.getPrecio());
          pstmt.setInt(3,
                  producto.getCategoría().getId());
          pstmt.execute();
      }
    }

    @Override
    public Producto obtenerPorId(int id) throws SQLException {
                String sql = "SELECT * FROM producto WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
			CategoriaProducto categoria = new CategoriaProductoDAOImpl().obtenerPorId(rs.getInt("categoria_id"));
                    Producto p = new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
                    p.setCategoría(categoria);
                    return p ;
                }
            }
        }
        return null;

    }

    @Override
    public List<Producto> listarTodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
		CategoriaProducto categoria = 
                        new CategoriaProductoDAOImpl().obtenerPorId(rs.getInt("categoria_id"));
                    Producto p = new 
        Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
                    p.setCategoría(categoria);
                productos.add(p);
            }
        }
        return productos;

    }

    @Override
    public void actualizar(Producto producto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
