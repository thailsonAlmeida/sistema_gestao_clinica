package backend.clinica.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.clinica.dto.ProfessionalDTO;
import backend.clinica.entities.Professional;
import backend.clinica.services.ProfessionalService;


@RestController
@RequestMapping(value = "/profissionais")
public class ProfessionalController {
	
	@Autowired
	private ProfessionalService professionalService;
		
	@GetMapping
	public ResponseEntity<List<ProfessionalDTO>> findAllProfessional(){
		List<ProfessionalDTO> listProfissionals = professionalService.findAllProfessional();
		return ResponseEntity.ok().body(listProfissionals);
	}	
	
	@GetMapping(value = "/{id}")
	public Optional<Professional> findByIdProfessional(@PathVariable Long id){
		return professionalService.findByIdProfessional(id);
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
