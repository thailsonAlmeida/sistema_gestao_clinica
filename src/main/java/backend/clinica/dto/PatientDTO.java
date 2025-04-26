package backend.clinica.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import backend.clinica.entities.Patient;
import backend.clinica.entities.Report;

public class PatientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String name;
    private String address;
    private String email;
    private String contact;
    private String birthDay;   
    
    private List<ReportDTO> reportHistory = new ArrayList<ReportDTO>();
    
    public PatientDTO() {}

	public PatientDTO(Long id, String name, String address, String contact, String birthDay, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.birthDay = birthDay;
		this.email = email;
	}
    
    public PatientDTO(Patient patient) {
    	this.id = patient.getId();
		this.name = patient.getName();
		this.address = patient.getAddress();
		this.contact = patient.getContact();
		this.birthDay = patient.getBirthDay();
		this.email = patient.getEmail();
    }
    
    public PatientDTO(Patient patient, Set<Report> reports) {
    	this(patient);
    	reports.forEach(repo -> this.reportHistory.add(new ReportDTO(repo, repo.getProfessional())));
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ReportDTO> getReportHistory() {
		return reportHistory;
	}

	public void setReportHistory(List<ReportDTO> reportHistory) {
		this.reportHistory = reportHistory;
	}
    
}
