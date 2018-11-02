package de.cofinpro.training.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import de.cofinpro.training.jpa.model.Book;

public class PersistenceUnitTest {

    @Test
    public void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bookstore");

        // Assert that Book.class has been mapped
        emf.getMetamodel().entity(Book.class);
    }

}
