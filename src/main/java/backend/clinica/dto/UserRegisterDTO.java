package backend.clinica.dto;

import backend.clinica.enums.UserRole;

public record UserRegisterDTO(String login, String password, UserRole role) {

}
