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

import backend.clinica.dto.PatientDTO;
import backend.clinica.services.PatientService;

@RestController
@RequestMapping(value = "/pacientes")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<Page<PatientDTO>> findAllPatientPaged(
			@RequestParam(name="name", defaultValue = "") String name,
			Pageable pageable){		
		Page<PatientDTO> listPatients = patientService.findAllPatientsPaged(name, pageable);
		return ResponseEntity.ok().body(listPatients);
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PatientDTO> findByIdPatient(@PathVariable Long id){
		PatientDTO patientDTO = patientService.findByIdPatient(id);
		return ResponseEntity.ok().body(patientDTO);
	}
	
	@PostMapping
	public ResponseEntity<PatientDTO> registryPatient(@RequestBody PatientDTO patientDTO) {
		patientDTO = patientService.registryPatient(patientDTO);
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(patientDTO.getId())
				.toUri();
		return ResponseEntity.created(url).body(patientDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
		patientDTO = patientService.updateRegistryPatient(id, patientDTO);		
		return ResponseEntity.ok().body(patientDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}

}
