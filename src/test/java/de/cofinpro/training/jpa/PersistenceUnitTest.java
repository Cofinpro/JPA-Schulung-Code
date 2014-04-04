package de.cofinpro.training.jpa;

import de.cofinpro.training.jpa.model.Author;
import de.cofinpro.training.jpa.model.Book;
import de.cofinpro.training.jpa.model.ISBN;
import de.cofinpro.training.jpa.model.Publisher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Metamodel;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class PersistenceUnitTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	
	@BeforeClass
	public static void initEntityManagerFactory(){
		emf = Persistence.createEntityManagerFactory("Bookstore");
	}
	
	@Before
	public void initEntityManager(){
		em = emf.createEntityManager();
	}
	
	@Test
	public void testMapping() throws Exception {
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
	public void testPersistAuthor(){
		// This test is broken. Find out what's missing

		Author author = new Author("Verne", "Jules");
		em.persist(author);

		assertThat(author.getId(), is(notNullValue()));
	}
	
	@Test
	public void testUpdateAuthor(){
		Author author = new Author("Hanks","Tom");
		// save it
		
		assertThat(author.getId(), is(notNullValue()));
		//oops
		author.setLastName("Clancy");
		// save it again.
		
		
		// reload the author, discarding all local changes
		em.refresh(author);
		assertThat(author.getLastName(), is(equalTo("Clancy")));
	}
	
	@Test
	public void testRollback(){
		// Make a change and then roll it back. Make sure the change has been discarded
		fail("Not implemented yet.");
	}
	
	@Test
	public void testSavingAuthorWithBook(){
		Author author = new Author("Verne","Jules");
		Book book = new Book("Around the world in 80 days");
		book.setPublisher(new Publisher("NiceBooks Ltd."));
		author.getBooks().add(book);
	
		em.getTransaction().begin();
		em.persist(author);
		em.persist(book);
		em.getTransaction().commit();
	
		assertThat(book.getId(), is(notNullValue()));
		assertThat(author.getId(), is(notNullValue()));
		
		em.refresh(author);
		//What went wrong here?
		assertThat(author.getBooks(),hasItem(book));
	}
}
