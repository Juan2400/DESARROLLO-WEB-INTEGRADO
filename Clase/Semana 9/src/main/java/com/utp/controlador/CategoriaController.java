/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utp.controlador;

import com.utp.modelo.CategoriaProducto;
import com.utp.modelo.CategoriaProductoDAO;
import com.utp.modelo.CategoriaProductoDAOImpl;
import jakarta.ejb.Init;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author C25170
 */
@WebServlet(name = "CategoriaController",
        urlPatterns = {"/categoria/*"})
public class CategoriaController extends HttpServlet {

    private CategoriaProductoDAO categoriaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        categoriaDAO = new CategoriaProductoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action=request.getPathInfo();
        if (action==null){
            action="/list";
        }
        try{
            switch (action) {
                case "/new":
                    showNewForm(request,response);
                    break;
                case "/insert":
                    insertCategoria(request,response);
                    break;
                case "/update":
                    updateCategoria(request,response);
                    break;
                case "/list":
                    listarCategorias(request,response);
                    break;
                case "/delete":
                    deleteCategorias(request,response);
                    break;
                case "/edit":
                    showEditForm(request,response);
                    break;
                            
                default:
                    
            }
        }catch (Exception e){
            throw new ServletException(e);
            
        }
        
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher
        ("/WEB-INF/views/categoria-form.jsp").
                    forward(request, response);
    }
    private void updateCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            
         CategoriaProducto c= new CategoriaProducto();
         c.setNombre(request.getParameter
        ("nombre"));
         c.setId(Integer.parseInt(request.getParameter
        ("id")));
         categoriaDAO.actualizar(c);
         response.sendRedirect("./list");
        
         
    }
     private void insertCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            
         CategoriaProducto c= new CategoriaProducto();
         c.setNombre(request.getParameter
        ("nombre"));
         new CategoriaProductoDAOImpl().insertar(c);
         response.sendRedirect("./list");
        
         
    }
     private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         List<CategoriaProducto> lista = 
                 categoriaDAO.listarTodos();
         request.setAttribute("listaCategorias",lista);
         request.getRequestDispatcher
        ("/WEB-INF/views/categoria-list.jsp").
                    forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        CategoriaProducto c =categoriaDAO.obtenerPorId
        (Integer.parseInt
        (request.getParameter("id")));
    
        request.setAttribute("categoria",c);
        request.getRequestDispatcher
        ("/WEB-INF/views/categoria-form.jsp").
                    forward(request, response);
    } 
     private void deleteCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         categoriaDAO.eliminar((Integer.parseInt
        (request.getParameter("id"))));
        response.sendRedirect("./list");    
     } 
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   

}
