package backend.clinica.entities;

import java.io.Serializable;

public class Patient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String name;
    private String address;
    private String contact;
    private String birthDay;   
    
    public Patient() {}   
    
	public Patient(Long id, String name, String address, String contact, String birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.birthDay = birthDay;
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
    
    

}
