package com.psew.moneytransferapi;

import com.psew.moneytransferapi.domains.Account;
import com.psew.moneytransferapi.domains.Transfer;
import com.psew.moneytransferapi.exceptions.TransferException;
import com.psew.moneytransferapi.services.AccountService;
import com.psew.moneytransferapi.services.TransferService;
import com.psew.moneytransferapi.wrappers.TransferRequest;
import com.psew.moneytransferapi.wrappers.TransferStatus;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@AutoConfigureTestDatabase
public class TransferServiceIntegrationTest {

    @Autowired
    private TransferService transferService;

    @Autowired
    private AccountService accountService;

    private TransferRequest transferRequest;

    @Before
    public void setUp() {

        transferRequest = TransferRequest.builder()
                .senderId(1L)
                .receiverId(2L)
                .amount(300.00)
                .reason("test reason")
                .build();
    }

    @Test
    public void shouldTransferMoneyFromOneValidAccountToAnotherOne() {

        Transfer createdTransfer = transferService.transferMoney(transferRequest);

        assertNotNull(createdTransfer.getId());
        assertEquals(transferRequest.getSenderId(), createdTransfer.getSender().getId());
        assertEquals(transferRequest.getReceiverId(), createdTransfer.getReceiver().getId());
        assertEquals(transferRequest.getAmount(), createdTransfer.getAmount());
        assertEquals(transferRequest.getReason(), createdTransfer.getReason());
        assertEquals(TransferStatus.SUCCESSFUL, createdTransfer.getStatus());

        Account senderAccount = accountService.getAccountById(1L);
        Account receiverAccount = accountService.getAccountById(2L);

        assert senderAccount.getBalance() == 1100.0;
        assert receiverAccount.getBalance() == 1900.0;
    }

    @Test
    public void shouldFailToTransferMoneyIfBalanceIsInsufficient() {
        Exception exception = assertThrows(TransferException.class, () -> {

            transferRequest.setAmount(1000000.00);

            transferService.transferMoney(transferRequest);
        });

        String expectedMessage = "Insufficient balance";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test(expected = TransferException.class)
    public void shouldFailToTransferMoneyToTheSameAccount() {

        transferRequest.setReceiverId(1L);

        transferService.transferMoney(transferRequest);
    }
}
