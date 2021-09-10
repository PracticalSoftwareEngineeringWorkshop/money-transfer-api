package com.psew.moneytransferapi;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.repositories.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void shouldCreateAnAccountInDB() {

        // Builder pattern (@Builder)
        // GIVEN
        Account account = Account.builder()
                .firstName("John")
                .lastName("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .phoneNumber("12345555")
                .email("john@doe.com")
                .pin(1234)
                .balance(450.0)
                .build();

        // WHEN
        Account savedAccount = accountRepository.save(account);

        // THEN
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isEqualTo(4L);

        assertThat(savedAccount.getFirstName()).isEqualTo("John");
        assertThat(savedAccount.getFirstName()).isEqualTo(account.getFirstName());
    }
}
