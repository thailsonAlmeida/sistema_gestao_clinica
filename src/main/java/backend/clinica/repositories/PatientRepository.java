package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
