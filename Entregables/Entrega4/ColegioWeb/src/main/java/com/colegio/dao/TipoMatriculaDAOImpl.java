package com.colegio.dao;

import com.colegio.modelo.TipoMatricula;
import com.colegio.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoMatriculaDAOImpl implements TipoMatriculaDAO {

 
    @Override
    public List<TipoMatricula> listarTodos() {
        List<TipoMatricula> tiposMatricula = new ArrayList<>();
        String sql = "SELECT id_tipo_matricula, nombre_tipo, descripcion_tipo FROM Tipos_Matricula";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TipoMatricula tipoMatricula = new TipoMatricula();
                tipoMatricula.setIdTipoMatricula(rs.getInt("id_tipo_matricula"));
                tipoMatricula.setNombreTipo(rs.getString("nombre_tipo"));
                tipoMatricula.setDescripcionTipo(rs.getString("descripcion_tipo"));
                tiposMatricula.add(tipoMatricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposMatricula;
    }
    
    @Override
    public TipoMatricula obtenerPorId(int idTipoMatricula) {
        String sql = "SELECT id_tipo_matricula, nombre_tipo, descripcion_tipo FROM Tipos_Matricula WHERE id_tipo_matricula = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idTipoMatricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    TipoMatricula tipoMatricula = new TipoMatricula();
                    tipoMatricula.setIdTipoMatricula(rs.getInt("id_tipo_matricula"));
                    tipoMatricula.setNombreTipo(rs.getString("nombre_tipo"));
                    tipoMatricula.setDescripcionTipo(rs.getString("descripcion_tipo"));
                    return tipoMatricula;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
