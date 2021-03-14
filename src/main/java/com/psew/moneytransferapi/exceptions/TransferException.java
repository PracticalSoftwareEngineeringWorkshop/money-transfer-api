package com.psew.moneytransferapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Definition of all Transfer related exceptions with specific message and code
 *
 * @author Biniam Asnake.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransferException extends RuntimeException {

    public static final String INVALID_TRANSFER_ID_EXCEPTION = "Transfer is invalid.";
    public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Insufficient balance";
    public static final String SAME_ACCOUNT_TRANSFER_EXCEPTION = "Both sender and receiver cannot be the same account";

    public TransferException(String message) {
        super(message);
    }
}