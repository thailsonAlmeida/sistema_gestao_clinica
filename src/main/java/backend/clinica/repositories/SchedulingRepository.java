package backend.clinica.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
	Page<Scheduling> findByDateHourBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
	Page<Scheduling> findByDateHour(LocalDate dateHour, Pageable pageable);

}
