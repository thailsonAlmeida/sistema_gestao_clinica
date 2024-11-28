package backend.clinica.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_report")
public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private LocalDateTime dateReport;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    
    public Report() {}
    
	public Report(Long id, LocalDateTime dateReport, String description) {
		this.id = id;
		this.dateReport = dateReport;
		this.description = description;
	}
			
	public Report(Long id, LocalDateTime dateReport, String description, Patient patient, Professional professional) {
		this.id = id;
		this.dateReport = dateReport;
		this.description = description;
		this.patient = patient;
		this.professional = professional;
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

	public Professional getProfessional() {
		return professional;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		return Objects.equals(id, other.id);
	}
	
}
