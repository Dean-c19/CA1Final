import dao.EmissionDAO;
import dao.UserDAO;
import entities.Emission;
import entities.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/emissions")
public class EmissionService {

    EmissionDAO emissionDAO = new EmissionDAO();
    UserDAO userDAO = new UserDAO();
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Emission> getAllEmissions() {
        return emissionDAO.getAllEmissions();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmissionById(@PathParam("id") int id) {
        Emission e = emissionDAO.getById(id);
        if (e == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Emission not found").build();
        }
        return Response.ok(e).build();
    }

    @POST
    @Path("/json/approve/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveEmission(
            @PathParam("id") int emissionId,
            @QueryParam("userId") int userId
    ) {

        Emission emission = emissionDAO.getById(emissionId);
        if (emission == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Emission not found").build();
        }

        User user = userDAO.getById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found").build();
        }

        emission.setApproved(true);
        emission.setApprovedBy(user);

        emissionDAO.update(emission);

        return Response.ok(emission).build();
    }

    @DELETE
    @Path("/json/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEmission(@PathParam("id") int id) {
        Emission e = emissionDAO.getById(id);
        if (e == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Emission not found").build();
        }
        emissionDAO.remove(e);
        return Response.ok("Emission deleted").build();
    }

    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User input) {
        if (input == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Please provide user details").build();
        }

        userDAO.persist(input);

        return Response.status(Response.Status.CREATED)
                .entity(input)
                .build();
    }


    @GET
    @Path("/allUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }


    @DELETE
    @Path("/jsonUser/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(@PathParam("id") int id) {
        User u = userDAO.getById(id);
        if (u == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found").build();
        }
        userDAO.remove(u);
        return Response.ok("User deleted").build();
    }

    @PUT
    @Path("/jsonUser/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User input) {
        if (input == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Please provide user details").build();
        }

        User existing = userDAO.getById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found").build();
        }

        input.setId(id);
        userDAO.update(input);

        return Response.ok(input).build();
    }
}
