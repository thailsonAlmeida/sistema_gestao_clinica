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
    private String reportType;
    private String description;
    
    private PatientDTO patient;
    
    private ProfessionalDTO professional;
    
    public ReportDTO() {}

	public ReportDTO(Long id, LocalDateTime dateReport, String reportType, String description, Patient patient,
			Professional professional) {
		this.id = id;
		this.dateReport = dateReport;
		this.reportType = reportType;
		this.description = description;
	}
	
	public ReportDTO(Report report) {
		this.id = report.getId();
		this.dateReport = report.getDateReport();
		this.reportType = report.getReportType();
		this.description = report.getDescription();
	}
	
	public ReportDTO(Report report, Professional professional) {
		this(report);
		this.professional = new ProfessionalDTO(professional);
	}
	
	public ReportDTO(Report report, Patient patient) {
		this(report);
		this.patient = new PatientDTO(patient);
	}
	
	public ReportDTO(Report report, Professional professional, Patient patient) {
		this(report);
		this.professional = new ProfessionalDTO(professional);
		this.patient = new PatientDTO(patient);
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

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public ProfessionalDTO getProfessional() {
		return professional;
	}

	public void setProfessional(ProfessionalDTO professional) {
		this.professional = professional;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
