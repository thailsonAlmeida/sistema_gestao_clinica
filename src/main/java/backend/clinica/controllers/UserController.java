package backend.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import backend.clinica.dto.UserRegisterDTO;
import backend.clinica.entities.User;
import backend.clinica.services.UserService;
import backend.clinica.dto.AuthenticatedUserDTO;
import backend.clinica.dto.UserDTO;
import backend.clinica.dto.UserLoginResponseDTO;

@RestController
@RequestMapping("/auth")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Validated UserDTO data) {
        String token = userService.login(data);
        return ResponseEntity.ok(new UserLoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDTO> register(@RequestBody @Validated UserRegisterDTO data) {
        boolean isRegistered = userService.register(data);
        if (isRegistered) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<AuthenticatedUserDTO> getAuthenticatedUser(@AuthenticationPrincipal User authenticatedUser) {
        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        AuthenticatedUserDTO userDto = userService.getAuthenticatedUser(authenticatedUser);
        return ResponseEntity.ok(userDto);
    }
}