/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cst8218.jeya0023.entity.Sprite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ajithyugan Jeyakumar
 */
@Stateless
@Path("cst8218.jeya0023.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }
/**
 in this function we are going to update the full attribute  of the sprite but the main thing is our id is not gonna be null here if the id is null
 we will return a response NOT_Found
 and if our id is found in the database then we will update all the attribute of the specific sprite and return an ACCEPTED Response
 */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postOnSpecificID(@PathParam("id") Long id, Sprite entity) {
        if ( entity.getId() == null ){
                 return Response.status(Response.Status.NOT_FOUND).build(); 
        } else {
                entity = super.find(id).upDateInDB(entity);
                super.edit(entity);
                return Response.status(Response.Status.ACCEPTED).entity(entity).build();
                    }
    }

/**
 in this function we are going to edit the attribute of our sprite. if our id did not exist in database then our Response  gonna be not found
 if we find our id then we can edit the entity in the database 

 */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) {
        if( super.find(id) == null){
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
                if( super.find(id) != null){
                    if( super.find(entity.getId()) == null ){
                                return Response.status(Response.Status.NOT_FOUND).build(); 
                    }
                super.edit(entity);
                return Response.status(Response.Status.FOUND).entity(entity).build();
        }
          return Response.status(Response.Status.BAD_REQUEST).build();
        
        }
 /**
here we are gonna return bad request where our table on  the root is not supported
 */
    @PUT 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putOnRoot () {
        return Response.status(Response.Status.BAD_REQUEST).build();
        }
    
/**
in this function we will create an sprite if our id is not in the database or if the id is null. 
but if we had an id in the database the we will overwrite all the attribute of the specific sprite.
but if our id is invalid then we will return a BAD_REQUEST.
 */ 
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postOnRoot (Sprite entity) {
        if ( entity.getId() == null )
        {
        super.create(entity);
        return Response.status(Response.Status.CREATED).build();
        }
        if ( entity.getId() != null && super.find(entity.getId()) != null )
        {
                entity = super.find(entity.getId()).upDateInDB(entity);
                super.edit(entity);
                return Response.status(Response.Status.FOUND).entity(entity).build();
        }
        if (entity.getId() != null && super.find(entity.getId()) == null )
        {
          return Response.status(Response.Status.BAD_REQUEST).build();
        }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
