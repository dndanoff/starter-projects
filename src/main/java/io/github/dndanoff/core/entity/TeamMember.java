package io.github.dndanoff.core.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public final class TeamMember implements Entity{
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String photoUrl;
	private LocalDate hireDate;
	private Boolean active;
	private Title title;
	private List<Contact> contacts;
	private List<Technology> knownTechnologies;
	
	public TeamMember(String firstName, String lastName, LocalDate hireDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
		this.active = true;
		this.contacts = new ArrayList<>();
		this.knownTechnologies = new ArrayList<>();
		
		if(firstName == null || firstName.trim().length() == 0) {
			throw new IllegalStateException("Team member's firstname cannot be null or empty.");
		}
		
		if(lastName == null || lastName.trim().length() == 0) {
			throw new IllegalStateException("Team member's lastName cannot be null or empty.");
		}
		
		if(hireDate == null) {
			throw new IllegalStateException("Team member's hireDate cannot be null.");
		}
	}
	
	public void setKnownTechnologies(List<Technology> knownTechnologies){
		if(knownTechnologies == null) {
			return;
		}
		
		this.knownTechnologies.clear();
		this.knownTechnologies.addAll(knownTechnologies);
	}
	
	public List<Technology> getKnownTechnologies(){
		return new ArrayList<>(knownTechnologies);
	}
	
	public void setContacts(List<Contact> contacts){
		if(contacts == null) {
			return;
		}
		
		this.contacts.clear();
		this.contacts.addAll(contacts);
	}
	
	public List<Contact> getContacts(){
		return new ArrayList<>(contacts);
	}
}
