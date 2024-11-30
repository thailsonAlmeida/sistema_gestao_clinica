package backend.clinica.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import backend.clinica.entities.Professional;
import backend.clinica.entities.Scheduling;

public class ProfessionalDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String name;
    private String specialty;
    private String contact;
    
    private List<SchedulingDTO> schedulings = new ArrayList<>();
    
    
    public ProfessionalDTO() {}

	public ProfessionalDTO(Long id, String name, String specialty, String contact) {
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.contact = contact;
	}
	
	public ProfessionalDTO(Professional professional) {
		this.id = professional.getId();
		this.name = professional.getName();
		this.specialty = professional.getSpecialty();
		this.contact = professional.getContact();
	}
	
	public ProfessionalDTO(Professional professional,  Set<Scheduling> schedulings) {
		this(professional);
		schedulings.forEach(sched -> this.schedulings.add(new SchedulingDTO(sched, sched.getPatient())));
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
	
}
