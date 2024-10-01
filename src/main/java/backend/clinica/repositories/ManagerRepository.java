package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
