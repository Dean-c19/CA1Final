package entities;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Subscriber.findAll", query="select o from Subscriber o"), 
	@NamedQuery(name = "Subscriber.findByUsername", query = "select o from Subscriber o where o.username=:username")
})

@Entity
public class Subscriber {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	
	@OneToOne
	private Profile profile;

	public Subscriber() {

	}

	public Subscriber(String username, String password, Profile profile) {
		this.username = username;
		this.password = password;
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	

}
