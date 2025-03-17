package backend.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.clinica.dto.UserRegisterDTO;
import backend.clinica.entities.User;
import backend.clinica.repositories.UserRepository;
import backend.clinica.configs.TokenService;
import backend.clinica.dto.UserDTO;
import backend.clinica.dto.UserLoginResponseDTO;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenSerive;
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponseDTO> login(@RequestBody @Validated UserDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = authenticationManager.authenticate(usernamePassword);
		
		var token = tokenSerive.generateToken((User)auth.getPrincipal());		
		
		return ResponseEntity.ok(new UserLoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Validated UserRegisterDTO data) {
		if(this.userRepository.findByLogin(data.login()) != null) 
			return ResponseEntity.badRequest().build();
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());
		
		
		this.userRepository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
	
}
