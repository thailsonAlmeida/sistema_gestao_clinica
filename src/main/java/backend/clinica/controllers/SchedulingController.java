package backend.clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.clinica.entities.Scheduling;
import backend.clinica.services.SchedulingService;

@RestController
@RequestMapping(value = "/agendamentos")
public class SchedulingController {
	
	@Autowired
	private SchedulingService schedulingService;
	
	@GetMapping
	public ResponseEntity<List<Scheduling>> findAllSchedulings(){
		List<Scheduling> listScheduling = schedulingService.findAllScheduling();
		return ResponseEntity.ok().body(listScheduling);
	}	
	
	@PostMapping
	public Scheduling registryScheduling(@RequestBody Scheduling registry) {
		return schedulingService.registryScheduling(registry);
	}	 
	
	@DeleteMapping("/{id}")
	public void deleteScheduling(@PathVariable Long id) {
		schedulingService.deleteScheduling(id);
	}

}
