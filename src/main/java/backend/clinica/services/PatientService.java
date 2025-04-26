package backend.clinica.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.clinica.dto.PatientDTO;
import backend.clinica.entities.Patient;
import backend.clinica.repositories.PatientRepository;
import backend.clinica.services.exceptions.DataBaseException;
import backend.clinica.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Transactional(readOnly = true)
	public Page<PatientDTO> findAllPatientsPaged(String name, Pageable pageable) {
		Page<Patient> listPatient = patientRepository.searchByName(name, pageable);
		return listPatient.map(x -> new PatientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public PatientDTO findByIdPatient(Long id) {
		Optional<Patient> patientObj = patientRepository.findById(id);
		Patient patientEntity = patientObj.orElseThrow(() -> new ResourceNotFoundException("Paciente inexistente"));
		return new PatientDTO(patientEntity, patientEntity.getReportHistory());
	}
	
	@Transactional(readOnly = true)
	public PatientDTO registryPatient(PatientDTO patientDTO) {	
		Patient patientEntity = new Patient();
		patientEntity = copyDtoPatient(patientEntity, patientDTO);
		patientEntity = patientRepository.save(patientEntity);		
		return new PatientDTO(patientEntity);
	}	

	@Transactional
	public PatientDTO updateRegistryPatient(Long id, PatientDTO patientDTO) {
		try {
			Patient patientEntity = patientRepository.getReferenceById(id);
			patientEntity = copyDtoPatient(patientEntity, patientDTO);
			patientEntity = patientRepository.save(patientEntity);
			return new PatientDTO(patientEntity);
		
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID do paciente inexistente: " + id);
		}
	}
	
	public void deletePatient(Long idPatient) {
		try {
			patientRepository.deleteById(idPatient);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID do paciente inexistente: " + idPatient);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
	}
	
	public Patient copyDtoPatient(Patient patientEntity, PatientDTO patientDTO) {
		patientEntity.setName(patientDTO.getName());
		patientEntity.setAddress(patientDTO.getAddress());
		patientEntity.setContact(patientDTO.getContact());
		patientEntity.setBirthDay(patientDTO.getBirthDay());
		patientEntity.setEmail(patientDTO.getEmail());
		return patientEntity;
	}

}
