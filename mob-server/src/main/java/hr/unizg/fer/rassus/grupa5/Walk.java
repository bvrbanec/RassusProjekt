package hr.unizg.fer.rassus.grupa5;

public class Walk {

	private Long id;
	private Long dogId;
	private String dogName;
	private Long walkerId;
	private String walkerName;
	private Long ownerId;
	private String ownerName;
	private Integer duration;
	private Integer distance;
	private Integer price;
	private Integer rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDogId() {
		return dogId;
	}

	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Long getWalkerId() {
		return walkerId;
	}

	public void setWalkerId(Long walkerId) {
		this.walkerId = walkerId;
	}

	public String getWalkerName() {
		return walkerName;
	}

	public void setWalkerName(String walkerName) {
		this.walkerName = walkerName;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
