package backend.clinica.services;

import java.util.List;
import java.util.Optional;

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
	}//listar todos os profissionais
	
	public Optional<Professional> findByIdProfessional(Long id){
		return professionalRepository.findById(id);
	}//exibir profissional
	
	public Professional registryProfessional(Professional registry) {
		return professionalRepository.save(registry);
	}//registrar profissional
	
	public void deleteProfessional(Long id) {
		professionalRepository.deleteById(id);
	}//deletar profissional
	
	
}
