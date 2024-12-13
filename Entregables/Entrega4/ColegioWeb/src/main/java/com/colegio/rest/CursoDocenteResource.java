package com.colegio.rest;

import com.colegio.dao.CursoDocenteDAO;
import com.colegio.dao.CursoDocenteDAOImpl;
import com.colegio.modelo.CursoDocente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/curso-docente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoDocenteResource {

    private final CursoDocenteDAO cursoDocenteDAO = new CursoDocenteDAOImpl();

    // Obtener todas las asignaciones de cursos a docentes
    @GET
    public List<CursoDocente> getAllCursoDocente() {
        return cursoDocenteDAO.listarTodos();
    }

    // Obtener una asignación por su ID
    @GET
    @Path("/{id}")
    public Response getCursoDocenteById(@PathParam("id") int id) {
        CursoDocente cursoDocente = cursoDocenteDAO.obtenerPorId(id);
        if (cursoDocente != null) {
            return Response.ok(cursoDocente).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Crear una nueva asignación de curso a docente
    @POST
    public Response createCursoDocente(CursoDocente cursoDocente) {
        cursoDocenteDAO.insertar(cursoDocente);
        return Response.status(Response.Status.CREATED).entity(cursoDocente).build();
    }

    // Actualizar una asignación existente
    @PUT
    @Path("/{id}")
    public Response updateCursoDocente(@PathParam("id") int id, CursoDocente cursoDocente) {
        cursoDocente.setIdCursoDocente(id);
        cursoDocenteDAO.actualizar(cursoDocente);
        return Response.ok(cursoDocente).build();
    }

    // Eliminar una asignación por su ID
    @DELETE
    @Path("/{id}")
    public Response deleteCursoDocente(@PathParam("id") int id) {
        cursoDocenteDAO.eliminar(id);
        return Response.noContent().build();
    }
}
