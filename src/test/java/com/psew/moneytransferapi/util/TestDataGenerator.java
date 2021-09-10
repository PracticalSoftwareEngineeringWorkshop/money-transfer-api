package com.psew.moneytransferapi.util;

import com.psew.moneytransferapi.domains.Account;

import java.time.LocalDate;

public class TestDataGenerator {

    public static Account createAccount() {

        return Account.builder()
                .firstName("Jane")
                .lastName("Doe")
                .phoneNumber("+4911111111")
                .email("janedoe@example.com")
                .pin(1234)
                .balance(400.00)
                .dateOfBirth(LocalDate.of(1988, 1, 1))
                .isVerified(Boolean.TRUE)
                .build();
    }
}
