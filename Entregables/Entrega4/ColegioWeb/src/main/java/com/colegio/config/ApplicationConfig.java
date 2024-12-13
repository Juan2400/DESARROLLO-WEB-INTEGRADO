/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.colegio.config;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(com.colegio.rest.MatriculaResource.class);
        resources.add(com.colegio.rest.ResponsableResource.class);
        resources.add(com.colegio.rest.DocenteResource.class);
        resources.add(com.colegio.rest.AlumnoResource.class);
        resources.add(com.colegio.rest.CursoDocenteResource.class);
        resources.add(com.colegio.rest.CursoResource.class);
        return resources;
    }
}
