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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import backend.clinica.dto.SchedulingDTO;
import backend.clinica.services.SchedulingService;

@RestController
@RequestMapping(value = "/agendamentos")
public class SchedulingController {
	
	@Autowired
	private SchedulingService schedulingService;
	
	@GetMapping
	public ResponseEntity<Page<SchedulingDTO>> findAllSchedulingPaged(Pageable pageable){
		Page<SchedulingDTO> schedulingPage = schedulingService.findAllSchedulingPaged(pageable);		
		return ResponseEntity.ok().body(schedulingPage);
	}	
	
	@PostMapping
	public ResponseEntity<SchedulingDTO> registryScheduling(@RequestBody SchedulingDTO schedulingDTO) {
		schedulingDTO = schedulingService.registryScheduling(schedulingDTO);
		URI url = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(schedulingDTO.getId())
				.toUri();
		return ResponseEntity.created(url).body(schedulingDTO);
	}	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SchedulingDTO> updateRegistryScheduling(@PathVariable Long id, @RequestBody SchedulingDTO schedulingDTO) {
		schedulingDTO = schedulingService.updateRegistryScheduling(id, schedulingDTO);		
		return ResponseEntity.ok().body(schedulingDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteScheduling(@PathVariable Long id) {
		schedulingService.deleteScheduling(id);
		return ResponseEntity.noContent().build();
	}

}
