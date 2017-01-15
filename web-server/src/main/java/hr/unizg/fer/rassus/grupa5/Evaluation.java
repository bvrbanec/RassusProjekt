package hr.unizg.fer.rassus.grupa5;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Evaluation {

	@Id
	private Long evalId;
	private Long walkerId;
	private String walkerName;
	private Long ownerId;
	private Long dogId;
	private String dogName;
	private String walkerComment;
	private int walkerRating;
	private String dogComment;
	private int dogRating;

	public Long getEvalId() {
		return evalId;
	}

	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}

	public Long getWalkerId() {
		return walkerId;
	}

	public void setWalkerId(Long walkerId) {
		this.walkerId = walkerId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getDogId() {
		return dogId;
	}

	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}

	public String getWalkerComment() {
		return walkerComment;
	}

	public void setWalkerComment(String walkerComment) {
		this.walkerComment = walkerComment;
	}

	public int getWalkerRating() {
		return walkerRating;
	}

	public void setWalkerRating(int walkerRating) {
		this.walkerRating = walkerRating;
	}

	public String getDogComment() {
		return dogComment;
	}

	public void setDogComment(String dogComment) {
		this.dogComment = dogComment;
	}

	public int getDogRating() {
		return dogRating;
	}

	public void setDogRating(int dogRating) {
		this.dogRating = dogRating;
	}

	public String getWalkerName() {
		return walkerName;
	}

	public void setWalkerName(String walkerName) {
		this.walkerName = walkerName;
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}
}
