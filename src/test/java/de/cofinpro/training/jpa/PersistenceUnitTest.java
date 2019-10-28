package de.cofinpro.training.jpa;

import de.cofinpro.training.jpa.model.Author;
import de.cofinpro.training.jpa.model.Book;
import de.cofinpro.training.jpa.model.ISBN;
import de.cofinpro.training.jpa.model.Publisher;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.Metamodel;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class PersistenceUnitTest {

    @Test
    @Tag("exercise")
    void testMapping() {
        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("Bookstore");
            assertThat(emf.isOpen(), is(true));
        } catch (PersistenceException e) {
            fail("Something is wrong with your mapping. Look at the log and try to find out what it is", e);
        }

        Metamodel metaModel = emf.getMetamodel();
        assertThat(metaModel.entity(Author.class), notNullValue());
        assertThat(metaModel.entity(ISBN.class), notNullValue());
        assertThat(metaModel.entity(Book.class), notNullValue());
        assertThat(metaModel.entity(Publisher.class), notNullValue());
    }
}
