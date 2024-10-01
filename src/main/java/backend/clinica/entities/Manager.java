package backend.clinica.entities;

public class Manager {
	
	private String name;
	private String email;
	private String contact;
	private String permission;
	
	public Manager() {}

	public Manager(String name, String email, String contact, String permission) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.permission = permission;
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

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
