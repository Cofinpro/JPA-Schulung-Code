package de.coinor.training.jpa.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@ManyToMany(mappedBy="authors")
	private Collection<Book> books = new HashSet<Book>();
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	public Author() {
		super();
	}

	public Collection<Book> getBooks() {
		return Collections.unmodifiableCollection(books);
	}
	
	public void addBook(Book book){
		if (this.books.contains(book)) return;
		
		books.add(book);
		book.addAuthor(this);
	}
	
	public void removeBook(Book book){
		books.remove(book);
		book.removeAuthor(this);
	}
	
	
	public Author(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}


	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
