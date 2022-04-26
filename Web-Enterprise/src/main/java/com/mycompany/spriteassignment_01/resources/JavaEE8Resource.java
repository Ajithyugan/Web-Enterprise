package com.mycompany.spriteassignment_01.resources;

import cst8218.jeya0023.entity.Sprite;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("javaee8")
public class JavaEE8Resource {
    @GET
    public Response ping(){
        return Response
                .ok("Assignment_01")
                .build();
    }
    @POST
    public Response create(){
        return Response.status(Response.Status.CREATED).entity(Sprite.class).build();
    }
}
