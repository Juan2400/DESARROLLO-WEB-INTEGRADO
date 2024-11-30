package com.colegio.rest;

import com.colegio.dao.DocenteDAO;
import com.colegio.dao.DocenteDAOImpl;
import com.colegio.modelo.Docente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/docentes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocenteResource {

    private final DocenteDAO docenteDAO = new DocenteDAOImpl();

    // Obtener todos los docentes
    @GET
    public List<Docente> getDocentes() {
        return docenteDAO.listarTodos();
    }

    // Obtener un docente por ID
    @GET
    @Path("/{id}")
    public Response getDocente(@PathParam("id") int id) {
        Docente docente = docenteDAO.obtenerPorId(id);
        if (docente != null) {
            return Response.ok(docente).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Crear un nuevo docente
    @POST
    public Response createDocente(Docente docente) {
        docenteDAO.insertar(docente);
        return Response.status(Response.Status.CREATED).entity(docente).build();
    }

    // Actualizar un docente existente
    @PUT
    @Path("/{id}")
    public Response updateDocente(@PathParam("id") int id, Docente docente) {
        docente.setIdDocente(id);
        docenteDAO.actualizar(docente);
        return Response.ok(docente).build();
    }

    // Eliminar un docente
    @DELETE
    @Path("/{id}")
    public Response deleteDocente(@PathParam("id") int id) {
        docenteDAO.eliminar(id);
        return Response.noContent().build();
    }

    // Obtener docentes por especialidad y estado
    @GET
    @Path("/especialidad/{idEspecialidad}/estado/{estado}")
    public List<Docente> getDocentesByEspecialidadYEstado(@PathParam("idEspecialidad") int idEspecialidad, @PathParam("estado") String estado) {
        return docenteDAO.listarDocentesPorEspecialidadYEstado(idEspecialidad, estado);
    }
}
