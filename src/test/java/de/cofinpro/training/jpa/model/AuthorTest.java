package de.cofinpro.training.jpa.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorTest {

    @Test
    void testAddingBook() {
        Author author = new Author("Dickens", "Charles");
        Book book = new Book("The Raven");
        author.addBook(book);

        assertThat(book.getAuthors(), hasItem(author));
        assertThat(author.getBooks(), hasItem(book));
    }

    @Test
    void testProtectedBookCollection() {
        Author author = new Author("Dickens", "Charles");
        Book book = new Book("The Raven");
        assertThrows(UnsupportedOperationException.class, () ->
                author.getBooks().add(book)
        );
    }
}
