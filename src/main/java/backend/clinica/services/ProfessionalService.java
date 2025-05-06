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
import backend.clinica.dto.UserRegisterDTO;
import backend.clinica.entities.Professional;
import backend.clinica.entities.User;
import backend.clinica.enums.UserRole;
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
	
	@Autowired
	UserService userService;
	
	
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
		
		// Verifica se o usuário já existe
	    User existingUser = userRepository.findByLogin(dtoProfessional.getEmail());
	    if (existingUser != null) {
	        throw new ResourceNotFoundException("Usuário com esse e-mail já existe.");
	    }
		
		//criar user		
		UserRegisterDTO userRegisterDTO = new UserRegisterDTO(dtoProfessional.getEmail(), "123456789", UserRole.PROFESSIONAL);
		userService.register(userRegisterDTO);
		
		//se criou puxar dados 
		User newUserProfessional = userRepository.findByLogin(dtoProfessional.getEmail());
		
		
		//se deu certo a criação do user, cadastrar profisisonal associando ao user criado
		Professional  professionalEntity = copyDtoProfessional(new Professional(), dtoProfessional);
		professionalEntity.setUser(newUserProfessional);
		professionalEntity = professionalRepository.save(professionalEntity);
		
		return new ProfessionalDTO(professionalEntity);
	}	

	@Transactional
	public ProfessionalDTO updateRegistryProfessional(Long id, ProfessionalDTO professionalDTO) {
		try {
			Professional professionalEntity = professionalRepository.getReferenceById(id);
			professionalEntity = copyDtoProfessional(professionalEntity, professionalDTO);
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
	
	public Professional copyDtoProfessional(Professional professionalEntity, ProfessionalDTO professionalDTO) {
		professionalEntity.setName(professionalDTO.getName());
		professionalEntity.setSpecialty(professionalDTO.getSpecialty());
		professionalEntity.setContact(professionalDTO.getContact());
		professionalEntity.setEmail(professionalDTO.getEmail());
		professionalEntity.setRegistry(professionalDTO.getRegistry());
		return professionalEntity;
	}
	
}
