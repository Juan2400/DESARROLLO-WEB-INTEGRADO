/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utp.controlador;

import com.utp.modelo.CategoriaProducto;
import com.utp.modelo.CategoriaProductoDAOImpl;
import com.utp.modelo.Producto;
import com.utp.modelo.ProductoDAO;
import com.utp.modelo.ProductoDAOImpl;
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
@WebServlet(name = "ProductoController", 
        urlPatterns = {"/producto/*"})
public class ProductoController extends HttpServlet {
    private ProductoDAO productoDAO=new ProductoDAOImpl();    

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
                    insertProducto(request,response);
                    break;
                case "/update":
                   // updateProducto(request,response);
                    break;
                case "/list":
                    listarProductos(request,response);
                    break;
                case "/delete":
                  //  deleteCategorias(request,response);
                    break;
                case "/edit":
                   // showEditForm(request,response);
                    break;
                            
                default:
                    
            }
        }catch (Exception e){
            throw new ServletException(e);
            
        }
    }
     private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         
         List<CategoriaProducto> lista = 
                 new CategoriaProductoDAOImpl().listarTodos();
         request.setAttribute("listaCategorias",lista);
         request.getRequestDispatcher
        ("/WEB-INF/views/producto-form.jsp").
                    forward(request, response);
    }
      private void insertProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            
         Producto c= new Producto();
         c.setNombre(request.getParameter
        ("nombre"));
         c.setPrecio(Double.parseDouble
        (request.getParameter
        ("precio")));
         CategoriaProducto cat= new CategoriaProducto();
         cat.setId(Integer.parseInt(request.getParameter
        ("categoria")));
         c.setCategoría(cat);
         new ProductoDAOImpl().insertar(c);
         response.sendRedirect("./list");
        
    }
      private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         List<Producto> lista = 
                 productoDAO.listarTodos();
         request.setAttribute("listaProductos",lista);
         request.getRequestDispatcher
        ("/WEB-INF/views/producto-list.jsp").
                    forward(request, response);
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
