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

import backend.clinica.entities.Patient;
import backend.clinica.services.PatientService;

@RestController
@RequestMapping(value = "/pacientes")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<List<Patient>> findAllPatient(){
		List<Patient> listPatients = patientService.findAllPatients();
		return ResponseEntity.ok().body(listPatients);
	}	
	
	@GetMapping(value = "/{id}")
	public Optional<Patient> findByIdPatient(@PathVariable Long id){
		return patientService.findByIdPatient(id);
	}
	
	@PostMapping
	public Patient registryPatient(@RequestBody Patient registry) {
		return patientService.registryPatient(registry);
	}	 
	
	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
	}

}
