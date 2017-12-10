package io.github.davidwadden.websample;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unused")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CustomerRepository repository;

    @Transactional
    @Test
    public void findAll() throws Exception {
        Customer customer = Customer.builder()
            .firstName("some-first-name")
            .lastName("some-last-name")
            .build();

        entityManager.persist(customer);

        Iterable<Customer> customers = repository.findAll();
        assertThat(customers).containsExactly(customer);
    }

    @Transactional
    @Test
    public void findOne() throws Exception {
        Customer customerToSave = Customer.builder()
            .firstName("some-first-name")
            .lastName("some-last-name")
            .build();

        entityManager.persist(customerToSave);
        assertThat(customerToSave.getId()).isNotNull();

        Customer customer = repository.findOne(customerToSave.getId());
        assertThat(customer).isNotNull();
    }

    @Transactional
    @Test
    public void findOne_notFound() throws Exception {
        Customer customer = repository.findOne(0L);
        assertThat(customer).isNull();
    }


}
