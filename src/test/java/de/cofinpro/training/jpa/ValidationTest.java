package de.cofinpro.training.jpa;

import org.slf4j.Logger;
import de.cofinpro.training.jpa.model.Book;
import de.cofinpro.training.jpa.model.Publisher;
import org.junit.jupiter.api.*;
import org.slf4j.LoggerFactory;


import javax.validation.*;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;

/**
 * @author Gregor Tudan, Cofinpro AG
 */
class ValidationTest {

    private static ValidatorFactory validatorFactory;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @BeforeAll
    static void createFactory() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
    }

    @AfterAll
    static void tearDown() {
        validatorFactory.close();
    }

    //
    // Make these tests pass
    //

    @Test
    @Tag("exercise")
    void validateBook() {
        Book book = new Book("");
        final Set<ConstraintViolation<Book>> violations = validatorFactory.getValidator().validate(book);

        assertThat(violations, is(not(empty())));
        violations.forEach(v -> log.debug(v.toString()));

        assertThat(violations, hasItem(hasProperty("propertyPath", hasToString("title"))));
    }

    @Test
    @Tag("exercise")
    void validateBookWithPublisher() {
        Book book = new Book("Foo");
        book.setPublisher(new Publisher());
        final Set<ConstraintViolation<Book>> violations = validatorFactory.getValidator().validate(book);

        assertThat(violations, is(not(empty())));
        violations.forEach(v -> log.debug(v.toString()));

        assertThat(violations, hasItem(hasProperty("propertyPath", hasToString("publisher.name"))));
    }
}
