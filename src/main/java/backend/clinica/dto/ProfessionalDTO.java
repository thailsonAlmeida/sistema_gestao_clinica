package backend.clinica.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import backend.clinica.entities.Professional;
import backend.clinica.entities.Scheduling;
import backend.clinica.entities.User;

public class ProfessionalDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String name;
    private String specialty;
    private String contact;
    private String email;
    
    private List<SchedulingDTO> schedulings = new ArrayList<>();  
    
    private User user;
    
    public ProfessionalDTO() {}

    public ProfessionalDTO(Long id, String name, String specialty, String contact, String email) {
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.contact = contact;
		this.email = email;
	}
	
	public ProfessionalDTO(Long id, String name, String specialty, String contact, String email, User user) {
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.contact = contact;
		this.email = email;
		this.user = user;
	}
	
	public ProfessionalDTO(Professional professional) {
		this.id = professional.getId();
		this.name = professional.getName();
		this.specialty = professional.getSpecialty();
		this.contact = professional.getContact();
		this.email = professional.getEmail();
	}
	
	public ProfessionalDTO(Professional professional,  Set<Scheduling> schedulings) {
		this(professional);
		schedulings.forEach(sched -> this.schedulings.add(new SchedulingDTO(sched, sched.getPatient())));
	}
	
	public ProfessionalDTO(Professional professional,  Set<Scheduling> schedulings, User user) {
		this(professional);
		schedulings.forEach(sched -> this.schedulings.add(new SchedulingDTO(sched, sched.getPatient())));
		this.user = user;
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<SchedulingDTO> getSchedulings() {
		return schedulings;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
