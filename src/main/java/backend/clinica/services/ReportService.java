package backend.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.clinica.entities.Report;
import backend.clinica.repositories.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	public Report registryReport(Report registry) {
		return reportRepository.save(registry);
	}
	
	public void deleteReport(Long id) {
		reportRepository.deleteById(id);
	}
	
	

}
