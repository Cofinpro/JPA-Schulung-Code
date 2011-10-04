package de.coinor.training.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;

import de.coinor.training.jpa.model.Author;
import de.coinor.training.jpa.model.Book;
import de.coinor.training.jpa.model.ISBN;
import de.coinor.training.jpa.model.Publisher;

public class MappingTest {

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
