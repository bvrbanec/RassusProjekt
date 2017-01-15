package hr.unizg.fer.rassus.grupa5;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Dog {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private String name;
	private Long ownerId;
	private String breed;
	private Double age;
	private String gender;
	private String health;
	private String hairColour;
	private String eyeColour;
	private Double weight;
	private Double height;
	private String hairLength;
	
	
public Dog(){} //constructor for JPA
	
	
	
	public Dog(String name, String breed, Long ownerId, Double age, String gender, String health,
			String hairColour, String eyeColour, Double weight, Double height, String hairLength) {
		super();
		this.name = name;
		this.breed = breed;
		this.ownerId = ownerId;
		this.age = age;
		this.gender = gender;
		this.health = health;
		this.hairColour = hairColour;
		this.eyeColour = eyeColour;
		this.weight = weight;
		this.height = height;
		this.hairLength = hairLength;
	}
	
	public Dog(Dog reg){
		this.name = reg.name;
		this.breed = reg.breed;
		this.ownerId = reg.ownerId;
		this.age = reg.age;
		this.gender = reg.gender;
		this.health = reg.health;
		this.hairColour = reg.hairColour;
		this.eyeColour = reg.eyeColour;
		this.weight = reg.weight;
		this.height = reg.height;
		this.hairLength = reg.hairLength;
	}



	@Override
	public String toString() {
		return "Dog [id=" + id + ", name=" + name + ", breed=" + breed + ", owner=" + ownerId + ", age=" + age
				+ ", gender=" + gender + ", health=" + health + ", hairColour=" + hairColour + ", eyeColour="
				+ eyeColour + ", weight=" + weight + ", height=" + height + ", hairLength=" + hairLength + "]";
	}



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
	
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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
