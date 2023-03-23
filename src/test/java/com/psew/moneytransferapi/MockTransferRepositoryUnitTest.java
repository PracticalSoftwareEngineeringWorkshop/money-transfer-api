package com.psew.moneytransferapi;

import com.psew.moneytransferapi.repositories.TransferRepository;
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
public class MockTransferRepositoryUnitTest {

    @MockBean
    private TransferRepository repository;

    @Before
    public void setupTransferRepositoryMock() {
        given(repository.count()).willReturn(5L);
    }

    @Test
    public void shouldReturn5Transfers() {
        assertThat(repository.count()).isEqualTo(5L);
    }

    @After
    public void resetTransferRepositoryMock() {
        Mockito.reset(repository);
    }
}
