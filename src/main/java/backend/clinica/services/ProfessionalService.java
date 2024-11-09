package backend.clinica.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.dto.ProfessionalDTO;
import backend.clinica.entities.Professional;
import backend.clinica.repositories.ProfessionalRepository;
import jakarta.transaction.Transactional;

@Service
public class ProfessionalService {
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	@Transactional
	public List<ProfessionalDTO> findAllProfessional() {
		List<Professional> listProfessional = professionalRepository.findAll();		
		return listProfessional.stream().map(x -> new ProfessionalDTO(x)).collect(Collectors.toList());
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
