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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<ProfessionalDTO>> findAllProfessionalPaged(
			@RequestParam(name="name", defaultValue = "") String name,
			Pageable pageable){	
		Page<ProfessionalDTO> listProfissionals = professionalService.findAllProfessionalPaged(name, pageable);
		return ResponseEntity.ok().body(listProfissionals);
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProfessionalDTO> findByIdProfessional(@PathVariable Long id){
		ProfessionalDTO professionalDTO = professionalService.findByIdProfessional(id);
		return ResponseEntity.ok().body(professionalDTO);
	}
	
	@PostMapping
	public ResponseEntity<ProfessionalDTO> registryProfessional(@RequestBody ProfessionalDTO professionalDTO) {
		professionalDTO = professionalService.registryProfessional(professionalDTO);
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(professionalDTO.getId())
				.toUri();
		return ResponseEntity.created(url).body(professionalDTO);
	} 
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProfessionalDTO> updateRegistryProfessional(@PathVariable Long id, @RequestBody ProfessionalDTO professionalDTO) {
		professionalDTO = professionalService.updateRegistryProfessional(id, professionalDTO);		
		return ResponseEntity.ok().body(professionalDTO);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRegistryProfessional(@PathVariable Long id) {
		professionalService.deleteProfessional(id);		
		return ResponseEntity.noContent().build();
	} 

}
