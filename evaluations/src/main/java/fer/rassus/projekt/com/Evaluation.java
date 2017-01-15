package fer.rassus.projekt.com;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Evaluation {

	@Id
	@GeneratedValue
	private Long evalId;
	private Long walkerId;
	private Long ownerId;
	private Long dogId;
	private String comment;
	private Long gradeId;
	private String commentDog;
	private Long gradeDog;
	
	public Evaluation(){
		
	}
	
	public Evaluation(Evaluation eval) {
		super();
		this.gradeId=eval.gradeId;
		this.ownerId = eval.ownerId;
		this.walkerId=eval.walkerId;
		this.dogId = eval.dogId;
		this.comment=eval.comment;
		this.commentDog=eval.commentDog;
		this.gradeDog=eval.gradeDog;
	}


	public String getCommentDog() {
		return commentDog;
	}

	public void setCommentDog(String commentDog) {
		this.commentDog = commentDog;
	}

	public Long getGradeDog() {
		return gradeDog;
	}

	public void setGradeDog(Long gradeDog) {
		this.gradeDog = gradeDog;
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

	public Long getEvalId() {
		return evalId;
	}
	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	
	public Long getDogId() {
		return dogId;
	}
	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
