package backend.clinica.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Scheduling implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

    private LocalDateTime dateHour;
    
    public Scheduling() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateHour() {
		return dateHour;
	}

	public void setDateHour(LocalDateTime dateHour) {
		this.dateHour = dateHour;
	}
    
    

}
