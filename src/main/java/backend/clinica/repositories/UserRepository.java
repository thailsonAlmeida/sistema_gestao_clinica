package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import backend.clinica.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	UserDetails findByLogin(String login);
}
