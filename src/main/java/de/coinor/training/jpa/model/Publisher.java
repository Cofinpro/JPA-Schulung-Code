package de.coinor.training.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Publisher {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

}
