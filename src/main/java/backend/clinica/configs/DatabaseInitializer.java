package backend.clinica.configs;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
	DatabaseInitializer(JdbcTemplate jdbcTemplate) {
    }

    @PostConstruct
    public void init() {
        
    }
}
