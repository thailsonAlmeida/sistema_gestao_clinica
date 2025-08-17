package backend.clinica.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.clinica.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Query("SELECT obj FROM Patient obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Patient> searchByName(String name, Pageable pageable);
	
	Patient findByEmail(String email);
}
