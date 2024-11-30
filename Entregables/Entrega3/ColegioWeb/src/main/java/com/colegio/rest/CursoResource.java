package com.colegio.rest;

import com.colegio.dao.CursoDAO;
import com.colegio.dao.CursoDAOImpl;
import com.colegio.modelo.Curso;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    private final CursoDAO cursoDAO = new CursoDAOImpl();

    // Obtener todos los cursos
    @GET
    public List<Curso> getAllCursos() {
        return cursoDAO.listarTodos();
    }

    // Obtener un curso por ID
    @GET
    @Path("/{id}")
    public Response getCursoById(@PathParam("id") int id) {
        Curso curso = cursoDAO.obtenerPorId(id);
        if (curso != null) {
            return Response.ok(curso).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Crear un nuevo curso
    @POST
    public Response createCurso(Curso curso) {
        cursoDAO.insertar(curso);
        return Response.status(Response.Status.CREATED).entity(curso).build();
    }

    // Actualizar un curso existente
    @PUT
    @Path("/{id}")
    public Response updateCurso(@PathParam("id") int id, Curso curso) {
        curso.setIdCurso(id);
        cursoDAO.actualizar(curso);
        return Response.ok(curso).build();
    }

    // Eliminar un curso por ID
    @DELETE
    @Path("/{id}")
    public Response deleteCurso(@PathParam("id") int id) {
        cursoDAO.eliminar(id);
        return Response.noContent().build();
    }
}
