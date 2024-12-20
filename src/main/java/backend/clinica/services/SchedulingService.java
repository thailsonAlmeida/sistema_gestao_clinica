package backend.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.clinica.dto.PatientDTO;
import backend.clinica.dto.ProfessionalDTO;
import backend.clinica.dto.SchedulingDTO;
import backend.clinica.entities.Patient;
import backend.clinica.entities.Professional;
import backend.clinica.entities.Scheduling;
import backend.clinica.repositories.PatientRepository;
import backend.clinica.repositories.ProfessionalRepository;
import backend.clinica.repositories.SchedulingRepository;
import backend.clinica.services.exceptions.DataBaseException;
import backend.clinica.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;


@Service
public class SchedulingService {
	
	@Autowired
	SchedulingRepository schedulingRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	@Transactional(readOnly = true)
	public Page<SchedulingDTO> findAllSchedulingPaged(Pageable pageable) {
		Page<Scheduling> schedulingPage = schedulingRepository.findAll(pageable);
		
		Page<SchedulingDTO> schedulingDTOPage = schedulingPage.map(scheduling -> {
			
	        SchedulingDTO schedulingDTO = new SchedulingDTO();
	        
	        schedulingDTO.setId(scheduling.getId());
	        schedulingDTO.setDateHour(scheduling.getDateHour());
	        schedulingDTO.setConfirmed(scheduling.isConfirmed());
	        schedulingDTO.setPresent(scheduling.isPresent());
	        
	        schedulingDTO.setProfessional(
	        		new ProfessionalDTO(
	        				scheduling.getProfessional().getId(),
	        				scheduling.getProfessional().getName(),
	        				scheduling.getProfessional().getSpecialty(),
	        				scheduling.getProfessional().getContact()
	        ));
	        schedulingDTO.setPatient(
	        		new PatientDTO(
	        				scheduling.getPatient().getId(),
	        				scheduling.getPatient().getName(),
	        				scheduling.getPatient().getAddress(),
	        				scheduling.getPatient().getContact(),
	        				scheduling.getPatient().getBirthDay().toString()
	        ));
	        return schedulingDTO;
	    });
		
		return schedulingDTOPage;
	}
	
	@Transactional(readOnly = true)
	public SchedulingDTO registryScheduling(SchedulingDTO schedulingDTO) {
		Scheduling schedulingEntity = new Scheduling();
		Patient patientEntity = new Patient();		
		Professional professionalEntity = new Professional();
		
		patientEntity.setId(schedulingDTO.getPatient().getId());
		professionalEntity.setId(schedulingDTO.getProfessional().getId());
		schedulingEntity.setConfirmed(false);
		schedulingEntity.setPresent(false);
		schedulingEntity.setDateHour(schedulingDTO.getDateHour());
		schedulingEntity.setProfessional(professionalEntity);
		schedulingEntity.setPatient(patientEntity);
		schedulingEntity = schedulingRepository.save(schedulingEntity);
		return new SchedulingDTO(schedulingEntity);
	}
	
	@Transactional
	public SchedulingDTO updateRegistryScheduling(Long id, SchedulingDTO schedulingDTO) {
		try {	
			Scheduling schedulingEntity = schedulingRepository.getReferenceById(id);			
			schedulingEntity.setDateHour(schedulingDTO.getDateHour());
			schedulingEntity.setConfirmed(schedulingDTO.isConfirmed());
			schedulingEntity.setPresent(schedulingDTO.isPresent());
			schedulingEntity = schedulingRepository.save(schedulingEntity);
			return new SchedulingDTO(schedulingEntity);
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID do agendamento inexistente: " + id);
		}
	}
	
	public void deleteScheduling(Long id) {
		try {
			schedulingRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID do agendamento inexistente: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
	}

}
