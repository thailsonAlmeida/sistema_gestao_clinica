package backend.clinica.services;

import java.util.List;
import java.util.Optional;

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
	}//listar todos os pacientes
	
	public Optional<Patient> findByIdPatient(Long id) {
		return patientRepository.findById(id);
	}//exibir paciente
	
	public Patient registryPatient(Patient registry) {		
		return patientRepository.save(registry);
	}//cadastrar paciente	
	
	public void deletePatient(Long idPatient) {
		patientRepository.deleteById(idPatient);
	}//deletar

}
