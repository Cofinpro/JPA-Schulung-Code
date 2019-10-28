package de.cofinpro.training.jpa;

import de.cofinpro.training.jpa.model.Author;
import de.cofinpro.training.jpa.model.Book;
import de.cofinpro.training.jpa.model.ISBN;
import de.cofinpro.training.jpa.model.Publisher;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.metamodel.Metamodel;
import javax.validation.ValidationException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testValidationIntegration() {
        Author author = new Author();
        final RollbackException rollbackException = assertThrows(RollbackException.class, () -> {
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        });
        assertThat(rollbackException.getCause(), is(instanceOf(ValidationException.class)));
    }

    @Test
    void testPersistAuthor() {
        // This test is broken. Find out what's missing

        Author author = new Author("Verne", "Jules");
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();

        assertThat(author.getId(), is(notNullValue()));
        em.clear();

        assertThat(em.find(Author.class, author.getId()), is(notNullValue()));
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author("Hanks", "Tom");
        // save it
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();

        assertThat(author.getId(), is(notNullValue()));

        //oops
        em.getTransaction().begin();
        author = em.merge(author);
        author.setLastName("Clancy");
        em.getTransaction().commit();
        // save it again

        // reload the author, discarding all local changes
        em.refresh(author);
        assertThat(author.getLastName(), is(equalTo("Clancy")));
    }

    @Test
    void testRollback() {
        // Make a change and then roll it back. Make sure the change has been discarded
        Author author = new Author("Hanks", "Tom");
        // save it
        em.getTransaction().begin();
        em.persist(author);
        em.getTransaction().commit();

        assertThat(author.getId(), is(notNullValue()));

        //oops
        em.getTransaction().begin();
        author = em.merge(author);
        author.setLastName("Clancy");
        em.getTransaction().rollback();
        // save it again

        // reload the author, discarding all local changes
        author = em.merge(author);
        em.refresh(author);
        assertThat(author.getLastName(), is(equalTo("Hanks")));
    }

    @Test
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
