package com.psew.moneytransferapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Definition of all Account related exceptions with specific message and code
 *
 * @author Biniam Asnake.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountException extends RuntimeException {

	public static final String ACCOUNT_NOT_FOUND_EXCEPTION = "The account is not found.";
	public static final String INVALID_ACCOUNT_EXCEPTION = "Invalid account id";
	public static final String UNDER_AGE_ACCOUNT_CREATION_EXCEPTION = "User under the age of 15 years cannot register.";

	public AccountException(String message) {
		super(message);
	}
}
