package backend.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Scheduling;
import backend.clinica.repositories.SchedulingRepository;

@Service
public class SchedulingService {
	
	@Autowired
	SchedulingRepository schedulingRepository;
	
	public List<Scheduling> findAllScheduling() {
		return schedulingRepository.findAll();
	}
	
	public Scheduling registryScheduling(Scheduling registry) {		
		return schedulingRepository.save(registry);
	}
	
	public void deleteScheduling(Long id) {
		schedulingRepository.deleteById(id);
	}

}
