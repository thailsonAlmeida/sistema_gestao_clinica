package backend.clinica.enums;

public enum UserRole {
    
    PROFESSIONAL("PROFESSIONAL"),
    MANAGER("MANAGER");
    
    private String role;
    
    UserRole(String role){
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
}
