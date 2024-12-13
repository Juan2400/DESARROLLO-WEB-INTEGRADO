package com.colegio.rest;

import com.colegio.dao.MatriculaDAO;
import com.colegio.dao.MatriculaDAOImpl;
import com.colegio.modelo.Matricula;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/matriculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MatriculaResource {

    private final MatriculaDAO matriculaDAO = new MatriculaDAOImpl();

    // Obtener todas las matrículas
    @GET
    public List<Matricula> getMatriculas() {
        return matriculaDAO.listarTodos();
    }

    // Obtener una matrícula por su ID
    @GET
    @Path("/{id}")
    public Response getMatricula(@PathParam("id") int id) {
        Matricula matricula = matriculaDAO.obtenerPorId(id);
        if (matricula != null) {
            return Response.ok(matricula).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Crear una nueva matrícula
    @POST
    public Response createMatricula(Matricula matricula) {
        try {
            matriculaDAO.insertar(matricula);
            return Response.status(Response.Status.CREATED).entity(matricula).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al crear la matrícula: " + e.getMessage())
                           .build();
        }
    }

    // Actualizar una matrícula existente
    @PUT
    @Path("/{id}")
    public Response updateMatricula(@PathParam("id") int id, Matricula matricula) {
        try {
            matricula.setIdMatricula(id);
            matriculaDAO.actualizar(matricula);
            return Response.ok(matricula).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al actualizar la matrícula: " + e.getMessage())
                           .build();
        }
    }

    // Eliminar una matrícula
    @DELETE
    @Path("/{id}")
    public Response deleteMatricula(@PathParam("id") int id) {
        try {
            matriculaDAO.eliminar(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al eliminar la matrícula: " + e.getMessage())
                           .build();
        }
    }
}
