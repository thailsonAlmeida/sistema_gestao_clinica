package backend.clinica.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import backend.clinica.entities.Patient;
import backend.clinica.entities.Professional;
import backend.clinica.entities.Report;

public class ReportDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private LocalDateTime dateReport;
    private String description;
    
    private Patient patient;
    
    private Professional professional;
    
    public ReportDTO() {}

	public ReportDTO(Long id, LocalDateTime dateReport, String description, Patient patient,
			Professional professional) {
		this.id = id;
		this.dateReport = dateReport;
		this.description = description;
		this.patient = patient;
		this.professional = professional;
	}
	
	public ReportDTO(Report report) {
		this.id = report.getId();
		this.dateReport = report.getDateReport();
		this.description = report.getDescription();
		this.patient = report.getPatient();
		this.professional = report.getProfessional();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateReport() {
		return dateReport;
	}

	public void setDateReport(LocalDateTime dateReport) {
		this.dateReport = dateReport;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}
    
}
