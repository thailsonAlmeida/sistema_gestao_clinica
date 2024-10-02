package backend.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Manager;
import backend.clinica.repositories.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	ManagerRepository managerRepository;
	
	public List<Manager> findAllManagers() {
		return managerRepository.findAll();
	}
	
	public Manager registryManager(Manager registry) {
		return managerRepository.save(registry);
	}
	
	public void deleteManager(Long id) {
		managerRepository.deleteById(id);
	}
}
