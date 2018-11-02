/**
 *
 */
package de.cofinpro.training.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author gtudan
 */
public class BookstoreApp {

    EntityManagerFactory emFactory;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new BookstoreApp();

    }

    public BookstoreApp() {
        emFactory = Persistence.createEntityManagerFactory("Bookstore");
    }

}
