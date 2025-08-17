package backend.clinica.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.clinica.entities.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    Page<Scheduling> findByDateHourBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    // Query ajustada para PostgreSQL (sem parâmetros nulos)
    @Query("SELECT s FROM Scheduling s WHERE s.dateHour >= :startDate AND s.dateHour <= :endDate")
    Page<Scheduling> findByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    boolean existsByProfessionalIdAndDateHour(Long professionalId, LocalDateTime dateHour);
}
