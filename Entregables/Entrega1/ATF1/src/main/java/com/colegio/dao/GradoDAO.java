package com.colegio.dao;

import com.colegio.model.Grado;
import com.colegio.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradoDAO {

    // Método para listar todos los grados
    public List<Grado> listarGrados() throws SQLException {
        List<Grado> grados = new ArrayList<>();
        String sql = "SELECT * FROM Grados";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setIdGrado(rs.getInt("id_grado"));
                grado.setNombreGrado(rs.getString("nombre_grado"));
                grado.setDescripcionGrado(rs.getString("descripcion_grado"));
                
                grados.add(grado);
            }
        }
        return grados;
    }
    
   
}
