package de.cofinpro.training.jpa.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class BookTest {

    @Test
    void constructorTest() {
        String title = "Alice in Wonderland";
        Book book = new Book(title);
        assertThat(book.getTitle(), is(equalTo(title)));
    }

    @Test
    @Tag("exercise")
    public void testAddingAuthor() {
        // Create a book and add an author to it
        fail("Not implemented yet");
    }

}
