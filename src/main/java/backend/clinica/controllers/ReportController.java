package backend.clinica.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import backend.clinica.dto.ReportDTO;
import backend.clinica.services.ReportService;

@RestController
@RequestMapping(value = "/relatorios")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public ResponseEntity<ReportDTO> registryReport(@RequestBody ReportDTO reportDTO) {
		reportDTO = reportService.registryReport(reportDTO);
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(reportDTO.getId())
				.toUri();
		return ResponseEntity.created(url).body(reportDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ReportDTO> registryReport(@PathVariable Long id, @RequestBody ReportDTO reportDTO) {
		reportDTO = reportService.updateRegistryReport(id, reportDTO);
		return ResponseEntity.ok().body(reportDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
		reportService.deleteReport(id);
		return ResponseEntity.noContent().build();
	}
}
