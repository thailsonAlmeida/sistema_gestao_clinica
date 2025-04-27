package backend.clinica.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
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
	private SchedulingRepository schedulingRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private ProfessionalRepository professionalRepository;
	
	@Autowired
	private EmailService emailService;
			
	@Transactional(readOnly = true)
	public Page<SchedulingDTO> findAllSchedulingPaged(Pageable pageable) {
		Page<Scheduling> schedulingPage = schedulingRepository.findAll(pageable);
		
		Page<SchedulingDTO> schedulingDTOPage = schedulingPage.map(scheduling -> {
			
	        SchedulingDTO schedulingDTO = new SchedulingDTO();
	        
	        schedulingDTO.setId(scheduling.getId());
	        schedulingDTO.setDateHour(scheduling.getDateHour());
	        schedulingDTO.setConfirmed(scheduling.isConfirmed());
	        schedulingDTO.setPresent(scheduling.isPresent());
	        schedulingDTO.setCancel(scheduling.isCancel());
	        
	        schedulingDTO.setProfessional(factoryDtoProfessional(scheduling));
	        schedulingDTO.setPatient(factoryDtoPatient(scheduling));
	        return schedulingDTO;
	    });
		
		return schedulingDTOPage;
	}
	
	
	@Transactional(readOnly = true)
	public Page<SchedulingDTO> findAllSchedulingPaged(Pageable pageable, String startDate, String endDate) {
		// Se não for fornecida uma data de início, define como o primeiro dia do mês atual
	    LocalDateTime start = (startDate != null && !startDate.isEmpty()) 
	            ? LocalDateTime.parse(startDate + "T00:00:00") 
	            : LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIDNIGHT);
	    
		    
	    // Se não for fornecida uma data de fim, define como o último dia do mês atual
	    LocalDateTime end = (endDate != null && !endDate.isEmpty()) 
	            ? LocalDateTime.parse(endDate + "T23:59:59") 
	            : LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
		    
		Page<Scheduling> schedulingPage = schedulingRepository.findByDateRange(start, end, pageable);
		
		Page<SchedulingDTO> schedulingDTOPage = schedulingPage.map(scheduling -> {
			
	        SchedulingDTO schedulingDTO = new SchedulingDTO();
	        
	        schedulingDTO.setId(scheduling.getId());
	        schedulingDTO.setDateHour(scheduling.getDateHour());
	        schedulingDTO.setConfirmed(scheduling.isConfirmed());
	        schedulingDTO.setPresent(scheduling.isPresent());
	        schedulingDTO.setCancel(scheduling.isCancel());
	        
	        schedulingDTO.setProfessional(factoryDtoProfessional(scheduling));
	        schedulingDTO.setPatient(factoryDtoPatient(scheduling));
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
		schedulingEntity.setCancel(false);
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
			
			boolean wasConfirmedBefore = schedulingEntity.isConfirmed(); // valor antigo
			
			Patient patientEntity = new Patient();		
			Professional professionalEntity = new Professional();
			patientEntity.setId(schedulingDTO.getPatient().getId());
			professionalEntity.setId(schedulingDTO.getProfessional().getId());
			
			schedulingEntity = copyDtoScheduling(schedulingDTO, schedulingEntity);
			schedulingEntity.setPatient(patientEntity);
			schedulingEntity.setProfessional(professionalEntity);
			schedulingEntity = schedulingRepository.save(schedulingEntity);	
			
			//Observer: detecta mudança de false → true no campo "confirmed"
	        if (!wasConfirmedBefore && schedulingEntity.isConfirmed()) {
	            sendEmailPatient(patientEntity, professionalEntity, schedulingEntity);
	        }
	        
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
	
	@Async
	public void sendEmailPatient(Patient patientEntity, Professional professionalEntity, Scheduling schedulingEntity) {
		// Carrega o paciente e profissional com dados completos
        Patient patient = patientRepository.findById(patientEntity.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        Professional professional = professionalRepository.findById(professionalEntity.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
        
     // Formatador para dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
        String formattedDate = schedulingEntity.getDateHour().format(formatter);

        String subject = "Confirmação de Agendamento";
        String message = String.format(
            "Olá %s,\n\nSeu agendamento com o profissional %s foi confirmado para o dia %s \n\nObrigado por escolher nossa clínica!",
            patient.getName(),
            professional.getName(),
            formattedDate
        );

        // Envia o e-mail (usa o e-mail real do paciente)
        if (patient.getEmail() != null && !patient.getEmail().isEmpty()) {
            emailService.sendConfirmationEmail(patient.getEmail(), subject, message);
        }
	}
	
	
	@Transactional
	public SchedulingDTO confirmScheduling(Long id, SchedulingDTO schedulingDTO) {
	    try {
	        Scheduling schedulingEntity = schedulingRepository.getReferenceById(id);
			
	        Patient patientEntity = patientRepository.findById(schedulingDTO.getPatient().getId())
	        	    .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

	        Professional professionalEntity = professionalRepository.findById(schedulingDTO.getProfessional().getId())
	        	    .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado"));
						
			schedulingEntity.setDateHour(schedulingDTO.getDateHour());
			schedulingEntity.setConfirmed(schedulingDTO.isConfirmed());
			schedulingEntity.setPresent(schedulingDTO.isPresent());
			schedulingEntity.setCancel(schedulingDTO.isCancel());
			schedulingEntity.setPatient(patientEntity);
			schedulingEntity.setProfessional(professionalEntity);

			
	        if (schedulingEntity.isConfirmed()) {
	            schedulingEntity.setConfirmed(true);
	            schedulingRepository.save(schedulingEntity);

	            // Enviar e-mail de confirmação para o paciente
	            String subject = "Confirmação de Agendamento";
	            String message = String.format(
	                "Olá %s,\n\nSeu agendamento com o profissional %s foi realizado com sucesso no dia %s.\n\nObrigado por escolher nossa clínica!",
	                schedulingEntity.getPatient().getName(),
	                schedulingEntity.getProfessional().getName(),
	                schedulingEntity.getDateHour()
	            );

	            emailService.sendConfirmationEmail("a4611ac39c-6e3ffb+1@inbox.mailtrap.io", subject, message);
	        }						

	        return new SchedulingDTO(schedulingEntity);
	    } catch (EntityNotFoundException e) {
	        throw new ResourceNotFoundException("ID do agendamento inexistente: " + id);
	    }
	}
	
	public Scheduling copyDtoScheduling(SchedulingDTO schedulingDTO, Scheduling schedulingEntity) {
		schedulingEntity.setDateHour(schedulingDTO.getDateHour());
		schedulingEntity.setConfirmed(schedulingDTO.isConfirmed());
		schedulingEntity.setPresent(schedulingDTO.isPresent());
		schedulingEntity.setCancel(schedulingDTO.isCancel());		
		return schedulingEntity;
	}
	
	public ProfessionalDTO factoryDtoProfessional(Scheduling scheduling) {
		return new ProfessionalDTO(
				scheduling.getProfessional().getId(),
				scheduling.getProfessional().getName(),
				scheduling.getProfessional().getSpecialty(),
				scheduling.getProfessional().getContact()
		);
	}
	
	public PatientDTO factoryDtoPatient(Scheduling scheduling) {
		return new PatientDTO(
				scheduling.getPatient().getId(),
				scheduling.getPatient().getName(),
				scheduling.getPatient().getAddress(),
				scheduling.getPatient().getContact(),
				scheduling.getPatient().getBirthDay().toString(),
				scheduling.getPatient().getEmail()
		);
	}
}
