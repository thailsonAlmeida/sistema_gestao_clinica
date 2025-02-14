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
	boolean isConfirmed; 
	boolean isPresent;
	private ProfessionalDTO professional;
	private PatientDTO patient;
	private boolean isCancel;
	
	public SchedulingDTO() {}

	public SchedulingDTO(Long id, LocalDateTime dateHour, boolean isConfirmed, boolean isPresent, boolean isCancel) {
		this.id = id;
		this.dateHour = dateHour;
		this.isConfirmed = isConfirmed;
		this.isPresent = isPresent;
		this.isCancel = isCancel;
	}
	
	public SchedulingDTO(Scheduling scheduling) {
		this.id = scheduling.getId();
		this.dateHour = scheduling.getDateHour();
		this.isConfirmed = scheduling.isConfirmed();
		this.isPresent = scheduling.isPresent();
		this.isCancel = scheduling.isCancel();
	}
	
	public SchedulingDTO(Scheduling scheduling, Professional professional) {
		this(scheduling);
		this.professional = new ProfessionalDTO(professional);
	}
	
	public SchedulingDTO(Scheduling scheduling, Patient patient) {
		this(scheduling);
		this.patient = new PatientDTO(patient);
	}
	
	public SchedulingDTO(Scheduling scheduling, Professional professional, Patient patient) {
		this(scheduling);
		this.professional = new ProfessionalDTO(professional);
		this.patient = new PatientDTO(patient);
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

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public ProfessionalDTO getProfessional() {
		return professional;
	}

	public void setProfessional(ProfessionalDTO professional) {
		this.professional = professional;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
	
}
