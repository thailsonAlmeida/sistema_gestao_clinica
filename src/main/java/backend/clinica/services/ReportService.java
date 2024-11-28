package backend.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.clinica.dto.ReportDTO;
import backend.clinica.entities.Patient;
import backend.clinica.entities.Professional;
import backend.clinica.entities.Report;
import backend.clinica.repositories.ReportRepository;
import backend.clinica.services.exceptions.DataBaseException;
import backend.clinica.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Transactional(readOnly = true)
	public ReportDTO registryReport(ReportDTO reportDTO) {
		Report reportEntity = new Report();
		Patient patientEntity = new Patient();		
		Professional professionalEntity = new Professional();
		
		patientEntity.setId(reportDTO.getPatient().getId());
		professionalEntity.setId(reportDTO.getProfessional().getId());
		
		reportEntity.setDateReport(reportDTO.getDateReport());
		reportEntity.setDescription(reportDTO.getDescription());
		reportEntity.setPatient(patientEntity);
		reportEntity.setProfessional(professionalEntity);
		reportEntity = reportRepository.save(reportEntity);
		return new ReportDTO(reportEntity);
	}
	
	@Transactional
	public ReportDTO updateRegistryReport(Long id, ReportDTO reportDTO) {
		try {
			Report reportEntity = reportRepository.getReferenceById(id);
			reportEntity.setDateReport(reportDTO.getDateReport());
			reportEntity.setDescription(reportDTO.getDescription());
			reportEntity = reportRepository.save(reportEntity);
			return new ReportDTO(reportEntity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID do relatório inexistente: " + id);
		}
	}
	
	public void deleteReport(Long id) {
		try {
			reportRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID do relatório inexistente: " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Violação de integridade");
		}
	}
	
	

}
