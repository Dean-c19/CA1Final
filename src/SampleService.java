

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dao.CommentDAO;
import dao.ProfileDAO;
import dao.SubscriberDAO;
import entities.Comment;
import entities.Profile;
import entities.Subscriber;
import model.Employee;

@Path("/sampleservice")
public class SampleService {

    private static Map<String, Employee> employees = new HashMap<String, Employee>();

    static {

        Employee employee1 = new Employee();
        employee1.setEmployeeId("1");
        employee1.setEmployeeName("Fabrizio");
        employee1.setJob("Software Engineer");
        employees.put(employee1.getEmployeeId(), employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeId("2");
        employee2.setEmployeeName("Justin");
        employee2.setJob("Business Analyst");
        employees.put(employee2.getEmployeeId(), employee2);

        SubscriberDAO sDAO = new SubscriberDAO();
        ProfileDAO pDAO = new ProfileDAO();
        CommentDAO cDAO = new CommentDAO();

        //Add comments
        Comment c1 = new Comment("Jane loves apples");
        Comment c2 = new Comment("Jane hates bananas");
        Comment c3 = new Comment("Jane loves dogs");
        cDAO.persist(c1);
        cDAO.persist(c2);
        cDAO.persist(c3);

        List<Comment> comments = new ArrayList<Comment>();
        comments.add(c1);
        comments.add(c2);
        comments.add(c3);
        //Add Profile
        Profile profile = new Profile("Jane's cool profile", comments);
        pDAO.persist(profile);

        //Add Subscriber
        Subscriber subscriber = new Subscriber("Jane","beans", profile );
        sDAO.persist(subscriber);

        //View all subscribers (here I've accessed all objects through the subscriber)
        ArrayList<Subscriber> subscribers = (ArrayList<Subscriber>) sDAO.getAllSubscribers();
        for(Subscriber s : subscribers) {
            System.out.println("Subscriber object username is "+s.getUsername());
            System.out.println("Subscriber's Profile says "+ s.getProfile().getDescription());
            //Note I've made an Eagar Fetch on the Comments List in Profile to enable this
            System.out.println("Subscriber's profile's first comment is "+s.getProfile().getComments().get(0).getContent());
        }

        //Update username using merge
        subscriber.setUsername("JANEY");
        sDAO.merge(subscriber);

        //remove the last comment
        cDAO.remove(c3);

        //Get subscriber by username, print their password
        System.out.println(sDAO.getSubscriberByUsername("JANEY").getPassword());

    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Hello World";
    }

    @GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        return message;
    }


    @GET
    @Path("/employees")
    @Produces("application/xml")
    public List<Employee> listEmployees(){
        return new ArrayList<Employee>(employees.values());
    }

    @GET
    @Path("/employee/{employeeid}")
    @Produces("application/xml")
    public Employee getEmployee(@PathParam("employeeid")String employeeId){
        return employees.get(employeeId);
    }

    @GET
    @Path("/json/employees/")
    @Produces("application/json")
    public List<Employee> listEmployeesJSON(){
        return new ArrayList<Employee>(employees.values());
    }

    @GET
    @Path("/json/employee/{employeeid}")
    @Produces("application/json")
    public Employee getEmployeeJSON(@PathParam("employeeid")String employeeId){
        return employees.get(employeeId);
    }

}

