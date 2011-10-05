package de.coinor.training.jpa.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	public void addBook(Book book){
		if (books.contains(book)) return;
		
		books.add(book);
		book.setPublisher(this);
	}
	
	public void removeBook(Book book){
		if (!book.getPublisher().equals(this)) return;
		
		books.remove(book);
		book.setPublisher(null);
	}
	
	@Override
	public String toString() {
		return "Publisher " + id + ": " + name;
	}
	

}
