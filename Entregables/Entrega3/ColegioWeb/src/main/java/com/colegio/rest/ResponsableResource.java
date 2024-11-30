package com.colegio.rest;

import com.colegio.dao.ResponsableDAO;
import com.colegio.dao.ResponsableDAOImpl;
import com.colegio.modelo.Responsable;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/responsables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResponsableResource {

    private final ResponsableDAO responsableDAO = new ResponsableDAOImpl();

    @GET
    public List<Responsable> getResponsables() {
        return responsableDAO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response getResponsable(@PathParam("id") int id) {
        Responsable responsable = responsableDAO.obtenerPorId(id);
        if (responsable != null) {
            return Response.ok(responsable).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createResponsable(Responsable responsable) {
        responsableDAO.insertar(responsable);
        return Response.status(Response.Status.CREATED).entity(responsable).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateResponsable(@PathParam("id") int id, Responsable responsable) {
        responsable.setIdResponsable(id);
        responsableDAO.actualizar(responsable);
        return Response.ok(responsable).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteResponsable(@PathParam("id") int id) {
        responsableDAO.eliminar(id);
        return Response.noContent().build();
    }
}
