package backend.clinica.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import backend.clinica.entities.Patient;
import backend.clinica.entities.Professional;
import backend.clinica.entities.Scheduling;

public class SchedulingDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private LocalDateTime dateHour;
	private Professional professional;
	private Patient patient;
	
	public SchedulingDTO() {}

	public SchedulingDTO(Long id, LocalDateTime dateHour, Professional professional, Patient patient) {
		this.id = id;
		this.dateHour = dateHour;
		this.professional = professional;
		this.patient = patient;
	}
	
	public SchedulingDTO(Scheduling scheduling) {
		this.id = scheduling.getId();
		this.dateHour = scheduling.getDateHour();
		this.professional = scheduling.getProfessional();
		this.patient = scheduling.getPatient();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateHour() {
		return dateHour;
	}

	public void setDateHour(LocalDateTime dateHour) {
		this.dateHour = dateHour;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
}
