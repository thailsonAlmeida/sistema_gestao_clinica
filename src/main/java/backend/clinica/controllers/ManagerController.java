package backend.clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.clinica.entities.Manager;
import backend.clinica.services.ManagerService;

@RestController
@RequestMapping("/gestores")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;	
	
	@GetMapping
	public List<Manager> findAllManagers(){
		return managerService.findAllManagers();
	}	
	
	@PostMapping
	public Manager registryManager(@RequestBody Manager registry) {
		return managerService.registryManager(registry);
	}	 
	
	@DeleteMapping("/{id}")
	public void deleteManager(@PathVariable Long id) {
		managerService.deleteManager(id);
	}
	
	
}
