package com.clinic.app.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Doctor implements Ownable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id = null;
	
	String name;
	
	String phone;
	
	@ManyToOne
	User createdBy;
	
	@ManyToMany
	List<User> sharedWith = new ArrayList<User>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<User> getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(List<User> sharedWith) {
		this.sharedWith = sharedWith;
	}
	
	
	
}
