package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "select u from User u"),

})

@XmlRootElement
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String membersId) {
		this.usersId = membersId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;

	}



}
