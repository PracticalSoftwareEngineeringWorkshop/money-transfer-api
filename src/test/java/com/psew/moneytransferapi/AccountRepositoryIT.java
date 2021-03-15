package com.psew.moneytransferapi;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class AccountRepositoryIT {

    @Autowired
    private TestEntityManager mgr;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldPersistAndRetrieveAccount() {

        Long id = mgr.persistAndGetId(TestDataGenerator.createAccount(), Long.class);

        Account account = accountRepository.findById(id).get();

        assertThat(account.getFirstName()).isEqualTo("Jane");
        assertThat(account.getLastName()).isEqualTo("Doe");
    }
}