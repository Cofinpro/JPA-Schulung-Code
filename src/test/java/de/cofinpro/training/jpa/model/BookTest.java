package de.cofinpro.training.jpa.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class BookTest {

	@Test
	public void constructorTest() {
		String title = "Alice in Wonderland";
		Book book = new Book(title);
		assertThat(book.getTitle(), is(equalTo(title)));
	}
	
	public void testAddingAuthor(){
		Author author = new Author("Dickens", "Charles");
		Book book = new Book("The Raven");
		book.addAuthor(author);
		
		assertThat(book.getAuthors(), hasItem(author));
		assertThat(author.getBooks(), hasItem(book));		
	}

}
