package com.psew.moneytransferapi;

import com.psew.moneytransferapi.domains.Account;

public class TestDataGenerator {

    public static Account createAccount() {

        return Account.builder()
                .firstName("Jane")
                .lastName("Doe")
                .phoneNumber("+4911111111")
                .email("janedoe@example.com")
                .pin(1234)
                .balance(400.00)
                .isVerified(Boolean.TRUE)
                .build();
    }
}
