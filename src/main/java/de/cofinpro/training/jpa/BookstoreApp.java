package de.cofinpro.training.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author gtudan
 */
public class BookstoreApp {

    private EntityManagerFactory emFactory;

    public static void main(String[] args) {
        new BookstoreApp();

    }

    private BookstoreApp() {
        emFactory = Persistence.createEntityManagerFactory("Bookstore");
    }

}
