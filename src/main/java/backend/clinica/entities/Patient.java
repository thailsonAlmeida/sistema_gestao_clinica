package backend.clinica.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_patient")
public class Patient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    private String address;
    private String contact;
    private String birthDay;   
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Report> reportHistory;
    
    public Patient() {}   
    
	public Patient(Long id, String name, String address, String contact, String birthDay) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.birthDay = birthDay;
	}	

	public Patient(Long id, String name, String address, String contact, String birthDay, List<Report> reportHistory) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.birthDay = birthDay;
		this.reportHistory = reportHistory;
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

	public List<Report> getReportHistory() {
		return reportHistory;
	}

	public void setReportHistory(List<Report> reportHistory) {
		this.reportHistory = reportHistory;
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}	
	
	
}
