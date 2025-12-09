package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

//named query to get all users
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "select u from User u"),

})

@XmlRootElement(name = "emissions")
@Entity
public class User {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String usersId;
	private String name;


	public User() {

	}

	
	public User(String usersId, String name) {
		this.usersId = usersId;
		this.name = name;

	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String membersId) {
		this.usersId = membersId;
	}


	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;

	}



}
