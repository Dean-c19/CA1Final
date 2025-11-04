

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.PaymentDAO;
import dao.MemberDAO;
import dao.MembersPlanDAO;
import entities.MembersPlan;
import entities.Member;
import entities.Payment;
import model.MemberM;

@Path("/fitness")
public class FitnessService {


    MembersPlanDAO planDAO = new MembersPlanDAO();
    MemberDAO memberDAO = new MemberDAO();
    PaymentDAO paymentDAO = new PaymentDAO();

    @POST
    @Path("/json/member")
    @Consumes(MediaType.APPLICATION_JSON)// tells jax rs to expect a json
    @Produces(MediaType.APPLICATION_JSON)// returns respone
    public Response addMember(MemberM input) {
        if (input == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please enter Details").build();
        }

        // create a membersplan from the model
    MembersPlan plan = new MembersPlan(input.getPlan().getContent(), input.getPlan().getTotalCost());
    planDAO.persist(plan);
// create a member using the model
    Member member = new Member(input.getMembersId(), input.getName(), input.getPhone(), input.getAddress(), input.getGoals());
    member.setMembersPlan(plan);// link the member to their plan
    memberDAO.persist(member);
// return success response
    return Response.status(Response.Status.CREATED).entity(input).build();


    }

    //returns all members in the database as json
    @GET
    @Path("/members")
    @Produces("application/json")
    public List<Member> getAllMembers(){
        return memberDAO.getAllMembers();

    }

    // deletes a member from their id
    @DELETE
    @Path("/json/member/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMember(@PathParam("id")int id) {
        Member m = memberDAO.getById(id);
        if (m==null){
            return Response.status(Response.Status.NOT_FOUND).entity("member not found").build();

        }
        memberDAO.remove(m);
        return Response.ok("member deleted").build();


    }
}