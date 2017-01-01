package hr.unizg.fer.rassus.grupa5;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dogs")
public class Dog {
	
	@Id
	private Long id;
	
	@Indexed
	private String name;
	private String breed;
	private String owner;
	private Double age;
	private String gender;
	private String health;
	private String hairColour;
	private String eyeColour;
	private Double weight;
	private Double height;
	private String hairLength;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHairColour() {
		return hairColour;
	}
	public void setHairColour(String hairColour) {
		this.hairColour = hairColour;
	}
	public String getEyeColour() {
		return eyeColour;
	}
	public void setEyeColour(String eyeColour) {
		this.eyeColour = eyeColour;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getHairLength() {
		return hairLength;
	}
	public void setHairLength(String hairLength) {
		this.hairLength = hairLength;
	}
}
