package backend.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.ObjectProvider; // Usando ObjectProvider do Spring
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import backend.clinica.configs.TokenService;
import backend.clinica.dto.AuthenticatedUserDTO;
import backend.clinica.dto.ChangePasswordDTO;
import backend.clinica.dto.UserDTO;
import backend.clinica.dto.UserRegisterDTO;
import backend.clinica.entities.User;
import backend.clinica.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ObjectProvider<AuthenticationManager> authenticationManagerProvider; // Usando ObjectProvider

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public String login(UserDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authenticationManager = authenticationManagerProvider.getObject(); // Pegando o AuthenticationManager
        var auth = authenticationManager.authenticate(usernamePassword); // Agora podemos chamar authenticate
        return tokenService.generateToken((User) auth.getPrincipal());
    }

    public boolean register(UserRegisterDTO data) {
        if (userRepository.findByLogin(data.login()) != null) {
            return false; // Usuário já existe
        }
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        userRepository.save(newUser);
        return true;
    }

    public AuthenticatedUserDTO getAuthenticatedUser(User authenticatedUser) {
        User user = userRepository.findById(authenticatedUser.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new AuthenticatedUserDTO(user);
    }
    
    public boolean changePassword(User authenticatedUser, ChangePasswordDTO data) {
        User user = userRepository.findById(authenticatedUser.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verificar se a senha antiga está correta
        if (!passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
            return false; // Senha antiga não confere
        }

        // Atualizar para a nova senha (codificada)
        String newPasswordEncoded = passwordEncoder.encode(data.getNewPassword());
        user.setPassword(newPasswordEncoded);

        userRepository.save(user);
        return true;
    }
}
