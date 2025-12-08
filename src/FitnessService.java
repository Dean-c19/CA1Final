

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import dao.UserDAO;
import entities.User;
import model.UserM;

@Path("/fitness")
public class FitnessService {


    UserDAO userDAO = new UserDAO();


    @POST
    @Path("/json/member")
    @Consumes(MediaType.APPLICATION_JSON)// tells jax rs to expect a json
    @Produces(MediaType.APPLICATION_JSON)// returns respone
    public Response addMember(UserM input) {
        if (input == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please enter Details").build();
        }

        }

    //returns all members in the database as json
    @GET
    @Path("/members")
    @Produces("application/json")
    public List<User> getAllUsers(){
        return userDAO.getAllUsers();

    }

    // deletes a member from their id
    @DELETE
    @Path("/json/member/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMember(@PathParam("id")int id) {
        User m = userDAO.getById(id);
        if (m==null){
            return Response.status(Response.Status.NOT_FOUND).entity("member not found").build();

        }
        userDAO.remove(m);
        return Response.ok("member deleted").build();


    }
}