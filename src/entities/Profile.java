package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Profile.findAll", query = "select o from Profile o ")


@Entity
public class Profile {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String description;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Profile() {

	}

	
	public Profile(String description, List<Comment> comments) {
		super();
		this.description = description;
		this.comments = comments;
	}
	
	
	public Profile(String description) {
		this.description = description;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public List<Comment> getComments() {
		return comments;
	}



	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	
	

}
