package backend.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.clinica.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
