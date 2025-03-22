package backend.clinica.configs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import backend.clinica.entities.User;

@Service
public class TokenService {
    
    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            
            // Obtendo as authorities do usuário como uma string separada por vírgula
            String roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            
            return JWT.create()
                    .withIssuer("clinica")
                    .withSubject(user.getLogin())
                    .withClaim("authorities", roles) // Adiciona as authorities no payload
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }
    
    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            
            return JWT.require(algorithm)
                    .withIssuer("clinica")
                    .build()
                    .verify(token)
                    .getSubject(); // Isso retorna apenas o usuário
            
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
