package backend.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Patient;
import backend.clinica.repositories.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}
	
	public Patient registryPatient(Patient registry) {
		return patientRepository.save(registry);
	}
	
	public void deletePatient(Long idPatient) {
		patientRepository.deleteById(idPatient);
	}

}
