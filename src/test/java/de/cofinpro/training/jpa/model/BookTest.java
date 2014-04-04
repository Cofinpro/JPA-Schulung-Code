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
		// Create a book and add an author to it
		fail("Not implemented yet");
		
	}

}
