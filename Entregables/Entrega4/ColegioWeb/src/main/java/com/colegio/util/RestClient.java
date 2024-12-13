/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.colegio.util;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class RestClient {
    
    private static final long serialVersionUID = 1L;
    private static final String BASE_URI = "http://localhost:8080/ColegioWeb/api";
    private final Client client;
    private final WebTarget target;
    
    public RestClient() {
        this.client = ClientBuilder.newClient();
        this.target = client.target(BASE_URI);
    }

    public <T> List<T> getList(String path, Class<T> responseType) {
        return target.path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<T>>(){});
    }

    public <T> T get(String path, Class<T> responseType) {
        return target.path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .get(responseType);
    }

    public <T> T post(String path, Object requestEntity, Class<T> responseType) {
        return target.path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T put(String path, Object requestEntity, Class<T> responseType) {
        return target.path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(requestEntity, MediaType.APPLICATION_JSON), responseType);
    }

    public Response delete(String path) {
        return target.path(path)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();
    }

}
