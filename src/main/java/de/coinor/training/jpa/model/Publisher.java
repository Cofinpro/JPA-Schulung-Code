package de.coinor.training.jpa.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publisher {

	@Id
	@GeneratedValue
	private Long id;
	
	@Basic(optional=false)
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="publisher")
	private Collection<Book> books = new HashSet<Book>();
	
	public Publisher() {
		super();
	}

	public Publisher(String name) {
		this();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
	
	

}
