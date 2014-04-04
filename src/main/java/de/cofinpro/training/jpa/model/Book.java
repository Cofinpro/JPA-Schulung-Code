package de.cofinpro.training.jpa.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Basic(optional=false)
	private String title;
	
	@OneToOne(cascade=CascadeType.ALL, optional=true)
	private ISBN isbn;
	
	@ManyToMany(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Author> authors = new ArrayList<Author>();
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, optional=false)
	private Publisher publisher;
	
	private int pages;
	
	private double price;
	
	@Temporal(TemporalType.DATE)
	private Date published;
	
	
	/**
	 * Default constructor for persistence
	 */
	public Book(){
		
	}
	
	public Book(String title) {
		super();
		this.title = title;
	}


	/**
	 * @return the books authors as unmodifiable collection.
	 */
	public List<Author> getAuthors() {
		return Collections.unmodifiableList(authors);
	}
	
	/**
	 * Add an author at a specific position to the author list.
	 */
	public void addAuthor(int position, Author author){
		if (!authors.contains(author)){
			authors.add(position, author);
		}
	}
	
	public void addAuthor(Author author){
		if (!authors.contains(author)){
			authors.add(author);
		}
	}
	
	public void removeAuthor(Author author){
		authors.remove(author);
	}
	
	/**
	 * Delete all authors from the author list
	 */
	public void clearAuthors(){
		authors.clear();
	}

	public Long getId() {
		return id;
	}
	
	public ISBN getIsbn() {
		return isbn;
	}
	
	public int getPages() {
		return pages;
	}
	
	public double getPrice() {
		return price;
	}

	public Date getPublished() {
		return published;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setIsbn(ISBN isbn) {
		this.isbn = isbn;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
		publisher.addBook(this);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book " + id + ": " + title;
	}
	
}
