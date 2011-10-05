package de.coinor.training.jpa;

import javax.persistence.EntityManager;

import de.coinor.training.jpa.model.Author;
import de.coinor.training.jpa.model.Book;
import de.coinor.training.jpa.model.Publisher;

public class DemoDataBuilder {
	
	EntityManager entityManager;
	
	public DemoDataBuilder(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void createDemoData(){
		Publisher publisher1 = new Publisher("Nice Books Ltd.");
		Publisher publisher2 = new Publisher("Nerd Stuff Inc.");
		Publisher publisher3 = new Publisher("Books for Business GmbH");
		
		Author tolkin = new Author("Tolkin", "J.R.R.");
		Author dickens = new Author("Dickens","Charles");
		Author slama = new Author("Slama","Dirk");
		Author krafzig = new Author("Krafzig","");
		Author banke = new Author("Banke","");
		
		
		Book book1 = new Book("A Christmas Story");
		book1.addAuthor(dickens);
		book1.setPrice(4.95);
		book1.setPages(150);
		book1.setPublisher(publisher1);
		
		Book book2 = new Book("The Raven");
		book2.addAuthor(dickens);
		book2.setPrice(3.75);
		book2.setPages(15);
		book2.setPublisher(publisher1);
		
		Book book3 = new Book("Enterprise SOA");
		book3.addAuthor(krafzig);
		book3.addAuthor(banke);
		book3.addAuthor(slama);
		book3.setPublisher(publisher3);
		book3.setPrice(45.99);
		book3.setPages(350);
		
		Book book4 = new Book("The Lord of the Rings - Part I");
		book4.addAuthor(tolkin);
		book4.setPublisher(publisher2);
		book4.setPrice(23.95);
		book4.setPages(500);
		
		Book book5 = new Book("The Lord of the Rings - Part II");
		book5.addAuthor(tolkin);
		book5.setPublisher(publisher2);
		book5.setPrice(23.95);
		book5.setPages(500);
		
		Book book6 = new Book("The Lord of the Rings - Part III");
		book6.addAuthor(tolkin);
		book6.setPublisher(publisher2);
		book6.setPrice(23.95);
		book6.setPages(500);
		
		entityManager.getTransaction().begin();
		entityManager.persist(book1);
		entityManager.persist(book2);
		entityManager.persist(book3);
		entityManager.persist(book4);
		entityManager.persist(book5);
		entityManager.persist(book6);
		entityManager.getTransaction().commit();
	}

}
