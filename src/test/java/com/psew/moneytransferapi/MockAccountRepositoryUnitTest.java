package com.psew.moneytransferapi;

import com.psew.moneytransferapi.repositories.AccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class MockAccountRepositoryUnitTest {

    @MockBean
    private AccountRepository repository;

    @Before
    public void setupAccountRepositoryMock() {
        given(repository.count()).willReturn(3L);
    }

    @Test
    public void shouldReturn3Accounts() {
        assertThat(repository.count()).isEqualTo(3L);
    }

    @After
    public void resetAccountRepositoryMock() {
        Mockito.reset(repository);
    }
}
