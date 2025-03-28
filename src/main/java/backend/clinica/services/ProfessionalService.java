package backend.clinica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.clinica.dto.ProfessionalDTO;
import backend.clinica.entities.Professional;
import backend.clinica.entities.User;
import backend.clinica.repositories.ProfessionalRepository;
import backend.clinica.repositories.UserRepository;
import backend.clinica.services.exceptions.DataBaseException;
import backend.clinica.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class ProfessionalService {
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<ProfessionalDTO> findAllProfessionalPaged(String name, Pageable pageable) {
		Page<Professional> listProfessional = professionalRepository.searchByName(name, pageable);		
		return listProfessional.map(x -> new ProfessionalDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ProfessionalDTO findByIdProfessional(Long id){
		Optional<Professional> prefessionalObj = professionalRepository.findById(id);
		Professional professionalEntity = prefessionalObj.orElseThrow(() -> new ResourceNotFoundException("Profissional inexistente"));
		User user = new User();
		user.setLogin(professionalEntity.getUser().getLogin());
		user.setId(professionalEntity.getUser().getId());
		user.setRole(professionalEntity.getUser().getRole());
		return new ProfessionalDTO(professionalEntity, professionalEntity.getSchedulings(), user);
	}
	
	@Transactional(readOnly = true)
	public ProfessionalDTO registryProfessional(ProfessionalDTO dtoProfessional) {
		Professional professionalEntity = new Professional();
		professionalEntity.setName(dtoProfessional.getName());
		professionalEntity.setSpecialty(dtoProfessional.getSpecialty());
		professionalEntity.setContact(dtoProfessional.getContact());
		professionalEntity = professionalRepository.save(professionalEntity);
		return new ProfessionalDTO(professionalEntity);
	}	

	@Transactional
	public ProfessionalDTO updateRegistryProfessional(Long id, ProfessionalDTO professionalDTO) {
		try {
			Professional professionalEntity = professionalRepository.getReferenceById(id);
			professionalEntity.setName(professionalDTO.getName());
			professionalEntity.setSpecialty(professionalDTO.getSpecialty());
			professionalEntity.setContact(professionalDTO.getContact());	
			professionalEntity = professionalRepository.save(professionalEntity);
			return new ProfessionalDTO(professionalEntity);		
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID do profissional inexistente: " + id);
		}		
	}
	
	public void deleteProfessional(Long id) {
		try {
			professionalRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID do profissional inexistente: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
	}
	
}
