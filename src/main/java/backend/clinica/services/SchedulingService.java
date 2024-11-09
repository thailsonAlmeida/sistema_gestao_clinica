package backend.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Scheduling;
import backend.clinica.repositories.PatientRepository;
import backend.clinica.repositories.ProfessionalRepository;
import backend.clinica.repositories.SchedulingRepository;

@Service
public class SchedulingService {
	
	@Autowired
	SchedulingRepository schedulingRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ProfessionalRepository professionalRepository;
	
	public List<Scheduling> findAllScheduling() {
		return schedulingRepository.findAll();
	}//listar todos os agendamentos
	
	public Scheduling registryScheduling(Scheduling registry) {	
		return schedulingRepository.save(registry);
	}//registrar agendamento
	
	public void deleteScheduling(Long id) {
		schedulingRepository.deleteById(id);
	}//deletar agendamento

}
