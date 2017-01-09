package com.vo44480.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_USER")
public class User {

	@Id
//	@Column(length=40)
//	@GeneratedValue(generator="randomId")
//	@GenericGenerator(name="randomId", strategy="com.vo44480.model.RandomIdGenerator")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
//	@Column(unique = true, nullable = false, name="IME_KOLONE_U_BAZI")
	private String username;
	
//	@Column(name="hugo")
	private String firstName;
	
	private String lastName;
	
	private Integer numOfDogs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getNumOfDogs() {
		return numOfDogs;
	}

	public void setNumOfDogs(Integer numOfDogs) {
		this.numOfDogs = numOfDogs;
	}
	
	
}
