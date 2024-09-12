package backend.clinica.entities;

import java.io.Serializable;

public class Professional implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;
    private String specialty;
    private String contact;
    
    public Professional() {}
    
	public Professional(Long id, String name, String specialty, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.contact = contact;
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
    
    

}
