package backend.clinica.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Report implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private LocalDateTime dateReport;
    private String description;
    
    public Report() {}

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
    
}
