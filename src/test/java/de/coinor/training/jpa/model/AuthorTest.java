package de.coinor.training.jpa.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionContaining.*;

import org.junit.Test;

public class AuthorTest {

	@Test
	public void testAddingBook(){
		Author author = new Author("Dickens", "Charles");
		Book book = new Book("The Raven");
		author.addBook(book);
		
		assertThat(book.getAuthors(), hasItem(author));
		assertThat(author.getBooks(), hasItem(book));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testProtectedBookCollection(){
		Author author = new Author("Dickens", "Charles");
		Book book = new Book("The Raven");
		author.getBooks().add(book);
	}
}
