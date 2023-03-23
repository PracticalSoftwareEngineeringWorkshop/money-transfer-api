package com.psew.moneytransferapi;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.repositories.AccountRepository;
import com.psew.moneytransferapi.services.AccountService;
import com.psew.moneytransferapi.util.TestDataGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class MockAccountServiceUnitTest {

    @MockBean
    private AccountRepository repository;

    @Autowired
    private AccountService accountService;

    @Before
    public void setupAccountRepositoryMock() {
        given(repository.save(TestDataGenerator.createAccount())).willReturn(TestDataGenerator.createAccount());
    }

    @Test
    public void shouldValidateAgeAndSaveAccount() {

        Account myAccount = Account.builder()
                .firstName("Jane")
                .lastName("Doe")
                .phoneNumber("+4911111111")
                .email("janedoe@example.com")
                .pin(1234)
                .balance(400.00)
                .dateOfBirth(LocalDate.of(1988, 1, 1))
                .isVerified(Boolean.TRUE)
                .build();

        accountService.createAccount(myAccount);

        assertThat(repository.save(myAccount)).isEqualTo(myAccount);
    }

    @After
    public void resetAccountRepositoryMock() {
        Mockito.reset(repository);
    }
}
