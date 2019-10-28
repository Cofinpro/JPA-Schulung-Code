package de.cofinpro.training.jpa;

import de.cofinpro.training.jpa.model.Author;
import de.cofinpro.training.jpa.model.Book;
import de.cofinpro.training.jpa.model.ISBN;
import de.cofinpro.training.jpa.model.Publisher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Metamodel;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class PersistenceUnitTest {

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void initEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("Bookstore");
    }

    @BeforeEach
    void initEntityManager() {
        em = emf.createEntityManager();
    }

    @Test
    void testMapping() {
        assertThat(emf.isOpen(), is(true));

        Metamodel metaModel = emf.getMetamodel();
        assertThat(metaModel.entity(Author.class), notNullValue());
        assertThat(metaModel.entity(ISBN.class), notNullValue());
        assertThat(metaModel.entity(Book.class), notNullValue());
        assertThat(metaModel.entity(Publisher.class), notNullValue());
    }

    //
    // Try to make these tests pass.
    //

    @Test
    @Tag("exercise")
    void testPersistAuthor() {
        // This test is broken. Find out what's missing

        Author author = new Author("Verne", "Jules");
        em.persist(author);

        assertThat(author.getId(), is(notNullValue()));
        em.clear();

        assertThat(em.find(Author.class, author.getId()), is(notNullValue()));
    }

    @Test
    @Tag("exercise")
    void testUpdateAuthor() {
        Author author = new Author("Clancy", "Tom");
        // save it

        assertThat(author.getId(), is(notNullValue()));
        //oops
        author.setLastName("Hanks");
        // save it again.


        // reload the author, discarding all local changes
        em.refresh(author);
        assertThat(author.getLastName(), is(equalTo("Clancy")));
    }

    @Test
    @Tag("exercise")
    void testRollback() {
        // Make a change and then roll it back. Make sure the change has been discarded
        fail("Not implemented yet.");
    }

    @Test
    @Tag("exercise")
    void testSavingAuthorWithBook() {
        Author author = new Author("Verne", "Jules");
        Book book = new Book("Around the world in 80 days");
        book.setPublisher(new Publisher("NiceBooks Ltd."));
        author.addBook(book);

        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();

        em.refresh(author);
        //What went wrong here?
        assertThat(author.getBooks(), hasItem(book));
    }
}
