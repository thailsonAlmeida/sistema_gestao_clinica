package backend.clinica.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_scheduling")
public class Scheduling implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private LocalDateTime dateHour;
    
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    public Scheduling() {}
    
	public Scheduling(Long id, LocalDateTime dateHour, Professional professional, Patient patient) {
		super();
		this.id = id;
		this.dateHour = dateHour;
		this.professional = professional;
		this.patient = patient;
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
		Scheduling other = (Scheduling) obj;
		return Objects.equals(id, other.id);
	}
    
}
