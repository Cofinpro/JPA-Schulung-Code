package de.cofinpro.training.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.cofinpro.training.jpa.model.Book;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class PersistenceUnitTest {

    @Test
    @Tag("exercise")
    void test() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bookstore");

        // Assert that Book.class has been mapped
        try {
            emf.getMetamodel().entity(Book.class);
        } catch (IllegalArgumentException e) {
            fail("Book is not an entity", e);
        }
    }

}
