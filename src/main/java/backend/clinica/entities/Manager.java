package backend.clinica.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Manager {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String contact;
	private boolean permission;
	
	public Manager() {}

	public Manager(Long id, String name, String email, String contact, boolean permission) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.permission = permission;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean getPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	
}
