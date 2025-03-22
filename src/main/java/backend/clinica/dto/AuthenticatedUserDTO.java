package backend.clinica.dto;

import backend.clinica.entities.User;
import backend.clinica.enums.UserRole;

public class AuthenticatedUserDTO {
	
	private Long userId;
    private String login;
    private UserRole role;
    private ProfessionalDTO professional;

    public AuthenticatedUserDTO(User user) {
        this.userId = user.getId();
        this.login = user.getLogin();
        this.role = user.getRole();
        this.professional = user.getProfessional() != null ? new ProfessionalDTO(user.getProfessional()) : null;
    }

    public Long getUserId() { return userId; }
    public String getLogin() { return login; }
    public UserRole getRole() { return role; }
    public ProfessionalDTO getProfessional() { return professional; }

}
