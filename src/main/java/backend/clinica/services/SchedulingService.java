package backend.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Scheduling;
import backend.clinica.repositories.SchedulingRepository;

@Service
public class SchedulingService {
	
	@Autowired
	SchedulingRepository schedulingRepository;
	
	public Scheduling schedulingDataHour(Scheduling dataHour) {		
		return schedulingRepository.save(dataHour);
	}
	
	public void deleteScheduling(Long id) {
		schedulingRepository.deleteById(id);
	}

}
