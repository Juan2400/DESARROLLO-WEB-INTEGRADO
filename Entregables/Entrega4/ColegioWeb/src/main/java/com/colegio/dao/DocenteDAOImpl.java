package com.colegio.dao;

import com.colegio.modelo.Docente;
import com.colegio.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.colegio.modelo.Especialidad;

public class DocenteDAOImpl implements DocenteDAO {

    private EspecialidadDAO especialidadDAO = new EspecialidadDAOImpl();

    @Override
    public void insertar(Docente docente) {
        String sql = "INSERT INTO docentes (dni, nombre, apellido, telefono, email, id_especialidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, docente.getDni());
            pstmt.setString(2, docente.getNombre());
            pstmt.setString(3, docente.getApellido());
            pstmt.setString(4, docente.getTelefono());
            pstmt.setString(5, docente.getEmail());
            pstmt.setInt(6, docente.getEspecialidad().getIdEspecialidad());
            pstmt.setString(7, docente.getEstado());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    docente.setIdDocente(generatedKeys.getInt(1));  // Establecer el ID del Docente
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Docente> listarTodos() {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT * FROM docentes";

        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setDni(rs.getString("dni"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setTelefono(rs.getString("telefono"));
                docente.setEmail(rs.getString("email"));

                Especialidad especialidad = especialidadDAO.obtenerPorId(rs.getInt("id_especialidad"));
                docente.setEspecialidad(especialidad);

                docente.setEstado(rs.getString("estado"));
                docentes.add(docente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    public List<Docente> listarDocentesPorEspecialidadYEstado(int idEspecialidad, String estado) {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT * FROM Docentes WHERE id_especialidad = ? AND estado = ?";
        
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idEspecialidad);
            pstmt.setString(2, estado);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Docente docente = new Docente();
                    docente.setIdDocente(rs.getInt("id_docente"));
                    docente.setDni(rs.getString("dni"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setApellido(rs.getString("apellido"));
                    docente.setTelefono(rs.getString("telefono"));
                    docente.setEmail(rs.getString("email"));
                    docente.setEstado(rs.getString("estado"));
                    
                    Especialidad especialidad = new Especialidad();
                    especialidad.setIdEspecialidad(rs.getInt("id_especialidad"));
                    docente.setEspecialidad(especialidad);
                    
                    docentes.add(docente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    @Override
    public Docente obtenerPorId(int idDocente) {
        String sql = "SELECT * FROM docentes WHERE id_docente = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDocente);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Docente docente = new Docente();
                    docente.setIdDocente(rs.getInt("id_docente"));
                    docente.setDni(rs.getString("dni"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setApellido(rs.getString("apellido"));
                    docente.setTelefono(rs.getString("telefono"));
                    docente.setEmail(rs.getString("email"));

                    Especialidad especialidad = especialidadDAO.obtenerPorId(rs.getInt("id_especialidad"));
                    docente.setEspecialidad(especialidad);

                    docente.setEstado(rs.getString("estado"));
                    return docente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Docente docente) {
        String sql = "UPDATE docentes SET dni = ?, nombre = ?, apellido = ?, telefono = ?, email = ?, id_especialidad = ?, estado = ? WHERE id_docente = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getDni());
            pstmt.setString(2, docente.getNombre());
            pstmt.setString(3, docente.getApellido());
            pstmt.setString(4, docente.getTelefono());
            pstmt.setString(5, docente.getEmail());
            pstmt.setInt(6, docente.getEspecialidad().getIdEspecialidad());
            pstmt.setString(7, docente.getEstado());
            pstmt.setInt(8, docente.getIdDocente());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int idDocente) {
        String sql = "DELETE FROM docentes WHERE id_docente = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDocente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
