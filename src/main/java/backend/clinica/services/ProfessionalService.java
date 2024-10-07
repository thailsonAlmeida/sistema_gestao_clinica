package backend.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Professional;
import backend.clinica.repositories.ProfessionalRepository;

@Service
public class ProfessionalService {
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	public List<Professional> findAllProfessional() {
		return professionalRepository.findAll();
	}
	
	public Professional registryProfessional(Professional registry) {
		return professionalRepository.save(registry);
	}
	
	public void deleteProfessional(Long id) {
		professionalRepository.deleteById(id);
	}
	
	
}
