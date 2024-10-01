package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

}
