package backend.clinica.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class JwtConfig {

    @Value("${api.security.token.secret}")
    private String jwtSecret;

    @PostConstruct
    public void init() {
        System.out.println("JWT Secret: " + jwtSecret);
    }
}