package backend.clinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.clinica.entities.Report;
import backend.clinica.services.ReportService;

@RestController
@RequestMapping(value = "/relatorios")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public Report registryReport(@RequestBody Report registry) {
		return reportService.registryReport(registry);
	}//registrar relatorio para o paciente
	
	@DeleteMapping("/{id}")
	public void deleteReport(@PathVariable Long id) {
		reportService.deleteReport(id);
	}//deletar relatório do paciente
}
