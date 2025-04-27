package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
}
