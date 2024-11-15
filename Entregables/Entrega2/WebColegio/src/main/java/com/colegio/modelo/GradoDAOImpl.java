package com.colegio.modelo;

import com.colegio.modelo.GradoDAO;
import com.colegio.modelo.Grado;
import com.colegio.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradoDAOImpl implements GradoDAO {

    @Override
    public Grado obtenerPorId(int id_grado) throws SQLException {
        String sql = "SELECT * FROM Grados WHERE id_grado = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_grado);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Grado grado = new Grado();
                    grado.setId_grado(rs.getInt("id_grado"));
                    grado.setNombre_grado(rs.getString("nombre_grado"));
                    grado.setDescripcion_grado(rs.getString("descripcion_grado"));
                    return grado;
                }
            }
        }
        return null;
    }

    @Override
    public List<Grado> listarTodos() throws SQLException {
        List<Grado> grados = new ArrayList<>();
        String sql = "SELECT * FROM Grados";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Grado grado = new Grado();
                grado.setId_grado(rs.getInt("id_grado"));
                grado.setNombre_grado(rs.getString("nombre_grado"));
                grado.setDescripcion_grado(rs.getString("descripcion_grado"));
                grados.add(grado);
            }
        }
        return grados;
    }

}
