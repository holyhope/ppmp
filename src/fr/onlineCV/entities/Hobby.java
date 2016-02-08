package fr.onlineCV.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Hobby")
public class Hobby {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long      id;
    
    @NotNull( message = "{label.notNull}" )
    private String label;

    @NotNull( message = "{description.notNull}" )
    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
