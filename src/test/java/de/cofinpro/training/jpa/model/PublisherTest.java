package de.cofinpro.training.jpa.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PublisherTest {

    @Test
    void testAddingBooks() {
        Book book = new Book("The Raven");
        Publisher publisher = new Publisher("Nice Books Ltd.");
        publisher.addBook(book);

        assertThat(publisher.getBooks(), hasItem(book));
        assertThat(book.getPublisher(), is(equalTo(publisher)));
    }

}
