package de.cofinpro.training.jpa.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class PublisherTest {

    @Test
    public void testAddingBooks() {
        Book book = new Book("The Raven");
        Publisher publisher = new Publisher("Nice Books Ltd.");
        publisher.addBook(book);

        assertThat(publisher.getBooks(), hasItem(book));
        assertThat(book.getPublisher(), is(equalTo(publisher)));
    }

}
