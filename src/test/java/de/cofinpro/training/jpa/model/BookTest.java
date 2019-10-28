package de.cofinpro.training.jpa.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class BookTest {

    @Test
    void constructorTest() {
        String title = "Alice in Wonderland";
        Book book = new Book(title);
        assertThat(book.getTitle(), is(equalTo(title)));
    }

    @Test
    void testAddingAuthor() {
        Author author = new Author("Dickens", "Charles");
        Book book = new Book("The Raven");
        book.addAuthor(author);

        assertThat(book.getAuthors(), hasItem(author));
        assertThat(author.getBooks(), hasItem(book));
    }

}
