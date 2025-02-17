package backend.clinica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import backend.clinica.entities.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long>  {
	@Query("SELECT obj FROM Professional obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Professional> searchByName(String name, Pageable pageable);
}
