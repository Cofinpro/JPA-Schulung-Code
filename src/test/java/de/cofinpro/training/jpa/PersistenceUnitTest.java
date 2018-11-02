package de.cofinpro.training.jpa;

import de.cofinpro.training.jpa.model.Author;
import de.cofinpro.training.jpa.model.Book;
import de.cofinpro.training.jpa.model.ISBN;
import de.cofinpro.training.jpa.model.Publisher;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Metamodel;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class PersistenceUnitTest {

    @Test
    public void testMapping() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bookstore");
        assertThat(emf.isOpen(), is(true));

        Metamodel metaModel = emf.getMetamodel();
        assertThat(metaModel.entity(Author.class), notNullValue());
        assertThat(metaModel.entity(ISBN.class), notNullValue());
        assertThat(metaModel.entity(Book.class), notNullValue());
        assertThat(metaModel.entity(Publisher.class), notNullValue());
    }
}
