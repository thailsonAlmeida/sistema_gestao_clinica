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

import backend.clinica.entities.Patient;
import backend.clinica.services.PatientService;

@RestController
@RequestMapping("/pacientes")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public List<Patient> findAllPatient(){
		return patientService.findAllPatients();
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
