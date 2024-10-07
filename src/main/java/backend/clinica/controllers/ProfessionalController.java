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

import backend.clinica.entities.Professional;
import backend.clinica.services.ProfessionalService;


@RestController
@RequestMapping("/profissionais")
public class ProfessionalController {
	
	@Autowired
	private ProfessionalService professionalService;
		
	@GetMapping
	public List<Professional> findAllProfessional(){
		return professionalService.findAllProfessional();
	}	
	
	@PostMapping
	public Professional registryProfessional(@RequestBody Professional registry) {
		return professionalService.registryProfessional(registry);
	}	 
	
	@DeleteMapping("/{id}")
	public void deleteProfessional(@PathVariable Long id) {
		professionalService.deleteProfessional(id);
	}

}
