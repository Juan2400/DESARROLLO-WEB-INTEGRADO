/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.modelo;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author C25170
 */
public interface ProductoDAO {
    void insertar(Producto producto) throws SQLException;
    Producto obtenerPorId(int id) throws SQLException;
    List<Producto> listarTodos()  throws SQLException;
    void actualizar(Producto producto) throws SQLException;
    void eliminar(int id) throws SQLException;
}
