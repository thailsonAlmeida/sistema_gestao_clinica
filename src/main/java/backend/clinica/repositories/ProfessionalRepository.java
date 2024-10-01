package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long>  {

}
