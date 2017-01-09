package hr.unizg.fer.rassus.grupa5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Registration {
	
	   @Id
	
	   private long id;
	   private String email;
	   private String username;
	   private String password;
	   private long personId;
	   
	public Registration(){ //constructor for JPA
		
	}
	public Registration(String email, String username, String password, long personId) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.personId=personId;
	}
	
	public Registration(String n) {
		// TODO Auto-generated constructor stub
		this.username=n;
		this.password="pass"+n;
		this.email=n+"123@mail.com";
		this.personId=1;
	}
	public Registration(Registration reg) {
		// TODO Auto-generated constructor stub
		this.username=reg.username;
		this.password=reg.password;
		this.email=reg.email;
		this.personId=reg.personId;
	}
	@Override
	public String toString(){
		return "registration number "+id+", username:"+username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
}
