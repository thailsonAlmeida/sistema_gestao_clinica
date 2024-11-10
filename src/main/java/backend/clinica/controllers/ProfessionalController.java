package backend.clinica.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import backend.clinica.dto.ProfessionalDTO;
import backend.clinica.services.ProfessionalService;



@RestController
@RequestMapping(value = "/profissionais")
public class ProfessionalController {
	
	@Autowired
	private ProfessionalService professionalService;
		
	@GetMapping
	public ResponseEntity<Page<ProfessionalDTO>> findAllProfessional(Pageable pageable){	
		Page<ProfessionalDTO> listProfissionals = professionalService.findAllProfessionalPaged(pageable);
		return ResponseEntity.ok().body(listProfissionals);
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProfessionalDTO> findByIdProfessional(@PathVariable Long id){
		ProfessionalDTO dtoProfessional = professionalService.findByIdProfessional(id);
		return ResponseEntity.ok().body(dtoProfessional);
	}
	
	@PostMapping
	public ResponseEntity<ProfessionalDTO> registryProfessional(@RequestBody ProfessionalDTO dtoProfessional) {
		dtoProfessional = professionalService.registryProfessional(dtoProfessional);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dtoProfessional.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dtoProfessional);
	} 
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProfessionalDTO> updateRegistryProfessional(@PathVariable Long id, @RequestBody ProfessionalDTO dtoProfessional) {
		dtoProfessional = professionalService.updateRegistryProfessional(id, dtoProfessional);		
		return ResponseEntity.ok().body(dtoProfessional);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRegistryProfessional(@PathVariable Long id) {
		professionalService.deleteProfessional(id);		
		return ResponseEntity.noContent().build();
	} 

}
