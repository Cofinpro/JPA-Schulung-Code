package de.cofinpro.training.jpa.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class BookTest {

    @Test
    void constructorTest() {
        String title = "Alice in Wonderland";
        Book book = new Book(title);
        assertThat(book.getTitle(), is(equalTo(title)));
    }

}
