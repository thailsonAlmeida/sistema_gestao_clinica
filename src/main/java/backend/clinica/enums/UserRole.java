package backend.clinica.enums;

public enum UserRole {
    
	PROFESSIONAL("ROLE_PROFESSIONAL"),
    MANAGER("ROLE_MANAGER");
    
    private String role;
    
    UserRole(String role){
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}
