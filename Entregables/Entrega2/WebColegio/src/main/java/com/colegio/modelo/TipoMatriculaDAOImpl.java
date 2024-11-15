package com.colegio.modelo;

import com.colegio.modelo.TipoMatricula;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoMatriculaDAOImpl implements TipoMatriculaDAO {

    
    @Override
    public TipoMatricula obtenerPorId(int id_tipo_matricula) throws SQLException {
        String sql = "SELECT * FROM Tipos_Matricula WHERE id_tipo_matricula = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_tipo_matricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TipoMatricula(
                        rs.getInt("id_tipo_matricula"),
                        rs.getString("nombre_tipo"),
                        rs.getString("descripcion_tipo")
                    );
                }
            }
        }
        return null;
    }
    
    @Override
    public List<TipoMatricula> listarTodos() throws SQLException {
        List<TipoMatricula> tiposMatricula = new ArrayList<>();
        String sql = "SELECT * FROM Tipos_Matricula";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tiposMatricula.add(new TipoMatricula(
                    rs.getInt("id_tipo_matricula"),
                    rs.getString("nombre_tipo"),
                    rs.getString("descripcion_tipo")
                ));
            }
        }
        return tiposMatricula;
    }


    
}
